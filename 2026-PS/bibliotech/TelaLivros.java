import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class TelaLivros extends JFrame {

    private final LivroDAO dao = new LivroDAO();

    private final DefaultTableModel modelo;
    private final JTable tabela;

    // Campo para pesquisar pelo ID
    private final JTextField campoId =
        new JTextField(8);

    // Campo para pesquisar pelo título
    private final JTextField campoTitulo =
        new JTextField(18);

    private final JLabel status =
        new JLabel("Pronto.");

    public TelaLivros() {

        setTitle("BiblioTech - Consulta de Livros");
        setSize(1150, 500);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(
            JFrame.DISPOSE_ON_CLOSE
        );

        setLayout(new BorderLayout(8, 8));

        // Etapa 1: Adicionando a coluna Capa
        modelo = new DefaultTableModel(
            new Object[]{
                "ID", "Capa", "Título", "Autor", "Ano"
            }, 0
        ) {
            @Override
            public boolean isCellEditable(
                    int linha, int coluna) {
                return false;
            }
        };

        tabela = new JTable(modelo);

        // Etapa 4: Configurando a apresentação da imagem
        tabela.setRowHeight(85);

        tabela.getColumnModel()
            .getColumn(1)
            .setPreferredWidth(90);

        tabela.getColumnModel()
            .getColumn(1)
            .setCellRenderer(
                new javax.swing.table.DefaultTableCellRenderer() {

                    @Override
                    public java.awt.Component
                            getTableCellRendererComponent(
                                JTable tabela,
                                Object valor,
                                boolean selecionada,
                                boolean foco,
                                int linha,
                                int coluna) {

                        JLabel label = new JLabel();

                        label.setHorizontalAlignment(
                            JLabel.CENTER
                        );

                        label.setOpaque(true);

                        label.setBackground(
                            selecionada
                            ? tabela.getSelectionBackground()
                            : tabela.getBackground()
                        );

                        if (valor instanceof ImageIcon) {
                            label.setIcon((ImageIcon) valor);
                        } else {
                            label.setText("Sem capa");
                        }

                        return label;
                    }
                }
            );

        add(
            new JScrollPane(tabela),
            BorderLayout.CENTER
        );

        // Painel de controles
        JPanel controles = new JPanel(
            new FlowLayout(FlowLayout.LEFT)
        );

        JButton atualizar =
            new JButton("Atualizar listagem");

        JButton buscar =
            new JButton("Buscar por ID");

        JButton mostrarTodos =
            new JButton("Mostrar todos");

        JButton botaoBuscarTitulo =
            new JButton("Buscar por título");

        JButton limpar =
            new JButton("Limpar pesquisa");

        JButton botaoCapa =
            new JButton("Selecionar capa");

        // Componentes da busca por ID
        controles.add(new JLabel("ID do livro:"));
        controles.add(campoId);
        controles.add(buscar);

        // Componentes da busca por título
        controles.add(new JLabel("Título:"));
        controles.add(campoTitulo);
        controles.add(botaoBuscarTitulo);

        // Outros botões
        controles.add(atualizar);
        controles.add(mostrarTodos);
        controles.add(limpar);
        controles.add(botaoCapa);

        add(controles, BorderLayout.NORTH);
        add(status, BorderLayout.SOUTH);

        // Eventos dos botões
        atualizar.addActionListener(
            e -> carregarLivros()
        );

        mostrarTodos.addActionListener(
            e -> carregarLivros()
        );

        buscar.addActionListener(
            e -> buscarLivro()
        );

        botaoBuscarTitulo.addActionListener(
            e -> buscarPorTitulo()
        );

        campoTitulo.addActionListener(
            e -> buscarPorTitulo()
        );

        limpar.addActionListener(e -> {
            campoId.setText("");
            campoTitulo.setText("");
            carregarLivros();
        });

        botaoCapa.addActionListener(
            e -> selecionarCapa()
        );

        carregarLivros();
    }

    // Etapa 2: Criando as miniaturas
    private ImageIcon carregarMiniatura(
            String caminho) {

        if (caminho == null || caminho.isBlank()) {
            return null;
        }

        java.io.File arquivo =
            new java.io.File(caminho);

        if (!arquivo.isFile()) {
            return null;
        }

        try {

            java.awt.image.BufferedImage imagem =
                javax.imageio.ImageIO.read(arquivo);

            if (imagem == null) {
                return null;
            }

            Image reduzida = imagem.getScaledInstance(
                55, 75, Image.SCALE_SMOOTH
            );

            return new ImageIcon(reduzida);

        } catch (java.io.IOException e) {
            return null;
        }
    }

    // LISTAR TODOS OS LIVROS
    private void carregarLivros() {

        modelo.setRowCount(0);

        List<Livro> livros =
            dao.listarLivros();

        for (Livro livro : livros) {
            adicionarLinha(livro);
        }

        status.setText(
            "Livros apresentados: " + livros.size()
        );
    }

    // Etapa 3: Atualizando adicionarLinha()
    private void adicionarLinha(Livro livro) {

        modelo.addRow(new Object[]{
            livro.getId(),
            carregarMiniatura(livro.getCapa()),
            livro.getTitulo(),
            livro.getAutor(),
            livro.getAno()
        });
    }

    // BUSCAR LIVRO POR ID
    private void buscarLivro() {

        String texto = campoId.getText().trim();

        int id;

        try {
            id = Integer.parseInt(texto);

            if (id <= 0) {
                throw new NumberFormatException();
            }

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                this,
                "Informe um ID inteiro positivo.",
                "Entrada inválida",
                JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        Livro livro = dao.buscarLivroPorId(id);

        modelo.setRowCount(0);

        if (livro == null) {

            status.setText(
                "Nenhum livro encontrado para o ID " + id
            );

            JOptionPane.showMessageDialog(
                this,
                "Livro não encontrado."
            );

        } else {

            adicionarLinha(livro);

            status.setText(
                "Livro encontrado: " + livro.getTitulo()
            );
        }
    }

    // BUSCAR LIVROS PELO TÍTULO
    private void buscarPorTitulo() {

        String titulo =
            campoTitulo.getText().trim();

        if (titulo.isEmpty()) {

            JOptionPane.showMessageDialog(
                this,
                "Informe o título do livro.",
                "Campo obrigatório",
                JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        List<Livro> livros =
            dao.buscarLivrosPorTitulo(titulo);

        modelo.setRowCount(0);

        for (Livro livro : livros) {
            adicionarLinha(livro);
        }

        status.setText(
            "Livros encontrados: " + livros.size()
        );

        if (livros.isEmpty()) {

            JOptionPane.showMessageDialog(
                this,
                "Nenhum livro encontrado."
            );
        }
    }

    // SELECIONAR E SALVAR CAPA DO LIVRO
    private void selecionarCapa() {

        int linha = tabela.getSelectedRow();

        if (linha == -1) {
            JOptionPane.showMessageDialog(
                this,
                "Selecione um livro na tabela."
            );
            return;
        }

        int linhaModelo =
            tabela.convertRowIndexToModel(linha);

        int id = (int) modelo.getValueAt(
            linhaModelo, 0
        );

        JFileChooser seletor = new JFileChooser();

        seletor.setDialogTitle(
            "Selecione a capa do livro"
        );

        seletor.setFileFilter(
            new FileNameExtensionFilter(
                "Imagens JPG e PNG", "jpg", "jpeg", "png"
            )
        );

        if (seletor.showOpenDialog(this)
                != JFileChooser.APPROVE_OPTION) {
            return;
        }

        Path destino = null;

        try {

            destino = Path.of(
                ImagemLivro.salvar(
                    seletor.getSelectedFile().toPath(),
                    id
                )
            );

            boolean atualizado =
                dao.atualizarCapa(
                    id, destino.toString().replace('\\', '/')
                );

            if (!atualizado) {

                Files.deleteIfExists(destino);

                JOptionPane.showMessageDialog(
                    this,
                    "Não foi possível atualizar o livro."
                );

                return;
            }

            carregarLivros();

            JOptionPane.showMessageDialog(
                this,
                "Capa salva com sucesso!"
            );

        } catch (Exception ex) {

            if (destino != null) {
                try {
                    Files.deleteIfExists(destino);
                } catch (IOException ignorado) {
                }
            }

            JOptionPane.showMessageDialog(
                this,
                "Erro ao salvar capa: " +
                ex.getMessage()
            );
        }
    }

    // INICIAR A INTERFACE
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new TelaLivros().setVisible(true);
        });
    }
}
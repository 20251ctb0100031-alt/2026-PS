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

    // LISTAR TODOS OS LIVROS (Atualizado na Parte 21 - Etapa 3)
    private void carregarLivros() {

        try {

            List<Livro> livros =
                dao.listarLivros();

            modelo.setRowCount(0);

            for (Livro livro : livros) {
                adicionarLinha(livro);
            }

            status.setText(
                "Livros encontrados: " + livros.size()
            );

        } catch (PersistenciaException e) {

            status.setText(
                "Erro ao consultar o banco."
            );

            System.err.println(e.getMessage());
            e.printStackTrace();

            JOptionPane.showMessageDialog(
                this,
                "Não foi possível carregar os livros. " +
                "Verifique a conexão com o MariaDB.",
                "Erro de conexão",
                JOptionPane.ERROR_MESSAGE
            );
        }
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

    // BUSCAR LIVRO POR ID (Atualizado na Parte 21 - Etapas 1 e 3)
    private void buscarLivro() {

        String texto = campoId.getText().trim();

        int id;

        try {

            id = Integer.parseInt(texto);

            if (id <= 0) {
                throw new IllegalArgumentException(
                    "O ID deve ser positivo."
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                this,
                "Informe um número inteiro válido."
            );

            return;

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(
                this,
                e.getMessage()
            );

            return;
        }

        try {

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

        } catch (PersistenciaException e) {

            status.setText(
                "Erro ao consultar o banco."
            );

            System.err.println(e.getMessage());
            e.printStackTrace();

            JOptionPane.showMessageDialog(
                this,
                "Não foi possível buscar o livro pelo ID. " +
                "Verifique a conexão com o MariaDB.",
                "Erro de conexão",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // BUSCAR LIVROS PELO TÍTULO (Atualizado na Parte 21 - Etapa 3)
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

        try {

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

        } catch (PersistenciaException e) {

            status.setText(
                "Erro ao consultar o banco."
            );

            System.err.println(e.getMessage());
            e.printStackTrace();

            JOptionPane.showMessageDialog(
                this,
                "Não foi possível buscar os livros pelo título. " +
                "Verifique a conexão com o MariaDB.",
                "Erro de conexão",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // SELECIONAR E SALVAR CAPA DO LIVRO (Atualizado na Parte 21 - Etapa 4)
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

        Path arquivoSelecionado = seletor.getSelectedFile().toPath();
        Path destino = null;

        try {

            String caminho = ImagemLivro.salvar(
                arquivoSelecionado, id
            );

            System.out.println(
                "Imagem salva em: " + caminho
            );

            destino = Path.of(caminho);

            boolean atualizado =
                dao.atualizarCapa(
                    id, caminho.replace('\\', '/')
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

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                this,
                "Não foi possível salvar a imagem.",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }

    // INICIAR A INTERFACE
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new TelaLivros().setVisible(true);
        });
    }
}
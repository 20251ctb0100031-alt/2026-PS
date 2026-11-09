
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TelaLivros extends JFrame {

    private final LivroDAO dao = new LivroDAO();

    private final DefaultTableModel modelo;
    private final JTable tabela;

    // Campo para pesquisar pelo ID
    private final JTextField campoId =
        new JTextField(8);

    // NOVO: Campo para pesquisar pelo título
    private final JTextField campoTitulo =
        new JTextField(18);

    private final JLabel status =
        new JLabel("Pronto.");

    public TelaLivros() {

        setTitle("BiblioTech - Consulta de Livros");
        setSize(1000, 500);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(
            JFrame.DISPOSE_ON_CLOSE
        );

        setLayout(new BorderLayout(8, 8));

        modelo = new DefaultTableModel(
            new Object[]{
                "ID", "Título", "Autor", "Ano"
            }, 0
        ) {
            @Override
            public boolean isCellEditable(
                int linha, int coluna) {
                return false;
            }
        };

        tabela = new JTable(modelo);

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

        // NOVO: Botão de busca por título
        JButton botaoBuscarTitulo =
            new JButton("Buscar por título");

        // NOVO: Botão para limpar pesquisa
        JButton limpar =
            new JButton("Limpar pesquisa");

        // Componentes da busca por ID
        controles.add(new JLabel("ID do livro:"));
        controles.add(campoId);
        controles.add(buscar);

        // NOVOS componentes da busca por título
        controles.add(new JLabel("Título:"));
        controles.add(campoTitulo);
        controles.add(botaoBuscarTitulo);

        // Outros botões
        controles.add(atualizar);
        controles.add(mostrarTodos);
        controles.add(limpar);

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

        // NOVO: Executar busca por título
        botaoBuscarTitulo.addActionListener(
            e -> buscarPorTitulo()
        );

        // NOVO: Pesquisar pressionando Enter
        campoTitulo.addActionListener(
            e -> buscarPorTitulo()
        );

        // NOVO: Limpar campos e restaurar listagem
        limpar.addActionListener(e -> {
            campoId.setText("");
            campoTitulo.setText("");
            carregarLivros();
        });

        carregarLivros();
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

    // ADICIONAR LIVRO NA TABELA
    private void adicionarLinha(Livro livro) {

        modelo.addRow(new Object[]{
            livro.getId(),
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

    // NOVO MÉTODO DA AULA 44:
    // BUSCAR LIVROS PELO TÍTULO
    private void buscarPorTitulo() {

        String titulo =
            campoTitulo.getText().trim();

        // Verificar se o campo está vazio
        if (titulo.isEmpty()) {

            JOptionPane.showMessageDialog(
                this,
                "Informe o título do livro.",
                "Campo obrigatório",
                JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Consultar o banco de dados
        List<Livro> livros =
            dao.buscarLivrosPorTitulo(titulo);

        // Limpar resultados anteriores
        modelo.setRowCount(0);

        // Apresentar todos os livros encontrados
        for (Livro livro : livros) {
            adicionarLinha(livro);
        }

        // Atualizar quantidade de resultados
        status.setText(
            "Livros encontrados: " + livros.size()
        );

        // Informar quando não encontrar resultados
        if (livros.isEmpty()) {

            JOptionPane.showMessageDialog(
                this,
                "Nenhum livro encontrado."
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

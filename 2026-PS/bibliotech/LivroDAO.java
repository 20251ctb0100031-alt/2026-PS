
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    // Método da Aula 42: Adicionar um novo livro
    public void adicionarLivro(Livro livro) {

        String sql = "INSERT INTO livros (titulo, autor, ano) VALUES (?, ?, ?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setString(1, livro.getTitulo());
            comando.setString(2, livro.getAutor());
            comando.setInt(3, livro.getAno());

            comando.executeUpdate();

            System.out.println("Livro adicionado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao adicionar livro: " + e.getMessage());
        }
    }

    // Método da Aula 42: Atualizar os dados de um livro
    public void atualizarLivro(Livro livro) {

        String sql = "UPDATE livros SET titulo = ?, autor = ?, ano = ? WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setString(1, livro.getTitulo());
            comando.setString(2, livro.getAutor());
            comando.setInt(3, livro.getAno());
            comando.setInt(4, livro.getId());

            comando.executeUpdate();

            System.out.println("Livro atualizado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar livro: " + e.getMessage());
        }
    }

    // Método da Aula 42: Excluir um livro pelo ID
    public void excluirLivro(int id) {

        String sql = "DELETE FROM livros WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, id);

            comando.executeUpdate();

            System.out.println("Livro excluído com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao excluir livro: " + e.getMessage());
        }
    }

    // Método da Aula 43: Listar todos os livros
    public List<Livro> listarLivros() {

        List<Livro> livros = new ArrayList<>();

        String sql =
            "SELECT id, titulo, autor, ano " +
            "FROM livros ORDER BY titulo";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql);
             ResultSet resultado = comando.executeQuery()) {

            while (resultado.next()) {

                int id = resultado.getInt("id");
                String titulo = resultado.getString("titulo");
                String autor = resultado.getString("autor");
                int ano = resultado.getInt("ano");

                Livro livro = new Livro(id, titulo, autor, ano);

                livros.add(livro);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar livros: " + e.getMessage());
        }

        return livros;
    }

    // Método da Aula 43: Buscar livro por ID
    public Livro buscarLivroPorId(int id) {

        String sql =
            "SELECT id, titulo, autor, ano " +
            "FROM livros WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, id);

            try (ResultSet resultado = comando.executeQuery()) {

                if (resultado.next()) {

                    int codigo = resultado.getInt("id");
                    String titulo = resultado.getString("titulo");
                    String autor = resultado.getString("autor");
                    int ano = resultado.getInt("ano");

                    return new Livro(codigo, titulo, autor, ano);
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar livro: " + e.getMessage());
        }

        return null;
    }

    // NOVO MÉTODO DA AULA 44: Buscar livros pelo título
    public List<Livro> buscarLivrosPorTitulo(String titulo) {

        List<Livro> livros = new ArrayList<>();

        String sql =
            "SELECT id, titulo, autor, ano " +
            "FROM livros " +
            "WHERE titulo LIKE ? " +
            "ORDER BY titulo";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setString(1, "%" + titulo + "%");

            try (ResultSet resultado = comando.executeQuery()) {

                while (resultado.next()) {

                    Livro livro = new Livro(
                        resultado.getInt("id"),
                        resultado.getString("titulo"),
                        resultado.getString("autor"),
                        resultado.getInt("ano")
                    );

                    livros.add(livro);
                }
            }

        } catch (SQLException e) {

            System.out.println(
                "Erro ao buscar livros: " + e.getMessage()
            );
        }

        return livros;
    }
}

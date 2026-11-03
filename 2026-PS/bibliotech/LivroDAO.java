import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    // Método para ADICIONAR um livro no MariaDB
    public boolean adicionarLivro(String titulo, String autor, int ano) {

        String sql = 
            "INSERT INTO livros (titulo, autor, ano) " +
            "VALUES (?, ?, ?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setString(1, titulo);
            comando.setString(2, autor);
            comando.setInt(3, ano);

            return comando.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao adicionar: " + e.getMessage());
            return false;
        }
    }

    // Método para ATUALIZAR um livro no MariaDB
    public boolean atualizarLivro(int id, String titulo, String autor, int ano) {

        String sql = 
            "UPDATE livros " +
            "SET titulo = ?, autor = ?, ano = ? " +
            "WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setString(1, titulo);
            comando.setString(2, autor);
            comando.setInt(3, ano);
            comando.setInt(4, id);

            return comando.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
            return false;
        }
    }

    // Método para EXCLUIR um livro no MariaDB
    public boolean excluirLivro(int id) {

        String sql = "DELETE FROM livros WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, id);

            return comando.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
            return false;
        }
    }

    // Método para LISTAR todos os livros em formato de texto
    public List<String> listarLivros() {

        List<String> livros = new ArrayList<>();

        // Se quiser responder ao Desafio Rápido, troque por: "ORDER BY ano DESC"
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

                String livro = id + " - " + titulo + " - " + autor + " (" + ano + ")";
                livros.add(livro);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar livros: " + e.getMessage());
        }

        return livros;
    }
}
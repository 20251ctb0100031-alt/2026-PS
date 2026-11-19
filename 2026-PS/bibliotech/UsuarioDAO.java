import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public boolean atualizarFotoUsuario(int id, String caminho) {
        String sql = "UPDATE usuarios SET foto = ? WHERE id = ?";

        try (
            Connection conexao = Conexao.conectar();
            PreparedStatement comando = conexao.prepareStatement(sql)
        ) {
            comando.setString(1, caminho);
            comando.setInt(2, id);

            return comando.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public String buscarFotoUsuario(int id) {
        String sql = "SELECT foto FROM usuarios WHERE id = ?";

        try (
            Connection conexao = Conexao.conectar();
            PreparedStatement comando = conexao.prepareStatement(sql)
        ) {
            comando.setInt(1, id);
            ResultSet rs = comando.executeQuery();

            if (rs.next()) {
                return rs.getString("foto");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
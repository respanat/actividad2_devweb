package Models.Repositories;

import Models.Entities.Usuario;
import Libs.orm.ormconfig; // Importa la clase para la conexión a la BD
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {

    private Connection getConnection() throws SQLException {
        return ormconfig.getConnection(); // Obtiene la conexión desde la clase de configuración ORM
    }

    public void agregar(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO Usuario (username, password, nombre, email) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, usuario.getUsername());
            pstmt.setString(2, usuario.getPassword());
            pstmt.setString(3, usuario.getNombre());
            pstmt.setString(4, usuario.getEmail());
            pstmt.executeUpdate(); // Ejecuta la inserción
        }
    }

    public Usuario buscar(int id) throws SQLException {
        String sql = "SELECT id, username, password, nombre, email FROM Usuario WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) { // Ejecuta la consulta y obtiene el resultado
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getInt("id"));
                    usuario.setUsername(rs.getString("username"));
                    usuario.setPassword(rs.getString("password"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setEmail(rs.getString("email"));
                    return usuario;
                }
            }
        }
        return null; // Retorna null si no se encuentra el usuario
    }

    public List<Usuario> listarTodos() throws SQLException {
        String sql = "SELECT id, username, password, nombre, email FROM Usuario";
        List<Usuario> usuarios = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) { // Ejecuta la consulta y obtiene todos los resultados
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setUsername(rs.getString("username"));
                usuario.setPassword(rs.getString("password"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setEmail(rs.getString("email"));
                usuarios.add(usuario); // Agrega cada usuario a la lista
            }
        }
        return usuarios;
    }

    public void actualizar(Usuario usuario) throws SQLException {
        String sql = "UPDATE Usuario SET username = ?, password = ?, nombre = ?, email = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, usuario.getUsername());
            pstmt.setString(2, usuario.getPassword());
            pstmt.setString(3, usuario.getNombre());
            pstmt.setString(4, usuario.getEmail());
            pstmt.setInt(5, usuario.getId());
            pstmt.executeUpdate(); // Ejecuta la actualización
        }
    }

    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM Usuario WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate(); // Ejecuta la eliminación
        }
    }

    public Usuario buscarPorUsername(String username) throws SQLException {
        String sql = "SELECT id, username, password, nombre, email FROM Usuario WHERE username = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getInt("id"));
                    usuario.setUsername(rs.getString("username"));
                    usuario.setPassword(rs.getString("password"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setEmail(rs.getString("email"));
                    return usuario;
                }
            }
        }
        return null;
    }
}
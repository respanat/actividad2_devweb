package Models.Repositories;

import Libs.orm.ormconfig;
import Models.Entities.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositoryImpl implements UsuarioRepository {

    // Implementación del método para obtener un usuario por su ID
    @Override
    public Usuario obtenerUsuarioPorId(int id) {
        String sql = "SELECT id, username, password, nombre, email FROM Usuario WHERE id = ?";
        try (Connection conn = ormconfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Usuario(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("nombre"), rs.getString("email"));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener usuario por ID: " + e.getMessage());
        }
        return null;
    }

    // Implementación del método para obtener todos los usuarios
    @Override
    public List<Usuario> obtenerTodosUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT id, username, password, nombre, email FROM Usuario";
        try (Connection conn = ormconfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                usuarios.add(new Usuario(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("nombre"), rs.getString("email")));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener todos los usuarios: " + e.getMessage());
        }
        return usuarios;
    }

    // Implementación del método para guardar un nuevo usuario
    @Override
    public void guardarUsuario(Usuario usuario) {
        String sql = "INSERT INTO Usuario (username, password, nombre, email) VALUES (?, ?, ?, ?)";
        try (Connection conn = ormconfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, usuario.getUsername());
            pstmt.setString(2, usuario.getPassword());
            pstmt.setString(3, usuario.getNombre());
            pstmt.setString(4, usuario.getEmail());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al guardar usuario: " + e.getMessage());
        }
    }

    // Implementación del método para actualizar un usuario existente
    @Override
    public void actualizarUsuario(Usuario usuario) {
        String sql = "UPDATE Usuario SET username = ?, password = ?, nombre = ?, email = ? WHERE id = ?";
        try (Connection conn = ormconfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, usuario.getUsername());
            pstmt.setString(2, usuario.getPassword());
            pstmt.setString(3, usuario.getNombre());
            pstmt.setString(4, usuario.getEmail());
            pstmt.setInt(5, usuario.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar usuario: " + e.getMessage());
        }
    }

    // Implementación del método para eliminar un usuario por su ID
    @Override
    public void eliminarUsuario(int id) {
        String sql = "DELETE FROM Usuario WHERE id = ?";
        try (Connection conn = ormconfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar usuario: " + e.getMessage());
        }
    }

    // Implementación del método para buscar usuarios por nombre (ejemplo básico)
    @Override
    public List<Usuario> buscarUsuariosPorNombre(String nombre) {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT id, username, password, nombre, email FROM Usuario WHERE nombre LIKE ?";
        try (Connection conn = ormconfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + nombre + "%"); // Usamos LIKE para buscar coincidencias parciales
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                usuarios.add(new Usuario(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("nombre"), rs.getString("email")));
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar usuarios por nombre: " + e.getMessage());
        }
        return usuarios;
    }

    // Implementación del método para obtener un usuario por su nombre de usuario
    @Override
    public Usuario obtenerUsuarioPorUsername(String username) {
        String sql = "SELECT id, username, password, nombre, email FROM Usuario WHERE username = ?";
        try (Connection conn = ormconfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Usuario(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("nombre"), rs.getString("email"));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener usuario por username: " + e.getMessage());
        }
        return null;
    }

    // Implementación del método para obtener un usuario por su correo electrónico
    @Override
    public Usuario obtenerUsuarioPorEmail(String email) {
        String sql = "SELECT id, username, password, nombre, email FROM Usuario WHERE email = ?";
        try (Connection conn = ormconfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Usuario(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("nombre"), rs.getString("email"));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener usuario por email: " + e.getMessage());
        }
        return null;
    }
}
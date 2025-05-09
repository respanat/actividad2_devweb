package Models.Services;

import Models.Entities.Usuario;
import Models.Repositories.UsuarioRepository;
import java.sql.SQLException;
import java.util.List;

public class UsuarioService {

    private final UsuarioRepository usuarioRepository = new UsuarioRepository();

    public void agregarUsuario(Usuario usuario) throws SQLException {
        // Aquí se podrían agregar validaciones de negocio antes de guardar
        usuarioRepository.agregar(usuario);
    }

    public Usuario obtenerUsuario(int id) throws SQLException {
        return usuarioRepository.buscar(id);
    }

    public List<Usuario> listarTodosUsuarios() throws SQLException {
        return usuarioRepository.listarTodos();
    }

    public void actualizarUsuario(Usuario usuario) throws SQLException {
        // Aquí se podrían agregar validaciones de negocio antes de actualizar
        usuarioRepository.actualizar(usuario);
    }

    public void eliminarUsuario(int id) throws SQLException {
        usuarioRepository.eliminar(id);
    }

    public Usuario autenticarUsuario(String username, String password) throws SQLException {
        Usuario usuario = usuarioRepository.buscarPorUsername(username);
        if (usuario != null && usuario.getPassword().equals(password)) {
            return usuario;
        }
        return null;
    }
}
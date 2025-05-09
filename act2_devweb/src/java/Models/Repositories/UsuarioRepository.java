package Models.Repositories;

import Models.Entities.Usuario;
import java.util.List;

public interface UsuarioRepository {
    Usuario obtenerUsuarioPorId(int id);
    List<Usuario> obtenerTodosUsuarios();
    void guardarUsuario(Usuario usuario);
    void actualizarUsuario(Usuario usuario);
    void eliminarUsuario(int id);
    List<Usuario> buscarUsuariosPorNombre(String nombre);
    Usuario obtenerUsuarioPorUsername(String username);
    Usuario obtenerUsuarioPorEmail(String email);
}
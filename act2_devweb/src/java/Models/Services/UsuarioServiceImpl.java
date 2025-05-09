package Models.Services;

import Models.Entities.Usuario;
import Models.Repositories.UsuarioRepository;
import Models.Repositories.UsuarioRepositoryImpl;
import java.util.List;

public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository = new UsuarioRepositoryImpl();

    // Implementación del método para obtener un usuario por su ID
    @Override
    public Usuario obtenerUsuarioPorId(int id) {
        return usuarioRepository.obtenerUsuarioPorId(id);
    }

    // Implementación del método para obtener todos los usuarios
    @Override
    public List<Usuario> obtenerTodosUsuarios() {
        return usuarioRepository.obtenerTodosUsuarios();
    }

    // Implementación del método para guardar un nuevo usuario
    @Override
    public void guardarUsuario(Usuario usuario) {
        // Aquí podríamos agregar lógica de negocio o validaciones antes de guardar
        usuarioRepository.guardarUsuario(usuario);
    }

    // Implementación del método para actualizar un usuario existente
    @Override
    public void actualizarUsuario(Usuario usuario) {
        // Aquí podríamos agregar lógica de negocio o validaciones antes de actualizar
        usuarioRepository.actualizarUsuario(usuario);
    }

    // Implementación del método para eliminar un usuario por su ID
    @Override
    public void eliminarUsuario(int id) {
        // Aquí podríamos agregar lógica de negocio antes de eliminar
        usuarioRepository.eliminarUsuario(id);
    }

    // Implementación del método para buscar usuarios por nombre
    @Override
    public List<Usuario> buscarUsuariosPorNombre(String nombre) {
        return usuarioRepository.buscarUsuariosPorNombre(nombre);
    }

    // Implementación del método para obtener un usuario por su nombre de usuario
    @Override
    public Usuario obtenerUsuarioPorUsername(String username) {
        return usuarioRepository.obtenerUsuarioPorUsername(username);
    }

    // Implementación del método para obtener un usuario por su correo electrónico
    @Override
    public Usuario obtenerUsuarioPorEmail(String email) {
        return usuarioRepository.obtenerUsuarioPorEmail(email);
    }
}
package TestUnits.TestUsuario;

import Models.Entities.Usuario;
import Models.Services.UsuarioService;
import Models.Services.UsuarioServiceImpl;

public class TestUsuario {
    public static void main(String[] args) {
        UsuarioService usuarioService = new UsuarioServiceImpl();

        // Crear un nuevo usuario
        Usuario nuevoUsuario = new Usuario(null, "testuser", "password123", "Usuario de Prueba", "test@example.com");
        usuarioService.guardarUsuario(nuevoUsuario);
        System.out.println("Usuario guardado con éxito.");

        // Obtener todos los usuarios
        System.out.println("\nListado de todos los usuarios:");
        usuarioService.obtenerTodosUsuarios().forEach(usuario -> System.out.println(usuario.getId() + " - " + usuario.getNombre()));

        // Obtener un usuario por ID
        Usuario usuarioPorId = usuarioService.obtenerUsuarioPorId(1); // Asumiendo que el ID 1 existe
        if (usuarioPorId != null) {
            System.out.println("\nUsuario con ID 1: " + usuarioPorId.getNombre());
        } else {
            System.out.println("\nNo se encontró el usuario con ID 1.");
        }

        // Actualizar un usuario
        Usuario usuarioActualizado = new Usuario(1, "updateduser", "newpassword", "Usuario Actualizado", "updated@example.com");
        usuarioService.actualizarUsuario(usuarioActualizado);
        System.out.println("\nUsuario con ID 1 actualizado.");

        // Buscar usuarios por nombre
        System.out.println("\nUsuarios con 'Usuario' en el nombre:");
        usuarioService.buscarUsuariosPorNombre("Usuario").forEach(usuario -> System.out.println(usuario.getNombre()));

        // Eliminar un usuario
        // usuarioService.eliminarUsuario(2); // Descomentar para eliminar un usuario con ID 2
        // System.out.println("\nUsuario con ID 2 eliminado.");

        System.out.println("\nPruebas finalizadas.");
    }
}
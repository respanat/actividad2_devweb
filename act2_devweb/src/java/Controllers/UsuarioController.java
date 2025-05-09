package Controllers;

import Models.Entities.Usuario;
import Models.Services.UsuarioService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

// La anotación @WebServlet define la URL bajo la cual este servlet responderá
@WebServlet("/usuarios")
public class UsuarioController extends HttpServlet {

    private final UsuarioService usuarioService = new UsuarioService();

    // Método doGet para manejar las peticiones GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list"; // Acción por defecto
        }

        switch (action) {
            case "list":
                listarUsuarios(request, response);
                break;
            case "addForm":
                mostrarFormularioAgregar(request, response);
                break;
            case "editForm":
                mostrarFormularioEditar(request, response);
                break;
            case "delete":
                eliminarUsuario(request, response);
                break;
            case "searchForm":
                mostrarFormularioBuscar(request, response);
                break;
            default:
                listarUsuarios(request, response);
                break;
        }
    }

    // Método doPost para manejar las peticiones POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "add"; // Acción por defecto
        }

        switch (action) {
            case "add":
                agregarUsuario(request, response);
                break;
            case "edit":
                editarUsuario(request, response);
                break;
            case "search":
                buscarUsuario(request, response);
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/usuarios?action=list");
                break;
        }
    }

    // Método para listar todos los usuarios
    private void listarUsuarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Usuario> usuarios = usuarioService.obtenerTodosUsuarios();
        request.setAttribute("usuarios", usuarios);
        // Redirecciona a la página JSP para mostrar la lista de usuarios
        request.getRequestDispatcher("/Views/forms/usuarios/listar_todo.jsp").forward(request, response);
    }

    // Método para mostrar el formulario de agregar usuario
    private void mostrarFormularioAgregar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/Views/forms/usuarios/agregar.jsp").forward(request, response);
    }

    // Método para agregar un nuevo usuario
    private void agregarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");

        Usuario usuario = new Usuario(null, username, password, nombre, email);
        usuarioService.guardarUsuario(usuario);
        // Redirecciona a la lista de usuarios después de agregar
        response.sendRedirect(request.getContextPath() + "/usuarios?action=list");
    }

    // Método para mostrar el formulario de edición de usuario
    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Usuario usuario = usuarioService.obtenerUsuarioPorId(id);
        request.setAttribute("usuario", usuario);
        // Redirecciona al formulario de edición con los datos del usuario
        request.getRequestDispatcher("/Views/forms/usuarios/agregar.jsp").forward(request, response); // Reutilizamos el formulario de agregar para editar
    }

    // Método para editar un usuario existente
    private void editarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");

        Usuario usuario = new Usuario(id, username, password, nombre, email);
        usuarioService.actualizarUsuario(usuario);
        // Redirecciona a la lista de usuarios después de editar
        response.sendRedirect(request.getContextPath() + "/usuarios?action=list");
    }

    // Método para eliminar un usuario
    private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        usuarioService.eliminarUsuario(id);
        // Redirecciona a la lista de usuarios después de eliminar
        response.sendRedirect(request.getContextPath() + "/usuarios?action=list");
    }

    // Método para mostrar el formulario de búsqueda de usuario
    private void mostrarFormularioBuscar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/Views/forms/usuarios/buscar.jsp").forward(request, response);
    }

    // Método para buscar usuarios (ejemplo básico por nombre)
    private void buscarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String criterio = request.getParameter("criterio");
        List<Usuario> usuarios = usuarioService.buscarUsuariosPorNombre(criterio);
        request.setAttribute("resultados", usuarios);
        // Redirecciona a una página para mostrar los resultados de la búsqueda
        request.getRequestDispatcher("/Views/forms/usuarios/listar_personalizada.jsp").forward(request, response);
    }
}
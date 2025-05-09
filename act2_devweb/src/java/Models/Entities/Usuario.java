package Models.Entities;

public class Usuario {
    private int id;
    private String username;
    private String password;
    private String nombre;
    private String email;

    // Constructor por defecto
    public Usuario() {
    }

    // Constructor con todos los campos
    public Usuario(int id, String username, String password, String nombre, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.email = email;
    }

    // Getters y setters para todos los atributos
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
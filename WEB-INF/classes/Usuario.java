package com.sise.modelo;

/**
 * Clase modelo que representa un Usuario del sistema
 * @author Daniela Paez
 */
public class Usuario {

    // Atributos del usuario
    private int id;
    private String nombre;
    private String correo;
    private String password;
    private String rol;

    /**
     * Constructor vacio
     */
    public Usuario() {}

    /**
     * Constructor con parametros
     * @param nombre nombre del usuario
     * @param correo correo electronico
     * @param password contrasena
     * @param rol rol del usuario
     */
    public Usuario(String nombre, String correo, String password, String rol) {
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
        this.rol = rol;
    }

    // Metodos getter y setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    @Override
    public String toString() {
        return "Usuario{id=" + id + ", nombre=" + nombre + ", correo=" + correo + ", rol=" + rol + "}";
    }
}
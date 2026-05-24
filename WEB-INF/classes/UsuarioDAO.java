package com.sise.dao;

import com.sise.conexion.Conexion;
import com.sise.modelo.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO para gestionar operaciones CRUD de Usuario
 * @author Daniela Paez
 */
public class UsuarioDAO {

    /**
     * Inserta un nuevo usuario en la base de datos
     * @param usuario objeto Usuario a insertar
     * @return true si se inserto correctamente
     */
    public boolean insertar(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nombre, correo, password, rol) VALUES (?, ?, ?, ?)";
        Connection conexion = Conexion.obtenerConexion();
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getCorreo());
            ps.setString(3, usuario.getPassword());
            ps.setString(4, usuario.getRol());
            ps.executeUpdate();
            System.out.println("Usuario insertado correctamente");
            return true;
        } catch (SQLException e) {
            System.out.println("Error al insertar usuario - " + e.getMessage());
            return false;
        } finally {
            Conexion.cerrarConexion(conexion);
        }
    }

    /**
     * Consulta todos los usuarios de la base de datos
     * @return lista de usuarios
     */
    public List<Usuario> consultarTodos() {
        List<Usuario> listaUsuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        Connection conexion = Conexion.obtenerConexion();
        try {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setPassword(rs.getString("password"));
                usuario.setRol(rs.getString("rol"));
                listaUsuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar usuarios - " + e.getMessage());
        } finally {
            Conexion.cerrarConexion(conexion);
        }
        return listaUsuarios;
    }

    /**
     * Actualiza los datos de un usuario existente
     * @param usuario objeto Usuario con los datos actualizados
     * @return true si se actualizo correctamente
     */
    public boolean actualizar(Usuario usuario) {
        String sql = "UPDATE usuarios SET nombre=?, correo=?, password=?, rol=? WHERE id=?";
        Connection conexion = Conexion.obtenerConexion();
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getCorreo());
            ps.setString(3, usuario.getPassword());
            ps.setString(4, usuario.getRol());
            ps.setInt(5, usuario.getId());
            ps.executeUpdate();
            System.out.println("Usuario actualizado correctamente");
            return true;
        } catch (SQLException e) {
            System.out.println("Error al actualizar usuario - " + e.getMessage());
            return false;
        } finally {
            Conexion.cerrarConexion(conexion);
        }
    }

    /**
     * Elimina un usuario por su id
     * @param id identificador del usuario a eliminar
     * @return true si se elimino correctamente
     */
    public boolean eliminar(int id) {
        String sql = "DELETE FROM usuarios WHERE id=?";
        Connection conexion = Conexion.obtenerConexion();
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Usuario eliminado correctamente");
            return true;
        } catch (SQLException e) {
            System.out.println("Error al eliminar usuario - " + e.getMessage());
            return false;
        } finally {
            Conexion.cerrarConexion(conexion);
        }
    }
}
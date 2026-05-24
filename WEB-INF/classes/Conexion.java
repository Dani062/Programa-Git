package com.sise.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase para gestionar la conexion con la base de datos MySQL
 * @author Daniela Paez
 */
public class Conexion {

    // Datos de conexion a la base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/sise_db";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "123456";

    /**
     * Metodo que retorna la conexion activa
     * @return Connection objeto de conexion
     */
    public static Connection obtenerConexion() {
        Connection conexion = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
            System.out.println("Conexion exitosa a la base de datos");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: Driver no encontrado - " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error de conexion - " + e.getMessage());
        }
        return conexion;
    }

    /**
     * Metodo para cerrar la conexion
     * @param conexion objeto Connection a cerrar
     */
    public static void cerrarConexion(Connection conexion) {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("Conexion cerrada correctamente");
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexion - " + e.getMessage());
            }
        }
    }
}
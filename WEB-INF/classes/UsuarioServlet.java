package com.sise.servlet;

import com.sise.conexion.Conexion;
import com.sise.modelo.Usuario;
import com.sise.dao.UsuarioDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Servlet para gestionar operaciones CRUD de usuarios
 * @author Daniela Paez
 */
public class UsuarioServlet extends HttpServlet {

    // Objeto DAO para acceder a la base de datos
    private UsuarioDAO usuarioDAO;

    @Override
    public void init() {
        usuarioDAO = new UsuarioDAO();
    }

    /**
     * Metodo GET para consultar usuarios
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        List<Usuario> listaUsuarios = usuarioDAO.consultarTodos();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='es'><head><meta charset='UTF-8'>");
        out.println("<title>Usuarios - SISE</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; max-width: 900px; margin: 40px auto; padding: 20px; background: #f5f5f5; }");
        out.println("h2 { color: #0d6efd; }");
        out.println("table { width: 100%; border-collapse: collapse; background: white; }");
        out.println("th { background: #0d6efd; color: white; padding: 10px; }");
        out.println("td { padding: 10px; border-bottom: 1px solid #ddd; text-align: center; }");
        out.println("tr:hover { background: #f0f0f0; }");
        out.println("a { color: #0d6efd; text-decoration: none; margin: 10px 0; display: inline-block; }");
        out.println("</style></head><body>");
        out.println("<h2>Lista de Usuarios</h2>");
        out.println("<a href='index.html'>← Volver al formulario</a>");
        out.println("<table>");
        out.println("<tr><th>ID</th><th>Nombre</th><th>Correo</th><th>Rol</th></tr>");

        for (Usuario u : listaUsuarios) {
            out.println("<tr>");
            out.println("<td>" + u.getId() + "</td>");
            out.println("<td>" + u.getNombre() + "</td>");
            out.println("<td>" + u.getCorreo() + "</td>");
            out.println("<td>" + u.getRol() + "</td>");
            out.println("</tr>");
        }

        out.println("</table>");
        out.println("</body></html>");
    }

    /**
     * Metodo POST para insertar, actualizar y eliminar usuarios
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String accion = request.getParameter("accion");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html><html lang='es'><head><meta charset='UTF-8'>");
        out.println("<title>Resultado - SISE</title>");
        out.println("<style>body { font-family: Arial, sans-serif; max-width: 600px; margin: 40px auto; padding: 20px; }");
        out.println("a { color: #0d6efd; } .msg { padding: 15px; border-radius: 8px; margin: 20px 0; }");
        out.println(".ok { background: #d1e7dd; color: #0f5132; } .err { background: #f8d7da; color: #842029; }</style></head><body>");

        if (accion.equals("insertar")) {
            // Insertar nuevo usuario
            String nombre = request.getParameter("nombre");
            String correo = request.getParameter("correo");
            String password = request.getParameter("password");
            String rol = request.getParameter("rol");

            Usuario nuevoUsuario = new Usuario(nombre, correo, password, rol);
            boolean resultado = usuarioDAO.insertar(nuevoUsuario);

            if (resultado) {
                out.println("<div class='msg ok'>✅ Usuario <b>" + nombre + "</b> insertado correctamente.</div>");
            } else {
                out.println("<div class='msg err'>❌ Error al insertar el usuario.</div>");
            }

        } else if (accion.equals("actualizar")) {
            // Actualizar usuario existente
            int id = Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("nombre");
            String correo = request.getParameter("correo");
            String password = request.getParameter("password");
            String rol = request.getParameter("rol");

            Usuario usuarioActualizar = new Usuario(nombre, correo, password, rol);
            usuarioActualizar.setId(id);
            boolean resultado = usuarioDAO.actualizar(usuarioActualizar);

            if (resultado) {
                out.println("<div class='msg ok'>✅ Usuario con ID <b>" + id + "</b> actualizado correctamente.</div>");
            } else {
                out.println("<div class='msg err'>❌ Error al actualizar el usuario.</div>");
            }

        } else if (accion.equals("eliminar")) {
            // Eliminar usuario por id
            int id = Integer.parseInt(request.getParameter("id"));
            boolean resultado = usuarioDAO.eliminar(id);

            if (resultado) {
                out.println("<div class='msg ok'>✅ Usuario con ID <b>" + id + "</b> eliminado correctamente.</div>");
            } else {
                out.println("<div class='msg err'>❌ Error al eliminar el usuario.</div>");
            }
        }

        out.println("<a href='index.html'>← Volver al formulario</a>");
        out.println("<br><a href='usuarios?accion=consultar'>Ver todos los usuarios</a>");
        out.println("</body></html>");
    }
}

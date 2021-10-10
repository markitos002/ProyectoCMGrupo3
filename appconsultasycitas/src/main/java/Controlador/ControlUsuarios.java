/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Modelo.Usuario;
import ModeloDAO.UsuarioDAO;
import javax.servlet.RequestDispatcher;
import javax.swing.JOptionPane;
/**
 *
 * @author jacun
 */
public class ControlUsuarios extends HttpServlet {

    String listar = "vistas/usua_listar.jsp";
    String add = "vistas/usua_add.jsp";
    String edit = "vistas/usua_edit.jsp";
    String ver = "vistas/usua_ver.jsp";
    String citausu = "vistas/cita_usuario.jsp";
    Usuario usuario = new Usuario();
    UsuarioDAO dao = new UsuarioDAO();
    int cambio = 0;
    String sql;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControlUsuarios</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControlUsuarios at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String acceso = "";
        String mensaje = "";
        String action = request.getParameter("accion");        

        if (action==null){
            action="listarPagina";
        }
        if(action.equalsIgnoreCase("listarPagina")){
            acceso = listar;
        }else
            if(action.equalsIgnoreCase("add")){
                acceso = add;
            }else 
                if(action.equalsIgnoreCase("Agregar")){
                    mensaje = "";

                        String parametro = (String)request.getParameter("parametro");
                        String paginador = (String)request.getParameter("paginador");

                        if(mensaje.equals("")){
                            if((String)request.getParameter("identificacion")!= ""){
                                usuario.setIdentificacion((String)request.getParameter("identificacion"));
                            }else{
                                mensaje = "El campo Identificación debe tener un valor.";
                            }
                        }                            
                        if(mensaje.equals("")){
                            if((String)request.getParameter("nombre")!= ""){
                                usuario.setNombre((String)request.getParameter("nombre"));
                            }else{
                                mensaje = "El campo id Paciente debe tener un valor válido.";
                            }
                        }
                        if(mensaje.equals("")){
                            if((String)request.getParameter("idRope")!= ""){
                                usuario.setIdRope(Integer.parseInt(request.getParameter("idRope")));
                            }else{
                                mensaje = "El campo Rol debe tener un valor.";
                            }
                        }                                                                                           
                        if(mensaje.equals("")){
                            if((String)request.getParameter("usuario") != ""){
                                usuario.setUsuario((String)request.getParameter("usuario"));
                            }else{
                                mensaje = "El campo Usuario debe tener un valor válido.";
                            }
                        }  
                        if(mensaje.equals("")){
                            if((String)request.getParameter("ocntrasena") != ""){
                                usuario.setContrasena((String)request.getParameter("ocntrasena"));
                                cambio = 1;
                            }else{
                                mensaje = "El campo Contraseña debe tener un valor.";
                            }
                        } 
                        usuario.setRegistradoPor("USUARIO_03");

                        if(mensaje.equals("")){
                            mensaje = dao.add(usuario);
                        }
                        if (!mensaje.equals("")){
                            JOptionPane.showMessageDialog(null, "Error: "+ mensaje, "Resultado de la Operación", JOptionPane.ERROR_MESSAGE);
                            request.getSession().setAttribute("parametro",parametro);
                            request.getSession().setAttribute("paginador",paginador);

                            String identificacion = (String)request.getParameter("identificacion");
                            String nombre = (String)request.getParameter("nombre");        
                            String idRope = (String)request.getParameter("idRope");        
                            String usuario = (String)request.getParameter("usuario");        
                            String contrasena = (String)request.getParameter("contrasena");        
                                        
                            request.getSession().setAttribute("identificacion", identificacion);
                            request.getSession().setAttribute("nombre",nombre);
                            request.getSession().setAttribute("idRope",idRope);
                            request.getSession().setAttribute("usuario",usuario);
                            request.getSession().setAttribute("contrasena",contrasena);
    
                        }else{

                            request.getSession().removeAttribute("identificacion");
                                request.getSession().removeAttribute("nombre");
                                request.getSession().removeAttribute("idRope");
                                request.getSession().removeAttribute("usuario");
                                request.getSession().removeAttribute("contrasena");                            
                                JOptionPane.showMessageDialog(null, "Registro agregado éxitosamente.", "Resultado de la Operación", JOptionPane.INFORMATION_MESSAGE);

                        }

                        acceso = add;

                }else

                    if(action.equalsIgnoreCase("consultar")){
                        request.setAttribute("id", request.getParameter("id"));
                        acceso = edit;
                    }else
                        if(action.equalsIgnoreCase("Actualizar")){
                            String parametro = (String)request.getParameter("parametro");
                            String paginador = (String)request.getParameter("paginador");

                            String id = (String)request.getParameter("id");
                            cambio = 0;
                            mensaje = "";
                            usuario.setIdUsuario(Integer.parseInt(request.getParameter("id")));
                            if(mensaje.equals("")){
                                if((String)request.getParameter("identificacion")!= ""){
                                    usuario.setIdentificacion((String)request.getParameter("identificacion"));
                                }else{
                                    mensaje = "El campo Identificación debe tener un valor.";
                                }
                            }                            
                            if(mensaje.equals("")){
                                if((String)request.getParameter("nombre")!= ""){
                                    usuario.setNombre((String)request.getParameter("nombre"));
                                }else{
                                    mensaje = "El campo id Paciente debe tener un valor válido.";
                                }
                            }
                            if(mensaje.equals("")){
                                if((String)request.getParameter("idRope")!= ""){
                                    usuario.setIdRope(Integer.parseInt(request.getParameter("idRope")));
                                }else{
                                    mensaje = "El campo Rol debe tener un valor.";
                                }
                            }                                                                                           
                            if(mensaje.equals("")){
                                if((String)request.getParameter("usuario") != ""){
                                    usuario.setUsuario((String)request.getParameter("usuario"));
                                }else{
                                    mensaje = "El campo Usuario debe tener un valor válido.";
                                }
                            }  
                            if(mensaje.equals("")){
                                if((String)request.getParameter("ocntrasena") != ""){
                                    usuario.setContrasena((String)request.getParameter("ocntrasena"));
                                    cambio = 1;
                                }else{
                                    mensaje = "El campo Contraseña debe tener un valor.";
                                }
                            } 
                        
                            usuario.setRegistradoPor("USUARIO_03");                    

                            if(mensaje.equals("")){
                                mensaje = dao.edit(usuario);
                            }
                            if (!mensaje.equals("")){
                                JOptionPane.showMessageDialog(null, "Error: "+ mensaje, "Resultado de la Operación", JOptionPane.ERROR_MESSAGE);
                                request.getSession().setAttribute("parametro",parametro);
                                request.getSession().setAttribute("paginador",paginador);
                                request.getSession().setAttribute("id", id);
                            
                                String identificacion = (String)request.getParameter("identificacion");
                                String nombre = (String)request.getParameter("nombre");        
                                String idRope = (String)request.getParameter("idRope");        
                                String usuario = (String)request.getParameter("usuario");        
                                String contrasena = (String)request.getParameter("contrasena");        
                                        
                               request.getSession().setAttribute("identificacion", identificacion);
                                request.getSession().setAttribute("nombre",nombre);
                                request.getSession().setAttribute("idRope",idRope);
                                request.getSession().setAttribute("usuario",usuario);
                                request.getSession().setAttribute("contrasena",contrasena);

                                JOptionPane.showMessageDialog(null, "Error: "+ mensaje, "Resultado de la Operación", JOptionPane.ERROR_MESSAGE);
                                acceso = edit;
                            
                            }else{

                                request.getSession().removeAttribute("identificacion");
                                request.getSession().removeAttribute("nombre");
                                request.getSession().removeAttribute("idRope");
                                request.getSession().removeAttribute("usuario");
                                request.getSession().removeAttribute("contrasena");       
                                if (cambio==1){
                                    JOptionPane.showMessageDialog(null, "Registro agregado éxitosamente.", "Resultado de la Operación", JOptionPane.INFORMATION_MESSAGE);
                                }
                                acceso = listar;
                            }
                        }else 
                            if(action.equalsIgnoreCase("eliminar")){
                                Integer id = Integer.parseInt(request.getParameter("id"));
                                String parametro = (String)(request.getParameter("parametro"));
                                mensaje = dao.elim(id);

                                if (!mensaje.equals("")){
                                    JOptionPane.showMessageDialog(null, "Error: "+ mensaje, "Resultado de la Operación", JOptionPane.ERROR_MESSAGE);
                                }else{
                                    JOptionPane.showMessageDialog(null, "Registro eliminado éxitosamente.", "Resultado de la Operación", JOptionPane.INFORMATION_MESSAGE);
                                }

                                acceso = listar;
                            }else 
                                if(action.equalsIgnoreCase("Buscar")){                                 
                                        acceso = listar;
                                }else
                                    if(action.equalsIgnoreCase("Ver")){                                 
                                        acceso = ver;
                                    }else
                                        if(action.equalsIgnoreCase("CitaUsuario")){                                 
                                            acceso = citausu;
                                        }
        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response); 

        //processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

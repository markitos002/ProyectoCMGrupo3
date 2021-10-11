/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Departamento;
import ModeloDAO.DepartamentoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 *
 * @author jacun
 */
public class ControlDepartamento extends HttpServlet {

    String listar = "vistas/depa_listar.jsp";
    String add = "vistas/depa_add.jsp";
    String edit = "vistas/depa_edit.jsp";
    String volver = "inic_functionalities.jsp";
    Departamento departamento = new Departamento();
    DepartamentoDAO dao = new DepartamentoDAO();
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
            out.println("<title>Servlet ControlDepartamento</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControlDepartamento at " + request.getContextPath() + "</h1>");
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
        String usuario = request.getParameter("usuario");
//JOptionPane.showMessageDialog(null, action, "Resultado de la Operación", JOptionPane.ERROR_MESSAGE);
        request.setAttribute("usuario", usuario);
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
                    String nom = request.getParameter("nombre");
                    String idPais = request.getParameter("idPais");
                    String str = "";
                    String subStr = "Duplicate entry";
                    boolean containsStr = false;
                    
                    System.out.println(containsStr);
                    if(nom.equals("")){
                        mensaje = "El campo Nombre debe tener un valor.";
                    }else{
                        departamento.setNombre(nom);
                        departamento.setIdPais(Integer.parseInt(idPais));
                        departamento.setRegistradoPor(usuario);
                        mensaje = dao.add(departamento);
                    }
                   
                    if (!mensaje.equals("")){
                        containsStr = mensaje.contains(subStr);
                        if(containsStr){
                            JOptionPane.showMessageDialog(null, "Error: "+ "Nombre duplicado en la Tabla.", "Resultado de la Operación", JOptionPane.ERROR_MESSAGE);
                        }else{
                            JOptionPane.showMessageDialog(null, "Error: "+ mensaje, "Resultado de la Operación", JOptionPane.ERROR_MESSAGE);
                        }
                    }else{
                        request.getSession().setAttribute("nombre", null);
                        departamento.setNombre("");
                        JOptionPane.showMessageDialog(null, "Registro adicionado éxitosamente.", "Resultado de la Operación", JOptionPane.INFORMATION_MESSAGE);
                    }
                    acceso = add;
                }else
                    if(action.equalsIgnoreCase("edit")){
                        request.setAttribute("id", request.getParameter("id"));
                        acceso = edit;
                    }else 
                        if(action.equalsIgnoreCase("Actualizar")){
                            Integer id = Integer.parseInt(request.getParameter("id"));
                            String nom = request.getParameter("nombre");
                            String idPais = request.getParameter("idPais");
                            String str = "";
                            String subStr = "Duplicate entry";
                            boolean containsStr = false;
                            
                            if(nom.equals("")){
                                mensaje = "El campo Nombre debe tener un valor.";
                            }else{
                                departamento.setIdDepartamento(id);
                                departamento.setNombre(nom);
                                departamento.setIdPais(Integer.parseInt(idPais));
                                departamento.setRegistradoPor(usuario);
                                mensaje = dao.edit(departamento);
                            }

                            if (!mensaje.equals("")){
                                containsStr = mensaje.contains(subStr);
                                if(containsStr){
                                    JOptionPane.showMessageDialog(null, "Error: "+ "Nombre duplicada en la Tabla.", "Resultado de la Operación", JOptionPane.ERROR_MESSAGE);
                                }else{
                                    JOptionPane.showMessageDialog(null, "Error: "+ mensaje, "Resultado de la Operación", JOptionPane.ERROR_MESSAGE);
                                }
                            }else{
                                JOptionPane.showMessageDialog(null, "Registro editado éxitosamente.", "Resultado de la Operación", JOptionPane.INFORMATION_MESSAGE);
                            }
                            
                            acceso = listar;
                        }else 
                            if(action.equalsIgnoreCase("eliminar")){
                                Integer id = Integer.parseInt(request.getParameter("id"));
                                String parametro = (String)(request.getParameter("parametro"));
                                mensaje = dao.elim(id);
                                
                                if (!mensaje.equals("")){
                                    //containsStr = mensaje.contains(subStr);
                                    //if(containsStr){
                                        JOptionPane.showMessageDialog(null, "Error: "+ mensaje, "Resultado de la Operación", JOptionPane.ERROR_MESSAGE);
                                    //}
                                }else{
                                    JOptionPane.showMessageDialog(null, "Registro eliminado éxitosamente.", "Resultado de la Operación", JOptionPane.INFORMATION_MESSAGE);
                                }
                                                    
                                acceso = listar;
                            }else 
                                if(action.equalsIgnoreCase("Buscar")){                                 
                                    acceso = listar;
                                }else
                                    if(action.equalsIgnoreCase("volver")){                                 
                                        acceso = volver;
                                    }
        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
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

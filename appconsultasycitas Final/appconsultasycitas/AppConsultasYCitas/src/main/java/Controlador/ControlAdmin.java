/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Citas;
import ModeloDAO.CitasDAO;
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
public class ControlAdmin extends HttpServlet {

    String lis_citas = "vistas/cita_listar.jsp";
    String lis_motivos = "vistas/moti_listar.jsp";
    String lis_recetas = "vistas/rece_listar.jsp";
    String lis_tipocitas = "vistas/tici_listar.jsp";
    String lis_pais = "vistas/pais_listar.jsp";
    String lis_usuarios = "vistas/usua_listar.jsp";
    String lis_consultas = "vistas/come_listar.jsp";
    String lis_departamentos = "vistas/depa_listar.jsp";
    String lis_ciudades = "vistas/ciud_listar.jsp";
    String listar ="inic_functionalities";
    Citas citas = new Citas();
    CitasDAO dao = new CitasDAO();
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
            out.println("<title>Servlet ControlCita</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControlCita at " + request.getContextPath() + "</h1>");
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
        String usuario = (String)request.getParameter("usuario");
        
        request.getSession().setAttribute("usuario", usuario);
        request.getSession().setAttribute("paginador", 1);

        if(action.equalsIgnoreCase("07")){
            acceso = lis_pais;
        }else
            if(action.equalsIgnoreCase("03")){
                acceso = lis_citas;
            }else 
              if(action.equalsIgnoreCase("01")){
                    acceso = lis_motivos;
              }else 
                if(action.equalsIgnoreCase("06")){
                    mensaje = "";
                    
                    acceso = lis_recetas;
                }else
                    if(action.equalsIgnoreCase("05")){
                        acceso = lis_usuarios;
                    }else
                        if(action.equalsIgnoreCase("02")){
                            acceso = lis_tipocitas;
                        }else 
                            if(action.equalsIgnoreCase("04")){
                                acceso = lis_departamentos;
                            }else 
                                if(action.equalsIgnoreCase("08")){
                                    acceso = lis_ciudades;
                                }else 
                                    acceso = listar;

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


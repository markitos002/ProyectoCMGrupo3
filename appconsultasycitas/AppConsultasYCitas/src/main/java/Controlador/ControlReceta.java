

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;


import Modelo.Receta;
import ModeloDAO.RecetaDAO;
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
public class ControlReceta extends HttpServlet {

    String listar = "vistas/rece_listar.jsp";
    String add = "vistas/rece_add.jsp";
    String edit = "vistas/rece_edit.jsp";
    String ver = "vistas/rece_ver.jsp";
    Receta receta = new Receta();
    RecetaDAO dao = new RecetaDAO();
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
            out.println("<title>Servlet ControlReceta</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControlReceta at " + request.getContextPath() + "</h1>");
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
                    //receta.setIdReceta(Integer.parseInt(request.getParameter("idReceta")));
                    receta.setCantidadMedicamento(request.getParameter("cantidadMedicamento"));
                    if(receta.getCantidadMedicamento().equals("") && mensaje.equals("") ){
                        mensaje = "El campo Cantidad Medicamento debe tener un valor";
                    }
                    receta.setMedicamento(request.getParameter("medicamento"));
                    if(receta.getMedicamento().equals("") && mensaje.equals("") ){
                        mensaje = "El campo Medicamento debe tener un valor";
                    }
                    receta.setPosologia(request.getParameter("posologia"));
                    if(receta.getPosologia().equals("") && mensaje.equals("") ){
                        mensaje = "El campo Posologia debe tener un valor";
                    }
                    receta.setViaAdministracion(request.getParameter("viaAdministracion"));
                    if(receta.getViaAdministracion().equals("") && mensaje.equals("") ){
                       mensaje = "El campo Via Administración debe tener un valor";
                    }
                    receta.setObservacion(request.getParameter("observacion"));
                    /*if(receta.getObservacion().equals("") && mensaje.equals("") ){
                        mensaje = "El campo Observacion debe tener un valor";

                    }*/
                    receta.setRegistradoPor("USUARIO_03");
                    if(receta.getRegistradoPor().equals("") && mensaje.equals("")){
                        mensaje = "El campo Registrado Por debe tener un valor";
                    }
                    receta.setIdConsulta(Integer.parseInt(request.getParameter("idConsulta")));
                    if(String.valueOf(receta.getIdConsulta()).equals("") && mensaje.equals("")){
                        mensaje = "El campo Id Consulta debe tener un valor";
                    }
                            
                    if(mensaje.equals("")){
                        mensaje = dao.add(receta);
                    }
                    if (!mensaje.equals("")){
                        JOptionPane.showMessageDialog(null, "Error: "+ mensaje, "Resultado de la Operación", JOptionPane.ERROR_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(null, "Registro agregado éxitosamente.", "Resultado de la Operación", JOptionPane.INFORMATION_MESSAGE);
                    }
                    acceso = add;
                }else
                    if(action.equalsIgnoreCase("consultar")){
                        request.setAttribute("id", request.getParameter("id"));
                        acceso = edit;
                    }else 
                        if(action.equalsIgnoreCase("Actualizar")){
                            //Integer id = Integer.parseInt((String)request.getParameter("id"));
                            int cambio = 0;
                            mensaje = "";
                            receta.setIdReceta(Integer.parseInt(request.getParameter("idReceta")));
                            receta.setCantidadMedicamento(request.getParameter("cantidadMedicamento"));
                            if(receta.getCantidadMedicamento().equals("") && mensaje.equals("") ){
                                  mensaje = "El campo Cantidad Medicamento debe tener un valor";
                            }else{
                                receta.setMedicamento(request.getParameter("medicamento"));
                                if(receta.getMedicamento().equals("") && mensaje.equals("") ){
                                    mensaje = "El campo Medicamento debe tener un valor";
                                }else{
                                    receta.setPosologia(request.getParameter("posologia"));
                                    if(receta.getPosologia().equals("") && mensaje.equals("") ){
                                          mensaje = "El campo Posologia debe tener un valor";
                                    }else{
                                        receta.setViaAdministracion(request.getParameter("viaAdministracion"));
                                        if(receta.getViaAdministracion().equals("") && mensaje.equals("") ){
                                              mensaje = "El campo Via Administración debe tener un valor";
                                        }else{
                                            receta.setObservacion(request.getParameter("observacion"));
                                            /*if(receta.getObservacion().equals("") && mensaje.equals("") ){
                                                  mensaje = "El campo Observacion debe tener un valor";
                                            }*/
                                            receta.setRegistradoPor("USUARIO_03");
                                            if(receta.getRegistradoPor().equals("") && mensaje.equals("")){
                                                  mensaje = "El campo Registrado Por debe tener un valor";

                                            }else{
                                                receta.setIdConsulta(Integer.parseInt(request.getParameter("idConsulta")));
                                                if(String.valueOf(receta.getIdConsulta()).equals("") && mensaje.equals("")){
                                                    mensaje = "El campo Id Consulta debe tener un valor";
                                                }else{
                                                    mensaje = dao.edit(receta);
                                                    cambio = 1;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            if (!mensaje.equals("")){
                                JOptionPane.showMessageDialog(null, "Error: "+ mensaje, "Resultado de la Operación", JOptionPane.ERROR_MESSAGE);
                            }else{
                                if (cambio==1){
                                    JOptionPane.showMessageDialog(null, "Registro actualizado éxitosamente.", "Resultado de la Operación", JOptionPane.INFORMATION_MESSAGE);
                                }
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
                                        if(action.equalsIgnoreCase("Ver")){                                 
                                            acceso = ver;
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



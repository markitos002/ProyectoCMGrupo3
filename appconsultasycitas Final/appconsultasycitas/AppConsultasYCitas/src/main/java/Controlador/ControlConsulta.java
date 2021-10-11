/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ConsultaMedica;
import ModeloDAO.ConsultaMedicaDAO;
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
public class ControlConsulta extends HttpServlet {

    String listar = "vistas/come_listar2.jsp";
    String add = "vistas/come_add.jsp";
    String add2 = "come_usuario.jsp";
    String edit = "vistas/come_edit.jsp";
    String ver = "vistas/come_ver.jsp";
    String ver2 = "vistas/come_ver2.jsp";
    String volver = "inic_functionalities.jsp";

    ConsultaMedica consultaMedica = new ConsultaMedica();
    ConsultaMedicaDAO dao = new ConsultaMedicaDAO();
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
            out.println("<title>Servlet ControlConsulta</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControlConsulta at " + request.getContextPath() + "</h1>");
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
        
        String accion2 = (String)request.getParameter("accion2");
        if (action==null){
            if(request.getParameter("accion2")==null){
                action="listarPagina";
            }else{
                action="add2";      
            }
        }
        if(action.equalsIgnoreCase("listarPagina")){
            acceso = listar;
        }else
            if(action.equalsIgnoreCase("add")){
                acceso = add;
            }else 
              if(action.equalsIgnoreCase("add2")){
                    acceso = add2;
              }else 
                if(action.equalsIgnoreCase("Agregar")){
                    mensaje = "";
                    //consultaMedica.setIdConsultaMedica(Integer.parseInt(request.getParameter("idConsultaMedica")));
                        String parametro = (String)request.getParameter("parametro");
                        String paginador = (String)request.getParameter("paginador");
                        String valor = (String)request.getParameter("valor");
                        
        //JOptionPane.showMessageDialog(null, consultaMedica.getIdPaciente(), "Resultado 1 de la Operación", JOptionPane.ERROR_MESSAGE);

                            if(((String)request.getParameter("idCita"))!= ""){
                                consultaMedica.setIdCita(Integer.parseInt(request.getParameter("idCita")));
                            }else{
                                mensaje = "El campo Id Cita debe tener un valor.";
                            }        
                            if(mensaje.equals("")){
                                if(!((String)request.getParameter("idPaciente")).equals("0")){
                                    consultaMedica.setIdPaciente(Integer.parseInt(request.getParameter("idPaciente")));
                                }else{
                                    mensaje = "El campo id Paciente debe tener un valor.";
                                }
                            }
                            if(mensaje.equals("")){
                                if(!((String)request.getParameter("idMedico")).equals("0")){
                                    consultaMedica.setIdPaciente(Integer.parseInt(request.getParameter("idMedico")));
                                }else{
                                    mensaje = "El campo id Médico debe tener un valor.";
                                }  
                            }
                            if(mensaje.equals("")){
                                if((String)request.getParameter("sintomatologia")!= ""){
                                    consultaMedica.setSintomatologia((String)request.getParameter("sintomatologia"));
                                }else{
                                    mensaje = "El campo id Sintomatología debe tener un valor válido.";
                                }
                            }
                            if(mensaje.equals("")){
                                if(((String)request.getParameter("tiempoEnfermedad"))!= ""){
                                    consultaMedica.setTiempoEnfermedad(request.getParameter("tiempoEnfermedad"));
                                }else{
                                    mensaje = "El campo Tiempo Enfermedad debe tener un valor.";
                                }
                            }                            
                            if(mensaje.equals("")){
                                if((String)request.getParameter("relatoCronologico") != ""){
                                    consultaMedica.setRelatoCronologico(request.getParameter("relatoCronologico"));
                                }else{
                                    mensaje = "El campo Relato Cronológico debe tener un valor.";
                                }
                            }                             
                            if(mensaje.equals("")){
                                if(((String)request.getParameter("diagnostico"))!= ""){
                                    consultaMedica.setDiagnostico(request.getParameter("diagnostico"));
                                }else{
                                    mensaje = "El campo Diagnóstico debe tener un valor.";
                                }
                            }  
                            if(mensaje.equals("")){
                                if((String)request.getParameter("antecedentes") != ""){
                                    consultaMedica.setAntecedentes((String)request.getParameter("antecedentes"));
                                    cambio = 1;
                                }else{
                                    mensaje = "El campo Antecedentes Consulta debe tener un valor.";
                                }
                            } 
                            if(mensaje.equals("")){
                                if((String)request.getParameter("tiempoTratamiento")!= ""){
                                    consultaMedica.setTiempoEnfermedad((String)request.getParameter("tiempoTratamiento"));
                                }else{
                                    mensaje = "El campo Tiempo Tratamiento debe tener un valor.";
                                }
                            }                            
                            consultaMedica.setObservacionReceta((String)request.getParameter("observacionReceta"));
                            consultaMedica.setRegistradoPor((String)request.getParameter("usuario"));

                            if(mensaje.equals("")){
                                mensaje = dao.add(consultaMedica);
                                //JOptionPane.showMessageDialog(null, "Grabo...."+ consultaMedica.getRegistradoPor(), "Resultado de la Operación", JOptionPane.ERROR_MESSAGE);
                            }
                            if (!mensaje.equals("")){
                                JOptionPane.showMessageDialog(null, "Error: "+ mensaje, "Resultado de la Operación", JOptionPane.ERROR_MESSAGE);
                                request.getSession().setAttribute("parametro",parametro);
                                request.getSession().setAttribute("paginador",paginador);
                                request.getSession().setAttribute("valor",0);

                                String motivoConsulta = (String)request.getParameter("motivoConsulta");
                                String sintomatologia = (String)request.getParameter("sintomatologia");
                                String tiempoEnfermedad = (String)request.getParameter("tiempoEnfermedad");        
                                String relatoCronologico = (String)request.getParameter("relatoCronologico");        
                                String diagnostico = (String)request.getParameter("diagnostico");
                                String antecedentes = (String)request.getParameter("antecedentes");        
                                String duracionTratamiento = (String)request.getParameter("duracionTratamiento");        
                                String observacionReceta = (String)request.getParameter("observacionReceta");        
                                String idPaciente = (String)request.getParameter("idPaciente");        
                                String paciente = (String)request.getParameter("paciente");        
                                String idMedico = (String)request.getParameter("idMedico");        
                                String medico = (String)request.getParameter("medico");        
                                String idCita = (String)request.getParameter("idCita");        
                                        
                                request.getSession().setAttribute("motivoConsulta",motivoConsulta);
                                request.getSession().setAttribute("sintomatologia",sintomatologia);
                                request.getSession().setAttribute("tiempoEnfermedad",tiempoEnfermedad);
                                request.getSession().setAttribute("relatoCronologico",relatoCronologico);
                                request.getSession().setAttribute("diagnostico",diagnostico);
                                request.getSession().setAttribute("antecedentes",antecedentes);
                                request.getSession().setAttribute("duracionTratamiento",duracionTratamiento);
                                request.getSession().setAttribute("observacionReceta",observacionReceta);
                                request.getSession().setAttribute("idPaciente",idPaciente);
                                request.getSession().setAttribute("paciente",paciente);
                                request.getSession().setAttribute("idMedico",idMedico);
                                request.getSession().setAttribute("medico",medico);
                                request.getSession().setAttribute("idCita",idCita);
    
                            }else{
                               
                                request.getSession().setAttribute("motivoConsulta","");
                                request.getSession().setAttribute("sintomatologia","");
                                request.getSession().setAttribute("tiempoEnfermedad","");
                                request.getSession().setAttribute("relatoCronologico","");
                                request.getSession().setAttribute("diagnostico","");
                                request.getSession().setAttribute("antecedentes","");
                                request.getSession().setAttribute("duracionTratamiento","");
                                request.getSession().setAttribute("observacionReceta","");
                                request.getSession().setAttribute("idPaciente",0);
                                request.getSession().setAttribute("paciente","");
                                request.getSession().setAttribute("idMedico",0);
                                request.getSession().setAttribute("medico","");
                                request.getSession().setAttribute("idCita",0);

                                
                                
                                JOptionPane.showMessageDialog(null, "Registro agregado éxitosamente.", "Resultado de la Operación", JOptionPane.INFORMATION_MESSAGE);
                            }
                            acceso = add;
                }else
                    if(action.equalsIgnoreCase("Grabar")){
                        mensaje = "";
                        //consultaMedica.setIdConsultaMedica(Integer.parseInt(request.getParameter("idConsultaMedica")));
                            String parametro = (String)request.getParameter("parametro");
                            String paginador = (String)request.getParameter("paginador");
                            String valor = (String)request.getParameter("valor");

                            if(((String)request.getParameter("idCita"))!= ""){
                                consultaMedica.setIdCita(Integer.parseInt(request.getParameter("idCita")));
                            }else{
                                mensaje = "El campo Id Cita debe tener un valor.";
                            }        
                            if(mensaje.equals("")){
                                if(!((String)request.getParameter("idPaciente")).equals("0")){
                                    consultaMedica.setIdPaciente(Integer.parseInt(request.getParameter("idPaciente")));
                                }else{
                                    mensaje = "El campo id Paciente debe tener un valor.";
                                }
                            }
                            if(mensaje.equals("")){
                                if(!((String)request.getParameter("idMedico")).equals("0")){
                                    consultaMedica.setIdPaciente(Integer.parseInt(request.getParameter("idMedico")));
                                }else{
                                    mensaje = "El campo id Médico debe tener un valor.";
                                }  
                            }
                            if(mensaje.equals("")){
                                if((String)request.getParameter("sintomatologia")!= ""){
                                    consultaMedica.setSintomatologia((String)request.getParameter("sintomatologia"));
                                }else{
                                    mensaje = "El campo id Sintomatología debe tener un valor válido.";
                                }
                            }
                            if(mensaje.equals("")){
                                if(((String)request.getParameter("tiempoEnfermedad"))!= ""){
                                    consultaMedica.setTiempoEnfermedad(request.getParameter("tiempoEnfermedad"));
                                }else{
                                    mensaje = "El campo Tiempo Enfermedad debe tener un valor.";
                                }
                            }                            
                            if(mensaje.equals("")){
                                if((String)request.getParameter("relatoCronologico") != ""){
                                    consultaMedica.setRelatoCronologico(request.getParameter("relatoCronologico"));
                                }else{
                                    mensaje = "El campo Relato Cronológico debe tener un valor.";
                                }
                            }                             
                            if(mensaje.equals("")){
                                if(((String)request.getParameter("diagnostico"))!= ""){
                                    consultaMedica.setDiagnostico(request.getParameter("diagnostico"));
                                }else{
                                    mensaje = "El campo Diagnóstico debe tener un valor.";
                                }
                            }  
                            if(mensaje.equals("")){
                                if((String)request.getParameter("antecedentes") != ""){
                                    consultaMedica.setAntecedentes((String)request.getParameter("antecedentes"));
                                    cambio = 1;
                                }else{
                                    mensaje = "El campo Antecedentes Consulta debe tener un valor.";
                                }
                            } 
                            if(mensaje.equals("")){
                                if((String)request.getParameter("tiempoTratamiento")!= ""){
                                    consultaMedica.setTiempoEnfermedad((String)request.getParameter("tiempoTratamiento"));
                                }else{
                                    mensaje = "El campo Tiempo Tratamiento debe tener un valor.";
                                }
                            }                            
                            consultaMedica.setObservacionReceta((String)request.getParameter("observacionReceta"));
                            consultaMedica.setRegistradoPor((String)request.getParameter("usuario"));

                                if(mensaje.equals("")){
                                    mensaje = dao.add(consultaMedica);
                                    //JOptionPane.showMessageDialog(null, "Grabo...."+ consultaMedica.getRegistradoPor(), "Resultado de la Operación", JOptionPane.ERROR_MESSAGE);
                                }
                                if (!mensaje.equals("")){
                                    JOptionPane.showMessageDialog(null, "Error: "+ mensaje, "Resultado de la Operación", JOptionPane.ERROR_MESSAGE);
                                    request.getSession().setAttribute("parametro",parametro);
                                    request.getSession().setAttribute("paginador",paginador);
                                    request.getSession().setAttribute("valor",0);

                                    String motivoConsulta = (String)request.getParameter("motivoConsulta");
                                    String sintomatologia = (String)request.getParameter("sintomatologia");
                                    String tiempoEnfermedad = (String)request.getParameter("tiempoEnfermedad");        
                                    String relatoCronologico = (String)request.getParameter("relatoCronologico");        
                                    String diagnostico = (String)request.getParameter("diagnostico");
                                    String antecedentes = (String)request.getParameter("antecedentes");        
                                    String duracionTratamiento = (String)request.getParameter("duracionTratamiento");        
                                    String observacionReceta = (String)request.getParameter("observacionReceta");        
                                    String idPaciente = (String)request.getParameter("idPaciente");        
                                    String paciente = (String)request.getParameter("paciente");        
                                    String idMedico = (String)request.getParameter("idMedico");        
                                    String medico = (String)request.getParameter("medico");        
                                    String idCita = (String)request.getParameter("idCita");        

                                    request.getSession().setAttribute("motivoConsulta",motivoConsulta);
                                    request.getSession().setAttribute("sintomatologia",sintomatologia);
                                    request.getSession().setAttribute("tiempoEnfermedad",tiempoEnfermedad);
                                    request.getSession().setAttribute("relatoCronologico",relatoCronologico);
                                    request.getSession().setAttribute("diagnostico",diagnostico);
                                    request.getSession().setAttribute("antecedentes",antecedentes);
                                    request.getSession().setAttribute("duracionTratamiento",duracionTratamiento);
                                    request.getSession().setAttribute("observacionReceta",observacionReceta);
                                    request.getSession().setAttribute("idPaciente",idPaciente);
                                    request.getSession().setAttribute("paciente",paciente);
                                    request.getSession().setAttribute("idMedico",idMedico);
                                    request.getSession().setAttribute("medico",medico);
                                    request.getSession().setAttribute("idCita",idCita);

                                    acceso = add2;
                                }else{
                                    request.setAttribute("idPaciente", null);
                                    request.setAttribute("sintomatologia", "");
                                    String aux = (String)request.getParameter("idPaciente");
                                    /*request.setAttribute("fecha", "");
                                    request.setAttribute("idCita", 0);
                                    request.setAttribute("idMedico", 0);
                                    request.setAttribute("medico", 0);
                                    request.setAttribute("tiempoEnfermedad", 0);
                                    request.setAttribute("relatoCronologico", 0);
                                    request.setAttribute("consultorio", "");
                                    request.setAttribute("diagnostico", "");
                                    request.setAttribute("idTipoCita", "");
                                    request.setAttribute("antecedentes", "");*/


                                    JOptionPane.showMessageDialog(null, "Registro agregado éxitosamente.", "Resultado de la Operación", JOptionPane.INFORMATION_MESSAGE);
                                    acceso = ver2;
                                }

                    }else
                    if(action.equalsIgnoreCase("edit")){
                        request.setAttribute("id", request.getParameter("id"));
                        acceso = edit;
                    }else
                        if(action.equalsIgnoreCase("Actualizar")){
                            //Integer id = Integer.parseInt((String)request.getParameter("id"));
                            String parametro = (String)request.getParameter("parametro");
                            String paginador = (String)request.getParameter("paginador");
                            String valor = (String)request.getParameter("valor");
                            String id = (String)request.getParameter("id");
                            cambio = 0;
                            mensaje = "";
                            consultaMedica.setIdConsultaMedica(Integer.parseInt(request.getParameter("id")));
                            //JOptionPane.showMessageDialog(null, "'"+request.getParameter("idPaciente")+"'", "Resultado de la Operación", JOptionPane.ERROR_MESSAGE);
                            if(((String)request.getParameter("idCita"))!= ""){
                                consultaMedica.setIdCita(Integer.parseInt(request.getParameter("idCita")));
                            }else{
                                mensaje = "El campo Id Cita debe tener un valor.";
                            }        
                            if(mensaje.equals("")){
                                if(!((String)request.getParameter("idPaciente")).equals("0")){
                                    consultaMedica.setIdPaciente(Integer.parseInt(request.getParameter("idPaciente")));
                                }else{
                                    mensaje = "El campo id Paciente debe tener un valor.";
                                }
                            }
                            if(mensaje.equals("")){
                                if(!((String)request.getParameter("idMedico")).equals("0")){
                                    consultaMedica.setIdPaciente(Integer.parseInt(request.getParameter("idMedico")));
                                }else{
                                    mensaje = "El campo id Médico debe tener un valor.";
                                }  
                            }
                            if(mensaje.equals("")){
                                if((String)request.getParameter("sintomatologia")!= ""){
                                    consultaMedica.setSintomatologia((String)request.getParameter("sintomatologia"));
                                }else{
                                    mensaje = "El campo id Sintomatología debe tener un valor válido.";
                                }
                            }
                            if(mensaje.equals("")){
                                if(((String)request.getParameter("tiempoEnfermedad"))!= ""){
                                    consultaMedica.setTiempoEnfermedad(request.getParameter("tiempoEnfermedad"));
                                }else{
                                    mensaje = "El campo Tiempo Enfermedad debe tener un valor.";
                                }
                            }                            
                            if(mensaje.equals("")){
                                if((String)request.getParameter("relatoCronologico") != ""){
                                    consultaMedica.setRelatoCronologico(request.getParameter("relatoCronologico"));
                                }else{
                                    mensaje = "El campo Relato Cronológico debe tener un valor.";
                                }
                            }                             
                            if(mensaje.equals("")){
                                if(((String)request.getParameter("diagnostico"))!= ""){
                                    consultaMedica.setDiagnostico(request.getParameter("diagnostico"));
                                }else{
                                    mensaje = "El campo Diagnóstico debe tener un valor.";
                                }
                            }  
                            if(mensaje.equals("")){
                                if((String)request.getParameter("antecedentes") != ""){
                                    consultaMedica.setAntecedentes((String)request.getParameter("antecedentes"));
                                    cambio = 1;
                                }else{
                                    mensaje = "El campo Antecedentes Consulta debe tener un valor.";
                                }
                            } 
                            if(mensaje.equals("")){
                                if((String)request.getParameter("tiempoTratamiento")!= ""){
                                    consultaMedica.setTiempoEnfermedad((String)request.getParameter("tiempoTratamiento"));
                                }else{
                                    mensaje = "El campo Tiempo Tratamiento debe tener un valor.";
                                }
                            }                            
                            consultaMedica.setObservacionReceta((String)request.getParameter("observacionReceta"));
                            consultaMedica.setRegistradoPor((String)request.getParameter("usuario"));                 

                            if(mensaje.equals("")){
                                mensaje = dao.edit(consultaMedica);
                            }
                            if (!mensaje.equals("")){
                                request.getSession().setAttribute("parametro",parametro);
                                request.getSession().setAttribute("paginador",paginador);
                                request.getSession().setAttribute("id", id);
                                
                                String motivoConsulta = (String)request.getParameter("motivoConsulta");
                                String sintomatologia = (String)request.getParameter("sintomatologia");
                                String tiempoEnfermedad = (String)request.getParameter("tiempoEnfermedad");        
                                String relatoCronologico = (String)request.getParameter("relatoCronologico");        
                                String diagnostico = (String)request.getParameter("diagnostico");
                                String antecedentes = (String)request.getParameter("antecedentes");        
                                String duracionTratamiento = (String)request.getParameter("duracionTratamiento");        
                                String observacionReceta = (String)request.getParameter("observacionReceta");        
                                String idPaciente = (String)request.getParameter("idPaciente");        
                                String paciente = (String)request.getParameter("paciente");        
                                String idMedico = (String)request.getParameter("idMedico");        
                                String medico = (String)request.getParameter("medico");        
                                String idCita = (String)request.getParameter("idCita");        
                                        
                                request.getSession().setAttribute("motivoConsulta",motivoConsulta);
                                request.getSession().setAttribute("sintomatologia",sintomatologia);
                                request.getSession().setAttribute("tiempoEnfermedad",tiempoEnfermedad);
                                request.getSession().setAttribute("relatoCronologico",relatoCronologico);
                                request.getSession().setAttribute("diagnostico",diagnostico);
                                request.getSession().setAttribute("antecedentes",antecedentes);
                                request.getSession().setAttribute("duracionTratamiento",duracionTratamiento);
                                request.getSession().setAttribute("observacionReceta",observacionReceta);
                                request.getSession().setAttribute("idPaciente",idPaciente);
                                request.getSession().setAttribute("paciente",paciente);
                                request.getSession().setAttribute("idMedico",idMedico);
                                request.getSession().setAttribute("medico",medico);
                                request.getSession().setAttribute("idCita",idCita);

                                JOptionPane.showMessageDialog(null, "Error: "+ mensaje, "Resultado de la Operación", JOptionPane.ERROR_MESSAGE);
                                acceso = edit;
                            }else{
                                if (cambio==1){
                                    JOptionPane.showMessageDialog(null, "Registro actualizado exitosamente", "Resultado de la Operación", JOptionPane.INFORMATION_MESSAGE);
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




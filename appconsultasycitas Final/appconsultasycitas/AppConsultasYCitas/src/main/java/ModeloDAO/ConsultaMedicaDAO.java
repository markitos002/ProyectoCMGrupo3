/*
* ConsultaMedicaDAO.java
*
* Creado el 09 de octubre de 2021, 21:34
*/

package ModeloDAO;

import Config.Conexion;
import Interfaces.CRUD;
import Modelo.ConsultaMedica;
import java.sql.*;
import java.util.*;
/**
*
* @autor GRUPO DE DESARROLLO 3 - MINTIC UIS
*/

public class ConsultaMedicaDAO implements CRUD {

    /** Definición de la conexión */
    Conexion cnx = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    ConsultaMedica consultaMedica = new ConsultaMedica();
    String mensaje = "";

/* METODOS DE LA INTERFAZ CRUD */

/**
* Lista los elementos de la tabla
* @return List
*/
@Override
public List listar() {
    ArrayList< ConsultaMedica > lista = new ArrayList<>();
    String sql = "SELECT * FROM APPCONSULTAS.CONSULTAMEDICA";
    try{
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            consultaMedica = new ConsultaMedica();
            consultaMedica.setIdConsultaMedica(rs.getInt("COME_ID"));
            consultaMedica.setMotivoConsulta(rs.getString("COME_MOTIVOCONSULTA"));
            consultaMedica.setSintomatologia(rs.getString("COME_SINTOMATOLOGIA"));
            consultaMedica.setTiempoEnfermedad(rs.getString("COME_TIEMPOENFERMEDAD"));
            consultaMedica.setRelatoCronologico(rs.getString("COME_RELATOCRONOLOGICO"));
            consultaMedica.setDiagnostico(rs.getString("COME_DIAGNOSTICO"));
            consultaMedica.setAntecedentes(rs.getString("COME_ANTECEDENTES"));
            consultaMedica.setRegistradoPor(rs.getString("COME_REGISTRADOPOR"));
            consultaMedica.setFechaCambio(rs.getTimestamp("COME_FECHACAMBIO"));
            consultaMedica.setDuracionTratamiento(rs.getString("COME_DURACIONTRATAMIENTO"));
            consultaMedica.setObservacionReceta(rs.getString("COME_OBSERVACIONRECETA"));
            consultaMedica.setIdCita(rs.getInt("CITA_ID"));
            consultaMedica.setIdPaciente(rs.getInt("PERS_IDPACIENTE"));
            consultaMedica.setIdMedico(rs.getInt("PERS_IDMEDICO"));
            consultaMedica.setIdMotivoConsulta(rs.getInt("MOCO_ID"));
            lista.add(consultaMedica);
        }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase ConsultaMedicaDAO ::> function public Object listar() ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase ConsultaMedicaDAO ::> function public Object listar() ::> Exception ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }finally{
        /*Cerrar.PsRs(ps,rs);*/
    }
    return lista;
}

/**
* Lista los elementos de una pagina dada
* @return Object
*/
@Override

public List listarPagina(String pagina, int desde, int hasta) {

    ArrayList< ConsultaMedica > lista = new ArrayList<>();
    String sql = "SELECT * FROM (SELECT COME_ID , COME_MOTIVOCONSULTA , COME_SINTOMATOLOGIA , COME_TIEMPOENFERMEDAD , COME_RELATOCRONOLOGICO , COME_DIAGNOSTICO , COME_ANTECEDENTES , COME_REGISTRADOPOR , COME_FECHACAMBIO , COME_DURACIONTRATAMIENTO , COME_OBSERVACIONRECETA , CITA_ID , PERS_IDPACIENTE , PERS_IDMEDICO , MOCO_ID , ROW_NUMBER() OVER (ORDER BY come_id) AS CONT FROM APPCONSULTAS.CONSULTAMEDICA WHERE POSITION(? IN MOCO_DESCRIPCION)>0 ORDER BY MOCO_ID) AS TABLA WHERE CONT>=? AND CONT<=? ORDER BY CONT";
    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, pagina);
        ps.setInt(2, desde);
        ps.setInt(3, hasta);
        rs = ps.executeQuery();

        while (rs.next()){

            ConsultaMedica consultaMedica = new ConsultaMedica();
            consultaMedica.setIdConsultaMedica(rs.getInt("COME_ID"));
            consultaMedica.setMotivoConsulta(rs.getString("COME_MOTIVOCONSULTA"));
            consultaMedica.setSintomatologia(rs.getString("COME_SINTOMATOLOGIA"));
            consultaMedica.setTiempoEnfermedad(rs.getString("COME_TIEMPOENFERMEDAD"));
            consultaMedica.setRelatoCronologico(rs.getString("COME_RELATOCRONOLOGICO"));
            consultaMedica.setDiagnostico(rs.getString("COME_DIAGNOSTICO"));
            consultaMedica.setAntecedentes(rs.getString("COME_ANTECEDENTES"));
            consultaMedica.setRegistradoPor(rs.getString("COME_REGISTRADOPOR"));
            consultaMedica.setFechaCambio(rs.getTimestamp("COME_FECHACAMBIO"));
            consultaMedica.setDuracionTratamiento(rs.getString("COME_DURACIONTRATAMIENTO"));
            consultaMedica.setObservacionReceta(rs.getString("COME_OBSERVACIONRECETA"));
            consultaMedica.setIdCita(rs.getInt("CITA_ID"));
            consultaMedica.setIdPaciente(rs.getInt("PERS_IDPACIENTE"));
            consultaMedica.setIdMedico(rs.getInt("PERS_IDMEDICO"));
            consultaMedica.setIdMotivoConsulta(rs.getInt("MOCO_ID"));
            lista.add(consultaMedica);

        }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase ConsultaMedicaDAO ::> function public Object listar(String filtro) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase ConsultaMedicaDAO ::> function public Object listar(String filtro) ::> Exception ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }finally{
        /*Cerrar.PsRs(ps,rs);*/
    }
    return lista;

}

/**
* Lista los elementos de una tabla dado un parámetro
* @return Object
*/
@Override

public List listarConParametro(String parametro) {
    ArrayList lista = new ArrayList<>();
    //int parametro2 =Integer.parseInt(parametro);
    String sql = "Select * from APPCONSULTAS.CONSULTAMEDICA where campo_referencia like ? ";
    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, "%"+parametro+"%");
        rs = ps.executeQuery();
        while (rs.next()){
            consultaMedica = new ConsultaMedica();
            consultaMedica.setIdConsultaMedica(rs.getInt("COME_ID"));
            consultaMedica.setMotivoConsulta(rs.getString("COME_MOTIVOCONSULTA"));
            //consultaMedica.setCampoReferencia(rs.getString("campo_referencia"));
            lista.add(consultaMedica);
        }
    } catch (SQLException e) {
    }
    return lista;
}
/**
* Busca un nuevo elemento de acuerdo a un id
* @return String
*/
@Override

public Object list(int id) {

    ArrayList< ConsultaMedica > lista = new ArrayList<>();
    String sql = "SELECT * FROM APPCONSULTAS.CONSULTAMEDICA WHERE cita_id = ? AND moco_id = ? ";
    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        rs = ps.executeQuery();

        while (rs.isBeforeFirst()){

            consultaMedica.setIdConsultaMedica(rs.getInt("COME_ID"));
            consultaMedica.setMotivoConsulta(rs.getString("COME_MOTIVOCONSULTA"));
            consultaMedica.setSintomatologia(rs.getString("COME_SINTOMATOLOGIA"));
            consultaMedica.setTiempoEnfermedad(rs.getString("COME_TIEMPOENFERMEDAD"));
            consultaMedica.setRelatoCronologico(rs.getString("COME_RELATOCRONOLOGICO"));
            consultaMedica.setDiagnostico(rs.getString("COME_DIAGNOSTICO"));
            consultaMedica.setAntecedentes(rs.getString("COME_ANTECEDENTES"));
            consultaMedica.setRegistradoPor(rs.getString("COME_REGISTRADOPOR"));
            consultaMedica.setFechaCambio(rs.getTimestamp("COME_FECHACAMBIO"));
            consultaMedica.setDuracionTratamiento(rs.getString("COME_DURACIONTRATAMIENTO"));
            consultaMedica.setObservacionReceta(rs.getString("COME_OBSERVACIONRECETA"));
            consultaMedica.setIdCita(rs.getInt("CITA_ID"));
            consultaMedica.setIdPaciente(rs.getInt("PERS_IDPACIENTE"));
            consultaMedica.setIdMedico(rs.getInt("PERS_IDMEDICO"));
            consultaMedica.setIdMotivoConsulta(rs.getInt("MOCO_ID"));
            lista.add(consultaMedica);

            }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase ConsultaMedicaDAO ::> function public Object listar(String filtro) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase ConsultaMedicaDAO ::> function public Object listar(String filtro) ::> Exception ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }finally{
        /*Cerrar.PsRs(ps,rs);*/
    }
    return consultaMedica;

}

/**
* Inserta un nuevo elemento en el objeto
* @return String
*/
@Override

public String add(Object dat) {
ConsultaMedica consultaMedica = (ConsultaMedica) dat;
    String mensaje = "";
    String sql = "";
    try{
        con = cnx.getConnection();
        sql = "INSERT INTO APPCONSULTAS.CONSULTAMEDICA ( COME_MOTIVOCONSULTA , COME_SINTOMATOLOGIA , COME_TIEMPOENFERMEDAD , COME_RELATOCRONOLOGICO , COME_DIAGNOSTICO , COME_ANTECEDENTES , COME_REGISTRADOPOR , COME_FECHACAMBIO , COME_DURACIONTRATAMIENTO , COME_OBSERVACIONRECETA , CITA_ID , PERS_IDPACIENTE , PERS_IDMEDICO , MOCO_ID ) VALUES ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";
        ps.setString(1, consultaMedica.getMotivoConsulta());
        ps.setString(2, consultaMedica.getSintomatologia());
        ps.setString(3, consultaMedica.getTiempoEnfermedad());
        ps.setString(4, consultaMedica.getRelatoCronologico());
        ps.setString(5, consultaMedica.getDiagnostico());
        ps.setString(6, consultaMedica.getAntecedentes());
        ps.setString(7, consultaMedica.getRegistradoPor());
        ps.setDate(8, java.sql.Date.valueOf(java.time.LocalDate.now()));
        ps.setString(9, consultaMedica.getDuracionTratamiento());
        ps.setString(10, consultaMedica.getObservacionReceta());
        ps.setInt(11, consultaMedica.getIdCita());
        ps.setInt(12, consultaMedica.getIdPaciente());
        ps.setInt(13, consultaMedica.getIdMedico());
        ps.setInt(14, consultaMedica.getIdMotivoConsulta());
        ps.executeUpdate();
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase ConsultaMedicaDAO ::> function public String insertar(Object dat) ::> SQLException ::> " + e.getMessage());
        consultaMedica.setError(e.getMessage());
        mensaje = e.getMessage();
        e.printStackTrace();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase ConsultaMedicaDAO ::> function public String insertar(Object dat) ::> Exception ::> " + e.getMessage());
        e.printStackTrace();
    }finally{
        //Cerrar.Ps(ps);
    }
    return mensaje;
}

/**
* Consulta un elemento del objeto según el parámetro enviado
* @return Object
*/
@Override
public Object consultar(String llave) {
    ConsultaMedica consultaMedica = new ConsultaMedica();
    ArrayList< ConsultaMedica > lista = new ArrayList<>();
    String sql = "";
    try{
        con = cnx.getConnection();
        sql = "SELECT * FROM APPCONSULTAS.CONSULTAMEDICA WHERE come_id = ? ";
        ps = con.prepareStatement(sql);
        ps.setString(1, llave);
        rs = ps.executeQuery();
        while (rs.next()) {
            consultaMedica.setIdConsultaMedica(rs.getInt("COME_ID"));
            consultaMedica.setMotivoConsulta(rs.getString("COME_MOTIVOCONSULTA"));
            consultaMedica.setSintomatologia(rs.getString("COME_SINTOMATOLOGIA"));
            consultaMedica.setTiempoEnfermedad(rs.getString("COME_TIEMPOENFERMEDAD"));
            consultaMedica.setRelatoCronologico(rs.getString("COME_RELATOCRONOLOGICO"));
            consultaMedica.setDiagnostico(rs.getString("COME_DIAGNOSTICO"));
            consultaMedica.setAntecedentes(rs.getString("COME_ANTECEDENTES"));
            consultaMedica.setRegistradoPor(rs.getString("COME_REGISTRADOPOR"));
            consultaMedica.setFechaCambio(rs.getTimestamp("COME_FECHACAMBIO"));
            consultaMedica.setDuracionTratamiento(rs.getString("COME_DURACIONTRATAMIENTO"));
            consultaMedica.setObservacionReceta(rs.getString("COME_OBSERVACIONRECETA"));
            consultaMedica.setIdCita(rs.getInt("CITA_ID"));
            consultaMedica.setIdPaciente(rs.getInt("PERS_IDPACIENTE"));
            consultaMedica.setIdMedico(rs.getInt("PERS_IDMEDICO"));
            consultaMedica.setIdMotivoConsulta(rs.getInt("MOCO_ID"));
        }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase ConsultaMedicaDAO ::> function public Object consultar(String llave) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase ConsultaMedicaDAO ::> function public Object consultar(String llave) ::> Exception ::> " + e.getMessage());
        e.printStackTrace();
    }finally{
        /*Cerrar.PsRs(ps,rs);*/
    }
    return consultaMedica;
}

/**
* Modifica un elemento del objeto
* @return boolean
*/
public boolean modificar(Object ob) {
    return false;
}

/**
* Actualiza un elemento del objeto
* @return boolean
*/
@Override
public String edit(Object dat) {
ConsultaMedica consultaMedica = (ConsultaMedica) dat;
    String mensaje = "";
    String sql =
    "UPDATE APPCONSULTAS.CONSULTAMEDICA SET "
    +" COME_MOTIVOCONSULTA = ? , "
    +" COME_SINTOMATOLOGIA = ? , "
    +" COME_TIEMPOENFERMEDAD = ? , "
    +" COME_RELATOCRONOLOGICO = ? , "
    +" COME_DIAGNOSTICO = ? , "
    +" COME_ANTECEDENTES = ? , "
    +" COME_REGISTRADOPOR = ? , "
    +" COME_FECHACAMBIO = ? , "
    +" COME_DURACIONTRATAMIENTO = ? , "
    +" COME_OBSERVACIONRECETA = ? , "
    +" CITA_ID = ? , "
    +" PERS_IDPACIENTE = ? , "
    +" PERS_IDMEDICO = ? , "
    +" MOCO_ID = ? "
    +" WHERE come_id = ? ";
    try{
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, consultaMedica.getMotivoConsulta());
        ps.setString(2, consultaMedica.getSintomatologia());
        ps.setString(3, consultaMedica.getTiempoEnfermedad());
        ps.setString(4, consultaMedica.getRelatoCronologico());
        ps.setString(5, consultaMedica.getDiagnostico());
        ps.setString(6, consultaMedica.getAntecedentes());
        ps.setString(7, consultaMedica.getRegistradoPor());
        ps.setDate(8, java.sql.Date.valueOf(java.time.LocalDate.now()));
        ps.setString(9, consultaMedica.getDuracionTratamiento());
        ps.setString(10, consultaMedica.getObservacionReceta());
        ps.setInt(11, consultaMedica.getIdCita());
        ps.setInt(12, consultaMedica.getIdPaciente());
        ps.setInt(13, consultaMedica.getIdMedico());
        ps.setInt(14, consultaMedica.getIdMotivoConsulta());
        ps.setInt(15, consultaMedica.getIdConsultaMedica());
        ps.executeUpdate();

    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase ConsultaMedicaDAO ::> function public String actualizar(Object dat) ::> SQLException ::> " + e.getMessage());
        consultaMedica.setError(e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase ConsultaMedicaDAO ::> function public String actualizar(Object dat) ::> Exception ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }finally{
        //Cerrar.Ps(ps);
    }
    return mensaje;
}

/**
* ELimina un elemento de acuerdo a un id
* @return String
*/
@Override

public String elim(int id) {

    mensaje = "";
    String sql = "DELETE FROM APPCONSULTAS.CONSULTAMEDICA WHERE come_id = ? ";

    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();

    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase ConsultaMedicaDAO ::> function public Object eliminar(String filtro) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
    }catch(Exception e){
        mensaje = e.getMessage();
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase ConsultaMedicaDAO ::> function public Object eliminar(String filtro) ::> Exception ::> " + e.getMessage());
        e.printStackTrace();
    }finally{
        /*Cerrar.PsRs(ps,rs);*/
    }
    return mensaje;

}



@Override
public int calcularPaginas(String parametro) {
    int nroRegistros = 0;
    int paginador = 0;
    String sql = "Select count(*) from appconsultas.consultaMedica where come_motivoconsulta like ? ";
    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, "%"+parametro+"%");
        rs = ps.executeQuery();
        if(rs.next()) {
            nroRegistros = rs.getInt(1);
        }
        double paginas = (nroRegistros/10)-((nroRegistros/10)%1);
        if ((nroRegistros % 10) == 0){
             paginador = (int)paginas;
        }else{
            paginador = (int)paginas +1;
        }
    } catch (SQLException e) {
    }
    return paginador;
}
}
/*
* 09-OCTUBRE-2021 @: GRUPO DE DESARROLLO 3 &: creación
*/

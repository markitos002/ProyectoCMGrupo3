

/*
* CitasDAO.java
*
* Creado el 25 de septiembre de 2021, 23:19
*/

package ModeloDAO;

import Config.Conexion;
import Interfaces.CRUD;
import Modelo.Citas;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
/**
*
* @autor GRUPO DE DESARROLLO 3 - MINTIC UIS
*/

public class CitasDAO implements CRUD {

    /** Definición de la conexión */
    Conexion cnx = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Citas citas = new Citas();
    String mensaje = "";

/* METODOS DE LA INTERFAZ CRUD */

/**
* Lista los elementos de la tabla
* @return List
*/
@Override
public List listar() {
    ArrayList< Citas > lista = new ArrayList<>();
    String sql = "SELECT * FROM APPCONSULTAS.CITAS";
    try{
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            citas = new Citas();
            citas.setIdCitas(rs.getInt("CITA_ID"));
            citas.setIdPaciente(rs.getInt("PERS_IDPACIENTE"));
            citas.setIdHorarioCitas(rs.getInt("HOCI_ID"));
            citas.setIdMedico(rs.getInt("PERS_IDMEDICO"));
            citas.setIdConsultorio(rs.getInt("CONS_ID"));
            citas.setIdTipoCita(rs.getInt("TICI_ID"));
            citas.setObservacion(rs.getString("CITA_OBSERVACION"));
            citas.setRegistradoPor(rs.getString("CITA_REGISTRADOPOR"));
            citas.setFechaCambio(rs.getDate("CITA_FECHACAMBIO"));
            citas.setFecha(rs.getString("FECHA"));
            citas.setMotivoConsulta(rs.getString("CITA_MOTIVODECONSULTA"));
            citas.setIdMotivoConsulta(rs.getInt("MOCO_ID"));
            lista.add(citas);
        }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase CitasDAO ::> function public Object listar() ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase CitasDAO ::> function public Object listar() ::> Exception ::> " + e.getMessage());
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

    ArrayList< Citas > lista = new ArrayList<>();
    String sql = "SELECT * FROM ("
            +"SELECT CI.CITA_ID , CI.PERS_IDPACIENTE , "
            +"CONCAT(P1.PERS_PRIMERNOMBRE,' ',P1.PERS_SEGUNDONOMBRE,' ',P1.PERS_PRIMERAPELLIDO,' ',P1.PERS_SEGUNDOAPELLIDO) PACIENTE, "
            +"CI.HOCI_ID , CONCAT(HC.HOCI_HORAINICIO, ':' ,HC.HOCI_MINUTOINICIO) HORA, CI.PERS_IDMEDICO , "
            +"CONCAT(P2.PERS_PRIMERNOMBRE,' ', P2.PERS_SEGUNDONOMBRE,' ', P2.PERS_PRIMERAPELLIDO,' ', P2.PERS_SEGUNDOAPELLIDO) MEDICO, "
            +"CI.CONS_ID , CONCAT(CO.CONS_DIRECCION, '-', CO.CONS_NOMBRE) LUGAR, CI.TICI_ID , TC.TICI_NOMBRE, "
            +"CI.CITA_OBSERVACION , CI.CITA_REGISTRADOPOR , CI.CITA_FECHACAMBIO , CI.FECHA , CI.CITA_MOTIVODECONSULTA , "
            +"CI.MOCO_ID , MC.MOCO_DESCRIPCION, ROW_NUMBER() OVER (ORDER BY cita_id) AS CONT "
            +"FROM APPCONSULTAS.CITAS CI, APPCONSULTAS.MEDICO ME, APPCONSULTAS.PACIENTE PA, "
            +"APPCONSULTAS.PERSONA P1, APPCONSULTAS.PERSONA P2, APPCONSULTAS.CONSULTORIO CO, "
            +"APPCONSULTAS.HORARIOCITASDIA HC, APPCONSULTAS.TIPOCITA TC, APPCONSULTAS.MOTIVOCONSULTA MC "
            +"WHERE CI.PERS_IDPACIENTE = PA.PERS_ID AND PA.PERS_ID = P1.PERS_ID AND "
            +"CI.PERS_IDMEDICO = ME.PERS_ID AND ME.PERS_ID = P2.PERS_ID AND "
            +"CI.CONS_ID = CO.CONS_ID AND CI.HOCI_ID = HC.HOCI_ID AND "
            +"CI.TICI_ID = TC.TICI_ID AND CI.MOCO_ID = MC.MOCO_ID AND "
            +"POSITION(? IN PERS_IDMEDICO)>0 "
            +"ORDER BY CITA_ID) AS TABLA WHERE CONT>=? AND CONT<=? ORDER BY CONT";  
    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, pagina);
        ps.setInt(2, desde);
        ps.setInt(3, hasta);
        rs = ps.executeQuery();

        while (rs.next()){

            Citas citas = new Citas();
            citas.setIdCitas(rs.getInt("CITA_ID"));
            citas.setIdPaciente(rs.getInt("PERS_IDPACIENTE"));
            citas.setPaciente(rs.getString("PACIENTE"));
            citas.setIdHorarioCitas(rs.getInt("HOCI_ID"));
            citas.setHora(rs.getString("HORA"));
            citas.setIdMedico(rs.getInt("PERS_IDMEDICO"));
            citas.setMedico(rs.getString("MEDICO"));
            citas.setIdConsultorio(rs.getInt("CONS_ID"));
            citas.setLugar(rs.getString("LUGAR"));
            citas.setIdTipoCita(rs.getInt("TICI_ID"));
            citas.setTipoCita(rs.getString("TICI_NOMBRE"));
            citas.setObservacion(rs.getString("CITA_OBSERVACION"));
            citas.setRegistradoPor(rs.getString("CITA_REGISTRADOPOR"));
            citas.setFechaCambio(rs.getDate("CITA_FECHACAMBIO"));
            citas.setFecha(rs.getString("FECHA"));
            citas.setMotivoConsulta(rs.getString("CITA_MOTIVODECONSULTA"));
            citas.setIdMotivoConsulta(rs.getInt("MOCO_ID"));
            citas.setMotivoCons(rs.getString("MOCO_DESCRIPCION"));
            lista.add(citas);

        }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase CitasDAO ::> function public Object listar(String filtro) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase CitasDAO ::> function public Object listar(String filtro) ::> Exception ::> " + e.getMessage());
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
    String sql = "Select * from APPCONSULTAS.CITAS where campo_referencia like ? ";
    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, "%"+parametro+"%");
        rs = ps.executeQuery();
        while (rs.next()){
            citas = new Citas();
            citas.setIdCitas(rs.getInt("CITA_ID"));
            citas.setIdPaciente(rs.getInt("PERS_IDPACIENTE"));
            //citas.setCampoReferencia(rs.getString("campo_referencia"));
            lista.add(citas);
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

    ArrayList< Citas > lista = new ArrayList<>();
    String sql = "SELECT * FROM APPCONSULTAS.CITAS WHERE pers_idpaciente = ? AND hoci_id = ? AND pers_idmedico = ? AND cons_id = ? AND tici_id = ? AND moco_id = ? ";
    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        rs = ps.executeQuery();

        while (rs.isBeforeFirst()){

            citas.setIdCitas(rs.getInt("CITA_ID"));
            citas.setIdPaciente(rs.getInt("PERS_IDPACIENTE"));
            citas.setIdHorarioCitas(rs.getInt("HOCI_ID"));
            citas.setIdMedico(rs.getInt("PERS_IDMEDICO"));
            citas.setIdConsultorio(rs.getInt("CONS_ID"));
            citas.setIdTipoCita(rs.getInt("TICI_ID"));
            citas.setObservacion(rs.getString("CITA_OBSERVACION"));
            citas.setRegistradoPor(rs.getString("CITA_REGISTRADOPOR"));
            citas.setFechaCambio(rs.getDate("CITA_FECHACAMBIO"));
            citas.setFecha(rs.getString("FECHA"));
            citas.setMotivoConsulta(rs.getString("CITA_MOTIVODECONSULTA"));
            citas.setIdMotivoConsulta(rs.getInt("MOCO_ID"));
            lista.add(citas);

            }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase CitasDAO ::> function public Object listar(String filtro) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase CitasDAO ::> function public Object listar(String filtro) ::> Exception ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }finally{
        /*Cerrar.PsRs(ps,rs);*/
    }
    return citas;

}

/**
* Inserta un nuevo elemento en el objeto
* @return String
*/
@Override

public String add(Object dat) {
Citas citas = (Citas) dat;
    String mensaje = "";
    String sql = "";
    try{
        con = cnx.getConnection();
        sql = "INSERT INTO APPCONSULTAS.CITAS ( PERS_IDPACIENTE , HOCI_ID , PERS_IDMEDICO , CONS_ID , TICI_ID , CITA_OBSERVACION , CITA_REGISTRADOPOR , CITA_FECHACAMBIO , FECHA , CITA_MOTIVODECONSULTA , MOCO_ID ) VALUES ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";
        ps.setInt(1, citas.getIdPaciente());
        ps.setInt(2, citas.getIdHorarioCitas());
        ps.setInt(3, citas.getIdMedico());
        ps.setInt(4, citas.getIdConsultorio());
        ps.setInt(5, citas.getIdTipoCita());
        ps.setString(6, citas.getObservacion());
        ps.setString(7, citas.getRegistradoPor());
        ps.setDate(8, java.sql.Date.valueOf(java.time.LocalDate.now()));
        ps.setDate(9, java.sql.Date.valueOf(java.time.LocalDate.now()));
        ps.setString(10, citas.getMotivoConsulta());
        ps.setInt(11, citas.getIdMotivoConsulta());
        ps.executeUpdate();
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase CitasDAO ::> function public String insertar(Object dat) ::> SQLException ::> " + e.getMessage());
        citas.setError(e.getMessage());
        mensaje = e.getMessage();
        e.printStackTrace();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase CitasDAO ::> function public String insertar(Object dat) ::> Exception ::> " + e.getMessage());
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
    Citas citas = new Citas();
    ArrayList< Citas > lista = new ArrayList<>();
    String sql = "";
    try{
        con = cnx.getConnection();
        sql = "SELECT CI.CITA_ID , CI.PERS_IDPACIENTE , "
            +"CONCAT(P1.PERS_PRIMERNOMBRE,' ',P1.PERS_SEGUNDONOMBRE,' ',P1.PERS_PRIMERAPELLIDO,' ',P1.PERS_SEGUNDOAPELLIDO) PACIENTE, "
            +"CI.HOCI_ID , CONCAT(HC.HOCI_HORAINICIO, ':' ,HC.HOCI_MINUTOINICIO) HORA, CI.PERS_IDMEDICO , "
            +"CONCAT(P2.PERS_PRIMERNOMBRE,' ', P2.PERS_SEGUNDONOMBRE,' ', P2.PERS_PRIMERAPELLIDO,' ', P2.PERS_SEGUNDOAPELLIDO) MEDICO, "
            +"CI.CONS_ID , CONCAT(CO.CONS_DIRECCION, '-', CO.CONS_NOMBRE) LUGAR, CI.TICI_ID , TC.TICI_NOMBRE, "
            +"CI.CITA_OBSERVACION , CI.CITA_REGISTRADOPOR , CI.CITA_FECHACAMBIO , CI.FECHA , CI.CITA_MOTIVODECONSULTA , "
            +"CI.MOCO_ID , MC.MOCO_DESCRIPCION, ROW_NUMBER() OVER (ORDER BY cita_id) AS CONT "
            +"FROM APPCONSULTAS.CITAS CI, APPCONSULTAS.MEDICO ME, APPCONSULTAS.PACIENTE PA, "
            +"APPCONSULTAS.PERSONA P1, APPCONSULTAS.PERSONA P2, APPCONSULTAS.CONSULTORIO CO, "
            +"APPCONSULTAS.HORARIOCITASDIA HC, APPCONSULTAS.TIPOCITA TC, APPCONSULTAS.MOTIVOCONSULTA MC "
            +"WHERE CI.PERS_IDPACIENTE = PA.PERS_ID AND PA.PERS_ID = P1.PERS_ID AND "
            +"CI.PERS_IDMEDICO = ME.PERS_ID AND ME.PERS_ID = P2.PERS_ID AND "
            +"CI.CONS_ID = CO.CONS_ID AND CI.HOCI_ID = HC.HOCI_ID AND "
            +"CI.TICI_ID = TC.TICI_ID AND CI.MOCO_ID = MC.MOCO_ID AND CITA_ID = ?  ";

        ps = con.prepareStatement(sql);
        ps.setString(1, llave);
        rs = ps.executeQuery();
        while (rs.next()) {
            citas.setIdCitas(rs.getInt("CITA_ID"));
            citas.setIdPaciente(rs.getInt("PERS_IDPACIENTE"));
            citas.setPaciente(rs.getString("PACIENTE"));
            citas.setIdHorarioCitas(rs.getInt("HOCI_ID"));
            citas.setHora(rs.getString("HORA"));
            citas.setIdMedico(rs.getInt("PERS_IDMEDICO"));
            citas.setMedico(rs.getString("MEDICO"));
            citas.setIdConsultorio(rs.getInt("CONS_ID"));
            citas.setLugar(rs.getString("LUGAR"));
            citas.setIdTipoCita(rs.getInt("TICI_ID"));
            citas.setTipoCita(rs.getString("TICI_NOMBRE"));
            citas.setObservacion(rs.getString("CITA_OBSERVACION"));
            citas.setRegistradoPor(rs.getString("CITA_REGISTRADOPOR"));
            citas.setFechaCambio(rs.getDate("CITA_FECHACAMBIO"));
            citas.setFecha(rs.getString("FECHA"));
            citas.setMotivoConsulta(rs.getString("CITA_MOTIVODECONSULTA"));
            citas.setIdMotivoConsulta(rs.getInt("MOCO_ID"));
            citas.setMotivoCons(rs.getString("MOCO_DESCRIPCION"));
            citas.setIdCitas(rs.getInt("CITA_ID"));

        }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase CitasDAO ::> function public Object consultar(String llave) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase CitasDAO ::> function public Object consultar(String llave) ::> Exception ::> " + e.getMessage());
        e.printStackTrace();
    }finally{
        /*Cerrar.PsRs(ps,rs);*/
    }
    return citas;
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
Citas citas = (Citas) dat;
    String mensaje = "";
    String sql =
    "UPDATE APPCONSULTAS.CITAS SET "
    +" PERS_IDPACIENTE = ? , "
    +" HOCI_ID = ? , "
    +" PERS_IDMEDICO = ? , "
    +" CONS_ID = ? , "
    +" TICI_ID = ? , "
    +" CITA_OBSERVACION = ? , "
    +" CITA_REGISTRADOPOR = ? , "
    +" CITA_FECHACAMBIO = ? , "
    +" FECHA = ? , "
    +" CITA_MOTIVODECONSULTA = ? , "
    +" MOCO_ID = ? "
    +" WHERE cita_id = ? ";
    try{
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, citas.getIdPaciente());
        ps.setInt(2, citas.getIdHorarioCitas());
        ps.setInt(3, citas.getIdMedico());
        ps.setInt(4, citas.getIdConsultorio());
        ps.setInt(5, citas.getIdTipoCita());
        ps.setString(6, citas.getObservacion());
        ps.setString(7, citas.getRegistradoPor());
        ps.setDate(8, java.sql.Date.valueOf(java.time.LocalDate.now()));
        ps.setString(9, citas.getFecha());
        ps.setString(10, citas.getMotivoConsulta());
        ps.setInt(11, citas.getIdMotivoConsulta());
        ps.setInt(12, citas.getIdCitas());
        ps.executeUpdate();

    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase CitasDAO ::> function public String actualizar(Object dat) ::> SQLException ::> " + e.getMessage());
        citas.setError(e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase CitasDAO ::> function public String actualizar(Object dat) ::> Exception ::> " + e.getMessage());
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
    String sql = "DELETE FROM APPCONSULTAS.CITAS WHERE cita_id = ? ";

    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();

    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase CitasDAO ::> function public Object eliminar(String filtro) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
    }catch(Exception e){
        mensaje = e.getMessage();
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase CitasDAO ::> function public Object eliminar(String filtro) ::> Exception ::> " + e.getMessage());
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
    String sql = "Select count(*) from appconsultas.motivoconsulta where moco_descripcion like ? ";
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
* 25-SEPTIEMBRE-2021 @: GRUPO DE DESARROLLO 3 &: creación
*/


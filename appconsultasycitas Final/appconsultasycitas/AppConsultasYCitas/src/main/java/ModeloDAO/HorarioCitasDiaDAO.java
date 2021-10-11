/*
* HorarioCitasDiaDAO.java
*
* Creado el 26 de septiembre de 2021, 16:49
*/

package ModeloDAO;

import Config.Conexion;
import Interfaces.CRUD;
import Modelo.HorarioCitasDia;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
*
* @autor GRUPO DE DESARROLLO 3 - MINTIC UIS
*/

public class HorarioCitasDiaDAO implements CRUD {

    /** Definición de la conexión */
    Conexion cnx = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    HorarioCitasDia horarioCitasDia = new HorarioCitasDia();
    String mensaje = "";

/* METODOS DE LA INTERFAZ CRUD */

/**
* Lista los elementos de la tabla
* @return List
*/
@Override
public ArrayList listar() {
    ArrayList< HorarioCitasDia > lista = new ArrayList<>();
    String sql = "SELECT HOCI_ID, HOCI_HORAINICIO, HOCI_MINUTOINICIO, HOCI_HORAFIN, HOCI_MINUTOFIN, HOCI_ACTIVO, HOCI_REGISTRADOPOR, HOCI_FECHACAMBIO, CONCAT(HOCI_HORAINICIO,':', HOCI_MINUTOINICIO) HORA FROM APPCONSULTAS.HORARIOCITASDIA";
         
   try{
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            horarioCitasDia = new HorarioCitasDia();
            horarioCitasDia.setIdHorarioCitasDia(rs.getInt("HOCI_ID"));
            horarioCitasDia.setHoraInicio(rs.getString("HOCI_HORAINICIO"));
            horarioCitasDia.setMinutoInicio(rs.getString("HOCI_MINUTOINICIO"));
            horarioCitasDia.setHoraFin(rs.getString("HOCI_HORAFIN"));
            horarioCitasDia.setMinutoFin(rs.getString("HOCI_MINUTOFIN"));
            horarioCitasDia.setActivo(rs.getString("HOCI_ACTIVO"));
            horarioCitasDia.setRegistradoPor(rs.getString("HOCI_REGISTRADOPOR"));
            horarioCitasDia.setFechaCambio(rs.getString("HOCI_FECHACAMBIO"));
            horarioCitasDia.setHora(rs.getString("HORA"));
            lista.add(horarioCitasDia);
       }
             //JOptionPane.showMessageDialog(null, lista.size(), "Resultado de la Operación", JOptionPane.INFORMATION_MESSAGE);
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase HorarioCitasDiaDAO ::> function public Object listar() ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase HorarioCitasDiaDAO ::> function public Object listar() ::> Exception ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }finally{
        try {
            con.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(MedicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    ArrayList< HorarioCitasDia > lista = new ArrayList<>();
    String sql = "SELECT * FROM (SELECT HOCI_ID , HOCI_HORAINICIO , HOCI_MINUTOINICIO , HOCI_HORAFIN , HOCI_MINUTOFIN , HOCI_ACTIVO , HOCI_REGISTRADOPOR , HOCI_FECHACAMBIO , ROW_NUMBER() OVER (ORDER BY hoci_id) AS CONT FROM APPCONSULTAS.HORARIOCITASDIA WHERE POSITION(? IN MOCO_DESCRIPCION)>0 ORDER BY MOCO_ID) AS TABLA WHERE CONT>=? AND CONT<=? ORDER BY CONT";
    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, pagina);
        ps.setInt(2, desde);
        ps.setInt(3, hasta);
        rs = ps.executeQuery();

        while (rs.next()){

            HorarioCitasDia horarioCitasDia = new HorarioCitasDia();
            horarioCitasDia.setIdHorarioCitasDia(rs.getInt("HOCI_ID"));
            horarioCitasDia.setHoraInicio(rs.getString("HOCI_HORAINICIO"));
            horarioCitasDia.setMinutoInicio(rs.getString("HOCI_MINUTOINICIO"));
            horarioCitasDia.setHoraFin(rs.getString("HOCI_HORAFIN"));
            horarioCitasDia.setMinutoFin(rs.getString("HOCI_MINUTOFIN"));
            horarioCitasDia.setActivo(rs.getString("HOCI_ACTIVO"));
            horarioCitasDia.setRegistradoPor(rs.getString("HOCI_REGISTRADOPOR"));
            horarioCitasDia.setFechaCambio(rs.getString("HOCI_FECHACAMBIO"));
            lista.add(horarioCitasDia);

        }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase HorarioCitasDiaDAO ::> function public Object listar(String filtro) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase HorarioCitasDiaDAO ::> function public Object listar(String filtro) ::> Exception ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }finally{
        try {
            con.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(MedicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    //int posicion=parametro.indexOf("*", 0);
    String fecha = parametro.substring(0,10);
    String idMedico = parametro.substring(10,parametro.length());

    
    String sql = "SELECT * FROM "
            + "(SELECT B.*,A.PERS_ID, A. A.CONS_ID, A.CONSULTORIO, A.NOMBRE FROM  "
            + "(SELECT M.PERS_ID, M.HOAT_ID, CONCAT(H.HOAT_HORAINICIO,H.HOAT_MINUTOINICIO) INICIO,  "
            + "CONCAT(H.HOAT_HORAFIN,H.HOAT_MINUTOFIN) FIN, CONCAT(H.HOAT_HORAINICIO,':',H.HOAT_MINUTOINICIO) HORA, M.CONS_ID, C.CONS_NOMBRE CONSULTORIO,   " 
            + "CONCAT(P.PERS_PRIMERAPELLIDO,' ',P.PERS_SEGUNDOAPELLIDO,' ',P.PERS_PRIMERNOMBRE,' ',P.PERS_SEGUNDONOMBRE) NOMBRE  "
            + "FROM APPCONSULTAS.MEDICOHORARIO M , APPCONSULTAS.HORARIOSATENCION H, APPCONSULTAS.CONSULTORIO C, APPCONSULTAS.PERSONA P   "
            + "WHERE M.HOAT_ID = H.HOAT_ID AND M.PERS_ID = P.PERS_ID AND M.CONS_ID = C.CONS_ID ) A,  "
            + "(SELECT HOCI_ID, CONCAT(HOCI_HORAINICIO,HOCI_MINUTOINICIO) HI, CONCAT(H.HOCI_HORAINICIO,':',H.HOCI_MINUTOINICIO) HORA, CONCAT(HOCI_HORAFIN,HOCI_MINUTOFIN) HF FROM APPCONSULTAS.HORARIOCITASDIA H) B  "
            + "WHERE (B.HI >= A.INICIO) AND (B.HF<=A.FIN)  "
            + "ORDER BY HOAT_ID) C  "
            + "WHERE  PERS_ID = ? AND HOCI_ID NOT IN  "
            + "(SELECT HOCI_ID FROM APPCONSULTAS.CITAS  "
            + "WHERE DATE (FECHA) = ? AND PERS_IDMEDICO = ? )  ";
        
    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, idMedico);
        ps.setString(2, fecha);
        ps.setString(3, idMedico);
        rs = ps.executeQuery();
        while (rs.next()){
            horarioCitasDia = new HorarioCitasDia();
            horarioCitasDia.setIdHorarioCitasDia(rs.getInt("HOCI_ID"));
            horarioCitasDia.setInicio(rs.getString("HI"));
            horarioCitasDia.setHora(rs.getString("HORA"));
            horarioCitasDia.setIdConsultorio(rs.getInt("CONS_ID"));
            horarioCitasDia.setConsultorio(rs.getString("CONSULTORIO"));
            horarioCitasDia.setIdPers(rs.getInt("PERS_ID"));
            lista.add(horarioCitasDia);
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

    ArrayList< HorarioCitasDia > lista = new ArrayList<>();
    String sql = "SELECT * FROM APPCONSULTAS.HORARIOCITASDIA WHERE hoci_id = ? ";
    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        rs = ps.executeQuery();

        while (rs.isBeforeFirst()){

            horarioCitasDia.setIdHorarioCitasDia(rs.getInt("HOCI_ID"));
            horarioCitasDia.setHoraInicio(rs.getString("HOCI_HORAINICIO"));
            horarioCitasDia.setMinutoInicio(rs.getString("HOCI_MINUTOINICIO"));
            horarioCitasDia.setHoraFin(rs.getString("HOCI_HORAFIN"));
            horarioCitasDia.setMinutoFin(rs.getString("HOCI_MINUTOFIN"));
            horarioCitasDia.setActivo(rs.getString("HOCI_ACTIVO"));
            horarioCitasDia.setRegistradoPor(rs.getString("HOCI_REGISTRADOPOR"));
            horarioCitasDia.setFechaCambio(rs.getString("HOCI_FECHACAMBIO"));
            lista.add(horarioCitasDia);
            }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase HorarioCitasDiaDAO ::> function public Object listar(String filtro) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase HorarioCitasDiaDAO ::> function public Object listar(String filtro) ::> Exception ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }finally{
        try {
            con.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(MedicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*Cerrar.PsRs(ps,rs);*/
    }
    return horarioCitasDia;

}

/**
* Inserta un nuevo elemento en el objeto
* @return String
*/
@Override

public String add(Object dat) {
HorarioCitasDia horarioCitasDia = (HorarioCitasDia) dat;
    String mensaje = "";
    String sql = "";
    try{
        con = cnx.getConnection();
        sql = "INSERT INTO APPCONSULTAS.HORARIOCITASDIA ( HOCI_HORAINICIO , HOCI_MINUTOINICIO , HOCI_HORAFIN , HOCI_MINUTOFIN , HOCI_ACTIVO , HOCI_REGISTRADOPOR , HOCI_FECHACAMBIO ) VALUES ( ? , ? , ? , ? , ? , ? , ? )";
        ps.setString(1, horarioCitasDia.getHoraInicio());
        ps.setString(2, horarioCitasDia.getMinutoInicio());
        ps.setString(3, horarioCitasDia.getHoraFin());
        ps.setString(4, horarioCitasDia.getMinutoFin());
        ps.setString(5, horarioCitasDia.getActivo());
        ps.setString(6, horarioCitasDia.getRegistradoPor());
        ps.setDate(7, java.sql.Date.valueOf(java.time.LocalDate.now()));
        ps.executeUpdate();
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase HorarioCitasDiaDAO ::> function public String insertar(Object dat) ::> SQLException ::> " + e.getMessage());
        horarioCitasDia.setError(e.getMessage());
        mensaje = e.getMessage();
        e.printStackTrace();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase HorarioCitasDiaDAO ::> function public String insertar(Object dat) ::> Exception ::> " + e.getMessage());
        e.printStackTrace();
    }finally{
        try {
            con.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(MedicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    HorarioCitasDia horarioCitasDia = new HorarioCitasDia();
    ArrayList< HorarioCitasDia > lista = new ArrayList<>();
    String sql = "";
    try{
        con = cnx.getConnection();
        sql = "SELECT * FROM APPCONSULTAS.HORARIOCITASDIA WHERE hoci_id = ? ";
        ps = con.prepareStatement(sql);
        ps.setString(1, llave);
        rs = ps.executeQuery();
        while (rs.next()) {
            horarioCitasDia.setIdHorarioCitasDia(rs.getInt("HOCI_ID"));
            horarioCitasDia.setHoraInicio(rs.getString("HOCI_HORAINICIO"));
            horarioCitasDia.setMinutoInicio(rs.getString("HOCI_MINUTOINICIO"));
            horarioCitasDia.setHoraFin(rs.getString("HOCI_HORAFIN"));
            horarioCitasDia.setMinutoFin(rs.getString("HOCI_MINUTOFIN"));
            horarioCitasDia.setActivo(rs.getString("HOCI_ACTIVO"));
            horarioCitasDia.setRegistradoPor(rs.getString("HOCI_REGISTRADOPOR"));
            horarioCitasDia.setFechaCambio(rs.getString("HOCI_FECHACAMBIO"));
        }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase HorarioCitasDiaDAO ::> function public Object consultar(String llave) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase HorarioCitasDiaDAO ::> function public Object consultar(String llave) ::> Exception ::> " + e.getMessage());
        e.printStackTrace();
    }finally{
        try {
            con.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(MedicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*Cerrar.PsRs(ps,rs);*/
    }
    return horarioCitasDia;
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
HorarioCitasDia horarioCitasDia = (HorarioCitasDia) dat;
    String mensaje = "";
    String sql =
    "UPDATE APPCONSULTAS.HORARIOCITASDIA SET "
    +" HOCI_HORAINICIO = ? , "
    +" HOCI_MINUTOINICIO = ? , "
    +" HOCI_HORAFIN = ? , "
    +" HOCI_MINUTOFIN = ? , "
    +" HOCI_ACTIVO = ? , "
    +" HOCI_REGISTRADOPOR = ? , "
    +" HOCI_FECHACAMBIO = ? "
    +" WHERE hoci_id = ? ";
    try{
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, horarioCitasDia.getHoraInicio());
        ps.setString(2, horarioCitasDia.getMinutoInicio());
        ps.setString(3, horarioCitasDia.getHoraFin());
        ps.setString(4, horarioCitasDia.getMinutoFin());
        ps.setString(5, horarioCitasDia.getActivo());
        ps.setString(6, horarioCitasDia.getRegistradoPor());
        ps.setDate(7, java.sql.Date.valueOf(java.time.LocalDate.now()));
        ps.setInt(8, horarioCitasDia.getIdHorarioCitasDia());
        ps.executeUpdate();

    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase HorarioCitasDiaDAO ::> function public String actualizar(Object dat) ::> SQLException ::> " + e.getMessage());
        horarioCitasDia.setError(e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase HorarioCitasDiaDAO ::> function public String actualizar(Object dat) ::> Exception ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }finally{
        try {
            con.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(MedicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    String sql = "DELETE FROM APPCONSULTAS.HORARIOCITASDIA WHERE hoci_id = ? ";

    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();

    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase HorarioCitasDiaDAO ::> function public Object eliminar(String filtro) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
    }catch(Exception e){
        mensaje = e.getMessage();
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase HorarioCitasDiaDAO ::> function public Object eliminar(String filtro) ::> Exception ::> " + e.getMessage());
        e.printStackTrace();
    }finally{
        try {
            con.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(MedicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
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
* 26-SEPTIEMBRE-2021 @: GRUPO DE DESARROLLO 3 &: creación
*/

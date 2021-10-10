/*
* MedicoHorarioDAO.java
*
* Creado el 03 de octubre de 2021, 23:16
*/

package ModeloDAO;

import Config.Conexion;
import Interfaces.CRUD;
import Modelo.MedicoHorario;
import java.sql.*;
import java.util.*;
/**
*
* @autor GRUPO DE DESARROLLO 3 - MINTIC UIS
*/

public class MedicoHorarioDAO implements CRUD {

    /** Definición de la conexión */
    Conexion cnx = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    MedicoHorario medicoHorario = new MedicoHorario();
    String mensaje = "";

/* METODOS DE LA INTERFAZ CRUD */

/**
* Lista los elementos de la tabla
* @return List
*/
@Override
public List listar() {
    ArrayList< MedicoHorario > lista = new ArrayList<>();
    String sql = "SELECT * FROM APPCONSULTAS.MEDICOHORARIO";
    try{
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            medicoHorario = new MedicoHorario();
            medicoHorario.setIdMedicoHorario(rs.getInt("MEHO_ID"));
            medicoHorario.setIdPers(rs.getInt("PERS_ID"));
            medicoHorario.setHoat_idHorarioTrabajo(rs.getInt("HOAT_ID"));
            medicoHorario.setRegistradoPor(rs.getString("MEHO_REGISTRADOPOR"));
            medicoHorario.setFechaCambio(rs.getTimestamp("MEHO_FECHACAMBIO"));
            medicoHorario.setIdConsultorio(rs.getInt("CONS_ID"));
            lista.add(medicoHorario);
        }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase MedicoHorarioDAO ::> function public Object listar() ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase MedicoHorarioDAO ::> function public Object listar() ::> Exception ::> " + e.getMessage());
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

    ArrayList< MedicoHorario > lista = new ArrayList<>();
    String sql = "SELECT * FROM (SELECT MEHO_ID , PERS_ID , HOAT_ID , MEHO_REGISTRADOPOR , MEHO_FECHACAMBIO , CONS_ID , ROW_NUMBER() OVER (ORDER BY meho_id) AS CONT FROM APPCONSULTAS.MEDICOHORARIO WHERE POSITION(? IN MOCO_DESCRIPCION)>0 ORDER BY MOCO_ID) AS TABLA WHERE CONT>=? AND CONT<=? ORDER BY CONT";
    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, pagina);
        ps.setInt(2, desde);
        ps.setInt(3, hasta);
        rs = ps.executeQuery();

        while (rs.next()){

            MedicoHorario medicoHorario = new MedicoHorario();
            medicoHorario.setIdMedicoHorario(rs.getInt("MEHO_ID"));
            medicoHorario.setIdPers(rs.getInt("PERS_ID"));
            medicoHorario.setHoat_idHorarioTrabajo(rs.getInt("HOAT_ID"));
            medicoHorario.setRegistradoPor(rs.getString("MEHO_REGISTRADOPOR"));
            medicoHorario.setFechaCambio(rs.getTimestamp("MEHO_FECHACAMBIO"));
            medicoHorario.setIdConsultorio(rs.getInt("CONS_ID"));
            lista.add(medicoHorario);

        }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase MedicoHorarioDAO ::> function public Object listar(String filtro) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase MedicoHorarioDAO ::> function public Object listar(String filtro) ::> Exception ::> " + e.getMessage());
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
    String sql = "Select * from APPCONSULTAS.MEDICOHORARIO where campo_referencia like ? ";
    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, "%"+parametro+"%");
        rs = ps.executeQuery();
        while (rs.next()){
            medicoHorario = new MedicoHorario();
            medicoHorario.setIdMedicoHorario(rs.getInt("MEHO_ID"));
            medicoHorario.setIdPers(rs.getInt("PERS_ID"));
            //medicoHorario.setCampoReferencia(rs.getString("campo_referencia"));
            lista.add(medicoHorario);
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

    ArrayList< MedicoHorario > lista = new ArrayList<>();
    String sql = "SELECT * FROM APPCONSULTAS.MEDICOHORARIO WHERE pers_id = ? AND hoat_id = ? ";
    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        rs = ps.executeQuery();

        while (rs.isBeforeFirst()){

            medicoHorario.setIdMedicoHorario(rs.getInt("MEHO_ID"));
            medicoHorario.setIdPers(rs.getInt("PERS_ID"));
            medicoHorario.setHoat_idHorarioTrabajo(rs.getInt("HOAT_ID"));
            medicoHorario.setRegistradoPor(rs.getString("MEHO_REGISTRADOPOR"));
            medicoHorario.setFechaCambio(rs.getTimestamp("MEHO_FECHACAMBIO"));
            medicoHorario.setIdConsultorio(rs.getInt("CONS_ID"));
            lista.add(medicoHorario);

            }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase MedicoHorarioDAO ::> function public Object listar(String filtro) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase MedicoHorarioDAO ::> function public Object listar(String filtro) ::> Exception ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }finally{
        /*Cerrar.PsRs(ps,rs);*/
    }
    return medicoHorario;

}

/**
* Inserta un nuevo elemento en el objeto
* @return String
*/
@Override

public String add(Object dat) {
MedicoHorario medicoHorario = (MedicoHorario) dat;
    String mensaje = "";
    String sql = "";
    try{
        con = cnx.getConnection();
        sql = "INSERT INTO APPCONSULTAS.MEDICOHORARIO ( PERS_ID , HOAT_ID , MEHO_REGISTRADOPOR , MEHO_FECHACAMBIO , CONS_ID ) VALUES ( ? , ? , ? , ? , ? )";
        ps.setInt(1, medicoHorario.getIdPers());
        ps.setInt(2, medicoHorario.getHoat_idHorarioTrabajo());
        ps.setString(3, medicoHorario.getRegistradoPor());
        ps.setDate(4, java.sql.Date.valueOf(java.time.LocalDate.now()));
        ps.setInt(5, medicoHorario.getIdConsultorio());
        ps.executeUpdate();
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase MedicoHorarioDAO ::> function public String insertar(Object dat) ::> SQLException ::> " + e.getMessage());
        medicoHorario.setError(e.getMessage());
        mensaje = e.getMessage();
        e.printStackTrace();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase MedicoHorarioDAO ::> function public String insertar(Object dat) ::> Exception ::> " + e.getMessage());
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
    MedicoHorario medicoHorario = new MedicoHorario();
    ArrayList< MedicoHorario > lista = new ArrayList<>();
    String sql = "";
    try{
        con = cnx.getConnection();
        sql = "SELECT * FROM APPCONSULTAS.MEDICOHORARIO WHERE meho_id = ? ";
        ps = con.prepareStatement(sql);
        ps.setString(1, llave);
        rs = ps.executeQuery();
        while (rs.next()) {
            medicoHorario.setIdMedicoHorario(rs.getInt("MEHO_ID"));
            medicoHorario.setIdPers(rs.getInt("PERS_ID"));
            medicoHorario.setHoat_idHorarioTrabajo(rs.getInt("HOAT_ID"));
            medicoHorario.setRegistradoPor(rs.getString("MEHO_REGISTRADOPOR"));
            medicoHorario.setFechaCambio(rs.getTimestamp("MEHO_FECHACAMBIO"));
            medicoHorario.setIdConsultorio(rs.getInt("CONS_ID"));
        }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase MedicoHorarioDAO ::> function public Object consultar(String llave) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase MedicoHorarioDAO ::> function public Object consultar(String llave) ::> Exception ::> " + e.getMessage());
        e.printStackTrace();
    }finally{
        /*Cerrar.PsRs(ps,rs);*/
    }
    return medicoHorario;
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
MedicoHorario medicoHorario = (MedicoHorario) dat;
    String mensaje = "";
    String sql =
    "UPDATE APPCONSULTAS.MEDICOHORARIO SET "
    +" PERS_ID = ? , "
    +" HOAT_ID = ? , "
    +" MEHO_REGISTRADOPOR = ? , "
    +" MEHO_FECHACAMBIO = ? , "
    +" CONS_ID = ? "
    +" WHERE meho_id = ? ";
    try{
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, medicoHorario.getIdPers());
        ps.setInt(2, medicoHorario.getHoat_idHorarioTrabajo());
        ps.setString(3, medicoHorario.getRegistradoPor());
        ps.setDate(4, java.sql.Date.valueOf(java.time.LocalDate.now()));
        ps.setInt(5, medicoHorario.getIdConsultorio());
        ps.setInt(6, medicoHorario.getIdMedicoHorario());
        ps.executeUpdate();

    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase MedicoHorarioDAO ::> function public String actualizar(Object dat) ::> SQLException ::> " + e.getMessage());
        medicoHorario.setError(e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase MedicoHorarioDAO ::> function public String actualizar(Object dat) ::> Exception ::> " + e.getMessage());
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
    String sql = "DELETE FROM APPCONSULTAS.MEDICOHORARIO WHERE meho_id = ? ";

    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();

    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase MedicoHorarioDAO ::> function public Object eliminar(String filtro) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
    }catch(Exception e){
        mensaje = e.getMessage();
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase MedicoHorarioDAO ::> function public Object eliminar(String filtro) ::> Exception ::> " + e.getMessage());
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
* 03-OCTUBRE-2021 @: GRUPO DE DESARROLLO 3 &: creación
*/

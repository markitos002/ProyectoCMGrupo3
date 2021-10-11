/*
* RolPersonaDAO.java
*
* Creado el 01 de octubre de 2021, 21:14
*/

package ModeloDAO;

import Config.Conexion;
import Interfaces.CRUD;
import Modelo.RolPersona;
import java.sql.*;
import java.util.*;
/**
*
* @autor GRUPO DE DESARROLLO 3 - MINTIC UIS
*/

public class RolPersonaDAO implements CRUD {

    /** Definición de la conexión */
    Conexion cnx = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    RolPersona rolPersona = new RolPersona();
    String mensaje = "";

/* METODOS DE LA INTERFAZ CRUD */

/**
* Lista los elementos de la tabla
* @return List
*/
@Override
public List listar() {
    ArrayList< RolPersona > lista = new ArrayList<>();
    String sql = "SELECT * FROM APPCONSULTAS.ROLPERSONA";
    try{
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            rolPersona = new RolPersona();
            rolPersona.setIdRolPersona(rs.getInt("ROPE_ID"));
            rolPersona.setIdRol(rs.getInt("ROL_ID"));
            rolPersona.setIdPers(rs.getInt("PERS_ID"));
            rolPersona.setRegistradoPor(rs.getString("ROPE_REGISTRADOPOR"));
            rolPersona.setFechaCambio(rs.getTimestamp("ROPE_FECHACAMBIO"));
            lista.add(rolPersona);
        }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase RolPersonaDAO ::> function public Object listar() ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase RolPersonaDAO ::> function public Object listar() ::> Exception ::> " + e.getMessage());
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

    ArrayList< RolPersona > lista = new ArrayList<>();
    String sql = "SELECT * FROM (SELECT ROPE_ID , ROL_ID , PERS_ID , ROPE_REGISTRADOPOR , ROPE_FECHACAMBIO , ROW_NUMBER() OVER (ORDER BY rope_id) AS CONT FROM APPCONSULTAS.ROLPERSONA WHERE POSITION(? IN MOCO_DESCRIPCION)>0 ORDER BY MOCO_ID) AS TABLA WHERE CONT>=? AND CONT<=? ORDER BY CONT";
    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, pagina);
        ps.setInt(2, desde);
        ps.setInt(3, hasta);
        rs = ps.executeQuery();

        while (rs.next()){

            RolPersona rolPersona = new RolPersona();
            rolPersona.setIdRolPersona(rs.getInt("ROPE_ID"));
            rolPersona.setIdRol(rs.getInt("ROL_ID"));
            rolPersona.setIdPers(rs.getInt("PERS_ID"));
            rolPersona.setRegistradoPor(rs.getString("ROPE_REGISTRADOPOR"));
            rolPersona.setFechaCambio(rs.getTimestamp("ROPE_FECHACAMBIO"));
            lista.add(rolPersona);

        }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase RolPersonaDAO ::> function public Object listar(String filtro) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase RolPersonaDAO ::> function public Object listar(String filtro) ::> Exception ::> " + e.getMessage());
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
    String sql = "Select * from APPCONSULTAS.ROLPERSONA where campo_referencia like ? ";
    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, "%"+parametro+"%");
        rs = ps.executeQuery();
        while (rs.next()){
            rolPersona = new RolPersona();
            rolPersona.setIdRolPersona(rs.getInt("ROPE_ID"));
            rolPersona.setIdRol(rs.getInt("ROL_ID"));
            //rolPersona.setCampoReferencia(rs.getString("campo_referencia"));
            lista.add(rolPersona);
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

    ArrayList< RolPersona > lista = new ArrayList<>();
    String sql = "SELECT * FROM APPCONSULTAS.ROLPERSONA WHERE rol_id = ? AND pers_id = ? ";
    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        rs = ps.executeQuery();

        while (rs.isBeforeFirst()){

            rolPersona.setIdRolPersona(rs.getInt("ROPE_ID"));
            rolPersona.setIdRol(rs.getInt("ROL_ID"));
            rolPersona.setIdPers(rs.getInt("PERS_ID"));
            rolPersona.setRegistradoPor(rs.getString("ROPE_REGISTRADOPOR"));
            rolPersona.setFechaCambio(rs.getTimestamp("ROPE_FECHACAMBIO"));
            lista.add(rolPersona);

            }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase RolPersonaDAO ::> function public Object listar(String filtro) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase RolPersonaDAO ::> function public Object listar(String filtro) ::> Exception ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }finally{
        /*Cerrar.PsRs(ps,rs);*/
    }
    return rolPersona;

}

/**
* Inserta un nuevo elemento en el objeto
* @return String
*/
@Override

public String add(Object dat) {
RolPersona rolPersona = (RolPersona) dat;
    String mensaje = "";
    String sql = "";
    try{
        con = cnx.getConnection();
        sql = "INSERT INTO APPCONSULTAS.ROLPERSONA ( ROL_ID , PERS_ID , ROPE_REGISTRADOPOR , ROPE_FECHACAMBIO ) VALUES ( ? , ? , ? , ? )";
        ps.setInt(1, rolPersona.getIdRol());
        ps.setInt(2, rolPersona.getIdPers());
        ps.setString(3, rolPersona.getRegistradoPor());
        ps.setDate(4, java.sql.Date.valueOf(java.time.LocalDate.now()));
        ps.executeUpdate();
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase RolPersonaDAO ::> function public String insertar(Object dat) ::> SQLException ::> " + e.getMessage());
        rolPersona.setError(e.getMessage());
        mensaje = e.getMessage();
        e.printStackTrace();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase RolPersonaDAO ::> function public String insertar(Object dat) ::> Exception ::> " + e.getMessage());
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
    RolPersona rolPersona = new RolPersona();
    ArrayList< RolPersona > lista = new ArrayList<>();
    String sql = "";
    try{
        con = cnx.getConnection();
        sql = "SELECT * FROM APPCONSULTAS.ROLPERSONA WHERE rope_id = ? ";
        ps = con.prepareStatement(sql);
        ps.setString(1, llave);
        rs = ps.executeQuery();
        while (rs.next()) {
            rolPersona.setIdRolPersona(rs.getInt("ROPE_ID"));
            rolPersona.setIdRol(rs.getInt("ROL_ID"));
            rolPersona.setIdPers(rs.getInt("PERS_ID"));
            rolPersona.setRegistradoPor(rs.getString("ROPE_REGISTRADOPOR"));
            rolPersona.setFechaCambio(rs.getTimestamp("ROPE_FECHACAMBIO"));
        }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase RolPersonaDAO ::> function public Object consultar(String llave) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase RolPersonaDAO ::> function public Object consultar(String llave) ::> Exception ::> " + e.getMessage());
        e.printStackTrace();
    }finally{
        /*Cerrar.PsRs(ps,rs);*/
    }
    return rolPersona;
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
RolPersona rolPersona = (RolPersona) dat;
    String mensaje = "";
    String sql =
    "UPDATE APPCONSULTAS.ROLPERSONA SET "
    +" ROL_ID = ? , "
    +" PERS_ID = ? , "
    +" ROPE_REGISTRADOPOR = ? , "
    +" ROPE_FECHACAMBIO = ? "
    +" WHERE rope_id = ? ";
    try{
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, rolPersona.getIdRol());
        ps.setInt(2, rolPersona.getIdPers());
        ps.setString(3, rolPersona.getRegistradoPor());
        ps.setDate(4, java.sql.Date.valueOf(java.time.LocalDate.now()));
        ps.setInt(5, rolPersona.getIdRolPersona());
        ps.executeUpdate();

    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase RolPersonaDAO ::> function public String actualizar(Object dat) ::> SQLException ::> " + e.getMessage());
        rolPersona.setError(e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase RolPersonaDAO ::> function public String actualizar(Object dat) ::> Exception ::> " + e.getMessage());
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
    String sql = "DELETE FROM APPCONSULTAS.ROLPERSONA WHERE rope_id = ? ";

    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();

    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase RolPersonaDAO ::> function public Object eliminar(String filtro) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
    }catch(Exception e){
        mensaje = e.getMessage();
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase RolPersonaDAO ::> function public Object eliminar(String filtro) ::> Exception ::> " + e.getMessage());
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
* 01-OCTUBRE-2021 @: GRUPO DE DESARROLLO 3 &: creación
*/


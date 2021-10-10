/*
* UsuarioDAO.java
*
* Creado el 01 de octubre de 2021, 07:17
*/

package ModeloDAO;

import Config.Conexion;
import Interfaces.CRUD;
import Modelo.Usuario;
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;
/**
*
* @autor GRUPO DE DESARROLLO 3 - MINTIC UIS
*/

public class UsuarioDAO implements CRUD {

    /** Definición de la conexión */
    Conexion cnx = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Usuario usuario = new Usuario();
    String mensaje = "";

/* METODOS DE LA INTERFAZ CRUD */

/**
* Lista los elementos de la tabla
* @return List
*/
@Override
public List listar() {
    ArrayList< Usuario > lista = new ArrayList<>();
    String sql = "SELECT PE.PERS_ID, CONCAT(PE.PERS_PRIMERAPELLIDO,' ',PE.PERS_SEGUNDOAPELLIDO,' ',PE.PERS_PRIMERNOMBRE,' ',PE.PERS_SEGUNDONOMBRE) NOMBRE, "
            + "PE.PERS_MAIL, RO.ROL_NOMBRE, US.USUA_ID, US.ROPE_ID, US.USUA_USUARIO, US.USUA_CONTRASENA "
            + "FROM APPCONSULTAS.USUARIO US, APPCONSULTAS.ROLPERSONA RP, APPCONSULTAS.ROL RO,  APPCONSULTAS.PERSONA PE "
            + "WHERE US.ROPE_ID=RP.ROPE_ID AND RP.ROL_ID=RO.ROL_ID AND RP.PERS_ID = PE.PERS_ID ";

    try{
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
            
        while (rs.next()) {
            
            usuario = new Usuario();
            usuario.setIdUsuario(rs.getInt("USUA_ID"));
            usuario.setIdentificacion(rs.getString("PERS_ID"));
            usuario.setNombre(rs.getString("NOMBRE"));
            usuario.setMail(rs.getString("PERS_MAIL"));
            usuario.setIdRope(rs.getInt("ROPE_ID"));
            usuario.setRol(rs.getString("ROL_NOMBRE"));
            usuario.setUsuario(rs.getString("USUA_USUARIO"));
            usuario.setContrasena(rs.getString("USUA_CONTRASENA"));
            //usuario.setRegistradoPor(rs.getString("USUA_REGISTRADOPOR"));
            //usuario.setFechaCambio(rs.getString("USUA_FECHACAMBIO"));
            lista.add(usuario);
        }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase UsuarioDAO ::> function public Object listar() ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase UsuarioDAO ::> function public Object listar() ::> Exception ::> " + e.getMessage());
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

public List listarPagina(String parametro, int desde, int hasta) {

    ArrayList< Usuario > lista = new ArrayList<>();
    String sql = "SELECT * FROM (  "
        + "SELECT PE.PERS_ID, CONCAT(PE.PERS_PRIMERAPELLIDO,' ',PE.PERS_SEGUNDOAPELLIDO,' ',PE.PERS_PRIMERNOMBRE,' ',PE.PERS_SEGUNDONOMBRE) NOMBRE, "
        + "PE.PERS_MAIL, RO.ROL_NOMBRE, US.USUA_ID, US.USUA_USUARIO, US.USUA_CONTRASENA, US.ROPE_ID,  ROW_NUMBER() OVER (ORDER BY usua_id) AS CONT "
        + "FROM APPCONSULTAS.USUARIO US, APPCONSULTAS.ROLPERSONA RP, APPCONSULTAS.ROL RO,  APPCONSULTAS.PERSONA PE  "
        + "WHERE POSITION( ? IN CONCAT(PE.PERS_PRIMERAPELLIDO,' ',PE.PERS_SEGUNDOAPELLIDO,' ',PE.PERS_PRIMERNOMBRE,' ',PE.PERS_SEGUNDONOMBRE))>0 "
        + "AND US.ROPE_ID=RP.ROPE_ID AND RP.ROL_ID=RO.ROL_ID AND RP.PERS_ID = PE.PERS_ID ORDER BY US.USUA_ID) AS TABLA "
        + "WHERE CONT>=? AND CONT<=? ORDER BY CONT";


    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, parametro);
        ps.setInt(2, desde);
        ps.setInt(3, hasta);
        rs = ps.executeQuery();

        while (rs.next()){

            Usuario usuario = new Usuario();
            usuario.setIdUsuario(rs.getInt("USUA_ID"));
            usuario.setIdentificacion(rs.getString("PERS_ID"));
            usuario.setNombre(rs.getString("NOMBRE"));        
            usuario.setIdRope(rs.getInt("ROPE_ID"));
            usuario.setRol(rs.getString("ROL_NOMBRE"));
            usuario.setUsuario(rs.getString("USUA_USUARIO"));
            usuario.setContrasena(rs.getString("USUA_CONTRASENA"));
            //usuario.setRegistradoPor(rs.getString("USUA_REGISTRADOPOR"));
            //usuario.setFechaCambio(rs.getString("USUA_FECHACAMBIO"));
            lista.add(usuario);

        }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase UsuarioDAO ::> function public Object listar(String filtro) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase UsuarioDAO ::> function public Object listar(String filtro) ::> Exception ::> " + e.getMessage());
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
    String sql = "Select * from APPCONSULTAS.USUARIO where campo_referencia like ? ";
    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, "%"+parametro+"%");
        rs = ps.executeQuery();
        while (rs.next()){
            usuario = new Usuario();
            usuario.setIdUsuario(rs.getInt("USUA_ID"));
            usuario.setIdRope(rs.getInt("ROPE_ID"));
            //usuario.setCampoReferencia(rs.getString("campo_referencia"));
            lista.add(usuario);
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

    ArrayList< Usuario > lista = new ArrayList<>();
    String sql = "SELECT * FROM APPCONSULTAS.USUARIO WHERE rope_id = ? ";
    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        rs = ps.executeQuery();

        while (rs.isBeforeFirst()){

            usuario.setIdUsuario(rs.getInt("USUA_ID"));
            usuario.setIdRope(rs.getInt("ROPE_ID"));
            usuario.setUsuario(rs.getString("USUA_USUARIO"));
            usuario.setContrasena(rs.getString("USUA_CONTRASENA"));
            usuario.setRegistradoPor(rs.getString("USUA_REGISTRADOPOR"));
            usuario.setFechaCambio(rs.getString("USUA_FECHACAMBIO"));
            lista.add(usuario);

            }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase UsuarioDAO ::> function public Object listar(String filtro) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase UsuarioDAO ::> function public Object listar(String filtro) ::> Exception ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }finally{
        /*Cerrar.PsRs(ps,rs);*/
    }
    return usuario;

}

/**
* Inserta un nuevo elemento en el objeto
* @return String
*/
@Override

public String add(Object dat) {
Usuario usuario = (Usuario) dat;
    String mensaje = "";
    String sql = "";
    try{
        con = cnx.getConnection();
        sql = "INSERT INTO APPCONSULTAS.USUARIO ( ROPE_ID , USUA_USUARIO , USUA_CONTRASENA , USUA_REGISTRADOPOR , USUA_FECHACAMBIO ) VALUES ( ? , ? , ? , ? , ? )";
        ps.setInt(1, usuario.getIdRope());
        ps.setString(2, usuario.getUsuario());
        ps.setString(3, usuario.getContrasena());
        ps.setString(4, usuario.getRegistradoPor());
        ps.setDate(5, java.sql.Date.valueOf(java.time.LocalDate.now()));
        ps.executeUpdate();
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase UsuarioDAO ::> function public String insertar(Object dat) ::> SQLException ::> " + e.getMessage());
        usuario.setError(e.getMessage());
        mensaje = e.getMessage();
        e.printStackTrace();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase UsuarioDAO ::> function public String insertar(Object dat) ::> Exception ::> " + e.getMessage());
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
    Usuario usuario = new Usuario();
    ArrayList< Usuario > lista = new ArrayList<>();
    String sql = "";
    try{
        con = cnx.getConnection();
        sql = "SELECT PE.PERS_ID, CONCAT(PE.PERS_PRIMERAPELLIDO,' ',PE.PERS_SEGUNDOAPELLIDO,' ',PE.PERS_PRIMERNOMBRE,' ',PE.PERS_SEGUNDONOMBRE) NOMBRE, "
                + "PE.PERS_MAIL, RO.ROL_NOMBRE, US.ROPE_ID, US.USUA_USUARIO, US.USUA_CONTRASENA "
                + "FROM APPCONSULTAS.USUARIO US, APPCONSULTAS.ROLPERSONA RP, APPCONSULTAS.ROL RO,  APPCONSULTAS.PERSONA PE "
                + "WHERE US.ROPE_ID=RP.ROPE_ID AND RP.ROL_ID=RO.ROL_ID AND RP.PERS_ID = PE.PERS_ID AND USUA_ID = ? ";
        ps = con.prepareStatement(sql);
        ps.setString(1, llave);
        rs = ps.executeQuery();
        while (rs.next()) {
            usuario.setIdUsuario(rs.getInt("USUA_ID"));
            usuario.setMail(rs.getString("PERS_MAIL"));
            usuario.setNombre(rs.getString("NOMBRE"));
            usuario.setIdRope(rs.getInt("ROPE_ID"));
            usuario.setUsuario(rs.getString("USUA_USUARIO"));
            usuario.setContrasena(rs.getString("USUA_CONTRASENA"));
            usuario.setRegistradoPor(rs.getString("USUA_REGISTRADOPOR"));
            usuario.setFechaCambio(rs.getString("USUA_FECHACAMBIO"));
        }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase UsuarioDAO ::> function public Object consultar(String llave) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase UsuarioDAO ::> function public Object consultar(String llave) ::> Exception ::> " + e.getMessage());
        e.printStackTrace();
    }finally{
        /*Cerrar.PsRs(ps,rs);*/
    }
    return usuario;
}

public boolean buscarUsuario(String user, String password) {
    Usuario usuario = new Usuario();
    ArrayList< Usuario > lista = new ArrayList<>();
    String sql = "";
    boolean encontrado = false;
    try{
        con = cnx.getConnection();
        sql = "SELECT PE.PERS_ID, CONCAT(PE.PERS_PRIMERAPELLIDO,' ',PE.PERS_SEGUNDOAPELLIDO,' ',PE.PERS_PRIMERNOMBRE,' ',PE.PERS_SEGUNDONOMBRE) NOMBRE, "
                + "PE.PERS_ID, PE.PERS_MAIL, RO.ROL_NOMBRE, US.ROPE_ID, US.USUA_ID, US.USUA_USUARIO, US.USUA_CONTRASENA "
                + "FROM APPCONSULTAS.USUARIO US, APPCONSULTAS.ROLPERSONA RP, APPCONSULTAS.ROL RO,  APPCONSULTAS.PERSONA PE "
                + "WHERE US.ROPE_ID=RP.ROPE_ID AND RP.ROL_ID=RO.ROL_ID AND RP.PERS_ID = PE.PERS_ID AND USUA_USUARIO = ? AND USUA_CONTRASENA = ? ";
        ps = con.prepareStatement(sql);
        ps.setString(1, user);
        ps.setString(2, password);
        rs = ps.executeQuery();
        while (rs.next()) {
            usuario.setIdUsuario(rs.getInt("USUA_ID"));
            usuario.setIdentificacion(rs.getString("PERS_ID"));
            usuario.setMail(rs.getString("PERS_MAIL"));
            usuario.setNombre(rs.getString("NOMBRE"));
            usuario.setIdRope(rs.getInt("ROPE_ID"));
            usuario.setUsuario(rs.getString("USUA_USUARIO"));
            usuario.setContrasena(rs.getString("USUA_CONTRASENA"));
            //usuario.setRegistradoPor(rs.getString("USUA_REGISTRADOPOR"));
            //usuario.setFechaCambio(rs.getString("USUA_FECHACAMBIO"));
        }
        if(usuario.getIdentificacion()!=null){
            encontrado = true;
        }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase UsuarioDAO ::> function public boolean buscarUsuario(String user, String password) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase UsuarioDAO ::> function public boolean buscarUsuario(String llave, String password) ::> Exception ::> " + e.getMessage());
        e.printStackTrace();
    }finally{
        /*Cerrar.PsRs(ps,rs);*/
    }
    return encontrado;
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
Usuario usuario = (Usuario) dat;
    String mensaje = "";
    String sql =
    "UPDATE APPCONSULTAS.USUARIO SET "
    +" ROPE_ID = ? , "
    +" USUA_USUARIO = ? , "
    +" USUA_CONTRASENA = ? , "
    +" USUA_REGISTRADOPOR = ? , "
    +" USUA_FECHACAMBIO = ? "
    +" WHERE USUA_ID = ? ";
    try{
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, usuario.getIdRope());
        ps.setString(2, usuario.getUsuario());
        ps.setString(3, usuario.getContrasena());
        ps.setString(4, usuario.getRegistradoPor());
        ps.setDate(5, java.sql.Date.valueOf(java.time.LocalDate.now()));
        ps.setInt(6, usuario.getIdUsuario());
        ps.executeUpdate();

    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase UsuarioDAO ::> function public String actualizar(Object dat) ::> SQLException ::> " + e.getMessage());
        usuario.setError(e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase UsuarioDAO ::> function public String actualizar(Object dat) ::> Exception ::> " + e.getMessage());
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
    String sql = "DELETE FROM APPCONSULTAS.USUARIO WHERE usua_id = ? ";

    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();

    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase UsuarioDAO ::> function public Object eliminar(String filtro) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
    }catch(Exception e){
        mensaje = e.getMessage();
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase UsuarioDAO ::> function public Object eliminar(String filtro) ::> Exception ::> " + e.getMessage());
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

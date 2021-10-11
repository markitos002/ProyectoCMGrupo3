/*
* CiudadDAO.java
*
* Creado el 10 de octubre de 2021, 12:48
*/

package ModeloDAO;

import Config.Conexion;
import Interfaces.CRUD;
import Modelo.Ciudad;
import java.sql.*;
import java.util.*;
/**
*
* @autor GRUPO DE DESARROLLO 3 - MINTIC UIS
*/

public class CiudadDAO implements CRUD {

    /** Definición de la conexión */
    Conexion cnx = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Ciudad ciudad = new Ciudad();
    String mensaje = "";

/* METODOS DE LA INTERFAZ CRUD */

/**
* Lista los elementos de la tabla
* @return List
*/
@Override
public List listar() {
    ArrayList< Ciudad > lista = new ArrayList<>();
    String sql = "SELECT C.CIUD_ID , C.CIUD_NOMBRE , C.DEPA_ID , C.CIUD_REGISTRADOPOR , C.CIUD_FECHACAMBIO , D.DEPA_NOMBRE FROM APPCONSULTAS.CIUDAD C, APPCONSULTAS.DEPARTAMENTO D WHERE C.DEPA_ID = D.DEPA_ID ORDER BY CIUD_ID";
    try{
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            ciudad = new Ciudad();
            ciudad.setIdCiudad(rs.getInt("CIUD_ID"));
            ciudad.setNombre(rs.getString("CIUD_NOMBRE"));
            ciudad.setIdDepartamento(rs.getInt("DEPA_ID"));
            ciudad.setDepartamento(rs.getString("DEPA_NOMBRE"));
            ciudad.setRegistradoPor(rs.getString("CIUD_REGISTRADOPOR"));
            ciudad.setFechaCambio(rs.getString("CIUD_FECHACAMBIO"));
            lista.add(ciudad);
        }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase CiudadDAO ::> function public Object listar() ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase CiudadDAO ::> function public Object listar() ::> Exception ::> " + e.getMessage());
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

    ArrayList< Ciudad > lista = new ArrayList<>();
    String sql = "SELECT * FROM (SELECT C.CIUD_ID , C.CIUD_NOMBRE , C.DEPA_ID , C.CIUD_REGISTRADOPOR , C.CIUD_FECHACAMBIO , D.DEPA_NOMBRE, ROW_NUMBER() OVER (ORDER BY CIUD_ID) AS CONT FROM APPCONSULTAS.CIUDAD C, APPCONSULTAS.DEPARTAMENTO D WHERE POSITION(? IN DEPA_NOMBRE)>0 AND C.DEPA_ID = D.DEPA_ID ORDER BY DEPA_ID) AS TABLA WHERE CONT>=? AND CONT<=? ORDER BY CONT";
    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, parametro);
        ps.setInt(2, desde);
        ps.setInt(3, hasta);
        rs = ps.executeQuery();

        while (rs.next()){

            Ciudad ciudad = new Ciudad();
            ciudad.setIdCiudad(rs.getInt("CIUD_ID"));
            ciudad.setNombre(rs.getString("CIUD_NOMBRE"));
            ciudad.setIdDepartamento(rs.getInt("DEPA_ID"));
            ciudad.setDepartamento(rs.getString("DEPA_NOMBRE"));
            ciudad.setRegistradoPor(rs.getString("CIUD_REGISTRADOPOR"));
            ciudad.setFechaCambio(rs.getString("CIUD_FECHACAMBIO"));
            lista.add(ciudad);

        }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase CiudadDAO ::> function public Object listar(String filtro) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase CiudadDAO ::> function public Object listar(String filtro) ::> Exception ::> " + e.getMessage());
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
    String sql = "Select * from APPCONSULTAS.CIUDAD where campo_referencia like ? ";
    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, "%"+parametro+"%");
        rs = ps.executeQuery();
        while (rs.next()){
            ciudad = new Ciudad();
            ciudad.setIdCiudad(rs.getInt("CIUD_ID"));
            ciudad.setNombre(rs.getString("CIUD_NOMBRE"));
            //ciudad.setCampoReferencia(rs.getString("campo_referencia"));
            lista.add(ciudad);
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

    ArrayList< Ciudad > lista = new ArrayList<>();
    String sql = "SELECT * FROM APPCONSULTAS.CIUDAD WHERE depa_id = ? ";
    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        rs = ps.executeQuery();

        while (rs.isBeforeFirst()){

            ciudad.setIdCiudad(rs.getInt("CIUD_ID"));
            ciudad.setNombre(rs.getString("CIUD_NOMBRE"));
            ciudad.setIdDepartamento(rs.getInt("DEPA_ID"));
            ciudad.setRegistradoPor(rs.getString("CIUD_REGISTRADOPOR"));
            ciudad.setFechaCambio(rs.getString("CIUD_FECHACAMBIO"));
            lista.add(ciudad);

            }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase CiudadDAO ::> function public Object listar(String filtro) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase CiudadDAO ::> function public Object listar(String filtro) ::> Exception ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }finally{
        /*Cerrar.PsRs(ps,rs);*/
    }
    return ciudad;

}

/**
* Inserta un nuevo elemento en el objeto
* @return String
*/
@Override

public String add(Object dat) {
Ciudad ciudad = (Ciudad) dat;
    String mensaje = "";
    String sql = "";
    try{
        con = cnx.getConnection();
        sql = "INSERT INTO APPCONSULTAS.CIUDAD ( CIUD_NOMBRE , DEPA_ID , CIUD_REGISTRADOPOR , CIUD_FECHACAMBIO ) VALUES ( ? , ? , ? , ? )";
        ps = con.prepareStatement(sql);
        ps.setString(1, ciudad.getNombre());
        ps.setInt(2, ciudad.getIdDepartamento());
        ps.setString(3, ciudad.getRegistradoPor());
        ps.setDate(4, java.sql.Date.valueOf(java.time.LocalDate.now()));
        ps.executeUpdate();
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase CiudadDAO ::> function public String insertar(Object dat) ::> SQLException ::> " + e.getMessage());
        ciudad.setError(e.getMessage());
        mensaje = e.getMessage();
        e.printStackTrace();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase CiudadDAO ::> function public String insertar(Object dat) ::> Exception ::> " + e.getMessage());
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
    Ciudad ciudad = new Ciudad();
    ArrayList< Ciudad > lista = new ArrayList<>();
    String sql = "";
    try{
        con = cnx.getConnection();
        sql = "SELECT C.CIUD_ID , C.CIUD_NOMBRE , C.PAIS_ID , C.CIUD_REGISTRADOPOR , C.CIUD_FECHACAMBIO , D.DEPA_NOMBRE FROM APPCONSULTAS.CIUDAD C, APPCONSULTAS.DEPARTAMENTO D WHERE C.DEPA_ID = D.DEPA_ID AND CIUD_ID = ? ORDER BY CIUD_ID";
        ps = con.prepareStatement(sql);
        ps.setString(1, llave);
        rs = ps.executeQuery();
        while (rs.next()) {
            ciudad.setIdCiudad(rs.getInt("CIUD_ID"));
            ciudad.setNombre(rs.getString("CIUD_NOMBRE"));
            ciudad.setIdDepartamento(rs.getInt("DEPA_ID"));
            ciudad.setRegistradoPor(rs.getString("CIUD_REGISTRADOPOR"));
            ciudad.setFechaCambio(rs.getString("CIUD_FECHACAMBIO"));
        }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase CiudadDAO ::> function public Object consultar(String llave) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase CiudadDAO ::> function public Object consultar(String llave) ::> Exception ::> " + e.getMessage());
        e.printStackTrace(); 
    }finally{
        /*Cerrar.PsRs(ps,rs);*/
    }
    return ciudad;
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
Ciudad ciudad = (Ciudad) dat;
    String mensaje = "";
    String sql =
    "UPDATE APPCONSULTAS.CIUDAD SET "
    +" CIUD_NOMBRE = ? , "
    +" DEPA_ID = ? , "
    +" CIUD_REGISTRADOPOR = ? , "
    +" CIUD_FECHACAMBIO = ? "
    +" WHERE ciud_id = ? ";
    try{
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, ciudad.getNombre());
        ps.setInt(2, ciudad.getIdDepartamento());
        ps.setString(3, ciudad.getRegistradoPor());
        ps.setDate(4, java.sql.Date.valueOf(java.time.LocalDate.now()));
        ps.setInt(5, ciudad.getIdCiudad());
        ps.executeUpdate();

    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase CiudadDAO ::> function public String actualizar(Object dat) ::> SQLException ::> " + e.getMessage());
        ciudad.setError(e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase CiudadDAO ::> function public String actualizar(Object dat) ::> Exception ::> " + e.getMessage());
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
    String sql = "DELETE FROM APPCONSULTAS.CIUDAD WHERE ciud_id = ? ";

    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();

    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase CiudadDAO ::> function public Object eliminar(String filtro) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
    }catch(Exception e){
        mensaje = e.getMessage();
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase CiudadDAO ::> function public Object eliminar(String filtro) ::> Exception ::> " + e.getMessage());
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
    String sql = "Select count(*) from appconsultas.ciudad where ciud_nombre like ? ";
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
* 10-OCTUBRE-2021 @: GRUPO DE DESARROLLO 3 &: creación
*/

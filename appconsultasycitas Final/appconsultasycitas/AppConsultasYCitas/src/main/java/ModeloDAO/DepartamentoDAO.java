/*
* DepartamentoDAO.java
*
* Creado el 10 de octubre de 2021, 08:08
*/

package ModeloDAO;

import Config.Conexion;
import Interfaces.CRUD;
import Modelo.Departamento;
import java.sql.*;
import java.util.*;
/**
*
* @autor GRUPO DE DESARROLLO 3 - MINTIC UIS
*/

public class DepartamentoDAO implements CRUD {

    /** Definición de la conexión */
    Conexion cnx = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Departamento departamento = new Departamento();
    String mensaje = "";

/* METODOS DE LA INTERFAZ CRUD */

/**
* Lista los elementos de la tabla
* @return List
*/
@Override
public List listar() {
    ArrayList< Departamento > lista = new ArrayList<>();
    String sql = "SELECT D.DEPA_ID , D.DEPA_NOMBRE , D.PAIS_ID , D.DEPA_REGISTRADOPOR , D.DEPA_FECHACAMBIO , P.PAIS_NOMBRE FROM APPCONSULTAS.DEPARTAMENTO D, APPCONSULTAS.PAIS P WHERE D.PAIS_ID = P.PAIS_ID ORDER BY DEPA_ID";
    try{
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            departamento = new Departamento();
            departamento.setIdDepartamento(rs.getInt("DEPA_ID"));
            departamento.setNombre(rs.getString("DEPA_NOMBRE"));
            departamento.setIdPais(rs.getInt("PAIS_ID"));
            departamento.setPais(rs.getString("PAIS_NOMBRE"));
            departamento.setRegistradoPor(rs.getString("DEPA_REGISTRADOPOR"));
            departamento.setFechaCambio(rs.getString("DEPA_FECHACAMBIO"));
            lista.add(departamento);
        }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase DepartamentoDAO ::> function public Object listar() ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase DepartamentoDAO ::> function public Object listar() ::> Exception ::> " + e.getMessage());
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

    ArrayList< Departamento > lista = new ArrayList<>();
    String sql = "SELECT * FROM (SELECT D.DEPA_ID , D.DEPA_NOMBRE , D.PAIS_ID , D.DEPA_REGISTRADOPOR , D.DEPA_FECHACAMBIO , P.PAIS_NOMBRE, ROW_NUMBER() OVER (ORDER BY DEPA_ID) AS CONT FROM APPCONSULTAS.DEPARTAMENTO D, APPCONSULTAS.PAIS P WHERE POSITION(? IN DEPA_NOMBRE)>0 AND D.PAIS_ID = P.PAIS_ID ORDER BY DEPA_ID) AS TABLA WHERE CONT>=? AND CONT<=? ORDER BY CONT";
    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, parametro);
        ps.setInt(2, desde);
        ps.setInt(3, hasta);
        rs = ps.executeQuery();

        while (rs.next()){

            Departamento departamento = new Departamento();
            departamento.setIdDepartamento(rs.getInt("DEPA_ID"));
            departamento.setNombre(rs.getString("DEPA_NOMBRE"));
            departamento.setIdPais(rs.getInt("PAIS_ID"));
            departamento.setPais(rs.getString("PAIS_NOMBRE"));
            departamento.setRegistradoPor(rs.getString("DEPA_REGISTRADOPOR"));
            departamento.setFechaCambio(rs.getString("DEPA_FECHACAMBIO"));
            lista.add(departamento);

        }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase DepartamentoDAO ::> function public Object listar(String filtro) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase DepartamentoDAO ::> function public Object listar(String filtro) ::> Exception ::> " + e.getMessage());
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
    String sql = "Select * from APPCONSULTAS.DEPARTAMENTO where campo_referencia like ? ";
    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, "%"+parametro+"%");
        rs = ps.executeQuery();
        while (rs.next()){
            departamento = new Departamento();
            departamento.setIdDepartamento(rs.getInt("DEPA_ID"));
            departamento.setNombre(rs.getString("DEPA_NOMBRE"));
            //departamento.setCampoReferencia(rs.getString("campo_referencia"));
            lista.add(departamento);
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

    ArrayList< Departamento > lista = new ArrayList<>();
    String sql = "SELECT * FROM APPCONSULTAS.DEPARTAMENTO WHERE pais_id = ? ";
    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        rs = ps.executeQuery();

        while (rs.isBeforeFirst()){

            departamento.setIdDepartamento(rs.getInt("DEPA_ID"));
            departamento.setNombre(rs.getString("DEPA_NOMBRE"));
            departamento.setIdPais(rs.getInt("PAIS_ID"));
            departamento.setRegistradoPor(rs.getString("DEPA_REGISTRADOPOR"));
            departamento.setFechaCambio(rs.getString("DEPA_FECHACAMBIO"));
            lista.add(departamento);

            }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase DepartamentoDAO ::> function public Object listar(String filtro) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase DepartamentoDAO ::> function public Object listar(String filtro) ::> Exception ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }finally{
        /*Cerrar.PsRs(ps,rs);*/
    }
    return departamento;

}

/**
* Inserta un nuevo elemento en el objeto
* @return String
*/
@Override

public String add(Object dat) {
Departamento departamento = (Departamento) dat;
    String mensaje = "";
    String sql = "";
    try{
        con = cnx.getConnection();
        sql = "INSERT INTO APPCONSULTAS.DEPARTAMENTO ( DEPA_NOMBRE , PAIS_ID , DEPA_REGISTRADOPOR , DEPA_FECHACAMBIO ) VALUES ( ? , ? , ? , ? )";
        ps = con.prepareStatement(sql);
        ps.setString(1, departamento.getNombre());
        ps.setInt(2, departamento.getIdPais());
        ps.setString(3, departamento.getRegistradoPor());
        ps.setDate(4, java.sql.Date.valueOf(java.time.LocalDate.now()));
        ps.executeUpdate();
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase DepartamentoDAO ::> function public String insertar(Object dat) ::> SQLException ::> " + e.getMessage());
        departamento.setError(e.getMessage());
        mensaje = e.getMessage();
        e.printStackTrace();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase DepartamentoDAO ::> function public String insertar(Object dat) ::> Exception ::> " + e.getMessage());
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
    Departamento departamento = new Departamento();
    ArrayList< Departamento > lista = new ArrayList<>();
    String sql = "";
    try{
        con = cnx.getConnection();
        sql = "SELECT D.DEPA_ID , D.DEPA_NOMBRE , D.PAIS_ID , D.DEPA_REGISTRADOPOR , D.DEPA_FECHACAMBIO , P.PAIS_NOMBRE FROM APPCONSULTAS.DEPARTAMENTO D, APPCONSULTAS.PAIS P WHERE D.PAIS_ID = P.PAIS_ID AND DEPA_ID = ? ORDER BY DEPA_ID ";
        ps = con.prepareStatement(sql);
        ps.setString(1, llave);
        rs = ps.executeQuery();
        while (rs.next()) {
            departamento.setIdDepartamento(rs.getInt("DEPA_ID"));
            departamento.setNombre(rs.getString("DEPA_NOMBRE"));
            departamento.setIdPais(rs.getInt("PAIS_ID"));
            departamento.setRegistradoPor(rs.getString("DEPA_REGISTRADOPOR"));
            departamento.setFechaCambio(rs.getString("DEPA_FECHACAMBIO"));
        }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase DepartamentoDAO ::> function public Object consultar(String llave) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase DepartamentoDAO ::> function public Object consultar(String llave) ::> Exception ::> " + e.getMessage());
        e.printStackTrace();
    }finally{
        /*Cerrar.PsRs(ps,rs);*/
    }
    return departamento;
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
Departamento departamento = (Departamento) dat;
    String mensaje = "";
    String sql =
    "UPDATE APPCONSULTAS.DEPARTAMENTO SET "
    +" DEPA_NOMBRE = ? , "
    +" PAIS_ID = ? , "
    +" DEPA_REGISTRADOPOR = ? , "
    +" DEPA_FECHACAMBIO = ? "
    +" WHERE depa_id = ? ";
    try{
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, departamento.getNombre());
        ps.setInt(2, departamento.getIdPais());
        ps.setString(3, departamento.getRegistradoPor());
        ps.setDate(4, java.sql.Date.valueOf(java.time.LocalDate.now()));
        ps.setInt(5, departamento.getIdDepartamento());
        ps.executeUpdate();

    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase DepartamentoDAO ::> function public String actualizar(Object dat) ::> SQLException ::> " + e.getMessage());
        departamento.setError(e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase DepartamentoDAO ::> function public String actualizar(Object dat) ::> Exception ::> " + e.getMessage());
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
    String sql = "DELETE FROM APPCONSULTAS.DEPARTAMENTO WHERE depa_id = ? ";

    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();

    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase DepartamentoDAO ::> function public Object eliminar(String filtro) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
    }catch(Exception e){
        mensaje = e.getMessage();
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase DepartamentoDAO ::> function public Object eliminar(String filtro) ::> Exception ::> " + e.getMessage());
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
    String sql = "Select count(*) from appconsultas.departamento where depa_nombre like ? ";
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
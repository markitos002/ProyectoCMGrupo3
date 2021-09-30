/*
* TipoCitaDAO.java
*
* Creado el 26 de septiembre de 2021, 21:30
*/

package ModeloDAO;

import Config.Conexion;
import Interfaces.CRUD;
import Modelo.TipoCita;
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;
/**
*
* @autor GRUPO DE DESARROLLO 3 - MINTIC UIS
*/

public class TipoCitaDAO implements CRUD {

    /** Definición de la conexión */
    Conexion cnx = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    TipoCita tipoCita = new TipoCita();
    String mensaje = "";

/* METODOS DE LA INTERFAZ CRUD */

/**
* Lista los elementos de la tabla
* @return List
*/
@Override
public List listar() {
    ArrayList< TipoCita > lista = new ArrayList<>();
    String sql = "SELECT * FROM APPCONSULTAS.TIPOCITA";
    try{
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            tipoCita = new TipoCita();
            tipoCita.setIdTipoCita(rs.getInt("TICI_ID"));
            tipoCita.setNombre(rs.getString("TICI_NOMBRE"));
            tipoCita.setRegistradoPor(rs.getString("TICI_REGISTRADOPOR"));
            tipoCita.setFechaCambio(rs.getString("TICI_FECHACAMBIO"));
            lista.add(tipoCita);
        }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase TipoCitaDAO ::> function public Object listar() ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase TipoCitaDAO ::> function public Object listar() ::> Exception ::> " + e.getMessage());
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

    ArrayList< TipoCita > lista = new ArrayList<>();
    String sql = "SELECT * FROM (SELECT TICI_ID , TICI_NOMBRE , TICI_REGISTRADOPOR , TICI_FECHACAMBIO , ROW_NUMBER() OVER (ORDER BY tici_id) AS CONT FROM APPCONSULTAS.TIPOCITA WHERE POSITION(? IN TICI_NOMBRE)>0 ORDER BY TICI_ID) AS TABLA WHERE CONT>=? AND CONT<=? ORDER BY CONT";
    
    try {       
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, parametro);
        ps.setInt(2, desde);
        ps.setInt(3, hasta);
        rs = ps.executeQuery();

        while (rs.next()){

            TipoCita tipoCita = new TipoCita();
            tipoCita.setIdTipoCita(rs.getInt("TICI_ID"));
            tipoCita.setNombre(rs.getString("TICI_NOMBRE"));
            tipoCita.setRegistradoPor(rs.getString("TICI_REGISTRADOPOR"));
            tipoCita.setFechaCambio(rs.getString("TICI_FECHACAMBIO"));
            lista.add(tipoCita);

        }
        
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase TipoCitaDAO ::> function public Object listar(String filtro) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase TipoCitaDAO ::> function public Object listar(String filtro) ::> Exception ::> " + e.getMessage());
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
    String sql = "Select * from APPCONSULTAS.TIPOCITA where campo_referencia like ? ";
    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, "%"+parametro+"%");
        rs = ps.executeQuery();
        while (rs.next()){
            tipoCita = new TipoCita();
            tipoCita.setIdTipoCita(rs.getInt("TICI_ID"));
            tipoCita.setNombre(rs.getString("TICI_NOMBRE"));
            //tipoCita.setCampoReferencia(rs.getString("campo_referencia"));
            lista.add(tipoCita);
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

    ArrayList< TipoCita > lista = new ArrayList<>();
    String sql = "SELECT * FROM APPCONSULTAS.TIPOCITA WHERE tici_id = ? ";
    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        rs = ps.executeQuery();

        while (rs.isBeforeFirst()){

            tipoCita.setIdTipoCita(rs.getInt("TICI_ID"));
            tipoCita.setNombre(rs.getString("TICI_NOMBRE"));
            tipoCita.setRegistradoPor(rs.getString("TICI_REGISTRADOPOR"));
            tipoCita.setFechaCambio(rs.getString("TICI_FECHACAMBIO"));
            lista.add(tipoCita);

            }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase TipoCitaDAO ::> function public Object listar(String filtro) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase TipoCitaDAO ::> function public Object listar(String filtro) ::> Exception ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }finally{
        /*Cerrar.PsRs(ps,rs);*/
    }
    return tipoCita;

}

/**
* Inserta un nuevo elemento en el objeto
* @return String
*/
@Override

public String add(Object dat) {
TipoCita tipoCita = (TipoCita) dat;
    String mensaje = "";
    String sql = "";
    try{
        con = cnx.getConnection();
        sql = "INSERT INTO APPCONSULTAS.TIPOCITA ( TICI_NOMBRE , TICI_REGISTRADOPOR , TICI_FECHACAMBIO ) VALUES ( ? , ? , ? )";
        ps = con.prepareStatement(sql);
        ps.setString(1, tipoCita.getNombre());
        ps.setString(2, tipoCita.getRegistradoPor());
        ps.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
        ps.executeUpdate();
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase TipoCitaDAO ::> function public String insertar(Object dat) ::> SQLException ::> " + e.getMessage());
        tipoCita.setError(e.getMessage());
        mensaje = e.getMessage();
        e.printStackTrace();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase TipoCitaDAO ::> function public String insertar(Object dat) ::> Exception ::> " + e.getMessage());
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
    TipoCita tipoCita = new TipoCita();
    ArrayList< TipoCita > lista = new ArrayList<>();
    String sql = "";
    try{
        con = cnx.getConnection();
        sql = "SELECT * FROM APPCONSULTAS.TIPOCITA WHERE tici_id = ? ";
        ps = con.prepareStatement(sql);
        ps.setString(1, llave);
        rs = ps.executeQuery();
        while (rs.next()) {
            tipoCita.setIdTipoCita(rs.getInt("TICI_ID"));
            tipoCita.setNombre(rs.getString("TICI_NOMBRE"));
            tipoCita.setRegistradoPor(rs.getString("TICI_REGISTRADOPOR"));
            tipoCita.setFechaCambio(rs.getString("TICI_FECHACAMBIO"));
        }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase TipoCitaDAO ::> function public Object consultar(String llave) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase TipoCitaDAO ::> function public Object consultar(String llave) ::> Exception ::> " + e.getMessage());
        e.printStackTrace();
    }finally{
        /*Cerrar.PsRs(ps,rs);*/
    }
    return tipoCita;
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
TipoCita tipoCita = (TipoCita) dat;
    String mensaje = "";
    String sql =
    "UPDATE APPCONSULTAS.TIPOCITA SET "
    +" TICI_NOMBRE = ? , "
    +" TICI_REGISTRADOPOR = ? , "
    +" TICI_FECHACAMBIO = ? "
    +" WHERE tici_id = ? ";
    try{
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, tipoCita.getNombre());
        ps.setString(2, tipoCita.getRegistradoPor());
        ps.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
        ps.setInt(4, tipoCita.getIdTipoCita());
        ps.executeUpdate();

    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase TipoCitaDAO ::> function public String actualizar(Object dat) ::> SQLException ::> " + e.getMessage());
        tipoCita.setError(e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase TipoCitaDAO ::> function public String actualizar(Object dat) ::> Exception ::> " + e.getMessage());
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
    String sql = "DELETE FROM APPCONSULTAS.TIPOCITA WHERE tici_id = ? ";

    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();

    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase TipoCitaDAO ::> function public Object eliminar(String filtro) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
    }catch(Exception e){
        mensaje = e.getMessage();
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase TipoCitaDAO ::> function public Object eliminar(String filtro) ::> Exception ::> " + e.getMessage());
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
* 26-SEPTIEMBRE-2021 @: GRUPO DE DESARROLLO 3 &: creación
*/ 
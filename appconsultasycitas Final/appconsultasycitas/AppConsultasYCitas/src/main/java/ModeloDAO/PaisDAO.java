/*
* PaisDAO.java
*
* Creado el 09 de octubre de 2021, 09:29
*/

package ModeloDAO;

import Config.Conexion;
import Interfaces.CRUD;
import Modelo.Pais;
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;
/**
*
* @autor GRUPO DE DESARROLLO 3 - MINTIC UIS
*/

public class PaisDAO implements CRUD {

    /** Definición de la conexión */
    Conexion cnx = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Pais pais = new Pais();
    String mensaje = "";

/* METODOS DE LA INTERFAZ CRUD */

/**
* Lista los elementos de la tabla
* @return List
*/
@Override
public List listar() {
    ArrayList< Pais > lista = new ArrayList<>();
    String sql = "SELECT * FROM APPCONSULTAS.PAIS";
    try{
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            pais = new Pais();
            pais.setIdPais(rs.getInt("PAIS_ID"));
            pais.setNombre(rs.getString("PAIS_NOMBRE"));
            pais.setRegistradoPor(rs.getString("PAIS_REGISTRADOPOR"));
            pais.setFechaCambio(rs.getString("PAIS_FECHACAMBIO"));
            lista.add(pais);
        }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase PaisDAO ::> function public Object listar() ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase PaisDAO ::> function public Object listar() ::> Exception ::> " + e.getMessage());
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

    ArrayList< Pais > lista = new ArrayList<>();
    String sql = "SELECT * FROM (SELECT PAIS_ID , PAIS_NOMBRE , PAIS_REGISTRADOPOR , PAIS_FECHACAMBIO , ROW_NUMBER() OVER (ORDER BY PAIS_ID) AS CONT FROM APPCONSULTAS.PAIS WHERE POSITION(? IN PAIS_NOMBRE)>0 ORDER BY PAIS_ID) AS TABLA WHERE CONT>=? AND CONT<=? ORDER BY CONT";
    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, pagina);
        ps.setInt(2, desde);
        ps.setInt(3, hasta);
        rs = ps.executeQuery();

        while (rs.next()){

            Pais pais = new Pais();
            pais.setIdPais(rs.getInt("PAIS_ID"));
            pais.setNombre(rs.getString("PAIS_NOMBRE"));
            pais.setRegistradoPor(rs.getString("PAIS_REGISTRADOPOR"));
            pais.setFechaCambio(rs.getString("PAIS_FECHACAMBIO"));
            lista.add(pais);

        }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase PaisDAO ::> function public Object listar(String filtro) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase PaisDAO ::> function public Object listar(String filtro) ::> Exception ::> " + e.getMessage());
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
    String sql = "Select * from APPCONSULTAS.PAIS where campo_referencia like ? ";
    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, "%"+parametro+"%");
        rs = ps.executeQuery();
        while (rs.next()){
            pais = new Pais();
            pais.setIdPais(rs.getInt("PAIS_ID"));
            pais.setNombre(rs.getString("PAIS_NOMBRE"));
            //pais.setCampoReferencia(rs.getString("campo_referencia"));
            lista.add(pais);
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

    ArrayList< Pais > lista = new ArrayList<>();
    String sql = "SELECT * FROM APPCONSULTAS.PAIS WHERE pais_id = ? ";
    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        rs = ps.executeQuery();

        while (rs.isBeforeFirst()){

            pais.setIdPais(rs.getInt("PAIS_ID"));
            pais.setNombre(rs.getString("PAIS_NOMBRE"));
            pais.setRegistradoPor(rs.getString("PAIS_REGISTRADOPOR"));
            pais.setFechaCambio(rs.getString("PAIS_FECHACAMBIO"));
            lista.add(pais);

            }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase PaisDAO ::> function public Object listar(String filtro) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase PaisDAO ::> function public Object listar(String filtro) ::> Exception ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }finally{
        /*Cerrar.PsRs(ps,rs);*/
    }
    return pais;

}

/**
* Inserta un nuevo elemento en el objeto
* @return String
*/
@Override

public String add(Object dat) {
Pais pais = (Pais) dat;
    String mensaje = "";
    String sql = "";
    try{
        con = cnx.getConnection();
        sql = "INSERT INTO APPCONSULTAS.PAIS ( PAIS_NOMBRE , PAIS_REGISTRADOPOR , PAIS_FECHACAMBIO ) VALUES ( ? , ? , ? )";
        ps = con.prepareStatement(sql);
        ps.setString(1, pais.getNombre());
        ps.setString(2, pais.getRegistradoPor());
        ps.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
        ps.executeUpdate();
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase PaisDAO ::> function public String insertar(Object dat) ::> SQLException ::> " + e.getMessage());
        pais.setError(e.getMessage());
        mensaje = e.getMessage();
        e.printStackTrace();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase PaisDAO ::> function public String insertar(Object dat) ::> Exception ::> " + e.getMessage());
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
    Pais pais = new Pais();
    ArrayList< Pais > lista = new ArrayList<>();
    String sql = "";
    try{
        con = cnx.getConnection();
        sql = "SELECT * FROM APPCONSULTAS.PAIS WHERE pais_id = ? ";
        ps = con.prepareStatement(sql);
        ps.setString(1, llave);
        rs = ps.executeQuery();
        while (rs.next()) {
            pais.setIdPais(rs.getInt("PAIS_ID"));
            pais.setNombre(rs.getString("PAIS_NOMBRE"));
            pais.setRegistradoPor(rs.getString("PAIS_REGISTRADOPOR"));
            pais.setFechaCambio(rs.getString("PAIS_FECHACAMBIO"));
        }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase PaisDAO ::> function public Object consultar(String llave) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase PaisDAO ::> function public Object consultar(String llave) ::> Exception ::> " + e.getMessage());
        e.printStackTrace();
    }finally{
        /*Cerrar.PsRs(ps,rs);*/
    }
    return pais;
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
Pais pais = (Pais) dat;
    String mensaje = "";
    String sql =
    "UPDATE APPCONSULTAS.PAIS SET PAIS_NOMBRE = ? , PAIS_REGISTRADOPOR = ? , PAIS_FECHACAMBIO = ? WHERE pais_id = ? ";
    try{
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, pais.getNombre());
        ps.setString(2, pais.getRegistradoPor());
        ps.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
        ps.setInt(4, pais.getIdPais());
        ps.executeUpdate();

    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase PaisDAO ::> function public String actualizar(Object dat) ::> SQLException ::> " + e.getMessage());
        pais.setError(e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase PaisDAO ::> function public String actualizar(Object dat) ::> Exception ::> " + e.getMessage());
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
    String sql = "DELETE FROM APPCONSULTAS.PAIS WHERE pais_id = ? ";

    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();

    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase PaisDAO ::> function public Object eliminar(String filtro) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
    }catch(Exception e){
        mensaje = e.getMessage();
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase PaisDAO ::> function public Object eliminar(String filtro) ::> Exception ::> " + e.getMessage());
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
    String sql = "Select count(*) from appconsultas.pais where pais_nombre like ? ";
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
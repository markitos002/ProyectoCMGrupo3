/*
* ConsultorioDAO.java
*
* Creado el 29 de septiembre de 2021, 07:01
*/

package ModeloDAO;

import Config.Conexion;
import Interfaces.CRUD;
import Modelo.Consultorio;
import java.sql.*;
import java.util.*;
/**
*
* @autor GRUPO DE DESARROLLO 3 - MINTIC UIS
*/

public class ConsultorioDAO implements CRUD {

    /** Definición de la conexión */
    Conexion cnx = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Consultorio consultorio = new Consultorio();
    String mensaje = "";

/* METODOS DE LA INTERFAZ CRUD */

/**
* Lista los elementos de la tabla
* @return List
*/
@Override
public List listar() {
    ArrayList< Consultorio > lista = new ArrayList<>();
    String sql = "SELECT CO.*, CI.CIUD_NOMBRE FROM APPCONSULTAS.CONSULTORIO CO, APPCONSULTAS.CIUDAD CI WHERE CO.CIUD_ID = CI.CIUD_ID";
    try{
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            consultorio = new Consultorio();
            consultorio.setIdConsultorio(rs.getInt("CONS_ID"));
            consultorio.setNombre(rs.getString("CONS_NOMBRE"));
            consultorio.setDireccion(rs.getString("CONS_DIRECCION"));
            consultorio.setTelefono(rs.getString("CONS_TELEFONO"));
            consultorio.setCiudad(rs.getString("CIUD_NOMBRE"));
            consultorio.setRegistradopor(rs.getString("CONS_REGISTRADOPOR"));
            consultorio.setFechacambio(rs.getTimestamp("CONS_FECHACAMBIO"));
            consultorio.setIdCiudad(rs.getInt("CIUD_ID"));
            lista.add(consultorio);
        }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase ConsultorioDAO ::> function public Object listar() ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase ConsultorioDAO ::> function public Object listar() ::> Exception ::> " + e.getMessage());
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

    ArrayList< Consultorio > lista = new ArrayList<>();
    String sql = "SELECT * FROM (SELECT CONS_ID , CONS_NOMBRE , CONS_DIRECCION , CONS_TELEFONO , CONS_REGISTRADOPOR , CONS_FECHACAMBIO , CIUD_ID , ROW_NUMBER() OVER (ORDER BY cons_id) AS CONT FROM APPCONSULTAS.CONSULTORIO WHERE POSITION(? IN MOCO_DESCRIPCION)>0 ORDER BY MOCO_ID) AS TABLA WHERE CONT>=? AND CONT<=? ORDER BY CONT";
    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, pagina);
        ps.setInt(2, desde);
        ps.setInt(3, hasta);
        rs = ps.executeQuery();

        while (rs.next()){

            Consultorio consultorio = new Consultorio();
            consultorio.setIdConsultorio(rs.getInt("CONS_ID"));
            consultorio.setNombre(rs.getString("CONS_NOMBRE"));
            consultorio.setDireccion(rs.getString("CONS_DIRECCION"));
            consultorio.setTelefono(rs.getString("CONS_TELEFONO"));
            consultorio.setRegistradopor(rs.getString("CONS_REGISTRADOPOR"));
            consultorio.setFechacambio(rs.getTimestamp("CONS_FECHACAMBIO"));
            consultorio.setIdCiudad(rs.getInt("CIUD_ID"));
            lista.add(consultorio);

        }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase ConsultorioDAO ::> function public Object listar(String filtro) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase ConsultorioDAO ::> function public Object listar(String filtro) ::> Exception ::> " + e.getMessage());
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
    String sql = "Select * from APPCONSULTAS.CONSULTORIO where campo_referencia like ? ";
    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, "%"+parametro+"%");
        rs = ps.executeQuery();
        while (rs.next()){
            consultorio = new Consultorio();
            consultorio.setIdConsultorio(rs.getInt("CONS_ID"));
            consultorio.setNombre(rs.getString("CONS_NOMBRE"));
            //consultorio.setCampoReferencia(rs.getString("campo_referencia"));
            lista.add(consultorio);
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

    ArrayList< Consultorio > lista = new ArrayList<>();
    String sql = "SELECT * FROM APPCONSULTAS.CONSULTORIO WHERE ciud_id = ? ";
    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        rs = ps.executeQuery();

        while (rs.isBeforeFirst()){

            consultorio.setIdConsultorio(rs.getInt("CONS_ID"));
            consultorio.setNombre(rs.getString("CONS_NOMBRE"));
            consultorio.setDireccion(rs.getString("CONS_DIRECCION"));
            consultorio.setTelefono(rs.getString("CONS_TELEFONO"));
            consultorio.setRegistradopor(rs.getString("CONS_REGISTRADOPOR"));
            consultorio.setFechacambio(rs.getTimestamp("CONS_FECHACAMBIO"));
            consultorio.setIdCiudad(rs.getInt("CIUD_ID"));
            lista.add(consultorio);

            }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase ConsultorioDAO ::> function public Object listar(String filtro) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase ConsultorioDAO ::> function public Object listar(String filtro) ::> Exception ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }finally{
        /*Cerrar.PsRs(ps,rs);*/
    }
    return consultorio;

}

/**
* Inserta un nuevo elemento en el objeto
* @return String
*/
@Override

public String add(Object dat) {
Consultorio consultorio = (Consultorio) dat;
    String mensaje = "";
    String sql = "";
    try{
        con = cnx.getConnection();
        sql = "INSERT INTO APPCONSULTAS.CONSULTORIO ( CONS_NOMBRE , CONS_DIRECCION , CONS_TELEFONO , CONS_REGISTRADOPOR , CONS_FECHACAMBIO , CIUD_ID ) VALUES ( ? , ? , ? , ? , ? , ? )";
        ps.setString(1, consultorio.getNombre());
        ps.setString(2, consultorio.getDireccion());
        ps.setString(3, consultorio.getTelefono());
        ps.setString(4, consultorio.getRegistradopor());
        ps.setDate(5, java.sql.Date.valueOf(java.time.LocalDate.now()));
        ps.setInt(6, consultorio.getIdCiudad());
        ps.executeUpdate();
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase ConsultorioDAO ::> function public String insertar(Object dat) ::> SQLException ::> " + e.getMessage());
        consultorio.setError(e.getMessage());
        mensaje = e.getMessage();
        e.printStackTrace();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase ConsultorioDAO ::> function public String insertar(Object dat) ::> Exception ::> " + e.getMessage());
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
    Consultorio consultorio = new Consultorio();
    ArrayList< Consultorio > lista = new ArrayList<>();
    String sql = "";
    try{
        con = cnx.getConnection();
        sql = "SELECT * FROM APPCONSULTAS.CONSULTORIO WHERE cons_id = ? ";
        ps = con.prepareStatement(sql);
        ps.setString(1, llave);
        rs = ps.executeQuery();
        while (rs.next()) {
            consultorio.setIdConsultorio(rs.getInt("CONS_ID"));
            consultorio.setNombre(rs.getString("CONS_NOMBRE"));
            consultorio.setDireccion(rs.getString("CONS_DIRECCION"));
            consultorio.setTelefono(rs.getString("CONS_TELEFONO"));
            consultorio.setRegistradopor(rs.getString("CONS_REGISTRADOPOR"));
            consultorio.setFechacambio(rs.getTimestamp("CONS_FECHACAMBIO"));
            consultorio.setIdCiudad(rs.getInt("CIUD_ID"));
        }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase ConsultorioDAO ::> function public Object consultar(String llave) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase ConsultorioDAO ::> function public Object consultar(String llave) ::> Exception ::> " + e.getMessage());
        e.printStackTrace();
    }finally{
        /*Cerrar.PsRs(ps,rs);*/
    }
    return consultorio;
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
Consultorio consultorio = (Consultorio) dat;
    String mensaje = "";
    String sql =
    "UPDATE APPCONSULTAS.CONSULTORIO SET "
    +" CONS_NOMBRE = ? , "
    +" CONS_DIRECCION = ? , "
    +" CONS_TELEFONO = ? , "
    +" CONS_REGISTRADOPOR = ? , "
    +" CONS_FECHACAMBIO = ? , "
    +" CIUD_ID = ? "
    +" WHERE cons_id = ? ";
    try{
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, consultorio.getNombre());
        ps.setString(2, consultorio.getDireccion());
        ps.setString(3, consultorio.getTelefono());
        ps.setString(4, consultorio.getRegistradopor());
        ps.setDate(5, java.sql.Date.valueOf(java.time.LocalDate.now()));
        ps.setInt(6, consultorio.getIdCiudad());
        ps.setInt(7, consultorio.getIdConsultorio());
        ps.executeUpdate();

    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase ConsultorioDAO ::> function public String actualizar(Object dat) ::> SQLException ::> " + e.getMessage());
        consultorio.setError(e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase ConsultorioDAO ::> function public String actualizar(Object dat) ::> Exception ::> " + e.getMessage());
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
    String sql = "DELETE FROM APPCONSULTAS.CONSULTORIO WHERE cons_id = ? ";

    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();

    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase ConsultorioDAO ::> function public Object eliminar(String filtro) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
    }catch(Exception e){
        mensaje = e.getMessage();
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase ConsultorioDAO ::> function public Object eliminar(String filtro) ::> Exception ::> " + e.getMessage());
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
* 29-SEPTIEMBRE-2021 @: GRUPO DE DESARROLLO 3 &: creación
*/ 
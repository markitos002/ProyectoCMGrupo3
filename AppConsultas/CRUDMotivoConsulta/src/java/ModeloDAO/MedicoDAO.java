/*
* MedicoDAO.java
*
* Creado el 28 de septiembre de 2021, 23:10
*/

package ModeloDAO;

import Config.Conexion;
import Interfaces.CRUD;
import Modelo.Medico;
import java.sql.*;
import java.util.*;
/**
*
* @autor GRUPO DE DESARROLLO 3 - MINTIC UIS
*/

public class MedicoDAO implements CRUD {

    /** Definición de la conexión */
    Conexion cnx = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Medico medico = new Medico();
    String mensaje = "";

/* METODOS DE LA INTERFAZ CRUD */

/**
* Lista los elementos de la tabla
* @return List
*/
@Override
public List listar() {
    ArrayList< Medico > lista = new ArrayList<>();
    String sql = "SELECT ME.*, CONCAT(PE.PERS_PRIMERAPELLIDO, ' ', PE.PERS_SEGUNDOAPELLIDO, ' ', PE.PERS_PRIMERNOMBRE, ' ', PE.PERS_SEGUNDONOMBRE ) NOMBRE "
               + "FROM APPCONSULTAS.MEDICO ME, APPCONSULTAS.PERSONA PE "
               + "WHERE ME.PERS_ID = PE.PERS_ID";
    try{
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            medico = new Medico();
            medico.setIdPers(rs.getInt("PERS_ID"));
            medico.setNombre(rs.getString("NOMBRE"));
            medico.setNroRegistro(rs.getString("MEDI_NROREGISTRO"));
            medico.setActivo(rs.getString("MEDI_ACTIVO"));
            medico.setRegistradoPor(rs.getString("MEDI_REGISTRADOPOR"));
            medico.setFechaCambio(rs.getTimestamp("MEDI_FECHACAMBIO"));
            lista.add(medico);
        }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase MedicoDAO ::> function public Object listar() ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase MedicoDAO ::> function public Object listar() ::> Exception ::> " + e.getMessage());
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

    ArrayList< Medico > lista = new ArrayList<>();
    String sql = "SELECT * FROM (SELECT PERS_ID , MEDI_NROREGISTRO , MEDI_ACTIVO , MEDI_REGISTRADOPOR , MEDI_FECHACAMBIO , ROW_NUMBER() OVER (ORDER BY pers_id) AS CONT FROM APPCONSULTAS.MEDICO WHERE POSITION(? IN MOCO_DESCRIPCION)>0 ORDER BY MOCO_ID) AS TABLA WHERE CONT>=? AND CONT<=? ORDER BY CONT";
    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, pagina);
        ps.setInt(2, desde);
        ps.setInt(3, hasta);
        rs = ps.executeQuery();

        while (rs.next()){

            Medico medico = new Medico();
            medico.setIdPers(rs.getInt("PERS_ID"));
            medico.setNroRegistro(rs.getString("MEDI_NROREGISTRO"));
            medico.setActivo(rs.getString("MEDI_ACTIVO"));
            medico.setRegistradoPor(rs.getString("MEDI_REGISTRADOPOR"));
            medico.setFechaCambio(rs.getTimestamp("MEDI_FECHACAMBIO"));
            lista.add(medico);

        }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase MedicoDAO ::> function public Object listar(String filtro) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase MedicoDAO ::> function public Object listar(String filtro) ::> Exception ::> " + e.getMessage());
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
    String sql = "Select * from APPCONSULTAS.MEDICO where campo_referencia like ? ";
    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, "%"+parametro+"%");
        rs = ps.executeQuery();
        while (rs.next()){
            medico = new Medico();
            medico.setIdPers(rs.getInt("PERS_ID"));
            medico.setNroRegistro(rs.getString("MEDI_NROREGISTRO"));
            //medico.setCampoReferencia(rs.getString("campo_referencia"));
            lista.add(medico);
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

    ArrayList< Medico > lista = new ArrayList<>();
    String sql = "SELECT * FROM APPCONSULTAS.MEDICO WHERE pers_id = ? ";
    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        rs = ps.executeQuery();

        while (rs.isBeforeFirst()){

            medico.setIdPers(rs.getInt("PERS_ID"));
            medico.setNroRegistro(rs.getString("MEDI_NROREGISTRO"));
            medico.setActivo(rs.getString("MEDI_ACTIVO"));
            medico.setRegistradoPor(rs.getString("MEDI_REGISTRADOPOR"));
            medico.setFechaCambio(rs.getTimestamp("MEDI_FECHACAMBIO"));
            lista.add(medico);

            }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase MedicoDAO ::> function public Object listar(String filtro) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase MedicoDAO ::> function public Object listar(String filtro) ::> Exception ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }finally{
        /*Cerrar.PsRs(ps,rs);*/
    }
    return medico;

}

/**
* Inserta un nuevo elemento en el objeto
* @return String
*/
@Override

public String add(Object dat) {
Medico medico = (Medico) dat;
    String mensaje = "";
    String sql = "";
    try{
        con = cnx.getConnection();
        sql = "INSERT INTO APPCONSULTAS.MEDICO ( MEDI_NROREGISTRO , MEDI_ACTIVO , MEDI_REGISTRADOPOR , MEDI_FECHACAMBIO ) VALUES ( ? , ? , ? , ? )";
        ps.setString(1, medico.getNroRegistro());
        ps.setString(2, medico.getActivo());
        ps.setString(3, medico.getRegistradoPor());
        ps.setDate(4, java.sql.Date.valueOf(java.time.LocalDate.now()));
        ps.executeUpdate();
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase MedicoDAO ::> function public String insertar(Object dat) ::> SQLException ::> " + e.getMessage());
        medico.setError(e.getMessage());
        mensaje = e.getMessage();
        e.printStackTrace();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase MedicoDAO ::> function public String insertar(Object dat) ::> Exception ::> " + e.getMessage());
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

    Medico medico = new Medico();
    ArrayList< Medico > lista = new ArrayList<>();
    String sql = "";
    try{
        con = cnx.getConnection();
        sql = "SELECT ME.*, CONCAT(PE.PERS_PRIMERAPELLIDO, ' ', PE.PERS_SEGUNDOAPELLIDO, ' ', PE.PERS_PRIMERNOMBRE, ' ', PE.PERS_SEGUNDONOMBRE ) NOMBRE "
                + " FROM APPCONSULTAS.MEDICO ME, APPCONSULTAS.PERSONA PE "
                + " WHERE ME.PERS_ID = PE.PERS_ID AND PA.PERS_ID = ? ";
        ps = con.prepareStatement(sql);
        ps.setString(1, llave);
        rs = ps.executeQuery();
        while (rs.next()) {
            medico.setIdPers(rs.getInt("PERS_ID"));
            medico.setNombre(rs.getString("NOMBRE"));
            medico.setNroRegistro(rs.getString("MEDI_NROREGISTRO"));
            medico.setActivo(rs.getString("MEDI_ACTIVO"));
            medico.setRegistradoPor(rs.getString("MEDI_REGISTRADOPOR"));
            medico.setFechaCambio(rs.getTimestamp("MEDI_FECHACAMBIO"));
        }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase MedicoDAO ::> function public Object consultar(String llave) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase MedicoDAO ::> function public Object consultar(String llave) ::> Exception ::> " + e.getMessage());
        e.printStackTrace();
    }finally{
        /*Cerrar.PsRs(ps,rs);*/
    }
    return medico;
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
Medico medico = (Medico) dat;
    String mensaje = "";
    String sql =
    "UPDATE APPCONSULTAS.MEDICO SET "
    +" MEDI_NROREGISTRO = ? , "
    +" MEDI_ACTIVO = ? , "
    +" MEDI_REGISTRADOPOR = ? , "
    +" MEDI_FECHACAMBIO = ? "
    +" WHERE pers_id = ? ";
    try{
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, medico.getNroRegistro());
        ps.setString(2, medico.getActivo());
        ps.setString(3, medico.getRegistradoPor());
        ps.setDate(4, java.sql.Date.valueOf(java.time.LocalDate.now()));
        ps.setInt(5, medico.getIdPers());
        ps.executeUpdate();

    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase MedicoDAO ::> function public String actualizar(Object dat) ::> SQLException ::> " + e.getMessage());
        medico.setError(e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase MedicoDAO ::> function public String actualizar(Object dat) ::> Exception ::> " + e.getMessage());
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
    String sql = "DELETE FROM APPCONSULTAS.MEDICO WHERE pers_id = ? ";

    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();

    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase MedicoDAO ::> function public Object eliminar(String filtro) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
    }catch(Exception e){
        mensaje = e.getMessage();
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase MedicoDAO ::> function public Object eliminar(String filtro) ::> Exception ::> " + e.getMessage());
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
* 28-SEPTIEMBRE-2021 @: GRUPO DE DESARROLLO 3 &: creación
*/ 
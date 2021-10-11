/*
* EspecialidadesMedicasDAO.java
*
* Creado el 04 de octubre de 2021, 05:48
*/

package ModeloDAO;

import Config.Conexion;
import Interfaces.CRUD;
import Modelo.EspecialidadesMedicas;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
*
* @autor GRUPO DE DESARROLLO 3 - MINTIC UIS
*/

public class EspecialidadesMedicasDAO implements CRUD {

    /** Definición de la conexión */
    Conexion cnx = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    EspecialidadesMedicas especialidadesMedicas = new EspecialidadesMedicas();
    String mensaje = "";

/* METODOS DE LA INTERFAZ CRUD */

/**
* Lista los elementos de la tabla
* @return List
*/
@Override
public List listar() {
    ArrayList< EspecialidadesMedicas > lista = new ArrayList<>();
    String sql = "SELECT * FROM APPCONSULTAS.ESPECIALIDADESMEDICAS";
    try{
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            especialidadesMedicas = new EspecialidadesMedicas();
            especialidadesMedicas.setIdEspecialidadesMedicas(rs.getInt("ESME_ID"));
            especialidadesMedicas.setNombre(rs.getString("ESME_NOMBRE"));
            especialidadesMedicas.setRegistradoPor(rs.getString("ESME_REGISTRADOPOR"));
            especialidadesMedicas.setFechaCambio(rs.getTimestamp("ESME_FECHACAMBIO"));
            lista.add(especialidadesMedicas);
        }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase EspecialidadesMedicasDAO ::> function public Object listar() ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase EspecialidadesMedicasDAO ::> function public Object listar() ::> Exception ::> " + e.getMessage());
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

    ArrayList< EspecialidadesMedicas > lista = new ArrayList<>();
    String sql = "SELECT * FROM (SELECT ESME_ID , ESME_NOMBRE , ESME_REGISTRADOPOR , ESME_FECHACAMBIO , ROW_NUMBER() OVER (ORDER BY esme_id) AS CONT FROM APPCONSULTAS.ESPECIALIDADESMEDICAS WHERE POSITION(? IN MOCO_DESCRIPCION)>0 ORDER BY MOCO_ID) AS TABLA WHERE CONT>=? AND CONT<=? ORDER BY CONT";
    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, pagina);
        ps.setInt(2, desde);
        ps.setInt(3, hasta);
        rs = ps.executeQuery();

        while (rs.next()){

            EspecialidadesMedicas especialidadesMedicas = new EspecialidadesMedicas();
            especialidadesMedicas.setIdEspecialidadesMedicas(rs.getInt("ESME_ID"));
            especialidadesMedicas.setNombre(rs.getString("ESME_NOMBRE"));
            especialidadesMedicas.setRegistradoPor(rs.getString("ESME_REGISTRADOPOR"));
            especialidadesMedicas.setFechaCambio(rs.getTimestamp("ESME_FECHACAMBIO"));
            lista.add(especialidadesMedicas);

        }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase EspecialidadesMedicasDAO ::> function public Object listar(String filtro) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase EspecialidadesMedicasDAO ::> function public Object listar(String filtro) ::> Exception ::> " + e.getMessage());
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
    String sql = "Select * from APPCONSULTAS.ESPECIALIDADESMEDICAS where campo_referencia like ? ";
    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, "%"+parametro+"%");
        rs = ps.executeQuery();
        while (rs.next()){
            especialidadesMedicas = new EspecialidadesMedicas();
            especialidadesMedicas.setIdEspecialidadesMedicas(rs.getInt("ESME_ID"));
            especialidadesMedicas.setNombre(rs.getString("ESME_NOMBRE"));
            //especialidadesMedicas.setCampoReferencia(rs.getString("campo_referencia"));
            lista.add(especialidadesMedicas);
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

    ArrayList< EspecialidadesMedicas > lista = new ArrayList<>();
    String sql = "SELECT * FROM APPCONSULTAS.ESPECIALIDADESMEDICAS WHERE esme_id = ? ";
    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        rs = ps.executeQuery();

        while (rs.isBeforeFirst()){

            especialidadesMedicas.setIdEspecialidadesMedicas(rs.getInt("ESME_ID"));
            especialidadesMedicas.setNombre(rs.getString("ESME_NOMBRE"));
            especialidadesMedicas.setRegistradoPor(rs.getString("ESME_REGISTRADOPOR"));
            especialidadesMedicas.setFechaCambio(rs.getTimestamp("ESME_FECHACAMBIO"));
            lista.add(especialidadesMedicas);

            }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase EspecialidadesMedicasDAO ::> function public Object listar(String filtro) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase EspecialidadesMedicasDAO ::> function public Object listar(String filtro) ::> Exception ::> " + e.getMessage());
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
    return especialidadesMedicas;

}

/**
* Inserta un nuevo elemento en el objeto
* @return String
*/
@Override

public String add(Object dat) {
EspecialidadesMedicas especialidadesMedicas = (EspecialidadesMedicas) dat;
    String mensaje = "";
    String sql = "";
    try{
        con = cnx.getConnection();
        sql = "INSERT INTO APPCONSULTAS.ESPECIALIDADESMEDICAS ( ESME_NOMBRE , ESME_REGISTRADOPOR , ESME_FECHACAMBIO ) VALUES ( ? , ? , ? )";
        ps.setString(1, especialidadesMedicas.getNombre());
        ps.setString(2, especialidadesMedicas.getRegistradoPor());
        ps.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
        ps.executeUpdate();
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase EspecialidadesMedicasDAO ::> function public String insertar(Object dat) ::> SQLException ::> " + e.getMessage());
        especialidadesMedicas.setError(e.getMessage());
        mensaje = e.getMessage();
        e.printStackTrace();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase EspecialidadesMedicasDAO ::> function public String insertar(Object dat) ::> Exception ::> " + e.getMessage());
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
    EspecialidadesMedicas especialidadesMedicas = new EspecialidadesMedicas();
    ArrayList< EspecialidadesMedicas > lista = new ArrayList<>();
    String sql = "";
    try{
        con = cnx.getConnection();
        sql = "SELECT * FROM APPCONSULTAS.ESPECIALIDADESMEDICAS WHERE esme_id = ? ";
        ps = con.prepareStatement(sql);
        ps.setString(1, llave);
        rs = ps.executeQuery();
        while (rs.next()) {
            especialidadesMedicas.setIdEspecialidadesMedicas(rs.getInt("ESME_ID"));
            especialidadesMedicas.setNombre(rs.getString("ESME_NOMBRE"));
            especialidadesMedicas.setRegistradoPor(rs.getString("ESME_REGISTRADOPOR"));
            especialidadesMedicas.setFechaCambio(rs.getTimestamp("ESME_FECHACAMBIO"));
        }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase EspecialidadesMedicasDAO ::> function public Object consultar(String llave) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase EspecialidadesMedicasDAO ::> function public Object consultar(String llave) ::> Exception ::> " + e.getMessage());
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
    return especialidadesMedicas;
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
EspecialidadesMedicas especialidadesMedicas = (EspecialidadesMedicas) dat;
    String mensaje = "";
    String sql =
    "UPDATE APPCONSULTAS.ESPECIALIDADESMEDICAS SET "
    +" ESME_NOMBRE = ? , "
    +" ESME_REGISTRADOPOR = ? , "
    +" ESME_FECHACAMBIO = ? "
    +" WHERE esme_id = ? ";
    try{
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, especialidadesMedicas.getNombre());
        ps.setString(2, especialidadesMedicas.getRegistradoPor());
        ps.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
        ps.setInt(4, especialidadesMedicas.getIdEspecialidadesMedicas());
        ps.executeUpdate();

    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase EspecialidadesMedicasDAO ::> function public String actualizar(Object dat) ::> SQLException ::> " + e.getMessage());
        especialidadesMedicas.setError(e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase EspecialidadesMedicasDAO ::> function public String actualizar(Object dat) ::> Exception ::> " + e.getMessage());
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
    String sql = "DELETE FROM APPCONSULTAS.ESPECIALIDADESMEDICAS WHERE esme_id = ? ";

    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();

    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase EspecialidadesMedicasDAO ::> function public Object eliminar(String filtro) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
    }catch(Exception e){
        mensaje = e.getMessage();
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase EspecialidadesMedicasDAO ::> function public Object eliminar(String filtro) ::> Exception ::> " + e.getMessage());
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
* 04-OCTUBRE-2021 @: GRUPO DE DESARROLLO 3 &: creación
*/ 
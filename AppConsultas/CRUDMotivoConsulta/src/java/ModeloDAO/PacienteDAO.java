
/*
* PacienteDAO.java
*
* Creado el 27 de septiembre de 2021, 21:28
*/

package ModeloDAO;

import Config.Conexion;
import Interfaces.CRUD;
import Modelo.Paciente;
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;
/**
*
* @autor GRUPO DE DESARROLLO 3 - MINTIC UIS
*/

public class PacienteDAO implements CRUD {

    /** Definición de la conexión */
    Conexion cnx = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Paciente paciente = new Paciente();
    String mensaje = "";

/* METODOS DE LA INTERFAZ CRUD */

/**
* Lista los elementos de la tabla
* @return List
*/
@Override
public List listar() {
    ArrayList< Paciente > lista = new ArrayList<>();
    String sql = "SELECT PA.*, CONCAT(PE.PERS_PRIMERAPELLIDO, ' ', PE.PERS_SEGUNDOAPELLIDO, ' ', PE.PERS_PRIMERNOMBRE, ' ', PE.PERS_SEGUNDONOMBRE ) NOMBRE "
                + "FROM APPCONSULTAS.PACIENTE PA, APPCONSULTAS.PERSONA PE "
                + "WHERE PA.PERS_ID = PE.PERS_ID";
    try{
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            paciente = new Paciente();
            paciente.setPers_id(rs.getInt("PERS_ID"));
            paciente.setNombre(rs.getString("NOMBRE"));
            //JOptionPane.showMessageDialog(null, paciente.getNombre(), "Resultado de la Operación", JOptionPane.ERROR_MESSAGE);

            paciente.setRegistradoPor(rs.getString("PACI_REGISTRADOPOR"));
            paciente.setFechaCambio(rs.getTimestamp("PACI_FECHACAMBIO"));
            paciente.setAlergias(rs.getString("PACI_ALERGIAS"));
            lista.add(paciente);
        }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase PacienteDAO ::> function public Object listar() ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase PacienteDAO ::> function public Object listar() ::> Exception ::> " + e.getMessage());
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

    ArrayList< Paciente > lista = new ArrayList<>();
    String sql = "SELECT * FROM (SELECT PERS_ID , PACI_REGISTRADOPOR , PACI_FECHACAMBIO , PACI_ALERGIAS , ROW_NUMBER() OVER (ORDER BY pers_id) AS CONT FROM APPCONSULTAS.PACIENTE WHERE POSITION(? IN MOCO_DESCRIPCION)>0 ORDER BY MOCO_ID) AS TABLA WHERE CONT>=? AND CONT<=? ORDER BY CONT";
    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, pagina);
        ps.setInt(2, desde);
        ps.setInt(3, hasta);
        rs = ps.executeQuery();

        while (rs.next()){

            Paciente paciente = new Paciente();
            paciente.setPers_id(rs.getInt("PERS_ID"));
            paciente.setRegistradoPor(rs.getString("PACI_REGISTRADOPOR"));
            paciente.setFechaCambio(rs.getTimestamp("PACI_FECHACAMBIO"));
            paciente.setAlergias(rs.getString("PACI_ALERGIAS"));
            lista.add(paciente);

        }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase PacienteDAO ::> function public Object listar(String filtro) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase PacienteDAO ::> function public Object listar(String filtro) ::> Exception ::> " + e.getMessage());
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
    String sql = "Select * from APPCONSULTAS.PACIENTE where campo_referencia like ? ";
    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, "%"+parametro+"%");
        rs = ps.executeQuery();
        while (rs.next()){
            paciente = new Paciente();
            paciente.setPers_id(rs.getInt("PERS_ID"));
            paciente.setRegistradoPor(rs.getString("PACI_REGISTRADOPOR"));
            //paciente.setCampoReferencia(rs.getString("campo_referencia"));
            lista.add(paciente);
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

    ArrayList< Paciente > lista = new ArrayList<>();
    String sql = "SELECT * FROM APPCONSULTAS.PACIENTE WHERE pers_id = ? ";
    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        rs = ps.executeQuery();

        while (rs.isBeforeFirst()){

            paciente.setPers_id(rs.getInt("PERS_ID"));
            paciente.setRegistradoPor(rs.getString("PACI_REGISTRADOPOR"));
            paciente.setFechaCambio(rs.getTimestamp("PACI_FECHACAMBIO"));
            paciente.setAlergias(rs.getString("PACI_ALERGIAS"));
            lista.add(paciente);

            }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase PacienteDAO ::> function public Object listar(String filtro) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase PacienteDAO ::> function public Object listar(String filtro) ::> Exception ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }finally{
        /*Cerrar.PsRs(ps,rs);*/
    }
    return paciente;

}

/**
* Inserta un nuevo elemento en el objeto
* @return String
*/
@Override

public String add(Object dat) {
Paciente paciente = (Paciente) dat;
    String mensaje = "";
    String sql = "";
    try{
        con = cnx.getConnection();
        sql = "INSERT INTO APPCONSULTAS.PACIENTE ( PACI_REGISTRADOPOR , PACI_FECHACAMBIO , PACI_ALERGIAS ) VALUES ( ? , ? , ? )";
        ps.setString(1, paciente.getRegistradoPor());
        ps.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.now()));
        ps.setString(3, paciente.getAlergias());
        ps.executeUpdate();
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase PacienteDAO ::> function public String insertar(Object dat) ::> SQLException ::> " + e.getMessage());
        paciente.setError(e.getMessage());
        mensaje = e.getMessage();
        e.printStackTrace();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase PacienteDAO ::> function public String insertar(Object dat) ::> Exception ::> " + e.getMessage());
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
    Paciente paciente = new Paciente();
    ArrayList< Paciente > lista = new ArrayList<>();
    String sql = "";
    try{
        con = cnx.getConnection();
        sql = "SELECT PA.*, CONCAT(PE.PERS_PRIMERAPELLIDO, ' ', PE.PERS_SEGUNDOAPELLIDO, ' ', PE.PERS_PRIMERNOMBRE, ' ', PE.PERS_SEGUNDONOMBRE ) NOMBRE "
                + "FROM APPCONSULTAS.PACIENTE PA, APPCONSULTAS.PERSONA PE "
                + "WHERE PA.PERS_ID = PE.PERS_ID AND PA.PERS_ID = ? ";

        ps = con.prepareStatement(sql);
        ps.setString(1, llave);
        rs = ps.executeQuery();
        while (rs.next()) {
            paciente.setPers_id(rs.getInt("PERS_ID"));
            paciente.setNombre(rs.getString("NOMBRE"));
        //JOptionPane.showMessageDialog(null, paciente.getNombre(), "Resultado de la Operación", JOptionPane.ERROR_MESSAGE);
            paciente.setRegistradoPor(rs.getString("PACI_REGISTRADOPOR"));
            paciente.setFechaCambio(rs.getTimestamp("PACI_FECHACAMBIO"));
            paciente.setAlergias(rs.getString("PACI_ALERGIAS"));
        }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase PacienteDAO ::> function public Object consultar(String llave) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase PacienteDAO ::> function public Object consultar(String llave) ::> Exception ::> " + e.getMessage());
        e.printStackTrace();
    }finally{
        /*Cerrar.PsRs(ps,rs);*/
    }
    return paciente;
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
Paciente paciente = (Paciente) dat;
    String mensaje = "";
    String sql =
    "UPDATE APPCONSULTAS.PACIENTE SET "
    +" PACI_REGISTRADOPOR = ? , "
    +" PACI_FECHACAMBIO = ? , "
    +" PACI_ALERGIAS = ? "
    +" WHERE pers_id = ? ";
    try{
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, paciente.getRegistradoPor());
        ps.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.now()));
        ps.setString(3, paciente.getAlergias());
        ps.setInt(4, paciente.getPers_id());
        ps.executeUpdate();

    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase PacienteDAO ::> function public String actualizar(Object dat) ::> SQLException ::> " + e.getMessage());
        paciente.setError(e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase PacienteDAO ::> function public String actualizar(Object dat) ::> Exception ::> " + e.getMessage());
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
    String sql = "DELETE FROM APPCONSULTAS.PACIENTE WHERE pers_id = ? ";

    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();

    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase PacienteDAO ::> function public Object eliminar(String filtro) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
    }catch(Exception e){
        mensaje = e.getMessage();
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase PacienteDAO ::> function public Object eliminar(String filtro) ::> Exception ::> " + e.getMessage());
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
* 27-SEPTIEMBRE-2021 @: GRUPO DE DESARROLLO 3 &: creación
*/

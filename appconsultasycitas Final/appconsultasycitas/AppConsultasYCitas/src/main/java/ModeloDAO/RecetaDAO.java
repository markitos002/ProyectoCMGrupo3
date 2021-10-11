
/*
* RecetaDAO.java
*
* Creado el 24 de septiembre de 2021, 09:43
*/

package ModeloDAO;

import Config.Conexion;
import Interfaces.CRUD;
import Modelo.Receta;
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;
/**
*
* @autor GRUPO DE DESARROLLO 3 - MINTIC UIS
*/

public class RecetaDAO implements CRUD {

    /** Definición de la conexión */
    Conexion cnx = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Receta receta = new Receta();
    String mensaje = "";

/* METODOS DE LA INTERFAZ CRUD */

/**
* Lista los elementos de la tabla
* @return List
*/
@Override
public List listar() {
    ArrayList< Receta > lista = new ArrayList<>();
    String sql = "SELECT * FROM APPCONSULTAS.RECETA";
    try{
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            receta = new Receta();
            receta.setIdReceta(rs.getInt("RECE_ID"));
            receta.setCantidadMedicamento(rs.getString("RECE_CANTIDADMEDICAMENTO"));
            receta.setMedicamento(rs.getString("RECE_MEDICAMENTO"));
            receta.setPosologia(rs.getString("RECE_POSOLOGIA"));
            receta.setViaAdministracion(rs.getString("RECE_VIAADMINISTRACIÓN"));
            receta.setObservacion(rs.getString("RECE_OBSERVACION"));
            receta.setRegistradoPor(rs.getString("RECE_REGISTRADOPOR"));
            receta.setFechaCambio(rs.getTimestamp("RECE_FECHACAMBIO"));
            receta.setIdConsulta(rs.getInt("COME_ID"));
            lista.add(receta);
        }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase RecetaDAO ::> function public Object listar() ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase RecetaDAO ::> function public Object listar() ::> Exception ::> " + e.getMessage());
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

    String parametro = (String)pagina;
    if (parametro.equals("")){
        parametro = "";
    }
  
    ArrayList< Receta > lista = new ArrayList<>();
    String sql = "SELECT * FROM (SELECT RECE_ID , RECE_CANTIDADMEDICAMENTO , RECE_MEDICAMENTO , RECE_POSOLOGIA , RECE_VIAADMINISTRACIÓN , RECE_OBSERVACION , RECE_REGISTRADOPOR , RECE_FECHACAMBIO , COME_ID , ROW_NUMBER() OVER (ORDER BY rece_id) AS CONT FROM APPCONSULTAS.RECETA WHERE POSITION(? IN RECE_ID)>0 ORDER BY RECE_ID) AS TABLA WHERE CONT>=? AND CONT<=? ORDER BY CONT";
    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, parametro);
        ps.setInt(2, desde);
        ps.setInt(3, hasta);
        rs = ps.executeQuery();
//JOptionPane.showMessageDialog(null, "'"+parametro+"'", "Resultado de la Operación", JOptionPane.ERROR_MESSAGE);
        while (rs.next()){

            Receta receta = new Receta();
            receta.setIdReceta(rs.getInt("RECE_ID"));
            receta.setCantidadMedicamento(rs.getString("RECE_CANTIDADMEDICAMENTO"));
            receta.setMedicamento(rs.getString("RECE_MEDICAMENTO"));
            receta.setPosologia(rs.getString("RECE_POSOLOGIA"));
            receta.setViaAdministracion(rs.getString("RECE_VIAADMINISTRACIÓN"));
            receta.setObservacion(rs.getString("RECE_OBSERVACION"));
            receta.setRegistradoPor(rs.getString("RECE_REGISTRADOPOR"));
            receta.setFechaCambio(rs.getTimestamp("RECE_FECHACAMBIO"));
            receta.setIdConsulta(rs.getInt("COME_ID"));
            lista.add(receta);

        }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase RecetaDAO ::> function public Object listar(String filtro) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase RecetaDAO ::> function public Object listar(String filtro) ::> Exception ::> " + e.getMessage());
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
    String sql = "Select * from APPCONSULTAS.RECETA where campo_referencia like ? ";
    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, "%"+parametro+"%");
        rs = ps.executeQuery();
        while (rs.next()){
            receta = new Receta();
            receta.setIdReceta(rs.getInt("RECE_ID"));
            receta.setCantidadMedicamento(rs.getString("RECE_CANTIDADMEDICAMENTO"));
            //receta.setCampoReferencia(rs.getString("campo_referencia"));
            lista.add(receta);
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

    ArrayList< Receta > lista = new ArrayList<>();
    String sql = "SELECT * FROM APPCONSULTAS.RECETA WHERE come_id = ? ";
    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        rs = ps.executeQuery();

        while (rs.isBeforeFirst()){

            receta.setIdReceta(rs.getInt("RECE_ID"));
            receta.setCantidadMedicamento(rs.getString("RECE_CANTIDADMEDICAMENTO"));
            receta.setMedicamento(rs.getString("RECE_MEDICAMENTO"));
            receta.setPosologia(rs.getString("RECE_POSOLOGIA"));
            receta.setViaAdministracion(rs.getString("RECE_VIAADMINISTRACIÓN"));
            receta.setObservacion(rs.getString("RECE_OBSERVACION"));
            receta.setRegistradoPor(rs.getString("RECE_REGISTRADOPOR"));
            receta.setFechaCambio(rs.getTimestamp("RECE_FECHACAMBIO"));
            receta.setIdConsulta(rs.getInt("COME_ID"));
            lista.add(receta);

            }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase RecetaDAO ::> function public Object listar(String filtro) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase RecetaDAO ::> function public Object listar(String filtro) ::> Exception ::> " + e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }finally{
        /*Cerrar.PsRs(ps,rs);*/
    }
    return receta;

}

/**
* Inserta un nuevo elemento en el objeto
* @return String
*/
@Override


public String add(Object dat) {
Receta receta = (Receta) dat;
    String mensaje = "";
    String sql = "";
    try{
        con = cnx.getConnection();
        sql = "INSERT INTO APPCONSULTAS.RECETA ( RECE_CANTIDADMEDICAMENTO , RECE_MEDICAMENTO , RECE_POSOLOGIA , RECE_VIAADMINISTRACIÓN , RECE_OBSERVACION , RECE_REGISTRADOPOR , RECE_FECHACAMBIO , COME_ID ) VALUES ( ? , ? , ? , ? , ? , ? , ? , ? )";
        ps = con.prepareStatement(sql);
        ps.setString(1, receta.getCantidadMedicamento());
        ps.setString(2, receta.getMedicamento());
        ps.setString(3, receta.getPosologia());
        ps.setString(4, receta.getViaAdministracion());
        ps.setString(5, receta.getObservacion());
        ps.setString(6, receta.getRegistradoPor());
        ps.setDate(7, java.sql.Date.valueOf(java.time.LocalDate.now()));
        ps.setInt(8, receta.getIdConsulta());
        ps.executeUpdate();
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase RecetaDAO ::> function public String insertar(Object dat) ::> SQLException ::> " + e.getMessage());
        receta.setError(e.getMessage());
        mensaje = e.getMessage();
        e.printStackTrace();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase RecetaDAO ::> function public String insertar(Object dat) ::> Exception ::> " + e.getMessage());
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
    Receta receta = new Receta();
    ArrayList< Receta > lista = new ArrayList<>();
    String sql = "";
    try{
        con = cnx.getConnection();
        sql = "SELECT * FROM APPCONSULTAS.RECETA WHERE rece_id = ? ";
        ps = con.prepareStatement(sql);
        ps.setString(1, llave);
        rs = ps.executeQuery();
        while (rs.next()) {
            receta.setIdReceta(rs.getInt("RECE_ID"));
            receta.setCantidadMedicamento(rs.getString("RECE_CANTIDADMEDICAMENTO"));
            receta.setMedicamento(rs.getString("RECE_MEDICAMENTO"));
            receta.setPosologia(rs.getString("RECE_POSOLOGIA"));
            receta.setViaAdministracion(rs.getString("RECE_VIAADMINISTRACIÓN"));
            receta.setObservacion(rs.getString("RECE_OBSERVACION"));
            receta.setRegistradoPor(rs.getString("RECE_REGISTRADOPOR"));
            receta.setFechaCambio(rs.getTimestamp("RECE_FECHACAMBIO"));
            receta.setIdConsulta(rs.getInt("COME_ID"));
        }
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase RecetaDAO ::> function public Object consultar(String llave) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase RecetaDAO ::> function public Object consultar(String llave) ::> Exception ::> " + e.getMessage());
        e.printStackTrace();
    }finally{
        /*Cerrar.PsRs(ps,rs);*/
    }
    return receta;
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
Receta receta = (Receta) dat;
    String mensaje = "";
    String sql =
    "UPDATE APPCONSULTAS.RECETA SET "
    +" RECE_CANTIDADMEDICAMENTO = ? , "
    +" RECE_MEDICAMENTO = ? , "
    +" RECE_POSOLOGIA = ? , "
    +" RECE_VIAADMINISTRACIÓN = ? , "
    +" RECE_OBSERVACION = ? , "
    +" RECE_REGISTRADOPOR = ? , "
    +" RECE_FECHACAMBIO = ? , "
    +" COME_ID = ? "
    +" WHERE rece_id = ? ";
    try{
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, receta.getCantidadMedicamento());
        ps.setString(2, receta.getMedicamento());
        ps.setString(3, receta.getPosologia());
        ps.setString(4, receta.getViaAdministracion());
        ps.setString(5, receta.getObservacion());
        ps.setString(6, receta.getRegistradoPor());
        ps.setDate(7, java.sql.Date.valueOf(java.time.LocalDate.now()));
        ps.setInt(8, receta.getIdConsulta());
        ps.setInt(9, receta.getIdReceta());
        ps.executeUpdate();
        
    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase RecetaDAO ::> function public String actualizar(Object dat) ::> SQLException ::> " + e.getMessage());
        receta.setError(e.getMessage());
        e.printStackTrace();
        mensaje = e.getMessage();
    }catch(Exception e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase RecetaDAO ::> function public String actualizar(Object dat) ::> Exception ::> " + e.getMessage());
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
    String sql = "DELETE FROM APPCONSULTAS.RECETA WHERE rece_id = ? ";

    try {
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();

    }catch(SQLException e){
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase RecetaDAO ::> function public Object eliminar(String filtro) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
    }catch(Exception e){
        mensaje = e.getMessage();
        System.out.println("Error ::> Modelo.ModeloDAO ::> clase RecetaDAO ::> function public Object eliminar(String filtro) ::> Exception ::> " + e.getMessage());
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
    String sql = "Select count(*) from appconsultas.receta where rece_medicamento like ? ";
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
* 24-SEPTIEMBRE-2021 @: GRUPO DE DESARROLLO 3 &: creación
*/

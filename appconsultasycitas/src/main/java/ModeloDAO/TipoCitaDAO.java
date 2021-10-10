/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloDAO;

import Config.Conexion;
import Interfaces.CRUD;
import Modelo.TipoCita;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author jacun
 */
public class TipoCitaDAO implements CRUD{

    Conexion cnx = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    TipoCita mc = new TipoCita();
    
    @Override
    public List listar() {
        ArrayList<TipoCita> lista = new ArrayList<>();
        String sql = "Select * from appconsultas.TipoCita";
        try {
            con = cnx.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                TipoCita tipoCita = new TipoCita();
                tipoCita.setIdTipoCita(rs.getInt("tici_id"));
                tipoCita.setNombre(rs.getString("tici_nombre"));
                lista.add(tipoCita);
            }
            
        } catch (SQLException e) {
            
        }
        return lista;
    }

    @Override
    public List listarPagina(String pagina, int desde, int hasta) {
        ArrayList<TipoCita> lista = new ArrayList<>();
        String sql = "Select * from (Select  tici_id, tici_nombre, row_number() OVER (ORDER BY tici_id) as cont from appconsultas.TipoCita where position(? in tici_nombre)>0 order by tici_id) as tabla where cont>=? and cont<=? order by cont";
        try {
            con = cnx.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, pagina);
            ps.setInt(2, desde);
            ps.setInt(3, hasta);
            rs = ps.executeQuery();
            while (rs.next()){
                TipoCita tipoCita = new TipoCita();
                tipoCita.setIdTipoCita(rs.getInt("tici_id"));
                tipoCita.setNombre(rs.getString("tici_nombre"));
                lista.add(tipoCita);
            }
            JOptionPane.showMessageDialog(null, lista.size(), "Resultado de la Operación", JOptionPane.ERROR_MESSAGE);
  
        } catch (SQLException e) {
            
        }
        return lista;
    }

    
    @Override
    public TipoCita list(int id) {
        ArrayList<TipoCita> lista = new ArrayList<>();
        String sql = "Select * from appconsultas.TipoCita where tici_id = ? ";
        try {
            con = cnx.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()){
                mc.setIdTipoCita(rs.getInt("tici_id"));
                mc.setNombre(rs.getString("tici_nombre"));
                lista.add(mc);
            }
            
        } catch (SQLException e) {
            
        }
        return mc;
    }

    @Override
    public List listarConParametro(String parametro) {
        ArrayList<TipoCita> lista = new ArrayList<>();
        
        //int parametro2 =Integer.parseInt(parametro);
        String sql = "Select * from appconsultas.TipoCita where tici_nombre like  ? ";
        try {
            con = cnx.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%"+parametro+"%"); 
            rs = ps.executeQuery();

            while (rs.next()){
                TipoCita tipoCita = new TipoCita();
                tipoCita.setIdTipoCita(rs.getInt("tici_id"));
                tipoCita.setNombre(rs.getString("tici_nombre"));
                lista.add(tipoCita);
            }
            
        } catch (SQLException e) {
            
        }
        return lista;
    }

    
    @Override
    public String add(Object dat) {
        TipoCita tipoCita = (TipoCita) dat;
        String sql = "Insert into appconsultas.TipoCita(tici_id, tici_nombre, tici_registradopor, tici_fechacambio)values( default , '"+tipoCita.getNombre()+"', '"+tipoCita.getRegistradoPor()+"', now());";
        String mensaje = "";
        try {
            con = cnx.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return mensaje;
    }

/**
* Consulta un elemento del objeto según el parámetro enviado
* @return Object
*/
@Override
public Object consultar(String llave) {
    ArrayList< TipoCita > lista = new ArrayList<>();
    TipoCita tipoCita = new TipoCita();
    String sql = "SELECT * FROM APPCONSULTAS.TipoCita WHERE tici_id = ? ";
    try{
        con = cnx.getConnection();
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
        System.out.println("Error ::> Motivo.ModeloDAO ::> clase TipoCitaDAO ::> function public Object consultar(String llave) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
    }catch(Exception e){
        System.out.println("Error ::> Motivo.ModeloDAO ::> clase TipoCitaDAO ::> function public Object consultar(String llave) ::> Exception ::> " + e.getMessage());
        e.printStackTrace();
    }finally{
        /*Cerrar.PsRs(ps,rs);*/
    }
    return tipoCita;
}

    @Override
    public String edit(Object dat) {
        TipoCita tipoCita = (TipoCita)dat;
        String mensaje = "";
        String sql = "Update appconsultas.TipoCita set tici_nombre = ? where tici_id = ? ";
        try {
            con = cnx.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, tipoCita.getNombre());
            ps.setInt(2, tipoCita.getIdTipoCita());
            ps.executeUpdate();
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return mensaje;
    }

    @Override
    public String elim(int id) {
        String mensaje = "";
        String sql = "Delete from appconsultas.TipoCita where tici_id = ? ";
        try {
            con = cnx.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return mensaje;
    }
    
    @Override
    public int calcularPaginas(String parametro) {
        int nroRegistros = 0;
        int paginador = 0;
        String sql = "Select count(*) from appconsultas.TipoCita where tici_nombre like ? ";
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

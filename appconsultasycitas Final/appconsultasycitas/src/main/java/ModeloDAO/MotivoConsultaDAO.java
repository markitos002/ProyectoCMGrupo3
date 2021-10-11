/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloDAO;

import Config.Conexion;
import Interfaces.CRUD;
import Modelo.MotivoConsulta;
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
public class MotivoConsultaDAO implements CRUD{

    Conexion cnx = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    MotivoConsulta mc = new MotivoConsulta();
    
    @Override
    public List listar() {
        ArrayList<MotivoConsulta> lista = new ArrayList<>();
        String sql = "Select * from appconsultas.motivoconsulta";
        try {
            con = cnx.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                MotivoConsulta moc = new MotivoConsulta();
                moc.setId(rs.getInt("moco_id"));
                moc.setDescripcion(rs.getString("moco_descripcion"));
                lista.add(moc);
            }
            
        } catch (SQLException e) {
            
        }
        return lista;
    }

    @Override
    public List listarPagina(String pagina, int desde, int hasta) {
        ArrayList<MotivoConsulta> lista = new ArrayList<>();
        String sql = "Select * from (Select  moco_id, moco_descripcion, row_number() OVER (ORDER BY moco_id) as cont from appconsultas.motivoconsulta where position(? in moco_descripcion)>0 order by moco_id) as tabla where cont>=? and cont<=? order by cont";
        try {
            con = cnx.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, pagina);
            ps.setInt(2, desde);
            ps.setInt(3, hasta);
            rs = ps.executeQuery();
            while (rs.next()){
                MotivoConsulta moc = new MotivoConsulta();
                moc.setId(rs.getInt("moco_id"));
                moc.setDescripcion(rs.getString("moco_descripcion"));
                lista.add(moc);
            }
            JOptionPane.showMessageDialog(null, lista.size(), "Resultado de la Operación", JOptionPane.ERROR_MESSAGE);
  
        } catch (SQLException e) {
            
        }
        return lista;
    }

    
    @Override
    public MotivoConsulta list(int id) {
        ArrayList<MotivoConsulta> lista = new ArrayList<>();
        String sql = "Select * from appconsultas.motivoconsulta where moco_id = ? ";
        try {
            con = cnx.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()){
                mc.setId(rs.getInt("moco_id"));
                mc.setDescripcion(rs.getString("moco_descripcion"));
                lista.add(mc);
            }
            
        } catch (SQLException e) {
            
        }
        return mc;
    }

    @Override
    public List listarConParametro(String parametro) {
        ArrayList<MotivoConsulta> lista = new ArrayList<>();
        
        //int parametro2 =Integer.parseInt(parametro);
        String sql = "Select * from appconsultas.motivoconsulta where moco_descripcion like  ? ";
        try {
            con = cnx.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%"+parametro+"%"); 
            rs = ps.executeQuery();

            while (rs.next()){
                MotivoConsulta moc = new MotivoConsulta();
                moc.setId(rs.getInt("moco_id"));
                moc.setDescripcion(rs.getString("moco_descripcion"));
                lista.add(moc);
            }
            
        } catch (SQLException e) {
            
        }
        return lista;
    }

    
    @Override
    public String add(Object dat) {
        MotivoConsulta motivo = (MotivoConsulta) dat;
        String sql = "Insert into appconsultas.motivoconsulta(moco_id, moco_descripcion, moco_registradopor, moco_fechacambio)values( default , '"+motivo.getDescripcion()+"', '"+motivo.getRegistradoPor()+"', now());";
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
    ArrayList< MotivoConsulta > lista = new ArrayList<>();
    String sql = "SELECT * FROM APPCONSULTAS.MOTIVOCONSULTA WHERE MOCO_id = ? ";
    try{
        con = cnx.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, llave);
        rs = ps.executeQuery();
        while (rs.next()) {
            MotivoConsulta motivo = new MotivoConsulta();
            motivo.setId(rs.getInt("MOCO_ID"));
            motivo.setRegistradoPor(rs.getString("MOCO_REGISTRADOPOR"));
            motivo.setFecha(rs.getDate("MOCO_FECHACAMBIO"));
            lista.add(motivo);
        }
    }catch(SQLException e){
        System.out.println("Error ::> Motivo.ModeloDAO ::> clase MotivoConsultaDAO ::> function public Object consultar(String llave) ::> SQLException ::> " + e.getMessage());
        e.printStackTrace();
    }catch(Exception e){
        System.out.println("Error ::> Motivo.ModeloDAO ::> clase MotivoConsultaDAO ::> function public Object consultar(String llave) ::> Exception ::> " + e.getMessage());
        e.printStackTrace();
    }finally{
        /*Cerrar.PsRs(ps,rs);*/
    }
    return lista;
}

    @Override
    public String edit(Object dat) {
        MotivoConsulta moc = (MotivoConsulta)dat;
        String mensaje = "";
        String sql = "Update appconsultas.motivoconsulta set moco_descripcion = ? where moco_id = ? ";
        try {
            con = cnx.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, moc.getDescripcion());
            ps.setInt(2, moc.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return mensaje;
    }

    @Override
    public String elim(int id) {
        String mensaje = "";
        String sql = "Delete from appconsultas.motivoconsulta where moco_id = ? ";
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

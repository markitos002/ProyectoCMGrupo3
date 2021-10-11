/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Config;

import java.sql.*;

/**
 *
 * @author jacun
 */
public class Conexion {
    Connection con;
    
    public Conexion(){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/appconsultas?useUnicode=true&characterEncoding=utf-8", "root", "Tgbnkl76");

            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Error: "+ e);
            }
    }

    public Connection getConnection() {
        return con;
    }

    	public void cerrar(Connection connection) {

		try {
			if (connection != null && connection.isClosed() == false) {
				connection.close();				
			}
		} catch (SQLException e) {
                    // TODO Auto-generated catch block
                    System.out.println("Error");
		}

	}
        
}
    
    


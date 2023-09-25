package com.utec.datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexion {
    // Variables a utilizar
    private static final String USUARIO = "prograIV";
    private static final String PASS = "prograIV";
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=GestionClientes;encrypt=true; trustServerCertificate=true";
    // variable para gestionar la conexion
    private static Connection conexion = null;
    
    // metodo para realizar la conexion
    public static Connection conectarse(){
        try{
            // cargar el driver para la conexion
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            // Obtener la conexion a la BD
            conexion = DriverManager.getConnection(URL, USUARIO, PASS);
            
            // para probar
            System.out.println("Conexion exitosa");
            
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Error al conectarse a la BD " + e);
        }
        return conexion;
    }
    
    // Metodos para cerrar conexion
    public static void close(Connection conn){
        try{
            conn.close();
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }
    }

    public static void close(ResultSet rs){
        try{
            rs.close();
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }
    }

    public static void close(PreparedStatement stmt){
        try{
            stmt.close();
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }
    }
}

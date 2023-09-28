
package com.utec.datos;

import com.utec.modelo.Cliente;
import com.utec.modelo.Productos;
import com.utec.modelo.Ventas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProductoDao {
    
    private static final String SQL_SELECT = "SELECT IdProducto, Producto, Tipo, Cantidad, Precio FROM productos";

    private static final String SQL_SELECT_BY_ID = "SELECT IdProducto, Producto, Tipo, Cantidad, Precio FROM productos WHERE IdProducto = ?";

    private static final String SQL_INSERT = "INSERT INTO productos(Producto, Tipo, Cantidad, Precio) VALUES(?, ?, ?, ?)";

    private static final String SQL_UPDATE = "UPDATE productos SET Producto=?, Tipo=?, Cantidad=?, Precio=? WHERE IdProducto=?";

    private static final String SQL_DELETE = "DELETE FROM productos WHERE IdProducto = ?";
    
    
    
    
    // Definimos el método para mostrar todos los clientes
    public List<Productos> listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Productos producto = null;
        List<Productos> listProducto = new ArrayList<>();

        try {
            conn = Conexion.conectarse();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {                
                producto = new Productos();
                //recuperar datos de los productos
                producto.setIdProducto(rs.getInt("IdProducto"));
                producto.setProducto(rs.getString("Producto"));
                producto.setCantidad(rs.getInt("Cantidad"));
                producto.setPrecio(rs.getDouble("Precio"));
                producto.setTipo(rs.getString("Tipo"));
                
                listProducto.add(producto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return listProducto;
    }

    // Método para buscar un producto en particular
    public Productos encontrar(Productos producto) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.conectarse();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, producto.getIdProducto());
            rs = stmt.executeQuery();
            //rs.absolute(1);//nos posicionamos en el primer registro devuelto
            rs.next();

            //recuperar datos de la consulta productos            
            
                producto.setIdProducto(rs.getInt("IdProducto"));
                producto.setProducto(rs.getString("Producto"));
                producto.setCantidad(rs.getInt("Cantidad"));
                producto.setPrecio(rs.getDouble("Precio"));
                producto.setTipo(rs.getString("Tipo"));

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return producto;
    }

    //Metodo para insertar productos
    public String insertarProducto(Productos producto){
        Connection conn = null;
        PreparedStatement stmt = null;

        try{
            conn = Conexion.conectarse();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, producto.getProducto());
            stmt.setString(2, producto.getTipo());
            stmt.setInt(3, producto.getCantidad());
            stmt.setDouble(4, producto.getPrecio());
            stmt.execute();

            return null;
        }catch(SQLException ex){
            return ex.getMessage();
        }finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
    }
    
    //Metodo para modificar producto  
    public String modificarProducto(Productos producto){
        Connection conn = null;
        PreparedStatement stmt = null;
       
       
        try{
            conn = Conexion.conectarse();
            stmt = conn.prepareStatement(SQL_UPDATE);
            
            stmt.setString(1, producto.getProducto());
            stmt.setString(2, producto.getTipo());
            stmt.setInt(3, producto.getCantidad());
            stmt.setDouble(4, producto.getPrecio());
            stmt.setInt(5,producto.getIdProducto());
            stmt.execute();

            return null;
            
        }catch(SQLException ex){
            return ex.getMessage();
        }finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
       
    }
    public boolean eliminarProducto(Productos producto){
    Connection conn = null;
    PreparedStatement stmt = null;

    try{
        conn = Conexion.conectarse();
        stmt = conn.prepareStatement(SQL_DELETE);
        stmt.setInt(1,producto.getIdProducto());

        stmt.execute();
        
        return true;
    }catch(SQLException ex){
        ex.printStackTrace(System.out);
        return false;
    }finally{
        Conexion.close(stmt);
        Conexion.close(conn);
    }
} 
    
    
    
    
    
    
    
}

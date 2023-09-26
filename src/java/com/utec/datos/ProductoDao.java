
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
    
    private static final String SQL_SELECT = "SELECT p.IdProducto, p.Producto, p.Tipo, p.Cantidad, p.Precio FROM productos";

    private static final String SQL_SELECT_BY_ID = "SELECT IdProducto, Producto, Tipo, Cantidad, Precio FROM productos WHERE IdProducto = ?";

    private static final String SQL_INSERT = "INSERT INTO Cliente(Nombres, Apellidos, Email, Telefono, Saldo) VALUES(?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE = "UPDATE productos SET IdProducto=?, Producto=?, Tipo=?, Cantidad=?, Precio=? WHERE IdVenta=?";

    private static final String SQL_DELETE = "DELETE FROM productos WHERE IdProducto = ?";
    
    
    
    
    // Definimos el método para mostrar todos los clientes
    public List<Productos> listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Productos producto = null;        
        Cliente cliente = null;
        List<Productos> listProducto = new ArrayList<>();

        try {
            conn = Conexion.conectarse();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {                
                producto = new Productos();
                cliente = new Cliente();
                //recuperar datos de las ventas
                producto.setIdProducto(rs.getInt("IdProducto"));
                producto.setProducto(rs.getString("Producto"));
                producto.setCantidad(rs.getInt("Cantidad"));
                producto.setPrecio(rs.getDouble("Precio"));
                producto.setTipo(rs.getString("Tipo"));                
               
                //Datos del cliente.
                cliente.setNombres(rs.getString("Nombres"));
                cliente.setApellidos(rs.getString("Apellidos"));
               
               //colocar en la venta los datos del cliente
                //producto.setCliente(cliente);

                //recuperar datos del producto
                producto.setProducto(rs.getString("Producto"));

                //colocar en la venta los datos del producto
                //producto.setProducto(producto);
                
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

    // Método para buscar una venta en particular
    public Productos encontrar(Productos producto) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;       
        Cliente cliente = new Cliente();
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
             
               
                //Datos del cliente.
                cliente.setIdCliente(rs.getInt("IdCliente"));
               
               //colocar en la venta los datos del cliente
                //venta.setCliente(cliente);

                //recuperar datos del producto
                producto.setIdProducto(rs.getInt("IdProducto"));

                //colocar en la venta los datos del producto
                //producto.setProducto(producto);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return producto;
    }

    //Metodo para insertar una nueva venta
    public boolean insertarProducto(Productos producto){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            conn = Conexion.conectarse();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();

            return true;
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
            return false;
        }
    }
//Este metodo ya esta finalizado
    //Metodo para modificar producto  
    public int ModificarProducto(Productos producto){
        Connection conn = null;
        PreparedStatement stmt = null;
        //Cliente cliente = producto.getCliente();
        int rows = 0;
        //Realizamos el procedimiento
        try{
            conn = Conexion.conectarse();
            stmt = conn.prepareStatement(SQL_UPDATE);

            // Multiplicar cantidad por precioProducto para obtener el monto de la venta
        double totalProducto = producto.getCantidad() * producto.getPrecio();
        
            
            stmt.setInt(1,producto.getIdProducto());//Dato que vamos a seleccionar al momento de modifcar
            stmt.setString(2,producto.getProducto());
            stmt.setInt(3,producto.getCantidad());
            stmt.setDouble(4,producto.getPrecio());
            stmt.setString(5,producto.getTipo());            

            //Creamos la operacion a realizar 
            //la cantidad por el precio venta el
            //cual sera el monto total
            
        }catch(SQLException ex){
        }
        return rows;
    }
    public void EliminarProducto(Productos producto){
    Connection conn = null;
    PreparedStatement stmt = null;
    int rows = 0;

    try{
        conn = Conexion.conectarse();
        stmt = conn.prepareStatement(SQL_DELETE);
        stmt.setInt(1,producto.getIdProducto());

        rows =stmt.executeUpdate();
    }catch(SQLException ex){
        ex.printStackTrace(System.out);
    }finally{
        Conexion.close(stmt);
        Conexion.close(conn);
    }
} 
    
    
    
    
    
    
    
}

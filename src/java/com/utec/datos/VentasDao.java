package com.utec.datos;

import com.utec.modelo.Productos;
import com.utec.modelo.Ventas;
import com.utec.modelo.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VentasDao {
    // Definimos variables donde almacenaremos los query para realizar CRUD
    private static final String SQL_SELECT = "SELECT v.IdVenta, c.Nombre,c.Apellido, p.Producto, v.Cantidad, v.PrecioProd, v.TotalVenta, v.Fecha FROM ventas v INNER JOIN productos p on p.IdProducto=v.IdProducto INNER JOIN cliente c on c.IdCliente = v.IdCliente";

    private static final String SQL_SELECT_BY_ID = "SELECT IdVenta, IdCliente, IdProducto, Cantidad, PrecioProd, TotalVenta, Fecha FROM ventas WHERE IdVenta = ?";

    private static final String SQL_INSERT = "INSERT INTO ventas(IdCliente, IdProducto, Cantidad, PrecioProd, TotalVenta, Fecha) VALUES(?, ?, ?, ?, ?, NOW())";

    private static final String SQL_UPDATE = "UPDATE ventas SET IdCliente=?, IdProducto=?, Cantidad=?, PrecioProd=?, TotalVenta=? WHERE IdVenta=?";

    private static final String SQL_DELETE = "DELETE FROM ventas WHERE IdVenta = ?";

    // Definimos el método para mostrar todos los clientes
    public List<Ventas> listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Ventas venta = null;
        Productos producto = null;
        Cliente cliente = null;
        List<Ventas> listVenta = new ArrayList<>();

        try {
            conn = Conexion.conectarse();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                venta = new Ventas();
                producto = new Productos();
                cliente = new Cliente();
                //recuperar datos de las ventas
                venta.setIdVenta(rs.getInt("IdVenta"));
                venta.setCantidad(rs.getInt("Cantidad"));
                venta.setPrecioProd(rs.getDouble("PrecioProd"));
                venta.setTotalVenta(rs.getDouble("TotalVenta"));
                venta.setFecha(rs.getDate("Fecha"));
               
                //Datos del cliente.
                cliente.setNombres(rs.getString("Nombre"));
                cliente.setApellidos(rs.getString("Apellido"));
               
               //colocar en la venta los datos del cliente
                venta.setCliente(cliente);

                //recuperar datos del producto
                producto.setProducto(rs.getString("Producto"));

                //colocar en la venta los datos del producto
                venta.setProductos(producto);
                
                listVenta.add(venta);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return listVenta;
    }

    // Método para buscar una venta en particular
    public Ventas encontrar(Ventas venta) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Productos producto = new Productos();
        Cliente cliente = new Cliente();
        try {
            conn = Conexion.conectarse();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, venta.getIdVenta());
            rs = stmt.executeQuery();
            //rs.absolute(1);//nos posicionamos en el primer registro devuelto
            rs.next();

            //recuperar datos de la consulta
            //recuperar datos de las ventas
                venta.setIdVenta(rs.getInt("IdVenta"));
                venta.setCantidad(rs.getInt("Cantidad"));
                venta.setPrecioProd(rs.getDouble("PrecioProd"));
                venta.setTotalVenta(rs.getDouble("TotalVenta"));
                venta.setFecha(rs.getDate("Fecha"));
               
                //Datos del cliente.
                cliente.setIdCliente(rs.getInt("IdCliente"));
               
               //colocar en la venta los datos del cliente
                venta.setCliente(cliente);

                //recuperar datos del producto
                producto.setIdProducto(rs.getInt("IdProducto"));

                //colocar en la venta los datos del producto
                venta.setProductos(producto);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return venta;
    }

    //Metodo para insertar una nueva venta
    public boolean insertarVenta(Ventas venta, String errorMessage){
        errorMessage=null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Productos producto = venta.getProductos();
        Cliente cliente = venta.getCliente();
        
        try{
            conn = Conexion.conectarse();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, cliente.getIdCliente());
            stmt.setInt(2, producto.getIdProducto());
            stmt.setInt(3, venta.getCantidad());
            stmt.setDouble(4, venta.getPrecioProd());
            stmt.setDouble(5,venta.getTotalVenta());
            rs = stmt.executeQuery();
            
            return true;
        }catch(SQLException ex){
            errorMessage=ex.getMessage();
            return false;
        }
    }
//Este metodo ya esta finalizado
    //Metodo para modificar ventas  
    public int modificarVenta(Ventas ventas){
        Connection conn = null;
        PreparedStatement stmt = null;
        Cliente cliente = ventas.getCliente();
        Productos producto = ventas.getProductos();
        int rows = 0;
        //Realizamos el procedimiento
        try{
            conn = Conexion.conectarse();
            stmt = conn.prepareStatement(SQL_UPDATE);

            // Multiplicar cantidad por precioProducto para obtener el monto de la venta
        double totalVenta = ventas.getCantidad() * ventas.getPrecioProd();
        
            stmt.setInt(1,cliente.getIdCliente());
            stmt.setInt(2,producto.getIdProducto());
            stmt.setInt(3,ventas.getCantidad());
            stmt.setDouble(4,ventas.getPrecioProd());
            stmt.setDouble(5,ventas.getTotalVenta());
            stmt.setInt(6,ventas.getIdVenta());//Dato que vamos a seleccionar al momento de modifcar

            //Creamos la operacion a realizar 
            //la cantidad por el precio venta el
            //cual sera el monto total
            
        }catch(SQLException ex){
        }
        return rows;
    }
    public void EliminarVenta(Ventas ventas){
    Connection conn = null;
    PreparedStatement stmt = null;
    int rows = 0;

    try{
        conn = Conexion.conectarse();
        stmt = conn.prepareStatement(SQL_DELETE);
        stmt.setInt(1,ventas.getIdVenta());

        rows =stmt.executeUpdate();
    }catch(SQLException ex){
        ex.printStackTrace(System.out);
    }finally{
        Conexion.close(stmt);
        Conexion.close(conn);
    }
} 
}

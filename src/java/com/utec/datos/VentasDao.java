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
        Ventas venta;
        Productos producto;
        Cliente cliente;
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
    public Ventas encontrar(Ventas venta2) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Productos producto = new Productos();
        Cliente cliente = new Cliente();
        Ventas venta = new Ventas();
        try {
            conn = Conexion.conectarse();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, venta2.getIdVenta());
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
    public String insertarVenta(Ventas venta) {
        Connection conn = null;
        PreparedStatement stmt = null;
        Productos producto = venta.getProductos();
        Cliente cliente = venta.getCliente();

        //voy a buscar el prducto para obtener su precio
        Productos producto2 = new ProductoDao().encontrar(producto);
        venta.setPrecioProd(producto2.getPrecio());
        venta.setTotalVenta(producto2.getPrecio() * venta.getCantidad());

        //valido que exista la cantidad de producto solicitada
        if (venta.getCantidad() > producto2.getCantidad()) {
            return "Solamente tenemos " + producto2.getCantidad() + " " + producto2.getProducto() + " Disponibles.";
        }
        
        //Buscar un cliente para obtener su saldo
        Cliente cliente2 = new ClienteDao().encontrar(cliente);
        //validar que el cliente tenga los fondos suficientes.
        if(cliente2.getSaldo() < venta.getTotalVenta()){
            return "El saldo del cliente no es suficiente para realizar la venta. Solamente posee: $"+cliente.getSaldo();
        }
        try {
            conn = Conexion.conectarse();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, cliente.getIdCliente());
            stmt.setInt(2, producto.getIdProducto());
            stmt.setInt(3, venta.getCantidad());
            stmt.setDouble(4, venta.getPrecioProd());
            stmt.setDouble(5, venta.getTotalVenta());
            stmt.execute();
            
            //descontar la cantidad de productos 
            
            //descontar al cliente el Total de la venta

            return null;
        } catch (SQLException ex) {
            return ex.getMessage();
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
    }
//Este metodo ya esta finalizado
    //Metodo para modificar ventas  

    public String modificarVenta(Ventas venta) {
        Connection conn = null;
        PreparedStatement stmt = null;
        Cliente cliente = venta.getCliente();
        Productos producto = venta.getProductos();
        
        //obtener los valores anteriores de la venta
        Ventas ventaAnt = encontrar(venta);
        
        //obtener la diferencia de cantidad de las ventas, para actualizar la cantidad de producto
        int difCant = ventaAnt.getCantidad() - venta.getCantidad();
        
        
        //voy a buscar el prducto para obtener su precio
        Productos producto2 = new ProductoDao().encontrar(producto);
        venta.setPrecioProd(producto2.getPrecio());
        venta.setTotalVenta(producto2.getPrecio() * venta.getCantidad());
        
        //obtener la diferencia de dinero para actulizar el saldo del usuario.
        double difTotal = ventaAnt.getTotalVenta()- venta.getTotalVenta();

        //valido que exista la cantidad de producto solicitada
        if (venta.getCantidad() > producto2.getCantidad()+difCant) {
            return "Solamente tenemos " + (producto2.getCantidad()-difCant) + " " + producto2.getProducto() + " Disponibles.";
        }
        
        //Buscar un cliente para obtener su saldo
        Cliente cliente2 = new ClienteDao().encontrar(cliente);
        //validar que el cliente tenga los fondos suficientes.
        if(cliente2.getSaldo()+difTotal < venta.getTotalVenta()+difTotal){
            return "El saldo del cliente no es suficiente para realizar la venta. Solamente posee: $"+(cliente.getSaldo()+difTotal);
        }
        //Realizamos el procedimiento
        try {
            conn = Conexion.conectarse();
            stmt = conn.prepareStatement(SQL_UPDATE);
            
            stmt.setInt(1, cliente.getIdCliente());
            stmt.setInt(2, producto.getIdProducto());
            stmt.setInt(3, venta.getCantidad());
            stmt.setDouble(4, venta.getPrecioProd());
            stmt.setDouble(5, venta.getTotalVenta());
            stmt.setInt(6, venta.getIdVenta());
            
            stmt.execute();

            //Actualizar el saldo del cliente
            
            //Actualizar la cantidad de producto
            return null;
        } catch (SQLException ex) {
            return ex.getMessage();
        }finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
    }

    public boolean eliminarVenta(Ventas ventas) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = Conexion.conectarse();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, ventas.getIdVenta());
            stmt.execute();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return false;
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
    }
}

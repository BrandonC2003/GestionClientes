package com.utec.datos;

import com.utec.modelo.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao {
    // Definimos variables donde almacenaremos los query para realizar CRUD
    private static final String SQL_SELECT = "SELECT IdCliente, Nombre, Apellido, Email, Telefono, Saldo FROM Cliente";

    private static final String SQL_SELECT_BY_ID = "SELECT IdCliente, Nombre, Apellido, Email, Telefono, Saldo FROM Cliente WHERE IdCliente = ?";

    private static final String SQL_INSERT = "INSERT INTO Cliente(Nombre, Apellido, Email, Telefono, Saldo) VALUES(?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE = "UPDATE Cliente SET Nombre=?, Apellido=?, Email=?, Telefono=?, Saldo=? WHERE IdCliente=?";

    private static final String SQL_DELETE = "DELETE FROM Cliente WHERE IdCliente = ?";

    // Definimos el método para mostrar todos los clientes
    public List<Cliente> listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente cliente = null;
        List<Cliente> clientes = new ArrayList<>();

        try {
            conn = Conexion.conectarse();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idCliente = rs.getInt("IdCliente");
                String nombre = rs.getString("Nombre");
                String apellido = rs.getString("Apellido");
                String email = rs.getString("Email");
                String telefono = rs.getString("Telefono");
                float saldo = rs.getFloat("Saldo");

                cliente = new Cliente(idCliente, nombre, apellido, email, telefono, saldo);
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return clientes;
    }

    // Método para buscar un cliente en particular
    public Cliente encontrar(Cliente cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.conectarse();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, cliente.getIdCliente());
            rs = stmt.executeQuery();
            //rs.absolute(1);//nos posicionamos en el primer registro devuelto
            rs.next();

            String nombre = rs.getString("Nombres");
            String apellido = rs.getString("Apellidos");
            String email = rs.getString("Email");
            String telefono = rs.getString("Telefono");
            float saldo = rs.getFloat("Saldo");

            cliente.setNombres(nombre);
            cliente.setApellidos(apellido);
            cliente.setEmail(email);
            cliente.setTelefono(telefono);
            cliente.setSaldo(saldo);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return cliente;
    }
}

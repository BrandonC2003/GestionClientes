package com.utec.datos;

import com.utec.modelo.Cliente;
import java.util.List;

public class TestConexion {
    public static void main(String[] args) {
        List<Cliente> clientes = new ClienteDao().listar();
        System.out.println("clientes = " + clientes);
    }
}

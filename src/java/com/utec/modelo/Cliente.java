package com.utec.modelo;

public class Cliente {
    // atributos
    private int idCliente;
    private String nombres;
    private String apellidos;
    private String email;
    private String telefono;
    private double saldo;

    // constructores
    public Cliente() {
    }

    public Cliente(int idCliente, String nombres, String apellidos, String email, String telefono, double saldo) {
        this.idCliente = idCliente;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.telefono = telefono;
        this.saldo = saldo;
    }

    // getters y setters
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
    // Otros métodos
    @Override
    public String toString() {
        return "Cliente{" + "idCliente=" + idCliente + ", nombres=" + nombres + ", apellidos=" + apellidos + ", email=" + email + ", telefono=" + telefono + ", saldo=" + saldo + '}';
    }

}

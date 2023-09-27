/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.utec.controlador;

import com.utec.datos.ClienteDao;
import com.utec.datos.ProductoDao;
import com.utec.datos.VentasDao;
import com.utec.modelo.Ventas;
import com.utec.modelo.Cliente;
import com.utec.modelo.Productos;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author brand
 */
@WebServlet(name = "SvVentas", urlPatterns = {"/SvVentas"})
public class SvVentas extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        
        
        //obtener la lista de los clientes para que el usuario agregue a la venta
        List<Cliente> cliente = new ClienteDao().listar();
        request.setAttribute("cliente",cliente);
        
        //obtener la lista de los productos para que el usuario seleccione cual vender
        List<Productos> producto = new ProductoDao().listar();
        request.setAttribute("producto",producto);
        
        //mensaje de error
        String errorMessage = (String) request.getParameter("error");
        request.setAttribute("errorMessage",errorMessage);
        switch(accion){
            case "listar":
                 List<Ventas> ventas = new VentasDao().listar();
                request.setAttribute("ventas", ventas);
                request.getRequestDispatcher("ventas/listar.jsp").forward(request, response);
                break;
            case "agregar":
                request.getRequestDispatcher("ventas/agregar.jsp").forward(request,response);
                break;
            case "modificar":
                Ventas venta1 = new Ventas();
                venta1.setIdVenta(Integer.parseInt(request.getParameter("idVenta")));
                Ventas venta = new VentasDao().encontrar(venta1);
                request.setAttribute("venta",venta);
                request.getRequestDispatcher("ventas/modificar.jsp").forward(request,response);
                break;
            default:
                request.getRequestDispatcher("error.jsp").forward(request,response);
                break;
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        
        //variable para obtener mensajes de error.
        String errorMessage=null;
        
        //creacion de objetos a utilizar
        Ventas venta = new Ventas();
        Cliente cliente = new Cliente();
        Productos producto = new Productos();
        
        VentasDao ventaD = new VentasDao();
        
        switch(accion){
            case "agregar":
                cliente.setIdCliente(Integer.parseInt(request.getParameter("IdCliente")));
                producto.setIdProducto(Integer.parseInt(request.getParameter("IdProducto")));
                venta.setCantidad(Integer.parseInt(request.getParameter("Cantidad")));
                //pasar el cliente que realiza la compra
                venta.setCliente(cliente);
                //pasar el producto vendido
                venta.setProductos(producto);
                
                String result = ventaD.insertarVenta(venta);
                if(result==null){
                     response.sendRedirect("SvVentas?accion=listar");
                }else{
                   response.sendRedirect("SvVentas?accion=agregar&error="+result); 
                }
                break;
            case "modificar":
                venta.setIdVenta(Integer.parseInt(request.getParameter("IdVenta")));
                cliente.setIdCliente(Integer.parseInt(request.getParameter("IdCliente")));
                producto.setIdProducto(Integer.parseInt(request.getParameter("IdProducto")));
                venta.setCantidad(Integer.parseInt(request.getParameter("Cantidad")));
                //pasar el cliente que realiza la compra
                venta.setCliente(cliente);
                //pasar el producto vendido
                venta.setProductos(producto);
                
                String result2 = ventaD.modificarVenta(venta);
                if(result2==null){
                     response.sendRedirect("SvVentas?accion=listar");
                }else{
                   response.sendRedirect("SvVentas?accion=modificar&idVenta="+request.getParameter("IdVenta")+"&error="+result2); 
                }
                break;
            case "eliminar":
                venta.setIdVenta(Integer.parseInt(request.getParameter("IdVenta")));
                if(ventaD.eliminarVenta(venta)){
                    response.sendRedirect("SvVentas?accion=listar");
                }
                break;
            default:
                request.getRequestDispatcher("error.jsp").forward(request,response);
                break;
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

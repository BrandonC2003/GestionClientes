/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.utec.controlador;

import com.utec.datos.ClienteDao;
import com.utec.datos.VentasDao;
import com.utec.modelo.Ventas;
import com.utec.modelo.Cliente;
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

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        //lista de clientes para que el usuario seleccione el cliente de la venta
        List<Cliente> cliente = new ClienteDao().listar();
        request.setAttribute("cliente",cliente);
        switch(accion){
            case "listar":
                 List<Ventas> clientes = new VentasDao().listar();
                request.setAttribute("ventas", clientes);
                request.getRequestDispatcher("ventas/listar.jsp").forward(request, response);
                break;
            case "agregar":
                request.getRequestDispatcher("ventas/agregar.jsp").forward(request,response);
                break;
            case "modificar":
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
        
        switch(accion){
            case "agregar":
                
                response.sendRedirect("SvVentas?accion=listar");
                break;
            case "modificar":
                request.getRequestDispatcher("ventas/listar.jsp").forward(request,response);
                break;
            case "eliminar":
                Ventas venta = new Ventas();
                venta.setIdVenta(Integer.parseInt(request.getParameter("IdVenta")));
                VentasDao ventaD = new VentasDao();
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

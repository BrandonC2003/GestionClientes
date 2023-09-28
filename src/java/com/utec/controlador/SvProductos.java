/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.utec.controlador;

import com.utec.datos.ProductoDao;
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
 * @author Cristian De Paz
 */
@WebServlet(name = "SvProductos", urlPatterns = {"/SvProductos"})
public class SvProductos extends HttpServlet {

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
        Productos product = new Productos();
        
        
        switch(accion){
            case "listar":
                List<Productos> producto = new ProductoDao().listar();
                request.setAttribute("productos", producto);
                request.getRequestDispatcher("productos/listar.jsp").forward(request, response);
                break;
            case "agregar":
                request.getRequestDispatcher("productos/agregar.jsp").forward(request,response);
                break;
            case "modificar":
                product.setIdProducto(Integer.parseInt(request.getParameter("idProducto")));
                Productos prod = new ProductoDao().encontrar(product);
                request.setAttribute("producto",prod);
                request.getRequestDispatcher("productos/modificar.jsp").forward(request,response);
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
         String errorMessage=null;
         Productos producto = new Productos();
         ProductoDao prodDao = new ProductoDao();
        switch(accion){
            case "agregar":     
                producto.setProducto(request.getParameter("Producto"));
                producto.setTipo(request.getParameter("Tipo"));
                producto.setCantidad(Integer.parseInt(request.getParameter("Cantidad")));
                producto.setPrecio(Double.parseDouble(request.getParameter("Precio")));
                errorMessage = prodDao.insertarProducto(producto);
                if(errorMessage==null){
                     response.sendRedirect("SvProductos?accion=listar");
                }
                break;
            case "modificar":
                producto.setIdProducto(Integer.parseInt(request.getParameter("IdProducto")));
                producto.setProducto(request.getParameter("Producto"));
                producto.setTipo(request.getParameter("Tipo"));
                producto.setCantidad(Integer.parseInt(request.getParameter("Cantidad")));
                producto.setPrecio(Double.parseDouble(request.getParameter("Precio")));
                errorMessage = prodDao.modificarProducto(producto);
                if(errorMessage==null){
                     response.sendRedirect("SvProductos?accion=listar");
                }
                break;
            case "eliminar":
                producto.setIdProducto(Integer.parseInt(request.getParameter("IdProducto")));
                if(prodDao.eliminarProducto(producto)){
                    response.sendRedirect("SvProductos?accion=listar");
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

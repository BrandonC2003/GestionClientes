package com.utec.controlador;

import com.utec.datos.ClienteDao;
import com.utec.modelo.Cliente;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvClientes", urlPatterns = {"/SvClientes"})
public class SvClientes extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       String accion = request.getParameter("accion");
        
        switch(accion){
            case "listar":
                 List<Cliente> clientes = new ClienteDao().listar();
                request.setAttribute("Cliente", clientes);
                request.getRequestDispatcher("clientes/listar.jsp").forward(request, response);
                break;
            case "agregar":
                request.getRequestDispatcher("clientes/agregar.jsp").forward(request,response);
                break;
            case "modificar":
                request.getRequestDispatcher("clientes/modificar.jsp").forward(request,response);
                break;
            default:
                request.getRequestDispatcher("error.jsp").forward(request,response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
             String accion = request.getParameter("accion");
        
        switch(accion){
            case "agregar":
                
                response.sendRedirect("SvClientes?accion=listar");
                break;
            case "modificar":
                request.getRequestDispatcher("clientes/listar.jsp").forward(request,response);
                break;
            default:
                request.getRequestDispatcher("error.jsp").forward(request,response);
                break;
        }
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

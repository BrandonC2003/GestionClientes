<%-- 
    Document   : listar
    Created on : 25 sep. 2023, 19:39:01
    Author     : Cristian De Paz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ventas</title>

        <!--bootstrap-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

    </head>
    <body>
        <nav class="navbar bg-dark navbar-expand-lg" dada-bs-theme="dark">
            <div class="container-fluid">
                <a class="navbar-brand text-white" href="${pageContext.request.contextPath}/SvClientes">Usuarios</a>
                <button class="navbar-toggler bg-white" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link text-white" href="#">Productos</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-white" href="${pageContext.request.contextPath}/SvVentas?accion=listar">productos</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <article class="container mt-5">
            <div class="card">
                <div class="card-header justify-content-between d-flex">
                    <h3 class="card-tile">Productos</h3>
                    <a class="btn btn-success" href="${pageContext.request.contextPath}/SvVentas?accion=agregar">Agregar Productos</a>
                </div>
                <div class="card-body">
                    <table class="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>IdProducto</th>
                                <th>Cliente</th>
                                <th>Producto</th>
                                <th>Tipo</th>
                                <th>Cantidad</th>
                                <th>Precio</th>    
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="productos" items="${productos}">
                                <tr>
                                    <td>${productos.idProducto}</td>
                                    <td>${productos.cliente.nombres} ${productos.cliente.apellidos}</td>
                                    <td>${productos.productos.producto}</td>
                                    <td>${productos.cantidad}</td>
                                    <td>${productos.tipo}</td>
                                    <td>${productos.precio}</td>
                                    <td></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </article>
    </body>
</html>

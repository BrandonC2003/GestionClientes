<%-- 
    Document   : agregar
    Created on : 25 sep. 2023, 13:09:59
    Author     : brand
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar venta</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script><!-- comment -->
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
                            <a class="nav-link text-white" href="${pageContext.request.contextPath}/SvVentas?accion=listar">Ventas</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <article class="container mt-5">
            
            <form action="SvVentas?accion=agregar" method="POST">
                <div class="mb-5">
                    <label class="form-label" for="IdCliente">Cliente</label>
                    <select name="IdCliente" class="form-select" id="IdCliente">
                        <option>-Seleccionar-</option>
                    </select>
                    <span class="text-danger"></span>
                </div>
                <div class="mb-5">
                    <label class="form-label" for="IdProducto">Producto</label>
                    <select name="IdProducto" class="form-select" id="IdProducto">
                        <option>-Seleccionar-</option>
                    </select>
                    <span class="text-danger"></span>
                </div>
                <div class="mb-5">
                    <label class="form-label" for="Cantidad">Cantidad</label>
                    <input type="number" class="form-control" name="Cantidad" id="Cantidad">
                    <span class="text-danger"></span>
                </div>
                <div class="mb-5">
                    <a class="btn btn-warning" href="${pageContext.request.contextPath}/SvVentas?accion=listar">Cancelar</a>
                    <input type="submit" class="btn btn-dark" value="Realizar Venta">
                </div>
            </form>
            </form>
            </form>
            
        </article>
    </body>
</html>
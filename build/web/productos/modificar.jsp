<%-- 
    Document   : agregar
    Created on : 25 sep. 2023, 19:24:00
    Author     : Cristian De Paz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>modificar producto</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script><!-- comment -->

        <!--SweetAlert-->
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

        <!--jquery-->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    </head>
    <body>
        <nav class="navbar bg-dark navbar-expand-lg" dada-bs-theme="dark">
            <div class="container-fluid">
                <a class="navbar-brand text-white" href="${pageContext.request.contextPath}/SvClientes?accion=listar">Clientes</a>
                <button class="navbar-toggler bg-white" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link text-white" href="${pageContext.request.contextPath}/SvProductos?accion=listar">Productos</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-white" href="${pageContext.request.contextPath}/SvVentas?accion=listar">Ventas</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <article class="container mt-5">

            <form action="SvProductos?accion=modificar" method="POST" id="formModificar">
                 <c:set var="producto" value="${requestScope.producto}" />
                 <input type="hidden" name="IdProducto" value="${producto.idProducto}">
                <div class="mb-5">
                    <label class="form-label" for="Producto">Producto</label>
                    <input type="text" class="form-control" name="Producto" id="Producto" required value="${producto.producto}">
                    <span class="text-danger" id="productoVal"></span>
                </div>
                <div class="mb-5">
                    <label class="form-label" for="Tipo">Tipo</label>
                    <input type="text" class="form-control" name="Tipo" id="Tipo" required value="${producto.tipo}">
                    <span class="text-danger" id="tipoVal"></span>
                </div>
                <div class="mb-5">
                    <label class="form-label" for="Cantidad">Cantidad</label>
                    <input type="number" class="form-control" name="Cantidad" id="Cantidad" value="${producto.cantidad}">
                    <span class="text-danger" id="cantidadVal"></span>
                </div>
                <div class="mb-5">
                    <label class="form-label" for="Precio">Precio</label>
                    <input type="number" class="form-control" name="Precio" id="Precio" step="0.01" value="${producto.precio}">
                    <span class="text-danger" id="precioVal"></span>
                </div>
                <div class="mb-5">
                    <a class="btn btn-warning" href="${pageContext.request.contextPath}/SvProductos?accion=listar">Cancelar</a>
                    <input type="submit" class="btn btn-dark" value="Modificar Producto">
                </div>
            </form>

        </article>                

        <script>
            $(document).ready(function () {
                $("#formModificar").submit(function (e) {
                    e.preventDefault();
                    $("#productoVal").text("");
                    $("#tipoVal").text("");
                    $("#cantidadVal").text("");
                    $("#precioVal").text("");
                    let cantidad = $("#Cantidad").val();
                    let precio = $("#Precio").val();

                    //validar que la cantidad sea entero positivo
                    if (!esEntero(cantidad)) {
                        $("#cantidadVal").text("La cantidad debe ser un entero positivo.");
                        return false;
                    }
                    
                    if(precio ==="" || precio <= 0){
                        $("#precioVal").text("El precio tiene que ser un valor positivo.");
                        return false;
                    }

                    //validar que el precio sea un valor positivo

                    this.submit();
                });

                //mostrar las validaciones de la base de datos
                const error = '<c:out value="${requestScope.errorMessage}" />';

                if (error) {
                    Swal.fire({
                        title: 'Error',
                        text: error,
                        icon: 'error',
                        confirmButtonColor: '#3085d6',
                        confirmButtonText: 'Aceptar'
                    });
                }
                //metodo para validar si un valor es entero y positivo
                function esEntero(valor) {
                    var patron = /^[1-9]\d*$/;
                    return patron.test(valor);
                }

            });
        </script>              
    </body>
</html>

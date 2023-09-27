<%-- 
    Document   : Modificar
    Created on : 25 sep. 2023, 13:10:11
    Author     : brand
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modificar Venta</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

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

            <form action="SvVentas?accion=modificar" method="POST" id="formModificar">
                <c:set var="venta" value="${requestScope.venta}" />
                <input type="hidden" name="IdVenta" value="${venta.idVenta}">
                <div class="mb-5">
                    <label class="form-label" for="IdCliente">Cliente</label>
                    <select name="IdCliente" class="form-select" id="IdCliente">
                        <option disabled selected>-Seleccionar-</option>
                        <c:forEach var="cliente" items="${cliente}">
                            <c:choose>
                                <c:when test="${venta.cliente.idCliente eq cliente.idCliente}">
                                    <option value="${cliente.idCliente}" selected>${cliente.nombres} ${cliente.apellidos}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${cliente.idCliente}">${cliente.nombres} ${cliente.apellidos}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach> 
                    </select>
                    <span class="text-danger" id="idClienteVal"></span>
                </div>
                <div class="mb-5">
                    <label class="form-label" for="IdProducto">Producto</label>
                    <select name="IdProducto" class="form-select" id="IdProducto">
                        <option disabled selected>-Seleccionar-</option>

                        <c:forEach var="producto" items="${producto}">
                            <c:choose>
                                <c:when test="${venta.productos.idProducto eq producto.idProducto}">
                                    <option value="${producto.idProducto}" selected>${producto.producto}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${producto.idProducto}">${producto.producto}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach> 
                    </select>
                    <span class="text-danger" id="idProductoVal"></span>
                </div>
                <div class="mb-5">
                    <label class="form-label" for="Cantidad">Cantidad</label>
                    <input type="number" class="form-control" name="Cantidad" id="Cantidad" value="${venta.cantidad}">
                    <span class="text-danger" id="cantidadVal"></span>

                </div>
                <div class="mb-5">
                    <a class="btn btn-warning" href="${pageContext.request.contextPath}/SvVentas?accion=listar">Cancelar</a>
                    <input type="submit" class="btn btn-dark" value="modificar Venta">
                </div>
            </form>

        </article>
        <script>
            $(document).ready(function () {
                $("#formModificar").submit(function (e) {
                    e.preventDefault();
                    $("#idClienteVal").text("");
                    $("#idProductoVal").text("");
                    $("#cantidadVal").text("");
                    let idCliente = $("#IdCliente").val();
                    let idProducto = $("#IdProducto").val();
                    let cantidad = $("#Cantidad").val();

                    if (idCliente === null) {
                        $("#idClienteVal").text("Tienes que seleccionar un cliente.");
                        return false;
                    }

                    if (idProducto === null) {
                        $("#idProductoVal").text("Tienes que seleccioanr un producto");
                        return false;
                    }

                    if (!esEntero(cantidad)) {
                        $("#cantidadVal").text("La cantidad debe ser un entero positivo.");
                        console.log(cantidad);
                        return false;
                    }
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

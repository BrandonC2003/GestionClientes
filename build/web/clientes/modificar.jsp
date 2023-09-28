<%-- 
    Document   : modificar
    Created on : 25 sep. 2023, 23:51:53
    Author     : brand
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modificar Cliente</title>
        

        <!--Bootstrap-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script><!-- comment -->

        <!--Bootstrap icons-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">

        <!--jquery-->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        
        <!--SweetAlert-->
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

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
            <c:set var="cliente" value="${requestScope.client}" />

            <form action="SvClientes?accion=modificar" method="POST" id="formAgregar">
                <input type="hidden" value="${cliente.idCliente}" name="IdCliente">
                <div class="mb-5">
                    <label class="form-label" for="IdCliente">Cliente</label>
                    <br>
                    <br>
                    <label for="nombre">Nombre:</label><br>
                        <input type="text" id="nombre" name="nombre" required value="${cliente.nombres}"><br><br>
                        <label for="apellido">Apellido:</label><br>
                        <input type="text" id="apellido" name="apellido" required value="${cliente.apellidos}"><br><br>
                        <label for="email">Corrreo:</label><br>
                        <input type="email" id="email" name="email" required value="${cliente.email}"><br><br>
                        <label for="telefono">Telefono:</label><br>
                        <input type="text" id="telefono" name="telefono" required value="${cliente.telefono}"><br><br>
                        <label for="saldo">Saldo:</label><br>
                        <input type="number" id="saldo" name="saldo" step="0.01" value="${cliente.saldo}">
                        <br>
                        <span class="text-danger" id="saldoVal"></span>
                        <br>
                        <input type="submit" class="btn btn-dark" value="Modificar Cliente">
                    <span class="text-danger" id="idClienteVal"></span>
                </div>
            </form>
        </article>

        <script>
            $(document).ready(function () {
                $("#formAgregar").submit(function (e) {
                    e.preventDefault();
                    $("#saldoVal").text("");
                    let saldo = $("#saldo").val();

                    if (saldo ==="" ||saldo <= 0) {
                        $("#saldoVal").text("El saldo tiene que ser un valor positivo.");
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
            });
        </script>    
    </body>
</html>
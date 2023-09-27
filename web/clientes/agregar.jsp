<%-- 
    Document   : agregar
    Created on : 25 sep. 2023, 23:51:44
    Author     : brand
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar Cliente</title>

        <!--Bootstrap-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script><!-- comment -->

        <!--Bootstrap icons-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">

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
            </div>
        </nav>
        <article class="container mt-5">

            <form action="SvClientes?accion=agregar" method="POST" id="formAgregar">
                <div class="mb-5">
                    <label class="form-label" for="IdCliente">Cliente</label>
                    <br>
                    <br>
                    <form action="TuServlet" method="post">
                        <label for="nombre">Nombre:</label><br>
                        <input type="text" id="nombre" name="nombre" value="${cliente.nombres}"><br><br>
                        <label for="apellido">Apellido:</label><br>
                        <input type="text" id="apellido" name="apellido" value="${cliente.apellidos}"><br><br>
                        <label for="email">Corrreo:</label><br>
                        <input type="text" id="email" name="email" value="${cliente.email}"><br><br>
                        <label for="telefono">Telefono:</label><br>
                        <input type="text" id="telefono" name="telefono" value="${cliente.telefono}"><br><br>
                        <label for="saldo">Saldo:</label><br>
                        <input type="text" id="saldo" name="saldo" value="${cliente.saldo}"><br><br>
                        <input type="submit" class="btn btn-dark" value="Agregar Cliente">
                    </form>
                    <span class="text-danger" id="idClienteVal"></span>
                </div>
            </form>
        </article>
        <script>
            $(document).ready(function () {
                $("#formAgregar").submit(function (e) {
                    e.preventDefault();
                    $("#idClienteVal").text("");
                    $("#nombreVal").text("");
                    $("#apellidoVal").text("");
                    $("#emailVal").text("");
                    $("#telefonoVal").text("");
                    $("#saldoVal").text("");
                    let idCliente = $("#IdCliente").val();
                    let nombres = $("#Nombre").val();
                    let apellidos = $("#N}Apellido").val();
                    let email = $("#Email").val();
                    let telefono = $("#Telefono").val();
                    let saldo = $("#Saldo").val();
                    if (idCliente === null)
                    if (nombres === not null)
                    if (apellidos === not null)
                    if (telefono === not null)
                    if (telefono === not null)
                    if (saldo === not null)
                        this.submit();
                });
            });
        </script>    
    </body>
</html>

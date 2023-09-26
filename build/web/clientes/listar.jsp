<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Clientes</title>

        <!--bootstrap-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

        <!--Bootstrap icons-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
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
            <div class="card">
                <div class="card-header justify-content-between d-flex">
                    <h3 class="card-tile">Clientes</h3>
                    <a class="btn btn-success" href="${pageContext.request.contextPath}/SvClientes?accion=agregar"><i class="bi bi-plus-circle"></i> Agregar Cliente</a>
                </div>
                <div class="card-body">
                    <table class="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>Cliente</th>
                                <th>Nombre</th>
                                <th>Apellido</th>
                                <th>Email</th>
                                <th>Telefono</th>
                                <th>Saldo</th>
                                <th>Opciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="clientes" items="${clientes}">
                                <tr>
                                    <td>${clientes.idCliente}</td>
                                    <td>${clientes.nombres}</td>
                                   <td>${clientes.apellidos}</td>
                                   <td>${clientes.email}</td>
                                   <td>${clientes.telefono}</td>
                                   <td>${clientes.saldo}</td>
                                   <td>
                                        <a class="btn btn-warning" href="${pageContext.request.contextPath}/SvClientes?accion=modificar&idCliente=${clientes.idCliente}"><i class="bi bi-pencil-square"></i></a>
                                        <a class="btn btn-danger"><i class="bi bi-trash"></i></a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </article>
    </body>
</html>
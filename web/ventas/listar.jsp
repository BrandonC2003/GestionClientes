<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ventas</title>

        <!--bootstrap-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

        <!--Bootstrap icons-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">

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
            <div class="card">
                <div class="card-header justify-content-between d-flex">
                    <h3 class="card-tile">Ventas</h3>
                    <a class="btn btn-success" href="${pageContext.request.contextPath}/SvVentas?accion=agregar"><i class="bi bi-plus-circle"></i> Agregar Venta</a>
                </div>
                <div class="card-body">
                    <table class="table table-bordered table-striped">
                        <thead>
                            <tr>
                                <th>Venta</th>
                                <th>Cliente</th>
                                <th>Producto</th>
                                <th>Cantidad</th>
                                <th>Precio Unitario</th>
                                <th>Total</th>
                                <th>Fecha</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="ventas"  items="${ventas}">
                                <tr>
                                    <td>${ventas.idVenta}</td>
                                    <td>${ventas.cliente.nombres} ${ventas.cliente.apellidos}</td>
                                    <td>${ventas.productos.producto}</td>
                                    <td>${ventas.cantidad}</td>
                                    <td><fmt:formatNumber value="${ventas.precioProd}" type="currency" currencySymbol="$" /></td>
                                    <td><fmt:formatNumber value="${ventas.totalVenta}" type="currency" currencySymbol="$" /></td>
                                    <td>${ventas.fecha}</td>
                                    <td>
                                        <a class="btn btn-warning" href="${pageContext.request.contextPath}/SvVentas?accion=modificar&idVenta=${ventas.idVenta}"><i class="bi bi-pencil-square"></i></a>
                                        <button class="btn btn-danger btn-eliminar" value="${ventas.idVenta}"><i class="bi bi-trash"></i></button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </article>
        <form action="SvVentas?accion=eliminar" method="POST" hidden id="formEliminar">
            <input type="hidden" name="IdVenta" id="idVenta">
        </form>
        <script>
            $(document).ready(function () {

                $(".btn-eliminar").click(function (e) {
                    Swal.fire({
                        title: '¿Estás seguro?',
                        text: 'Esta acción eliminará la venta permanentemente.',
                        icon: 'warning',
                        showCancelButton: true,
                        confirmButtonColor: '#3085d6',
                        cancelButtonColor: '#d33',
                        confirmButtonText: 'Sí, eliminar',
                        cancelButtonText: 'Cancelar'
                    }).then((result) => {
                        if (result.isConfirmed) {
                            let idVenta = $(this).val();
                            $("#idVenta").val(idVenta);
                            $("#formEliminar").submit();
                        }
                    });
                });

            });

        </script>
    </body>
</html>

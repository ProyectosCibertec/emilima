<%@ page session="true" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Documentos Emilima | Login</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.3/font/bootstrap-icons.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/styles.css">
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/Document.js"
	type="text/javascript"></script>
<script>
	let contextPath = "${pageContext.request.contextPath}"
</script>
<script src="${pageContext.request.contextPath}/resources/js/scripts.js"
	type="text/javascript"></script>
</head>
<body class="vh-100">
	<div class="container-fluid h-100">
		<div class="row h-100">
			<div
				class="d-flex flex-column flex-shrink-0 p-3 text-dark bg-white shadow-sm h-100 col-md-3">
				<a href="${pageContext.request.contextPath}/"
					class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
					<img alt="Emilima logo"
					src="${pageContext.request.contextPath}/resources/img/logo-emilima-color.png"
					class="w-100">
				</a>
				<hr>
				<ul class="nav nav-pills flex-column mb-auto">
					<li class="nav-item"><a
						href="${pageContext.request.contextPath}/" class="nav-link text-dark"
						aria-current="page"> <svg class="bi me-2" width="16"
								height="16">
						<use xlink:href="#home" /></svg> Inicio
					</a></li>
					<li><a
						href="${pageContext.request.contextPath}/documentos/listar"
						class="nav-link text-dark"> <svg class="bi me-2" width="16"
								height="16">
						<use xlink:href="#speedometer2" /></svg> Documentos
					</a></li>
					<li><a
						href="${pageContext.request.contextPath}/solicitudes/listar"
						class="nav-link text-dark"> <svg class="bi me-2" width="16"
								height="16">
						<use xlink:href="#table" /></svg> Solicitudes
					</a></li>
					<li><a
						href="${pageContext.request.contextPath}/usuarios/listar"
						class="nav-link text-dark"> <svg class="bi me-2" width="16"
								height="16">
						<use xlink:href="#grid" /></svg> Usuarios
					</a></li>
				</ul>
				<hr>
				<div class="dropdown">
					<button
						class="d-flex align-items-center text-dark text-decoration-none dropdown-toggle"
						id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
						<img src="https://github.com/mdo.png" alt="" width="32"
							height="32" class="rounded-circle me-2"> <strong>${ sessionScope.loginedUser.username }</strong>
					</button>
					<ul class="dropdown-menu dropdown-menu-dark text-small shadow"
						aria-labelledby="dropdownUser1">
						<li><button class="dropdown-item">Configuración</button></li>
						<li><hr class="dropdown-divider"></li>
						<li><button class="dropdown-item" id="logout-button">Salir</button></li>
					</ul>
				</div>
			</div>
			<div class="col-md-9 py-5 main-content d-flex flex-column align-items-center justify-content-center">
				<h1 class="text-center">ERROR 401 UNAUTHORIZED</h1>
				<p>No estás autorizado para acceder</p>
			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
</body>
</html>
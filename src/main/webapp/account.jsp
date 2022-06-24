<%@ page session="true" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Documentos Emilima | Usuarios</title>
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
						href="${pageContext.request.contextPath}/"
						class="nav-link text-dark" aria-current="page"> <svg
								class="bi me-2" width="16" height="16">
						<use xlink:href="#index" /></svg> Inicio
					</a></li>
					<li><a
						href="${pageContext.request.contextPath}/documentos/listar"
						class="nav-link text-dark"> <svg class="bi me-2" width="16"
								height="16">
						<use xlink:href="#documents" /></svg> Documentos
					</a></li>
					<li><a
						href="${pageContext.request.contextPath}/solicitudes/listar"
						class="nav-link text-dark"> <svg class="bi me-2" width="16"
								height="16">
						<use xlink:href="#requests" /></svg> Solicitudes
					</a></li>
					<li><a
						href="${pageContext.request.contextPath}/usuarios/listar"
						class="nav-link active"> <svg class="bi me-2" width="16"
								height="16">
						<use xlink:href="#users" /></svg> Usuarios
					</a></li>
				</ul>
				<hr>
				<div class="dropdown">
					<button
						class="d-flex align-items-center text-dark text-decoration-none dropdown-toggle"
						id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
						<img alt="" width="32" height="32" class="rounded-circle me-2"
							id="user-photo"> <strong>${ sessionScope.loginedUser.username }</strong>
					</button>
					<ul class="dropdown-menu dropdown-menu-dark text-small shadow"
						aria-labelledby="dropdownUser1">
						<li><a class="dropdown-item"
							href="${pageContext.request.contextPath}/cuenta">Configuración</a></li>
						<li><hr class="dropdown-divider"></li>
						<li><button class="dropdown-item" id="logout-button">Salir</button></li>
					</ul>
				</div>
			</div>
			<div class="col-md-9 py-5 main-content h-100">
				<div class="d-flex justify-content-between align-items-center">
					<h1>Configuración</h1>
				</div>
				<div class="card col-md-5">
					<div class="card-body">
						<form id="edit-user-form-data" method="POST" action="/cuenta"
							enctype="multipart/form-data">
							<div class="mb-3">
								<label for="edit-user-name" class="form-label">Nombre de
									usuario</label> <input type="text" class="form-control"
									id="edit-user-name" name="user-name" required readonly
									value="${ sessionScope.loginedUser.username }">
							</div>
							<div class="mb-3">
								<label for="edit-user-password" class="form-label">Contraseña</label>
								<input type="password" class="form-control"
									id="edit-user-password" name="user-password" required>
							</div>
							<div class="mb-3">
								<label for="edit-user-email" class="form-label">Email</label> <input
									type="email" class="form-control" name="user-email"
									id="edit-user-email" required>
							</div>
							<div class="mb-3">
								<label for="edit-user-photo" class="form-label">Foto de
									perfil</label> <input class="form-control" type="file"
									id="edit-user-photo" name="file" accept="image/*"
									disabled>
							</div>
							<div class="mb-3 form-check">
								<input type="checkbox" class="form-check-input"
									id="edit-user-photo-check"> <label
									class="form-check-label" for="edit-user-photo-check">Cambiar
									foto</label>
							</div>
							<button type="submit" class="btn btn-primary">Actualizar
								datos</button>
						</form>
					</div>
				</div>

			</div>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
</body>
</html>
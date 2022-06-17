<%@ page session="true" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Documentos Emilima | Solicitudes</title>
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
						class="nav-link active"> <svg class="bi me-2" width="16"
								height="16">
						<use xlink:href="#requests" /></svg> Solicitudes
					</a></li>
					<li><a
						href="${pageContext.request.contextPath}/usuarios/listar"
						class="nav-link text-dark"> <svg class="bi me-2" width="16"
								height="16">
						<use xlink:href="#users" /></svg> Usuarios
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
			<div class="col-md-9 py-5 main-content h-100">
				<div class="d-flex justify-content-between align-items-center">
					<h1>Solicitudes</h1>
					<button type="button" class="btn btn-primary"
						data-bs-toggle="modal" data-bs-target="#register-request-modal">Registrar
						solicitud</button>
				</div>

				<form class="my-4">
					<div>
						<input type="text" class="form-control w-100"
							id="search-request-name" placeholder="Buscar solicitudes">
					</div>
				</form>
				<table class="table table-striped requests-table">
					<thead>
						<tr>
							<th scope="col">Nombre</th>
							<th scope="col">Descripción</th>
							<th scope="col">Fecha de creación</th>
							<th scope="col">Estado</th>
							<th scope="col">Operaciones</th>
						</tr>
					</thead>
					<tbody id="requests-list-table-body" class="requests-table__body">
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div class="modal fade" id="register-request-modal"
		data-backdrop="static" data-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div
			class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Registro de
						solicitud</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form id="register-request-form-data">
						<div class="mb-3">
							<label for="request-name" class="form-label">Nombre</label> <input
								type="text" class="form-control" id="request-name"
								name="request-name" required>
						</div>
						<div class="mb-3">
							<label for="request-description" class="form-label">Descripción</label>
							<textarea class="form-control" id="request-description" rows="3"
								name="request-description" required></textarea>
						</div>
						<div class="mb-3">
							<label for="request-creation-date" class="form-label">Fecha
								de creación</label> <input type="date" class="form-control"
								name="request-creation-date" id="request-creation-date" readonly>
						</div>
						<div class="mb-3">
							<label for="request-user">Usuario que registra la
								solicitud</label> <input type="text" class="form-control"
								name="request-user" id="request-user" readonly
								value="${ sessionScope.loginedUser.username }" required>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Cancelar</button>
					<button type="button" class="btn btn-primary"
						id="register-request-accept-button">Registrar</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="edit-request-modal" data-backdrop="static"
		data-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div
			class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Editar
						solicitud</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form id="edit-request-form-data">
						<div class="mb-3">
							<label for="edit-request-name" class="form-label">Nombre</label> <input
								type="text" class="form-control" id="edit-request-name"
								name="request-name" required>
						</div>
						<div class="mb-3">
							<label for="edit-request-description" class="form-label">Descripción</label>
							<textarea class="form-control" id="edit-request-description"
								rows="3" name="request-description" required></textarea>
						</div>
						<div class="mb-3">
							<label for="edit-request-creation-date" class="form-label">Fecha
								de creación</label> <input type="date" class="form-control"
								name="request-creation-date" id="edit-request-creation-date"
								readonly>
						</div>
						<div class="mb-3">
							<label for="edit-request-user">Usuario que registra la
								solicitud</label> <input type="text" class="form-control"
								name="request-user" id="edit-request-user" readonly
								 required>
						</div>
						<div class="mb-3">
							<label for="edit-request-document">Documento</label> <select
								class="form-control" name="request-document"
								id="edit-request-document" required>
								<option value="" disabled selected hidden>[Elige un
									documento]</option>
							</select>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Cancelar</button>
					<button type="button" class="btn btn-primary"
						id="edit-request-accept-button">Editar</button>
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
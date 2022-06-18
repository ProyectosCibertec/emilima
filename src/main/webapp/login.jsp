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
	href="${pageContext.request.contextPath}/resources/css/styles.css">
<script src="${pageContext.request.contextPath}/resources/js/scripts.js"
	type="text/javascript"></script>
</head>
<body class="body--login">
	<div class="container h-100 d-flex align-items-center">
		<div class="row mx-0 justify-content-center">
			<h1 class="text-center text-light">Documentos Emilima</h1>
			<div class="row d-flex align-items-stretch gx-5 mt-4">
				<div class="col-md-1"></div>
				<div class="col-md-5">
					<div class="card rounded-0 text-center"
						style="background-color: #F7F7F7">
						<div class="card-body">
							<img alt="Icono Emilima"
								src="${pageContext.request.contextPath}/resources/img/logo-emilima-color.png"
								class="w-100"> <img alt="Icono Emilima"
								src="${pageContext.request.contextPath}/resources/img/documento-icono.png"
								class="img-fluid">
						</div>
					</div>
				</div>
				<div class="col-md-5">
					<div class="card rounded-0 h-100" style="background-color: #F7F7F7">
						<div class="card-header" style="background-color: #10A7A1">
							<h2 class="text-center text-light">Inicio de sesión</h2>
						</div>
						<div class="card-body">
							<form action="${pageContext.request.contextPath}/login" method="POST">
								<div class="mb-3">
									<label for="username" class="form-label">Usuario: </label> <input
										class="form-control" id="username" name="username" type="text" required/>
								</div>
								<div class="mb-3">
									<label for="password" class="form-label">Contraseña: </label> <input
										class="form-control" id="password" name="password"
										type="password" required/>
								</div>
								<div class="row mb-3">
									<div class="col-xl-5">
										<input class="form-check-input" id="rememberMe"
											name="rememberMe" type="checkbox" /> <label for="rememberMe"
											class="form-check-label">Recuérdame</label>
									</div>
									<div class="col-xl-7">
										<label><a href="#">¿Olvidaste tu contraseña?</a></label>
									</div>
								</div>

								<button type="submit" class="btn btn-primary w-100">Ingresar</button>
							</form>
						</div>
					</div>
				</div>
				<div class="col-md-1"></div>
			</div>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
</body>
</html>
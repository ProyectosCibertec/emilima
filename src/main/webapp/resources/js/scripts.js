$(document).ready(function() {
	app();
});

/**
 * Document init
 */

function app() {
	let pathnames = location.pathname.split("/");

	if (pathnames.includes("documentos")) {
		documents();
	}

	if (pathnames.includes("solicitudes")) {
		requests();
	}

	if (pathnames.includes("usuarios")) {
		users();
	}

	$("#logout-button").click(function() {
		logout();
	});
}

/**
 * Controller
 */

function documents() {
	let currentDate = new Date().toJSON().substring(0, Number.parseInt(new Date().toJSON().indexOf("T")));

	getDocumentsInView();

	$("#document-upload-date").val(currentDate);

	$("#search-document-name").keyup(function() {
		searchDocumentsInView();
	});

	$("#register-document-accept-button").click(function() {
		registerDocument();
	});
}

function requests() {

}

function users() {
	getUsersInView();
	getRolesInView();

	$("#register-user-accept-button").click(function() {
		registerUserInView();
	});

	$("#edit-user-accept-button").click(function() {
		editUserInView();
	});

	$("#search-user-name").keyup(function() {
		searchUsersInView();
	});

	$("select[name='user-rol']").focus(function() {
		getRolesInView();
	});
}

async function getDocumentsInView() {
	$("#documents-list-grid").empty();

	let documents = await getDocuments();

	showDocumentsInView(documents);
}

async function getUsersInView() {
	$("#users-list-table-body").empty();

	let users = await getUsers();

	showUsersInView(users);
}

async function getRolesInView() {
	$("select[name='user-rol']").empty();

	let roles = await getRoles();

	showRolesInView(roles);
}

async function registerUserInView() {
	let user = {
		username: $("#user-name").val(),
		password: $("#user-password").val(),
		email: $("#user-email").val(),
		roleId: $("#user-rol").val()
	};

	let result = await registerUser(user);

	if (result == 1) {
		showSuccessAlert($("#register-user-modal .modal-body"), "Usuario creado correctamente");
		$("#register-user-form-data").trigger("reset");
		getUsersInView();
	} else {
		showDangerAlert($("#register-user-modal .modal-body"), "Hubo un error al crear el usuario");
	}
}

async function editUserInView() {
	let user = {
		username: $("#edit-user-name").val(),
		password: $("#edit-user-password").val(),
		email: $("#edit-user-email").val(),
		roleId: $("#edit-user-rol").val()
	};

	let result = await editUser(user);

	if (result == 1) {
		showSuccessAlert($("#edit-user-modal .modal-body"), "Usuario editado correctamente");
		getUsersInView();
	} else {
		showDangerAlert($("#edit-user-modal .modal-body"), "Hubo un error al editar el usuario");
	}
}

/**
 * Search operation
 */

function searchUsersInView() {
	$(".users-table__body__item").filter(
		function() {
			$(this).toggle(
				$(this).children("td").eq(0).text()
					.includes($("#search-user-name").val()));
		}
	)
}

function searchDocumentsInView() {
	$(".documents-list__item").filter(
		function() {
			$(this).toggle(
				$(this).children("div").children("div").children("div").children("div.col-md-7").children("h2.documents-list__item__title").text()
					.includes($("#search-document-name").val()));
		}
	)
}

/**
 * HTML DOM generation
 */

async function showUsersInView(users) {
	let roles = await getRoles();

	users.forEach(function(user) {
		let usersRole = roles.filter(role => role.id == user.roleId)[0].name;

		$("#users-list-table-body").append(
			`
			<tr class="users-table__body__item">
							<td classs="users-table__body__item__name">${user.username}</td>
							<td>${user.email}</td>
							<td>${usersRole}</td>
							<td>
								<button class="btn btn-primary users-table__body__item__edit-button" data-bs-toggle="modal" data-bs-target="#edit-user-modal" onclick="editUserOperation('${user.username}')">Actualizar</button>
									<button class="btn btn-primary users-table__body__item__delete-button" onclick="deleteUserOperation('${user.username}')">Eliminar</button>
							</td>
						</tr>
		`);
	});
}

function showRolesInView(roles) {
	roles.forEach(function(rol) {
		$("select[name='user-rol']").append(`<option value="${rol.id}">${rol.name}</option>`);
	});
}

function showDocumentsInView(documents) {
	documents.forEach(function(document) {
		$("#documents-list-grid").append(
			`
		<div class="col-lg-6 documents-list__item">
							<div class="bg-white card shadow-sm">
								<div class="card-body">
									<div class="row align-items-center justify-content-center">
										<div class="col-md-2">Icon</div>
										<div class="col-md-7">
											<h2 class="card-title h4 documents-list__item__title">${document.name}</h2>
											<p class="card-text fw-light fst-italic">${document.description}</p>
										</div>
										<div class="col-md-3">
											<div class="row align-items-center">
												<div class="col flex-column">
													<i class="bi bi-pencil-fill documents-list__item__edit-button" style="font-size: 1.5rem;"></i>
													<i class="bi bi-x documents-list__item__delete-button" style="font-size: 1.5rem;" onclick='deleteDocumentOperation(${document.id})'></i>
												</div>
												<div class="col">
													<i class="bi bi-download documents-list__item__download-button" style="font-size: 2.5rem;"></i>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
		`);
	});
}

function showDangerAlert(element, message) {
	const container = document.createElement("div");
	let alert = `
	<div class="alert alert-danger" role="alert">
	 ${message}
	</div>
	`
	container.innerHTML = alert;
	element.prepend(container);

	setTimeout(function() {
		container.remove();
	}, 3000);
}

function showSuccessAlert(element, message) {
	const container = document.createElement("div");
	let alert = `
	<div class="alert alert-success" role="alert">
	 ${message}
	</div>
	`
	container.innerHTML = alert;
	element.prepend(container);

	setTimeout(function() {
		container.remove();
	}, 3000);
}

/**
 * Operations (HTML events)
 */

async function deleteDocumentOperation(id) {
	let documentsDeleted = await deleteDocument(id);

	getDocumentsInView();
}

async function deleteUserOperation(username) {
	let usersDeleted = await deleteUser(username);

	if (usersDeleted == 1) {
		showSuccessAlert($(".main-content"), "Usuario eliminado correctamente");
	} else {
		showDangerAlert($(".main-content"), "Hubo un error al eliminar el usuario")
	}

	getUsersInView();
}

async function editUserOperation(username) {
	let user = await getUser(username);

	$("#edit-user-name").val(user.username);
	$("#edit-user-password").val(user.password);
	$("#edit-user-email").val(user.email);
	$("#edit-user-rol").val(user.roleId);
}

/**
 * Asynchronous operations
 */

function logout() {
	let result = $.ajax({
		url: `${contextPath}/logout`, method: "POST"
	})

	location.reload();
}

function getDocuments() {
	let result = $.get(`${contextPath}/documentos/`);

	return result;
}

function deleteDocument(id) {
	let result = $.ajax({
		url: `${contextPath}/documentos/${id}`, method: "DELETE"
	});

	return result;
}

function registerUser(user) {
	let result = $.ajax({
		url: `${contextPath}/usuarios/`, method: "POST", data: {
			username: user.username,
			password: user.password,
			email: user.email,
			roleId: user.roleId
		}
	})

	return result;
}

function editUser(user) {
	let result = $.ajax({
		url: `${contextPath}/usuarios/`, method: "PUT", data: JSON.stringify({
			username: user.username,
			password: user.password,
			email: user.email,
			roleId: user.roleId
		}), contentType: "application/json"
	});

	return result;
}

function registerDocument() {
	let formData = new FormData();

	formData.append("name", $("#document-name").val());
	formData.append("description", $("#document-description").val());
	formData.append("uploadDate", $("#document-upload-date").val());
	formData.append("documentFile", $("#document-file")[0].files[0]);
	formData.append("requestId", $("#document-request-id").val());

	let result = $.ajax({
		url: `${contextPath}/documentos/`, method: "POST", data: formData, contentType: 'multipart/form-data', processData: false, contentType: false
	});

	return result;
}

function deleteUser(username) {
	let result = $.ajax({
		url: `${contextPath}/usuarios/${username}`, method: "DELETE"
	});

	return result;
}

function getUsers() {
	let result = $.get(`${contextPath}/usuarios/`);

	return result;
}

function getUser(username) {
	let result = $.get(`${contextPath}/usuarios/?username=${username}`);

	return result;
}

function getRoles() {
	let result = $.get(`${contextPath}/roles/`);

	return result;
}
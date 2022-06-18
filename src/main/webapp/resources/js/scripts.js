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
	getDocumentsInView();

	$("#search-document-name").keyup(function() {
		searchDocumentsInView();
	});

	$("#register-document-accept-button").click(function() {
		let registerDocumentForm = $("#document-form-data").get()[0];

		if (registerDocumentForm.checkValidity()) {
			registerDocumentInView();
		} else {
			registerDocumentForm.reportValidity();
		}
	});
	
	$("#edit-document-accept-button").click(function() {
		let editDocumentForm = $("#edit-document-form-data").get()[0];

		if (editDocumentForm.checkValidity()) {
			editDocumentInView();
		} else {
			editDocumentForm.reportValidity();
		}
	});
}

function requests() {
	getRequestsInView();
	getDocumentsInRequestDropdownList();

	$("#register-request-accept-button").click(function() {
		let registerRequestForm = $("#register-request-form-data").get()[0];

		if (registerRequestForm.checkValidity()) {
			registerRequestInView();
		} else {
			registerRequestForm.reportValidity();
		}
	});

	$("#edit-request-accept-button").click(function() {
		let editRequestForm = $("#edit-request-form-data").get()[0];

		if (editRequestForm.checkValidity()) {
			editRequestInView();
		} else {
			editRequestForm.reportValidity();
		}
	});

	$("#search-request-name").keyup(function() {
		searchRequestsInView();
	});
}

function users() {
	getUsersInView();
	getRolesInView();

	$("#register-user-accept-button").click(function() {
		let registerUserForm = $("#register-user-form-data").get()[0];

		if (registerUserForm.checkValidity()) {
			registerUserInView();
		} else {
			registerUserForm.reportValidity();
		}
	});

	$("#edit-user-accept-button").click(function() {
		let editUserForm = $("#edit-user-form-data").get()[0];

		if (editUserForm.checkValidity()) {
			editUserInView();
		} else {
			editUserForm.reportValidity();
		}
	});

	$("#search-user-name").keyup(function() {
		searchUsersInView();
	});
}

async function registerDocumentInView() {
	let formData = new FormData();

	formData.append("name", $("#document-name").val());
	formData.append("description", $("#document-description").val());
	formData.append("uploadDate", $("#document-upload-date").val());
	formData.append("documentFile", $("#document-file")[0].files[0]);

	let result = await registerDocument(formData);

	if (result == 1) {
		showSuccessAlert($("#register-document-modal .modal-body"), "Documento creado correctamente");
		$("#document-form-data").trigger("reset");
		getDocumentsInView()
	} else {
		showDangerAlert($("#register-document-modal .modal-body"), "Hubo un error al crear el Documento");
	}
}

async function editDocumentInView() {
	let formData = new FormData();
	
	formData.append("id", $("#edit-document-accept-button").attr("document-id"));
	formData.append("name", $("#edit-document-name").val());
	formData.append("description", $("#edit-document-description").val());
	formData.append("uploadDate", $("#edit-document-upload-date").val());

	let result = await editDocument(formData);

	if (result == 1) {
		showSuccessAlert($("#edit-document-modal .modal-body"), "Documento editado correctamente");
		$("#edit-document-form-data").trigger("reset");
		getDocumentsInView()
	} else {
		showDangerAlert($("#edit-document-modal .modal-body"), "Hubo un error al editar el Documento");
	}
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

async function getRequestsInView() {
	$("#requests-list-table-body").empty();

	let requests = await getRequests();

	showRequestsInView(requests);
}

async function getDocumentsInRequestDropdownList() {
	$("select[name='request-document']").empty();

	let documents = await getDocuments();

	showDocumentsInRequestDropdownList(documents);
}

async function getRolesInView() {
	$("select[name='user-rol']").empty();

	let roles = await getRoles();

	showRolesInView(roles);
}

async function registerRequestInView() {
	let request = {
		name: $("#request-name").val(),
		description: $("#request-description").val(),
		creationDate: $("#request-creation-date").val(),
		user: $("#request-user").val()
	};

	let result = await registerRequest(request);

	if (result == 1) {
		showSuccessAlert($("#register-request-modal .modal-body"), "Solicitud creada correctamente");
		$("#register-request-form-data").trigger("reset");
		getRequestsInView();
	} else {
		showDangerAlert($("#register-request-modal .modal-body"), "Hubo un error al crear la solicitud");
	}
}

async function editRequestInView() {
	let request = {
		id: $("#edit-request-accept-button").attr("request-id"),
		name: $("#edit-request-name").val(),
		description: $("#edit-request-description").val(),
		creationDate: $("#edit-request-creation-date").val(),
		user: $("#edit-request-user").val(),
		documentId: $("#edit-request-document").val()
	};

	let result = await editRequest(request);

	if (result == 1) {
		showSuccessAlert($("#edit-request-modal .modal-body"), "Solicitud editada correctamente");
		$("#edit-request-form-data").trigger("reset");
		getRequestsInView();
	} else {
		showDangerAlert($("#edit-request-modal .modal-body"), "Hubo un error al editar la solicitud");
	}
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

function searchRequestsInView() {
	$(".requests-table__body__item").filter(
		function() {
			$(this).toggle(
				$(this).children("td").eq(0).text()
					.includes($("#search-request-name").val()));
		}
	)
}

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
								<button class="btn btn-warning users-table__body__item__edit-button" data-bs-toggle="modal" data-bs-target="#edit-user-modal" onclick="editUserOperation('${user.username}')">Actualizar</button>
									<button class="btn btn-danger users-table__body__item__delete-button" onclick="deleteUserOperation('${user.username}')">Eliminar</button>
							</td>
						</tr>
		`);
	});
}

async function showRequestsInView(requests) {
	let requestStates = await getRequestStates();

	requests.forEach(function(request) {
		let requestState = requestStates.filter(state => state.id == request.requestStateId)[0];

		$("#requests-list-table-body").append(
			`
			<tr class="requests-table__body__item">
							<td class="requests-table__body__item__name">${request.name}</td>
							<td>${request.description}</td>
							<td>${request.creationDate}</td>
							<td>${requestState.name}</td>
							<td>
							<button class="btn btn-primary requests-table__body__item__authorize-button" onclick="authorizeRequestOperation(${request.id})" ${requestState.id == 2 ? '' : 'hidden'}>Autorizar</button>
														<button class="btn btn-primary requests-table__body__item__validate-button" onclick="validateRequestOperation(${request.id})" ${requestState.id == 1 ? '' : 'hidden'}>Validar</button>
							<button class="btn btn-primary requests-table__body__item__approve-button" onclick="approveRequestOperation(${request.id})" ${requestState.id == 3 ? '' : 'hidden'}>Atender</button>
								<button class="btn btn-warning requests-table__body__item__edit-button" data-bs-toggle="modal" data-bs-target="#edit-request-modal" onclick="editRequestOperation(${request.id})">Actualizar</button>
									<button class="btn btn-danger requests-table__body__item__delete-button" onclick="deleteRequestOperation(${request.id})">Eliminar</button>
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

function showDocumentsInRequestDropdownList(documents) {
	documents.forEach(function(document) {
		$("select[name='request-document']").append(`<option value="${document.id}">${document.name}</option>`)
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
										<div class="col-md-2">
											<img src="${contextPath}/resources/img/icono-pdf.png" width="100%">
										</div>
										<div class="col-md-7">
											<h2 class="card-title h4 documents-list__item__title">${document.name}</h2>
											<p class="card-text fw-light fst-italic">${document.description}</p>
										</div>
										<div class="col-md-3">
											<div class="row align-items-center">
												<div class="col flex-column">
													<i class="bi bi-pencil-fill documents-list__item__edit-button" style="font-size: 1.5rem;" data-bs-toggle="modal" data-bs-target="#edit-document-modal" onclick="editDocumentOperation('${document.id}')"></i>
													<i class="bi bi-x documents-list__item__delete-button" style="font-size: 1.5rem;" onclick='deleteDocumentOperation(${document.id})'></i>
												</div>
												<div class="col">
													<i class="bi bi-download documents-list__item__download-button" style="font-size: 2.5rem;" onclick="downloadDocumentOperation('${document.fileId}')"></i>
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

async function downloadDocumentOperation(id) {
	let file = await downloadDocument(id);
	let fileObject = await getFile(id);
	let link = document.createElement('a');
	let blob = new Blob([file], { type: "application/pdf" });
	let reader = new FileReader();

	reader.readAsDataURL(blob);
	reader.onload = function() {
		link.href = reader.result;
		link.download = fileObject.filename;
		link.click();
	}
}

async function editDocumentOperation(id) {
	let document = await getDocument(id);

	$("#edit-document-name").val(document.name);
	$("#edit-document-description").val(document.description);
	$("#edit-document-upload-date").val(dateFormat(new Date(document.uploadDate), "yyyy-MM-dd"));
	$("#edit-document-file").val(document.documentName);
	$("#edit-document-accept-button").attr("document-id", id);
}

async function deleteDocumentOperation(id) {
	let documentsDeleted = await deleteDocument(id);

	if (documentsDeleted == 1) {
		showSuccessAlert($(".main-content"), "Documento eliminado correctamente");
	} else {
		showDangerAlert($(".main-content"), "Hubo un error al eliminar el documento")
	}
	
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

async function authorizeRequestOperation(id) {
	let requestUpdated = await authorizeRequest(id);

	if (requestUpdated == 1) {
		showSuccessAlert($(".main-content"), "Solicitud actualizada correctamente");
	} else {
		showDangerAlert($(".main-content"), "Hubo un error al actualizar la solicitud")
	}

	getRequestsInView();
}

async function approveRequestOperation(id) {
	let requestUpdated = await approveRequest(id);

	if (requestUpdated == 1) {
		showSuccessAlert($(".main-content"), "Solicitud actualizada correctamente");
	} else {
		showDangerAlert($(".main-content"), "Hubo un error al actualizar la solicitud")
	}

	getRequestsInView();
}

async function editRequestOperation(id) {
	let request = await getRequest(id);

	$("#edit-request-name").val(request.name);
	$("#edit-request-description").val(request.description);
	$("#edit-request-creation-date").val(dateFormat(new Date(request.creationDate), "yyyy-MM-dd"));
	$("#edit-request-user").val(request.userId);
	$("#edit-request-document").val(request.documentId);
	$("#edit-request-accept-button").attr("request-id", id);
}

async function validateRequestOperation(id) {
	let requestUpdated = await validateRequest(id);

	if (requestUpdated == 1) {
		showSuccessAlert($(".main-content"), "Solicitud actualizada correctamente");
	} else {
		showDangerAlert($(".main-content"), "Hubo un error al actualizar la solicitud")
	}

	getRequestsInView();
}

async function deleteRequestOperation(id) {
	let requestsDeleted = await deleteRequest(id);

	if (requestsDeleted == 1) {
		showSuccessAlert($(".main-content"), "Solicitud eliminada correctamente");
	} else {
		showDangerAlert($(".main-content"), "Hubo un error al eliminar la solicitud")
	}

	getRequestsInView();
}

async function editUserOperation(username) {
	let user = await getUser(username);

	$("#edit-user-name").val(user.username);
	$("#edit-user-password").val(user.password);
	$("#edit-user-email").val(user.email);
	$("#edit-user-rol").val(user.roleId);
}

async function openRegisterRequestModal() {
	let currentDate = dateFormat(new Date(), "yyyy-MM-dd");
	
	$("#request-creation-date").val(currentDate);
}

async function openRegisterDocumentModal() {
	let currentDate = dateFormat(new Date(), "yyyy-MM-dd");
	
	$("#document-upload-date").val(currentDate);
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

function downloadDocument(id) {
	let result = $.ajax({ url: `${contextPath}/descargar/${id}`, method: "GET", xhrFields: { responseType: 'arraybuffer' } });

	return result;
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

function registerRequest(request) {
	let result = $.ajax({
		url: `${contextPath}/solicitudes/`, method: "POST", data: {
			name: request.name,
			description: request.description,
			creationDate: request.creationDate,
			user: request.user,
			document: request.document
		}
	})

	return result;
}

function editRequest(request) {
	let result = $.ajax({
		url: `${contextPath}/solicitudes/`, method: "PUT", data: JSON.stringify({
			id: request.id,
			name: request.name,
			description: request.description,
			creationDate: request.creationDate,
			userId: request.user,
			documentId: request.documentId
		}), contentType: "application/json"
	})

	return result;
}

function getDocument(id) {
	let result = $.get(`${contextPath}/documentos/?id=${id}`);

	return result;
}

function registerDocument(formData) {
	let result = $.ajax({
		url: `${contextPath}/documentos/`, method: "POST", data: formData, contentType: 'multipart/form-data', processData: false, contentType: false, xhrFields: { responseType: 'json' }
	});

	return result;
}

function editDocument(formData) {
	let result = $.ajax({
		url: `${contextPath}/documentos/`, method: "PUT", data: formData, contentType: 'multipart/form-data', processData: false, contentType: false, xhrFields: { responseType: 'json' }
	});

	return result;
}

function getRequests() {
	let result = $.get(`${contextPath}/solicitudes/`);

	return result;
}

function getFile(id) {
	let result = $.get(`${contextPath}/archivo/${id}`);

	return result;
}

function deleteRequest(id) {
	let result = $.ajax({
		url: `${contextPath}/solicitudes/${id}`, method: "DELETE"
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

function getRequestStates() {
	let result = $.get(`${contextPath}/estados-solicitudes/`);

	return result;
}

function validateRequest(id) {
	let result = $.ajax({
		url: `${contextPath}/solicitudes/validar/?id=${id}`, method: "PUT"
	});

	return result;
}

function approveRequest(id) {
	let result = $.ajax({
		url: `${contextPath}/solicitudes/aprobar/?id=${id}`, method: "PUT"
	});

	return result;
}

function authorizeRequest(id) {
	let result = $.ajax({
		url: `${contextPath}/solicitudes/autorizar/?id=${id}`, method: "PUT"
	});

	return result;
}

function getRequest(id) {
	let result = $.get(`${contextPath}/solicitudes/?id=${id}`);

	return result;
}

function dateFormat(inputDate, format) {
	const date = new Date(inputDate);

	const day = date.getDate();
	const month = date.getMonth() + 1;
	const year = date.getFullYear();

	format = format.replace("MM", month.toString().padStart(2, "0"));

	if (format.indexOf("yyyy") > -1) {
		format = format.replace("yyyy", year.toString());
	} else if (format.indexOf("yy") > -1) {
		format = format.replace("yy", year.toString().substr(2, 2));
	}

	format = format.replace("dd", day.toString().padStart(2, "0"));

	return format;
}
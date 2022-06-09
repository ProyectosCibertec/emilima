$(document).ready(function() {
	app();
});

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
}

function documents() {
	let currentDate = new Date().toJSON().substring(0, Number.parseInt(new Date().toJSON().indexOf("T")));

	getDocuments();

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

	$("#register-user-accept-button").click(function() {
		registerUser();
	});

	$("#search-user-name").keyup(function() {
		searchUsersInView();
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

function registerUser() {

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

async function showUsersInView(users) {
	let roles = await getRoles().then(data => data);

	users.forEach(function(user) {
		let usersRole = roles.filter(role => role.id == user.roleId)[0].name;

		$("#users-list-table-body").append(
			`
			<tr class="users-table__body__item">
							<td classs="users-table__body__item__name">${user.username}</td>
							<td>${user.email}</td>
							<td>${usersRole}</td>
							<td>
								<button class="btn btn-primary">Actualizar</button>
									<button class="btn btn-primary users-table__body__item__delete-button" onclick="deleteUserOperation('${user.username}')">Eliminar</button>
							</td>
						</tr>
		`);
	});
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

function registerDocument() {
	let formData = new FormData();

	formData.append("name", $("#document-name").val());
	formData.append("description", $("#document-description").val());
	formData.append("uploadDate", $("#document-upload-date").val());
	formData.append("documentFile", $("#document-file")[0].files[0]);
	formData.append("requestId", $("#document-request-id").val());

	return $.ajax({
		url: `${contextPath}/documentos/`, method: "POST", data: formData, contentType: 'multipart/form-data', processData: false, contentType: false
	})
}

async function deleteDocumentOperation(id) {
	let documentsDeleted = await deleteDocument(id);

	getDocumentsInView();
}

async function deleteUserOperation(username) {
	let usersDeleted = await deleteUser(username);
	
	if (usersDeleted == 1) {
		// If there's time to make an alert to the user, this will be implemented later.
	}

	getUsersInView();
}

function getDocuments() {
	$.get(`${contextPath}/documentos/`).done(function(documents) {
		showDocumentsInView(documents);
	});
}

function deleteDocument(id) {
	return $.ajax({
		url: `${contextPath}/documentos/${id}`, method: "DELETE"
	})
}

function deleteUser(username) {
	return $.ajax({
		url: `${contextPath}/usuarios/${username}`, method: "DELETE"
	})
}

function getUsers() {
	let result = $.get(`${contextPath}/usuarios/`);

	return result;
}

function getRoles() {
	let result = $.get(`${contextPath}/roles/`)

	return result;
}
$(document).ready(function() {
	app();
});

function app() {
	let pathnames = location.pathname.split("/");

	if (pathnames.includes("documentos")) {
		documents();
	}
}


function documents() {
	getDocuments();

	$("#search-document-name").keyup(function() {
		searchDocumentsInView();
	});

	$("#register-document-accept-button").click(function() {
		registerDocument();
	});
}

function getDocuments() {
	$.get("/documentos/").done(function(documents) {
		showDocumentsInView(documents);
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
													<i class="bi bi-pencil-fill" style="font-size: 1.5rem;"></i>
													<i class="bi bi-x" style="font-size: 1.5rem;"></i>
												</div>
												<div class="col">
													<i class="bi bi-download" style="font-size: 2.5rem;"></i>
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

function searchDocumentsInView() {
	if ($("#search-document-name").val() != "") {
		$(".documents-list__item").filter(
			function(item) {
				$(this).toggle(
					$(".documents-list__item__title").text()
						.indexOf($("#search-document-name").val()) >= 0);
			})
	}
}

function registerDocument() {
	let formData = new FormData();

	formData.append("name", $("#document-name").val());
	formData.append("description", $("#document-description").val());
	formData.append("uploadDate", $("#document-upload-date").val());
	formData.append("documentFile", $("#document-file")[0].files[0]);
	formData.append("requestId", $("#document-request-id").val());

	$.ajax({
		url: "/documentos", method: "POST", data: formData, contentType: 'multipart/form-data', processData: false, contentType: false
	})
}
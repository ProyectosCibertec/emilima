class Document {
	constructor(
		id,
		name,
		description,
		uploadDate,
		documentFile,
		requestId
	) {
		this._id = id;
		this._name = name;
		this._description = description;
		this._uploadDate = uploadDate;
		this.documentFile = documentFile
		this._requestId = requestId;
	}
	
	get id() {
		return this._id;
	}
	
	set id(id) {
		this._id = id;
	}
	
	get name() {
		return this._name;
	}
	
	set name(name) {
		this._name = name;
	}
	
	get description() {
		return this._description;
	}
	
	set description(description) {
		this._description = description;
	}
	
	get uploadDate() {
		return this._uploadDate;
	}
	
	set uploadDate(uploadDate) {
		this._uploadDate = uploadDate;
	}
	
	get documentFile() {
		return this._documentFile;
	}
	
	set documentFile(documentFile) {
		this._documentFile = documentFile;
	}
	
	get requestId() {
		return this._requestId;
	}
	
	set requestId(requestId) {
		this._requestId = requestId;
	}
}
//Get the details from the create new form and validate
function validateCreateNew(){
	// you can get value by name 
	var firstname=createNew.firstName.value;
	// or get value by id
	var lastname=document.forms["createNew"]["lastName"].value;
	var email=document.forms["createNew"]["email"].value;
	return validate(firstname, lastname, email);
}

//Get the details from the update form and validate
function validateUpdate(){
	// you can get value by name 
	var firstname=update.firstName.value;
	// or get value by id
	var lastname=document.forms["update"]["lastName"].value;
	var email=document.forms["update"]["email"].value;
	return validate(firstname, lastname, email);
}

//Validate each of the fields
function validate(firstname, lastname, email) {
	var alertMessage="";
	var canBeZero = false

	if( !check(firstname, canBeZero) ) {
		alertMessage = alertMessage + "First name cannot be blank and must not contain \< \> \& \" \'or \/. ";
	}
	if( !check(lastname, canBeZero) ) {
		alertMessage = alertMessage + "Last name cannot be blank and must not contain \< \> \& \" \'or \/. ";
	}
	if( !check(email, canBeZero) ) {
		alertMessage = alertMessage + "Email cannot be blank and must not contain \< \> \& \" \'or \/.";
	}
	if( alertMessage.length > 0) {
		alert(alertMessage);
		return false;
	}
	return true;
}

//Check the supplied string is not empty and does not contain
//characters which allow hacking attempts
function check(name, canBeZero){
	if (canBeZero) {
		if (name == null || name == "") {
			return true;
		}
	} else {
		if (name == null || name == "") {
			return false;
		}
	}
	if (name.indexOf('<') > -1 ) {
		return false;
	}
	if (name.indexOf('>') > -1 ) {
		return false;
	}
	if (name.indexOf('&') > -1 ) {
		return false;
	}
	if (name.indexOf('"') > -1 ) {
		return false;
	}
	if (name.indexOf('\'') > -1 ) {
		return false;
	}
	if (name.indexOf('\/') > -1 ) {
		return false;
	}
	return true;
	
}

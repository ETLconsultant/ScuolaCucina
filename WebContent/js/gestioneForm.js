function fieldValidationDate(){
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth()+1; //January is 0!
	var yyyy = today.getFullYear();

	if(dd<10){
	        dd='0'+dd
	    } 
	    if(mm<10){
	        mm='0'+mm
	    } 
	    if(dd1<10){
	        dd1='0'+dd1
	    } 
	    if(mm1<10){
	        mm1='0'+mm1
	    } 
	today = yyyy+'-'+mm+'-'+dd;
	nextDay = yyyy1+'-'+mm1+'-'+dd1;
	console.log(today);
	document.getElementById("datefield1").setAttribute("min", today);
	document.getElementById("datefield2").setAttribute("min", nextDay);

}

function fieldValidation(){
	
	document.getElementById("nome2").innerHTML = ""
	document.getElementById("cognome2").innerHTML = ""
	document.getElementById("username2").innerHTML = ""
	document.getElementById("password2").innerHTML = ""

		
		
	var username = document.forms["myForm"]["username"].value
//	var isNomeValid = (nome !== null && nome.length > 0 && nome.length <= 20)
	
	var password = document.forms["myForm"]["password"].value

	
	var nome = document.forms["myForm"]["nome"].value
	
	var cognome = document.forms["myForm"]["cognome"].value
//	var isCognomeValid = (cognome !== null && cognome.length > 0 && cognome.length <= 20)
	
//	var isUsernameValid = (username !== null && username.length > 0 
//			&& username.length <= 20)
	
	var email = document.forms["myForm"]["email"].value
	
	var telefono = document.forms["myForm"]["telefono"].value

	var passwordExpression = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,20}$/;
	var isPasswordValid = password.match(passwordExpression)
	
	if(idUtente == null || idUtente.length==0){
		document.getElementById("username2").innerHTML = "Inserisca uno username";
		return false
	}
	
	if(username.length > 50){
		document.getElementById("username2").innerHTML = "Lo username deve essere piu' corto di 20 caratteri";
		return false
	}
	
	if(nome == null || nome.length == 0){
		document.getElementById("nome2").innerHTML = "Inserisca il suo nome";
		return false
	}
	
	if(nome.length > 50){
		document.getElementById("nome2").innerHTML = "Il nome deve essere piu' corto di 20 caratteri";
		return false
	}
	
	if(cognome == null || cognome.length == 0){
		document.getElementById("cognome2").innerHTML = "Inserisca il suo cognome";
		return false
	}
	
	if(cognome.length > 50){
		document.getElementById("cognome2").innerHTML = "Il cognome deve essere piu' corto di 20 caratteri";
		return false
	}
	
	if(password == null || password.length<8){
		document.getElementById("password2").innerHTML = "La password deve essere piu' lunga di 8 caratteri"
		return false
	}
	
	if(!isPasswordValid){
		document.getElementById("password2").innerHTML = "La password deve contenere " +
				"almeno una lettera minuscola e una lettera maiuscola, " +
				"un carattere speciale, un numero, deve essere compresa tra 8 e 20 caratteri " +
				"e non deve contenere spazi."
		return false
	}
	

}

function fieldValidationLogin(){
	document.getElementById("username2").innerHTML = ""
	document.getElementById("password2").innerHTML = ""
		
	var username = document.forms["myForm"]["username"].value
	var password = document.forms["myForm"]["password"].value
	var passwordExpression = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,20}$/;
	var isPasswordValid = password.match(passwordExpression)
	
	if(username == null || username.length == 0){
		document.getElementById("username2").innerHTML = "Username o password errati";
		return false
	}
	
	if(username.length > 20){
		document.getElementById("username2").innerHTML = "Username o password errati";
		return false
	}
	
	if(password == null || password.length<8){
		document.getElementById("password2").innerHTML = "Username o password errati"
		return false
	}
	
	if(!isPasswordValid){
		document.getElementById("password2").innerHTML = "Username o password errati"
		return false
	}
	
}



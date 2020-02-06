<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<script type="text/javascript" src="js/gestioneForm.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->
<link rel="icon" type="image/png" href="images/icons/favicon.ico" />
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/select2/select2.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="css/util.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<!--===============================================================================================-->
<%@include file="Header.jsp"%>
</head>
<body>

	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<div class="login100-form-title"
					style="background-image: url(img/food.jpg);"">
					<span class="login100-form-title-1"> Sign In </span>
				</div>

				<%
					String messaggio = (String) request.getAttribute("messageLogin");
					if (messaggio != null) {
						out.println(messaggio);
					}
				%>

				<form class="login100-form validate-form"
					action="LoginUtenteServlet" method="post">
					<div class="wrap-input100 validate-input m-b-26"
						data-validate="Username is required">
						<span class="label-input100">Username</span> <input
							class="input100" type="text" name="username"
							placeholder="Enter username"> <span
							class="focus-input100"> </span>

						<c:forEach items="${lista}" var="errore">
							<c:if test="${errore.campoValidato=='idUtente'}"> ${errore.descrizioneErrore}</c:if>
						</c:forEach>
					</div>

					<div class="wrap-input100 validate-input m-b-18"
						data-validate="Password is required">
						<span class="label-input100">Password</span> <input
							class="input100" type="password" name="password"
							placeholder="Enter password"> <span
							class="focus-input100"></span>
							
							<c:forEach items="${lista}" var="errore">
							<c:if test="${errore.campoValidato=='password'}"> ${errore.descrizioneErrore}</c:if>
						</c:forEach>
					</div>
					<br> <br> <input type="hidden" name="nome" value="nome"
						size="20 px"> <input type="hidden" name="cognome"
						value="cognome" size="20 px"> <input type="hidden"
						name="dataNascita" value="2000-02-06"> <input
						type="hidden" name="email" value="savoia@savoia.savoia"> <input
						type="hidden" name="telefono" value="0123456789"> <input
						type="hidden" name="admin" value="false">


					<div class="container-login100-form-btn">
						<button class="login100-form-btn" name="bottone" value="utente">
							Login Utente</button>

						<button class="login100-form-btn" name="bottone"
							value="amministratore">Login Amministratore</button>
					</div>
					<br> <br>
					<p>
						Non sei ancora registrato? <a href="registraUtente.jsp">Clicca
							qui!</a>
					<p>
				</form>

			</div>
		</div>
	</div>

	<!--===============================================================================================-->
	<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
	<!--===============================================================================================-->
	<script src="vendor/animsition/js/animsition.min.js"></script>
	<!--===============================================================================================-->
	<script src="vendor/bootstrap/js/popper.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
	<!--===============================================================================================-->
	<script src="vendor/select2/select2.min.js"></script>
	<!--===============================================================================================-->
	<script src="vendor/daterangepicker/moment.min.js"></script>
	<script src="vendor/daterangepicker/daterangepicker.js"></script>
	<!--===============================================================================================-->
	<script src="vendor/countdowntime/countdowntime.js"></script>
	<!--===============================================================================================-->
	<script src="js/main.js"></script>
	<%@include file="Footer.jsp"%>
</body>
</html>
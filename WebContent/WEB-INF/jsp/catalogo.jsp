<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Catalogo</title>
<style>
* {
	box-sizing: border-box;
}

#myInput {
	background-image: url('/css/searchicon.png');
	background-position: 10px 10px;
	background-repeat: no-repeat;
	width: 100%;
	font-size: 16px;
	padding: 12px 20px 12px 40px;
	border: 1px solid #ddd;
	margin-bottom: 12px;
}

#mylist {
	background-position: 10px 10px;
	background-repeat: no-repeat;
	width: 100%;
	font-size: 16px;
	padding: 12px 20px 12px 40px;
	border: 1px solid #ddd;
	margin-bottom: 12px;
}

#myTable {
	border-collapse: collapse;
	width: 100%;
	border: 1px solid #ddd;
	font-size: 18px;
}

#myTable th, #myTable td {
	text-align: left;
	padding: 12px;
}

#myTable tr {
	border-bottom: 1px solid #ddd;
}

#myTable tr.header, #myTable tr:hover {
	background-color: #f1f1f1;
}
</style>
</head>
<body>
	<%
		String username = (String) session.getAttribute("username");
	%>

	<h2>Elenco Corsi</h2>

	<select id="mylist" onchange="myFunction()" class='form-control'>
		<option>A</option>
		<option>b</option>
		<option>c</option>
	</select>

	<div>
		<table id="myTable">
			<tr class="header">
				<td><%="Codice Corso"%>
				<td><%="Titolo"%>
				
				<td><%="Categoria"%>
				
				<td><%="Partecipanti Max"%>
				
				<td><%="Costo"%>
				
				<td><%="Descrizione"%>
			
			</tr>
			<c:forEach var="corsi" items="${requestScope.corsi}">
				<br>
				<tr id="effetto">
					<td><c:out value="${corsi.codice}">
						</c:out></td>
					<td><c:out value="${corsi.titolo}">
						</c:out></td>
					<td><c:out value="${corsi.idCategoria}">
						</c:out></td>
					<td><c:out value="${corsi.maxPartecipanti}">
						</c:out></td>
					<td><c:out value="${corsi.costo}">
						</c:out></td>
					<td><c:out value="${corsi.descrizione}">
						</c:out></td>
	<!-- <td size=40px><c:choose>
							<c:when test="${lista.disponibile}">
								<form action="Prenota" class="form">
									<button id="centrato" type="submit" name="button"
										value="${lista.numeroStanza}">Prenota</button>
								</form>
							</c:when>
							<c:otherwise>
								<div class="form">
									<button id="no_button" type="button"">Già Prenotata</button>
								</div>

							</c:otherwise>
						</c:choose></td> -->				
				</tr>

			</c:forEach>

		</table>

	
</div> <script>
		function myFunction() {
			var input, filter, table, tr, td, i;
			input = document.getElementById("mylist");
			filter = input.value.toUpperCase();
			table = document.getElementById("myTable");
			tr = table.getElementsByTagName("tr");
			for (i = 0; i < tr.length; i++) {
				td = tr[i].getElementsByTagName("td")[0];
				if (td) {
					if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
						tr[i].style.display = "";
					} else {
						tr[i].style.display = "none";
					}
				}
			}
		}
	</script>


				</body>
</html>
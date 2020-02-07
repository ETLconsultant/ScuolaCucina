<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
	<h2>My Customers</h2>
	<select id="mylist" onchange="myFunction()" class='form-control'>
		<option>A</option>
		<option>b</option>
		<option>c</option>
	</select>
	<select id="mylist" onchange="myFunction()" class='form-control'>
		<option>A</option>
		<option>b</option>
		<option>c</option>
	</select>


	<table id="myTable">
		<tr class="header">
			<th style="width: 60%;">Name</th>
			<th style="width: 40%;">Country</th>
		</tr>
		<tr>
			<td>Alfreds Futterkiste</td>
			<td>Germany</td>
		</tr>
		<tr>
			<td>Berglunds snabbkop</td>
			<td>Sweden</td>
		</tr>
		<tr>
			<td>Island Trading</td>
			<td>UK</td>
		</tr>
		<tr>
			<td>Koniglich Essen</td>
			<td>Germany</td>
		</tr>
		<tr>
			<td>Laughing Bacchus Winecellars</td>
			<td>Canada</td>
		</tr>
		<tr>
			<td>Magazzini Alimentari Riuniti</td>
			<td>Italy</td>
		</tr>
		<tr>
			<td>North/South</td>
			<td>UK</td>
		</tr>
		<tr>
			<td>Paris specialites</td>
			<td>France</td>
		</tr>
	</table>

	<script>
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
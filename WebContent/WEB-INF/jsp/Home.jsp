<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<%@include file="Header.jsp"%>
<title>Home Page</title>
</head>
<body>
<div id="myCarousel" class="carousel slide" data-ride="carousel">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			<li data-target="#myCarousel" data-slide-to="1"></li>
			<li data-target="#myCarousel" data-slide-to="2"></li>
		</ol>
	
		<!-- Wrapper for slides -->
		<div class="carousel-inner" role="listbox">
			<div class="item active">
				<img src="img/pasta.jpg" alt="Image">
				<div class="carousel-caption">
				</div>
			</div>

			<div class="item">
				<img src="img/ape.jpg" alt="Image">
				<div class="carousel-caption">
				</div>
			</div>
			<div class="item">
				<img src="img/sweet.jpg" alt="Image">
				<div class="carousel-caption">
				</div>
			</div>
		</div>

<!-- Left and right controls -->
	<a class="left carousel-control" href="#myCarousel" role="button"
		data-slide="prev"> <span class="glyphicon glyphicon-chevron-left"
		aria-hidden="true"></span> <span class="sr-only">Previous</span>
	</a>
	<a class="right carousel-control" href="#myCarousel" role="button"
		data-slide="next"> <span class="glyphicon glyphicon-chevron-right"
		aria-hidden="true"></span> <span class="sr-only">Next</span>
	</a>
	</div>

<!-- Container (About Section) -->
<div id="about" class="container-fluid">
  <div class="row">
    <div class="col-sm-8">
      <h2>La nostra azienda</h2><br>
      <h4>Un percorso di alta formazione professionalizzante che si rivolge a chi ha già maturato esperienza nella ristorazione e desidera specializzarsi ai massimi livelli.</h4><br>
      <p>I migliori piatti nascono con il giusto mix tra tradizione
e innovazione, vale anche per la formazione. Javani Courses mette a disposizione di aziende, enti e privati, un modernissimo Centro congressi di 300 mq e 200 sedute, attrezzato con pareti amovibili per spazi modulabili, wifi, schermi 4k, videoproiettori e microfoni. Il Campus di Agerola è ideale per progetti formativi di tipo Indoor in cui le tecniche manageriali possono essere spiegate e sviluppate trasformando i partecipanti dell aula in una brigata aziendale che progetta, cucina e opera intorno agli stessi obiettivi condivisi. La struttura permette di offrire docenza, ospitalità, convivialità e contenuti manageriali realizzando altresì momenti finali di cooking show.</p>
      <br>
    </div>
  </div>
</div>

<!-- Container (Services Section) -->
<div id="services" class="container-fluid text-center">
  <h2>SERVIZI</h2>
  <h4>Cosa offriamo: </h4>
  <br>
  <div class="row slideanim">
    <div class="col-sm-4">
   <span class="glyphicon glyphicon-off logo-small"></span>
      <h4>POWER</h4>
    </div>
    <div class="col-sm-4">
      <span class="glyphicon glyphicon-heart logo-small"></span>
      <h4>LOVE</h4>
    </div>
    <div class="col-sm-4">
      <span class="glyphicon glyphicon-lock logo-small"></span>
      <h4>JOB DONE</h4>
    </div>
  </div>
  <br><br>
  <div class="row slideanim">
    <div class="col-sm-4">
      <span class="glyphicon glyphicon-leaf logo-small"></span>
      <h4>GREEN</h4>
    </div>
    <div class="col-sm-4">
      <span class="glyphicon glyphicon-certificate logo-small"></span>
      <h4>CERTIFIED</h4>
    </div>
    <div class="col-sm-4">
      <span class="glyphicon glyphicon-wrench logo-small"></span>
      <h4 style="color:#303030;">HARD WORK</h4>
    </div>
  </div>
</div>
<%@include file="Footer.jsp"%>
</body>
</html>
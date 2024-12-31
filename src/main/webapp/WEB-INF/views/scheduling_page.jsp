<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Agendamento de Visitas</title>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css"
	rel="stylesheet" />
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<link rel="stylesheet" href="resources/css/styles_homepage.css" />
<link rel="stylesheet" href="resources/css/styles_registration.css" />
<link rel="icon" href="resources/images/MS.png" type="image/x-icon">
</head>
<body>

	<%@ include file="control/navbar.jsp"%>

	<div class="content-registration">
		<div class="content-form">
			<h5>Fazer Agendamento</h5>
			<form action="scheduleVisit" method="post">
				
				<!-- Selecionar Museu -->
				<div class="input-field select">
					<i class="material-icons prefix">location_city</i> <select
						id="museum" name="museum" required>
						<option value="" disabled selected>Selecione um museu</option>
						<c:forEach var="museum" items="${museums}">
							<option value="${museum.id}">${museum.name}</option>
						</c:forEach>
					</select> <label for="museum">Museu</label>
				</div>

				<!-- Escolher data -->
				<div class="input-field">
					<i class="material-icons prefix">event</i> 
					<input type="text" id="datePicker" class="datepicker" placeholder="Escolha uma data">
				</div>

				<div class="hide-field">
				
					<!-- Escolher horário -->
					<div class="input-field select">
						<i class="material-icons prefix">schedule</i> 
						<select id="timePicker" name="time" required>
						</select> 
						
						<label for="timePicker">Horário</label>
					</div>

					<!-- Escolher quantidade de pessoas -->
					<div class="input-field">
						<i class="material-icons prefix">group</i> <input type="number"
							id="peopleCount" name="peopleCount" min="1"
							placeholder="Selecionar Horário">
					</div>
				</div><!-- hide-field -->
			</form>
		</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
	<script src="resources/script/script.js"></script>
	<script src="resources/script/script_messages.js"></script>
	<script src="resources/script/script_jquery.js"></script>

</body>
</html>

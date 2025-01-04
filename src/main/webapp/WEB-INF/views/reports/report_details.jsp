<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Agendamento de Visitas</title>
<link href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css" rel="stylesheet" />
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<link rel="stylesheet" href="resources/css/styles_homepage.css" />
<link rel="stylesheet" href="resources/css/styles_registration.css" />
<link rel="stylesheet" href="resources/css/styles_report.css" />
<link rel="stylesheet" href="resources/css/styles_table.css" />
<link rel="icon" href="resources/images/MS.png" type="image/x-icon">
</head>
<body>

	<%@ include file="../control/navbar.jsp"%>

	<div class="content-registration">

		<div class="content-form table">

			<h5>Agendamento: ${scheduling.museum.name}</h5>

			<c:if test="${not empty schedulings}">
				<div class="date-scheduling">
					<p>
						Data e Hora: ${formattedDate}
						às ${schedulings[0].hourlyReservation.time}
					</p>
				</div>
			</c:if>

			<div class="form-table">
				<table>
					<tr>
						<th>Nome</th>
						<th>CPF</th>
						<th>Ticket</th>
						<th>Museu</th>
						<th>Código</th>
						<th>Presença</th>
					</tr>
					<c:forEach var="scheduling" items="${schedulings}">
						<c:forEach var="person" items="${scheduling.people}">
							<tr>
								<td>${person.name}</td>
								<td>${person.cpf}</td>
								<td>${person.ticketType}</td>
								<td>${scheduling.museum.name}</td>
								<td>${scheduling.confirmationCode}</td>
								<td>
								<label> 
										<input type="checkbox"
										class="filled-in" id="presence-${person.id}"
										${person.present ? 'checked' : ''}
										onchange="markPresence(${person.id}, ${scheduling.id})" /> <span>Presente</span>
								</label>
								</td>

							</tr>
						</c:forEach>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
	<script src="resources/script/script_materialize.js"></script>
	<script src="resources/script/script_messages.js"></script>
	<script src="resources/script/script_cancel_jquery.js"></script>
</body>
</html>

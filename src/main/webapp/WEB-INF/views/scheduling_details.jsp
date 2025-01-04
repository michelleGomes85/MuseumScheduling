<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Agendamento de Visitas</title>
	<link rel="icon" href="resources/images/MS.png" type="image/x-icon">
	
	<link href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css" rel="stylesheet" />
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
	<link rel="stylesheet" href="resources/css/styles_homepage.css" />
	<link rel="stylesheet" href="resources/css/styles_registration.css" />
	<link rel="stylesheet" href="resources/css/styles_table.css" />
</head>
<body>
	<%@ include file="control/navbar.jsp"%>
	
	<div class="content-registration">
		
		<div class="content-form table">
		
			<h5>Agendamento: ${scheduling.museum.name}</h5>
			
			<div class="date-scheduling">
				<p>Código: ${scheduling.confirmationCode}</p>
				<p>
					Data e Hora:
					<fmt:formatDate value="${formattedDate}" pattern="dd/MM/yyyy" />
					às ${scheduling.hourlyReservation.time}
				</p>
			</div>

			<div class="form-table">
				<table>
					<tr>
						<th>Nome</th>
						<th>CPF</th>
						<th>Ticket</th>
						<th>Cancelar</th>
					</tr>
					<c:forEach items="${scheduling.people}" var="person">
						<tr>
							<td>${person.name}</td>
							<td>${person.cpf}</td>
							<td>${person.ticketType}</td>
							<td>
								<a href="javascript:void(0);" class="cancel-icon"
									title="Cancelar" data-scheduling-id="${scheduling.id}"
									data-person-id="${person.id}"> <i class="material-icons">cancel</i>
								</a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>

			<div class="cancel-all-button">
				<a href="javascript:void(0);" class="btn orange" id="cancel-all"
					data-scheduling-id="${scheduling.id}" title="Cancelar Todos"> Cancelar Todos </a>
			</div>
		</div>
	</div>
	
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
	<script	src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js"></script>
	<script	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
	<script src="resources/script/script_materialize.js"></script>
	<script src="resources/script/script_messages.js"></script>
	<script src="resources/script/script_cancel_jquery.js"></script>
</body>
</html>

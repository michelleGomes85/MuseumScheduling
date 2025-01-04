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

<!-- Estilo adicional para espaçamento -->
<style>
    .scheduling-details,
    .form-table {
        margin-bottom: 20px; /* Espaçamento entre as tabelas */
    }
</style>
</head>
<body>

	<%@ include file="../control/navbar.jsp"%>

	<div class="content-registration content-scheduling">
		<div class="content-form table">
			
			<h4>Agendamentos ${formattedDate}</h4>
			<h4>Número de agendamentos - ${numberScheduling}</h4>
						
			<c:forEach var="scheduling" items="${schedulings}">
				
					<h5 class="title_scheduling">Agendamento: ${scheduling.museum.name} (${scheduling.confirmationCode})</h5>
					
					<!-- Tabela de informações do agendamento -->
					<div class="scheduling-details">
						<table class="striped">
							<thead>
								<tr>
									<th>Informação</th>
									<th>Detalhe</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>Código de Confirmação</td>
									<td>${scheduling.confirmationCode}</td>
								</tr>
								<tr>
									<td>Email do Responsável</td>
									<td>${scheduling.responsibleEmail}</td>
								</tr>
								<tr>
									<td>Nome do Museu</td>
									<td>${scheduling.museum.name}</td>
								</tr>
								<tr>
									<td>Data</td>
									<td>${formattedDate}</td>
								</tr>
								<tr>
									<td>Horário</td>
									<td>${scheduling.hourlyReservation.time}</td>
								</tr>
								<tr>
									<td>Total de Pessoas</td>
									<td>${scheduling.hourlyReservation.reservedPeople}</td>
								</tr>
								<tr>
									<td>Pessoas Presentes</td>
									 <td id="people-present-${scheduling.id}">${scheduling.hourlyReservation.peoplePresent}</td>
								</tr>
							</tbody>
						</table>
					</div>
	
					<!-- Tabela de pessoas do agendamento -->
					<div class="form-table">
						<h6>Pessoas Agendadas</h6>
						<table class="striped">
							<thead>
								<tr>
									<th>Nome</th>
									<th>CPF</th>
									<th>Presença</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="person" items="${scheduling.people}">
									<tr>
										<td>${person.name}</td>
										<td>${person.cpf}</td>
										<td>
											<label>
												<input type="checkbox"
													class="filled-in" id="presence-${person.id}"
													${person.present ? 'checked' : ''}
													onchange="markPresence(${person.id}, ${scheduling.id})" />
												<span>Presente</span>
											</label>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				
			</c:forEach>
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

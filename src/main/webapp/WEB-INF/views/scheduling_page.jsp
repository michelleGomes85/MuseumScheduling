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

				<div class="input-field">
					<i class="material-icons prefix">email</i> <input
						id="responsibleEmail" type="email" name="responsibleEmail" required>
					<label for="responsibleEmail">E-mail</label>
				</div>

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
					<input type="text" id="datePicker" class="datepicker" placeholder="Escolha uma data" name="hourlyReservation.date">
				</div>

				<div class="hide-field section">
					<!-- Escolher horário -->
					<div class="input-field select">
						<i class="material-icons prefix">schedule</i> 
						<select	id="timePicker" name="hourlyReservation.time" required></select> 
						<label for="timePicker">Horário</label>
					</div>

					<!-- Escolher quantidade de pessoas -->
					<div class="input-field">
						<i class="material-icons prefix">group</i> <input type="number"
							id="peopleCount" name="hourlyReservation.reservedPeople" min="1"
							placeholder="Selecionar Horário">
					</div>
				</div><!-- hide-field -->

				<!-- Informações de pessoas -->
				<div id="peopleInfoContainer" class="hide-field"></div>

				<button id="continueButton" type="button" class="btn">Continuar
					Agendamento</button>
					
				<button id="submitButton" type="submit" class="btn">Cadastrar</button>
			</form>
		</div>
	</div>

	<!-- Modal de Termos de Responsabilidade -->
	<div id="termModal" class="modal">
		<div class="modal-content modal-upgrade">
			<h4>Termo de Responsabilidade</h4>
			<div id="termText">
				<p>Eu, abaixo assinado, declaro que aceito integralmente os
					termos e condições de uso do serviço fornecido. Compreendo que, ao
					utilizar os serviços oferecidos, estou ciente das responsabilidades
					que assumo e comprometo-me a respeitar as condições estabelecidas a
					seguir:</p>
				<ul class="item-term">
					<li><strong>1.</strong> Cumprir todas as normas, regulamentos
						e políticas aplicáveis ao serviço, conforme estabelecido pelo
						prestador do serviço.</li>
					<li><strong>2.</strong> Não utilizar o serviço para qualquer
						finalidade ilegal ou prejudicial a terceiros, incluindo, mas não
						se limitando, ao uso indevido de dados e violação de direitos
						autorais.</li>
					<li><strong>3.</strong> Assumir total responsabilidade pela
						veracidade, legalidade e precisão das informações fornecidas
						durante o processo de registro e ao utilizar os serviços.</li>
					<li><strong>4.</strong> Concordar com a política de
						privacidade do serviço e o uso dos meus dados pessoais de acordo
						com as normas estabelecidas pelo prestador, entendendo que posso a
						qualquer momento revogar meu consentimento, conforme as condições
						previstas.</li>
				</ul>
				
				<p>Estou ciente de que, ao aceitar este termo, estou
					vinculando-me legalmente às condições descritas acima, com a total
					compreensão das implicações legais que isso pode ter.</p>
				<p>Caso tenha dúvidas, recomendo que consulte um advogado ou
					especialista na área antes de prosseguir com a aceitação deste
					termo.</p>
			</div>
		</div>
	</div>
	
	<!-- Exibindo mensagens de erro -->
	<c:if test="${not empty messageReturn}">
		<div id="modalMessage-error" class="modalMessage">
			<div class="modal-content">
				<h2>Erro</h2>
				<ul class="ul-model">
					<c:forEach var="error" items="${messageReturn}">
						<li>${error}</li>
					</c:forEach>
				</ul>
	            <button onclick="closeModal()" class="btn-submit">Fechar</button>				
			</div>
		</div>
	</c:if>

	<!-- Exibindo mensagem de sucesso -->
	<c:if test="${messageReturn == 'Cadastro realizado com sucesso!'}">
		<div id="modalMessage-sucess" class="modalMessage">
			<div class="modal-content">
				<h2>Messagem Informativa</h2>
	            <p><c:out value="${messageReturn}" /></p>
	            <button onclick="closeModal()" class="btn-submit">Fechar</button>
			</div>
		</div>
	</c:if>

	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
	<script	src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js"></script>
	<script	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
	<script src="resources/script/script.js"></script>
	<script src="resources/script/script_messages.js"></script>
	<script src="resources/script/script_jquery.js"></script>

</body>
</html>

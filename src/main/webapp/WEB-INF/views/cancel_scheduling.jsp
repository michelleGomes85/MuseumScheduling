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
	<link rel="icon" href="resources/images/MS.png" type="image/x-icon">
</head>
<body>
	<%@ include file="control/navbar.jsp"%>
	
	<div class="content-registration">
		<div class="content-form">
			<h5>Cancelar Agendamento</h5>
			<form action="listSchedulingByCode" method="post">
				
				<div class="input-field">
					<i class="material-icons prefix">email</i> <input
						id="responsibleEmail" type="email" name="responsibleEmail" required>
					<label for="responsibleEmail">E-mail</label>
				</div>

				<div class="input-field">
					<i class="material-icons prefix">lock</i> <input
						id="confirmationCode" type="text" name="confirmationCode" required>
					<label for="confirmationCode">Código de Confirmação</label>
				</div>

				<button id="searchButton" type="submit" class="btn">Buscar</button>
			</form>
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
	<script src="resources/script/script_registration_jquery.js"></script>
	<script src="resources/script/script_messages.js"></script>
	<script src="resources/script/script_materialize.js"></script>
	
</body>
</html>
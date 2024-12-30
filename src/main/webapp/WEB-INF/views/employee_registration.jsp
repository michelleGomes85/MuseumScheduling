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

			<h5>Cadastrar Funcionario</h5>

			<form action="registerAppUser" method="post">
				<div class="input-field">
					<i class="material-icons prefix">person</i> <input id="name"
						type="text" name="name" required> <label for="name">Nome</label>
				</div>
				<div class="input-field">
					<i class="material-icons prefix">fingerprint</i> <input id="cpf"
						type="text" name="cpf" required> <label for="cpf">CPF</label>
				</div>
				<div class="input-field">
					<i class="material-icons prefix">email</i> <input id="email"
						type="email" name="email" required> <label for="email">E-mail</label>
				</div>
				<div class="input-field">
					<i class="material-icons prefix">lock</i> <input id="password"
						type="password" name="password" required> <label
						for="password">Senha</label> 
						
						<i class="fas fa-eye password-toggle" id="togglePassword" onclick="togglePasswordVisibility()"></i>
				</div>

				<div class="input-field select">
					<i class="material-icons prefix">location_city</i> 
					<select	id="museum" name="museum" required>
						<option value="" disabled selected>Selecione um museu</option>
						<c:forEach var="museum" items="${museums}">
							<option value="${museum.id}">${museum.name}</option>
						</c:forEach>
					</select> 
					
					<label for="museum">Museu</label>
					
				    <input type="hidden" name="userProfile" value="STAFF">
					
				</div>

				<button class="btn waves-effect waves-light" type="submit">Cadastrar</button>
			</form>
		</div>
	</div><!-- content-registration -->

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
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
	<script src="resources/script/script.js"></script>
	<script src="resources/script/script_messages.js"></script>
	<script src="resources/script/script_jquery.js"></script>
</body>
</html>
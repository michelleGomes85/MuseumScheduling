<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="resources/css/styles_report.css" />
</head>
<body>
	<!-- navbar.jsp -->
	<nav class="navbar">
		<div class="nav-wrapper container">
			<ul id="nav-mobile" class="center">

				<!-- Opções visíveis para todos, logado ou não -->
				<li><a href="<c:url value='/home_page'/>"><i
						class="material-icons left">home</i>Home</a></li>

				<li><a href="<c:url value='/scheduling_page'/>"><i
						class="material-icons left">event</i>Agendar Visitas</a></li>
				<li><a href="<c:url value='/cancel_page'/>"><i
						class="material-icons left">cancel</i>Cancelar Visitas</a></li>

				<!-- As opções abaixo serão visíveis apenas para usuários logados -->
				<c:if test="${not empty user}">
				<li><a href="#management_schedules" class="modal-trigger"><i class="material-icons left">assessment</i>Gestão de Agendamentos</a></li>
				</c:if>

				<c:if test="${not empty user and user.userProfile == 'ADMIN'}">
					<li><a href="<c:url value='/employee_registration'/>"><i
							class="material-icons left">person_add</i>Cadastrar Funcionários</a></li>
				</c:if>
			</ul>


			<!-- Opções de login/logout -->
			<ul class="right user-info">
				<c:choose>
					<c:when test="${empty user}">
						<li><a href="<c:url value='/loginForm'/>" class="btn out">Login</a></li>
					</c:when>
					<c:otherwise>
						<li><span>${user.name}</span></li>
						<li><a href="<c:url value='/logout'/>" class="btn out">Sair</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</nav>

	<!-- Modal Structure -->
	<div id="management_schedules" class="modal">
		<div class="modal-content">
			<h4>Gestão de Agendamentos</h4>
			<div class="row">
				<a href="<c:url value='/report_day_time'/>" class="waves-effect waves-light btn btn-report">Gerar Relatório
					de Pessoas Agendadas e marcar comparecimento</a>
			</div>
			<div class="row">
				<a href="<c:url value='/report_day'/>" class="waves-effect waves-light btn btn-report">Gerar Relatório
					de Agendamentos</a>
			</div>
		</div>

		<div class="modal-footer">
			<a href="#!" class="modal-close btn">Fechar</a>
		</div>
	</div>

</body>
</html>
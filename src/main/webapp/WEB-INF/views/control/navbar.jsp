<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- navbar.jsp -->
	<nav class="navbar">
		<div class="nav-wrapper container">
			<ul id="nav-mobile" class="center">

				<!-- Opções visíveis para todos, logado ou não -->
				<li><a href="<c:url value='/home_page'/>"><i class="material-icons left">home</i>Home</a></li>
				
				<li><a href="<c:url value='/scheduling_page'/>"><i	class="material-icons left">event</i>Agendar Visitas</a></li>
				<li><a href="<c:url value='/cancel_page'/>"><i
						class="material-icons left">cancel</i>Cancelar Visitas</a></li>

				<!-- As opções abaixo serão visíveis apenas para usuários logados -->
				<c:if test="${not empty user}">
					<li><a href="#relatorios"><i class="material-icons left">assessment</i>Relatórios</a></li>
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

	<script>
		document.addEventListener("DOMContentLoaded", function() {
			const elems = document.querySelectorAll(".modal");
			M.Modal.init(elems);
		});
	</script>

</body>
</html>
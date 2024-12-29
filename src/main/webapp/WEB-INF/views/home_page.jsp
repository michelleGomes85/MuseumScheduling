<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Agendamento de Visitas</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css" rel="stylesheet"/>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet"/>
    <link rel="stylesheet" href="resources/css/styles_homepage.css" />
    <link rel="icon" href="resources/images/MS.png" type="image/x-icon"> 
</head>
<body>

	<div class="content">
		<!-- Navbar -->
		<nav class="navbar">
			<div class="nav-wrapper container">
				<ul id="nav-mobile" class="center">
				
					<li><a href="<c:url value='/scheduling_page'/>"><i class="material-icons left">event</i>Agendar
							Visitas</a></li>
					<li><a href="#cancelar"><i class="material-icons left">cancel</i>Cancelar
							Visitas</a></li>
					<li><a class="modal-trigger" href="#relatorios"><i
							class="material-icons left">assessment</i>Relatórios</a></li>
					<li><a href="#cadastrar"><i class="material-icons left">person_add</i>Cadastrar
							Funcionários</a></li>
				</ul>
				<ul class="right user-info">
					<li><span>Usuário Logado</span></li>
					<li><a href="#logout" class="btn out">Sair</a></li>
				</ul>
			</div>
		</nav>

		<!-- Main Content -->
		<div class="main-content">
			<!-- Hero Section-->
			<div class="hero">
				<img alt="" src="resources/images/logo.png">
				<h1>
					Agende e Organize <span>Visitas aos Museus</span>
				</h1>
				<p class="description">Organize suas visitas aos museus de
					Barbacena com facilidade. Agende horários, conheça os museus e
					desfrute de uma experiência única de aprendizado e cultura!</p>
				<div class="buttons-calendar">
					<a href="<c:url value='/scheduling_page'/>" class="btn">Agendar Agora</a>
				</div>
			</div>
		</div>

		<!-- Info Section (Imagens dos Museus) -->
		<div class="info-section">
			<div class="info-card">
				<img src="resources/images/museu_municipal_barbacena.jpg" alt="Museu Municipal"
					class="museum-img" />
				<h5>Museu Municipal de Barbacena</h5>
				<p>
					Horário: 8h às 18h<br />Limite: 5 pessoas por hora
				</p>
			</div>
			<div class="info-card">
				<img src="resources/images/museu-da-loucura.jpg" alt="Museu da Loucura"
					class="museum-img" />
				<h5>Museu da Loucura</h5>
				<p>
					Horário: 9h às 17h<br />Limite: 10 pessoas por hora
				</p>
			</div>
		</div>
	</div>
	<!--content-->

	<!-- Footer -->
	<footer class="footer">
		<div class="container">
			<p>
				Feito por <strong>Michelle Cristina Gomes</strong>
			</p>
			<p>Email: gmichele498@gmail.com</p>
			<p class="icons-contact">
				<a href="https://www.linkedin.com/in/michelleGomes85/"
					target="_blank"> <i class="fab fa-linkedin"></i>
				</a> | <a href="https://github.com/michelleGomes85" target="_blank">
					<i class="fab fa-github"></i>
				</a>
			</p>
			<p class="footer-text">Feito para a disciplina de Desenvolvimento
				de Aplicação Web, graduação em Tecnologia em Sistemas para Internet.
			</p>
		</div>
	</footer>

	<!-- Modal Structure -->
	<div id="relatorios" class="modal">
		<div class="modal-content">
			<h4>Relatórios</h4>
			<ul>
				<li><a href="#">Relatório de agendamentos com data e hora</a></li>
				<li><a href="#">Relatório de lista de pessoas por dia</a></li>
				<li><a href="#">Relatório de dias previstos de visita</a></li>
			</ul>
		</div>
		<div class="modal-footer">
			<a href="#!" class="modal-close btn">Fechar</a>
		</div>
	</div>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
	<script>
		document.addEventListener("DOMContentLoaded", function() {
			const elems = document.querySelectorAll(".modal");
			M.Modal.init(elems);
		});
	</script>
</body>
</html>
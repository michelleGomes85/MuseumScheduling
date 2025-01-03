<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<title>Agendamento de Visitas</title>
	
	<!-- Link para o Favicon -->
    <link rel="icon" href="resources/images/MS.png" type="image/x-icon">

	<!-- Incluindo a biblioteca de ícones do Font Awesome -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
	
	<!-- style - meu -->
    <link rel="stylesheet" href="resources/css/styles_login.css"/>
</head>
<body>
	<div class="login-container">
        <div class="login-box">
            <h2>Login</h2>
            <form action="login" method="POST">
            
                <div class="input-group">
                    <i class="fas fa-user"></i>
                    <input type="text" placeholder="Usuário" name="name" required>
                </div>
                
                <div class="input-group">
                    <i class="fas fa-lock"></i>
                    <input type="password" placeholder="Senha" name="password" id="password" required>
                    <i class="fas fa-eye password-toggle" id="togglePassword" onclick="togglePasswordVisibility()"></i>
                </div>
                
                <button type="submit">Entrar</button>
                
                <input type="text" hidden="true" name="service" value="EfetuaLogin">
            </form>
        </div>
    </div>
       
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
	<script src="resources/script/script_registration_jquery.js"></script>
	<script src="resources/script/script_messages.js"></script>
	<script src="resources/script/script_materialize.js"></script>
</body>
</html>
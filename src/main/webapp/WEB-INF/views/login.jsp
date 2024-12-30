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
    <link rel="icon" href="resources/images/favicon.png" type="image/x-icon">

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
       
    <script type="text/javascript" src="resources/script/script.js"></script>
</body>
</html>
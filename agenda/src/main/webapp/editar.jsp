<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Agenda de Contatos</title>
<link rel="icon" href="Imagens/352510_local_phone_icon.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Editar contato</h1>
	<form name="frmContato" action="">
		<!-- action vai ser a requisição para camada controller -->
		<table>
		<tr>                                                                 <!-- Atributos recebido da class Controller -->     
				<td><input type="text" name="id" id="caixa3" readonly value="<%out.print(request.getAttribute("id"));%>"></td>
				<!-- readonly porque não quero perimitir a alteraççao do id -->
			</tr>
			<tr>
				<td><input type="text" name="nome" class="caixa1" value="<%out.print(request.getAttribute("nome"));%>"></td>
			</tr>
			<tr>
				<td><input type="tel" name="fone" class="caixa2" value="<%out.print(request.getAttribute("fone"));%>" ></td>
			</tr>
			<tr>
				<td><input type="email" name="email" class="caixa1" value="<%out.print(request.getAttribute("email"));%>"></td>
			</tr>
		</table>
		<input type="button" value="Salvar" class="button1"
			onclick="validar()">
		<!-- quando cliclar no botão irá chamar o método validar -->

	</form>
	<script src="scripts/validador.js"></script>
</body>
</html>
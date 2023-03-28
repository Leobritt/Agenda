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
	<form name="frmContato" action="insert">
		<!-- action vai ser a requisição para camada controller -->
		<table>
			<tr>
				<td><input type="text" name="nome" placeholder="Nome"
					class="caixa1"></td>
			</tr>
			<tr>
				<td><input type="tel" name="fone" placeholder="Fone"
					class="caixa2"></td>
			</tr>
			<tr>
				<td><input type="email" name="email" placeholder="Email"
					class="caixa1"></td>
			</tr>
		</table>
		<input type="button" value="Adicionar" class="button1"
			onclick="validar()">
		<!-- quando cliclar no botão irá chamar o método validar -->

	</form>
	<script src="scripts/validador.js"></script>
</body>
</html>
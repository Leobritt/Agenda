<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>
<%
/*Recebendo a lista enviada*/
ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("contatos");

%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Agenda de Contatos</title>
<link rel="icon" href="Imagens/352510_local_phone_icon.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Agenda de Contatos</h1>
	<a href="novo.html" class="button1">Novo Contato</a>
	<table id="tabela">
		<thead>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Fone</th>
				<th>Email</th>
				<th>Opções</th>
			</tr>

		</thead>

		<tbody>
			<%
			for (int i = 0; i < lista.size(); i++) {
			%>

			<tr>
				<td><%=String.valueOf(lista.get(i).getId())%></td>
				<td><%=lista.get(i).getNome()%></td>
				<td><%=lista.get(i).getFone()%></td>
				<td><%=lista.get(i).getEmail()%></td>
				<td><a href="select?id=<%=lista.get(i).getId()%>" 
					class="button1">Editar</a></td>
				<!-- Para enviar o id do contato a ser editado usa a ? que signfica q vai encaminhar um parâmetro -->
				<!-- Nesta situação o parametro é o id e utiliza-se o = e o scriplet p receber o dado -->
			</tr>
			<%}%>

		</tbody>

	</table>
</body>
</html>
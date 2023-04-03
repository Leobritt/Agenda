/**
 * Confirmação de exclusão de um contato
 * @author Leonardo Britto
 */

 function confirmar(id){
	  let resposta = confirm("Confirma a exlcusão desse contato?");
	  
	  if(resposta === true){
		  //comando para fazer redirecionamento 
		  //delete/servelet é o "lugar" para o redirecionamento
		  //?id= encaminhando o parâmetro id concatenando com valor que ele recebeu do campo id
		  window.location.href = "delete?id=" + id;
	  }
 }
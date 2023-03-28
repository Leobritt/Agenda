/**
 * Validação de Formulário
 @author Leonardo Britto
 */

function validar() {
	let nome = frmContato.nome.value
	/**criação da var recebendo do form frmContato do campo nome o valor passado */
	let fone = frmContato.fone.value
	/**criação da var recebendo do form frmContato do campo fone o valor passado */

	if (nome === "") {
		alert('Preencha o campo Nome')
		frmContato.nome.focus()
		/**Posicionado o cursor em cima do form frmContato em cima do campo nome */
		return false
		/**se o usuario não preencher este campo o forms não enviará essas infos p o servlet controller */
	} else if (fone === "") {
		alert('Preencha o campo Fone')
		frmContato.fone.focus()
		return false
	} else { 
		alert('Sucesso')
		document.forms["frmContato"].submit()
		/**SUBMISSÃO DOS DADOS DO FORM frmContato P A CAMADA CONTROLLER */
	}


}

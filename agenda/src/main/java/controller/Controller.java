package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO;
import model.JavaBeans;

@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update", "/delete" })
/*
 * passando url patterns de parametro + o /main as urls são as requisições
 * passando a action insert para a camada controller receber os dados do
 * formulário passando o href select para editar contatos
 */
public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
	JavaBeans contato = new JavaBeans();

	public Controller() {
		super();
	}

	// método principal servlet
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// variavel que ira armazenar o caminho da requisição
		String action = request.getServletPath();
		// verificaçao de requisições
		System.out.println(action);

		// verificação
		if (action.equals("/main")) {
			// chamando o método contatos passando os parametros padrões
			contatos(request, response);
		} else if (action.equals("/insert")) {
			// direcionando ao método responsável por criar novos contatos
			novoContato(request, response);
		} else if (action.equals("/select")) {
			// direcionando ao método responsável por editar os contatos
			listarContato(request, response);
		} else if (action.equals("/update")) {
			editarContato(request, response);
		} else if (action.equals("/delete")) {
			removerContato(request, response);
		} else {
			response.sendRedirect("index.html");
		}

	}

	// Listar contatos
	protected void contatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// criando um objeto que irá receber os dados da javabeans
		ArrayList<JavaBeans> lista = dao.listarContatos();
		// encaminhar a lista ao agenda.jsp pare ser exibido
		request.setAttribute("contatos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);
	}

	// Inserir contatos
	protected void novoContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*
		 * teste de recebimento dos dados do form captura do dados do form atraves do
		 * resquest,getparameter System.out.println(request.getParameter("nome"));
		 * System.out.println(request.getParameter("fone"));
		 * System.out.println(request.getParameter("email"));
		 */

		// setar as variáveis JavaBeans
		// o obj contato armazena o valor que veio do form
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));

		// invocar o método contato classe DAO passando o obj contato
		dao.inserirContato(contato);

		// redirecionar para o documento agenda.jsp
		response.sendRedirect("main");

	}

	// Editar Contato
	protected void listarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// recebendo o parametro ID quem vem do forms
		Integer id = Integer.parseInt(request.getParameter("id"));
		// pegando uma Stringo e convertendo para inteiro

		// setar a var
		contato.setId(id);

		// executar o método selecionarContato
		contato = dao.selecionarContato(contato);

		/*
		 * Teste de Recebimento dos dados que vem do banco
		 * 
		 * System.out.println(contato.getId()); System.out.println(contato.getNome());
		 * System.out.println(contato.getFone());
		 * System.out.println(contato.getEmail());
		 */

		// Setar oos atributos do formulário editar.jsp com conteúdo JavaBeans
		request.setAttribute("id", contato.getId());
		request.setAttribute("nome", contato.getNome());
		request.setAttribute("fone", contato.getFone());
		request.setAttribute("email", contato.getEmail());
		// Encaminhar ao documento editar.jsp
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);

	}

	// Editar Contato
	protected void editarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*
		 * Teste recebimento parametros forms editar
		 * 
		 * System.out.println(request.getParameter("id"));
		 * System.out.println(request.getParameter("nome"));
		 * System.out.println(request.getParameter("fone"));
		 * System.out.println(request.getParameter("email"));
		 */

		// Setar var javaBeans os dados que estão sendo passados no formularios serão
		// armazenados de forma temporaria na JavaBeans

		// fazendo cache forçado
		contato.setId(Integer.parseInt(request.getParameter("id")));
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));

		// executar o método alterar contato
		dao.alterarContato(contato);

		// redirecionando ao agenda.jsp
		response.sendRedirect("main");

	}

	// Remover Contato

	protected void removerContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//recebimento do id do contato a ser excluído (validador.js)
		Integer id = Integer.parseInt(request.getParameter("id"));
		//setar a variável id JavaBeans
		contato.setId(id);
		
		dao.deletarContato(contato);
		
		//obs diferença de botar main e o nome do arquivo é que o main vai executar a ação do método contatos e outro so redireciona 
		response.sendRedirect("main");
	}
}

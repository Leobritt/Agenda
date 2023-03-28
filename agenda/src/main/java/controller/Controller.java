package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO;
import model.JavaBeans;

@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select" })
//passando url patterns de parametro + o /main as urls são as requisições 
//passando a action insert para a camada controller receber os dados do formulário
//passando o href select para editar contatos
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

		// teste padrão servlet
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		// variavel que ira armazenar o caminho da requisição
		String action = request.getServletPath();
		//verificaçao de requisições 
		System.out.println(action);

		// verificação
		if (action.equals("/main")) {
			// chamando o método contatos passando os parametros padrões
			contatos(request, response);
		} else if (action.equals("/insert")) {
			// direcionando ao método responsável por criar novos contatos
			novoContato(request, response);
		}
		else if (action.equals("/select")) {
			// direcionando ao método responsável por editar os contatos
		listarContato(request, response);	
		}else {
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

		// teste de recebimento dos dados do form
		// captura do dados do form atraves do resquest,getparameter
		// System.out.println(request.getParameter("nome"));
		// System.out.println(request.getParameter("fone"));
		// System.out.println(request.getParameter("email"));

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
		//recebendo o parametro ID quem vem do forms 
		String id = request.getParameter("id");
		//setar a var
		contato.setId(id);
		//executar o método selecionarContato
		dao.selecionarContato(contato);
		System.out.println(contato.getId());
	}
}

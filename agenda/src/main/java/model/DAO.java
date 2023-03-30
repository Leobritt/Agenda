package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {
//modulo de conexão
//parametros de conexão

	private String url = "jdbc:postgresql://localhost:5432/dbagenda";
	private String usuario = "postgres";
	private String senha = "123";
	private Connection con;

	private Connection conectar() {
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(url, usuario, senha);
			System.out.println(con);
			return con;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	/* CRUD CREATE */
	// método sem retorno q irá receber como parâmetro o objeto que contem oa dados
	// dos atributos da class JavaBeans ou seja os mesmo que tem no banco
	public void inserirContato(JavaBeans contato) {
		String create = "insert into contatos (nome,fone,email) values (?,?,?)";
		// sempre que usar com banco é necessário usar o try catch

		try {
			// abrir a conexeão
			Connection con = conectar();
			// instrução sql
			PreparedStatement pst = con.prepareStatement(create);

			// contato.get requisitando a classe JavaBeans
			// pst.setString recebimento dos dados da classe JavaBeans
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());

			// executar a query
			pst.executeUpdate();

			// encerrar a conexão com o banco de dados
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/* CRUD READ */
	public ArrayList<JavaBeans> listarContatos() {
		// criando um obj para acessar a classe JavaBeans
		ArrayList<JavaBeans> contatos = new ArrayList<>();
		String read = "select * from contatos order by nome";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);

			// rs objeto da classe usado para armazenar os dados temporariamente quem vem do
			// banco
			ResultSet rs = pst.executeQuery();

			// enquanto tiver contato...
			// método next usado para recuperar todos os contatos do banco
			while (rs.next()) {
				// var de apoio que recebem dados do banco
				Integer id = rs.getInt(1);
				// pegando a primeira coluna...
				String nome = rs.getString(2);
				String fone = rs.getString(3);
				String email = rs.getString(4);

				// colocando os dados no arrayList
				contatos.add(new JavaBeans(id, nome, fone, email));

			}
			con.close();
			return contatos;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/* CRUD UPDATE */
	// selecionar o contato
	public JavaBeans selecionarContato(JavaBeans contato) {
		String read2 = "select * from contatos where id = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read2);

			pst.setInt(1, contato.getId());

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				contato.setId(rs.getInt("id"));
				contato.setNome(rs.getString("nome"));
				contato.setFone(rs.getString("fone"));
				contato.setEmail(rs.getString("email"));

			}
			return contato;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

}
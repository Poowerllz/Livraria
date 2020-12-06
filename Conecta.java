import java.sql.Connection;
import java.sql.DriverManager;

public class Conecta {
	String database, usuario,  senha;
	
	public Conecta(String db, String user, String pass) {
		database = db; usuario = user; senha = pass;
	}
	
	private Connection connect() {
		Connection c = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+database, usuario, senha);
		} catch (Exception e) {
			System.err.println(e.getClass().getName()+": "+e.getMessage());
	        System.exit(0);
		}		
		System.out.println("Conexão bem sucedida!");
		return c;
		
	}
	
	public Connection getConnection() {
		Connection c = connect();
		return c;
	}
}

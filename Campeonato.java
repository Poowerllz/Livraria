import javax.swing.SwingUtilities;

import com.sun.jdi.connect.spi.Connection;

public class Campeonato {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Campeonato();
            }
        });
	}

	public Campeonato() {
		Conecta conn = new Conecta("atividade_livro11", "postgres","admin"); 
		try {
			Gui tela = new Gui(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

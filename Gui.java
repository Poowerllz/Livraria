import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;///

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;



public class Gui {

		JFrame login = new JFrame("Login");
		JFrame janela = new JFrame("UniLibrary");
		JMenuBar mb = new JMenuBar();
		
		JMenu mOperacoes = new JMenu("| Cadastrar |");
		JMenuItem OpListar = new JMenuItem(" | Livros");
		
		JMenu mRelatorios = new JMenu("| Relatórios |");
		JMenuItem ReListarCompras = new JMenuItem(" | Compra");
		JMenuItem ReListarAlugueis = new JMenuItem(" | Aluguéis");

		JPanel container = new JPanel();
		JPanel Lcontainer = new JPanel();
		
		JTextField txt;

		JButton buttonL = new JButton("Logar como Administrador");
		JButton buttonLivro = new JButton("Logar como leitor ( Offline )");

		JButton btOk = new JButton("OK");

		public void TelaAdm(){
			try {
				String imagePath = "C:/Users/Safil/Desktop/src/network-3396348_1280.jpg";
				BufferedImage myPicture = ImageIO.read(new File(imagePath));

				Graphics2D g = (Graphics2D) myPicture.getGraphics();
				
				g.setStroke(new BasicStroke(3));
				g.setColor(Color.RED);

				g.drawRect(1, 1, myPicture.getWidth() - 20, myPicture.getHeight() - 20);

				JLabel picLabel = new JLabel(new ImageIcon(myPicture));
				JPanel jPanel = new JPanel();
				jPanel.add(picLabel);

				janela.setSize(640, 480);
				janela.add(jPanel);
				janela.setVisible(true);

				janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			} catch (Exception e) {
				System.out.println("Falha na compilação da janela main. ( First try )");
			}
		}

		public Gui(Conecta conn) {

			login.setSize(280, 180);
			login.setBackground(Color.red);
			login.setVisible(true);
			login.add(buttonL);
			login.add(buttonLivro);
			login.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			login.setLayout(null);
			buttonL.setBounds(20,30,200,30);
			buttonLivro.setBounds(20,70,200,30);	
			
			container.setBackground(new Color(110,110,110));//cinza escuro
			container.setLayout(new GridLayout(2,2));

			Lcontainer.setBackground(new Color(110,110,110));//cinza escuro
			Lcontainer.setLayout(new GridLayout(2,2));
						
			//acao de listar os clubes
			OpListar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {//a��o a ser realizada ao clicar no OpListar
					Connection c = conn.getConnection();
				
					DefaultListModel<Clube> listModel = new DefaultListModel<Clube>();//tipo de dados
					JList<Clube> lista =  getClubeInfo(c, listModel); //retornar uma lista de clubes
					//Add a um scrollpanel
					JScrollPane sp = new JScrollPane(lista);
					
					container.removeAll();
					container.add(sp);
					container.validate();
					container.repaint();
					
				}
			});

			buttonL.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					TelaAdm();
					login.dispose();
				}
			});

			btOk.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Connection c = conn.getConnection();
					DefaultListModel<DadosJogador> listModel = new DefaultListModel<DadosJogador>();//tipo de dados
					JList<DadosJogador> lista =  getJogadorInfo(c, listModel); //retornar uma lista de clubes
					
					JScrollPane sp = new JScrollPane(lista);
					
					container.removeAll();
					container.add(txt);
					container.add(btOk);
					container.add(sp);
					container.validate();
					container.repaint();
				}
			});
			
			
			mb.add(mOperacoes);
			mb.add(mRelatorios);
			mOperacoes.add(OpListar);
			mRelatorios.add(ReListarCompras);
			mRelatorios.add(ReListarAlugueis);
			
			janela.add(container);
			janela.setJMenuBar(mb);//obrigatorio menubar
			
		}

		protected JList<DadosJogador> getJogadorInfo(Connection c, DefaultListModel<DadosJogador> listModel) {
/* 			String query = " select p.nome as nome, ps.posicao, c.nome as clube, j.idade, j.peso, j.altura "
					+ " from profissional p"
					+ " join clube c on c.id = p.clube_id"
					+ " join jogador j on j.profissional_id = p.id"
					+ " join posicao ps on ps.id = j.posicao_id"
					+ " where c.nome ilike '%"+ txt.getText() +"%' ";
			ResultSet rs = null;
			
			String nome,clube,posicao;
			int idade,  peso,  altura;
			
			try {
				Statement stmt = c.createStatement(); //objeto utilizado para fazer a consulta
				rs = stmt.executeQuery(query); //esta � a consulta
				while (rs.next()) {
					nome = rs.getString("nome");
					clube = rs.getString("clube");
					posicao = rs.getString("posicao");
					idade= rs.getInt("idade");
					peso = rs.getInt("peso");
					altura = rs.getInt("altura");
					listModel.addElement(new DadosJogador(nome,clube,posicao, idade, peso, altura));
				
				}
				rs.close();
				stmt.close();
				c.close();
			} catch (Exception e) {
				System.err.println(e.getClass().getName()+": "+e.getMessage());
		        System.exit(0);
			} */
			JList<DadosJogador> lista = new JList <DadosJogador>(listModel); 
			return lista;
		}

		private JList<Clube> getClubeInfo(Connection c, DefaultListModel<Clube> listModel) {
/* 			String query = "select * from clube";
			ResultSet rs = null;
			int id;//clube
			String nome;
			
			try {
				Statement stmt = c.createStatement(); //objeto utilizado para fazer a consulta
				rs = stmt.executeQuery(query); //esta � a consulta
				while (rs.next()) {
					id = rs.getInt("id");
					nome = rs.getString("nome");
					listModel.addElement(new Clube(nome, id));
				
				}
				rs.close();
				stmt.close();
				c.close();
			} catch (Exception e) {
				System.err.println(e.getClass().getName()+": "+e.getMessage());
		        System.exit(0);
			} */
			JList<Clube> lista = new JList <Clube>(listModel);
			return lista;		
		}
}

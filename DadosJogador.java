
public class DadosJogador {
	private String nome;
	private String clube;
	private String posicao;
	
	private int idade;
	private float peso, altura;
	
	public DadosJogador(String nome, String clube, String posicao, int idade, float peso, float altura) {
		
		this.nome = nome;
		this.clube = clube;
		this.posicao = posicao;
		this.idade = idade;
		this.peso = peso;
		this.altura = altura;
	}

	@Override
	public String toString() {
		return "DadosJogador [nome=" + nome + ", clube=" + clube + ", posicao=" + posicao + ", idade=" + idade
				+ ", peso=" + peso + ", altura=" + altura + "]";
	}
	
}

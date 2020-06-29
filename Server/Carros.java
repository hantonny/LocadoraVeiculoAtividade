
public class Carros {

	private int placa;
	private String nome;
	private int retricao;
	private String locadora;
	private double preco;

	public Carros(int placa, String nome, int retricao, String locadora, double preco) {
		super();
		this.placa = placa;
		this.nome = nome;
		this.retricao = retricao;
		this.locadora = locadora;
		this.preco = preco;
	}

	public int getPlaca() {
		return placa;
	}

	public void setPlaca(int placa) {
		this.placa = placa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getRetricao() {
		return retricao;
	}

	public void setRetricao(int retricao) {
		this.retricao = retricao;
	}

	public String getLocadora() {
		return locadora;
	}

	public void setLocadora(String locadora) {
		this.locadora = locadora;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

}

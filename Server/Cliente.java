
public class Cliente {
	private int id;
	private String nome;
	private String categoriaHabilitacao;
	
	public Cliente(int id, String nome, String categoriaHabilitacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.categoriaHabilitacao = categoriaHabilitacao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCategoriaHabilitacao() {
		return categoriaHabilitacao;
	}

	public void setCategoriaHabilitacao(String categoriaHabilitacao) {
		this.categoriaHabilitacao = categoriaHabilitacao;
	}
	
}

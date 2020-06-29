import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class Matriz extends UnicastRemoteObject implements LocadoraRemota {

	private static final long serialVersionUID = 1L;
	private Locadoras[] locadoras;
	private Carros[] carros;
	private Cliente[] clientes;
	int proxima;
	private static Scanner ler;
	private static Registry r;

	public Matriz(int tamanho) throws RemoteException {

		super();

		this.locadoras = new Locadoras[tamanho];
		this.carros = new Carros[tamanho];
		this.clientes = new Cliente[tamanho];
		this.proxima = 0;
	}

	// Cadastrar Locadoras
	public synchronized void inserirLocadora(String nome, String login, String senha) throws RemoteException {

		if (this.proxima == this.locadoras.length) {

			proxima = 0;
		}

		locadoras[proxima] = new Locadoras(nome, login, senha);
		proxima++;
	}

	// Procurar Locadora
	public synchronized int procurarPosicao(String nome) {

		int i = 0;

		while (i < this.locadoras.length) {

			if (this.locadoras[i] != null && locadoras[i].getNome().equalsIgnoreCase(nome)) {
				return i;
			}
			i++;
		}

		return -1;
	}

	public synchronized void procurarLocadora(String nome) throws RemoteException, Excecao {

		int i = this.procurarPosicao(nome);

		if (i != -1) {

		} else
			throw new Excecao("Locadora \"" + nome + "\" n�o foi encontrado!");

	}

	// Autenticar Locadora
	public synchronized int autenticarLocadora(String login, String senha) {

		int i = 0;

		while (i < this.locadoras.length) {

			if (this.locadoras[i] != null && locadoras[i].getLogin().equals(login)
					&& locadoras[i].getSenha().equals(senha)) {
				return i;
			}
			i++;
		}

		return -1;
	}

	public synchronized boolean autenticar(String login, String senha) throws RemoteException, Excecao {

		int i = this.autenticarLocadora(login, senha);

		if (i != -1) {
			return true;
		} else
			throw new Excecao("Login ou Senha incorreta");

	}

	// Carros
	public synchronized void inserirCarros(int placa, String nome, int retricao, String locadora, double preco)
			throws RemoteException {

		if (this.proxima == this.carros.length) {

			proxima = 0;
		}

		carros[proxima] = new Carros(placa, nome, retricao, locadora, preco);
		proxima++;
	}

	public synchronized void ListarCarros() throws RemoteException {

		int i = 0;
		String[] msg = new String[carros.length];
		while (i < this.carros.length) {

			if (this.carros[i].getRetricao() == 0) {
				msg[i] = "Carro: " + carros[i].getNome() + " com Placa Final: " + carros[i].getPlaca();
				System.out.println(msg[i]);
			}

			i++;
		}

	}

	// Clientes
	public synchronized void inserirClientes(int id, String nome, String categoriaHabilitacao) throws RemoteException {

		if (this.proxima == this.clientes.length) {

			proxima = 0;
		}

		clientes[proxima] = new Cliente(id = proxima, nome, categoriaHabilitacao);
		proxima++;
	}

	public static void main(String args[]) {
		ler = new Scanner(System.in);

		int escolha = 0;
		try {
			setR(LocateRegistry.createRegistry(2127));

			while (escolha != 3) {
				String nome;
				String login;
				String senha;
				int placa;
				String nomecarro;
				int retricao;
				String carrolocadora;
				double preco;
				System.out.printf("--------------------------------\n");
				System.out.printf("Opera��es\n");
				System.out.println("1 - Cadastrar Locadoras");
				System.out.println("2 - Cadastrar Carros");
				System.out.printf("Qual opera��o voce escolhe?\n");
				escolha = ler.nextInt();
				switch (escolha) {
				case 1:
					Matriz locadora = new Matriz(10);

					Naming.rebind("rmi://localhost:2127/locadora", locadora);
					System.out.printf("Informe o nome da Locadora:\n");

					nome = ler.next();

					System.out.printf("Informe o login da Locadora:\n");

					login = ler.next();

					System.out.printf("Informe o senha da Locadora:\n");

					senha = ler.next();
					locadora.inserirLocadora(nome, login, senha);
					System.out.println("Locadora \"" + nome + "\" inserido com sucesso!");
					break;
				case 2:
					Matriz carro = new Matriz(10);

					Naming.rebind("rmi://localhost:2127/carro", carro);

					System.out.printf("Informe a Placa do Carro:\n");
					placa = ler.nextInt();

					System.out.printf("Informe a Nome do Carro:\n");
					nomecarro = ler.next();

					System.out.printf("Informe a Retricao do Carro:\n");
					System.out.printf("0 - Para carros dispon�veis:\n");
					System.out.printf("1 - Para carros indispon�veis:\n");
					retricao = ler.nextInt();

					System.out.printf("Informe a Locadora do Carro:\n");
					carrolocadora = ler.next();

					System.out.printf("Informe a preco do Carro:\n");
					preco = ler.nextDouble();

					carro.inserirCarros(placa, nomecarro, retricao, carrolocadora, preco);

					System.out.println("Carro \"" + nomecarro + "\" inserido com sucesso!");

					break;
				case 3:
					System.out.printf("Saindo");

					break;

				}
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();

		}

	}

	@Override
	public void inserirClientes(String nome, String categoriaHabilitacao) throws RemoteException {
		// TODO Auto-generated method stub

	}

	public static Registry getR() {
		return r;
	}

	public static void setR(Registry r) {
		Matriz.r = r;
	}

}
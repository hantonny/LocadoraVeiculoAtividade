import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class ClienteLocadora {

	private static Scanner ler;

	public static void main(String args[]) throws UnsupportedEncodingException {
		ler = new Scanner(System.in);

		int escolha = 0;

		// Autenticar Locadora
		String login;
		String senha;
		System.out.printf("Entrar:");
		System.out.printf("Login:\n");
		login = ler.next();
		System.out.printf("Senha:\n");
		senha = ler.next();
		//

		// Clientes
		String nomeCliente;
		String categoriaHabilitacao;
		//

		try {
			LocadoraRemota locadora = (LocadoraRemota) Naming.lookup("rmi://localhost:2127/locadora");
			LocadoraRemota carro = (LocadoraRemota) Naming.lookup("rmi://localhost:2127/carro");
			if (locadora.autenticar(login, senha)) {
				while (escolha != 3) {
					System.out.printf("Escolhas as operações\n");
					System.out.printf("1 - Cadastrar Clientes \n");
					System.out.printf("2 - Listar Carros \n");
					escolha = ler.nextInt();
					switch (escolha) {
					case 1:
						System.out.printf("Informe o nome do Cliente:\n");

						nomeCliente = ler.next();

						System.out.printf("Informe a Categoria Habilitação:\n");

						categoriaHabilitacao = ler.next();

						locadora.inserirClientes(nomeCliente, categoriaHabilitacao);
						System.out.println("Cliente " + nomeCliente + " com a Categoria Habilitação "
								+ categoriaHabilitacao + " inserido com sucesso!");

						// Localizar Locadora - achei importante deixar
						/*
						 * try { System.out.printf("Informe o nome da Locadora:\n"); String buscanome =
						 * ler.next(); locadora.procurarLocadora(buscanome);
						 * System.out.println("Locadora \"" + buscanome + "\" encontrado!"); } catch
						 * (Excecao e1) { System.out.println(e1.getMessage()); }
						 */
						break;
					case 2:

						carro.ListarCarros();

						break;
					case 3:
						System.out.printf("Obrigado, ter você como cliente é uma satisfação\n");

						break;

					}
				}

			}
		} catch (Excecao e1) {
			System.out.println(e1.getMessage());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

	}

}

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
		String login;
		String senha;
		System.out.printf("Entrar:");
		System.out.printf("Login:\n");
		login = ler.next();
		System.out.printf("Senha:\n");
		senha = ler.next();

		try {
			LocadoraRemota locadora = (LocadoraRemota) Naming.lookup("rmi://localhost:2127/locadora");
			if (locadora.autenticar(login, senha)) {
				while (escolha != 3) {
					System.out.printf("Escolhas as operações\n");
					escolha = ler.nextInt();
					switch (escolha) {
					case 1:
						try {
							System.out.printf("Informe o nome da Locadora:\n");
							String buscanome = ler.next();
							locadora.procurarLocadora(buscanome);
							System.out.println("Locadora \"" + buscanome + "\" encontrado!");
						} catch (Excecao e1) {
							System.out.println(e1.getMessage());
						}
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

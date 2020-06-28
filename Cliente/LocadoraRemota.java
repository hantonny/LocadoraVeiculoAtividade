import java.rmi.Remote;
import java.rmi.RemoteException;

public interface LocadoraRemota extends Remote {
	
	//Locadora
	public void inserirLocadora(String nome, String login, String senha) throws RemoteException; 
	public void procurarLocadora (String nome) throws RemoteException, Excecao;
	public boolean autenticar (String login, String senha) throws RemoteException, Excecao;
}

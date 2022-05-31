import java.util.ArrayList;

public class Conta extends Entidade{
	String login;
	String senha;
	ArrayList<String> convite;
	ArrayList<String> amigos;
	ArrayList<String> feed;
	int privFeed;
	
	Conta(String nome, String login, String senha) throws IllegalArgumentException{
        if(nome == null || nome.isBlank()) {
        	throw new IllegalArgumentException("Nome nao pode ser vazio!");
        }
        if(login == null || login.isBlank()) {
        	throw new IllegalArgumentException("Login nao pode ser vazio!");
        }
        if(senha == null || senha.isBlank()) {
        	throw new IllegalArgumentException("Senha nao pode ser vazio!");
        }
        this.nome =  nome;
        this.login = login;
        this.senha = senha;
        this.privFeed = 0;
        
        this.convite = new ArrayList<String>();
        this.amigos = new ArrayList<String>();
        this.mensagens = new ArrayList<Mensagem>();
        this.feed = new ArrayList<String>();
	}
	
	
	public void Autenticador(String senha) throws Exception {
		if(!this.senha.equals(senha)) {
			throw new Exception("Senha invalida");
		}
	}
	
}

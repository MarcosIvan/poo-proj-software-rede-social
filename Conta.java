import java.util.ArrayList;

public class Conta extends Entidade{
	String login;
	String senha;
	ArrayList<String> convite;
	ArrayList<String> amigos;
	ArrayList<String> feed;
	int privFeed;
	
	Conta(String nome, String login, String senha){
        this.nome =  nome;
        this.login = login;
        this.senha = senha;
        this.privFeed = 0;
        
        this.convite = new ArrayList<String>();
        this.amigos = new ArrayList<String>();
        this.mensagens = new ArrayList<Mensagem>();
        this.feed = new ArrayList<String>();
	}
	
	public boolean Autenticador(String senha) {
		return this.senha.equals(senha);
	}
	
}

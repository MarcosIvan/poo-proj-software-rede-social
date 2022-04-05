import java.util.ArrayList;

public class Comunidade {
	String nome, descricao;
	Conta dono;
	ArrayList<Conta> membros;
	ArrayList<Mensagem> mensagens;
	
	Comunidade(String nome, String descricao, Conta dono){
		this.nome = nome;
		this.descricao = descricao;
		this.dono = dono;
		this.membros = new ArrayList<Conta>();
		this.mensagens = new ArrayList<Mensagem>();
	}

}

import java.util.ArrayList;

public class Comunidade extends Entidade {
	String descricao;
	Conta dono;
	ArrayList<Conta> membros;
	
	
	Comunidade(String nome, String descricao, Conta dono){
		this.nome = nome;
		this.descricao = descricao;
		this.dono = dono;
		this.membros = new ArrayList<Conta>();
		this.mensagens = new ArrayList<Mensagem>();
	}

}

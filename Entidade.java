import java.util.ArrayList;

public abstract class Entidade {
	protected String nome;
	protected ArrayList<Mensagem> mensagens;
	
	public void receberMensagem(Mensagem mensagem) {
		this.mensagens.add(mensagem);
	}
}

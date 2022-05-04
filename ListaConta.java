import java.util.ArrayList;
import java.util.List;


public class ListaConta implements Lista<Conta> {

	public List<Conta> contas = new ArrayList<Conta>();
	
	public void add(Conta c) {
		this.contas.add(c);
	}

	public Conta find(String nome) {
		for(int i = 0; i < contas.size(); i++) {
			if(contas.get(i).login.equals(nome)) {
				return contas.get(i);
			}
		}
		System.out.printf("Login nao encontrado\n");
		return null;
	}
	
	public int size() {
		return this.contas.size();
	}
	
	public Conta get(int i) {
		return this.contas.get(i);
	}
	
	public void remove(Conta conta) {
		for(int i = 0; i < this.contas.size(); i++) {
			if(this.contas.get(i).nome.equals(conta.nome)) {
				this.contas.remove(i);
			}
		}
	}

}
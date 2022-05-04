import java.util.ArrayList;
import java.util.List;


public class ListaComunidade implements Lista<Comunidade> {

	public List<Comunidade> comunidades = new ArrayList<Comunidade>();
	
	public void add(Comunidade c) {
		this.comunidades.add(c);
	}

	public Comunidade find(String nome) {
		for(int i = 0; i < this.comunidades.size(); i++) {
			if(this.comunidades.get(i).nome.equals(nome)) {
				return this.comunidades.get(i);
			}
		}
		System.out.printf("Comunidade  nao  encontrada\n");
		return null;
	}
	
	public int size() {
		return this.comunidades.size();
	}
	
	public Comunidade get(int i) {
		return this.comunidades.get(i);
	}
	
	public void remove(Comunidade comunidade) {
		for(int i = 0; i < this.comunidades.size(); i++) {
			if(this.comunidades.get(i).nome.equals(comunidade.nome)) {
				this.comunidades.remove(i);
			}
		}
	}

}

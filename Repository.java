
public interface Repository<T extends Entidade> {
	public void add(T valor);
	public T find(String nome); 
}

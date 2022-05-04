
public interface Lista<T extends Entidade> {
	public void add(T valor);
	public T find(String nome); 
	public int size();
	public T get(int i);
	public void remove(T valor);
}

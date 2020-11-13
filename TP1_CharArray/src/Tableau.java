
public interface Tableau<E> {
	int size();
	E get(int index);
	void set (int index, E c);
	int indexOf(E c);
}

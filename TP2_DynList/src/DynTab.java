
public class DynTab<E> implements DynList<E> {

	E [] tab; // Pour stocker
	int s; // Pour la taille
	
	public DynTab () {
		tab = (E[]) new Object[1];
		s = 0;
	}
	
	@Override
	public int size() {
		return s;
	}
	protected int capacity() {
		return tab.length;
	}

	protected void checkCapacity(int incr) {
		if (this.capacity() >= incr + this.size()) return;
		tab = (E[]) new Object[this.capacity() * 2];
		this.checkCapacity(incr);
	}
	
	@Override
	public void add(int index, E element) {
		E previous [] = tab;
		// IndexOutOfBoundsException - if the index is out of range (index < 0 || index > size())
		if (index < 0 || index > this.size()) throw new IndexOutOfBoundsException();
		this.checkCapacity(1);
		// ** ATTENTION ** On copie par la fin
		for (int i = this.size()-1; i >= index; i--) {
			tab[i+1] = previous[i];
		}
		tab[index] = element;
		for (int i = index - 1; i >= 0; i--)
			tab[i] = previous[i];
		s++;
	}

	@Override
	public boolean add(E e) {
		this.add(this.size(), e);
		return true;
	}

	@Override
	public void clear() {
		tab = (E[]) new Object[1];
		s = 0;
	}

	@Override
	public boolean contains(Object o) {
		return this.indexOf(o) > -1;
	}

	@Override
	public E get(int index) {
		// ** Attention ** il faut tester la borne sup
		// IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size())
		if (index >= this.size())
			throw new IndexOutOfBoundsException();
		return tab[index];
	}

	@Override
	public int indexOf(Object o) {
		for (int pos = 0; pos < size(); pos++) {
			E item = tab[pos];
			if (item.equals(o))	 
				return pos;		
		}
		return -1;
	}

	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}

	@Override
	public int lastIndexOf(Object o) {
		for (int pos = this.size()-1; pos >= 0; pos--) {
			E item = tab[pos];
			if (item.equals(o))	 
				return pos;		
		}
		return -1;
	}

	@Override
	public E remove(int index) {
		if (index < 0 || index >= this.size()) throw new IndexOutOfBoundsException();
		E element = tab[index];
		for (int i = index; i < this.size() -1; i++)
			tab[i] = tab[i+1];
		s--;
		return element;
	}

	@Override
	public boolean remove(Object o) {
		int pos = indexOf(o);
		if (pos > -1) {
			this.remove(pos);
			return true;
		}
		return false;
	}

	@Override
	public Object[] toArray() {
		Object [] arr = new Object[this.size()];
		for (int i = 0; i < this.size(); i++)
			arr[i] = tab[i];
		return arr;
	}

}

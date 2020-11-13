package col;

import java.util.Arrays;
import java.util.NoSuchElementException;

import sdr.ChaineSimple;

/**
 * Mise en oeuvre de l'interface <tt>Set</tt> avec une chaîne simple d'objets.
 *
 * @param <E> type des éléments de l'ensemble.
 */
public class SetChaineSimple<E> implements Set<E> {

	/**
	 * Chaîne des éléments de l'ensemble.
	 */
	protected ChaineSimple<E> debut;

	/**
	 * Nombre d'éléments dans l'ensemble.
	 */
	protected int size;

	/**
	 * Constructeur : crée un ensemble vide.
	 */
	public SetChaineSimple() {
		this.debut = null;
		this.size = 0;
	}

	@Override
	public String toString() {
		String res = "{ ";
		for (ChaineSimple<E> c = this.debut; c != null; c = c.suivant)
			res += c.valeur + " ";
		return res + "}";
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null) return false;
		if (!(o instanceof Set)) return false;
		Set<E> s;
		try { s = (Set<E>) o; }
		catch (Exception ex) { return false; }
		return this.size() == s.size() && this.containsAll(s);
	}

	// ------------------------------------------------- Méthodes de Collection

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public void clear() {
		this.debut = null;
		this.size = 0;
	}

	@Override
	public boolean add(E e) {
		if (this.contains(e)) return false;
		this.debut = new ChaineSimple<>(e, this.debut);
		this.size++;
		return true;
	}

	@Override
	public boolean remove(E e) {
		for (ChaineSimple<E> c = new ChaineSimple<>(null, this.debut); c.suivant != null; c = c.suivant) {
			if (c.suivant.valeur.equals(e)) {
				if (c.suivant == this.debut)
 					 this.debut = this.debut.suivant;
				else c.supprimeElementSuivant();
				this.size--;
				return true;
			}
		}
		return false;

		/*
		// Version avec itérateur :
		for (Iterator<? extends E> it = this.iterator(); it.hasNext();) {
			if (it.next().equals(e)) {
				it.remove();
				return true;
			}
		}
		return false;
		*/

	}

	@Override
	public boolean contains(E e) {
		for (ChaineSimple<E> c = this.debut; c != null; c = c.suivant) {
			if (c.valeur.equals(e))
				return true;
		}
		return false;
		
		/*
		// Version avec itérateur :
		for (Iterator<? extends E> it = this.iterator(); it.hasNext();) {
			if (it.next().equals(e))
				return true;
		}
		return false;
		*/

	}

	@Override
	public E[] toArray(E[] a) {
		E[] res = (a.length >= this.size ? a : Arrays.copyOf(a, this.size));
		ChaineSimple<E> c;
		int i;
		for (c = this.debut, i = 0; c != null; c = c.suivant, i++)
			res[i] = c.valeur;
		for (; i < res.length; i++)
			res[i] = null;
		return res;
	}

	// ------------------------------------------------- Méthodes de Set

	@Override
	public Iterator<E> iterator() {
		return new SetChaineSimpleIterator();
	}

	/**
	 * Itérateur de <tt>SetChaineSimple</tt>.
	 */
	private class SetChaineSimpleIterator implements Iterator<E> {

		/**
		 * Chaîne dont l'élément suivant est le prochain élément à lire.
		 */
		private ChaineSimple<E> prevNext;

		/**
		 * Chaîne dont l'élément suivant est le prochain élément à supprimer.
		 */
		private ChaineSimple<E> prevRemove;

		/**
		 * Constructeur : positionne l'itérateur au début de la chaîne.
		 */
		public SetChaineSimpleIterator() {
			this.prevNext = new ChaineSimple<>(null, SetChaineSimple.this.debut);
			this.prevRemove = null;
		}

		@Override
		public boolean hasNext() {
			return this.prevNext.suivant != null;
		}

		@Override
		public E next() {
			if (this.prevNext.suivant == null)
				throw new NoSuchElementException();
			this.prevRemove = this.prevNext;
			this.prevNext = this.prevNext.suivant;
			return this.prevNext.valeur;
		}

		@Override
		public void remove() {
			if (this.prevRemove == null)
				throw new IllegalStateException();
			if (this.prevNext == SetChaineSimple.this.debut) {
				SetChaineSimple.this.debut = SetChaineSimple.this.debut.suivant;
				this.prevNext = this.prevRemove;
				this.prevNext.supprimeElementSuivant();
			}
			else {
				this.prevRemove.supprimeElementSuivant();
				this.prevNext = this.prevRemove;
			}
			this.prevRemove = null;
			SetChaineSimple.this.size--;
		}
	}

}

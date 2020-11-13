package col;

import java.util.Arrays;
import java.util.NoSuchElementException;

import sdr.ArbreBinaire;
import sdr.ChaineSimple;

/**
 * Mise en oeuvre de l'interface <tt>SortedSet</tt> avec un arbre binaire de
 * recherche.
 *
 * @param <E> type des éléments de l'ensemble.
 */
public class SortedSetArbreBinaire<E extends Comparable<E>> implements SortedSet<E> {

	/**
	 * Arbre des éléments de l'ensemble.
	 */
	protected ArbreBinaire<E> arbre;

	/**
	 * Nombre d'éléments dans l'ensemble.
	 */
	protected int size;

	/**
	 * Constructeur : crée un ensemble vide.
	 */
	public SortedSetArbreBinaire() {
		this.arbre = null;
		this.size = 0;
	}

	@Override
	public String toString() {
		String res = "{ ";
		for (Iterator<? extends E> it = this.iterator(); it.hasNext();)
			res += it.next() + " ";
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
		this.arbre = null;
		this.size = 0;
	}

	@Override
	public boolean add(E e) {
		if (this.arbre == null) {
			this.arbre = new ArbreBinaire<>(e);
			this.size++;
			return true;
		}
		boolean res = this.arbre.ajoute(e);
		if (res) this.size++;
		return res;
	}

	@Override
	public boolean remove(E e) {
		if (this.arbre == null) return false;
		if (this.arbre.feuille()) {
			if (this.arbre.valeur.equals(e)) {
				this.arbre = null;
				this.size = 0;
				return true;
			}
			return false;
		}
		boolean res = this.arbre.supprime(e);
		if (res) this.size--;
		return res;
	}

	@Override
	public boolean contains(E e) {
		if (this.arbre == null) return false;
		return this.arbre.contient(e);
	}

	@Override
	public E[] toArray(E[] a) {
		E[] res = (a.length >= this.size ? a : Arrays.copyOf(a, this.size));
		ChaineSimple<E> c;
		int i;
		for (c = (this.arbre == null ? null : this.arbre.infixe()), i = 0; c != null; c = c.suivant, i++)
			res[i] = c.valeur;
		for (; i < res.length; i++)
			res[i] = null;
		return res;
	}

	// ------------------------------------------------- Méthodes de Set
	
	@Override
	public Iterator<E> iterator() {
		return new SortedSetArbreBinaireIterator();
	}
	/**
	 * Itérateur dédié à la mise en oeuvre de <tt>SortedSetArbreBinaire</tt>.
	 */
	private class SortedSetArbreBinaireIterator implements Iterator<E> {

		/**
		 * Chaîne des valeurs à parcourir.
		 */
		private ChaineSimple<E> valeurs;

		/**
		 * Chaîne dont la valeur peut être supprimée au prochain appel de remove.
		 */
		private ChaineSimple<E> remove;

		/**
		 * Constructeur : positionne l'itérateur au début de la chaîne.
		 */
		public SortedSetArbreBinaireIterator() {
			if (SortedSetArbreBinaire.this.arbre == null)
				this.valeurs = null;
			else
				this.valeurs = SortedSetArbreBinaire.this.arbre.infixe();
			this.remove = null;
		}

		@Override
		public boolean hasNext() {
			return this.valeurs != null;
		}

		@Override
		public E next() {
			if (this.valeurs == null)
				throw new NoSuchElementException();
			this.remove = this.valeurs;
			this.valeurs = this.valeurs.suivant;
			return this.remove.valeur;
		}

		@Override
		public void remove() {
			if (this.remove == null)
				throw new IllegalStateException();
			SortedSetArbreBinaire.this.remove(this.remove.valeur);
			this.remove = null;
		}

	}

	// ------------------------------------------------- Méthodes de SortedSet

	@Override
	public SortedSet<E> subSet(E fromElement, E toElement) {
		SortedSetArbreBinaire<E> res = new SortedSetArbreBinaire<>();
		if (this.arbre == null)
			return res;
		for (ChaineSimple<E> c = this.arbre.extraire(fromElement, toElement); c != null; c = c.suivant)
			res.add(c.valeur);
		return res;
	}

	@Override
	public E first() {
		if (this.isEmpty())
			throw new NoSuchElementException();
		return this.arbre.minimum();
	}

	@Override
	public E last() {
		if (this.isEmpty())
			throw new NoSuchElementException();
		return this.arbre.maximum();
	}

}

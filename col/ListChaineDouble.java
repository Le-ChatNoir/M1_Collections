package col;

import java.util.Arrays;

import sdr.ChaineDouble;

/**
 * Mise en oeuvre de l'interface <tt>List</tt> avec une chaîne double d'objets.
 *
 * @param <E> type des éléments de la liste.
 */
public class ListChaineDouble<E> implements List<E> {

	/**
	 * Début de la chaîne des éléments de la liste.
	 */
	protected ChaineDouble<E> debut;

	/**
	 * Fin de la chaîne des éléments de la liste.
	 */
	protected ChaineDouble<E> fin;

	/**
	 * Dernier chaînon atteint par add, get, set ou remove.
	 */
	protected ChaineDouble<E> courant;

	/**
	 * Indice du chainon courant.
	 */
	protected int indiceCourant;

	/**
	 * Nombre d'éléments dans la liste.
	 */
	protected int size;

	/**
	 * Constructeur : crée une liste vide.
	 */
	public ListChaineDouble() {
		this.debut = null;
		this.fin = null;
		this.courant = null;
		this.indiceCourant = -1;
		this.size = 0;
	}

	@Override
	public String toString() {
		String res = "( ";
		for (ChaineDouble<E> c = this.debut; c != null; c = c.suivant)
			res += c.valeur + " ";
		return res + ")";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null) return false;
		if (!(o instanceof List)) return false;
		List<?> s = (List<?>) o;
		if (this.size != s.size()) return false;
		int i = 0;
		for (ChaineDouble<E> c = this.debut; c != null; c = c.suivant)
			if (!c.valeur.equals(s.get(i++))) return false;
		return true;
	}

	// ------------------------------------------------- Méthode utilitaire

	/**
	 * Positionnement du chainon courant à un indice valide.
	 * 
	 * @param i indice valide d'élément à atteindre.
	 */
	protected void positionne(int i) {
		if (i < this.indiceCourant / 2) // on part du début
			for (this.courant = this.debut, this.indiceCourant = 0; this.indiceCourant < i; this.indiceCourant++)
				this.courant = this.courant.suivant;
		else if (i < this.indiceCourant) // on part de courant vers le début
			for (; this.indiceCourant > i; this.indiceCourant--)
				this.courant = this.courant.precedent;
		else if (i < (this.size + this.indiceCourant) / 2) // on part de courant vers la fin
			for (; this.indiceCourant < i; this.indiceCourant++)
				this.courant = this.courant.suivant;
		else // on part de la fin
			for (this.courant = this.fin, this.indiceCourant = this.size
					- 1; this.indiceCourant > i; this.indiceCourant--)
				this.courant = this.courant.precedent;
	}

	// ------------------------------------------------- Méthodes de Collection

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public void clear() {
		this.debut = null;
		this.fin = null;
		this.courant = null;
		this.indiceCourant = -1;
		this.size = 0;
	}

	@Override
	public boolean add(E e) {
		this.add(this.size, e);
		return true;
	}

	@Override
	public boolean remove(E e) {
		int i = this.indexOf(e);
		if (i == -1) return false;
		this.remove(i);
		return true;
	}

	@Override
	public boolean contains(E e) {
		return this.indexOf(e) != -1;
	}

	@Override
	public E[] toArray(E[] a) {
		E[] res = (a.length >= this.size ? a : Arrays.copyOf(a, this.size));
		ChaineDouble<E> c;
		int i;
		for (c = this.debut, i = 0; c != null; c = c.suivant, i++)
			res[i] = c.valeur;
		for (; i < res.length; i++)
			res[i] = null;
		return res;
	}

	// ------------------------------------------------- Méthodes de List

	@Override
	public E get(int index) {
		if (index < 0 || index >= this.size)
			throw new IndexOutOfBoundsException();
		this.positionne(index);
		return this.courant.valeur;
	}

	@Override
	public E set(int index, E element) {
		if (index < 0 || index >= this.size)
			throw new IndexOutOfBoundsException();
		this.positionne(index);
		E res = this.courant.valeur;
		this.courant.valeur = element;
		return res;
	}

	@Override
	public void add(int index, E element) {
		if (index < 0 || index > this.size)
			throw new IndexOutOfBoundsException();
		if (this.size == 0) {
			this.debut = this.fin = this.courant = new ChaineDouble<>(element);
			this.indiceCourant = 0;
		}
		else if (index == 0) {
			this.debut = this.debut.insereElementPrecedent(element);
			this.indiceCourant++;
		}
		else if (index == this.size) {
			this.fin = this.fin.insereElementSuivant(element);
		}
		else {
			this.positionne(index);
			this.courant = this.courant.insereElementPrecedent(element);
		}
		this.size++;
	}

	@Override
	public boolean addAll(int index, List<E> c) {
		if (c.isEmpty()) return false;
		for (int i = 0; i < c.size(); i++)
			this.add(index + i, c.get(i));
		return true;
	}

	@Override
	public E remove(int index) {
		if (index < 0 || index >= this.size)
			throw new IndexOutOfBoundsException();
		E res;
		if (this.size == 1) {
			res = this.debut.valeur;
			this.debut = this.fin = this.courant = null;
			this.indiceCourant = -1;
		}
		else if (index == 0) {
			res = this.debut.valeur;
			this.debut = this.debut.suivant;
			this.debut.supprimeElementPrecedent();
			if (this.indiceCourant == 0) this.courant = this.debut;
			else this.indiceCourant--;
		} 
		else if (index == this.size - 1) {
			res = this.fin.valeur;
			this.fin = this.fin.precedent;
			this.fin.supprimeElementSuivant();
			if (this.indiceCourant == this.size - 1) {
				this.courant = this.fin;
				this.indiceCourant--;
			}
		} 
		else {
			this.positionne(index - 1);
			res = this.courant.suivant.valeur;
			this.courant.supprimeElementSuivant();
		}
		this.size--;
		return res;
	}

	@Override
	public int indexOf(E e) {
		int res = 0;
		for (ChaineDouble<E> c = this.debut; c != null; c = c.suivant)
			if (c.valeur.equals(e)) return res;
			else res++;
		return -1;
	}

	@Override
	public int lastIndexOf(E e) {
		int res = this.size - 1;
		for (ChaineDouble<E> c = this.fin; c != null; c = c.precedent)
			if (c.valeur.equals(e)) return res;
			else res--;
		return -1;
	}

}

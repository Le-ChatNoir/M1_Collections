package col;

import java.util.NoSuchElementException;

import sdr.ChaineSimple;

/**
 * Mise en oeuvre de l'interface <tt>SortedSet</tt> avec une chaîne simple
 * d'objets héritée de <tt>ChaineSet</tt>.
 *
 * @param <E> type des éléments de l'ensemble.
 */
public class SortedSetChaineSimple<E extends Comparable<E>> extends SetChaineSimple<E> implements SortedSet<E> {

	/**
	 * Constructeur : crée un ensemble vide.
	 */
	public SortedSetChaineSimple() {
		super();
	}

	// ------------------------------------------------- Méthodes de SortedSet

	@Override
	public SortedSet<E> subSet(E fromElement, E toElement) {
		SortedSetChaineSimple<E> res = new SortedSetChaineSimple<>();
		Iterator<E> it;
		// on cherche le premier élément du résultat :
		for (it = this.iterator(); it.hasNext() && res.isEmpty();) {
			E next = it.next();
			if (next.compareTo(fromElement) >= 0 && next.compareTo(toElement) < 0) {
				res.debut = new ChaineSimple<>(next);
				res.size = 1;
			}
		}
		// on rajoute les autres éléments :
		for (ChaineSimple<E> finRes = res.debut; it.hasNext();) {
			E next = it.next();
			if (next.compareTo(fromElement) >= 0 && next.compareTo(toElement) < 0) {
				finRes = finRes.insereElementSuivant(next);
				res.size++;
			}
		}
		return res;
	}

	@Override
	public E first() {
		if (this.isEmpty()) throw new NoSuchElementException();
		Iterator<E> it = this.iterator();
		E min = it.next();
		while (it.hasNext()) {
			E next = it.next();
			if (next.compareTo(min) < 0) min = next;
		}
		return min;
	}

	@Override
	public E last() {
		if (this.isEmpty()) throw new NoSuchElementException();
		Iterator<E> it = this.iterator();
		E max = it.next();
		while (it.hasNext()) {
			E next = it.next();
			if (next.compareTo(max) > 0) max = next;
		}
		return max;
	}

}

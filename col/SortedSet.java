package col;

import java.util.NoSuchElementException;

/**
 * Ensemble dont les éléments sont soumis à une relation d'ordre total.
 * 
 * @param <E> type des éléments de l'ensemble.
 */
public interface SortedSet<E extends Comparable<E>> extends Set<E> {

	/**
	 * Renvoie s'il existe le plus petit élément de l'ensemble.
	 * 
	 * @return plus petit élément de l'ensemble.
	 * @throws NoSuchElementException si l'ensemble est vide.
	 */
	E first();

	/**
	 * Renvoie s'il existe le plus grand élément de l'ensemble.
	 * 
	 * @return plus grand élément de l'ensemble.
	 * @throws NoSuchElementException si l'ensemble est vide.
	 */
	E last();

	/**
	 * Renvoie l'ensemble des éléments compris entre deux bornes.
	 * 
	 * @param fromElement borne inférieure (valeur incluse).
	 * @param toElement   borne supérieure (valeur exclue).
	 * @return éléments appartenant à [fromElement : toElement[.
	 */
	SortedSet<E> subSet(E fromElement, E toElement);

}

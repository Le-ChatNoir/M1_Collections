package col;

/**
 * Itérateur d'ensemble.
 *
 * @param <E> type des éléments de l'ensemble parcouru.
 */
public interface Iterator<E> {

	/**
	 * Indique si l'itérateur peut être déplacé.
	 * 
	 * @return vrai l'itérateur n'est pas déjà en fin de parcours, faux sinon.
	 */
	boolean hasNext();

	/**
	 * Déplace l'itérateur et renvoie la valeur située devant l'itérateur avant son
	 * déplacement.
	 * 
	 * @return valeur située devant l'itérateur avant son déplacement.
	 * @throws java.util.NoSuchElementException si l'itérateur est déjà en fin de
	 *         parcours
	 */
	E next();

	/**
	 * Supprime le dernier élément renvoyé par next.
	 * 
	 * @throws IllegalStateException si next n'a pas été exécuté avant, ou si remove
	 *                               a déjà été exécuté depuis.
	 */
	void remove();
}

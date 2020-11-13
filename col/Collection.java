package col;

/**
 * Interface racine de la hiérarchie des collections.
 *
 * @param <E> type des éléments de la collection.
 */
public interface Collection<E> {

	/**
	 * Renvoie le nombre d'éléments de la collection.
	 * 
	 * @return nombre d'éléments de la collection.
	 */
	int size();

	/**
	 * Indique si la collection est vide ou non.
	 * 
	 * @return vrai si la collection est vide, faux sinon.
	 */
	default boolean isEmpty() {
		return this.size() == 0;
	}

	/**
	 * Vide la collection.
	 */
	void clear();

	/**
	 * Ajoute si possible un élément à la collection.
	 * 
	 * @param e élément à ajouter.
	 * @return vrai si e a été ajouté, faux sinon.
	 */
	boolean add(E e);

	/**
	 * Supprime si possible un élément de la collection.
	 * 
	 * @param e élément à supprimer.
	 * @return vrai si e a été supprimé, faux sinon.
	 */
	boolean remove(E e);

	/**
	 * Indique si la collection contient un certain élément.
	 * <p>
	 * Plus formellement, renvoie vrai si et seulement si la collection contient au
	 * moins un élément <tt>e</tt> tel que
	 * <tt>(o==null ? e==null : o.equals(e))</tt>.
	 *
	 * @param e élément recherché.
	 * @return vrai si e est présent, faux sinon.
	 */
	boolean contains(E e);

	/**
	 * Renvoie un tableau contenant tous les éléments de la collection.
	 *
	 * <p>
	 * Si la collection peut être contenue dans le tableau fourni en paramètre,
	 * alors le tableau est mis à jour et renvoyé en résultat. Les premières cases
	 * du tableau contiennent les éléments de la collection. Les autres contiennent
	 * null.
	 *
	 * <p>
	 * Si la collection ne peut pas être contenue dans le tableau, alors un nouveau
	 * tableau est créé et renvoyé en résultat. Il contient exactement tous les
	 * éléments de la colection.
	 *
	 * @param a tableau à mettre à jour s'il est assez grand.
	 * @return tableau contenant les éléments de la collection.
	 */
	E[] toArray(E[] a);

}

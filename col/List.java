package col;

/**
 * Liste : collection dont les éléments sont organisés en <i>séquence</i> et
 * sont adressables par <i>indices</i>.
 * 
 * @param <E> type des éléments de la liste.
 */
public interface List<E> extends Collection<E> {

	/**
	 * Renvoie s'il existe l'élément situé à un certain indice.
	 * 
	 * @param index indice de l'élément recherché.
	 * @return élément d'indice index.
	 * @throws IndexOutOfBoundsException si l'indice est invalide
	 *                                   (<tt>index &lt; 0 || index &ge; size()</tt>)
	 */
	E get(int index);

	/**
	 * Met à jour s'il existe l'élément situé à un certain indice.
	 * 
	 * @param index   indice de l'élément recherché.
	 * @param element nouvelle valeur de l'élément d'indice index.
	 * @return ancienne valeur de l'élément d'indice index.
	 * @throws IndexOutOfBoundsException si l'indice est invalide
	 *                                   (<tt>index &lt; 0 || index &ge; size()</tt>)
	 */
	E set(int index, E element);

	/**
	 * Insère un élément à un certain indice.
	 * 
	 * @param index   indice de l'élément à insérer.
	 * @param element valeur de l'élément à insérer.
	 * @throws IndexOutOfBoundsException si l'indice est invalide
	 *                                   (<tt>index &lt; 0 || index &gt; size()</tt>)
	 */
	void add(int index, E element);

	/**
	 * Ajoute en fin de liste tous les éléments d'une autre liste fournie en
	 * paramètre.
	 * 
	 * @param c liste des éléments à ajouter.
	 * @return vrai si ou moins un élément de c a été ajouté, faux sinon.
	 */
	default boolean addAll(List<E> c) {
		return this.addAll(this.size(), c);
	}

	/**
	 * Insère à un certain indice tous les éléments d'une autre liste fournie en
	 * paramètre.
	 * 
	 * @param index indice d'insertion.
	 * @param c     liste des éléments à ajouter.
	 * @return vrai si ou moins un élément de c a été ajouté, faux sinon.
	 * @throws IndexOutOfBoundsException si l'indice est invalide
	 *                                   (<tt>index &lt; 0 || index &gt; size()</tt>)
	 */
	boolean addAll(int index, List<E> c);

	/**
	 * Supprime et renvoie s'il existe l'élément situé à un certain indice.
	 * 
	 * @param index indice de l'élément à supprimer.
	 * @return élément supprimé.
	 * @throws IndexOutOfBoundsException si l'indice est invalide
	 *                                   (<tt>index &lt; 0 || index &ge; size()</tt>)
	 */
	E remove(int index);

	/**
	 * Renvoie l'indice de la première occurrence dans la liste d'un certain objet
	 * fourni en paramètre.
	 * 
	 * @param e objet recherché.
	 * @return plus petit indice de o ou -1.
	 */
	int indexOf(E e);

	/**
	 * Renvoie l'indice de la dernière occurrence dans la liste d'un certain objet
	 * fourni en paramètre.
	 * 
	 * @param e objet recherché.
	 * @return plus grand indice de o ou -1.
	 */
	int lastIndexOf(E e);

}

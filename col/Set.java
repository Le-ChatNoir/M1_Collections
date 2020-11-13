package col;

/**
 * Ensemble : collection ne comportant aucun élément en double.
 * 
 * @param <E> type des éléments de l'ensemble.
 */
public interface Set<E> extends Collection<E> {

	/**
	 * Renvoie un nouvel itérateur positionné sur l'ensemble.
	 * 
	 * @return nouvel itérateur.
	 */
	Iterator<E> iterator();

	/**
	 * Inclusion : indique si l'ensemble contient tous les éléments d'un autre
	 * ensemble fourni en paramètre.
	 * 
	 * @param c ensemble des éléments à rechercher.
	 * @return vrai si l'ensemble contient tous les éléments de c, faux sinon.
	 */
	default boolean containsAll(Set<E> c) {
		for (Iterator<E> it_c = c.iterator(); it_c.hasNext();)
			if (!this.contains(it_c.next()))
				return false;
		return true;
	}

	/**
	 * Union : ajoute tous les éléments d'un autre ensemble fourni en paramètre.
	 * 
	 * @param c ensemble des éléments à ajouter.
	 * @return vrai si ou moins un élément de c a été ajouté, faux sinon.
	 */
	default boolean addAll(Set<E> c) {
		boolean res = false;
		for (Iterator<E> it_c = c.iterator(); it_c.hasNext();)
			if (this.add(it_c.next()))
				res = true;
		return res;
	}

	/**
	 * Intersection : supprime tous les éléments qui n'apparaissent pas dans un
	 * autre ensemble fourni en paramètre.
	 * 
	 * @param c ensemble des éléments susceptibles d'être conservés.
	 * @return vrai si ou moins un élément a été supprimé, faux sinon.
	 */
	default boolean retainAll(Set<E> c) {
		boolean res = false;
		for (Iterator<E> it_this = this.iterator(); it_this.hasNext();) {
			E next = it_this.next();
			if (!c.contains(next)) {
				it_this.remove();
				res = true;
			}
		}
		return res;
	}

	/**
	 * Différence : supprime tous les éléments qui apparaissent dans un autre
	 * ensemble fourni en paramètre.
	 * 
	 * @param c ensemble des éléments susceptibles d'être supprimés.
	 * @return vrai si ou moins un élément a été supprimé, faux sinon.
	 */
	default boolean removeAll(Set<E> c) {
		boolean res = false;
		for (Iterator<E> it_c = c.iterator(); it_c.hasNext();)
			if (this.remove(it_c.next()))
				res = true;
		return res;
	}

}

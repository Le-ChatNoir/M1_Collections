
interface DynList<E> {
	void add(int index, E element); // Insert e à la position index (même règles
									// que celles utilisées en cours)

	boolean add(E e); // Ajoute e à la fin du tableau

	void clear(); // Vide le tableau

	boolean contains(Object o); // Retourne true si o est contenu dans le
								// tableau

	boolean equals(Object o); // Compare deux tableaux (receveur et argument)

	E get(int index); // Retourne l’élément contenu à la position index

	int indexOf(Object o); // Retourne la position de o ou -1 s’il n’est pas
							// contenu dans le tableau

	boolean isEmpty(); // Retourne true si le tableau est vide

	int lastIndexOf(Object o); // Retourne le deernier index de o ou -1 s’il
								// n’est pas contenu dans le tableau

	E remove(int index); // Enlève l’élément contenu à la position index

	boolean remove(Object o); // Enlève o du tableau, retourne true s’il a été
								// enlevé (il a été trouvé et retiré) false
								// sinon

	int size(); // Retourne le nombre d’éléments du tableau

	Object[] toArray(); // Retourne un tableau de bas niveau contenant tous les
						// éléments
}

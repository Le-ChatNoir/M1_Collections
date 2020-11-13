
import java.nio.BufferOverflowException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* Table de hachage fermée avec paramètres (P) */

public class ClosedHashDict<K, V> implements Map<K, V> {
	/* Mise en cache du nombre d'éléments */
	private int s;

	/* Tableau de couples (table hash fermée) */
	protected Couple<K, V> tab[];

	/* Définit un maximum par défaut pour le tableau d'entrées */
	private final static int NB_COUPLES_MAX_DEFAUT = 100;

	/* Constructeur */
	public ClosedHashDict() {
		this(NB_COUPLES_MAX_DEFAUT);
	}

	/* Constructeur avec max en paramètre */
	public ClosedHashDict(int i) {
		s = 0;
		tab = new Couple[i];
	}

	/* Retourne le nombre d'éléments */
	public int size() {
		return s;
	}

	/* Capacité */
	public int capacity() {
		return tab.length;
	}

	/* Est vide */
	public boolean isEmpty() {
		return s == 0;
	}

	/* Index de départ */
	private int idxFromKey(Object k) {
		if (k == null)
			throw new NullPointerException();
		return Math.abs(k.hashCode()) % capacity();
	}

	private int idxNext(int idx) {
		return (idx + 1) % capacity();
	}

	/* Est-ce que l'objet est contenu dans notre table */
	public boolean containsKey(Object k) {
		int idx = idxFromKey(k);
		int start = idx;

		/* Couple<K,V> cp = new Couple<>((K)k,null); */

		do {

			Entry<K, V> c = tab[idx];
			if (c != null && (c.getKey().equals(k)/* ou cp.equals(c) */)) {
				return true;
			}

			idx = idxNext(idx);
		} while (idx != start);

		return false;
	}

	/* Est-ce que l'objet contient la valeur */
	public boolean containsValue(Object v) {
		int i = 0;
		while (i < tab.length) {
			Entry<K, V> c = tab[i];
			if (c != null) {
				V v2 = c.getValue();
				if (v2 == null) {
					return v == null;
				} else if (v != null && v2.equals(v)) {
					return true;
				}
			}
			i++;
		}
		return false;
	}

	/* Obtenir le couple correspondant à la clé */
	private Entry<K, V> getEntryAt(K k) {
		int idx = idxFromKey(k);
		int start = idx;

		/* Couple<K,V> cp = new Couple<>((K)k,null); */

		do {

			Entry<K, V> c = tab[idx];
			if (c != null && (c.getKey().equals(k)/* ou cp.equals(c) */)) {
				return c;
			}

			idx = idxNext(idx);
		} while (idx != start);

		return null;
	}

	/* Obtenir la valeur depuis clé correspondante */
	public V get(Object k) {
		Entry<K, V> c = getEntryAt((K) k);
		if (c == null)
			return null;
		return c.getValue();
	}

	/* Ajouter une valeur */
	public V put(K k, V v) {
		Entry<K, V> c = getEntryAt((K) k);
		if (c != null) {
			V v2 = (V) c.getValue();
			c.setValue(v);

			/* Peut également retourner null, mais conforme JavaDoc */
			return v2;
		}

		int idx = idxFromKey(k);
		int start = idx;

		do {
			if (tab[idx] == null) {
				s++;
				tab[idx] = new Couple<>(k, v);
				return null;
			}

			idx = idxNext(idx);
		} while (idx != start);

		throw new BufferOverflowException();
	}

	/* Supprimer par sa clé */
	public V remove(Object k) {
		int idx = idxFromKey(k);
		int start = idx;

		/* Couple<K,V> cp = new Couple<>((K)k,null); */

		do {
			Entry<K, V> c = tab[idx];
			if (c != null && (c.getKey().equals(k)/* ou cp.equals(c) */)) {
				tab[idx] = null;
				s--;
				return (V) c.getValue();
			}

			idx = idxNext(idx);
		} while (idx != start);

		return null;
	}

	/* Ajouter tout */
	public void putAll(Map<? extends K, ? extends V> m) {
		Set i = m.entrySet();
		Iterator iter = i.iterator();
		while (iter.hasNext()) {
			Entry<K, V> c = (Entry<K, V>) iter.next();
			put(c.getKey(), c.getValue());
		}
	}

	/* Nettoyer */
	public void clear() {
		s = 0;
		for (int i = 0; i < this.capacity(); i++)
			tab[i] = null;
	}

	/* Obtenir clés */
	public Set<K> keySet() {
		Set<K> set = new HashSet<>();
		int i = 0;
		while (i < this.capacity()) {
			Entry<K, V> c = tab[i];
			if (c != null) {
				set.add((K) c.getKey());
			}
			i++;
		}
		return set;
	}

	/* Collection de valeurs (peut contenir plusieurs fois null) */
	public Collection<V> values() {
		Collection<V> col = new ArrayList<>();
		int i = 0;
		while (i < this.capacity()) {
			Entry<K, V> c = tab[i];
			if (c != null) {
				col.add((V) c.getValue());
			}
			i++;
		}
		return col;
	}

	/* Récupération des entrées */
	public Set<Entry<K, V>> entrySet() {
		Set<Entry<K, V>> set = new HashSet<>();
		int i = 0;
		while (i < this.capacity()) {
			Entry<K, V> c = tab[i];
			if (c != null) {
				set.add(c);
			}
			i++;
		}
		return set;
	}

}

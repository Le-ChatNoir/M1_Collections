/*
* L'utilisation d'un tableau K/V est très peu efficace en cas de recherche d'une valeur précise. Si le tableau est très long, il faudra parcourir toutes les valeurs.
* Nous pouvons donc utiliser differentes méthodes alternatives:
* - Nous pouvons les regrouper selon un ordre à l'aide d'arbres
* - Nous pouvons les regrouper en ensembles
* - Table de Hachage Ouverte <-
*/

interface Map<K,V>{
	V get(K key);
	V put(K key);
	V remove(K key);
}

class TableHashFermee<K,V> implements Map<K,V>{
	List<Couple<K,V>>[] tab;
	final int N = 1024;

	//Constructor
	TableHashFermee(){
		tab = new ArrayList<Couple<K,V>>[N]; //Tab est donc un tableau d'ArrayLists
	}


	V get (K key){
		int pos = Math.abs(key.hashCode())%N;
		List<Couple> l = tab[pos];

		if(l == null){
			//Erreur d'utilisation -> Exception!
			return null;
		}

		Iterator<Couple> itor = l.iterator();
		while(itor.hasNext()){
			Couple<K,V> cpl = itor.next();
			if(cpl.getKey().equals(key))
				return cpl.getValue();
		}
		//Erreur d'utilisation -> Exception!
		return null;
	}

	V put(K key, V value){
		int pos = Math.abs(key.hashCode())%N;
		List<Couple> l = tab[pos];

		if(l == null){
			l = new ArrayList<Couple>();
			tab[pos] = l;
		}
		Couple<K,V> cpl = getCouple(l, k);
		if(cpl != null){
			V previousV = cpl.getValue();
			cpl.setValue(value);
			return previousV;
		}
		cpl = new Couple(key, value);
		l.add(cpl);
		return null;
	}

	Couple<K,V> getCouple(List<Couple<K,V>> l, K key){
		Iterator itor<K,V> = l.iterator();
		while(itor.hasNext()){
			Couple<K,V> cpl = itor.next();

			if((cpl.getKey()).equals(key))
				return cpl;
		}
		return null;
	}

	V remove(K key){
		int pos = Math.abs(key.hashCode())%N;
		List<Couple> l = tab[pos];

		if(l == null)
			return null;

		for(int i = 0; i< l.size(); i++){
			Couple<K,V> curr = l.get(i);
			if(curr.getKey().equals(key)){
				V previousV = curr;
				l.remove(i);
				return previousV;
			}
		}
		return null;
	}

}

class Couple{
	K key;
	V value;

	//Funcs
	getKey
	getValue
	setValue

	//Constructor
	Couple(K key, V value){

	}
}
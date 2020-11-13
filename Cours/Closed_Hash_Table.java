class THFermee<K,V> implements Map<K,V>{
	final int N = 1024;
	Couple[] tab;

	THFermee(){
		tab = new Couple[N];
	}

	int size(){
		int s = 0;
		for(int i=0; i<N; i++){
			if(tab[i]!=null)
				s++;
		}
		return s;
	}


	V put(K key, V value){
		int pos = key.hashCode()%N;
		int t = pos;

		do{
			if(tab[t] != null){
				Couple cpl = tab[t];
				if(cpl.getKey().equals(key)){
					V previousV = cpl.getValue();
					cpl setValue(value);
					return previousV;
				}
			}
			t = (t+1)%N;
		} while(t != pos);

		t = pos;
		do{
			if(tab[t] == null){
				Couple cpl = new Couple(key, value);
				tab[t] = cpl;
				return null;
			}
			t = (t+1)%N;
		} while(t!=pos);
		throw (new Error("Table is full"));
	}

	

}
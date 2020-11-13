Interface Critere<E>{
	boolean passe(E e);
}

List recherche (Critere c){
	List resultat = new ArrayList();
	Iterator itor = etudiants.iterator();

	while(itor.hasNext()){
		Etudiant curr = itor.next();
		if(c.passe(curr)){
			resultat.add(curr);
		}
	}
	return resultat;
}

//Recherche par nom
Class ParNom implements Critere<Etudiant>{
	String n;
	ParNom(String n) {
		this.n = n;
	}

	boolean passe(Etudiant e){
		return.e.getNom().equals(n);
	}
}

//test
testCritereParNom(){
	Promotion p = new Promotion();
	p.addEtudiant(new Etudiant("Bob"));
	p.addEtudiant(new Etudiant("Bill"));

	List n = p.recherche(new ParNom("Bob"));
	assertTrue(r.size() == 1);
}
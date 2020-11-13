
public class DynTab2 {
	static class Sentinelle {}
	static Sentinelle FIN = new Sentinelle();
	Object [] l;
	
	public DynTab2 () {
		l = new Object [1];
		l[0] = FIN;
	}
	
	int size () {
		int s = 0;
		while (l[s] != FIN) s++;
		return s;
	}
	
	
}

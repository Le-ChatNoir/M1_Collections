
public class CharArray implements Tableau<Character> {
	Character t[];

	public CharArray(String s) {
		t = new Character[s.length()];
		for (int i = 0; i < s.length(); i++)
			t[i] = s.charAt(i);
	}

	private CharArray(int s) {
		t = new Character[s];
	}

	public String toString() {
		String s = "";
		for (int i = 0; i < this.size(); i++) {
			s += get(i);
		}
		return s;
	}

	public int size() {
		return capacity();
	}

	protected int capacity() {
		return t.length;
	}

	public Character get(int index) {
		// Le controle d'index est effectue par la VM Java
		return t[index];
	}

	public void set(int index, Character c) {
		// Le controle d'index est effectue par la VM Java
		t[index] = c;
	}

	public int indexOf(Character c) {
		for (int i = 0; i < this.size(); i++) {
			if (c.equals(t[i]))
				return i;
		}
		return -1;
	}

	public CharArray concat(CharArray ch2) {
		return new CharArray(this.toString()+ch2.toString());
	}

	public Boolean containsSubStartingAt(CharArray sub, int start) {
		if (start < 0 || start >= this.size()) {
			throw new ArrayIndexOutOfBoundsException(start);
		}
		int myidx = start;
		int subidx = 0;
		while (myidx < this.size() && subidx < sub.size()) {
			if (! (get(myidx).equals(sub.get(subidx))) ) {
				return false;
			}
		}
		return subidx == sub.size();
	}
	
	public Boolean containsSub(CharArray sub) {
		if (sub.size() == 0) return true;
		int start = indexOf(sub.get(0));
		if (start == -1) return false;
		return containsSubStartingAt (sub, start);		
	}

	
	public CharArray trim() {
		int start = 0;
		while (start < this.size() && (this.get(start)).equals(' ')) {
			start ++;
		}
		if (start == this.size()) {
			return new CharArray(0);
		}
		int stop = this.size() - 1;
		while ((this.get(stop)).equals(' ')) {
			stop--;
		}
		return copyFromTo(start, stop);
	}

	public CharArray copyFromTo(int start, int stop) {
		if (start < 0 || start >= this.size()) {
			throw new ArrayIndexOutOfBoundsException(start);
		}
		if (stop < 0 || stop >= this.size()) {
			throw new ArrayIndexOutOfBoundsException(stop);
		}

		CharArray result = new CharArray(stop - start + 1);
		for (int i = 0; i < result.size(); i++) {
			result.set(i, this.get(i+start));
		}
		return result;
	}
	
}

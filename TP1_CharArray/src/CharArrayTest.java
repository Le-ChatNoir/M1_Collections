import static org.junit.Assert.*;

import org.junit.Test;

public class CharArrayTest {

	@Test
	public void testCharArray1() {
		CharArray t = new CharArray("");
		assertTrue(t.size()==0);		
		assertTrue(t.capacity()==0);		
	}
	@Test
	public void testCharArray2() {
		CharArray t = new CharArray("A");
		assertTrue(t.size()==1);
		assertTrue(t.get(0).equals('A'));
		assertTrue(t.capacity()==1);		
	}

	@Test
	public void testToString1() {
		CharArray t = new CharArray("");
		assertTrue(t.toString().equals(""));
	}
	@Test
	public void testToString2() {
		CharArray t = new CharArray("AB");
		assertTrue(t.toString().equals("AB"));
	}

	@Test
	public void testSet() {
		CharArray t = new CharArray("AB");
		assertTrue(t.toString().equals("AB"));
		t.set(0, 'a');
		assertTrue(t.toString().equals("aB"));
	}

	@Test
	public void testSet2() {
		CharArray t = new CharArray("AB");
		assertTrue(t.toString().equals("AB"));
		try {
			t.set(2, 'c');
			assertFalse(true);
		} catch (IndexOutOfBoundsException e) {
			assertTrue(true);
		} catch (Exception e ) {
			assertTrue(false);
		}
	}

	@Test
	public void testIndexOf1() {
		CharArray t = new CharArray("");
		assertTrue(t.indexOf('A') == -1);
	}

	@Test
	public void testIndexOf2() {
		CharArray t = new CharArray("A");
		assertTrue(t.indexOf('A') == 0);
	}

	@Test
	public void testIndexOf3() {
		CharArray t = new CharArray("ABC");
		assertTrue(t.indexOf('C') == 2);
	}

	@Test
	public void testConcat1() {
		CharArray t1 = new CharArray("ABC");	
		CharArray t2 = new CharArray("");
		CharArray t3 = t1.concat(t2);
		assertTrue(t3.get(0).equals('A'));
		assertTrue(t3.get(1).equals('B'));
		assertTrue(t3.get(2).equals('C'));
		
	}
	
	@Test
	public void testConcat2() {
		CharArray t1 = new CharArray("ABC");	
		CharArray t2 = new CharArray("DEF");
		CharArray t3 = t1.concat(t2);
		assertTrue(t3.get(0).equals('A'));
		assertTrue(t3.get(1).equals('B'));
		assertTrue(t3.get(2).equals('C'));
		assertTrue(t3.get(3).equals('D'));
		assertTrue(t3.get(4).equals('E'));
		assertTrue(t3.get(5).equals('F'));
		
	}

	@Test
	public void testContainsSubStartingAt0() {
		CharArray t = new CharArray("ABC");
		CharArray sub1 = new CharArray("");
		assertTrue(t.containsSubStartingAt(sub1, 0));
		assertTrue(t.containsSubStartingAt(sub1, 1));
		assertTrue(t.containsSubStartingAt(sub1, 2));
		try {
			t.containsSubStartingAt(sub1, 3);
			assertFalse(true);
		} catch (IndexOutOfBoundsException e) {
			assertTrue(true);
		} catch (Exception e ) {
			assertTrue(false);
		}
	}

	@Test
	public void testContainsSubStartingAt1() {
		CharArray t = new CharArray("ABCABCAB");
		CharArray sub1 = new CharArray("A");
		CharArray sub2 = new CharArray("AB");
		CharArray sub3 = new CharArray("ABC");
		CharArray sub4 = new CharArray("ABCD");
		assertTrue(t.containsSubStartingAt(sub1, 0));
		assertTrue(t.containsSubStartingAt(sub2, 0));
		assertTrue(t.containsSubStartingAt(sub3, 0));
		assertFalse(t.containsSubStartingAt(sub4, 0));
		
		assertTrue(t.containsSubStartingAt(sub1, 3));
		assertTrue(t.containsSubStartingAt(sub2, 3));
		assertTrue(t.containsSubStartingAt(sub3, 3));
		assertFalse(t.containsSubStartingAt(sub4, 3));

		assertTrue(t.containsSubStartingAt(sub1, 6));
		assertTrue(t.containsSubStartingAt(sub2, 6));
		assertFalse(t.containsSubStartingAt(sub3, 6));
		assertFalse(t.containsSubStartingAt(sub4, 6));
	}

	@Test
	public void testContainsSub0() {
		CharArray t = new CharArray("ABCABCAB");
		CharArray sub1 = new CharArray("");
		assertTrue(t.containsSub(sub1));
	}
	
	@Test
	public void testContainsSub1() {
		CharArray t = new CharArray("ABCABCAB");
		CharArray sub1 = new CharArray("A");
		CharArray sub2 = new CharArray("AB");
		CharArray sub3 = new CharArray("ABC");
		CharArray sub4 = new CharArray("ABCD");
		assertTrue(t.containsSub(sub1));
		assertTrue(t.containsSub(sub2));
		assertTrue(t.containsSub(sub3));
		assertFalse(t.containsSub(sub4));
	}

	@Test
	public void testTrim0() {
		CharArray t = new CharArray("");
		assertTrue(t.trim().toString().equals(""));
	}

	@Test
	public void testTrim1() {
		CharArray t = new CharArray("A");
		assertTrue(t.trim().toString().equals("A"));
	}
	@Test
	public void testTrim2() {
		CharArray t = new CharArray(" A");
		assertTrue(t.trim().toString().equals("A"));
	}
	@Test
	public void testTrim3() {
		CharArray t = new CharArray("A ");
		assertTrue(t.trim().toString().equals("A"));
	}
	@Test
	public void testTrim4() {
		CharArray t = new CharArray("  A  ");
		assertTrue(t.trim().toString().equals("A"));
	}

	@Test
	public void testCopyFromTo() {
		CharArray t = new CharArray("ABCABCAB");
		assertTrue(t.copyFromTo(1,1).toString().equals("B"));
		assertTrue(t.copyFromTo(1,2).toString().equals("BC"));
		assertTrue(t.copyFromTo(0,t.size()-1).toString().equals(t.toString()));
		assertTrue(t.copyFromTo(5,4).toString().equals(""));
		try {
			t.copyFromTo(-1, 3);
			assertFalse(true);
		} catch (IndexOutOfBoundsException e) {
			assertTrue(true);
		} catch (Exception e ) {
			assertTrue(false);
		}
		try {
			t.copyFromTo(1, 13);
			assertFalse(true);
		} catch (IndexOutOfBoundsException e) {
			assertTrue(true);
		} catch (Exception e ) {
			assertTrue(false);
		}
	}

}

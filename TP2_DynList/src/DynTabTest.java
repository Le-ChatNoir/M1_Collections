import static org.junit.Assert.*;

import org.junit.Test;

public class DynTabTest {

	@Test
	public void testAdd1() {
		DynTab<String> dl = new DynTab<String>();
		assertTrue(dl.isEmpty());
		assertTrue(dl.size() == 0);
		assertTrue(dl.capacity() == 1);
		dl.add("A");
		assertTrue(dl.size() == 1);
		assertTrue(dl.capacity() == 1);
		dl.add("B");
		assertTrue(dl.size() == 2);
		assertTrue(dl.capacity() == 2);
		dl.add("C");
		assertTrue(dl.size() == 3);
		assertTrue(dl.capacity() == 4);
		dl.add("D");
		assertTrue(dl.size() == 4);
		assertTrue(dl.capacity() == 4);
		dl.add("E");
		assertTrue(dl.size() == 5);
		assertTrue(dl.capacity() == 8);
	}

	@Test
	public void testAdd2() {
		DynTab<String> dl = new DynTab<String>();
		assertTrue(dl.isEmpty());
		assertTrue(dl.size() == 0);
		assertTrue(dl.capacity() == 1);
		dl.add(0, "A");
		assertTrue(dl.size() == 1);
		assertTrue(dl.capacity() == 1);
		dl.add(1, "B");
		assertTrue(dl.size() == 2);
		assertTrue(dl.capacity() == 2);
		dl.add(0, "C");
		assertTrue(dl.size() == 3);
		assertTrue(dl.capacity() == 4);
		dl.add(1, "D");
		assertTrue(dl.size() == 4);
		assertTrue(dl.capacity() == 4);
		dl.add(2, "E");
		assertTrue(dl.size() == 5);
		assertTrue(dl.capacity() == 8);
		assertTrue(dl.indexOf("A") == 3);
		assertTrue(dl.indexOf("B") == 4);
		assertTrue(dl.indexOf("C") == 0);
		assertTrue(dl.indexOf("D") == 1);
		assertTrue(dl.indexOf("E") == 2);
	}

	@Test
	public void testAdd3() {
		DynTab<String> dl = new DynTab<String>();
		dl.add("A");
		dl.add("B");
		try {
			dl.add(-10, "C");
			assertTrue(true);
		} catch (Exception e) {
			assertTrue (e instanceof IndexOutOfBoundsException);
		}
		assertTrue(dl.indexOf("A") == 0);
		assertTrue(dl.indexOf("B") == 1);
		assertTrue(dl.indexOf("C") == -1);
	}

	@Test
	public void testAdd4() {
		DynTab<String> dl = new DynTab<String>();
		dl.add("A");
		dl.add("B");
		dl.add(0, "C");
		assertTrue(dl.indexOf("A") == 1);
		assertTrue(dl.indexOf("B") == 2);
		assertTrue(dl.indexOf("C") == 0);
	}

	@Test
	public void testAdd5() {
		DynTab<String> dl = new DynTab<String>();
		dl.add("A");
		dl.add("B");
		dl.add(1, "C");
		assertTrue(dl.indexOf("A") == 0);
		assertTrue(dl.indexOf("B") == 2);
		assertTrue(dl.indexOf("C") == 1);
	}

	@Test
	public void testAdd6() {
		DynTab<String> dl = new DynTab<String>();
		dl.add("A");
		dl.add("B");
		dl.add(2, "C");
		assertTrue(dl.indexOf("A") == 0);
		assertTrue(dl.indexOf("B") == 1);
		assertTrue(dl.indexOf("C") == 2);
	}

	@Test
	public void testAdd7() {
		DynTab<String> dl = new DynTab<String>();
		dl.add("A");
		dl.add("B");
		try {
			dl.add(30, "C");
			assertTrue(true);
		} catch (Exception e) {
			assertTrue (e instanceof IndexOutOfBoundsException);
		}
		assertTrue(dl.indexOf("A") == 0);
		assertTrue(dl.indexOf("B") == 1);
		assertTrue(dl.indexOf("C") == -1);
	}


	@Test
	public void testClear() {
		DynTab<String> dl = new DynTab<String>();
		dl.add("A");
		dl.add("B");
		assertTrue(dl.size() == 2);
		dl.clear();
		assertTrue(dl.size() == 0);
		assertTrue(dl.capacity() == 1);
		assertTrue(dl.isEmpty());
	}

	@Test
	public void testContains() {
		DynTab<String> dl = new DynTab<String>();
		assertTrue(dl.indexOf("A") == -1);
		assertFalse(dl.contains("A"));
		dl.add("A");
		assertTrue(dl.indexOf("A") == 0);
		assertTrue(dl.contains("A"));
		dl.remove("A");
		assertTrue(dl.indexOf("A") == -1);
		assertFalse(dl.contains("A"));		
	}

	@Test
	public void testGet() {
		DynTab<String> dl = new DynTab<String>();
		String s = "A";
		dl.add(0, s);
		assertTrue(dl.indexOf(s) == 0);
		assertTrue(dl.get(0) == s);
	}

	@Test
	public void testLastIndexOf() {
		DynTab<String> dl = new DynTab<String>();
		dl.add("A");
		dl.add("A");
		assertTrue(dl.indexOf("A") == 0);
		assertTrue(dl.lastIndexOf("A") == 1);
	}

	@Test
	public void testRemoveInt() {
		DynTab<String> dl = new DynTab<String>();
		dl.add("A");
		assertTrue(dl.size() == 1);
		dl.remove(0);
		assertTrue(dl.size() == 0);
	}
	@Test
	public void testRemoveInt2() {
		DynTab<String> dl = new DynTab<String>();
		dl.add("A");
		dl.add("B");
		assertTrue(dl.size() == 2);
		dl.remove(0);
		assertTrue(dl.size() == 1);
		assertTrue(dl.get(0).equals("B"));
	}
	@Test
	public void testRemoveInt3() {
		DynTab<String> dl = new DynTab<String>();
		try {
			dl.remove(0);
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(e instanceof IndexOutOfBoundsException);
		}
	}

	@Test
	public void testRemoveObject() {
		DynTab<String> dl = new DynTab<String>();
		dl.add("A");
		dl.add("B");
		assertTrue(dl.size() == 2);
		dl.remove("B");
		assertTrue(dl.size() == 1);
		assertTrue(dl.get(0).equals("A"));
		dl.remove("A");
		assertTrue(dl.size() == 0);
	}

	@Test
	public void testSize1() {
		DynTab<String> dl = new DynTab<String>();
		assertTrue(dl.size() == 0);
		assertTrue(dl.capacity() == 1);
	}

}

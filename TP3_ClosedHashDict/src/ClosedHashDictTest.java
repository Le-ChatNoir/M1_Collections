import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class ClosedHashDictTest {

	@Test
	public void testClear() {
		ClosedHashDict<Integer, Integer> d = new ClosedHashDict<Integer, Integer>();
		assertTrue(d.size() == 0);
		assertTrue(d.isEmpty());
		d.put(1, 1);
		assertTrue(d.size() == 1);
		assertFalse(d.isEmpty());
		assertTrue(d.containsKey(1));
		d.clear();
		assertTrue(d.size() == 0);
		assertTrue(d.isEmpty());
		assertFalse(d.containsKey(1));
	}

	@Test
	public void testContainsKey() {
		ClosedHashDict<Integer, Integer> d = new ClosedHashDict<Integer, Integer>();
		for (int i = 0; i < 10; i++) {
			d.put(i, i);
		}
		for (int i = 0; i < 10; i++) {
			d.put(i, i);
			assertTrue(d.containsKey(i));
		}
		for (int i = 0; i < 10; i++) {
			d.put(i, i);
			assertFalse(d.containsKey(i+ 11));
		}
		for (int i = 0; i < 5; i++) {
			d.remove(i);
		}
		for (int i = 0; i < 5; i++) {
			assertFalse(d.containsKey(i));
		}
		assertTrue(d.size() == 5);
	}

	@Test
	public void testContainsValue() {
		ClosedHashDict<Integer, Integer> d = new ClosedHashDict<Integer, Integer>();
		for (int i = 0; i < 10; i++) {
			d.put(i, i);
		}
		for (int i = 0; i < 10; i++) {
			d.put(i, i);
			assertTrue(d.containsValue(i));
		}
		for (int i = 0; i < 10; i++) {
			d.put(i, i);
			assertFalse(d.containsValue(i+ 11));
		}
		for (int i = 0; i < 5; i++) {
			d.remove(i);
		}
		for (int i = 0; i < 5; i++) {
			assertFalse(d.containsValue(i));
		}
		assertTrue(d.size() == 5);
	}

	@Test
	public void testEntrySet() {
		ClosedHashDict<Integer, Integer> d = new ClosedHashDict<Integer, Integer>();
		for (int i = 0; i < 10; i++) {
			d.put(i, i);
		}
		Set<Map.Entry<Integer,Integer>> s = d.entrySet();	
		assertTrue(s.size() == 10);
		Set<Integer> keys = new HashSet<Integer>();
		Set<Integer> values = new HashSet<Integer>();
		Iterator<Map.Entry<Integer,Integer>> kitor = s.iterator();
		while (kitor.hasNext()) {
			Map.Entry<Integer,Integer> cple = kitor.next();
			keys.add(cple.getKey());
			values.add(cple.getValue());
		}
		assertTrue(keys.size() == 10);
		assertTrue(values.size() == 10);
		for (int i = 0; i < 10; i++) {
			assertTrue(keys.contains(i));
			assertTrue(values.contains(i));
		}
	}

	@Test
	public void testGet() {
		ClosedHashDict<Integer, Integer> d = new ClosedHashDict<Integer, Integer>();
		for (int i = 0; i < 10; i++) {
			d.put(i, i);
		}
		for (int i = 0; i < 10; i++) {
			assertTrue(d.get(i).equals(new Integer(i)));
		}
		d.clear();
		for (int i = 0; i < 10; i++) {
			assertTrue(d.get(i) == null);
		}
	}

	@Test
	public void testKeySet() {
		ClosedHashDict<Integer, Integer> d = new ClosedHashDict<Integer, Integer>();
		for (int i = 0; i < 10; i++) {
			d.put(i, i);
		}
		Set<Integer> keys = d.keySet();
		for (int i = 0; i < 10; i++) {
			assertTrue(keys.contains(i));
		}
		assertTrue(keys.size() == 10);
	}

	@Test
	public void testPutAll() {
		ClosedHashDict<Integer, Integer> d1 = new ClosedHashDict<Integer, Integer>();
		for (int i = 0; i < 10; i++) {
			d1.put(i, i);
		}
		ClosedHashDict<Integer, Integer> d2 = new ClosedHashDict<Integer, Integer>();
		d2.putAll(d1);
		assertTrue(d2.size() == 10);
		for (int i = 0; i < 10; i++) {
			d2.containsKey(i);
			d2.containsValue(i);
		}
	}

	@Test
	public void testValues() {
		ClosedHashDict<Integer, Integer> d = new ClosedHashDict<Integer, Integer>();
		for (int i = 0; i < 10; i++) {
			d.put(i, i);
		}
		Collection<Integer> values = d.values();
		for (int i = 0; i < 10; i++) {
			assertTrue(values.contains(i));
		}
		assertTrue(values.size() == 10);
	}

}


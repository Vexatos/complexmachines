package archadia.complexmachines.common.helper.map;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Archadia
 *
 */
public class DualMap<K1, K2, V> {
	
	private final Map<K1, V> map1 = new HashMap<K1, V>();

	private final Map<K2, V> map2 = new HashMap<K2, V>();

	public Map<K1, V> getMap1() {
		return Collections.unmodifiableMap(map1);
	}

	public Map<K2, V> getMap2() {
		return Collections.unmodifiableMap(map2);
	}

	public Object get(K1 key1, K2 key2) {
		if(map1.get(key1) == map1.get(key2)) {
			return map1.get(key1);
		}
		return null;
	}
	
	public void put(K1 key1, K2 key2, V value) {
		map1.put(key1, value);
		map2.put(key2, value);
	}
}

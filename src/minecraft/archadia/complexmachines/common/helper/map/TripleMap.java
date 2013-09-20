package archadia.complexmachines.common.helper.map;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import archadia.complexmachines.common.helper.interfaces.IDualMap;
import archadia.complexmachines.common.helper.interfaces.ITripleMap;

/**
 * @author Archadia
 *
 */
public class TripleMap<K1, K2, K3, V> implements ITripleMap<K1, K2, K3, V> {

	private final Map<K1, V> map1 = new HashMap<K1, V>();

	private final Map<K2, V> map2 = new HashMap<K2, V>();
	
	private final Map<K3, V> map3 = new HashMap<K3, V>();
	
	@Override
	public Map<K1, V> getMap1() {
		return Collections.unmodifiableMap(map1);
	}

	@Override
	public Map<K2, V> getMap2() {
		return Collections.unmodifiableMap(map2);
	}

	@Override
	public Map<K3, V> getMap3() {
		return Collections.unmodifiableMap(map3);
	}

	@Override
	public void put(K1 key1, K2 key2, K3 key3, V value) {
		map1.put(key1, value);
		map2.put(key2, value);
		map3.put(key3, value);
	}

}

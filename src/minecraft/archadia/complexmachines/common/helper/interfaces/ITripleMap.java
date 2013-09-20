package archadia.complexmachines.common.helper.interfaces;

import java.util.Map;

/**
 * @author Archadia
 *
 */
public interface ITripleMap<K1, K2, K3, V> {
	
	Map<K1, V> getMap1();

	Map<K2, V> getMap2();
	
	Map<K3, V> getMap3();

	void put(K1 key1, K2 key2, K3 key3, V value);
}


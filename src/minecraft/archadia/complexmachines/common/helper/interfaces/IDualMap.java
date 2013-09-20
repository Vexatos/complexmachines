package archadia.complexmachines.common.helper.interfaces;

import java.util.Map;

/**
 * @author Archadia
 *
 */
public interface IDualMap<K1, K2, V> {

	Map<K1, V> getMap1();

	Map<K2, V> getMap2();

	void put(K1 key1, K2 key2, V value);
}
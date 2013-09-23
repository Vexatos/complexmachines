package archadia.complexmachines.helper.map;

import java.util.HashMap;
import java.util.Map;

import archadia.complexmachines.helper.ArchHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * @author Archadia
 *
 */
public class DualMap<K1, K2, V> {
	
	private final Map<K1, V> map1 = new HashMap<K1, V>();

	private final Map<K2, V> map2 = new HashMap<K2, V>();

	public V get(Object key1, Object key2) {
		ArchHelper.println(""+map1.get(key1));
		ArchHelper.println(""+map2.get(key2));
		if(map1.get(key1) == map2.get(key2)) {
			return map2.get(key2);
		}
		return null;
	}       
	
	public void put(K1 key1, K2 key2, V value) {
		map1.put(key1, value);
		map2.put(key2, value);
	}
}

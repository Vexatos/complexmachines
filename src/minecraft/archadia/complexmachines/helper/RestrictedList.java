package archadia.complexmachines.helper;

import java.util.ArrayList;

/**
 * @author Archadia
 *
 */
public class RestrictedList<K> {

	private ArrayList<K> list;
	private int sizeLimit;
	
	public RestrictedList(int size) {
		sizeLimit = size;
	}
	
	public ArrayList<K> getList() {
		return list;
	}
	
	public void add(K value) {
		if(list.size() > sizeLimit) {
			System.err.println("Error! An attempt to add a value failed! List already full!");
			return;
		}
		if(list.size() < sizeLimit) {
			list.add(value);
		}
	}
	
	public K get(int i) {
		if(i > sizeLimit) {
			System.err.println("Error! An attempt to return a value failed! Index too high!");
			return null;
		} else {
			return list.get(i);
		}
	}
}

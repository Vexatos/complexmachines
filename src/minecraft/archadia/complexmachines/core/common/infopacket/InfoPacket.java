package archadia.complexmachines.core.common.infopacket;

/**
 * @author Archadia
 *
 */
public class InfoPacket {
	
	private String packetName;
	private int packetSize;
	
	private int[] contents;
		
	public InfoPacket(String name) {
		this(name, 1);
	}
	
	public InfoPacket(String name, int size) {
		packetName = name;
		packetSize = size;
		
		populatePacket(size);
	}
	
	private void populatePacket(int integer) {
		contents = new int[integer];
	}
	
	public String getPacketName() {
		return "packet." + packetName;
	}
	
	public int getPacketSize() {
		return packetSize;
	}
	
	public void add(int integer) {
		for(int i = 0; i < packetSize; i++) {
			contents[i] = integer;
		}
	}
	
	public int get(int integer) {
		return contents[integer];
	}
}

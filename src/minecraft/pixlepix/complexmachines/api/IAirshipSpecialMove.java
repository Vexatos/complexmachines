package pixlepix.complexmachines.api;

import net.minecraftforge.common.ForgeDirection;

public interface IAirshipSpecialMove {

	//Implement this in your tile entity
	//To use special behavior when being moved by airships
	
	
	public AirshipSpecialMoveData beforeMove(ForgeDirection movementDirection);

	public void afterMove(AirshipSpecialMoveData data);
	
}

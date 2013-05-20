package pixlepix.complexmachines.common.laser.tileentity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;

public class TripwireLaserBeamTileEntity extends LaserBeamTileEntity {
	
	
	public void updateEntity(){
		super.updateEntity();
		
		List<Entity> entities=worldObj.getEntitiesWithinAABB(Entity.class, getLaserAABB());
		for(int i=0;i<entities.size();i++){
			Entity detectedEntity=entities.get(i);
			
			if(entity!=null){
				entity.notifyTripwire();
			}
			
		}
		
	}

}

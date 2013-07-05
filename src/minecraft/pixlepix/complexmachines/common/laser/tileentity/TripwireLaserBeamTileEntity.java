package pixlepix.complexmachines.common.laser.tileentity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import pixlepix.complexmachines.common.laser.LaserEmitterTileEntity;

public class TripwireLaserBeamTileEntity extends LaserBeamTileEntity {
	
	
	public void updateEntity(){
		super.updateEntity();
		ForgeDirection dir=ForgeDirection.VALID_DIRECTIONS[worldObj.getBlockMetadata(xCoord, yCoord, zCoord)];
		TileEntity entity=worldObj.getBlockTileEntity(xCoord-dir.offsetX,yCoord-dir.offsetY,zCoord-dir.offsetZ);
		if(entity instanceof LaserEmitterTileEntity){
		LaserEmitterTileEntity emitter=(LaserEmitterTileEntity)entity;
		
		System.out.println("0");
		List<Entity> entities=worldObj.getEntitiesWithinAABB(Entity.class, getLaserAABB());
		for(int i=0;i<entities.size();i++){
			System.out.println("1");
			Entity detectedEntity=entities.get(i);
			
			if(emitter!=null){

				System.out.println("2");
				emitter.notifyTripwire();
			}
			
		}
		
	}
	}

}

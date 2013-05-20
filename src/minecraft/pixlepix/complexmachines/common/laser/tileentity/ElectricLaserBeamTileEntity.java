package pixlepix.complexmachines.common.laser.tileentity;

import java.util.List;


import net.minecraft.entity.EntityLiving;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;


public class ElectricLaserBeamTileEntity extends LaserBeamTileEntity {
	
	public void updateEntity(){
		super.updateEntity();
		List<EntityLiving> entities=worldObj.getEntitiesWithinAABB(EntityLiving.class, getExpandedLaserAABB());
		for(int i=0;i<entities.size();i++){
			
			EntityLiving entity=entities.get(i);
			if(entity.isInWater()){

				entities.get(i).attackEntityFrom(DamageSource.onFire, 3);
			}
			
		}
	}
	
}

package pixlepix.complexmachines.common.laser.tileentity;

import java.util.List;


import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;

public class HarmingLaserBeamTileEntity extends LaserBeamTileEntity {

public int ticks=0;
	public void updateEntity(){
		
		super.updateEntity();
		List<EntityLiving> entities=worldObj.getEntitiesWithinAABB(EntityLiving.class, getLaserAABB());
		//System.out.println(entities);
		for(int i=0;i<entities.size();i++){
			
			entities.get(i).attackEntityFrom(DamageSource.onFire, 3);
			
		}
	}
}

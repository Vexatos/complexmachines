package pixlepix.complexmachines.common.laser.tileentity;

import java.util.List;


import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.ForgeDirection;

public class SuctionLaserBeamTileEntity extends LaserBeamTileEntity {
	public int xDirection=0;
	public int zDirection=0;
	public void updateEntity(){
		
		super.updateEntity(); 
		List<Entity> entities=worldObj.getEntitiesWithinAABB(Entity.class, getLaserAABB());
		//System.out.println(entities);
		for(int i=0;i<entities.size();i++){
			Entity entity=entities.get(i);
				int meta=worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
				if(meta==0){
					meta=3;
				}
				ForgeDirection direction=ForgeDirection.VALID_DIRECTIONS[meta];
				
				entity.addVelocity(-1*direction.offsetX, 0, -1*direction.offsetZ);
				
				
			}
			
		}
	}
	



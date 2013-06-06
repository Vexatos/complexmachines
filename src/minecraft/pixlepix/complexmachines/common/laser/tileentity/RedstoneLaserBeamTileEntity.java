package pixlepix.complexmachines.common.laser.tileentity;

import java.util.List;


import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.ForgeDirection;

public class RedstoneLaserBeamTileEntity extends LaserBeamTileEntity {
	public int xDirection=0;
	public int zDirection=0;
	public void updateEntity(){
		
		super.updateEntity(); 

		int meta=worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
		if(meta==0){
			meta=3;
		}
		ForgeDirection direction=ForgeDirection.VALID_DIRECTIONS[meta];
		
		List<Entity> entities=worldObj.getEntitiesWithinAABB(Entity.class, getLaserAABB());
		//System.out.println(entities);
		for(int i=0;i<20;i++){
			int x=xCoord+i*direction.offsetX;

			int y=yCoord+i*direction.offsetY;

			int z=zCoord+i*direction.offsetZ;
			int id=worldObj.getBlockId(x, y, z);
			int metadata=worldObj.getBlockMetadata(x, y, z);
			if(id==55){
				worldObj.setBlockMetadataWithNotify(x, y, z, 15, 3);
			}
			
		}
	}
	

}

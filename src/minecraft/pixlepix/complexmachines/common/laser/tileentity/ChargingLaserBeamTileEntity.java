package pixlepix.complexmachines.common.laser.tileentity;

import java.util.List;

import universalelectricity.core.item.IItemElectric;


import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;

public class ChargingLaserBeamTileEntity extends LaserBeamTileEntity {
	public void updateEntity(){
		
		super.updateEntity();
		List<EntityPlayer> entities=worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(xCoord-.5, yCoord-.5, zCoord-.5, xCoord+.5, yCoord+.5, zCoord+.5));
		//System.out.println(entities);
		for(int i=0;i<entities.size();i++){
			EntityPlayer currentEntity=entities.get(i);
			InventoryPlayer currentInventory=currentEntity.inventory;
			for(int j=0;j<currentInventory.mainInventory.length;j++){
				ItemStack currentItem=currentInventory.mainInventory[j];
				if(entity!=null&&currentItem!=null&&currentItem.getItem() instanceof IItemElectric){
					IItemElectric electricItem=(IItemElectric)currentItem.getItem();
					int currentJoules=(int) electricItem.getJoules(currentItem);
					int maxJoules=(int) electricItem.getMaxJoules(currentItem);
					int charge=(int)Math.min(entity.getJoules()-10000,maxJoules-currentJoules);
					electricItem.setJoules(currentJoules+charge, currentItem);
					entity.setJoules(entity.getJoules()-charge);
				}
			}
			
			for(int j=0;j<currentInventory.armorInventory.length;j++){
				ItemStack currentItem=currentInventory.armorInventory[j];
				if(entity!=null&&currentItem!=null&&currentItem.getItem() instanceof IItemElectric){
					IItemElectric electricItem=(IItemElectric)currentItem.getItem();
					int currentJoules=(int) electricItem.getJoules(currentItem);
					int maxJoules=(int) electricItem.getMaxJoules(currentItem);
					int charge=(int)Math.min(entity.getJoules()-10000,maxJoules-currentJoules);
					electricItem.setJoules(currentJoules+charge, currentItem);
					entity.setJoules(entity.getJoules()-charge);
				}
			}
			
			
		}
	}
}

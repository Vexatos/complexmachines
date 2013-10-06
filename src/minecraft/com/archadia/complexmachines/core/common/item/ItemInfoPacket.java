package com.archadia.complexmachines.core.common.item;

import java.util.List;

import com.archadia.complexmachines.core.common.ComplexMachines;
import com.archadia.complexmachines.prefab.item.ItemBase;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * @author Archadia
 *
 */
public class ItemInfoPacket extends ItemBase {

	public ItemInfoPacket(int id, String name) {
		super(id, name);
		setMaxStackSize(1);
	}
	
    public void onCreated(ItemStack itemstack, World world, EntityPlayer player) {
    	if(itemstack.getTagCompound() == null) {
    		itemstack.setTagCompound(new NBTTagCompound());
    	}
    	
    	itemstack.stackTagCompound.setInteger("1", Block.dirt.blockID);
    	itemstack.stackTagCompound.setInteger("2", Block.stone.blockID);
    	itemstack.stackTagCompound.setInteger("3", Block.oreDiamond.blockID);
    }

    public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean bool1) {
    	if(itemstack.getTagCompound() == null) {
    		itemstack.setTagCompound(new NBTTagCompound());
    	}
    	
    	list.add("Info ID: " + itemstack.getTagCompound().getInteger("1"));
    	list.add("Info ID: " + itemstack.getTagCompound().getInteger("2"));
    	list.add("Info ID: " + itemstack.getTagCompound().getInteger("3"));
    }
}

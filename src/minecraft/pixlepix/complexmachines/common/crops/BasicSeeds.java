package pixlepix.complexmachines.common.crops;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import pixlepix.complexmachines.common.ComplexMachines;
import pixlepix.complexmachines.common.Config;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;

public class BasicSeeds extends Item implements IPlantable {

	
	public BasicSeeds(int par1) {
		super(par1);

        this.blockType = 0;
        this.soilBlockID = 0;

        this.setCreativeTab(ComplexMachines.creativeTab);
	}

	
	 @Override
	 @SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon(texture);
	}
	
	private int blockType;
	public String texture;

    /** BlockID of the block the seeds can be planted on. */
    private int soilBlockID;

    public BasicSeeds(int par1, int par2, int par3, String texture)
    {
        super(par1);
        this.texture=texture;
        this.blockType = par2;
        this.soilBlockID = par3;
        this.setCreativeTab(CreativeTabs.tabMaterials);

		id=Config.itemStartingID+par1;
    }

	int id=0;
	public BasicCrop crop;
	
	@Override
	public EnumPlantType getPlantType(World world, int x, int y, int z) {
		// TODO Auto-generated method stub
		return EnumPlantType.Crop;
	}

	@Override
	public int getPlantID(World world, int x, int y, int z) {
		// TODO Auto-generated method stub
		return blockType;
	}

	@Override
	public int getPlantMetadata(World world, int x, int y, int z) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	//Imported code from ItemSeeds
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        if (par7 != 1)
        {
            return false;
        }
        else if (par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack) && par2EntityPlayer.canPlayerEdit(par4, par5 + 1, par6, par7, par1ItemStack))
        {
            int i1 = par3World.getBlockId(par4, par5, par6);
            Block soil = Block.blocksList[i1];
            if(crop instanceof BasicCrop && !((BasicCrop)crop).canPlantGrow(par3World, par4, par5, par6)){
            	return false;
            }
            if (soil != null && soil.canSustainPlant(par3World, par4, par5, par6, ForgeDirection.UP, this) && par3World.isAirBlock(par4, par5 + 1, par6))
            {
                par3World.setBlock(par4, par5 + 1, par6, this.blockType);
                --par1ItemStack.stackSize;
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

}

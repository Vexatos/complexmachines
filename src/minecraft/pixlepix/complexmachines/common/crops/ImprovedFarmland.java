package pixlepix.complexmachines.common.crops;

import static net.minecraftforge.common.ForgeDirection.UP;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import pixlepix.complexmachines.common.BasicComplexBlock;
import pixlepix.complexmachines.common.ComplexMachines;
import pixlepix.complexmachines.common.block.FillerMachine;
import pixlepix.complexmachines.common.itemblock.FillerItemBlock;
import pixlepix.complexmachines.common.tileentity.FillerMachineTileEntity;

public class ImprovedFarmland extends BasicComplexBlock {
	public ImprovedFarmland() {
		super(35);

        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.9375F, 1.0F);
		// TODO Auto-generated constructor stub
	}

	String textureBase="ComplexMachines:";
	public String textureSpecific="FillerModel";

	 
	 public String textureSpecificTop="null";

	public String textureSpecificConnector="null";
	@Override
	public String getFront() {
		// TODO Auto-generated method stub
		return textureSpecific;
	}
	@Override
	public boolean hasModel(){
		return false;
	}
	
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return AxisAlignedBB.getAABBPool().getAABB((double)(par2 + 0), (double)(par3 + 0), (double)(par4 + 0), (double)(par2 + 1), (double)(par3 + 1), (double)(par4 + 1));
    }
	
	@Override
	public String getTop() {
		// TODO Auto-generated method stub
		return textureSpecificTop;
	}

	@Override
	public String getInput() {
		// TODO Auto-generated method stub
		 return textureSpecificConnector;
	}
	@Override
	public Class getTileEntityClass() {
		return null;
	}

	@Override
	public void addRecipe() {

		//GameRegistry.addRecipe(new ItemStack(ComplexMachines.loader.getBlock(FillerMachine.class)), "xyx", "yzy", "xyx", 'x', new ItemStack(stone), 'y',new ItemStack(Item.shovelIron), 'z', new ItemStack(Item.ingotIron));
	}

	@Override
	public String getName() {
		return "ImprovedFarmland";
	}

	@Override
	public boolean hasItemBlock() {
		return true;
	}

	@Override
	public Class getItemBlock() {
		return FillerItemBlock.class;
		
	}
	@Override
	public boolean threeSidedTextures(){
		return false;
	}
	
	
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
        super.onNeighborBlockChange(par1World, par2, par3, par4, par5);
        Material material = par1World.getBlockMaterial(par2, par3 + 1, par4);

        if (material.isSolid())
        {
            par1World.setBlock(par2, par3, par4, Block.dirt.blockID);
        }
    }
	
	 public int idDropped(int par1, Random par2Random, int par3)
	    {
	        return Block.dirt.idDropped(0, par2Random, par3);
	    }
	 @SideOnly(Side.CLIENT)
	 public int idPicked(World par1World, int par2, int par3, int par4)
	    {
	        return Block.dirt.blockID;
	    }
	 public Icon icon;
	 public void registerIcons(IconRegister par1IconRegister)
	    {
	        this.icon= par1IconRegister.registerIcon("farmland_wet");
	    }
	 public Icon getIcon(int par1, int par2)
	    {
	        return par1 == 1 ? icon : Block.dirt.getBlockTextureFromSide(par1);
	    }
	 
	 
	 @Override
	 public boolean canSustainPlant(World world, int x, int y, int z, ForgeDirection direction, IPlantable plant)
	    {
	        return true;
	    }
	 
	 public boolean isFertile(World world, int x, int y, int z)
	    {
		 return true;
	    }

}

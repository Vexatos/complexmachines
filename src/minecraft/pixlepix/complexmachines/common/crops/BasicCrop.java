package pixlepix.complexmachines.common.crops;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
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
import pixlepix.complexmachines.common.BasicComplexBlock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class BasicCrop extends BasicComplexBlock implements IPlantable {

	public Icon stage1;
	public Icon stage2;
	public Icon stage3;
	public Icon stage4;
	
	public int seed;
	public boolean inCreativeTab(){
		return false;
	}
	
	@Override
	public EnumPlantType getPlantType(World world, int x, int y, int z) {
		// TODO Auto-generated method stub
		return EnumPlantType.Crop;
	}

	@Override
	public int getPlantID(World world, int x, int y, int z) {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public int getPlantMetadata(World world, int x, int y, int z) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public abstract String getTextureBase();
	
	public int id;

	public BasicCrop(int i,int seed) {
		super(i,Material.plants);
		this.id=i;
		this.seed=seed+256;
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
        setTickRandomly(true);
        
	}

	@Override
	public boolean isPlantMaterial() {
		return true;
	}
	
	@Override
	public String getTop() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getInput() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool (World world, int x,
            int y, int z) {
        return null;
    }
	
	@Override
	public boolean renderAsNormalBlock(){
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(){
		return false;
	}

	public boolean hasModel(){
		return false;
	}
	@SideOnly(Side.CLIENT)
	 @Override
	 public int getRenderType(){
		return 6;
	}
	@Override
	public boolean hasItemBlock() {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean threeSidedTextures(){
		return false;
	}
	public void registerIcons(IconRegister register){
		stage1=register.registerIcon(getTextureBase()+"0");

		stage2=register.registerIcon(getTextureBase()+"1");

		stage3=register.registerIcon(getTextureBase()+"2");

		stage4=register.registerIcon(getTextureBase()+"3");
	}
	public Icon getIcon(int side, int meta){
		switch(meta){
		case 0:
			return stage1;
		case 1:
			return stage2;
		case 2:
			return stage3;
		case 3:
			return stage4;
		default:
			return stage1;
		}
		
		
	}
	public int getInfertileRate(){
		return 25;
	}
	public int getFertileRate(){
		return 12;
	}
	@Override
    public void updateTick (World world, int x, int y, int z, Random random) {
        if (world.getBlockMetadata(x, y, z) == 3) {
            return;
        }

        if (random.nextInt(isFertile(world, x, y - 1, z) ? getFertileRate() : getInfertileRate()) != 0) {
            return;
        }

        //world.setBlockMetadataWithNotify(x, y, z, 3, 3);
        world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z)+1, 3);
    }
	@Override
    public void onNeighborBlockChange (World world, int x, int y, int z,
            int neighborId) {
        if (!canBlockStay(world, x, y, z)) {
            dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlock(x, y, z, 0);
        }
    }

	public boolean skipSustainCheck(){
		return false;
	}
	
    @Override
    public boolean canBlockStay (World world, int x, int y, int z) {
        Block soil = blocksList[world.getBlockId(x, y - 1, z)];
        if(canPlantGrow(world, x,y-1,z)){
        	if((world.getFullBlockLightValue(x, y, z) >= 8 || world.canBlockSeeTheSky(x, y, z))&& (soil != null)){
        		if((soil.canSustainPlant(world, x, y - 1, z,ForgeDirection.UP, (IPlantable)Item.itemsList[seed])||skipSustainCheck())){
        			return true;
        		}
        	}
        }
        return false;
        
    }

	public abstract boolean canPlantGrow(World world, int x, int y, int z);
	public abstract ArrayList<ItemStack> getSeedDrop();
	public abstract ArrayList<ItemStack> getHarvestDrop();
	public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune)
    {
       if(metadata==3){
    	   return getHarvestDrop();
       }
       return getSeedDrop();
    }
	
	@Override
    public int idPicked (World world, int x, int y, int z) {
        return seed;
    }
	@Override
	public String getFront() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	


}

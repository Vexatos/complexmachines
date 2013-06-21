package pixlepix.complexmachines.common;

import pixlepix.complexmachines.client.ClientProxy;
import pixlepix.complexmachines.common.tileentity.FillerMachineTileEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import universalelectricity.core.UniversalElectricity;
import universalelectricity.prefab.block.BlockAdvanced;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class BasicComplexBlock extends BlockAdvanced implements IBlock {

	public Icon connectorIcon;
	public Icon topIcon;
	static int blockIdIncrement;
	public String textureBase="ComplexMachines:";
	public abstract String getFront();
	public abstract String getTop();
	public abstract String getInput();
	
	@Override
	public boolean renderAsNormalBlock(){
		return true;
	}
	
	@Override
	public boolean isOpaqueCube(){
		return false;
	}

	public boolean hasModel(){
		return false;
	}
	
	public BasicComplexBlock(int increment, Material material) {
		super(Config.blockStartingID+increment,material);

		this.setUnlocalizedName(this.getName());
		
	}
	public BasicComplexBlock(int increment) {
		super(Config.blockStartingID+increment, UniversalElectricity.machine);

		this.setUnlocalizedName(this.getName());
		
	}
	
	public boolean inCreativeTab(){
		return true;
	}
	 @SideOnly(Side.CLIENT)
	 @Override
	 public int getRenderType()
	    {
	    	if(this.hasModel()){
	    		return ClientProxy.RENDER_ID;
	    	}
	    	return 0;
	    }
	


	 
	 
	 @Override
		@SideOnly(Side.CLIENT)
		public void registerIcons(IconRegister par1IconRegister) {

			blockIcon = par1IconRegister
					.registerIcon(textureBase+this.getFront());
			connectorIcon = par1IconRegister
					.registerIcon(textureBase+this.getInput());
			topIcon = par1IconRegister.registerIcon(textureBase+this.getTop());
		}

		@Override
		public Icon getIcon(int side, int meta) {
		if(threeSidedTextures()){
				if (side == meta + 2) {
					return connectorIcon;
				}
				if (side == 1 || side == 0) {
					return topIcon;
				}
			}
			return blockIcon;
			
		}
	
	@Override
    public boolean onUseWrench(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, int side,
            float hitX, float hitY, float hitZ)
    {
        int metadata = par1World.getBlockMetadata(x, y, z);
        
        int change = 0;
        
        // Re-orient the block
        switch (metadata)
        {
            case 0:
                change = 3;
                break;
            case 3:
                change = 1;
                break;
            case 1:
                change = 2;
                break;
            case 2:
                change = 0;
                break;
        }
        
        par1World.setBlock(x, y, z, this.blockID, change, 0);
        par1World.markBlockForRenderUpdate(x, y, z);
        
        ((BasicComplexTileEntity) par1World.getBlockTileEntity(x, y, z)).initiate();
        
        return true;
    }
	@Override
	public void onBlockPlacedBy(World par1World, int x, int y, int z,
			EntityLiving par5EntityLiving, ItemStack itemStack) {

		int angle = MathHelper.floor_double((par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		int change = 0;

		switch (angle)
		{
			case 0:
				change = 1;
				break;
			case 1:
				change = 2;
				break;
			case 2:
				change = 0;
				break;
			case 3:
				change = 3;
				break;
		}
		par1World.setBlockMetadataWithNotify(x, y, z, change, 2);
		par1World.notifyBlocksOfNeighborChange(x, y, z, this.blockID);
		if(par1World.getBlockTileEntity(x, y, z)!=null)
		((BasicComplexTileEntity) par1World.getBlockTileEntity(x, y, z)).initiate();
	}
	
	@Override
	public TileEntity createTileEntity(World var1, int metadata) {
		try {
			return getTileEntityClass().newInstance();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Error while creating tile entity");
		return null;

	}

	@Override
	public boolean hasTileEntity(int metadata) {
		return getTileEntityClass()!=null;
	}
	
	@Override
	public void addRecipe() {
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "BasicComplexBlock";
	}

	@Override
	public boolean hasItemBlock() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Class getItemBlock() {
		// TODO Auto-generated method stub
		
		return ItemBlock.class;
	}
	
	public boolean threeSidedTextures(){
		return true;
	}
	
	@Override
	public Class<TileEntity> getTileEntityClass() {
		return null;
	}
	public boolean isPlantMaterial() {
		return false;
	}

}

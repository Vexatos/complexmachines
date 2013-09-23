package archadia.complexmachines.common.block;

import archadia.complexmachines.common.ComplexMachines;
import archadia.complexmachines.common.tileentity.TileEntityGrinder;
import archadia.complexmachines.common.tileentity.TileEntityWireMill;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

/**
 * @author Archadia
 *
 */
public class BlockGrinder extends BlockBase {

	public BlockGrinder(int id, Material material, String name) {
		super(id, material, name);
		setCreativeTab(ComplexMachines.tabComplexMachines);
		setHardness(2F);
		setUnlocalizedName(name);
		setIconMax(6);
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		TileEntity te = world.getBlockTileEntity(x, y, z);
		if(te instanceof TileEntityGrinder && !player.isSneaking()) {
			player.openGui(ComplexMachines.instance, 2, world, x, y, z);
			return true;
		}
		return false;
    }
	
	public boolean hasTileEntity(int metadata) {
		return true;
	}
	
	public TileEntity createTileEntity(World world, int metadata) {
		return new TileEntityGrinder();
	}
	
	public Icon getIcon(int side, int meta)
	{
		if(side == 1 || side == 6) return icons[1];
		if(side > 1 && side != 6) return icons[0];
		return null;
	}

	public void registerIcons(IconRegister ir) {
		icons[0] = ir.registerIcon("complexmachines:grinder_side_off_32x");
		icons[1] = ir.registerIcon("complexmachines:basic_top_off_32x");
	}
}

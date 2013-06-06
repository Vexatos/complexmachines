package pixlepix.complexmachines.common;

import universalelectricity.prefab.tile.TileEntityElectrical;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class BasicComplexTileEntity extends TileEntityElectrical {

	public void initiate() {
		
	}

	@Override
	public boolean canConnect(ForgeDirection direction) {
		return direction.ordinal() == this.getBlockMetadata() + 2;
	}

}

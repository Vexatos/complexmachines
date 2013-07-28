package pixlepix.complexmachines.common.crops;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import pixlepix.complexmachines.common.ComplexMachines;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Monsterweed extends Item {

	
	
	

	
	 @Override
	 @SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon("ComplexMachines:monsterweed");
	}
	

    public Monsterweed(int i)
    {
        super(i);
        this.setUnlocalizedName("MonsterWeedItem");
        this.setCreativeTab(ComplexMachines.creativeTab);

    }
	
	

	
	
	
	
}

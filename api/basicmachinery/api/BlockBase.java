package basicmachinery.api;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;

/**
 * @author Archadia
 *
 */
public class BlockBase extends Block {

	public String iconName;
	public WrenchHelper wrenchHelper = new WrenchHelper();
	
	public BlockBase(int id, Material material, String name, CreativeTabs tab) {
		super(id, material);
		setUnlocalizedName(name);
		setCreativeTab(tab);
		iconName = "basicmachinery:" + name;
	}
	
	public Icon[] icons;
	
	public void registerIcons(IconRegister ir) {
		this.blockIcon = ir.registerIcon(this.iconName);
	}
	
	public void setIconMax(int max) {
		this.icons = new Icon[max];
	}
}

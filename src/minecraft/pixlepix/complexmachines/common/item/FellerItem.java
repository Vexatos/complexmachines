package pixlepix.complexmachines.common.item;


import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import pixlepix.complexmachines.common.*;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import universalelectricity.core.item.ItemElectric;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
public class FellerItem extends ItemElectric
{
    public FellerItem(int par1)
    {
        super(par1);
        this.setUnlocalizedName("Feller");
        maxStackSize=1;
        this.setCreativeTab(ComplexMachines.creativeTab);
    }
    @Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List list, boolean par4){
    	super.addInformation(par1ItemStack,par2EntityPlayer, list, par4);
		if(!Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
		{
			list.add("Hold " + EnumColor.AQUA + "shift" + EnumColor.GREY + " for more details.");
		}
		else {
			
			list.add(EnumColor.AQUA + "Mines all logs in a tree");
			list.add(EnumColor.DARK_GREEN + "100KJ per log mined");
		}
	}
    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10)
    {
    	
    		int targetId=world.getBlockId(x, y, z);
    		if(targetId==17){
    			ArrayList<CoordTuple> list=new ArrayList<CoordTuple>();
    			list.add(new CoordTuple(x,y,z));
    			while(list.size()>0&&getElectricityStored(itemStack)>1000){
    				CoordTuple curr=list.get(0);
    				setElectricity(itemStack,this.getElectricityStored(itemStack)-1000);
    				ArrayList<CoordTuple> nearby=new ArrayList<CoordTuple>();
    				int curX=(int)curr.x;
    				int curY=(int)curr.y;
    				int curZ=(int)curr.z;
    				list.remove(0);
    				world.destroyBlock(curX, curY, curZ, true);
    				nearby.add(new CoordTuple(curX+1,curY,curZ));
    				nearby.add(new CoordTuple(curX-1,curY,curZ));
    				nearby.add(new CoordTuple(curX,curY+1,curZ));
    				nearby.add(new CoordTuple(curX,curY-1,curZ));
    				nearby.add(new CoordTuple(curX,curY,curZ+1));
    				nearby.add(new CoordTuple(curX,curY,curZ-1));
    				for(int i=0;i<nearby.size();i++){
    					if(nearby.get(i).getBlock(world)==17){
    						list.add(nearby.get(i));
    					}
    				
    			}
    		}
    		
    	}
    	
    	
    	
    	
		return false;
        
    }

    
    @Override
    public float getMaxElectricityStored(ItemStack itemStack)
    {
        return 20000000;
    }
    
    @Override
    public float getVoltage(ItemStack itemStack)
    {
        return 240;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon("ComplexMachines:Feller");
    }
}
package pixlepix.complexmachines.client;

import pixlepix.complexmachines.common.CommonProxy;
import pixlepix.complexmachines.common.tileentity.ExtractorMachineTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
    public static int RENDER_ID;
    @Override
    public void init()
    {
        RENDER_ID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new RenderHandler());
        
        ClientRegistry.registerTileEntity(ExtractorMachineTileEntity.class, "TileEntityExtractorMachine", new RenderExtractor());
       
    }
    
    
}
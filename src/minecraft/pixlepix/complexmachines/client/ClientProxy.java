package pixlepix.complexmachines.client;

import pixlepix.complexmachines.client.render.RenderExtractor;
import pixlepix.complexmachines.client.render.RenderFiller;
import pixlepix.complexmachines.client.render.RenderFocalPoint;
import pixlepix.complexmachines.client.render.RenderHandler;
import pixlepix.complexmachines.client.render.RenderLaserEmitter;
import pixlepix.complexmachines.client.render.RenderOceanGenerator;
import pixlepix.complexmachines.client.render.RenderSinglePoint;
import pixlepix.complexmachines.client.render.item.RenderItemExtractor;
import pixlepix.complexmachines.client.render.item.RenderItemFiller;
import pixlepix.complexmachines.client.render.item.RenderItemFocalPoint;
import pixlepix.complexmachines.client.render.item.RenderItemLaserEmitter;
import pixlepix.complexmachines.client.render.item.RenderItemOceanGenerator;
import pixlepix.complexmachines.client.render.item.RenderItemSinglePoint;
import pixlepix.complexmachines.common.CommonProxy;
import pixlepix.complexmachines.common.Config;
import pixlepix.complexmachines.common.laser.LaserEmitterTileEntity;
import pixlepix.complexmachines.common.tileentity.ExtractorMachineTileEntity;
import pixlepix.complexmachines.common.tileentity.FillerMachineTileEntity;
import pixlepix.complexmachines.common.tileentity.FocalPointControledTileEntity;
import pixlepix.complexmachines.common.tileentity.OceanGeneratorTileEntity;
import pixlepix.complexmachines.common.tileentity.SinglePointTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
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
        

        MinecraftForgeClient.registerItemRenderer(Config.blockStartingID+2, new RenderItemExtractor());
        MinecraftForgeClient.registerItemRenderer(Config.blockStartingID+1, new RenderItemFiller());
        MinecraftForgeClient.registerItemRenderer(Config.blockStartingID+8, new RenderItemFocalPoint());
        MinecraftForgeClient.registerItemRenderer(Config.blockStartingID+9, new RenderItemLaserEmitter());
        MinecraftForgeClient.registerItemRenderer(Config.blockStartingID+5, new RenderItemOceanGenerator());
        MinecraftForgeClient.registerItemRenderer(Config.blockStartingID+4, new RenderItemSinglePoint());
        

        ClientRegistry.registerTileEntity(FillerMachineTileEntity.class, "TileEntityFillerMachine", new RenderFiller());

        ClientRegistry.registerTileEntity(ExtractorMachineTileEntity.class, "TileEntityExtractorMachine", new RenderExtractor());

        ClientRegistry.registerTileEntity(OceanGeneratorTileEntity.class, "OceanGenerator", new RenderOceanGenerator());

        ClientRegistry.registerTileEntity(SinglePointTileEntity.class, "SinglePoint", new RenderSinglePoint());

        ClientRegistry.registerTileEntity(FocalPointControledTileEntity.class, "FocalPoint", new RenderFocalPoint());
        ClientRegistry.registerTileEntity(LaserEmitterTileEntity.class, "LaserEmitter", new RenderLaserEmitter());
       
    }
    
    
}
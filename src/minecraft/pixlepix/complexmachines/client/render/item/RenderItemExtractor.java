package pixlepix.complexmachines.client.render.item;

import net.minecraft.client.model.ModelChest;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import pixlepix.complexmachines.client.model.ExtractorModel;


import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Equivalent-Exchange-3
 * 
 * ItemAlchemicalChestRenderer
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
@SideOnly(Side.CLIENT)
public class RenderItemExtractor implements IItemRenderer {

    private ExtractorModel model;

    public RenderItemExtractor() {

        model = new ExtractorModel();
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {

        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {

        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

        switch (type) {
            case ENTITY: {
                render(0.5F, 1F, 0.5F);
                break;
            }
            case EQUIPPED: {
                render(1.0F, 2F, 1.0F);
                break;
            }
            case INVENTORY: {
                render(0.0F, 1F, 0.0F);
                break;
            }
            default:
                break;
        }
    }

    private void render(float x, float y, float z) {

        FMLClientHandler.instance().getClient().renderEngine.bindTexture("/mods/ComplexMachines/textures/models/Extractor.png");
        GL11.glPushMatrix(); //start
        GL11.glTranslatef(x, y, z); //size
        GL11.glRotatef(180, 1, 0, 0);
        GL11.glRotatef(-90, 0, 1, 0);
        model.render(null, 0, 0, 0, 0, 0, 0.0625F);
        GL11.glPopMatrix(); //end
    }
}
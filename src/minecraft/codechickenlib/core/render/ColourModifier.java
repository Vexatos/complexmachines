package codechickenlib.core.render;

import net.minecraft.client.renderer.Tessellator;
import codechickenlib.core.vec.Vector3;

public class ColourModifier implements IVertexModifier
{
    public static final ColourModifier instance = new ColourModifier();

    @Override
    public void applyModifiers(CCModel m, Tessellator tess, Vector3 vec, UV uv, Vector3 normal, int i)
    {
        if(CCRenderState.useModelColours() && m.colours != null)
            CCRenderState.vertexColour(m.colours[i]);
    }

    @Override
    public boolean needsNormals()
    {
        return false;
    }
}

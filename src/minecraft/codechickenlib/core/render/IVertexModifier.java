package codechickenlib.core.render;

import net.minecraft.client.renderer.Tessellator;
import codechickenlib.core.vec.Vector3;

public interface IVertexModifier
{
    public void applyModifiers(CCModel m, Tessellator tess, Vector3 vec, UV uv, Vector3 normal, int i);

    public boolean needsNormals();
}

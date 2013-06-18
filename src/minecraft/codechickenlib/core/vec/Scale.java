package codechickenlib.core.vec;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Scale extends Transformation
{
    private Vector3 factor;
    
    public Scale(Vector3 factor)
    {
        this.factor = factor;
    }

    public Scale(double factor)
    {
        this(new Vector3(factor, factor, factor));
    }
    
    public Scale(double x, double y, double z)
    {
        this(new Vector3(x, y, z));
    }

    @Override
    public void apply(Vector3 vec)
    {
        vec.multiply(factor);
    }
    
    @Override
    public void applyN(Vector3 normal)
    {
    }
    
    @Override
    public void apply(Matrix4 mat)
    {
        mat.scale(factor);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void glApply()
    {
        GL11.glScaled(factor.x, factor.y, factor.z);
    }
}

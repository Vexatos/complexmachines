package codechickenlib.core.vec;

import net.minecraft.entity.player.EntityPlayer;

import org.lwjgl.opengl.GL11;

import codechickenlib.core.alg.MathHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Rotation extends Transformation
{
    public static Transformation[] sideRotations = new Transformation[]{
        new RedundantTransformation(),
        new VariableTransformation(new Matrix4(1, 0, 0, 0, 0,-1, 0, 0, 0, 0,-1, 0, 0, 0, 0, 1)){
            @Override public void apply(Vector3 vec){
                vec.y = -vec.y; vec.z = -vec.z;
            }},
        new VariableTransformation(new Matrix4(1, 0, 0, 0, 0, 0,-1, 0, 0, 1, 0, 0, 0, 0, 0, 1)){
            @Override public void apply(Vector3 vec){
                double d1 = vec.y; double d2 = vec.z;
                vec.y = -d2; vec.z = d1;
            }},
        new VariableTransformation(new Matrix4(1, 0, 0, 0, 0, 0, 1, 0, 0,-1, 0, 0, 0, 0, 0, 1)){
            @Override public void apply(Vector3 vec){
                double d1 = vec.y; double d2 = vec.z;
                vec.y = d2; vec.z = -d1;
            }},
        new VariableTransformation(new Matrix4(0, 1, 0, 0,-1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1)){
            @Override public void apply(Vector3 vec){
                double d0 = vec.x; double d1 = vec.y;
                vec.x = d1; vec.y = -d0;
            }},
        new VariableTransformation(new Matrix4(0,-1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1)){
            @Override public void apply(Vector3 vec){
                double d0 = vec.x; double d1 = vec.y;
                vec.x = -d1; vec.y = d0;
            }}
        };
    public static Quat[] sideQuats = new Quat[]{
        Quat.aroundAxis(1, 0, 0, 0),
        Quat.aroundAxis(1, 0, 0, Math.PI),
        Quat.aroundAxis(1, 0, 0, Math.PI/2),
        Quat.aroundAxis(1, 0, 0,-Math.PI/2),
        Quat.aroundAxis(0, 0, 1,-Math.PI/2),
        Quat.aroundAxis(0, 0, 1, Math.PI/2)};
    
    public static Transformation[] sideRotationsR = new Transformation[6];
    public static Rotation[] sideRotationsGR = new Rotation[6];
    public static Quat[] sideQuatsR = new Quat[6];
    
    public static Vector3[] axes = new Vector3[]{
        new Vector3( 0,-1, 0),
        new Vector3( 0, 1, 0),
        new Vector3( 0, 0,-1),
        new Vector3( 0, 0, 1),
        new Vector3(-1, 0, 0),
        new Vector3( 1, 0, 0)};
    
    private static int[] sideRotMap = new int[]{
        3,4,2,5,
        3,5,2,4,
        1,5,0,4,
        1,4,0,5,
        1,2,0,3,
        1,3,0,2};
    
    private static int[] rotSideMap = new int[]{
        -1,-1, 2, 0, 3, 1,
        -1,-1, 2, 0, 1, 3,
         2, 0,-1,-1, 3, 1,
         2, 0,-1,-1, 1, 3,
         2, 0, 1, 3,-1,-1,
         2, 0, 3, 1,-1,-1};
    
    private static Rotation[] sideRotRotations = new Rotation[24];
    
    static
    {
        double sqt = MathHelper.sqrt2*0.5;
        Quat[] sideRotQuats = new Quat[]{
            new Quat( 1  , 0  , 0  , 0  ),
            new Quat( sqt, 0  ,-sqt, 0  ),
            new Quat( 0  , 0  ,-1  , 0  ),
            new Quat(-sqt, 0  ,-sqt, 0  ),
            new Quat( 0  , 0  , 0  ,-1  ),
            new Quat( 0  ,-sqt, 0  ,-sqt),
            new Quat( 0  , 1  , 0  , 0  ),
            new Quat( 0  ,-sqt, 0  , sqt),
            new Quat( 0  , 0  ,-sqt,-sqt),
            new Quat(-0.5,-0.5,-0.5,-0.5),
            new Quat( sqt, sqt, 0  , 0  ),
            new Quat(-0.5,-0.5, 0.5, 0.5),
            new Quat( sqt,-sqt, 0  , 0  ),
            new Quat( 0.5,-0.5,-0.5, 0.5),
            new Quat( 0  , 0  ,-sqt, sqt),
            new Quat(-0.5, 0.5,-0.5, 0.5),
            new Quat( 0.5,-0.5,-0.5,-0.5),
            new Quat( 0  ,-sqt,-sqt, 0  ),
            new Quat(-0.5,-0.5,-0.5, 0.5),
            new Quat( sqt, 0  , 0  ,-sqt),
            new Quat( 0.5,-0.5, 0.5, 0.5),
            new Quat( sqt, 0  , 0  , sqt),
            new Quat( 0.5, 0.5,-0.5, 0.5),
            new Quat( 0  , sqt,-sqt, 0  )};
        
        for(int i = 0; i < 24; i++)
            sideRotRotations[i] = new Rotation(sideRotQuats[i]);
        
        int[] rev = new int[]{0, 1, 3, 2, 5, 4};
        for(int i = 0; i < 6; i++)
        {
            int r = rev[i];
            sideRotationsR[i] = sideRotations[r];
            sideQuatsR[i] = sideQuats[r];
        }
    }
    
    public static int rotateSide(int s, int r)
    {
        return sideRotMap[s<<2|r];
    }
    
    /**
     * Reverse of rotateSide
     */
    public static int rotationTo(int s1, int s2)
    {
        if((s1&6) == (s2&6))
            throw new IllegalArgumentException("Faces "+s1+" and "+s2+" are opposites");
        return rotSideMap[s1*6+s2];
    }
    
    /**
     * @param player The placing player, used for obtaining the look vector
     * @param side The side of the block being placed on
     * @return The rotation for the face == side^1
     */
    public static int getSidedRotation(EntityPlayer player, int side)
    {
        Vector3 look = new Vector3(player.getLook(1));
        double max = 0;
        int maxr = 0;
        for(int r = 0; r < 4; r++)
        {
            Vector3 axis = Rotation.axes[rotateSide(side^1, r)];
            double d = look.scalarProject(axis);
            if(max > d)
            {
                max = d;
                maxr = r;
            }
        }
        return maxr;
    }
    
    /**
     * @return The rotation quat for side 0 and rotation 0 to side s with rotation r
     */
    public static Rotation sideRotation(int s, int r)
    {
        return sideRotRotations[s<<2|r];
    }
    
    public double angle;
    public Vector3 axis;
    
    private Quat quat;
    
    public Rotation(double angle, Vector3 axis)
    {
        this.angle = angle;
        this.axis = axis;
    }
    
    public Rotation(double angle, double x, double y, double z)
    {
        this(angle, new Vector3(x, y, z));
    }
    
    public Rotation(Quat quat)
    {
        this.quat = quat;
        
        angle = Math.acos(quat.s)*2;
        if(angle == 0)
        {
            axis = new Vector3(0, 1, 0);
        }
        else
        {
            double sa = Math.sin(angle*0.5);
            axis = new Vector3(quat.x/sa, quat.y/sa, quat.z/sa);
        }
    }

    @Override
    public void apply(Vector3 vec)
    {
        if(quat == null)
            quat = Quat.aroundAxis(axis, angle);
        
        vec.rotate(quat);
    }
    
    @Override
    public void applyN(Vector3 normal)
    {
        apply(normal);
    }

    @Override
    public void apply(Matrix4 mat)
    {
        mat.rotate(angle, axis);
    }
    
    public Quat toQuat()
    {
        if(quat == null)
            quat = Quat.aroundAxis(axis, angle);
        return quat;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void glApply()
    {
        GL11.glRotatef((float)(angle*MathHelper.todeg), (float)axis.x, (float)axis.y, (float)axis.z);
    }
}

package codechickenlib.core.raytracer;

import codechickenlib.core.vec.Cuboid6;
import codechickenlib.core.vec.Rotation;
import codechickenlib.core.vec.Transformation;
import codechickenlib.core.vec.Vector3;

public class SelectionBox
{
    private Vector3[] corners = new Vector3[8];
    private static Rotation[] hRot = new Rotation[]{
        new Rotation(0, 1, 0, 0),
        new Rotation(0, 1, 0,-Math.PI/2),
        new Rotation(0, 1, 0, Math.PI),
        new Rotation(0, 1, 0, Math.PI/2)};
    
    private SelectionBox()
    {
    }
    
    public SelectionBox(Cuboid6 bound)
    {
        Vector3 min = bound.min;
        Vector3 max = bound.max;
        corners[0] = new Vector3(min.x, min.y, min.z);
        corners[1] = new Vector3(min.x, min.y, max.z);
        corners[2] = new Vector3(min.x, max.y, min.z);
        corners[3] = new Vector3(min.x, max.y, max.z);
        corners[4] = new Vector3(max.x, min.y, min.z);
        corners[5] = new Vector3(max.x, min.y, max.z);
        corners[6] = new Vector3(max.x, max.y, min.z);
        corners[7] = new Vector3(max.x, max.y, max.z);
    }
    
    public SelectionBox rotateH(int rotation)
    {
        return rotateH(rotation, Vector3.center);
    }
    
    public SelectionBox rotateH(int rotation, Vector3 point)
    {
        return transform(hRot[(rotation+2)%4].at(point));
    }
    
    public Cuboid6 bound()
    {
        Vector3 min = new Vector3(corners[0]);
        Vector3 max = new Vector3(corners[0]);
        
        for(Vector3 vec : corners)
        {
            if(vec.x > max.x) max.x = vec.x;
            if(vec.y > max.y) max.y = vec.y;
            if(vec.z > max.z) max.z = vec.z;
            if(vec.x < min.x) min.x = vec.x;
            if(vec.y < min.y) min.y = vec.y;
            if(vec.z < min.z) min.z = vec.z;
        }
        
        return new Cuboid6(min, max);
    }

    public SelectionBox copy()
    {
        SelectionBox copy = new SelectionBox();
        for(int i = 0; i < 8; i++)
            copy.corners[i] = corners[i].copy();
        return copy;
    }
    
    public SelectionBox transform(Transformation t)
    {
        for(int k = 0; k < corners.length; k++)
            corners[k].apply(t);
        
        return this;
    }
}

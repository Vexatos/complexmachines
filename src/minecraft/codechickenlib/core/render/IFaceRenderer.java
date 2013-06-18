package codechickenlib.core.render;

import codechickenlib.core.lighting.LightMatrix;
import codechickenlib.core.vec.Vector3;

public interface IFaceRenderer
{
    public void renderFace(Vertex5[] face, int side, Vector3 pos, LightMatrix lightMatrix);
}

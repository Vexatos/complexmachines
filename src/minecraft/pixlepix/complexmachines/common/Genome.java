package pixlepix.complexmachines.common;

import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;

public class Genome {
	
	public String texturePrefix="/mods/ComplexMachines/textures/mobs/";
	public String[] textures={"pigzombie.png","zombie.png","creeper.png","skeleton.png","cat_red.png", "cavespider.png", "cow.png","enderman.png","ghast.png","pig.png","redcow.png"};
	
	public String texture;
	public int damage;
	public float moveSpeed;
	public boolean hostile;
	public boolean provokable;
	public Genome(int health, String texture, int damage, float moveSpeed, boolean hostile, boolean provokable){
		
		this.texture=texture;
		this.damage=damage;
		this.moveSpeed=moveSpeed;
		this.hostile=hostile;
		this.provokable=provokable;
	}
	public Genome(){
		Random rand=new Random();
		this.texture=texturePrefix+textures[rand.nextInt(textures.length)];
		this.damage=rand.nextInt(6)+1;
		this.moveSpeed=rand.nextFloat();
		this.hostile=rand.nextInt(10)>5;
		this.provokable=rand.nextInt(10)>5;
	}
	
	public void writeToNbt(NBTTagCompound par1NBTTagCompound){

		par1NBTTagCompound.setString("texture", texture);
		par1NBTTagCompound.setInteger("damage", damage);

		par1NBTTagCompound.setFloat("moveSpeed", moveSpeed);
		par1NBTTagCompound.setBoolean("hostile",hostile);
	}
	
}

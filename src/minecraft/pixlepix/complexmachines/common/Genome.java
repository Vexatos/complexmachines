package pixlepix.complexmachines.common;

import java.util.Random;

public class Genome {
	
	
	public String[] textures={"/mob/pigzombie.png","/mob/zombie.png","/mob/creeper.png","/mob/skeleton.png"};
	
	public int health;
	public String texture;
	public int damage;
	public float moveSpeed;
	public boolean hostile;
	public boolean provokable;
	public Genome(int health, String texture, int damage, float moveSpeed, boolean hostile, boolean provokable){
		this.health=health;
		this.texture=texture;
		this.damage=damage;
		this.moveSpeed=moveSpeed;
		this.hostile=hostile;
		this.provokable=provokable;
	}
	public Genome(){
		Random rand=new Random();
		this.health=rand.nextInt(20)+1;
		this.texture=textures[rand.nextInt(3)];
		this.damage=rand.nextInt(6)+1;
		this.moveSpeed=rand.nextFloat();
		this.hostile=rand.nextInt(10)>5;
		this.provokable=rand.nextInt(10)>5;
	}
	
}

package pixlepix.complexmachines.common.mob;

import pixlepix.complexmachines.common.Genome;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class GeneticMob extends EntityMob {
	
	
	public Genome genome;
	
	
	@Override
	public void setDead(){

		System.out.println("Killing genetic mob");
		super.setDead();
	}
	
	public void updateEntity(){
		System.out.println("Genetic mob is alive");
	}
	
	
	public GeneticMob(World par1World) {
		super(par1World);
		genome=new Genome();
		moveSpeed=genome.moveSpeed;
		this.texture=genome.texture;
		
		
		/* 
		this.tasks.addTask(0, new EntityAISwimming(this));
		 if(genome.hostile||genome.provokable){

		        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		 }
		 if(genome.hostile){
	         this.tasks.addTask(1, new EntityAIBreakDoor(this));
	         this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, this.moveSpeed, false));
	         this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		 }
         this.tasks.addTask(4, new EntityAIWander(this, this.moveSpeed));
		*/
		
	}
	@Override
	protected boolean isAIEnabled()
    {
        return true;
    }

	@Override
	public int getAttackStrength(Entity par1Entity)
    {
		if(genome==null){
			System.out.println("Null Genome");
			return 10;
			
		}
     return genome.damage;
    }

	@Override
	public int getMaxHealth() {
		// TODO Auto-generated method stub
		if(genome==null){
			System.out.println("Null Genome");
			return 10;
			
		}
		return genome.health;
	}


}

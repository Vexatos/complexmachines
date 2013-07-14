package pixlepix.complexmachines.common.mob;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import pixlepix.complexmachines.common.Genome;

public class GeneticMob extends EntityMob {
	
	
	public Genome genome;
	
	
	@Override
	public void setDead(){

		super.setDead();
	}
	public boolean canDespawn(){
		return false;
	}
	
	
	
	
	public GeneticMob(World par1World) {
		super(par1World);
		float moveSpeed;
		String texture;
		genome=new Genome();
		moveSpeed=genome.moveSpeed;
		texture=genome.texture;
		
		
		this.tasks.addTask(0, new EntityAISwimming(this));
		 
		 
		 if(genome.hostile){
			 this.tasks.addTask(0, new EntityAISwimming(this));
		        this.tasks.addTask(1, new EntityAIBreakDoor(this));
		        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, moveSpeed, false));
		        this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityVillager.class, moveSpeed, true));
		        this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, moveSpeed));
		        this.tasks.addTask(5, new EntityAIMoveThroughVillage(this, moveSpeed, false));
		        this.tasks.addTask(6, new EntityAIWander(this, moveSpeed));
		        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		        this.tasks.addTask(7, new EntityAILookIdle(this));
		        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		       
		 }
		 if(!genome.provokable){

			 this.getNavigator().setAvoidsWater(true);
		        this.tasks.addTask(0, new EntityAISwimming(this));
		        this.tasks.addTask(1, new EntityAIPanic(this, 0.38F));
		        //this.tasks.addTask(2, new EntityAIMate(this, 0.2F));
		        this.tasks.addTask(3, new EntityAITempt(this, 0.25F, Item.wheat.itemID, false));
		        //this.tasks.addTask(4, new EntityAIFollowParent(this, 0.25F));
		        this.tasks.addTask(5, new EntityAIWander(this, 0.2F));
		        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		        this.tasks.addTask(7, new EntityAILookIdle(this));
		 }
		
		
	}
	@Override
	protected boolean isAIEnabled()
    {
        return true;
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}

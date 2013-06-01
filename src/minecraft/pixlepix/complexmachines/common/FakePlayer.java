package pixlepix.complexmachines.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityMinecartHopper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EnumStatus;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.stats.StatBase;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.FoodStats;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StringTranslate;
import net.minecraft.world.World;

import net.minecraft.world.WorldServer;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.entity.player.EntityInteractEvent;

public class FakePlayer extends EntityPlayer {

	public EntityPlayer realPlayer;

	public FakePlayer(World par1World) {
		super(par1World);
	}
	public void reassertRealPlayer(){
		realPlayer.inventory=this.inventory;
		realPlayer.inventoryContainer=this.inventoryContainer;
		realPlayer.openContainer=this.openContainer;

		realPlayer.field_71098_bD=this.field_71098_bD;

		
	}
	public FakePlayer(World par1World, EntityPlayer realPlayer) {
		super(par1World);
		this.realPlayer=realPlayer;
		
	}

	
	@Override
	public void sendChatToPlayer(String s) {
		realPlayer.sendChatToPlayer(s);
		
	}

	@Override
	public boolean canCommandSenderUseCommand(int i, String s) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ChunkCoordinates getPlayerCoordinates() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	

	    public int getMaxHealth()
	    {
	        return maxHealth <= 0 ? 20 : maxHealth;
	    }

	    

	  
	    public ItemStack getItemInUse()
	    {
	        return realPlayer.getItemInUse();
	    }

	    @SideOnly(Side.CLIENT)

	    /**
	     * Returns the item in use count
	     */
	    

	    /**
	     * Checks if the entity is currently using an item (e.g., bow, food, sword) by holding down the useItemButton
	     */
	    public boolean isUsingItem()
	    {
	        return realPlayer.isUsingItem();
	    }

	    @SideOnly(Side.CLIENT)

	    /**
	     * gets the duration for how long the current itemInUse has been in use
	     */
	    public int getItemInUseDuration()
	    {
	        return realPlayer.getItemInUseDuration();
	    }

	    public void stopUsingItem()
	    {
	        realPlayer.stopUsingItem();
	    }

	    public void clearItemInUse()
	    {
	        realPlayer.clearItemInUse();
	    }

	    public boolean isBlocking()
	    {
	        return realPlayer.isBlocking();
	    }

	    /**
	     * Called to update the entity's position/logic.
	     */
	    public void onUpdate()
	    {
	        realPlayer.onUpdate();
	    }

	    /**
	     * Return the amount of time this entity should stay in a portal before being transported.
	     */
	    public int getMaxInPortalTime()
	    {
	        return this.getMaxInPortalTime();
	    }

	    
	    

	    public void playSound(String par1Str, float par2, float par3)
	    {
	        realPlayer.playSound(par1Str, par2, par3);
	    }

	    /**
	     * Plays sounds and makes particles for item in use state
	     */
	    
	        

	  

	    @SideOnly(Side.CLIENT)
	    public void handleHealthUpdate(byte par1)
	    {
	        realPlayer.handleHealthUpdate(par1);
	    }

	    /**
	     * Dead and sleeping entities cannot move
	     */
	    protected boolean isMovementBlocked()
	    {
	        return this.getHealth() <= 0 || this.isPlayerSleeping();
	    }

	    /**
	     * sets current screen to null (used on escape buttons of GUIs)
	     */
	    public void closeScreen()
	    {
	        this.openContainer = this.inventoryContainer;
	    }

	    /**
	     * Called when a player mounts an entity. e.g. mounts a pig, mounts a boat.
	     */
	    public void mountEntity(Entity par1Entity)
	    {
	       realPlayer.mountEntity(par1Entity);
	    }

	    /**
	     * Handles updating while being ridden by an entity
	     */
	    public void updateRidden()
	    {
	        realPlayer.updateRidden();
	    }

	    @SideOnly(Side.CLIENT)

	    /**
	     * Keeps moving the entity up so it isn't colliding with blocks and other requirements for this entity to be spawned
	     * (only actually used on players though its also on Entity)
	     */
	    public void preparePlayerToSpawn()
	    {
	    	realPlayer.preparePlayerToSpawn();
	    }

	    protected void updateEntityActionState()
	    {
	        this.updateArmSwingProgress();
	    }

	    /**
	     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
	     * use this to react to sunlight and start to burn.
	     */
	    public void onLivingUpdate()
	    {
	        realPlayer.onLivingUpdate();
	    }

	    

	    public int getScore()
	    {
	        return realPlayer.getScore();
	    }

	    /**
	     * Set player's score
	     */
	    public void setScore(int par1)
	    {
	        realPlayer.setScore(par1);
	    }

	    /**
	     * Add to player's score
	     */
	    public void addScore(int par1)
	    {
	        realPlayer.addScore(par1);
	    }

	    /**
	     * Called when the mob's health reaches 0.
	     */
	    public void onDeath(DamageSource par1DamageSource)
	    {
	        realPlayer.onDeath(par1DamageSource);
	    }

	    /**
	     * Adds a value to the player score. Currently not actually used and the entity passed in does nothing. Args:
	     * entity, scoreToAdd
	     */
	    public void addToPlayerScore(Entity par1Entity, int par2)
	    {
	        realPlayer.addToPlayerScore(par1Entity, par2);
	    }

	    /**
	     * Called when player presses the drop item key
	     */
	    public EntityItem dropOneItem(boolean par1)
	    {
	        return realPlayer.dropOneItem(par1);
	    }

	    /**
	     * Args: itemstack - called when player drops an item stack that's not in his inventory (like items still placed in
	     * a workbench while the workbench'es GUI gets closed)
	     */
	    public EntityItem dropPlayerItem(ItemStack par1ItemStack)
	    {
	        return realPlayer.dropPlayerItem(par1ItemStack);
	    }

	    /**
	     * Args: itemstack, flag
	     */
	    public EntityItem dropPlayerItemWithRandomChoice(ItemStack par1ItemStack, boolean par2)
	    {
	        return realPlayer.dropPlayerItemWithRandomChoice(par1ItemStack,par2);
	    }

	    /**
	     * Joins the passed in entity item with the world. Args: entityItem
	     */
	    public void joinEntityItemWithWorld(EntityItem par1EntityItem)
	    {
	       realPlayer.joinEntityItemWithWorld(par1EntityItem);
	    }

	    /**
	     * Returns how strong the player is against the specified block at this moment
	     * Deprecated in favor of the more sensitive version
	     */
	    @Deprecated
	    public float getCurrentPlayerStrVsBlock(Block par1Block, boolean par2)
	    {
	        return realPlayer.getCurrentPlayerStrVsBlock(par1Block, par2, 0);
	    }

	    public float getCurrentPlayerStrVsBlock(Block par1Block, boolean par2, int meta)
	    {
	        
	    	return realPlayer.getCurrentPlayerStrVsBlock(par1Block, par2, meta);
	    }

	    /**
	     * Checks if the player has the ability to harvest a block (checks current inventory item for a tool if necessary)
	     */
	    public boolean canHarvestBlock(Block par1Block)
	    {
	        return realPlayer.canHarvestBlock(par1Block);
	    }

	    /**
	     * (abstract) Protected helper method to read subclass entity data from NBT.
	     */
	    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	    {
	        realPlayer.readEntityFromNBT(par1NBTTagCompound);
	    }

	    /**
	     * (abstract) Protected helper method to write subclass entity data to NBT.
	     */
	    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	    {
	        realPlayer.writeEntityToNBT(par1NBTTagCompound);
	    }

	    /**
	     * Displays the GUI for interacting with a chest inventory. Args: chestInventory
	     */
	    public void displayGUIChest(IInventory par1IInventory) {
	    	realPlayer.displayGUIChest(par1IInventory);
	    }

	    public void displayGUIHopper(TileEntityHopper par1TileEntityHopper) {
	    	realPlayer.displayGUIHopper(par1TileEntityHopper);
	    }

	    public void displayGUIHopperMinecart(EntityMinecartHopper par1EntityMinecartHopper) {
	    	realPlayer.displayGUIHopperMinecart(par1EntityMinecartHopper);
	    }

	    public void displayGUIEnchantment(int par1, int par2, int par3, String par4Str) {
	    	realPlayer.displayGUIEnchantment(par1, par2, par3, par4Str);
	    }

	    /**
	     * Displays the GUI for interacting with an anvil.
	     */
	    public void displayGUIAnvil(int par1, int par2, int par3) {}

	    /**
	     * Displays the crafting GUI for a workbench.
	     */
	    public void displayGUIWorkbench(int par1, int par2, int par3) {}

	    public float getEyeHeight()
	    {
	        return 0.12F;
	    }

	    /**
	     * sets the players height back to normal after doing things like sleeping and dieing
	     */
	    protected void resetHeight()
	    {
	        this.yOffset = 1.62F;
	    }

	    /**
	     * Called when the entity is attacked.
	     */
	    public boolean attackEntityFrom(DamageSource par1DamageSource, int par2)
	    {
	        return realPlayer.attackEntityFrom(par1DamageSource, par2);
	    }

	    public boolean func_96122_a(EntityPlayer par1EntityPlayer)
	    {
	        return realPlayer.func_96122_a(par1EntityPlayer);
	    }

	    /**
	     * Called when the player attack or gets attacked, it's alert all wolves in the area that are owned by the player to
	     * join the attack or defend the player.
	     */
	    

	    

	    /**
	     * Returns the current armor value as determined by a call to InventoryPlayer.getTotalArmorValue
	     */
	    public int getTotalArmorValue()
	    {
	        return realPlayer.getTotalArmorValue();
	    }

	    public float func_82243_bO()
	    {
	        int i = 0;
	        ItemStack[] aitemstack = this.inventory.armorInventory;
	        int j = aitemstack.length;

	        for (int k = 0; k < j; ++k)
	        {
	            ItemStack itemstack = aitemstack[k];

	            if (itemstack != null)
	            {
	                ++i;
	            }
	        }

	        return (float)i / (float)this.inventory.armorInventory.length;
	    }

	    /**
	     * Deals damage to the entity. If its a EntityPlayer then will take damage from the armor first and then health
	     * second with the reduced value. Args: damageAmount
	     */
	   

	    
	    

	    public boolean interactWith(Entity par1Entity)
	    {
	        return realPlayer.interactWith(par1Entity);
	    }

	    /**
	     * Returns the currently being used item by the player.
	     */
	    public ItemStack getCurrentEquippedItem()
	    {
	        return realPlayer.getCurrentEquippedItem();
	    }

	    /**
	     * Destroys the currently equipped item from the player's inventory.
	     */
	    public void destroyCurrentEquippedItem()
	    {
	        realPlayer.destroyCurrentEquippedItem();
	    }

	    /**
	     * Returns the Y Offset of this entity.
	     */
	    public double getYOffset()
	    {
	        return getYOffset();
	    }

	    /**
	     * Attacks for the player the targeted entity with the currently equipped item.  The equipped item has hitEntity
	     * called on it. Args: targetEntity
	     */
	    public void attackTargetEntityWithCurrentItem(Entity par1Entity)
	    {
	        realPlayer.attackTargetEntityWithCurrentItem(par1Entity);
	    }

	    /**
	     * Called when the player performs a critical hit on the Entity. Args: entity that was hit critically
	     */
	    public void onCriticalHit(Entity par1Entity) {}

	    public void onEnchantmentCritical(Entity par1Entity) {}

	    @SideOnly(Side.CLIENT)
	    public void respawnPlayer() {}

	    /**
	     * Will get destroyed next tick.
	     */
	    public void setDead()
	    {
	        realPlayer.setDead();
	    }

	    /**
	     * Checks if this entity is inside of an opaque block
	     */
	    public boolean isEntityInsideOpaqueBlock()
	    {
	        return realPlayer.isEntityInsideOpaqueBlock();
	    }

	    public boolean func_71066_bF()
	    {
	        return false;
	    }

	    /**
	     * Attempts to have the player sleep in a bed at the specified location.
	     */
	    public EnumStatus sleepInBedAt(int par1, int par2, int par3)
	    {
	        return sleepInBedAt(par1,par2,par3);
	    }

	    

	    /**
	     * Wake up the player if they're sleeping.
	     */
	    public void wakeUpPlayer(boolean par1, boolean par2, boolean par3)
	    {
	    	realPlayer.wakeUpPlayer(par1, par2, par3);
	    }

	    

	    

	    @SideOnly(Side.CLIENT)

	    /**
	     * Returns the orientation of the bed in degrees.
	     */
	    public float getBedOrientationInDegrees()
	    {
	        return realPlayer.getBedOrientationInDegrees();
	    }

	    /**
	     * Returns whether player is sleeping or not
	     */
	    public boolean isPlayerSleeping()
	    {
	        return realPlayer.isPlayerSleeping();
	    }

	    /**
	     * Returns whether or not the player is asleep and the screen has fully faded.
	     */
	    public boolean isPlayerFullyAsleep()
	    {
	        return realPlayer.isPlayerFullyAsleep();
	    }

	    @SideOnly(Side.CLIENT)
	    public int getSleepTimer()
	    {
	        return realPlayer.getSleepTimer();
	    }

	    
	    public void addChatMessage(String par1Str) {
	    	realPlayer.addChatMessage(par1Str);
	    }

	    /**
	     * Returns the location of the bed the player will respawn at, or null if the player has not slept in a bed.
	     */
	    public ChunkCoordinates getBedLocation()
	    {
	        return realPlayer.getBedLocation();
	    }

	    public boolean isSpawnForced()
	    {
	        return realPlayer.isSpawnForced();
	    }

	    /**
	     * Defines a spawn coordinate to player spawn. Used by bed after the player sleep on it.
	     */
	    public void setSpawnChunk(ChunkCoordinates par1ChunkCoordinates, boolean par2)
	    {
	        realPlayer.setSpawnChunk(par1ChunkCoordinates, par2);
	    }

	    /**
	     * Will trigger the specified trigger.
	     */
	    public void triggerAchievement(StatBase par1StatBase)
	    {
	        realPlayer.triggerAchievement(par1StatBase);
	    }

	    /**
	     * Adds a value to a statistic field.
	     */
	    public void addStat(StatBase par1StatBase, int par2) {}

	    /**
	     * Causes this entity to do an upwards motion (jumping).
	     */
	    
	    public void moveEntityWithHeading(float par1, float par2)
	    {
	        realPlayer.moveEntityWithHeading(par1, par2);
	    }

	    /**
	     * Adds a value to a movement statistic field - like run, walk, swin or climb.
	     */
	    public void addMovementStat(double par1, double par3, double par5)
	    {
	        realPlayer.addMovementStat(par1, par3, par5);
	    }

	    
	    public void onKillEntity(EntityLiving par1EntityLiving)
	    {
	        realPlayer.onKillEntity(par1EntityLiving);
	    }

	    /**
	     * Sets the Entity inside a web block.
	     */
	    public void setInWeb()
	    {
	        realPlayer.setInWeb();
	    }

	    @SideOnly(Side.CLIENT)

	    /**
	     * Gets the Icon Index of the item currently held
	     */
	    public Icon getItemIcon(ItemStack par1ItemStack, int par2)
	    {
	        return realPlayer.getItemIcon(par1ItemStack, par2);
	    }

	    public ItemStack getCurrentArmor(int par1)
	    {
	        return realPlayer.getCurrentArmor(par1);
	    }

	    /**
	     * Makes entity wear random armor based on difficulty
	     */
	    protected void addRandomArmor() {}

	    protected void func_82162_bC() {}

	    /**
	     * This method increases the player's current amount of experience.
	     */
	    public void addExperience(int par1)
	    {
	        realPlayer.addExperience(par1);
	    }

	    /**
	     * Add experience levels to this player.
	     */
	    public void addExperienceLevel(int par1)
	    {
	        realPlayer.addExperienceLevel(par1);
	    }

	    /**
	     * This method returns the cap amount of experience that the experience bar can hold. With each level, the
	     * experience cap on the player's experience bar is raised by 10.
	     */
	    public int xpBarCap()
	    {
	        return realPlayer.xpBarCap();
	    }

	    /**
	     * increases exhaustion level by supplied amount
	     */
	    public void addExhaustion(float par1)
	    {
	        realPlayer.addExhaustion(par1);
	    }

	    /**
	     * Returns the player's FoodStats object.
	     */
	    public FoodStats getFoodStats()
	    {
	        return realPlayer.getFoodStats();
	    }

	    public boolean canEat(boolean par1)
	    {
	        return realPlayer.canEat(par1);
	    }

	    /**
	     * Checks if the player's health is not full and not zero.
	     */
	    public boolean shouldHeal()
	    {
	        return realPlayer.shouldHeal();
	    }

	    /**
	     * sets the itemInUse when the use item button is clicked. Args: itemstack, int maxItemUseDuration
	     */
	    public void setItemInUse(ItemStack par1ItemStack, int par2)
	    {
	        realPlayer.setItemInUse(par1ItemStack, par2);
	    }

	    /**
	     * Returns true if the item the player is holding can harvest the block at the given coords. Args: x, y, z.
	     */
	    public boolean canCurrentToolHarvestBlock(int par1, int par2, int par3)
	    {
	       return realPlayer.canCurrentToolHarvestBlock(par1, par2, par3);
	    }

	    public boolean canPlayerEdit(int par1, int par2, int par3, int par4, ItemStack par5ItemStack)
	    {
	        return realPlayer.canPlayerEdit(par1, par2, par3, par4, par5ItemStack);
	    }

	    /**
	     * Get the experience points the entity currently has.
	     */
	   
	    public String getEntityName()
	    {
	        return realPlayer.getEntityName();
	    }

	    public boolean func_94062_bN()
	    {
	        return realPlayer.func_94062_bN();
	    }

	    @SideOnly(Side.CLIENT)
	    public boolean func_94059_bO()
	    {
	        return true;
	    }

	    public boolean canPickUpLoot()
	    {
	        return false;
	    }

	    /**
	     * Copies the values from the given player into this player if boolean par2 is true. Always clones Ender Chest
	     * Inventory.
	     */
	    public void clonePlayer(EntityPlayer par1EntityPlayer, boolean par2)
	    {
	       realPlayer.clonePlayer(par1EntityPlayer, par2);
	    }

	    /**
	     * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
	     * prevent them from trampling crops
	     */
	    
	    public String getCommandSenderName()
	    {
	        return realPlayer.getCommandSenderName();
	    }

	    public StringTranslate getTranslator()
	    {
	        return realPlayer.getTranslator();
	    }

	    /**
	     * Translates and formats the given string key with the given arguments.
	     */
	    public String translateString(String par1Str, Object ... par2ArrayOfObj)
	    {
	        return realPlayer.translateString(par1Str, par2ArrayOfObj);
	    }

	    /**
	     * Returns the InventoryEnderChest of this player.
	     */
	    public InventoryEnderChest getInventoryEnderChest()
	    {
	        return realPlayer.getInventoryEnderChest();
	    }

	    /**
	     * 0 = item, 1-n is armor
	     */
	    public ItemStack getCurrentItemOrArmor(int par1)
	    {
	        return realPlayer.getCurrentItemOrArmor(par1);
	    }

	    /**
	     * Returns the item that this EntityLiving is holding, if any.
	     */
	    public ItemStack getHeldItem()
	    {
	        return realPlayer.getHeldItem();
	    }

	    /**
	     * Sets the held item, or an armor slot. Slot 0 is held item. Slot 1-4 is armor. Params: Item, slot
	     */
	    public void setCurrentItemOrArmor(int par1, ItemStack par2ItemStack)
	    {
	        realPlayer.setCurrentItemOrArmor(par1, par2ItemStack);
	    }

	    @SideOnly(Side.CLIENT)
	    public boolean func_98034_c(EntityPlayer par1EntityPlayer)
	    {
	        return realPlayer.func_98034_c(par1EntityPlayer);
	    }

	    public ItemStack[] getLastActiveItems()
	    {
	        return realPlayer.getLastActiveItems();
	    }

	    @SideOnly(Side.CLIENT)
	    public boolean getHideCape()
	    {
	        return realPlayer.getHideCape();
	    }

	    public boolean func_96092_aw()
	    {
	        return realPlayer.func_96092_aw();
	    }

	    public Scoreboard getWorldScoreboard()
	    {
	        return realPlayer.getWorldScoreboard();
	    }

	    public ScorePlayerTeam getTeam()
	    {
	        return realPlayer.getTeam();
	    }

	    /**
	     * Returns the translated name of the entity.
	     */
	    public String getTranslatedEntityName()
	    {
	        return realPlayer.getTranslatedEntityName();
	    }

	    public void openGui(Object mod, int modGuiId, World world, int x, int y, int z)
	    {
	         realPlayer.openGui( mod, modGuiId, world, x, y, z);
	    }
	    
	    
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	



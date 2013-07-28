package pixlepix.complexmachines.common.tileentity;

import java.util.List;

import pixlepix.complexmachines.common.BasicComplexTileEntity;
import pixlepix.complexmachines.common.Config;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class FluxTileEntity extends BasicComplexTileEntity {
	public int count;
	public boolean established=false;
	public int ticks;
	TileEntity entityInCharge;
	private boolean initialized=false;;
	public void count(){
		count++;
	}
	public void initiate() {
		this.initialized = true;
	}
	@Override
	public float getMaxEnergyStored() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public void updateEntity(){
		super.updateEntity();
		
		if(worldObj.getTotalWorldTime()%100==0){
			established=true;
			count=0;
		}
		
		if(worldObj.getTotalWorldTime()%100==1&&established){
			int x=xCoord;
			int y=yCoord;
			int z=zCoord;
			int[][] blocks={{x+1,z+1},{x,z+1},{x-1,z+1},{x+1,z},{x-1,z},{x+1,z-1},{x,z-1},{x-1,z-1}};
			
			for(int i=0;i<8;i++){
				
				if(worldObj.getBlockId(blocks[i][0], y, blocks[i][1])==0){
					worldObj.setBlock(blocks[i][0], y, blocks[i][1], Config.blockStartingID+17);
					TileEntity targetTileEntity=worldObj.getBlockTileEntity(blocks[i][0], y, blocks[i][1]);
				}
			}
		}
		if(worldObj.getTotalWorldTime()%100==2&&established){
			int x=xCoord;
			int y=yCoord;
			int z=zCoord;
			int[][] blocks={{x+1,z+1},{x,z+1},{x-1,z+1},{x+1,z},{x-1,z},{x+1,z-1},{x,z-1},{x-1,z-1}};
			
			for(int i=0;i<8;i++){
				if(worldObj.blockHasTileEntity(blocks[i][0], y, blocks[i][1])){
					TileEntity targetTileEntity=worldObj.getBlockTileEntity(blocks[i][0], y, blocks[i][1]);
					if(targetTileEntity instanceof FluxTileEntity){
						((FluxTileEntity)targetTileEntity).count();
					}
					
				}
				
			}
			
		}	
			
			
			
			
		
		if(worldObj.getTotalWorldTime()%100==3){
			boolean survivor=false;
			//System.out.println("Value at: "+xCoord+" , "+zCoord+" is "+count);
			if(established){
				if(count==2||count==3){
					survivor=true;
				}
			}else{
				if(count==3){
					survivor=true;
				}
			}
			if(!survivor){
				worldObj.setBlockToAir(xCoord, yCoord, zCoord);
			}
		}
			
	}
	@Override
	public float getRequest(ForgeDirection direction) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public float getProvide(ForgeDirection direction) {
		// TODO Auto-generated method stub
		return 0;
	}
}

   

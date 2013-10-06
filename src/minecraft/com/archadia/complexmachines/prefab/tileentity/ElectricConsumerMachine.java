package com.archadia.complexmachines.prefab.tileentity;

import net.minecraftforge.common.ForgeDirection;
import universalelectricity.core.block.IElectrical;
import universalelectricity.core.block.IElectricalStorage;

/**
 * @author Archadia
 *
 */
public class ElectricConsumerMachine extends ElectricContainer implements IElectrical, IElectricalStorage {

	public final double WATTS_PER_TICK = 5000;
	public final double TRANSFER_LIMIT = 12500;
	private double joulesStored = 0;
	public double maxJoules = 2000000;
	
	@Override
	public float getMaxEnergyStored() {
		return getMaximumEnergy();
	}

	public int getMaximumEnergy(){
			return (int) this.getMaxJoules();
	}
	
	private double getMaxJoules() {
		return maxJoules;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
	}

	@Override
	public float getVoltage() {
		return 480;
	}

	public double getJoules() {
		return this.getEnergyStored();
	}
 
	public void setJoules(double joules) {
		this.setEnergyStored((float)joules);
	}

	@Override
	public boolean canConnect(ForgeDirection direction) {
		return direction.ordinal() == this.getBlockMetadata() + 2;
	}
	
	@Override
	public float getRequest(ForgeDirection direction) {
		return 10000;
	}

	@Override
	public float getProvide(ForgeDirection direction) {
		return 0;
	}
}

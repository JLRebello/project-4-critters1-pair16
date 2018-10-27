package assignment4;

import java.util.*;

public class PowerLord extends Critter.TestCritter {
	
	PowerLord(){
		this.setEnergy(Params.start_energy);
		this.setX_coord(Critter.getRandomInt(Params.world_width));
		this.setY_coord(Critter.getRandomInt(Params.world_height));
	}

	@Override
	public void doTimeStep() {
		walk(Critter.getRandomInt(8));
		this.setEnergy(this.getEnergy()-Params.walk_energy_cost);
	}

	@Override
	public boolean fight(String opponent) {
		if (getEnergy() > 10) return true;
		return false;
	}
	
	public String toString() {
		return "1";
	}
	
	public void test (List<Critter> l) {
		
	}
}
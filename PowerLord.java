package assignment4;

import java.util.*;

public class PowerLord extends Critter {
	
	PowerLord(){
		this.setEnergy(Params.start_energy);
		this.setX(Critter.getRandomInt(Params.world_width - 1));
		this.setY(Critter.getRandomInt(Params.world_height - 1));
		myWorld.world[getY()][getX()].add(this);
		TestCritter.getPopulation().add(this);
	}

	@Override
	public void doTimeStep() {
		walk(Critter.getRandomInt(8));
		this.setEnergy(this.getEnergy()-Params.walk_energy_cost);
		if (this.getEnergy() <= 0) {
			Critter.myWorld.world[getX()][getY()].remove(this);
			TestCritter.getPopulation().remove(this);
		}
		// will these remove the right one if there's two??
	}

	@Override
	public boolean fight(String opponent) {
		if (opponent.equals("GodEmperor")) return false;
		return true;
	}
	
	public String toString() {
		return "PowerLord";
	}
	
	public void test (List<Critter> l) {
		
	}
}
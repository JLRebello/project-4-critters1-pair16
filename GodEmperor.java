package assignment4;

import assignment4.Critter.TestCritter;

public class GodEmperor extends Critter {
	// will only run and fight, will never run from a fight or reproduce

	GodEmperor(){
		this.setEnergy(Params.start_energy);
		this.setX(Critter.getRandomInt(Params.world_width - 1));
		this.setY(Critter.getRandomInt(Params.world_height - 1));
		myWorld.world[getY()][getX()].add(this);
		TestCritter.getPopulation().add(this);
	}
	
	@Override
	public void doTimeStep() {
		run(Critter.getRandomInt(8));
		this.setEnergy(this.getEnergy() - Params.run_energy_cost);
		if (this.getEnergy() <= 0) {
			Critter.myWorld.world[getX()][getY()].remove(this);
			TestCritter.getPopulation().remove(this);
		}
	}

	@Override
	public boolean fight(String opponent) {
		if(opponent.equals("G")) return false;
		return true;
	}

	@Override
	public String toString () {
		return "G";
	}
}

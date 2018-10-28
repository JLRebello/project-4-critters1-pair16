package assignment4;

import java.util.*;

public class PowerLord extends Critter {
	
	PowerLord(){
		this.setEnergy(Params.start_energy);
		this.setX(Critter.getRandomInt(Params.world_width - 1));
		this.setY(Critter.getRandomInt(Params.world_height - 1));
		myWorld.world[getY()][getX()].add(this);
	}

	@Override
	public void doTimeStep() {
		if(this.getEnergy() >= Params.min_reproduce_energy) {
			PowerLord lilLord = new PowerLord();
			this.reproduce(lilLord,Critter.getRandomInt(8));
			this.setEnergy(this.getEnergy()/2);
			lilLord.setEnergy(this.getEnergy()/2);
		}
		else{
			walk(Critter.getRandomInt(8));
			this.moveFlag = true;
			this.setEnergy(this.getEnergy()-Params.walk_energy_cost);		//power Lord is supernatural so he uses walk energy to run
		}		
		if (this.getEnergy() <= 0) {
			Critter.myWorld.world[getX()][getY()].remove(this);
			TestCritter.getPopulation().remove(this);
		}
	}

	@Override
	public boolean fight(String opponent) {
		if (opponent.equals("G")) return false;
		return true;
	}
	
	public String toString() {
		return "P";
	}
}
package assignment4;
/*GodEmperor.java 
* EE422C Project 4 submission by
* Julia Rebello
* JLR3755
* Section 16365
* Samir Riad
* SR43888
* Section 16360
* Slip days used: <0>
* Fall 2018
*/
import assignment4.Critter.TestCritter;

public class GodEmperor extends Critter {
	// will only run and fight, will never run from a fight or reproduce

	GodEmperor(){
		this.setEnergy(Params.start_energy);
		this.setX(Critter.getRandomInt(Params.world_width - 1));
		this.setY(Critter.getRandomInt(Params.world_height - 1));
	}
	
	@Override
	public void doTimeStep() {
		run(Critter.getRandomInt(8));
		this.moveFlag = true;
		this.setEnergy(this.getEnergy() - Params.rest_energy_cost);
		if (this.getEnergy() <= 0) {
			Critter.myWorld.world[getX()][getY()].remove(this);
			TestCritter.getPopulation().remove(this);
		}
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

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
/*Critter1's always fight when given the chance. 
 * They are the only Critters Critter2’s are afraid of. 
 * They never reproduce however, and always run around the simulated world. 
 * Their joy in life is fighting other critters.
 */

public class Critter1 extends Critter {
	// will only run and fight, will never run from a fight or reproduce

	Critter1(){
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

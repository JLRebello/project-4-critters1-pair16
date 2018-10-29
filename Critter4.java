package assignment4;
/*Yoshi.java 
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
/*Critter4’s are smart and athletic. 
 * They only fight when they have half or more of their initial energy, 
 * and they run with the energy it takes a normal critter to walk. They reproduce randomly.
 */
public class Critter4 extends Critter {

	Critter4(){
		this.setEnergy(Params.start_energy);
		this.setX(Critter.getRandomInt(Params.world_width - 1));
		this.setY(Critter.getRandomInt(Params.world_height - 1));
	}

	@Override
	public void doTimeStep() {
		int activity = Critter.getRandomInt(3);
		if(activity == 0) {
			walk(Critter.getRandomInt(8));
			this.moveFlag = true;
			this.setEnergy(this.getEnergy()-Params.walk_energy_cost);
		}
		else if(activity == 1) {				// Yoshi is athletic so he only uses walk energy to run
			run(Critter.getRandomInt(8));
			this.moveFlag = true;
			this.setEnergy(this.getEnergy()-Params.walk_energy_cost);
		}
		else if(this.getEnergy() >= Params.min_reproduce_energy) {
			Critter4 egg = new Critter4();
			this.reproduce(egg,Critter.getRandomInt(8));
			this.setEnergy(this.getEnergy()/2);
			egg.setEnergy(this.getEnergy()/2);
		}
		else {
			this.setEnergy(this.getEnergy()-Params.rest_energy_cost);
		}
	}

	@Override
	public boolean fight(String opponent) {
		if (getEnergy() > (Params.start_energy/2)) {
			return true;
		}
		int check = this.getX() + 1 ;
		if(check > (Params.world_width-1)) {
			check = 0;
		}
		if((this.moveFlag == false) && (myWorld.world[this.getY()][check].isEmpty())){
			walk(0);
			this.moveFlag = true;
			this.setEnergy(this.getEnergy() - Params.run_energy_cost);
			return false;
		}
		else {
			this.setEnergy(this.getEnergy() - Params.run_energy_cost);
			return false;
		}
	}

	@Override
	public String toString () {
		return "Y";
	}
}
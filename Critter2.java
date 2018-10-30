package assignment4;
/*PowerLord.java 
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
import java.util.*;
/*Critter2’s always fight when given the chance, unless their opponent is a Critter1, which they fear greatly.
 * What makes Critter2’s  unique is that if they have energy to reproduce, they will do so every time, 
 * until they don’t have the energy anymore, in which case they walk. 
 * They do this because they want their children to take over the simulated world.
 */

public class Critter2 extends Critter {
	
	Critter2(){}

	@Override
	public void doTimeStep() {
		if(this.getEnergy() >= Params.min_reproduce_energy) {
			Critter2 lilLord = new Critter2();
			this.reproduce(lilLord,Critter.getRandomInt(8));
			this.setEnergy(this.getEnergy()/2);
			lilLord.setEnergy(this.getEnergy()/2);
		}
		else{
			walk(Critter.getRandomInt(8));
			this.moveFlag = true;
			this.setEnergy(this.getEnergy()-Params.walk_energy_cost);		//power Lord is supernatural so he uses walk energy to run
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
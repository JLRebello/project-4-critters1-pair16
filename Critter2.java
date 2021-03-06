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
    /**
     * doTime method.
     * This method allows this Critter to do their timeStep
     */
	@Override
	public void doTimeStep() {
		if(this.getEnergy() >= Params.min_reproduce_energy) {
			Critter2 lilLord = new Critter2();
			this.reproduce(lilLord,Critter.getRandomInt(8));
		}
		else{
			walk(Critter.getRandomInt(8));
			this.moveFlag = true;
		}		
	}
    /**
     * fight method.
     * This method allows this Critter to do encounter behavior
     */
	@Override
	public boolean fight(String opponent) {
		if (opponent.equals("G")) return false;
		return true;
	}
    /**
     * toString method
     * This method allows this Critter's symbol to be printed during "show"
     */	
	public String toString() {
		return "P";
	}
}
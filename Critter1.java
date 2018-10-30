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

	Critter1(){}
    /**
     * doTime method.
     * This method allows this Critter to do their timeStep
     */
	@Override
	public void doTimeStep() {
		run(Critter.getRandomInt(8));
		this.moveFlag = true;
	}
    /**
     * fight method.
     * This method allows this Critter to do encounter behavior
     */
	@Override
	public boolean fight(String opponent) {
		if(opponent.equals("G")) return false;
		return true;
	}
    /**
     * toString method
     * This method allows this Critter's symbol to be printed during "show"
     */
	@Override
	public String toString () {
		return "G";
	}
}

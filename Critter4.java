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
 * They reproduce randomly.
 * Critter4's are best friends with Critter3's and will not fight them.
 */
public class Critter4 extends Critter {

	Critter4(){}
    /**
     * doTime method.
     * This method allows this Critter to do their timeStep
     */
	@Override
	public void doTimeStep() {
		int activity = Critter.getRandomInt(3);
		if(activity == 0) {
			walk(Critter.getRandomInt(8));
			this.moveFlag = true;
		}
		else if(activity == 1) {				
			run(Critter.getRandomInt(8));
			this.moveFlag = true;
		}
		else if(this.getEnergy() >= Params.min_reproduce_energy) {
			Critter4 egg = new Critter4();
			this.reproduce(egg,Critter.getRandomInt(8));
		}
		else {
		}
	}
    /**
     * fight method.
     * This method allows this Critter to do encounter behavior
     */
	@Override
	public boolean fight(String opponent) {
		if (getEnergy() > (Params.start_energy/2) && (!opponent.equals("Critter3"))) {
			return true;
		}
		int check = this.getX() + 1 ;
		if(check > (Params.world_width-1)) {
			check = 0;
		}
		if((this.moveFlag == false) && (myWorld.world[this.getY()][check].isEmpty())){
			walk(0);
			this.moveFlag = true;
			return false;
		}
		else {
			this.setEnergy(this.getEnergy() - Params.walk_energy_cost);
			return false;
		}
	}
    /**
     * toString method
     * This method allows this Critter's symbol to be printed during "show"
     */
	@Override
	public String toString () {
		return "Y";
	}
}
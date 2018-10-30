package assignment4;
/*Squirtle.java 
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
/*Critter3’s are smart but lazy. 
 * They only fight when they have 1/3 or more of their initial energy, and when they walk or rest, 
 * they use the energy it takes a normal critter to run. 
 * They never run themselves, but they do reproduce randomly.
 */
public class Critter3 extends TestCritter {

    Critter3(){}

    @Override
    public void doTimeStep() {
		int activity = Critter.getRandomInt(3);
		if(activity == 0) {
			walk(Critter.getRandomInt(8));   // Squirtle is out of shape so he only uses run energy to walk
			this.moveFlag = true;
		}
		else if(activity == 1) {				
			run(Critter.getRandomInt(8));
			this.moveFlag = true;
		}
		else if(this.getEnergy() >= Params.min_reproduce_energy) {
			Critter3 egg = new Critter3();
			this.reproduce(egg,Critter.getRandomInt(8));
		}
		else {
			//this.setEnergy(this.getEnergy()-Params.rest_energy_cost);
		}
    }

    @Override
    public boolean fight(String opponent) {
		if (getEnergy() > (Params.start_energy/3)) {
			return true;
		}
		int check = this.getX() - 1 ;
		if(check < 0) {
			check = Params.world_width-1;
		}
		if((this.moveFlag == false) && (myWorld.world[this.getY()][check].isEmpty())){
			run(4);
			this.moveFlag = true;
			return false;
		}
		else {
			this.setEnergy(this.getEnergy() - Params.run_energy_cost - Params.rest_energy_cost);
			return false;
		}
    }

    @Override
    public String toString () {
        return "S";
    }

}

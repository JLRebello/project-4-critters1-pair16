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

public class Squirtle extends TestCritter {

    Squirtle(){
        this.setEnergy(Params.start_energy);
        this.setX(Critter.getRandomInt(Params.world_width - 1));
        this.setY(Critter.getRandomInt(Params.world_height - 1));
    }

    @Override
    public void doTimeStep() {
		int activity = Critter.getRandomInt(3);
		if(activity == 0) {
			walk(Critter.getRandomInt(8));   // Squirtle is out of shape so he only uses run energy to walk
			this.moveFlag = true;
			this.setEnergy(this.getEnergy()-Params.run_energy_cost);
		}
		else if(activity == 1) {				
			run(Critter.getRandomInt(8));
			this.moveFlag = true;
			this.setEnergy(this.getEnergy()-Params.walk_energy_cost);
		}
		else if(this.getEnergy() >= Params.min_reproduce_energy) {
			Squirtle egg = new Squirtle();
			this.reproduce(egg,Critter.getRandomInt(8));
			this.setEnergy(this.getEnergy()/2);
			egg.setEnergy(this.getEnergy()/2);
		}
		else {
			this.setEnergy(this.getEnergy()-Params.rest_energy_cost);
		}

		if (this.getEnergy() <= 0) {
			Critter.myWorld.world[getX()][getY()].remove(this);
			TestCritter.getPopulation().remove(this);
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
			walk(4);
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
        return "S";
    }

}

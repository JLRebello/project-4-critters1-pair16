package assignment4;
/* CRITTERS Critter.java
 * EE422C Project 4 submission by
 * Julia Rebello
 * JLR3755
 * Section 16365
 * Samir Riad
 * SR43888
 * 16360
 * Slip days used: <0>
 * Fall 2018
 */

import java.util.*;



/* see the PDF for descriptions of the methods and fields in this class
 * you may add fields, methods or inner classes to Critter ONLY if you make your additions private
 * no new public, protected or default-package code or data can be added to Critter
 */


public abstract class Critter {
	private static String myPackage;
	private	static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();
	// added by us
	public static CritterWorld myWorld = new CritterWorld();
	public static boolean testMoveFlag = false;
	public static int pastTestX, pastTestY;
	
	// Gets the package name.  This assumes that Critter and its subclasses are all in the same package.
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}

	private static java.util.Random rand = new java.util.Random();
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}
	
	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);
	}
	
	
	/* a one-character long string that visually depicts your critter in the ASCII interface */
	public String toString() { return ""; }
	
	private int energy = 0;
	protected int getEnergy() { return energy; }
	protected void setEnergy(int foo) {energy = foo;}
	
	private int x_coord;
	private int y_coord;
	public boolean moveFlag = false;
	public boolean runFlag = false;
	public static boolean fightFlag = false;
    /**
     * getX_coord method.
     * This method sets the X coordinate. 
     * @return new x coordinate.
     */	
	protected int getX() {return this.x_coord;}
    /**
     * getY_coord method.
     * This method sets the Y coordinate. 
     * @return new y coordinate.
     */	
	protected int getY() {return this.y_coord;}
    /**
     * setX_coord method.
     * This method sets the X coordinate. 
     * @param new_x_coord is the x coordinate of our simulated world.
     */
	protected void setX(int foo) {
		myWorld.world[this.getY()][this.getX()].remove(this);
		this.x_coord = foo;
		myWorld.world[this.getY()][this.getX()].add(this);
	}
    /**
     * setY_coord method.
     * This method sets the Y coordinate. 
     * @param new_y_coord is the y coordinate of our simulated world.
     */		
	protected void setY(int foo) {
		myWorld.world[this.getY()][this.getX()].remove(this);
		this.y_coord = foo;
		myWorld.world[this.getY()][this.getX()].add(this);
	}
	/**
	 * walk Method
	 * This method allows our Critters to move one position in any direction in our simulated world.
	 * @params direction is where the Critter will move, relative to where they already are.
	 */		
	protected final void walk(int direction) { 
		int currX = this.getX();
		int currY = this.getY();
		int planX = this.getX();
		int planY = this.getY();
		if(this instanceof Craig) {
			this.moveFlag = true;
		}
		if(direction == 0) {
			if(currX == Params.world_width - 1) {
				planX = 0;
			}
			else {
				planX = currX + 1;
			}
		}
		else if(direction == 1) {
			if(currX == Params.world_width - 1) {
				planX = 0;
			}
			else {
				planX = currX + 1;
			}
			if(currY == 0) {
				planY = Params.world_height - 1;
			}
			else {
				planY = currY - 1;
			}
		}
		else if(direction == 2) {
			if(currY == 0) {
				planY = Params.world_height - 1;
			}
			else {
				planY = currY - 1;
			}
		}
		else if(direction == 3) {
			if(currX == 0) {
				planX = Params.world_width - 1;
			}
			else {
				planX = currX - 1;
			}
			if(currY == 0) {
				planY = Params.world_height - 1;
			}
			else {
				planY = currY - 1;
			}
		}
		else if(direction == 4) {
			if(currX == 0) {
				planX = Params.world_width - 1;
			}
			else {
				planX = currX - 1;
			}
		}
		else if(direction == 5) {
			if(currX == 0) {
				planX = Params.world_width - 1;
			}
			else {
				planX = currX - 1;
			}
			if(currY == Params.world_height - 1) {
				planY = 0; 
			}
			else {
				planY = currY + 1; 
			}
		}
		else if(direction == 6) {
			if(currY == Params.world_height - 1) {
				planY = 0; 
			}
			else {
				planY = currY + 1; 
			}
		}
		else if(direction == 7) {
			if(currX == Params.world_width - 1) {
				planX = 0;
			}
			else {
				planX = currX + 1; 
			}
			if(currY == Params.world_height - 1) {
				planY = 0;
			}
			else {
				planY = currY + 1;
			}
		}
		if(runFlag != true) {
			this.setEnergy(this.getEnergy() - Params.walk_energy_cost);
		}
		if(fightFlag == true) {									//if during a fight
			if(myWorld.world[planY][planX].size() == 0) {		//only move if the desired space is empty
				this.setX(planX);
				this.setY(planY);
			}
		}
		else {
			this.setX(planX);
			this.setY(planY);
		}
	}
	
	/**
	 * run Method
	 * This method allows our Critters to move two positions in any direction in our simulated world.
	 * @params direction is where the Critter will move, relative to where they already are.
	 */	
	protected final void run(int direction) {
		runFlag = true;
		this.walk(direction);
		this.walk(direction);
		this.setEnergy(this.getEnergy() - Params.run_energy_cost);
		runFlag = false;
	}
	/**
	 * reproduce Method
	 * This method allows our Critters to generate offspring.
	 * @params offspring is a new Critter that becomes a baby
	 * @params direction is where the baby is placed in the simulated world, relative to the parent.
	 */
	protected final void reproduce(Critter offspring, int direction) {
		offspring.setX(this.getX());
		offspring.setY(this.getY());
		offspring.walk(direction);
		offspring.setEnergy(this.getEnergy()/2);
		this.setEnergy(this.getEnergy()/2);
		babies.add(offspring);
	}

	public abstract void doTimeStep();
	public abstract boolean fight(String opponent);
	
	/**
	 * create and initialize a Critter subclass.
	 * critter_class_name must be the unqualified name of a concrete subclass of Critter, if not,
	 * an InvalidCritterException must be thrown.
	 * (Java weirdness: Exception throwing does not work properly if the parameter has lower-case instead of
	 * upper. For example, if craig is supplied instead of Craig, an error is thrown instead of
	 * an Exception.)
	 * @param critter_class_name
	 * @throws InvalidCritterException
	 */
	public static void makeCritter(String critter_class_name) throws InvalidCritterException {
		try {
			Critter newCritter = (Critter) Class.forName(myPackage + "." + critter_class_name).newInstance();
			newCritter.setEnergy(Params.start_energy);
			newCritter.setX(Critter.getRandomInt(Params.world_width));
			newCritter.setY(Critter.getRandomInt(Params.world_height));
			population.add(newCritter);
		}
		catch(Exception e) {
			throw new InvalidCritterException("invalid critter");
		}
	}
	
	/**
	 * Gets a list of critters of a specific type.
	 * @param critter_class_name What kind of Critter is to be listed.  Unqualified class name.
	 * @return List of Critters.
	 * @throws InvalidCritterException
	 */
	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
		List<Critter> result = new java.util.ArrayList<Critter>();
		try {
			Critter myCrit = (Critter)Class.forName(myPackage + "." + critter_class_name).newInstance();
			for (Critter crit : population) {
				if(myCrit.getClass().isInstance(crit)) {
					result.add(crit);
				}
			}
			return result;
		}
		catch(Exception e) {
			throw new InvalidCritterException("invalid critter");
		}		
	}	
	
	
	/**
	 * Prints out how many Critters of each type there are on the board.
	 * @param critters List of Critters.
	 */
	public static void runStats(List<Critter> critters) {
		System.out.print("" + critters.size() + " critters as follows -- ");
		java.util.Map<String, Integer> critter_count = new java.util.HashMap<String, Integer>();
		for (Critter crit : critters) {
			String crit_string = crit.toString();
			Integer old_count = critter_count.get(crit_string);
			if (old_count == null) {
				critter_count.put(crit_string,  1);
			} else {
				critter_count.put(crit_string, old_count.intValue() + 1);
			}
		}
		String prefix = "";
		for (String s : critter_count.keySet()) {
			System.out.print(prefix + s + ":" + critter_count.get(s));
			prefix = ", ";
		}
		System.out.println();		
	}
	
	/* the TestCritter class allows some critters to "cheat". If you want to 
	 * create tests of your Critter model, you can create subclasses of this class
	 * and then use the setter functions contained here. 
	 * 
	 * NOTE: you must make sure that the setter functions work with your implementation
	 * of Critter. That means, if you're recording the positions of your critters
	 * using some sort of external grid or some other data structure in addition
	 * to the x_coord and y_coord functions, then you MUST update these setter functions
	 * so that they correctly update your grid/data structure.
	 */
	static abstract class TestCritter extends Critter {
		protected void setEnergy(int new_energy_value) {
			super.energy = new_energy_value;
		}
	    /**
	     * setX_coord method.
	     * This method sets the X coordinate. 
	     * @param new_x_coord is the x coordinate of our simulated world.
	     */
		protected void setX_coord(int new_x_coord) {
			myWorld.world[this.getY_coord()][this.getX_coord()].remove(this);
			super.x_coord = new_x_coord;
			myWorld.world[this.getY_coord()][this.getX_coord()].add(this);
		}
	    /**
	     * setY_coord method.
	     * This method sets the Y coordinate. 
	     * @param new_y_coord is the y coordinate of our simulated world.
	     */	
		protected void setY_coord(int new_y_coord) {
			myWorld.world[this.getY_coord()][this.getX_coord()].remove(this);
			super.y_coord = new_y_coord;
			myWorld.world[this.getY_coord()][this.getX_coord()].add(this);			
		}
	    /**
	     * getX_coord method.
	     * This method sets the X coordinate. 
	     * @return new x coordinate.
	     */			
		protected int getX_coord() {
			return super.x_coord;
		}
	    /**
	     * getY_coord method.
	     * This method sets the Y coordinate. 
	     * @return new y coordinate.
	     */		
		protected int getY_coord() {
			return super.y_coord;
		}
		

		/*
		 * This method getPopulation has to be modified by you if you are not using the population
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.
		 */
		protected static List<Critter> getPopulation() {
			return population;
		}
		
		/*
		 * This method getBabies has to be modified by you if you are not using the babies
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.  Babies should be added to the general population 
		 * at either the beginning OR the end of every timestep.
		 */
		protected static List<Critter> getBabies() {
			return babies;
		}
	}

    /**
     * clearWorld method.
     * This method clears the simulated world. 
     */
	public static void clearWorld() {
		population.clear();
		babies.clear();
		for(int i = 0; i < Params.world_height; i++) {
			for(int j = 0; j < Params.world_width; j++) {
				myWorld.world[i][j].clear();
			}
		}
	}
    /**
     * worldTimeStep method.
     * This method goes through each Critter's time step.
     * @throws invalidCritterException
     */	
	public static void worldTimeStep() {
		Iterator<Critter> itr = population.iterator();
		while(itr.hasNext()) {
			Critter current = (Critter)itr.next();
			current.doTimeStep();
			current.setEnergy(current.getEnergy() - Params.rest_energy_cost);
		}
		Iterator<Critter> itr2 = population.iterator();
		while(itr2.hasNext()) {
			Critter tempCrit = itr2.next();
			if(tempCrit.getEnergy() <= 0) {			//remove dead critters
				itr2.remove();						//removes from population
				myWorld.world[tempCrit.getY()][tempCrit.getX()].remove(tempCrit);
			}
		}
		for(int i = 0; i < Params.world_height; i++) {
			for(int j = 0; j < Params.world_width; j++) {
				ArrayList<Critter> adults = new ArrayList();
				for(int k = 0; k < myWorld.world[i][j].size(); k++) {
					if(!babies.contains(myWorld.world[i][j].get(k))) {
						adults.add((Critter)myWorld.world[i][j].get(k));
					}
				}
				while(adults.size() > 1){ 						//FIGHT SEQUENCE
					fightFlag = true;
					Critter c0 = (Critter) adults.get(0);
					Critter c1 = (Critter) adults.get(1);
					boolean a = c0.fight(c1.toString());
					boolean b = c1.fight(c0.toString());
					
					adults.clear();
					for(int k = 0; k < myWorld.world[i][j].size(); k++) {
						if(!babies.contains(myWorld.world[i][j].get(k))) {
							adults.add((Critter)myWorld.world[i][j].get(k));
						}
					}
					if (adults.size() <= 1) { break;}
					if((adults.get(0) != c0) || (adults.get(1) != c1)){
						continue;
					}
					else {
						if (!a && !b) {									//if no one wants to fight
							c0.setEnergy(c0.getEnergy() + (c1.getEnergy() / 2));
							c1.setEnergy(0);
							myWorld.world[i][j].remove(c1);
							population.remove(c1);
							adults.remove(c1);
						}
						else if((a == true) && (b == false)) {
							c0.setEnergy(c0.getEnergy() + (c1.getEnergy() / 2));
							c1.setEnergy(0);
							myWorld.world[i][j].remove(c1);
							population.remove(c1);
							adults.remove(c1);

						}
						else if((a == false) && (b == true)) {
							c1.setEnergy(c1.getEnergy() + (c0.getEnergy() / 2));
							c0.setEnergy(0);
							myWorld.world[i][j].remove(c0);
							population.remove(c0);
							adults.remove(c0);
						}
						else if((a == true) && (b == true)) {
							int aScore = Critter.getRandomInt(5);
							int bScore = Critter.getRandomInt(5);
							if (bScore > aScore) {
								c1.setEnergy(c1.getEnergy() + (c0.getEnergy() / 2));
								c0.setEnergy(0);
								myWorld.world[i][j].remove(c0);
								population.remove(c0);
								adults.remove(c0);
							} else {
								c0.setEnergy(c0.getEnergy() + (c1.getEnergy() / 2));
								c1.setEnergy(0);
								myWorld.world[i][j].remove(c1);
								population.remove(c1);
								adults.remove(c1);
							}
						}
					}
				}
			}
		}
		fightFlag = false;
		Iterator<Critter> itr3 = population.iterator();
		while(itr3.hasNext()) {
			Critter tempCrit = itr3.next();
			if(tempCrit.getEnergy() <= 0) {			//remove dead critters
				itr3.remove();
				myWorld.world[tempCrit.getY()][tempCrit.getX()].remove(tempCrit);
			}
		}
		int babyPop = babies.size();
		for(int i = 0; i < babyPop; i++) {		//add babies into general population
			population.add(babies.get(i));
		}
		babies.clear();
		for(Critter crit : population) {
			crit.moveFlag = false;
		}
		for(int i = 0; i < Params.refresh_algae_count; i++) {
			try {
				makeCritter("Algae");
			} catch (InvalidCritterException e) {
				System.out.println("Invalid Critter");
			}
		}
	}
    /**
     * displayWorld method.
     * This method prints out our simulated world with the Critter's symbols.
     */		
	public static void displayWorld() {
		System.out.print("+");
		for(int i = 0; i < Params.world_width; i++) {
			System.out.print("-");
		}
		System.out.print("+");
		for(int i = 0; i < Params.world_height; i++) {
			System.out.print('\n' + "|");
			for(int j = 0; j < Params.world_width; j++) {
				if (!myWorld.world[i][j].isEmpty())
					System.out.print(myWorld.world[i][j].get(0).toString());
				else System.out.print(" ");
			}
			System.out.print("|");
		}
		System.out.print("\n" + "+");
		for(int i = 0; i < Params.world_width; i++) {
			System.out.print("-");
		}
		System.out.println("+");
	}
}
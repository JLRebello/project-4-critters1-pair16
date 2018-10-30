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

	protected int getX() {return this.x_coord;}
	protected int getY() {return this.y_coord;}
	protected void setX(int foo) {
		myWorld.world[this.getX()][this.getY()].remove(this);
		this.x_coord = foo;
		myWorld.world[this.getX()][this.getY()].add(this);
	}
		
	protected void setY(int foo) {
		myWorld.world[this.getX()][this.getY()].remove(this);
		this.y_coord = foo;
		myWorld.world[this.getX()][this.getY()].add(this);
	}
	
	protected final void walk(int direction) {  
		myWorld.world[this.getY()][this.getX()].remove(this);
		if(this instanceof Craig) {
			this.moveFlag = true;
		}
		if(direction == 0) {
			if(this.getX() == Params.world_width - 1) {
				this.setX(0);
			}
			else {
				this.setX(this.getX() + 1);
			}
		}
		else if(direction == 1) {
			if(this.getX() == Params.world_width - 1) {
				this.setX(0);
			}
			else {
				this.setX(this.getX()+1);
			}
			if(this.getY() == 0) {
				this.setY(Params.world_height - 1);
			}
			else {
				this.setY(this.getY() - 1);
			}
		}
		else if(direction == 2) {
			if(this.getY() == 0) {
				this.setY(Params.world_height - 1);
			}
			else {
				this.setY(this.getY() - 1);
			}
		}
		else if(direction == 3) {
			if(this.getX() == 0) {
				this.setX(Params.world_width - 1);
			}
			else {
				this.setX(this.getX()-1);
			}
			if(this.getY() == 0) {
				this.setY(Params.world_height - 1);
			}
			else {
				this.setY(this.getY() - 1);
			}
		}
		else if(direction == 4) {
			if(this.getX() == 0) {
				this.setX(Params.world_width - 1);
			}
			else {
				this.setX(this.getX() - 1);
			}
		}
		else if(direction == 5) {
			if(this.getX() == 0) {
				this.setX(Params.world_width - 1);
			}
			else {
				this.setX(this.getX()-1);
			}
			if(this.getY() == Params.world_height - 1) {
				this.setY(0);
			}
			else {
				this.setY(this.getY() + 1);
			}
		}
		else if(direction == 6) {
			if(this.getY() == Params.world_height - 1) {
				this.setY(0);
			}
			else {
				this.setY(this.getY() + 1);
			}
		}
		else if(direction == 7) {
			if(this.getX() == Params.world_width - 1) {
				this.setX(0);
			}
			else {
				this.setX(this.getX()+1);
			}
			if(this.getY() == Params.world_height - 1) {
				this.setY(0);
			}
			else {
				this.setY(this.getY() + 1);
			}
		}
		myWorld.world[this.getY()][this.getX()].add(this);
		if(runFlag != true) {
			this.setEnergy(this.getEnergy() - Params.walk_energy_cost);
		}
	}
	
	protected final void run(int direction) {
		runFlag = true;
		this.walk(direction);
		this.walk(direction);
		this.setEnergy(this.getEnergy() - Params.run_energy_cost);
		runFlag = false;
	}

	protected final void reproduce(Critter offspring, int direction) {
		offspring.setX(this.getX());
		offspring.setY(this.getY());
		offspring.walk(direction);
		offspring.setEnergy(this.getEnergy()/2);
		this.setEnergy(this.getEnergy()/2);
		myWorld.world[offspring.getY()][offspring.getX()].add(offspring);
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
			myWorld.world[newCritter.getY()][newCritter.getX()].add(newCritter);
		}
		catch(Exception e) {
			throw new InvalidCritterException("invalid critter");
		}
//		if(critter_class_name.equals("Craig")) {
//			Craig newCraig = new Craig();
//			newCraig.setX(Critter.getRandomInt(Params.world_width));
//			newCraig.setY(Critter.getRandomInt(Params.world_height));
//			newCraig.setEnergy(Params.start_energy);
//			population.add(newCraig);
//			myWorld.world[newCraig.getY()][newCraig.getX()].add(newCraig);
//		}
//		else if(critter_class_name.equals("Critter1")){
//			Critter1 newGodEmp = new Critter1();
//			population.add(newGodEmp);
//			myWorld.world[newGodEmp.getY()][newGodEmp.getX()].add(newGodEmp);
//		}
//		else if(critter_class_name.equals("Algae")){
//			Algae newAlgae = new Algae();
//			newAlgae.setX_coord(Critter.getRandomInt(Params.world_width));
//			newAlgae.setY_coord(Critter.getRandomInt(Params.world_height));
//			newAlgae.setEnergy(Params.start_energy);
//			population.add(newAlgae);
//			myWorld.world[newAlgae.getY()][newAlgae.getX()].add(newAlgae);
//		}
//		else if(critter_class_name.equals("Critter4")){
//			Critter4 egg = new Critter4();
//			population.add(egg);
//			myWorld.world[egg.getY()][egg.getX()].add(egg);
//		}
//		else if(critter_class_name.equals("Critter3")){
//			Critter3 Squirt = new Critter3();
//			population.add(Squirt);
//			myWorld.world[Squirt.getY()][Squirt.getX()].add(Squirt);
//		}
//		else if(critter_class_name.equals("Critter2")){
//			Critter2 newLord = new Critter2();
//			population.add(newLord);
//			myWorld.world[newLord.getY()][newLord.getX()].add(newLord);
//		}
//		else {
//			throw new InvalidCritterException("invalid critter");
//		}
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
		
			/*if(critter_class_name.equals("Craig")) {
				for (Critter crit : population) {
					if(crit instanceof Craig)
						result.add(crit);
				}
				return result;
			}
			else if(critter_class_name.equals("Critter1")){
				for (Critter crit : population) {
					if(crit instanceof Critter1)
						result.add(crit);
				}
				return result;
			}
			else if(critter_class_name.equals("Algae")){
				for (Critter crit : population) {
					if(crit instanceof Algae)
						result.add(crit);
				}
				return result;
			}
			else if(critter_class_name.equals("Critter4")){
				for (Critter crit : population) {
					if(crit instanceof Critter4)
						result.add(crit);
				}
				return result;
			}
			else if(critter_class_name.equals("Critter3")){
				for (Critter crit : population) {
					if(crit instanceof Critter3)
						result.add(crit);
				}
				return result;
			}
			else if(critter_class_name.equals("Critter2")){
				for (Critter crit : population) {
					if(crit instanceof Critter2)
						result.add(crit);
				}
				return result;
			} 
			else {
				throw new InvalidCritterException("invalid critter");
			}*/
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
		
		protected void setX_coord(int new_x_coord) {
			/*pastTestX = this.getX_coord();
			if(testMoveFlag) {
				super.x_coord = new_x_coord;
				myWorld.world[pastTestY][pastTestX].remove(this);
				myWorld.world[super.y_coord][super.x_coord].add(this);
				testMoveFlag = false;
			}
			else {
				super.x_coord = new_x_coord;
				testMoveFlag = true;
			}*/
			myWorld.world[this.getX_coord()][this.getY_coord()].remove(this);
			super.x_coord = new_x_coord;
			myWorld.world[this.getX_coord()][this.getY_coord()].add(this);
		}
		
		protected void setY_coord(int new_y_coord) {
			/*pastTestY = this.getY_coord();
			if(testMoveFlag) {
				super.y_coord = new_y_coord;
				myWorld.world[pastTestY][pastTestX].remove(this);
				myWorld.world[super.y_coord][super.x_coord].add(this);
				testMoveFlag = false;
			}
			else {
				super.y_coord = new_y_coord;
				testMoveFlag = true;
			}*/
			myWorld.world[this.getX_coord()][this.getY_coord()].remove(this);
			super.y_coord = new_y_coord;
			myWorld.world[this.getX_coord()][this.getY_coord()].add(this);			
		}
		
		protected int getX_coord() {
			return super.x_coord;
		}
		
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
	 * Clear the world of all critters, dead and alive
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
				itr2.remove();
				myWorld.world[tempCrit.getY()][tempCrit.getX()].remove(tempCrit);
			}
		}
		for(int i = 0; i < Params.world_height; i++) {
			for(int j = 0; j < Params.world_width; j++) {
				while(myWorld.world[i][j].size() > 1){
					Critter c1 = (Critter) myWorld.world[i][j].get(0);
					Critter c2 = (Critter) myWorld.world[i][j].get(1);
					boolean a = c1.fight(c2.toString());
					boolean b = c2.fight(c1.toString());
					if (myWorld.world[i][j].size() <= 1) break;
					if (myWorld.world[i][j].contains(c1) && myWorld.world[i][j].contains(c2)) {
						if (!a && !b) {									//if no one wants to fight
								c1.setEnergy(c1.getEnergy() + (c2.getEnergy() / 2));
								c2.setEnergy(0);
								myWorld.world[i][j].remove(c2);
								population.remove(c2);
						}
						else if((a == true) && (b == false)) {
								c1.setEnergy(c1.getEnergy() + (c2.getEnergy() / 2));
								c2.setEnergy(0);
								myWorld.world[i][j].remove(c2);
								population.remove(c2);
						}
						else if((a == false) && (b == true)) {
								c2.setEnergy(c2.getEnergy() + (c1.getEnergy() / 2));
								c1.setEnergy(0);
								myWorld.world[i][j].remove(c1);
								population.remove(c1);
						}
						else if((a == true) && (b == true)) {
							int aScore = Critter.getRandomInt(5);
							int bScore = Critter.getRandomInt(5);
							if (bScore > aScore) {
								c2.setEnergy(c2.getEnergy() + (c1.getEnergy() / 2));
								c1.setEnergy(0);
								myWorld.world[i][j].remove(c1);
								population.remove(c1);
							} else {
								c1.setEnergy(c1.getEnergy() + (c2.getEnergy() / 2));
								c2.setEnergy(0);
								myWorld.world[i][j].remove(c2);
								population.remove(c2);
							}
						}
					}
				}
			}
		}
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
	
	public static void displayWorld() {
		// Complete this method.
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
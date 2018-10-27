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
//import java.util.Iterator;
//import java.util.List;

/* see the PDF for descriptions of the methods and fields in this class
 * you may add fields, methods or inner classes to Critter ONLY if you make your additions private
 * no new public, protected or default-package code or data can be added to Critter
 */


public abstract class Critter {
	private static String myPackage;
	private	static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();

	// added by us
	private static CritterWorld myWorld = new CritterWorld();

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
	
	protected int getX() {return x_coord;}
	protected int getY() {return y_coord;}
	protected void setX(int foo) {x_coord = foo;}
	protected void setY(int foo) {y_coord = foo;}
	
	protected final void walk(int direction) {
		myWorld.world[this.getY()][this.getX()] = null;
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
		myWorld.world[this.getY()][this.getX()] = this;
	}
	
	protected final void run(int direction) {
		this.walk(direction);
		this.walk(direction);
	}
	
	protected final void reproduce(Critter offspring, int direction) {}

	public abstract void doTimeStep();
	public abstract boolean fight(String oponent);
	
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
	}
	
	/**
	 * Gets a list of critters of a specific type.
	 * @param critter_class_name What kind of Critter is to be listed.  Unqualified class name.
	 * @return List of Critters.
	 * @throws InvalidCritterException
	 */
	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
		List<Critter> result = new java.util.ArrayList<Critter>();
	
		return result;
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
			super.x_coord = new_x_coord;
		}
		
		protected void setY_coord(int new_y_coord) {
			super.y_coord = new_y_coord;
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
		// TODO: Complete this method.
		population.clear();
		babies.clear();
	}
	
	public static void worldTimeStep() {
		// TODO: Complete this method.
		Iterator itr = population.iterator();
		while(itr.hasNext()) {
			Critter current = (Critter)itr.next();
			current.doTimeStep();
		}
		//TODO: REMOVE DEAD CRITTERS?
		//TODO: ENCOUNTERS
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
				if (myWorld.world[i][j] != null)
					System.out.print(CritterWorld.getSymbol(myWorld.world[i][j]));
				else System.out.print(" ");
			}
			System.out.print("|");
		}
	}
}
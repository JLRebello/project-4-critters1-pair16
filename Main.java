package assignment4;
/* CRITTERS Main.java
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

import java.util.Scanner;
import java.io.*;


/*
 * Usage: java <pkgname>.Main <input file> test
 * input file is optional.  If input file is specified, the word 'test' is optional.
 * May not use 'test' argument without specifying input file.
 */
public class Main {

    static Scanner kb;	// scanner connected to keyboard input, or input file
    private static String inputFile;	// input file, used instead of keyboard input if specified
    static ByteArrayOutputStream testOutputString;	// if test specified, holds all console output
    private static String myPackage;	// package of Critter file.  Critter cannot be in default pkg.
    private static boolean DEBUG = false; // Use it or not, as you wish!
    static PrintStream old = System.out;	// if you want to restore output to console


    // Gets the package name.  The usage assumes that Critter and its subclasses are all in the same package.
    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }

    /**
     * Main method.
     * @param args args can be empty.  If not empty, provide two parameters -- the first is a file name, 
     * and the second is test (for test output, where all output to be directed to a String), or nothing.
     */
    public static void main(String[] args) { 
        if (args.length != 0) {
            try {
                inputFile = args[0];
                kb = new Scanner(new File(inputFile));			
            } catch (FileNotFoundException e) {
                System.out.println("USAGE: java Main OR java Main <input file> <test output>");
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("USAGE: java Main OR java Main <input file>  <test output>");
            }
            if (args.length >= 2) {
                if (args[1].equals("test")) { // if the word "test" is the second argument to java
                    // Create a stream to hold the output
                    testOutputString = new ByteArrayOutputStream();
                    PrintStream ps = new PrintStream(testOutputString);
                    // Save the old System.out.
                    old = System.out;
                    // Tell Java to use the special stream; all console output will be redirected here from now
                    System.setOut(ps);
                }
            }
        } else { // if no arguments to main
            kb = new Scanner(System.in); // use keyboard and console
        }
        String word = "";
    	String[] words = {"null", "null"};

        while((!word.equals("quit")) ) { 
            word = kb.nextLine();
        	words = word.split(" ");
        	if(words.length > 3 ) {
        		System.out.println("invalid command: " + word);
        	}
        	if(words[0].equals("show")) {
            	if(words.length != 1 ) {
            		System.out.println("invalid command: " + word);
            	}
            	else {
            		Critter.displayWorld();
            	}
        	}
        	else if(words[0].equals("step")) {
            	if(words.length > 2 ){
            		System.out.println("invalid command: " + word);
            	}
            	else if(words.length != 1) {
        			try{
        				int num = Integer.parseInt(words[1]);
        				for(int i = 0; i < num; i++) {
        					Critter.worldTimeStep();
        				}
        			}
        			catch(NumberFormatException e) {
                		System.out.println("invalid command: " + word);
        			}
        		}
        		else {
            		Critter.worldTimeStep();
        		}
        	}
        	else if(words[0].equals("seed")) {
            	if(words.length != 2) {
            		System.out.println("invalid command: " + word);
            	}
            	else {
            		try{ 
            		int num = Integer.parseInt(words[1]);
            		Critter.setSeed(num);
            		}
            		catch(NumberFormatException e) {
            			System.out.println("invalid command: " + word);
            		}
            	}
        	}
        	else if(words[0].equals("make")) {
            	if(words.length != 3 ) {
            		System.out.println("invalid command: " + word);
            	}
            	else if(!words[2].equals(null)) {
            		try {
            			int num = Integer.parseInt(words[2]);
        					for(int i = 0; i < num; i++) {
        						try {
        							Critter.makeCritter(words[1]);
        						} catch (InvalidCritterException e) {
        							System.out.println("Invalid Critter");
        							break;
        						}
        					}
            		}
            		catch(NumberFormatException e) {
                		System.out.println("invalid command: " + word);
            		}
        		}
        		else {
            		try {
						Critter.makeCritter(words[1]);
					} catch (InvalidCritterException e) {
						System.out.println("Invalid Critter");
					}
        		}
        	}
        	else if(words[0].equals("stats")) {
            	if(words.length != 2) {
            		System.out.println("invalid command: " + word);
            	}
            	else try {
					Critter.runStats(Critter.getInstances(words[1]));
				} catch (InvalidCritterException e) {
					System.out.println("Invalid Critter");
					break;
				}
        	}
        	else if((words[0].equals("quit")) && (words.length != 1)) {
        		System.out.println("invalid command: " + word);
        	}
        	else if(!word.equals("quit")){
        		System.out.println("invalid command: " + word);
        	}
        }
        /* Do not alter the code above for your submission. */
        /* Write your code below. */
        
        // System.out.println("GLHF");
        
        /* Write your code above */
        System.out.flush();
    }
}
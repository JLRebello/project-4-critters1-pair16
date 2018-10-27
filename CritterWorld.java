package assignment4;

import java.util.ArrayList;

public class CritterWorld {
    public static ArrayList[][] world;

    public CritterWorld() {
        world = new ArrayList[Params.world_height][Params.world_width];
        for(int i = 0; i < Params.world_height; i++) {
            for(int j = 0; j < Params.world_width; j++) {
                world[i][j] = new ArrayList<Critter>();
            }
        }
    }

    public static char getSymbol(Critter current) {
        if (current instanceof GodEmperor) {
            return 'G';
        }
        if (current instanceof PowerLord) {
            return 'P';
        }
        if (current instanceof Yoshi) {
            return 'Y';
        }
        if (current instanceof Squirtle) {
            return 'S';
        }
        if (current instanceof Craig) {
            return 'C';
        }
        else {
            return '@';
        }
    }

    // write methods for getting char symbols for whatever critter is accessed
}

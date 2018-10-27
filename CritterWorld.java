package assignment4;
import java.util.*;

public class CritterWorld {
	public Critter[][] world;

    public CritterWorld() {
        world = new Critter[Params.world_height][Params.world_width];
        for(int i = 0; i < Params.world_height; i++) {
            for(int j = 0; j < Params.world_width; j++) {
                world[i][j] = null;
            }
        }
    }

    public static char getSymbol(Critter current) {
        return 'a';
    }

    // write methods for getting char symbols for whatever critter is accessed
}

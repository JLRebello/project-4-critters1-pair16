package assignment4;
/*CritterWorld.java 
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


    // write methods for getting char symbols for whatever critter is accessed
}

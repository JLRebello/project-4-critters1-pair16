package assignment4;

public class CritterWorld {
    public static Critter[][] world;

    public CritterWorld() {
        Critter[][] world = new Critter[Params.world_height][Params.world_width];
        for(int i = 0; i < Params.world_height; i++) {
            for(int j = 0; j < Params.world_width; j++) {
                world[i][j] = new Craig();
            }
        }
    }

    public static char getSymbol(Critter current) {
        return 'a';
    }

    // write methods for getting char symbols for whatever critter is accessed
}

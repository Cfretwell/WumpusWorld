public class Adventurer {
    // Adventurer explores Wumpus world map until they find the gold and get out or die
    int arrows;
    int x; // location in map
    int y;
    int steps; // keep track of steps taken for score
    boolean gold; // track if have gold or not

    public Adventurer(WumpusWorld map){     // takes in map to explore
        arrows = 1;  // start with one arrow to shoot wumpus
        x=0; // start at 0,0
        y=0;
        steps=0;
        gold = false;

        boolean live = explore(map);

    }

    private boolean explore(WumpusWorld map){
        //visited[0], pit[1], breeze[2], Gold[3], glitter[4], wumpus[5], stench[6]

        if(x==0 && y==0 && gold== true){// at exit with gold, Win condition!
            System.out.println("You Won! and got "+ (1000-steps)+" points!");
            return true;
        }

        boolean[] location = map.locationCheck(x,y);



    }



}

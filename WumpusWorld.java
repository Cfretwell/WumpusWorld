public class WumpusWorld {
    boolean [][][] map;  // x, y, c(visited[0], breeze[1], stench[2], glitter[3], pit[4], gold[5], wumpus[6])


    public WumpusWorld(int dim){

        map = new boolean[dim][dim][7];
        genMap();

        printMap();
    }

    private void genMap(){ // generates pits, gold, wumpus, as well as breeze, stench, and glitter

        int G = (int) (Math.random()*((map.length^2)-1)); // generate start spot for gold
        int W = (int) (Math.random()*((map.length^2)-1)); // generate start spot for wumpus

        int count =0;
        for(int x=0;x<map.length;x++){
            for(int y=0;y<map.length;y++){
                    count++;
                    if(count == G){ // Gold State, no pit
                        map[x][y][3]= true; // Glitter
                        map[x][y][5]= true; // Gold
                    }
                    if(count == W){ // Wumpus
                        map[x][y][6]= true;
                        setAround(x,y,2); // stench
                    }
                    int p = (int) (Math.random() * 5);
                    if(p == 0 && count != G){ // 20% chance pit and no pit on gold
                        map[x][y][4]= true; // pit
                        setAround(x,y,1); // breeze
                    }

                }
        }


    }

    private void setAround(int x, int y, int sense){ // takes coordinates and sets sense in adjacent cells
        if(x-1>=0){
            map[x-1][y][sense]= true;
        }
        if(x+1<= map.length){
            map[x+1][y][sense]= true;
        }
        if(y-1>=0){
            map[x][y-1][sense]= true;
        }
        if(y+1<=map.length){
            map[x][y+1][sense]= true;
        }
    }

    public void printMap(){ // prints out the maze or something

        for(int i=0; i<6;i++){
            System.out.println(i);
            for(int x=0; x<map.length;x++){
                for(int y=0;y<map.length;y++){
                    int p =0;
                    if(map[x][y][i]==true){
                        p=1;
                    }
                    System.out.print(" "+ p+" ");
                }
                 System.out.println();
            }
            System.out.println();
        }
        System.out.println();
    }
}

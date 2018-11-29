public class WumpusWorld {
    boolean [][][] map;  // x, y, c(visited[0], pit[1], breeze[2], Gold[3], glitter[4], wumpus[5], stench[6])


    public WumpusWorld(int dim){

        map = new boolean[dim][dim][7];
        System.out.println("Map dim: "+ dim);
        genMap();

        //printMap();
    }

    private void genMap(){ // generates pits, gold, wumpus, as well as breeze, stench, and glitter

        int G = (int) (1+(Math.random()*((map.length*map.length)-1))); // generate start spot for gold
        int W = (int) (1+(Math.random()*((map.length*map.length)-1))); // generate start spot for wumpus

        System.out.println("Gold at "+ G+ " Wumpus at "+W);
        int count =0;
        for(int x=0;x<map.length;x++){
            for(int y=0;y<map.length;y++){
                count++;
                    if(count == G){ // Gold State, no pit
                        map[x][y][4]= true; // Glitter
                        map[x][y][3]= true; // Gold
                    }
                    if(count == W){ // Wumpus
                        map[x][y][5]= true;
                        setAround(x,y,6); // stench
                    }
                    int p = (int) (Math.random() * 5);
                    if(p == 0 && count != G){ // 20% chance pit and no pit on gold
                        map[x][y][1]= true; // pit
                        setAround(x,y,2); // breeze
                    }

                }
        }


    }

    private void setAround(int x, int y, int sense){ // takes coordinates and sets sense in adjacent cells

        if(sense == 2){
            System.out.println("pit at "+x+", "+ y);
        }
        else if( sense == 6){
            System.out.println("Wumpus at "+x+", "+ y);
        }
        else{
            System.out.println("Something not good to go setARound");

        }
        if(x-1>=0){
            map[x-1][y][sense]= true;
        }
        if(x+1<= (map.length-1)){
            map[x+1][y][sense]= true;
        }
        if(y-1>=0){
            map[x][y-1][sense]= true;
        }
        if((y+1)<=(map.length-1)){
            map[x][y+1][sense]= true;
        }
    }

    public void printMap(){ // prints out the maze in boolean layers

        for(int i=0; i<7;i++){
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


    // take in coordinates and send back list of things in the thing
    public boolean[] locationCheck(int x, int y){
        boolean [] location = new boolean[7];

        for(int i=0;i<7;i++){
            location[i]= map[x][y][i];
        }
        return location;
    }

    // shoot the arrow in a direction and see if kill wampus
    public boolean shootArrow(int x, int y, int dir){ // dir: 0 up -y, 1 right +x, down 2, left 3
        boolean wumpusDead =false;

        // check if wumpus in direction of arrow shot, if so scream ...
        if(dir == 0){                   // up
            while(y>=0){
                if(map[x][y][5]==true){
                    wumpusDead= true;
                    killWumpus(x,y);
                }
                y--;
            }
        }
        if(dir == 1){                   // Right
            while(x<=map.length){
                if(map[x][y][5]==true){
                    wumpusDead= true;
                    killWumpus(x,y);
                }
                x++;
            }
        }
        if(dir == 2){                   //Down
            while(y<=map.length){
                if(map[x][y][5]==true){
                    wumpusDead= true;
                    killWumpus(x,y);
                }
                y++;
            }
        }
        if(dir == 3){
            while(x>=0){       //Left
                if(map[x][y][5]==true){
                    wumpusDead= true;
                    killWumpus(x,y);
                }
                y--;
            }
        }


        return wumpusDead;
    }



    // erase wumpus and stench
    private void killWumpus(int x, int y){
        map[x][y][5]=false;

        if(x+1<map.length){
            map[x+1][y][6]=false;
        }
        if(x-1>=0){
            map[x-1][y][6]=false;
        }
        if(y+1<map.length){
            map[x][y+1][6]=false;
        }
        if(y-1>=0){
            map[x][y-1][6]=false;
        }

    }

}

package robot;

import kareltherobot.*;

public class Roomba implements Directions {

    // Main method to make this self-contained
    public static void main(String[] args) {
        // LEAVE THIS ALONE!!!!!!
        String worldName = "robot/basicRoom.wld";

        Roomba cleaner = new Roomba();
        int totalBeepers = cleaner.cleanRoom(worldName, 7, 6);
        System.out.println("Roomba cleaned up a total of " + totalBeepers + " beepers.");

    }

    
    private Robot roomba;

    
    int largest_pile = 0;
    int totalBeepers = 0;

    public int cleanRoom(String worldName, int startX, int startY) {

        
        roomba = new Robot(7, 13, West, 0);

        World.readWorld(worldName);
        World.setVisible(true);
		World.setDelay(1);






        
        boolean done = true;
        //while (!done) means "keep executing the code inside this loop as long as the boolean variable done is false
        //This is the logical NOT operator. It inverts a boolean value. So, if done is true, !done evaluates to false. If done is false, !done evaluates to true.
        while (done) {
            clearAndMove();

        
            if (!roomba.frontIsClear()) {
                if (roomba.facingWest()) {
                    turnRight(); 
                    if (roomba.frontIsClear()) {
                        roomba.move();
                        turnRight(); 
                    } else {

                        done = true;
                    }
                } 
                else if (roomba.facingEast()) {
                    roomba.turnLeft(); 
                    if (roomba.frontIsClear()) {
                        roomba.move();
                        roomba.turnLeft(); 
                    } else {
                        done = true;
                    }
                }
            }
        }
        
        //roomba.turnOff();
        return totalBeepers;
    }

   public void clearAndMove() {
        int beepers = 0;
        while (roomba.nextToABeeper()) {
            roomba.pickBeeper();
            totalBeepers++;
            beepers++;
        }
        if (beepers > largest_pile) {
            largest_pile = beepers;
        }

        while (roomba.frontIsClear()) {
            roomba.move();
            beepers = 0;
            while (roomba.nextToABeeper()) {
                roomba.pickBeeper();
                totalBeepers++;
                beepers++;
            }
            if (beepers > largest_pile) {
                largest_pile = beepers;
            }
        }
    }

  public void turnRight() {
   int i = 0; 
   while (i < 3) { 
      roomba.turnLeft(); 
      i++; 
   }
}
}
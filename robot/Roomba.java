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

    // Declared here so it is visible in all the methods!
    private Robot roomba;

    // You will need to add many variables!!
    private int largest_pile = 0;
    private int totalBeepers = 0;

    public int cleanRoom(String worldName, int startX, int startY) {

        
        roomba = new Robot(7, 13, West, 0);

        World.readWorld(worldName);
        World.setVisible(true);
		World.setDelay(1);
        // This section will have all the logic that takes the Robot to every location
        // and cleans up all piles of beepers.
        boolean done = false;
        while (!done) {
            clearAndMove();

        
            if (!roomba.frontIsClear()) {
                if (roomba.facingWest()) {
                    turnRight(); // Face North
                    if (roomba.frontIsClear()) {
                        roomba.move();
                        turnRight(); // Face East
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
        
        roomba.turnOff();
        return totalBeepers;
    }

    private void clearAndMove() {
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

  public void turnRight(){
		int i=0;
		while(i< 3)
		{
			roomba.turnLeft();
			i = i+1;
		}
    }
}
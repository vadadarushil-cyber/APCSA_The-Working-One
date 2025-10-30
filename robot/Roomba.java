package robot;

import kareltherobot.*;

public class Roomba implements Directions {
    public static void main(String[] args) {
        String worldName = "robot/finalTestWorld2024.wld";

        Roomba cleaner = new Roomba();
        cleaner.cleanRoom(worldName);
    }

    private Robot roomba;
//set to doubles to get decimals 
    double total_piles = 0;
    double largest_pile = 0;
    double largest_pile_x = 0;
    double largest_pile_y = 0;
    double totalBeepers = 0;
    double totalArea = 2;

    public void cleanRoom(String worldName) {
        roomba = new Robot(26, 149, West, 0);

        World.readWorld(worldName);
        World.setVisible(true);
        World.setDelay(0);
        //make the world faster

        boolean done = false;
        clearCurrentPile();//use of my method

        while (!done) {//not done
            if (roomba.frontIsClear()) {
                roomba.move();
                totalArea++;
                clearCurrentPile();
            }

            else {
                if (roomba.facingWest()) {
                    turnRight();
                    if (roomba.frontIsClear()) {
                        roomba.move();
                        totalArea++;
                        turnRight();
                        clearCurrentPile();
                    } else {
                        done = true;
                    }
                } else if (roomba.facingEast()) {
                    roomba.turnLeft();
                    if (roomba.frontIsClear()) {
                        roomba.move();
                        totalArea++;
                        roomba.turnLeft();
                        clearCurrentPile();
                    } else {
                        done = true;
                    }
                }
            }
        }

        roomba.turnOff();
        //we have to turn off the robot so it doesn't crash

        //we have to find the avg's 
        double average_pile_size = 0;
        if (total_piles > 0) {
            average_pile_size = totalBeepers / total_piles;
        }

        double percent_dirty = 0;
        if (totalArea > 0) {
            percent_dirty = ( total_piles / totalArea) * 100;
        }
        //finally we print it out
        System.out.println("Area of the room: " + totalArea + " spaces");
        System.out.println("Number of piles: " + total_piles);
        System.out.println("Total number of beepers collected: " + totalBeepers);
        System.out.println("Largest pile of beepers: " + largest_pile);
        Systwem.out.println("Location of the largest pile: (" + largest_pile_x + ", " + largest_pile_y + ")");
        System.out.println("Average pile size: " + average_pile_size + " beepers");
        System.out.println("Percent dirty (piles/area): " + percent_dirty + "%");
    }

    public void clearCurrentPile() {
        double pileBeepers = 0;
        if (roomba.nextToABeeper()) {
            total_piles++;

            while (roomba.nextToABeeper()) {
                roomba.pickBeeper();
                pileBeepers++;
            }

            if (pileBeepers > largest_pile) {
                largest_pile = pileBeepers;
                largest_pile_x = roomba.street();
                largest_pile_y = roomba.avenue();
            }

            totalBeepers += pileBeepers;
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


package tower;

public class TowerModel {

    private IntegerStack[] towers;
    private int towerHeight = 0;
    private int printCounter = 0;
    private int moveCounter = 0;

    public TowerModel(int height)
    {
        towerHeight = height;
        towers = new IntegerStack[3];
        towers[0] = new IntegerStack(height);
        towers[1] = new IntegerStack(height);
        towers[2] = new IntegerStack(height);

        for (int i=0; i<height; i++)
        {
            towers[0].push(height - i);
        }
    }

    public int height()
    {
        return towerHeight;
    }

    // Move one disk from the source stack to the destination stack.
    public void move(int source, int destination)
    {
        IntegerStack from = towers[source];
        IntegerStack to = towers[destination];

        int movingDisk = from.peek();
        if (movingDisk == 0) return; // nothing to move

        int topDest = to.peek();
        if (topDest != 0 && movingDisk > topDest) return; // illegal move

        from.pop();
        to.push(movingDisk);

        System.out.println("Move #" + ++moveCounter + " from " + source + " to " + destination);
    }

    public void print()
    {
        System.out.print("Print #" + ++printCounter + " Towers of Hanoi\n");

        for (int layer = towerHeight-1; layer >= 0; layer--){
            System.out.print("\n");
            for (int tower = 0; tower < towers.length; tower++) {
                int value = towers[tower].get(layer);
                if (value == 0)
                {
                    System.out.print(" |");
                } else
                {
                    System.out.print(" " + value);
                }
            }
        }
        System.out.println();
        for (int i = 0; i < towers.length; i++) {
            System.out.print(" =");
        }
        System.out.println();
    }

    public IntegerStack[] getTowers()
    {
        return towers;
    }

    public int getHeight()
    {
        return towerHeight;
    }
}
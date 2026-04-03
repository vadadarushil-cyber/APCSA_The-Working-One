package tower;

public class TowerSolver {
    private TowerModel model;

    public TowerSolver()
    {
        // Nothing to do here
    }

    public void solve(TowerModel model)
    {
        this.model = model;
        solve(model.getHeight(), 0, 2, 1); // move all disks from tower 0 to tower 2 using tower 1 as spare
    }

    // Recursive helper
    private void solve(int n, int from, int to, int spare)
    {
        if (n == 0) return;

        solve(n - 1, from, spare, to);     // move n-1 disks to spare
        model.move(from, to);              // move largest disk to destination
        solve(n - 1, spare, to, from);     // move n-1 disks from spare to destination
    }
}
package polygon;

import java.awt.geom.*; // for Point2D.Double
import java.util.ArrayList; // for ArrayList
import gpdraw.*; // for DrawingTool

public class IrregularPolygon {
    private ArrayList<Point2D.Double> myPolygon = new ArrayList<Point2D.Double>();

    // constructor
    public IrregularPolygon() {}

    // public methods
    public void add(Point2D.Double aPoint)
    {
        // Add a point to the polygon
        myPolygon.add(aPoint);
    }

    public double perimeter() {
        // A polygon needs at least 2 points to have a perimeter
        if (myPolygon.size() < 2) {
            return 0.0;
        }

        double perimeter = 0.0;

        for (int i = 0; i < myPolygon.size(); i++) {
            Point2D.Double current = myPolygon.get(i);
            Point2D.Double next = myPolygon.get((i + 1) % myPolygon.size());

            double dx = next.x - current.x;
            double dy = next.y - current.y;

            perimeter += Math.sqrt(dx * dx + dy * dy);
        }

        return perimeter;
    }

    public double area() {
        // A polygon needs at least 3 points to have area
        if (myPolygon.size() < 3) {
            return 0.0;
        }

        double sum1 = 0.0;
        double sum2 = 0.0;

        for (int i = 0; i < myPolygon.size(); i++) {
            Point2D.Double current = myPolygon.get(i);
            Point2D.Double next = myPolygon.get((i + 1) % myPolygon.size());

            sum1 += current.x * next.y;
            sum2 += current.y * next.x;
        }

        return Math.abs(sum1 - sum2) / 2.0;
    }

    public void draw()
    {
        // Wrap the DrawingTool in a try/catch to allow development without need for graphics.
        try {
            // Draw the polygon
            if (myPolygon.size() == 0) {
                return;
            }

            DrawingTool myDrawingTool = new DrawingTool(new SketchPad(500, 500));

            // Move to first point
            Point2D.Double first = myPolygon.get(0);
            myDrawingTool.up();
            myDrawingTool.move(first.x, first.y);
            myDrawingTool.down();

            // Draw lines to remaining points
            for (int i = 1; i < myPolygon.size(); i++) {
                Point2D.Double point = myPolygon.get(i);
                myDrawingTool.move(point.x, point.y);
            }

            // Close the polygon by returning to the first point
            myDrawingTool.move(first.x, first.y);

        } catch (java.awt.HeadlessException e) {
            System.out.println("Exception: No graphics support available.");
        }
    }
}
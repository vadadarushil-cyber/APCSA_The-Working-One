package polygon;

import java.awt.geom.Point2D;

public class Main {
    public static void main(String [] args)
    {
        IrregularPolygon myPolygon = new IrregularPolygon();

        // Add some points so draw() shows something
        myPolygon.add(new Point2D.Double(0, 0));
        myPolygon.add(new Point2D.Double(0, 100));
        myPolygon.add(new Point2D.Double(100, 100));
        myPolygon.add(new Point2D.Double(100, 0));

        myPolygon.draw();

        TestSuite.run();
    }
}
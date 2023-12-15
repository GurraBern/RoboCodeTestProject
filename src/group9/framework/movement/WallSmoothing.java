package group9.framework.movement;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import group9.framework.util.CalcUtils;

public class WallSmoothing {
    public static Rectangle2D.Double _fieldRect = new Rectangle2D.Double(18, 18, 764, 564);

    public static double wallSmoothing(Point2D.Double botLocation, double angle, int orientation) {
        while (!_fieldRect.contains(CalcUtils.project(botLocation, angle, 160))) {
            angle += orientation*0.05;
        }
        return angle;
    }
}

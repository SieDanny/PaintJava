package svgeditor;

import java.awt.*;

public class LineShape extends Shape {
    private int x1;
    private int y1;
    private int x2;
    private int y2;

    public LineShape(int x1, int y1, int x2, int y2, int strokeWidth, Color color) {
        super(strokeWidth, color);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public void setCoordinates(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public void draw(Graphics2D g2d) {
        g2d.setStroke(new BasicStroke(getStrokeWidth()));
        g2d.setColor(getColor());
        g2d.drawLine(x1, y1, x2, y2);
    }
}

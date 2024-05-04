package svgeditor;

import java.awt.*;

public class RectangleShape extends Shape {
    private int x;
    private int y;
    private int width;
    private int height;

    public RectangleShape(int x, int y, int width, int height, int strokeWidth, Color color) {
        super(strokeWidth, color);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void setDimensions(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics2D g2d) {
        g2d.setStroke(new BasicStroke(getStrokeWidth()));
        g2d.setColor(getColor());
        g2d.drawRect(x, y, width, height);
    }
}
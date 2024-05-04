package svgeditor;

import java.awt.*;

public class EllipseShape extends Shape {
    private int centerX;
    private int centerY;
    private int width;
    private int height;

    public EllipseShape(int centerX, int centerY, int width, int height, int strokeWidth, Color color) {
        super(strokeWidth, color);
        this.centerX = centerX;
        this.centerY = centerY;
        this.width = width;
        this.height = height;
    }

    public void setDimensions(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setCenter(int centerX, int centerY) {
        this.centerX = centerX;
        this.centerY = centerY;
    }

    public void draw(Graphics2D g2d) {
        g2d.setStroke(new BasicStroke(getStrokeWidth()));
        g2d.setColor(getColor());
        g2d.drawOval(centerX - width / 2, centerY - height / 2, width, height);
    }
}


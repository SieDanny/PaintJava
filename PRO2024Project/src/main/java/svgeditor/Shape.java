package svgeditor;

import java.awt.*;

public abstract class Shape {
    private int strokeWidth; // tloušťka čáry
    private Color color; // barva

    public Shape(int strokeWidth, Color color) {
        this.strokeWidth = strokeWidth;
        this.color = color;
    }


    // Metody pro práci s tloušťkou čáry a barvou
    public int getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    // Abstraktní metoda pro vykreslení tvaru
    public abstract void draw(Graphics2D g2d);
}
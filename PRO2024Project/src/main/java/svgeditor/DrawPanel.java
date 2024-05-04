package svgeditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

public class DrawPanel extends JPanel {


    private final MainFrame mainFrame;
    private Shape currentShape;
    private Point startPoint;
    private Point endPoint;

    public DrawPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        setBackground(Color.WHITE);


        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startPoint = e.getPoint();
                endPoint = startPoint;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                endPoint = e.getPoint();
                // Vytvoření a přidání tvaru do seznamu
                if (currentShape != null) {
                    mainFrame.addShape(currentShape);
                    currentShape = null;
                }
                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                endPoint = e.getPoint();
                repaint();
            }
        });
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        // Vykreslení všech tvarů
        for (Shape shape : mainFrame.shapes) {
            shape.draw(g2d);
        }
        // Vykreslení aktuálního tvaru, který se kreslí
        if (currentShape != null) {
            currentShape.draw(g2d);
        }
    }

    // Metoda pro nastavení aktuálního tvaru kreslení
   /* public void setCurrentShape(Shape shape) {
        this.currentShape = shape;
    }

    */
}

package svgeditor;

import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame
{
    private DrawPanel drawPanel;
    private ShapeListPanel shapeListPanel;
    private ShapeAttributesPanel shapeAttributesPanel;

    private ButtonPanel buttonPanel;

    public MainFrame()
    {
        setTitle("SVG Editor");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,500);
        //setVisible(true);
        setBackground(Color.white);
        //setSize(750,1000);

        JPanel mainPanel = new JPanel(new BorderLayout());

        drawPanel = new DrawPanel();
        shapeListPanel = new ShapeListPanel();
        shapeAttributesPanel = new ShapeAttributesPanel();
        buttonPanel = new ButtonPanel();

        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        mainPanel.add(drawPanel, BorderLayout.CENTER);
        mainPanel.add(shapeListPanel, BorderLayout.WEST);
        mainPanel.add(shapeAttributesPanel, BorderLayout.EAST);


        add(mainPanel);

        setVisible(true);


    }
}

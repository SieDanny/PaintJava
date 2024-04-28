package svgeditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShapePropertiesDialog extends JDialog {

    private JComboBox<String> shapeComboBox;
    private Shape createdShape;
    private boolean confirmed = false;
    public boolean isConfirmed() {
        return confirmed;
    }
    public Shape getCreatedShape() {
        return createdShape;
    }
    private DrawPanel drawPanel;
    public ShapePropertiesDialog(JFrame parent, DrawPanel drawPanel, String shape) {
        super(parent, "Shape Properties", true);
        this.drawPanel = drawPanel; // Nastavení reference na instanci DrawPanel
        setSize(600, 600);
        setLayout(new GridLayout(0, 1));

        Dimension screenSize =Toolkit.getDefaultToolkit().getScreenSize();
        Dimension dialogSize = this.getSize();
        int x = (screenSize.width - dialogSize.width) / 2;
        int y = (screenSize.height - dialogSize.height) / 2;

        GraphicsConfiguration config = getGraphicsConfiguration();
        x = Math.max(config.getBounds().x, Math.min(x, config.getBounds().x + config.getBounds().width - dialogSize.width));
        y = Math.max(config.getBounds().y, Math.min(y, config.getBounds().y + config.getBounds().height - dialogSize.height));

        setLocation(x, y );

        shapeLabel = new JLabel("Selected Shape:");
        startPointLabel = new JLabel("Start Point (X, Y):");
        endPointLabel = new JLabel("End Point (X, Y):");
        strokeWidthLabel = new JLabel("Stroke Width:");
        colorLabel = new JLabel("Color (Hex):");
        centerLabel = new JLabel("Center (X, Y):");
        widthLabel = new JLabel("Width:");
        heightLabel = new JLabel("Height:");

        startXTextField = new JTextField(10);
        startYTextField = new JTextField(10);
        endXTextField = new JTextField(10);
        endYTextField = new JTextField(10);
        strokeWidthTextField = new JTextField(10);
        colorTextField = new JTextField(10);
        centerXTextField = new JTextField(10);
        centerYTextField = new JTextField(10);
        widthTextField = new JTextField(10);
        heightTextField = new JTextField(10);

        confirmButton = new JButton("Confirm");
        cancelButton = new JButton("Cancel");


        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Zde voláme metodu createShape pro vytvoření nového tvaru
                createdShape = createShape((String) shapeComboBox.getSelectedItem());
                // Pokud byl tvar úspěšně vytvořen, přidáme ho na panel DrawPanel
                if (createdShape != null) {
                    addShapeToPanel(createdShape);
                    confirmed = true; // Nastavíme potvrzení na true
                }
                dispose(); // Zavřít dialogové okno
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        add(shapeLabel);
        add(new JLabel(shape));
        add(startPointLabel);
        add(startXTextField);
        add(new JLabel()); // Prázdný label pro vodorovné zarovnání
        add(startYTextField);
        add(endPointLabel);
        add(endXTextField);
        add(new JLabel()); // Prázdný label pro vodorovné zarovnání
        add(endYTextField);



        // Podle vybraného tvaru přidáme další prvky
        if (shape.equals("Rectangle") || shape.equals("Ellipse")) {
            add(centerLabel);
            add(centerXTextField);
            add(centerYTextField);
            add(widthLabel);
            add(widthTextField);
            add(heightLabel);
            add(heightTextField);
        }

        add(startPointLabel);
        add(startXTextField);
        add(startYTextField);
        add(endPointLabel);
        add(endXTextField);
        add(endYTextField);
        add(strokeWidthLabel);
        add(strokeWidthTextField);
        add(colorLabel);
        add(colorTextField);
        add(confirmButton);
        add(cancelButton);
    }

    // Metoda pro vytvoření tvaru na základě vybraného typu
    private Shape createShape(String shapeType) {
        // Získání hodnot ze vstupních polí
        int startX = Integer.parseInt(startXTextField.getText());
        int startY = Integer.parseInt(startYTextField.getText());
        int endX = Integer.parseInt(endXTextField.getText());
        int endY = Integer.parseInt(endYTextField.getText());
        int strokeWidth = Integer.parseInt(strokeWidthTextField.getText());
        Color color = Color.decode(colorTextField.getText());

        // Vytvoření instance tvaru na základě vybraného typu a zadaných vlastností
        if (shapeType.equals("Rectangle")) {
            int width = Integer.parseInt(widthTextField.getText());
            int height = Integer.parseInt(heightTextField.getText());
            return new RectangleShape(startX, startY, width, height, strokeWidth, color);
        } else if (shapeType.equals("Ellipse")) {
            int width = Integer.parseInt(widthTextField.getText());
            int height = Integer.parseInt(heightTextField.getText());
            return new EllipseShape(startX, startY, width, height, strokeWidth, color);
        } else if (shapeType.equals("Line")) {
            return new LineShape(startX, startY, endX, endY, strokeWidth, color);
        } else {
            // Pokud není vybrán platný typ, vrátíme null
            return null;
        }
    }

    private void addShapeToPanel(Shape newShape) {
        drawPanel.addShape(newShape);
    }

    // Zde jsou deklarace komponent dialogu
    private JLabel shapeLabel;
    private JLabel startPointLabel;
    private JLabel endPointLabel;
    private JLabel strokeWidthLabel;
    private JLabel colorLabel;
    private JLabel centerLabel;
    private JLabel widthLabel;
    private JLabel heightLabel;

    private JTextField startXTextField;
    private JTextField startYTextField;
    private JTextField endXTextField;
    private JTextField endYTextField;
    private JTextField strokeWidthTextField;
    private JTextField colorTextField;
    private JTextField centerXTextField;
    private JTextField centerYTextField;
    private JTextField widthTextField;
    private JTextField heightTextField;

    private JButton confirmButton;
    private JButton cancelButton;
}

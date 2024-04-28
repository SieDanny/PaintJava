package svgeditor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddShapeDialog extends JDialog {
    private JComboBox<String> shapeComboBox;
    private DrawPanel drawPanel;
    private JButton cancelButton;
    private JButton continueButton;
    private String selectedShape;

    public AddShapeDialog(JFrame parent) {
        super(parent, "Add Shape", true);
        setSize(500, 500);
        setLayout(new BorderLayout());
        Dimension screenSize =Toolkit.getDefaultToolkit().getScreenSize();
        Dimension dialogSize = this.getSize();
        int x = (screenSize.width - dialogSize.width) / 2;
        int y = (screenSize.height - dialogSize.height) / 2;

        GraphicsConfiguration config = getGraphicsConfiguration();
        x = Math.max(config.getBounds().x, Math.min(x, config.getBounds().x + config.getBounds().width - dialogSize.width));
        y = Math.max(config.getBounds().y, Math.min(y, config.getBounds().y + config.getBounds().height - dialogSize.height));

        setLocation(x, y);

        String[] shapes = {"Line", "Rectangle", "Ellipse"};
        shapeComboBox = new JComboBox<>(shapes);

        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.add(shapeComboBox);
        add(comboBoxPanel, BorderLayout.CENTER);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedShape = null;
                dispose();
            }
        });

        continueButton = new JButton("Continue");
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedShape = (String) shapeComboBox.getSelectedItem();
                // Zde vytvoříme a zobrazíme dialog pro zadání vlastností tvaru
                ShapePropertiesDialog propertiesDialog = new ShapePropertiesDialog((JFrame) SwingUtilities.getWindowAncestor(AddShapeDialog.this), drawPanel, selectedShape);
                propertiesDialog.setVisible(true);

                // Po zavření dialogu zkontrolujeme, zda uživatel potvrdil vytvoření tvaru
                if (propertiesDialog.isConfirmed()) {
                    // Získání vytvořeného tvaru
                    Shape newShape = propertiesDialog.getCreatedShape();
                    // Přidání tvaru do panelu pro vykreslení
                    if (newShape != null) {
                        // Získání instance panelu DrawPanel
                        DrawPanel drawPanel = (DrawPanel) getParent();
                        // Přidání nového tvaru na panel
                        drawPanel.addShape(newShape);
                    }
                }

                dispose();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(cancelButton);
        buttonPanel.add(continueButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public String getSelectedShape() {
        return selectedShape;
    }
}


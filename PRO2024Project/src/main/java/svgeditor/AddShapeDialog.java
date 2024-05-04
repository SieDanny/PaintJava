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

    public AddShapeDialog(JFrame parent, DrawPanel drawPanel) {
        super(parent, "Add Shape", true);
        this.drawPanel = drawPanel;
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
                // Dialog pro zadání vlastností tvaru
                ShapePropertiesDialog propertiesDialog = new ShapePropertiesDialog((JFrame) SwingUtilities.getWindowAncestor(AddShapeDialog.this), new MainFrame(), selectedShape);
                propertiesDialog.setVisible(true);


                if (propertiesDialog.isConfirmed()) {
                    // Získání vytvořeného tvaru
                    Shape newShape = propertiesDialog.getCreatedShape();
                    // Přidání tvaru do panelu pro vykreslení
                    if (newShape != null) {
                        // Získání instance panelu DrawPanel
                        //DrawPanel drawPanel = (DrawPanel) getParent();
                        // Přidání tvaru do tabulky
                        MainFrame mainFrame = (MainFrame) SwingUtilities.getWindowAncestor(AddShapeDialog.this);
                        mainFrame.getShapeTableModel().addShape(newShape);
                        // Přidání nového tvaru na panel
                        mainFrame.addShape(newShape);
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


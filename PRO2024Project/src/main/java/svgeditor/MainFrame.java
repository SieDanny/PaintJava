package svgeditor;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class MainFrame extends JFrame
{
    private DrawPanel drawPanel;

    public List<Shape> shapes= new ArrayList<>();
    public void addShape(Shape shape) {
        shapes.add(shape);
        repaint();
    }

    private ButtonPanel buttonPanel;
    private ShapeTableModel shapeTableModel;
    public MainFrame()
    {
        setTitle("SVG Editor");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,500);
        setBackground(Color.white);

        shapeTableModel = new ShapeTableModel();
        JTable shapeTable = new JTable(shapeTableModel);

        JScrollPane scrollPane = new JScrollPane(shapeTable);
        JPanel mainPanel = new JPanel(new BorderLayout());

        drawPanel = new DrawPanel(this);
        buttonPanel = new ButtonPanel(drawPanel);

        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        mainPanel.add(drawPanel, BorderLayout.CENTER);
        mainPanel.add(scrollPane, BorderLayout.WEST);

        add(mainPanel);

        setVisible(true);

        shapeTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = shapeTable.getSelectedRow();
                if (selectedRow != -1) {
                    Shape selectedShape = shapeTableModel.getShape(selectedRow);
                    // Dialog pro úpravu atributů
                    ShapePropertiesDialog propertiesDialog = new ShapePropertiesDialog((JFrame) SwingUtilities.getWindowAncestor(MainFrame.this), this, selectedShape.toString());
                    propertiesDialog.setVisible(true);


                    if (propertiesDialog.isConfirmed()) {

                        shapeTableModel.fireTableDataChanged();
                    }
                }
            }
        });

    }
    public ShapeTableModel getShapeTableModel() {
        return shapeTableModel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}

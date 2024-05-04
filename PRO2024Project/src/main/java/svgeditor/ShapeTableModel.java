package svgeditor;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ShapeTableModel extends AbstractTableModel {
    private List<Shape> shapes;
    private String[] columnNames = {"Type", "Attributes"};

    public ShapeTableModel() {
        shapes = new ArrayList<>();
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
        fireTableDataChanged(); // Aktualizuje tabulku
    }

    public Shape getShape(int index) {
        return shapes.get(index);
    }

    @Override
    public int getRowCount() {
        return shapes.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Shape shape = shapes.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return shape.getClass().getSimpleName(); // Název třídy (Line, Rectangle, Ellipse)
            case 1:
                return shape.toString();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}
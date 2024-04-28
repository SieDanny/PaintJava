package svgeditor;

import javax.swing.*;
import java.util.List;


public class ShapeListModel extends AbstractListModel<Shape> {
    private List<Shape> shapes;

    public ShapeListModel(List<Shape> shapes) {
        this.shapes = shapes;
    }

    @Override
    public int getSize() {
        return shapes.size();
    }

    @Override
    public Shape getElementAt(int index) {
        return shapes.get(index);
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
        fireIntervalAdded(this, getSize() - 1, getSize() - 1);
    }

    public void removeShape(int index) {
        shapes.remove(index);
        fireIntervalRemoved(this, index, index);
    }
}

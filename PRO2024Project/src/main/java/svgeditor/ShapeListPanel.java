package svgeditor;

import javax.swing.*;
import java.util.ArrayList;
import java.awt.*;




public class ShapeListPanel extends JPanel {
    private JList<Shape> shapeList;
    private ShapeListModel shapeListModel;

    public ShapeListPanel() {
        shapeListModel = new ShapeListModel(new ArrayList<>());
        shapeList = new JList<>(shapeListModel);

        JScrollPane scrollPane = new JScrollPane(shapeList);
        scrollPane.setPreferredSize(new Dimension(200, 400));

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }

    public void addShape(Shape shape) {
        shapeListModel.addShape(shape);
    }

    public void removeShape(int index) {
        shapeListModel.removeShape(index);
    }
}

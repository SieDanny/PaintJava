package svgeditor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Map;

public class ShapeAttributesPanel extends JPanel {
    private JTable attributesTable;
    private DefaultTableModel tableModel;

    public ShapeAttributesPanel() {
        String[] columnNames = {"Attribute", "Value"};
        Object[][] data = {};

        tableModel = new DefaultTableModel(data, columnNames);
        attributesTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(attributesTable);
        scrollPane.setPreferredSize(new Dimension(200, 200));

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }

    public void setAttributes(Map<String, String> attributes) {
        tableModel.setRowCount(0);


        attributes.forEach((key, value) -> {
            tableModel.addRow(new Object[]{key, value});
        });
    }
}

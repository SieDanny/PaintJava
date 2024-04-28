package svgeditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPanel extends JPanel {

    public ButtonPanel() {
        setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton addButton = new JButton("Add Shape");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Zobrazení dialogového okna pro přidání tvaru
                AddShapeDialog dialog = new AddShapeDialog((JFrame) SwingUtilities.getWindowAncestor(ButtonPanel.this));
                dialog.setVisible(true);

                // Zde můžete zpracovat vstupy z dialogového okna a přidat tvar do DrawPanelu
            }
        });
        add(addButton);
    }
}
package UI;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuSide extends JFrame {
    public MenuSide() {

    }

    public JPanel MenuSideUI() {
        JPanel jPanelMenu = new JPanel();
        jPanelMenu.setSize(243, 956);
        jPanelMenu.setBackground(new Color(80, 65, 188));
        jPanelMenu.setForeground(new Color(80, 65, 188));
        jPanelMenu.setOpaque(true);

        JPanel jPanelIcon = new JPanel();
        jPanelIcon.setSize(243, 148);

        JLabel jLabelIcon = new JLabel(
                "Welcome");
        jLabelIcon.setSize(243, 148);
        // jLabelIcon.setAlignmentX(CENTER_ALIGNMENT);
        // jLabelIcon.setAlignmentY(CENTER_ALIGNMENT);
        // jLabelIcon.setBackground(new Color(80, 65, 188));
        // jLabelIcon.setOpaque(true);

        jPanelIcon.add(jLabelIcon);

        jPanelMenu.add(jPanelIcon);
        jPanelMenu.add(jPanelIcon);
        jPanelMenu.add(jPanelIcon);
        jPanelMenu.add(jPanelIcon);
        jPanelMenu.add(jPanelIcon);
        jPanelMenu.add(jPanelIcon);
        jPanelMenu.add(jPanelIcon);

        return jPanelMenu;
    }
}

package UI;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MenuSide extends JFrame {
    public MenuSide() {

    }

    public JLabel MenuSideUI() {
        JLabel jLabelMenu = new JLabel();
        jLabelMenu.setSize(243, 956);
        jLabelMenu.setBackground(new Color(80, 65, 188));
        jLabelMenu.setForeground(new Color(80, 65, 188));
        jLabelMenu.setOpaque(true);
        return jLabelMenu;
    }
}

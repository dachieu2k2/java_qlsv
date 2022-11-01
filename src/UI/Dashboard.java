package UI;

import java.awt.Color;
import javax.swing.JPanel;

public class Dashboard {
    public JPanel DashboardUI() {
        JPanel jLabelOutlet = new JPanel();
        jLabelOutlet.setSize(1200, 956);
        jLabelOutlet.setForeground(new Color(255, 255, 255));
        jLabelOutlet.setBackground(new Color(255, 255, 255));
        jLabelOutlet.setOpaque(true);
        return jLabelOutlet;
    }
}

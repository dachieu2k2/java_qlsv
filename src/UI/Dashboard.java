package UI;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Dashboard {
    public JPanel render() {
        JPanel jLabelOutlet = new JPanel();
        JLabel jLabel = new JLabel("Dashboard");
        jLabelOutlet.add(jLabel);
        return jLabelOutlet;
    }
}
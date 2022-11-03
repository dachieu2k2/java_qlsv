package UI;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Khoa {
    public JPanel render() {

        JPanel jLabelOutlet = new JPanel();
        JLabel jLabel = new JLabel("Khoa");
        jLabelOutlet.add(jLabel);
        return jLabelOutlet;

    }
}

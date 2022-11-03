package UI;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Diem {
    public JPanel render() {

        JPanel jLabelOutlet = new JPanel();
        JLabel jLabel = new JLabel("Diem");
        jLabelOutlet.add(jLabel);
        return jLabelOutlet;

    }
}

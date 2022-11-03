package UI;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Sinhvien {
    public JPanel render() {

        JPanel jLabelOutlet = new JPanel();
        JLabel jLabel = new JLabel("Sinhvien");
        jLabelOutlet.add(jLabel);
        return jLabelOutlet;

    }
}

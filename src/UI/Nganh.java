package UI;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Nganh {
    public JPanel render() {

        JPanel jLabelOutlet = new JPanel();
        JLabel jLabel = new JLabel("Nganh");
        jLabelOutlet.add(jLabel);
        return jLabelOutlet;

    }
}

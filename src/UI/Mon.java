package UI;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Mon {
    public JPanel render() {

        JPanel jLabelOutlet = new JPanel();
        JLabel jLabel = new JLabel("Mon");
        jLabelOutlet.add(jLabel);
        return jLabelOutlet;

    }
}

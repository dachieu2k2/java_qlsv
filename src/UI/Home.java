package UI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;

public class Home extends JFrame {
    public Home() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Quan ly sinh vien");
        this.setSize(1443, 956);
        MenuSide m = new MenuSide();

        JLabel jLabelOutlet = new JLabel();
        jLabelOutlet.setSize(1200, 956);
        jLabelOutlet.setForeground(new Color(255, 255, 255));
        jLabelOutlet.setBackground(new Color(255, 255, 255));
        jLabelOutlet.setOpaque(true);

        // this.setLayout();
        this.add(m.MenuSideUI());
        this.add(jLabelOutlet);
        this.setVisible(true);
    }
}

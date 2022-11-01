package UI;

import javax.swing.JFrame;

public class Home extends JFrame {
    public Home() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Quan ly sinh vien");
        this.setSize(1443, 956);
        MenuSide m = new MenuSide();
        Dashboard d = new Dashboard();

        // this.setLayout();
        this.add(m.MenuSideUI());
        this.add(d.DashboardUI());
        this.setVisible(true);
    }
}

package UI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.GridLayout;

import java.awt.event.*;

public class Home extends JFrame implements ActionListener {
    private JPanel jPanelHeader = new JPanel();
    private JPanel jPanelContent = new JPanel();
    // all component
    private Dashboard dashboard = new Dashboard();
    private Diem diem = new Diem();
    private Sinhvien sinhvien = new Sinhvien();
    private Mon mon = new Mon();
    private Khoa khoa = new Khoa();
    private Nganh nganh = new Nganh();

    public Home() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Quan ly sinh vien");
        this.setSize(1200, 700);
        this.setLayout(new GridLayout(2, 0));

        jPanelHeader.setSize(1200, 100);
        JButton DashboardMenu = new JButton("Dashboard");
        JButton KhoaMenu = new JButton("Khoa");
        JButton MonMenu = new JButton("Mon");
        JButton DiemMenu = new JButton("Diem");
        JButton NganhMenu = new JButton("Nganh");
        JButton SinhvienMenu = new JButton("Sinhvien");

        // add action listener
        DashboardMenu.addActionListener(this);
        KhoaMenu.addActionListener(this);
        MonMenu.addActionListener(this);
        DiemMenu.addActionListener(this);
        SinhvienMenu.addActionListener(this);
        NganhMenu.addActionListener(this);

        // add to menu
        jPanelHeader.add(DashboardMenu);
        jPanelHeader.add(KhoaMenu);
        jPanelHeader.add(NganhMenu);
        jPanelHeader.add(SinhvienMenu);
        jPanelHeader.add(MonMenu);
        jPanelHeader.add(DiemMenu);

        // this.setLayout();

        // container all content

        jPanelContent.add(khoa.render());
        this.add(jPanelHeader);
        this.add(jPanelContent);

        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        String ae = e.getActionCommand();
        switch (ae) {
            case "Dashboard":
                clearContent();
                jPanelContent.add(dashboard.render());
                break;

            case "Khoa":
                clearContent();
                jPanelContent.add(khoa.render());
                break;
            case "Nganh":
                clearContent();
                jPanelContent.add(nganh.render());
                break;
            case "Mon":
                clearContent();
                jPanelContent.add(mon.render());
                break;
            case "Diem":
                clearContent();
                jPanelContent.add(diem.render());
                break;
            case "Sinhvien":
                clearContent();
                jPanelContent.add(sinhvien.render());
                break;

            default:
                clearContent();
                jPanelContent.add(dashboard.render());
                break;
        }
    }

    void clearContent() {
        jPanelContent.removeAll();
        jPanelContent.revalidate();
        jPanelContent.repaint();
    }
}

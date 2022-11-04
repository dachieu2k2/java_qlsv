package UI;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.DimensionUIResource;

import java.awt.GridLayout;
import java.awt.FlowLayout;

public class Khoa {
    public JPanel render() {

        JPanel jLabelOutlet = new JPanel();
        // setLayout 1 dong 2 cot
        jLabelOutlet.setLayout(new FlowLayout());

        // Header
        JPanel jPanelHeader = new JPanel();
        JLabel jLabel = new JLabel("Khoa");
        jPanelHeader.add(jLabel);
        jPanelHeader.setPreferredSize(new DimensionUIResource(1200, 100));

        // Body
        JPanel jPanelBody = new JPanel();
        jPanelBody.setLayout(new GridLayout(1, 2));
        jPanelBody.setPreferredSize(new DimensionUIResource(1200, 700));

        // left
        JPanel jPanelLeft = new JPanel();
        jPanelLeft.setLayout(new FlowLayout());
        jPanelLeft.setPreferredSize(new DimensionUIResource(400, 700));
        JLabel jLabelTenKhoa = new JLabel("Ten khoa");

        JTextField jTextFieldTenKhoa = new JTextField();
        jTextFieldTenKhoa.setPreferredSize(new DimensionUIResource(300, 20));

        JButton jButtonAddKhoa = new JButton("Them khoa");

        jPanelLeft.add(jLabelTenKhoa);
        jPanelLeft.add(jTextFieldTenKhoa);
        jPanelLeft.add(jButtonAddKhoa);

        // right
        JPanel jPanelRight = new JPanel();
        jPanelRight.setLayout(new FlowLayout());
        jPanelRight.setPreferredSize(new DimensionUIResource(800, 700));

        JLabel jLabelRight = new JLabel("Ten Khoa");

        jPanelRight.add(jLabelRight);

        jPanelBody.add(jPanelLeft);
        jPanelBody.add(jPanelRight);

        jLabelOutlet.add(jPanelHeader);
        jLabelOutlet.add(jPanelBody);
        return jLabelOutlet;

    }
}

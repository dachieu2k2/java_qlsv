package UI;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import State.KhoaState;
import models.KhoaModel;

import java.util.ArrayList;
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

        // Body
        JPanel jPanelBody = new JPanel();
        jPanelBody.setLayout(new FlowLayout());

        // left
        JPanel jPanelLeft = new JPanel();
        jPanelLeft.setLayout(new FlowLayout());
        JLabel jLabelTenKhoa = new JLabel("Ten khoa");

        JTextField jTextFieldTenKhoa = new JTextField();

        JButton jButtonAddKhoa = new JButton("Them khoa");

        jPanelLeft.add(jLabelTenKhoa);
        jPanelLeft.add(jTextFieldTenKhoa);
        jPanelLeft.add(jButtonAddKhoa);

        // right
        JPanel jPanelRight = new JPanel();
        jPanelRight.setLayout(new FlowLayout());

        JLabel jLabelRight = new JLabel("Ten Khoa");

        KhoaState khoaState = new KhoaState();
        ArrayList<KhoaModel> list = khoaState.view();

        Object[][] row = new Object[100][2];
        String col[] = { "id", "tenkhoa" };

        for (int i = 0; i < list.size(); i++) {
            row[i][0] = list.get(i).getMa();
            row[i][1] = list.get(i).getTenKhoa();
        }
        JTable JTableKhoa = new JTable(row, col);

        JScrollPane JScrollPane = new JScrollPane();
        JTableKhoa.setBounds(30, 40, 200, 300);
        JScrollPane.add(JTableKhoa);

        jPanelRight.add(jLabelRight);
        jPanelRight.add(JScrollPane);

        // jPanelBody.add(jPanelLeft);
        jPanelBody.add(jPanelRight);

        // jLabelOutlet.add(jPanelHeader);
        jLabelOutlet.add(jPanelBody);
        return jLabelOutlet;

    }
}

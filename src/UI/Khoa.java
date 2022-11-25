package UI;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.table.DefaultTableModel;

import State.KhoaState;
import UI.Components.Dialog;
import models.KhoaModel;

import java.util.ArrayList;
import java.awt.FlowLayout;
import java.awt.Color;

import java.awt.event.*;

public class Khoa implements ActionListener {
    // data
    private KhoaState khoaState = new KhoaState();
    private ArrayList<KhoaModel> list;

    // ui
    JTextField jTextFieldTenKhoa;
    JTextField jTextFieldTimkiem;
    JTextField jTextFieldId;
    DefaultTableModel model;
    JTable jTableKhoa;
    String[] col = { "ma", "tenkhoa" };
    Dialog d;

    Khoa() {

    }

    public JPanel render() {

        JPanel jLabelOutlet = new JPanel();
        jLabelOutlet.setLayout(new FlowLayout());
        jLabelOutlet.setPreferredSize(new DimensionUIResource(1200, 600));

        // Header
        JPanel jPanelHeader = new JPanel();
        jPanelHeader.setLayout(new FlowLayout());
        jPanelHeader.setPreferredSize(new DimensionUIResource(1200, 50));

        JLabel jLabel = new JLabel("Khoa", SwingConstants.CENTER);
        jLabel.setPreferredSize(new DimensionUIResource(1200, 20));
        JLabel jLabelTimKiem = new JLabel("Tim kiem");
        jTextFieldTimkiem = new JTextField();
        jTextFieldTimkiem.setPreferredSize(new DimensionUIResource(200, 20));

        jTextFieldTimkiem.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                ChangeTextInput();
            }

            public void removeUpdate(DocumentEvent e) {
                ChangeTextInput();
            }

            public void insertUpdate(DocumentEvent e) {
                ChangeTextInput();
            }

            public void ChangeTextInput() {
                String s = jTextFieldTimkiem.getText();
                if (s == null) {
                    renderTable();
                } else {
                    list = khoaState.SearchText(s);
                    Object[][] row = new Object[list.size()][col.length];

                    for (int i = 0; i < list.size(); i++) {
                        row[i][0] = list.get(i).getMa();
                        row[i][1] = list.get(i).getTenKhoa();
                    }
                    model = new DefaultTableModel(row, col);
                    jTableKhoa.setModel(model);
                }
            }

        });

        jPanelHeader.add(jLabel);
        jPanelHeader.add(jLabelTimKiem);
        jPanelHeader.add(jTextFieldTimkiem);

        // Body
        JPanel jPanelBody = new JPanel();
        jPanelBody.setLayout(new FlowLayout());
        jPanelBody.setPreferredSize(new DimensionUIResource(1200, 550));

        // left
        JPanel jPanelLeft = new JPanel();
        jPanelLeft.setLayout(new FlowLayout());
        JLabel jLabelTenKhoa = new JLabel("Ten khoa: ");
        JLabel jLabelId = new JLabel("ma: ");
        jPanelLeft.setPreferredSize(new DimensionUIResource(1200, 50));
        jPanelLeft.setBackground(Color.green);

        jTextFieldTenKhoa = new JTextField();
        jTextFieldTenKhoa.setPreferredSize(new DimensionUIResource(200, 20));

        jTextFieldId = new JTextField();
        jTextFieldId.setPreferredSize(new DimensionUIResource(200, 20));

        JButton jButtonAddKhoa = new JButton("Them khoa");
        JButton jButtonUpdateKhoa = new JButton("Cap nhat khoa");
        JButton jButtonDeleteKhoa = new JButton("Xoa khoa");

        jButtonAddKhoa.addActionListener(this);
        jButtonUpdateKhoa.addActionListener(this);
        jButtonDeleteKhoa.addActionListener(this);

        jPanelLeft.add(jLabelId);
        jPanelLeft.add(jTextFieldId);
        jPanelLeft.add(jLabelTenKhoa);
        jPanelLeft.add(jTextFieldTenKhoa);
        jPanelLeft.add(jButtonAddKhoa);
        jPanelLeft.add(jButtonUpdateKhoa);
        jPanelLeft.add(jButtonDeleteKhoa);

        // right
        JPanel jPanelRight = new JPanel();
        jPanelRight.setLayout(new FlowLayout());
        jPanelRight.setPreferredSize(new DimensionUIResource(900, 550));
        jPanelRight.setBackground(Color.red);

        JLabel jLabelRight = new JLabel("Bang");
        this.list = khoaState.view();

        Object[][] row = new Object[list.size()][col.length];

        for (int i = 0; i < list.size(); i++) {
            row[i][0] = list.get(i).getMa();
            row[i][1] = list.get(i).getTenKhoa();
        }
        model = new DefaultTableModel(row, col);
        jTableKhoa = new JTable(model);

        jTableKhoa.setBounds(30, 40, 200, 300);
        jTableKhoa.setEnabled(false);

        JScrollPane jScrollPane = new JScrollPane(jTableKhoa);
        jScrollPane.setPreferredSize(new DimensionUIResource(900, 550));

        jPanelRight.add(jLabelRight);
        jPanelRight.add(jScrollPane);

        jPanelBody.add(jPanelLeft);
        jPanelBody.add(jPanelRight);

        jLabelOutlet.add(jPanelHeader);
        jLabelOutlet.add(jPanelBody);
        return jLabelOutlet;

    }

    public void actionPerformed(ActionEvent e) {
        String ae = e.getActionCommand();
        int check = 0;
        switch (ae) {
            case "Them khoa":

                if (jTextFieldTenKhoa.getText().equals("")) {
                    d = new Dialog("Can phai co ten khoa");
                    break;
                } else {
                    khoaState.insert(jTextFieldTenKhoa.getText());
                    resetInput();
                    renderTable();
                    d = new Dialog("Them thanh cong");
                }
                break;

            case "Xoa khoa":
                if (jTextFieldId.getText().equals("")) {
                    d = new Dialog("Can phai co id");
                    break;
                }
                check = 0;
                for (int i = 0; i < list.size(); i++) {
                    if (Integer.parseInt(jTextFieldId.getText()) == list.get(i).getMa()) {
                        khoaState.delete(Integer.parseInt(jTextFieldId.getText()));
                        renderTable();
                        resetInput();
                        d = new Dialog("Xoa thanh cong");
                        check = 1;
                        break;
                    }
                }
                if (check == 0) {
                    d = new Dialog("khong tim thay id");
                }

                break;
            case "Cap nhat khoa":
                if (jTextFieldTenKhoa.getText().equals("") || jTextFieldId.getText().equals("")) {
                    d = new Dialog("Can phai co ten khoa,id");
                    break;
                }
                check = 0;
                for (int i = 0; i < list.size(); i++) {
                    if (Integer.parseInt(jTextFieldId.getText()) == list.get(i).getMa()) {
                        khoaState.update(Integer.parseInt(jTextFieldId.getText()), jTextFieldTenKhoa.getText());
                        renderTable();
                        resetInput();
                        d = new Dialog("Cap nhat thanh cong");
                        check = 1;
                        break;
                    }
                }
                if (check == 0) {
                    d = new Dialog("khong tim thay id");
                }

                break;
            default:
                break;
        }

    }

    public void resetInput() {
        jTextFieldId.setText("");
        jTextFieldTenKhoa.setText("");
    }

    public void renderTable() {
        list = khoaState.view();
        Object[][] row = new Object[list.size()][col.length];

        for (int i = 0; i < list.size(); i++) {
            row[i][0] = list.get(i).getMa();
            row[i][1] = list.get(i).getTenKhoa();
        }
        model = new DefaultTableModel(row, col);
        jTableKhoa.setModel(model);
    }
}

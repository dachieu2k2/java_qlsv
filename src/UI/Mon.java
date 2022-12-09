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
import javax.swing.event.MouseInputAdapter;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.table.DefaultTableModel;

import State.MonState;
import UI.Components.Dialog;
import models.MonModel;

import java.util.ArrayList;
import java.awt.FlowLayout;
import java.awt.Color;

import java.awt.event.*;

public class Mon implements ActionListener {
    // data
    private MonState monState = new MonState();
    private ArrayList<MonModel> list;

    // ui
    JTextField jTextFieldTenMon;
    JTextField jTextFieldTimkiem;
    JTextField jTextFieldId;
    DefaultTableModel model;
    JTable jTableMon;
    String[] col = { "ma", "tenmon" };
    Dialog d;

    Mon() {

    }

    public JPanel render() {
        JPanel jLabelOutlet = new JPanel();
        jLabelOutlet.setLayout(new FlowLayout());
        jLabelOutlet.setPreferredSize(new DimensionUIResource(1200, 600));

        // Header
        JPanel jPanelHeader = new JPanel();
        jPanelHeader.setLayout(new FlowLayout());
        jPanelHeader.setPreferredSize(new DimensionUIResource(1200, 50));

        JLabel jLabel = new JLabel("Mon", SwingConstants.CENTER);
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
                    list = monState.SearchText(s);
                    Object[][] row = new Object[list.size()][col.length];

                    for (int i = 0; i < list.size(); i++) {
                        row[i][0] = list.get(i).getMa();
                        row[i][1] = list.get(i).getTenMon();
                    }
                    model = new DefaultTableModel(row, col);
                    jTableMon.setModel(model);
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
        JLabel jLabelTenMon = new JLabel("Ten Mon: ");
        JLabel jLabelId = new JLabel("ma: ");
        jPanelLeft.setPreferredSize(new DimensionUIResource(1200, 50));
        jPanelLeft.setBackground(Color.green);

        jTextFieldTenMon = new JTextField();
        jTextFieldTenMon.setPreferredSize(new DimensionUIResource(200, 20));

        jTextFieldId = new JTextField();
        jTextFieldId.setPreferredSize(new DimensionUIResource(200, 20));

        JButton jButtonAddMon = new JButton("Them Mon");
        JButton jButtonUpdateMon = new JButton("Cap nhat Mon");
        JButton jButtonDeleteMon = new JButton("Xoa Mon");

        jButtonAddMon.addActionListener(this);
        jButtonUpdateMon.addActionListener(this);
        jButtonDeleteMon.addActionListener(this);

        jPanelLeft.add(jLabelId);
        jPanelLeft.add(jTextFieldId);
        jPanelLeft.add(jLabelTenMon);
        jPanelLeft.add(jTextFieldTenMon);
        jPanelLeft.add(jButtonAddMon);
        jPanelLeft.add(jButtonUpdateMon);
        jPanelLeft.add(jButtonDeleteMon);

        // right
        JPanel jPanelRight = new JPanel();
        jPanelRight.setLayout(new FlowLayout());
        jPanelRight.setPreferredSize(new DimensionUIResource(900, 550));
        jPanelRight.setBackground(Color.red);

        JLabel jLabelRight = new JLabel("Bang");
        this.list = monState.view();

        Object[][] row = new Object[list.size()][col.length];

        for (int i = 0; i < list.size(); i++) {
            row[i][0] = list.get(i).getMa();
            row[i][1] = list.get(i).getTenMon();
        }
        model = new DefaultTableModel(row, col);
        jTableMon = new JTable(model);

        jTableMon.setBounds(30, 40, 200, 300);
        jTableMon.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = jTableMon.rowAtPoint(evt.getPoint());
                int col = jTableMon.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    jTextFieldId.setText(model.getValueAt(row, 0).toString());
                    jTextFieldTenMon.setText(model.getValueAt(row, 1).toString());

                }
            }
        });
        jTableMon.setEnabled(false);

        JScrollPane jScrollPane = new JScrollPane(jTableMon);
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
            case "Them Mon":
                if (jTextFieldTenMon.getText().equals("")) {
                    d = new Dialog("Can phai co ten mon");
                    break;

                } else {
                    monState.insert(jTextFieldTenMon.getText());

                    resetInput();
                    renderTable();
                    d = new Dialog("Them thanh cong");
                }

                break;

            case "Xoa Mon":
                if (jTextFieldId.getText().equals("")) {
                    d = new Dialog("Can phai co id");
                    break;
                }
                check = 0;
                for (int i = 0; i < list.size(); i++) {
                    if (Integer.parseInt(jTextFieldId.getText()) == list.get(i).getMa()) {
                        monState.delete(Integer.parseInt(jTextFieldId.getText()));
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
            case "Cap nhat Mon":
                if (jTextFieldTenMon.getText().equals("") || jTextFieldId.getText().equals("")) {
                    d = new Dialog("Can phai co ten mon ,id");
                    break;
                }
                check = 0;
                for (int i = 0; i < list.size(); i++) {
                    if (Integer.parseInt(jTextFieldId.getText()) == list.get(i).getMa()) {
                        monState.update(Integer.parseInt(jTextFieldId.getText()), jTextFieldTenMon.getText());
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
        jTextFieldTenMon.setText("");
    }

    public void renderTable() {
        list = monState.view();
        Object[][] row = new Object[list.size()][col.length];

        for (int i = 0; i < list.size(); i++) {
            row[i][0] = list.get(i).getMa();
            row[i][1] = list.get(i).getTenMon();
        }
        model = new DefaultTableModel(row, col);
        jTableMon.setModel(model);
    }
}

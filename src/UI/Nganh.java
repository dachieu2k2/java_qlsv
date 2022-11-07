package UI;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.table.DefaultTableModel;

import State.KhoaState;
import State.NganhState;
import UI.Components.ComboItem;
import models.KhoaModel;
import models.NganhModel;

import java.util.ArrayList;
import java.awt.FlowLayout;
import java.awt.Color;

import java.awt.event.*;

public class Nganh implements ActionListener {
    // data
    private NganhState nganhState = new NganhState();
    private ArrayList<NganhModel> list;
    private KhoaState khoaState = new KhoaState();

    // ui
    JTextField jTextFieldTimkiem;
    JTextField jTextFieldId;
    DefaultTableModel model;
    JTable jTableNganh;
    JTextField jTextFieldTenNganh;
    JComboBox<ComboItem> jComboBoxTenKhoa;

    String[] col = { "ma", "tennganh", "tenkhoa" };

    public JPanel render() {

        JPanel jLabelOutlet = new JPanel();
        jLabelOutlet.setLayout(new FlowLayout());
        jLabelOutlet.setPreferredSize(new DimensionUIResource(1200, 600));

        // Header
        JPanel jPanelHeader = new JPanel();
        jPanelHeader.setLayout(new FlowLayout());
        jPanelHeader.setPreferredSize(new DimensionUIResource(1200, 50));

        JLabel jLabel = new JLabel("Nganh", SwingConstants.CENTER);
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
                    list = nganhState.SearchText(s);
                    Object[][] row = new Object[list.size()][3];

                    for (int i = 0; i < list.size(); i++) {
                        row[i][0] = list.get(i).getMa();
                        row[i][1] = list.get(i).getTenNganh();
                        row[i][2] = list.get(i).getTenKhoa();
                    }
                    model = new DefaultTableModel(row, col);
                    jTableNganh.setModel(model);
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
        JLabel jLabelTenNganh = new JLabel("Ten nganh: ");
        JLabel jLabelTenKhoa = new JLabel("Ten Khoa: ");
        JLabel jLabelId = new JLabel("ma: ");
        jPanelLeft.setPreferredSize(new DimensionUIResource(1200, 50));
        jPanelLeft.setBackground(Color.green);

        jTextFieldTenNganh = new JTextField();
        jTextFieldTenNganh.setPreferredSize(new DimensionUIResource(200, 20));

        jTextFieldId = new JTextField();
        jTextFieldId.setPreferredSize(new DimensionUIResource(200, 20));

        JButton jButtonAddNganh = new JButton("Them Nganh");
        JButton jButtonUpdateNganh = new JButton("Cap nhat Nganh");
        JButton jButtonDeleteNganh = new JButton("Xoa Nganh");

        jComboBoxTenKhoa = new JComboBox<>();

        ArrayList<KhoaModel> listKhoa = new ArrayList<>(khoaState.view());

        for (int i = 0; i < listKhoa.size(); i++) {
            ComboItem cobo = new ComboItem(listKhoa.get(i).getMa(), listKhoa.get(i).getTenKhoa());
            jComboBoxTenKhoa.addItem(cobo);
        }

        jComboBoxTenKhoa.addActionListener(this);
        jButtonAddNganh.addActionListener(this);
        jButtonUpdateNganh.addActionListener(this);
        jButtonDeleteNganh.addActionListener(this);

        jPanelLeft.add(jLabelId);
        jPanelLeft.add(jTextFieldId);
        jPanelLeft.add(jLabelTenNganh);
        jPanelLeft.add(jTextFieldTenNganh);
        jPanelLeft.add(jLabelTenKhoa);
        jPanelLeft.add(jComboBoxTenKhoa);

        jPanelLeft.add(jButtonAddNganh);
        jPanelLeft.add(jButtonUpdateNganh);
        jPanelLeft.add(jButtonDeleteNganh);

        // right
        JPanel jPanelRight = new JPanel();
        jPanelRight.setLayout(new FlowLayout());
        jPanelRight.setPreferredSize(new DimensionUIResource(900, 550));
        jPanelRight.setBackground(Color.red);

        JLabel jLabelRight = new JLabel("Bang");
        this.list = nganhState.view();

        Object[][] row = new Object[list.size()][3];

        for (int i = 0; i < list.size(); i++) {
            row[i][0] = list.get(i).getMa();
            row[i][1] = list.get(i).getTenNganh();
            row[i][2] = list.get(i).getTenKhoa();
        }
        model = new DefaultTableModel(row, col);
        jTableNganh = new JTable(model);

        jTableNganh.setBounds(30, 40, 200, 300);
        jTableNganh.setEnabled(false);

        JScrollPane jScrollPane = new JScrollPane(jTableNganh);
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

        switch (ae) {
            case "Them Nganh":
                nganhState.insert(jTextFieldTenNganh.getText(),
                        ((ComboItem) jComboBoxTenKhoa.getSelectedItem()).getValue());

                // System.out.println(((ComboItem)
                // jComboBoxTenKhoa.getSelectedItem()).getValue());
                resetInput();
                renderTable();
                break;

            case "Xoa Nganh":
                nganhState.delete(Integer.parseInt(jTextFieldId.getText()));
                renderTable();
                resetInput();

                break;
            case "Cap nhat Nganh":
                nganhState.update(Integer.parseInt(jTextFieldId.getText()), jTextFieldTenNganh.getText(),
                        ((ComboItem) jComboBoxTenKhoa.getSelectedItem()).getValue());

                // jTextFieldTenNganh.getText());
                renderTable();
                resetInput();

                break;
            default:
                break;
        }

    }

    public void resetInput() {
        jTextFieldId.setText("");
        // jTextFieldTenNganh.setText("");
    }

    public void renderTable() {
        list = nganhState.view();
        Object[][] row = new Object[list.size()][3];

        for (int i = 0; i < list.size(); i++) {
            row[i][0] = list.get(i).getMa();
            row[i][1] = list.get(i).getTenNganh();
            row[i][2] = list.get(i).getTenKhoa();
        }
        model = new DefaultTableModel(row, col);
        jTableNganh.setModel(model);
    }

}

package UI;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
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
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.*;

import State.SinhVienState;
import UI.Components.ComboItem;
import models.SinhVienModel;

public class Sinhvien implements ActionListener {
    // data
    private SinhVienState sinhVienState = new SinhVienState();
    private ArrayList<SinhVienModel> list;

    // ui
    JTextField jTextFieldTimkiem;
    JTextField jTextFieldId;
    DefaultTableModel model;
    JTable jTableNganh;
    JTextField jTextFieldTenNganh;
    JComboBox<ComboItem> jComboBoxTenKhoa;

    String[] col = { "masv", "tensv", "hosv", "gioitinh", "ngaysinh", "noisinh",
            "diachi",
            "manganh", "hocbong", "avatar", "tennganh" };

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
                    list = sinhVienState.SearchText(s);
                    Object[][] row = new Object[list.size()][col.length];

                    for (int i = 0; i < list.size(); i++) {
                        row[i][0] = list.get(i).getMasv();
                        row[i][1] = list.get(i).getTensv();
                        row[i][2] = list.get(i).getHosv();
                        row[i][3] = list.get(i).getGioiTinh();
                        row[i][4] = list.get(i).getNgaysinh();
                        row[i][5] = list.get(i).getNoisinh();
                        row[i][6] = list.get(i).getDiachi();
                        row[i][7] = list.get(i).getManganh();
                        row[i][8] = list.get(i).getHocBong();
                        row[i][9] = list.get(i).getAvatar();
                        row[i][10] = list.get(i).getTennganh();
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

        JButton jButtonAddSinhvien = new JButton("Them Sinhvien");
        JButton jButtonUpdateSinhvien = new JButton("Cap nhat Sinhvien");
        JButton jButtonDeleteSinhvien = new JButton("Xoa Sinhvien");

        jComboBoxTenKhoa = new JComboBox<>();

        // ArrayList<KhoaModel> listKhoa = new ArrayList<>(khoaState.view());

        // for (int i = 0; i < listKhoa.size(); i++) {
        // ComboItem cobo = new ComboItem(listKhoa.get(i).getMa(),
        // listKhoa.get(i).getTenKhoa());
        // jComboBoxTenKhoa.addItem(cobo);
        // }

        jComboBoxTenKhoa.addActionListener(this);
        jButtonAddSinhvien.addActionListener(this);
        jButtonUpdateSinhvien.addActionListener(this);
        jButtonDeleteSinhvien.addActionListener(this);

        jPanelLeft.add(jLabelId);
        jPanelLeft.add(jTextFieldId);
        jPanelLeft.add(jLabelTenNganh);
        jPanelLeft.add(jTextFieldTenNganh);
        jPanelLeft.add(jLabelTenKhoa);
        jPanelLeft.add(jComboBoxTenKhoa);

        jPanelLeft.add(jButtonAddSinhvien);
        jPanelLeft.add(jButtonUpdateSinhvien);
        jPanelLeft.add(jButtonDeleteSinhvien);

        // right
        JPanel jPanelRight = new JPanel();
        jPanelRight.setLayout(new FlowLayout());
        jPanelRight.setPreferredSize(new DimensionUIResource(900, 550));
        jPanelRight.setBackground(Color.red);

        JLabel jLabelRight = new JLabel("Bang");
        this.list = sinhVienState.view();

        Object[][] row = new Object[list.size()][col.length];
        for (int i = 0; i < list.size(); i++) {
            row[i][0] = list.get(i).getMasv();
            row[i][1] = list.get(i).getTensv();
            row[i][2] = list.get(i).getHosv();
            row[i][3] = list.get(i).getGioiTinh();
            row[i][4] = list.get(i).getNgaysinh();
            row[i][5] = list.get(i).getNoisinh();
            row[i][6] = list.get(i).getDiachi();
            row[i][7] = list.get(i).getManganh();
            row[i][8] = list.get(i).getHocBong();
            row[i][9] = list.get(i).getAvatar();
            row[i][10] = list.get(i).getTennganh();
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
                // sinhVienState.insert(jTextFieldTenNganh.getText(),
                // ((ComboItem) jComboBoxTenKhoa.getSelectedItem()).getValue());

                // System.out.println(((ComboItem)
                // jComboBoxTenKhoa.getSelectedItem()).getValue());
                resetInput();
                renderTable();
                break;

            case "Xoa Nganh":
                sinhVienState.delete(Integer.parseInt(jTextFieldId.getText()));
                renderTable();
                resetInput();

                break;
            case "Cap nhat Nganh":
                sinhVienState.update(Integer.parseInt(jTextFieldId.getText()), jTextFieldTenNganh.getText(),
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
        list = sinhVienState.view();
        Object[][] row = new Object[list.size()][col.length];

        for (int i = 0; i < list.size(); i++) {
            row[i][0] = list.get(i).getMasv();
            row[i][1] = list.get(i).getTensv();
            row[i][2] = list.get(i).getHosv();
            row[i][3] = list.get(i).getGioiTinh();
            row[i][4] = list.get(i).getNgaysinh();
            row[i][5] = list.get(i).getNoisinh();
            row[i][6] = list.get(i).getDiachi();
            row[i][7] = list.get(i).getManganh();
            row[i][8] = list.get(i).getHocBong();
            row[i][9] = list.get(i).getAvatar();
            row[i][10] = list.get(i).getTennganh();
        }
        model = new DefaultTableModel(row, col);
        jTableNganh.setModel(model);
    }

}

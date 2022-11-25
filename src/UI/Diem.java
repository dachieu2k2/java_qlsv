package UI;

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

import State.DiemState;
import State.MonState;
import State.SinhVienState;
import UI.Components.ComboItem;
import UI.Components.Dialog;
import models.DiemModel;
import models.MonModel;
import models.SinhVienModel;

import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;

public class Diem implements ActionListener {
    private DiemState diemState = new DiemState();
    private ArrayList<DiemModel> list;
    // private KhoaState khoaState = new KhoaState();
    private MonState monState = new MonState();
    private SinhVienState sinhVienState = new SinhVienState();

    // ui
    JTextField jTextFieldTimkiem;
    JTextField jTextFieldDiem;

    DefaultTableModel model;
    JTable jTableNganh;
    JTextField jTextFieldTenNganh;
    JComboBox<ComboItem> jComboBoxTenMon;
    JComboBox<ComboItem> jComboBoxTenSinhvien;

    String[] col = { "masv", "mamon", "hosv", "tensv", "diem", "tenmon" };

    Dialog d;

    public JPanel render() {

        JPanel jLabelOutlet = new JPanel();
        jLabelOutlet.setLayout(new FlowLayout());
        jLabelOutlet.setPreferredSize(new DimensionUIResource(1200, 600));

        // Header
        JPanel jPanelHeader = new JPanel();
        jPanelHeader.setLayout(new FlowLayout());
        jPanelHeader.setPreferredSize(new DimensionUIResource(1200, 50));

        JLabel jLabel = new JLabel("Diem", SwingConstants.CENTER);
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
                    list = diemState.SearchText(s);
                    Object[][] row = new Object[list.size()][col.length];

                    for (int i = 0; i < list.size(); i++) {
                        row[i][0] = list.get(i).getMasv();
                        row[i][1] = list.get(i).getMamon();
                        row[i][2] = list.get(i).getHosv();
                        row[i][3] = list.get(i).getTensv();
                        row[i][4] = list.get(i).getDiem();
                        row[i][5] = list.get(i).getTenmon();

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
        JLabel jLabelTenNganh = new JLabel("Ten sinhvien: ");
        JLabel jLabelTenKhoa = new JLabel("Ten mon: ");
        JLabel jLabelDiem = new JLabel("diem: ");
        jPanelLeft.setPreferredSize(new DimensionUIResource(1200, 50));
        jPanelLeft.setBackground(Color.green);

        jTextFieldTenNganh = new JTextField();
        jTextFieldTenNganh.setPreferredSize(new DimensionUIResource(200, 20));

        jTextFieldDiem = new JTextField();
        jTextFieldDiem.setPreferredSize(new DimensionUIResource(200, 20));

        JButton jButtonAddNganh = new JButton("Them Diem");
        JButton jButtonUpdateNganh = new JButton("Cap nhat Diem");
        JButton jButtonDeleteNganh = new JButton("Xoa Diem");

        jComboBoxTenMon = new JComboBox<>();

        ArrayList<MonModel> listMon = new ArrayList<>(monState.view());

        for (int i = 0; i < listMon.size(); i++) {
            ComboItem cobo = new ComboItem(listMon.get(i).getMa(),
                    listMon.get(i).getTenMon());
            jComboBoxTenMon.addItem(cobo);
        }

        jComboBoxTenSinhvien = new JComboBox<>();
        ArrayList<SinhVienModel> lisSinhVien = new ArrayList<>(sinhVienState.view());

        for (int i = 0; i < lisSinhVien.size(); i++) {
            ComboItem cobo = new ComboItem(lisSinhVien.get(i).getMasv(),
                    lisSinhVien.get(i).getTensv());
            jComboBoxTenSinhvien.addItem(cobo);
        }

        jComboBoxTenMon.addActionListener(this);
        jComboBoxTenSinhvien.addActionListener(this);
        jButtonAddNganh.addActionListener(this);
        jButtonUpdateNganh.addActionListener(this);
        jButtonDeleteNganh.addActionListener(this);

        jPanelLeft.add(jLabelTenNganh);
        jPanelLeft.add(jComboBoxTenSinhvien);

        jPanelLeft.add(jLabelTenKhoa);
        jPanelLeft.add(jComboBoxTenMon);
        jPanelLeft.add(jLabelDiem);
        jPanelLeft.add(jTextFieldDiem);

        jPanelLeft.add(jButtonAddNganh);
        jPanelLeft.add(jButtonUpdateNganh);
        jPanelLeft.add(jButtonDeleteNganh);

        // right
        JPanel jPanelRight = new JPanel();
        jPanelRight.setLayout(new FlowLayout());
        jPanelRight.setPreferredSize(new DimensionUIResource(900, 550));
        jPanelRight.setBackground(Color.red);

        JLabel jLabelRight = new JLabel("Bang");
        this.list = diemState.view();

        Object[][] row = new Object[list.size()][col.length];

        for (int i = 0; i < list.size(); i++) {
            row[i][0] = list.get(i).getMasv();
            row[i][1] = list.get(i).getMamon();
            row[i][2] = list.get(i).getHosv();
            row[i][3] = list.get(i).getTensv();
            row[i][4] = list.get(i).getDiem();
            row[i][5] = list.get(i).getTenmon();
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
        int check = 0;
        switch (ae) {
            case "Them Diem":
                if (Double.parseDouble(jTextFieldDiem.getText()) > 10
                        || Double.parseDouble(jTextFieldDiem.getText()) < 0) {
                    d = new Dialog("Diem phai lon hon 0 va nho hon 10");
                    break;
                }
                check = 0;
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getMasv() == ((ComboItem) jComboBoxTenSinhvien.getSelectedItem()).getValue()
                            && list.get(i).getMamon() == ((ComboItem) jComboBoxTenMon.getSelectedItem()).getValue()) {
                        check = 1;
                        d = new Dialog("Sinh vien da co diem mon nay!");
                        break;
                    }
                }
                if (check == 1) {
                    break;
                } else {
                    diemState.insert(((ComboItem) jComboBoxTenSinhvien.getSelectedItem()).getValue(),
                            ((ComboItem) jComboBoxTenMon.getSelectedItem()).getValue(),
                            Double.parseDouble(jTextFieldDiem.getText()));
                    resetInput();
                    renderTable();
                }

                break;

            case "Xoa Diem":
                check = 0;
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getMasv() == ((ComboItem) jComboBoxTenSinhvien.getSelectedItem()).getValue()
                            && list.get(i).getMamon() == ((ComboItem) jComboBoxTenMon.getSelectedItem()).getValue()) {
                        diemState.delete(((ComboItem) jComboBoxTenSinhvien.getSelectedItem()).getValue(),
                                ((ComboItem) jComboBoxTenMon.getSelectedItem()).getValue());
                        renderTable();
                        resetInput();

                        check = 1;
                        d = new Dialog("Xoa thanh cong");
                        break;
                    }
                }
                if (check == 0) {
                    d = new Dialog("Khong tim thay sinh vien hoc mon hoc nay");
                    break;
                }

                break;
            case "Cap nhat Diem":
                if (Double.parseDouble(jTextFieldDiem.getText()) > 10
                        || Double.parseDouble(jTextFieldDiem.getText()) < 0) {
                    d = new Dialog("Diem phai lon hon 0 va nho hon 10");
                    break;
                }
                check = 0;
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getMasv() == ((ComboItem) jComboBoxTenSinhvien.getSelectedItem()).getValue()
                            && list.get(i).getMamon() == ((ComboItem) jComboBoxTenMon.getSelectedItem()).getValue()) {
                        diemState.update(((ComboItem) jComboBoxTenSinhvien.getSelectedItem()).getValue(),
                                ((ComboItem) jComboBoxTenMon.getSelectedItem()).getValue(),
                                Double.parseDouble(jTextFieldDiem.getText()));
                        // jTextFieldTenNganh.getText());
                        renderTable();
                        resetInput();
                        check = 1;
                        d = new Dialog("Xoa thanh cong");
                        break;
                    }
                }
                if (check == 0) {
                    d = new Dialog("Khong tim thay sinh vien hoc mon hoc nay");
                    break;
                }

                break;
            default:
                break;
        }

    }

    public void resetInput() {
        jTextFieldDiem.setText("");
        // jTextFieldTenNganh.setText("");
    }

    public void renderTable() {
        list = diemState.view();
        Object[][] row = new Object[list.size()][col.length];

        for (int i = 0; i < list.size(); i++) {
            row[i][0] = list.get(i).getMasv();
            row[i][1] = list.get(i).getMamon();
            row[i][2] = list.get(i).getHosv();
            row[i][3] = list.get(i).getTensv();
            row[i][4] = list.get(i).getDiem();
            row[i][5] = list.get(i).getTenmon();
        }
        model = new DefaultTableModel(row, col);
        jTableNganh.setModel(model);
    }
}

package UI;

import java.util.ArrayList;
import java.util.Properties;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.*;

import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.*;
import java.sql.Date;

import State.NganhState;
import State.SinhVienState;
import UI.Components.ComboItem;
import UI.Components.DateLabelFormatter;
import UI.Components.Dialog;
import models.NganhModel;
import models.SinhVienModel;

public class Sinhvien implements ActionListener {
    // data
    private SinhVienState sinhVienState = new SinhVienState();
    private ArrayList<SinhVienModel> list;
    private NganhState nganhState = new NganhState();

    // ui
    JTextField jTextFieldTimkiem;
    // "masv", "tensv", "hosv", "gioitinh", "ngaysinh",
    // "noisinh","diachi","manganh", "hocbong", "avatar", "tennganh"
    JTextField jTextFieldId;
    JTextField jTextFieldTenSv;
    JTextField jTextFieldHoSv;
    JTextField jTextFieldGioiTinh;
    JTextField jTextFieldNgaySinh;
    JTextField jTextFieldNoiSinh;
    JTextField jTextFieldDiaChi;
    JTextField jTextFieldMaNganh;
    JTextField jTextFieldHocBong;
    JTextField jTextFieldAvatar;

    JRadioButton jRadioButtonNam;
    JRadioButton jRadioButtonNu;

    JRadioButton jRadioButtonCo;
    JRadioButton jRadioButtonKhong;

    JComboBox<ComboItem> jComboBoxTenNganh;
    UtilDateModel modelDate;
    JDatePanelImpl datePanel;
    JDatePickerImpl datePicker;

    JTable jTableNganh;
    DefaultTableModel model;
    String[] col = { "masv", "tensv", "hosv", "gioitinh", "ngaysinh", "noisinh",
            "diachi",
            "manganh", "hocbong", "avatar", "tennganh" };

    Dialog d;

    public JPanel render() {

        JPanel jLabelOutlet = new JPanel();
        jLabelOutlet.setLayout(new FlowLayout());
        jLabelOutlet.setPreferredSize(new DimensionUIResource(1200, 600));

        // Header
        JPanel jPanelHeader = new JPanel();
        jPanelHeader.setLayout(new FlowLayout());
        jPanelHeader.setPreferredSize(new DimensionUIResource(1200, 50));

        JLabel jLabel = new JLabel("Sinh vien", SwingConstants.CENTER);
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
        // "masv", "tensv", "hosv", "gioitinh", "ngaysinh",
        // "noisinh","diachi","manganh", "hocbong", "avatar", "tennganh"
        JLabel jLabelId = new JLabel("ma: ");
        JLabel jLabeltensv = new JLabel("tensv: ");
        JLabel jLabelhosv = new JLabel("hosv: ");

        JLabel jLabelgioitinh = new JLabel("gioitinh: ");
        // JLabel jLabelgioitinhNam = new JLabel("nam: ");
        // JLabel jLabelgioitinhNu = new JLabel("nu: ");
        jRadioButtonNam = new JRadioButton("nam");
        jRadioButtonNu = new JRadioButton("nu");

        jRadioButtonCo = new JRadioButton("Co");
        jRadioButtonKhong = new JRadioButton("Khong");
        jRadioButtonKhong.setSelected(true);
        ButtonGroup groupButtonGioiTinh = new ButtonGroup();
        ButtonGroup groupButtonhocbong = new ButtonGroup();
        jRadioButtonNam.setSelected(true);
        groupButtonGioiTinh.add(jRadioButtonNam);
        groupButtonGioiTinh.add(jRadioButtonNu);
        groupButtonhocbong.add(jRadioButtonCo);
        groupButtonhocbong.add(jRadioButtonKhong);

        JLabel jLabelngaysinh = new JLabel("ngaysinh: ");
        JLabel jLabelnoisinh = new JLabel("noisinh: ");
        JLabel jLabeldiachi = new JLabel("diachi: ");
        // JLabel jLabelmanganh = new JLabel("manganh: ");
        JLabel jLabelhocbong = new JLabel("hocbong: ");
        JLabel jLabelavatar = new JLabel("avatar: ");
        JLabel jLabeltennganh = new JLabel("tennganh: ");
        jPanelLeft.setPreferredSize(new DimensionUIResource(1200, 100));
        jPanelLeft.setBackground(Color.green);

        jTextFieldTenSv = new JTextField();
        jTextFieldTenSv.setPreferredSize(new DimensionUIResource(200, 20));
        jTextFieldHoSv = new JTextField();
        jTextFieldHoSv.setPreferredSize(new DimensionUIResource(200, 20));
        jTextFieldGioiTinh = new JTextField();
        jTextFieldGioiTinh.setPreferredSize(new DimensionUIResource(200, 20));
        jTextFieldNgaySinh = new JTextField();
        jTextFieldNgaySinh.setPreferredSize(new DimensionUIResource(200, 20));
        jTextFieldNoiSinh = new JTextField();
        jTextFieldNoiSinh.setPreferredSize(new DimensionUIResource(200, 20));
        jTextFieldDiaChi = new JTextField();
        jTextFieldDiaChi.setPreferredSize(new DimensionUIResource(200, 20));
        jTextFieldMaNganh = new JTextField();
        jTextFieldMaNganh.setPreferredSize(new DimensionUIResource(200, 20));
        jTextFieldHocBong = new JTextField();
        jTextFieldHocBong.setPreferredSize(new DimensionUIResource(200, 20));
        jTextFieldAvatar = new JTextField();
        jTextFieldAvatar.setPreferredSize(new DimensionUIResource(200, 20));

        modelDate = new UtilDateModel();
        // modelDate.setDate(1990, 8, 24);
        modelDate.setSelected(true);
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");

        datePanel = new JDatePanelImpl(modelDate, p);

        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        jTextFieldId = new JTextField();
        jTextFieldId.setPreferredSize(new DimensionUIResource(200, 20));

        JButton jButtonAddSinhvien = new JButton("Them Sinhvien");
        JButton jButtonUpdateSinhvien = new JButton("Cap nhat Sinhvien");
        JButton jButtonDeleteSinhvien = new JButton("Xoa Sinhvien");

        // ArrayList<KhoaModel> listKhoa = new ArrayList<>(khoaState.view());

        // for (int i = 0; i < listKhoa.size(); i++) {
        // ComboItem cobo = new ComboItem(listKhoa.get(i).getMa(),
        // listKhoa.get(i).getTenKhoa());
        // jComboBoxTenKhoa.addItem(cobo);
        // }
        jComboBoxTenNganh = new JComboBox<>();

        ArrayList<NganhModel> listNganh = new ArrayList<>(nganhState.view());

        for (int i = 0; i < listNganh.size(); i++) {
            ComboItem cobo = new ComboItem(listNganh.get(i).getMa(), listNganh.get(i).getTenNganh());
            jComboBoxTenNganh.addItem(cobo);
        }

        jComboBoxTenNganh.addActionListener(this);

        jComboBoxTenNganh.addActionListener(this);
        jButtonAddSinhvien.addActionListener(this);
        jButtonUpdateSinhvien.addActionListener(this);
        jButtonDeleteSinhvien.addActionListener(this);

        // "masv", "tensv", "hosv", "gioitinh", "ngaysinh",
        // "noisinh","diachi","manganh", "hocbong", "avatar", "tennganh"
        jPanelLeft.add(jLabelId);
        jPanelLeft.add(jTextFieldId);

        jPanelLeft.add(jLabeltensv);
        jPanelLeft.add(jTextFieldTenSv);

        jPanelLeft.add(jLabelhosv);
        jPanelLeft.add(jTextFieldHoSv);

        jPanelLeft.add(jLabelgioitinh);
        // jPanelLeft.add(jTextFieldGioiTinh);
        // jPanelLeft.add(jLabelgioitinhNam);
        jPanelLeft.add(jRadioButtonNam);
        // jPanelLeft.add(jLabelgioitinhNam);
        jPanelLeft.add(jRadioButtonNu);

        jPanelLeft.add(jLabelngaysinh);
        // jPanelLeft.add(jTextFieldNgaySinh);
        jPanelLeft.add(datePicker);

        jPanelLeft.add(jLabelnoisinh);
        jPanelLeft.add(jTextFieldNoiSinh);

        jPanelLeft.add(jLabeldiachi);
        jPanelLeft.add(jTextFieldDiaChi);

        // jPanelLeft.add(jLabelmanganh);
        jPanelLeft.add(jLabeltennganh);
        // jPanelLeft.add(jTextFieldMaNganh);
        jPanelLeft.add(jComboBoxTenNganh);

        jPanelLeft.add(jLabelhocbong);
        // jPanelLeft.add(jTextFieldHocBong);

        jPanelLeft.add(jRadioButtonCo);
        jPanelLeft.add(jRadioButtonKhong);

        jPanelLeft.add(jLabelavatar);
        jPanelLeft.add(jTextFieldAvatar);

        // jPanelLeft.add(jLabeltennganh);
        // jPanelLeft.add(jtext);

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
        int check = 0;
        switch (ae) {
            case "Them Sinhvien":
                if (jTextFieldTenSv.getText().equals("") || jTextFieldHoSv.getText().equals("")) {
                    d = new Dialog("Can phai co ten sinh vien va ho sinh vien");
                    break;
                }

                sinhVienState.insert(jTextFieldTenSv.getText(), jTextFieldHoSv.getText(),
                        convertGioiTinh(),
                        Date.valueOf(datePicker.getJFormattedTextField().getText()),
                        jTextFieldNoiSinh.getText(), jTextFieldDiaChi.getText(),
                        ((ComboItem) jComboBoxTenNganh.getSelectedItem()).getValue(),
                        convertHocBong(),
                        jTextFieldAvatar.getText());

                resetInput();
                renderTable();
                break;

            case "Xoa Sinhvien":
                if (jTextFieldId.getText().equals("")) {
                    d = new Dialog("Can phai co id");
                    break;
                }
                check = 0;
                for (int i = 0; i < list.size(); i++) {
                    if (Integer.parseInt(jTextFieldId.getText()) == list.get(i).getMasv()) {
                        sinhVienState.delete(Integer.parseInt(jTextFieldId.getText()));
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
            case "Cap nhat Sinhvien":
                if (jTextFieldId.getText().equals("")) {
                    d = new Dialog("Can phai co id");
                    break;
                }
                if (jTextFieldTenSv.getText().equals("") || jTextFieldHoSv.getText().equals("")) {
                    d = new Dialog("Can phai co ten sinh vien va ho sinh vien");
                    break;
                }

                check = 0;
                for (int i = 0; i < list.size(); i++) {
                    if (Integer.parseInt(jTextFieldId.getText()) == list.get(i).getMasv()) {
                        sinhVienState.update(Integer.parseInt(jTextFieldId.getText()), jTextFieldTenSv.getText(),
                                jTextFieldHoSv.getText(),
                                convertGioiTinh(),
                                Date.valueOf(datePicker.getJFormattedTextField().getText()),
                                jTextFieldNoiSinh.getText(), jTextFieldDiaChi.getText(),
                                ((ComboItem) jComboBoxTenNganh.getSelectedItem()).getValue(),
                                convertHocBong(),
                                jTextFieldAvatar.getText());

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
        jTextFieldTenSv.setText("");
        jTextFieldHoSv.setText("");
        jTextFieldGioiTinh.setText("");
        jTextFieldNgaySinh.setText("");
        jTextFieldNoiSinh.setText("");
        jTextFieldDiaChi.setText("");
        jTextFieldMaNganh.setText("");
        jTextFieldHocBong.setText("");
        jTextFieldAvatar.setText("");
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

    int convertGioiTinh() {
        if (jRadioButtonNam.isSelected()) {
            return 0;
        }
        return 1;
    }

    int convertHocBong() {
        if (jRadioButtonCo.isSelected()) {
            return 1;
        }
        return 0;
    }
}

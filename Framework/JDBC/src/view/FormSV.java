package view;

import dao.SinhVienDao;
import model.Student;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class FormSV extends JFrame {
    private JTextField txtId, txtName, txtClassroom, txtGPA;
    private JTable table;
    private JButton btnShow, btnAdd, btnUpdate, btnRemove, btnReset;

    private SinhVienDao dao = new SinhVienDao();

    public FormSV() {
        setTitle("Quản lý Sinh viên - JDBC Demo");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lbMa = new JLabel("Mã SV:");
        lbMa.setBounds(30, 30, 80, 25);
        add(lbMa);
        txtId = new JTextField();
        txtId.setBounds(100, 30, 150, 25);
        add(txtId);

        JLabel lbTen = new JLabel("Họ tên:");
        lbTen.setBounds(30, 70, 80, 25);
        add(lbTen);
        txtName = new JTextField();
        txtName.setBounds(100, 70, 150, 25);
        add(txtName);

        JLabel lbLop = new JLabel("Lớp:");
        lbLop.setBounds(300, 30, 80, 25);
        add(lbLop);
        txtClassroom = new JTextField();
        txtClassroom.setBounds(350, 30, 150, 25);
        add(txtClassroom);

        JLabel lbGpa = new JLabel("GPA:");
        lbGpa.setBounds(300, 70, 80, 25);
        add(lbGpa);
        txtGPA = new JTextField();
        txtGPA.setBounds(350, 70, 150, 25);
        add(txtGPA);

        btnShow = new JButton("Hiển thị");
        btnAdd = new JButton("Thêm");
        btnUpdate = new JButton("Cập nhật");
        btnRemove = new JButton("Xóa");
        btnReset = new JButton("Reset");

        btnShow.setBounds(30, 120, 90, 30);
        btnAdd.setBounds(130, 120, 90, 30);
        btnUpdate.setBounds(230, 120, 100, 30);
        btnRemove.setBounds(340, 120, 80, 30);
        btnReset.setBounds(430, 120, 90, 30);

        add(btnShow);
        add(btnAdd);
        add(btnUpdate);
        add(btnRemove);
        add(btnReset);

        table = new JTable(new DefaultTableModel(new Object[]{"Mã SV", "Họ tên", "Lớp", "GPA"}, 0));
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(30, 170, 530, 170);
        add(scroll);

        btnShow.addActionListener(e -> loadData());
        btnAdd.addActionListener(e -> themSV());
        btnUpdate.addActionListener(e -> capNhatSV());
        btnRemove.addActionListener(e -> xoaSV());
        btnReset.addActionListener(e -> resetForm());

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                txtId.setText(table.getValueAt(row, 0).toString());
                txtName.setText(table.getValueAt(row, 1).toString());
                txtClassroom.setText(table.getValueAt(row, 2).toString());
                txtGPA.setText(table.getValueAt(row, 3).toString());
            }
        });
    }

    private void loadData() {
        List<Student> list = dao.getAll();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (Student sv : list) {
            model.addRow(new Object[]{sv.getId(), sv.getName(), sv.getClassroom(), sv.getGpa()});
        }
    }

    private void themSV() {
        try {
            Student sv = new Student(
                    txtId.getText(),
                    txtName.getText(),
                    txtClassroom.getText(),
                    Double.parseDouble(txtGPA.getText())
            );
            if (dao.insert(sv)) {
                JOptionPane.showMessageDialog(this, "✅ Thêm thành công!");
                loadData();
            } else {
                JOptionPane.showMessageDialog(this, "❌ Thêm thất bại (Mã trùng?)");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "⚠️ Dữ liệu không hợp lệ!");
        }
    }

    private void capNhatSV() {
        Student sv = new Student(
                txtId.getText(),
                txtName.getText(),
                txtClassroom.getText(),
                Double.parseDouble(txtGPA.getText())
        );
        if (dao.update(sv)) {
            JOptionPane.showMessageDialog(this, "✅ Cập nhật thành công!");
            loadData();
        }
    }

    private void xoaSV() {
        if (dao.delete(txtId.getText())) {
            JOptionPane.showMessageDialog(this, "🗑️ Xóa thành công!");
            loadData();
        }
    }

    private void resetForm() {
        txtId.setText("");
        txtName.setText("");
        txtClassroom.setText("");
        txtGPA.setText("");
    }
}

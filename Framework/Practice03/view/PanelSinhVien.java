package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import config.Database;

public class PanelSinhVien extends JPanel {
    private JTable tableSV;
    private DefaultTableModel modelSV;
    private JTextField txtTenSV, txtSdtSV, txtLopSV;

    public PanelSinhVien() {
        setLayout(new BorderLayout());
        initUI();
        loadTableSinhVien();
    }

    private void initUI() {
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Thông tin Sinh Viên"));

        txtTenSV = new JTextField();
        txtSdtSV = new JTextField();
        txtLopSV = new JTextField();

        formPanel.add(new JLabel("Họ tên:")); formPanel.add(txtTenSV);
        formPanel.add(new JLabel("Điện thoại:")); formPanel.add(txtSdtSV);
        formPanel.add(new JLabel("Lớp:")); formPanel.add(txtLopSV);

        JButton btnThem = new JButton("Thêm Mới");
        JButton btnSua = new JButton("Sửa Chọn");
        JPanel btnPanel = new JPanel();
        btnPanel.add(btnThem);
        btnPanel.add(btnSua);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(formPanel, BorderLayout.CENTER);
        topPanel.add(btnPanel, BorderLayout.SOUTH);

        String[] columns = {"Mã SV", "Họ Tên", "SĐT", "Lớp"};
        modelSV = new DefaultTableModel(columns, 0);
        tableSV = new JTable(modelSV);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(tableSV), BorderLayout.CENTER);

        tableSV.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = tableSV.getSelectedRow();
                if (row >= 0) {
                    txtTenSV.setText(modelSV.getValueAt(row, 1).toString());
                    txtSdtSV.setText(modelSV.getValueAt(row, 2).toString());
                    txtLopSV.setText(modelSV.getValueAt(row, 3).toString());
                }
            }
        });

        btnThem.addActionListener(e -> themSV());
        btnSua.addActionListener(e -> suaSV());
    }

    private void loadTableSinhVien() {
        modelSV.setRowCount(0);
        try (Connection conn = Database.getConnection()) {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM SINH_VIEN");
            while (rs.next()) {
                modelSV.addRow(new Object[]{
                        rs.getInt("ma_sv"), rs.getString("ho_ten"),
                        rs.getString("dien_thoai"), rs.getString("lop")
                });
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }

    private void themSV() {
        try (Connection conn = Database.getConnection()) {
            String sql = "INSERT INTO SINH_VIEN (ho_ten, dien_thoai, lop) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, txtTenSV.getText());
            ps.setString(2, txtSdtSV.getText());
            ps.setString(3, txtLopSV.getText());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Thêm thành công!");
            loadTableSinhVien();
        } catch (SQLException ex) { ex.printStackTrace(); }
    }

    private void suaSV() {
        int row = tableSV.getSelectedRow();
        if (row >= 0) {
            int maSV = Integer.parseInt(modelSV.getValueAt(row, 0).toString());
            try (Connection conn = Database.getConnection()) {
                String sql = "UPDATE SINH_VIEN SET ho_ten=?, dien_thoai=?, lop=? WHERE ma_sv=?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, txtTenSV.getText());
                ps.setString(2, txtSdtSV.getText());
                ps.setString(3, txtLopSV.getText());
                ps.setInt(4, maSV);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Sửa thành công!");
                loadTableSinhVien();
            } catch (SQLException ex) { ex.printStackTrace(); }
        }
    }
}
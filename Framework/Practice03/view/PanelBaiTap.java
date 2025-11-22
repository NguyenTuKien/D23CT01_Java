package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import config.Database;

public class PanelBaiTap extends JPanel {
    private JTable tableBT;
    private DefaultTableModel modelBT;
    private JTextField txtTenBT, txtThoiGianBT;
    private JComboBox<String> cbKieuBT;

    public PanelBaiTap() {
        setLayout(new BorderLayout());
        initUI();
        loadTableBaiTap();
    }

    private void initUI() {
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Thông tin Bài Tập"));

        txtTenBT = new JTextField();
        String[] kieu = {"BT nhỏ", "BT lớn", "BT chuyên đề"};
        cbKieuBT = new JComboBox<>(kieu);
        txtThoiGianBT = new JTextField();

        formPanel.add(new JLabel("Tên bài tập:")); formPanel.add(txtTenBT);
        formPanel.add(new JLabel("Kiểu bài tập:")); formPanel.add(cbKieuBT);
        formPanel.add(new JLabel("Tổng thời gian (ngày):")); formPanel.add(txtThoiGianBT);

        JButton btnThem = new JButton("Thêm Mới");
        JButton btnXoa = new JButton("Xóa Chọn");
        JPanel btnPanel = new JPanel();
        btnPanel.add(btnThem);
        btnPanel.add(btnXoa);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(formPanel, BorderLayout.CENTER);
        topPanel.add(btnPanel, BorderLayout.SOUTH);

        String[] columns = {"Mã BT", "Tên BT", "Kiểu BT", "Thời gian"};
        modelBT = new DefaultTableModel(columns, 0);
        tableBT = new JTable(modelBT);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(tableBT), BorderLayout.CENTER);

        // Events
        btnThem.addActionListener(e -> themBaiTap());
        btnXoa.addActionListener(e -> xoaBaiTap());
    }

    private void loadTableBaiTap() {
        modelBT.setRowCount(0);
        try (Connection conn = Database.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM BAI_TAP");
            while (rs.next()) {
                modelBT.addRow(new Object[]{
                        rs.getInt("ma_bt"), rs.getString("ten_bt"),
                        rs.getString("kieu_bt"), rs.getInt("tong_thoi_gian")
                });
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }

    private void themBaiTap() {
        try {
            if(txtTenBT.getText().isEmpty() || txtThoiGianBT.getText().isEmpty()) return;
            int time = Integer.parseInt(txtThoiGianBT.getText());
            try (Connection conn = Database.getConnection()) {
                String sql = "INSERT INTO BAI_TAP (ten_bt, kieu_bt, tong_thoi_gian) VALUES (?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, txtTenBT.getText());
                ps.setString(2, cbKieuBT.getSelectedItem().toString());
                ps.setInt(3, time);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Thêm thành công!");
                loadTableBaiTap();
            }
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    private void xoaBaiTap() {
        int row = tableBT.getSelectedRow();
        if (row >= 0) {
            int maBT = Integer.parseInt(modelBT.getValueAt(row, 0).toString());
            int confirm = JOptionPane.showConfirmDialog(this, "Xóa bài tập sẽ xóa cả phân công?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try (Connection conn = Database.getConnection()) {
                    conn.prepareStatement("DELETE FROM BAI_TAP WHERE ma_bt=" + maBT).executeUpdate();
                    loadTableBaiTap();
                } catch (SQLException ex) { ex.printStackTrace(); }
            }
        }
    }
}
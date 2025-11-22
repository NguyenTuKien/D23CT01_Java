package view;

import model.BaiTap;
import model.SinhVien;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import config.Database;

public class PanelPhanCong extends JPanel {
    private JTable tablePC;
    private DefaultTableModel modelPC;
    private JComboBox<SinhVien> cbChonSV;
    private JComboBox<BaiTap> cbChonBT;
    private JTextField txtSoNgayPC, txtViTriPC, txtTimKiemChung;
    private JComboBox<Object> cbTimKiemBT, cbTimKiemSV;

    public PanelPhanCong() {
        setLayout(new BorderLayout());
        initUI();
        loadTablePhanCong(null);
    }

    // Hàm này public để Main có thể gọi khi chuyển Tab (Refresh dữ liệu mới)
    public void refreshData() {
        updateComboBoxes();
        loadTablePhanCong(null);
    }

    private void initUI() {
        JPanel inputPanel = new JPanel(new GridLayout(3, 4, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Phân công"));
        cbChonSV = new JComboBox<>(); cbChonBT = new JComboBox<>();
        txtSoNgayPC = new JTextField(); txtViTriPC = new JTextField();

        inputPanel.add(new JLabel("Sinh Viên:")); inputPanel.add(cbChonSV);
        inputPanel.add(new JLabel("Bài Tập:")); inputPanel.add(cbChonBT);
        inputPanel.add(new JLabel("Số ngày:")); inputPanel.add(txtSoNgayPC);
        inputPanel.add(new JLabel("Vị trí:")); inputPanel.add(txtViTriPC);
        JButton btnPhanCong = new JButton("Lưu Phân Công");
        inputPanel.add(new JLabel("")); inputPanel.add(btnPhanCong);

        JPanel searchPanel = new JPanel(new GridLayout(2, 1));
        searchPanel.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));

        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtTimKiemChung = new JTextField(15);
        JButton btnTim = new JButton("Tìm chung");
        p1.add(new JLabel("Từ khóa:")); p1.add(txtTimKiemChung); p1.add(btnTim);

        JPanel p2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        cbTimKiemBT = new JComboBox<>(); cbTimKiemSV = new JComboBox<>();
        JButton btnLoc = new JButton("Lọc");
        JButton btnReset = new JButton("Reset");
        p2.add(new JLabel("BT:")); p2.add(cbTimKiemBT);
        p2.add(new JLabel("SV:")); p2.add(cbTimKiemSV);
        p2.add(btnLoc); p2.add(btnReset);

        searchPanel.add(p1); searchPanel.add(p2);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(inputPanel, BorderLayout.NORTH);
        topPanel.add(searchPanel, BorderLayout.SOUTH);

        modelPC = new DefaultTableModel(new String[]{"Tên SV", "Tên BT", "Số ngày", "Vị trí"}, 0);
        tablePC = new JTable(modelPC);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(tablePC), BorderLayout.CENTER);

        // Events
        btnPhanCong.addActionListener(e -> luuPhanCong());
        btnReset.addActionListener(e -> refreshData());
        // (Bạn có thể copy logic tìm kiếm cũ vào đây nếu cần)
        updateComboBoxes();
    }

    private void updateComboBoxes() {
        cbChonSV.removeAllItems(); cbTimKiemSV.removeAllItems(); cbTimKiemSV.addItem("Tất cả");
        cbChonBT.removeAllItems(); cbTimKiemBT.removeAllItems(); cbTimKiemBT.addItem("Tất cả");

        try (Connection conn = Database.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs1 = stmt.executeQuery("SELECT * FROM SINH_VIEN");
            while (rs1.next()) {
                SinhVien sv = new SinhVien(rs1.getInt("ma_sv"), rs1.getString("ho_ten"), "", "");
                cbChonSV.addItem(sv); cbTimKiemSV.addItem(sv.getHoTen());
            }
            ResultSet rs2 = stmt.executeQuery("SELECT * FROM BAI_TAP");
            while (rs2.next()) {
                BaiTap bt = new BaiTap(rs2.getInt("ma_bt"), rs2.getString("ten_bt"), "", rs2.getInt("tong_thoi_gian"));
                cbChonBT.addItem(bt); cbTimKiemBT.addItem(bt.getTenBT());
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }

    private void luuPhanCong() {
        // (Copy logic lưu phân công cũ vào đây)
        // Rút gọn để demo:
        try {
            SinhVien sv = (SinhVien) cbChonSV.getSelectedItem();
            BaiTap bt = (BaiTap) cbChonBT.getSelectedItem();
            int ngay = Integer.parseInt(txtSoNgayPC.getText());

            if (ngay > bt.getTongThoiGian()) {
                JOptionPane.showMessageDialog(this, "Số ngày quá lớn!"); return;
            }

            try (Connection conn = Database.getConnection()) {
                String sql = "INSERT INTO PHAN_CONG (ma_sv, ma_bt, so_ngay_tham_gia, vi_tri_cong_viec) VALUES (?, ?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, sv.getMaSV()); ps.setInt(2, bt.getMaBT());
                ps.setInt(3, ngay); ps.setString(4, txtViTriPC.getText());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Thành công");
                loadTablePhanCong(null);
            }
        } catch (Exception e) { JOptionPane.showMessageDialog(this, "Lỗi nhập liệu hoặc DB"); }
    }

    private void loadTablePhanCong(String sql) {
        modelPC.setRowCount(0);
        if (sql == null) sql = "SELECT sv.ho_ten, bt.ten_bt, pc.so_ngay_tham_gia, pc.vi_tri_cong_viec FROM PHAN_CONG pc JOIN SINH_VIEN sv ON pc.ma_sv = sv.ma_sv JOIN BAI_TAP bt ON pc.ma_bt = bt.ma_bt";

        try (Connection conn = Database.getConnection()) {
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                modelPC.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4)});
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }
}
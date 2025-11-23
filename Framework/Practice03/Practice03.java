import view.PanelBaiTap;
import view.PanelPhanCong;
import view.PanelSinhVien;

import javax.swing.*;

public class Practice03 extends JFrame {
    private JTabbedPane tabbedPane;
    private PanelBaiTap panelBaiTap;
    private PanelSinhVien panelSinhVien;
    private PanelPhanCong panelPhanCong;

    public Practice03() {
        setTitle("Quản Lý Bài Tập Nhóm - Java Swing (MVC)");
        setSize(950, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panelBaiTap = new PanelBaiTap();
        panelSinhVien = new PanelSinhVien();
        panelPhanCong = new PanelPhanCong();

        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("1. Quản lý Bài Tập", panelBaiTap);
        tabbedPane.addTab("2. Quản lý Sinh Viên", panelSinhVien);
        tabbedPane.addTab("3. Phân Công & Tìm Kiếm", panelPhanCong);

        tabbedPane.addChangeListener(e -> {
            if (tabbedPane.getSelectedIndex() == 2) {
                panelPhanCong.refreshData();
            }
        });

        add(tabbedPane);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {}

        SwingUtilities.invokeLater(() -> {
            new Practice03().setVisible(true);
        });
    }
}
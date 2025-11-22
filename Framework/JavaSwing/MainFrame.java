import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Hệ thống quản lý sinh viên - Nguyễn Tự Kiên");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Create tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();
        
        // Add panels
        StudentPanel studentPanel = new StudentPanel();
        CoursePanel coursePanel = new CoursePanel();
        GradePanel gradePanel = new GradePanel();
        
        tabbedPane.addTab("Quản lý Sinh viên", studentPanel);
        tabbedPane.addTab("Quản lý Môn học", coursePanel);
        tabbedPane.addTab("Quản lý Điểm", gradePanel);
        
        add(tabbedPane);
        
        // Set look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}

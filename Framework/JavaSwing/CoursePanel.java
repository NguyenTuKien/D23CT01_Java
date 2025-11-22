import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CoursePanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private List<Course> courses;
    
    private JTextField txtCourseName;
    private JTextField txtCredits;
    private JComboBox<String> cmbCourseType;
    private JButton btnAdd;
    private JButton btnDelete;
    private JButton btnRefresh;
    
    public CoursePanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Load data
        courses = DataManager.loadCourses();
        
        // Create input panel
        JPanel inputPanel = createInputPanel();
        add(inputPanel, BorderLayout.NORTH);
        
        // Create table
        String[] columnNames = {"Mã môn", "Tên môn", "Số tín chỉ", "Loại môn"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        
        // Load table data
        refreshTable();
        
        // Add listeners
        addListeners();
    }
    
    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Course Name
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Tên môn:"), gbc);
        gbc.gridx = 1;
        txtCourseName = new JTextField(20);
        panel.add(txtCourseName, gbc);
        
        // Credits
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Số tín chỉ:"), gbc);
        gbc.gridx = 1;
        txtCredits = new JTextField(20);
        panel.add(txtCredits, gbc);
        
        // Course Type
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Loại môn:"), gbc);
        gbc.gridx = 1;
        String[] courseTypes = {"Đại cương", "Cơ sở ngành", "Chuyên ngành"};
        cmbCourseType = new JComboBox<>(courseTypes);
        panel.add(cmbCourseType, gbc);
        
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnAdd = new JButton("Thêm");
        btnDelete = new JButton("Xóa");
        btnRefresh = new JButton("Làm mới");
        
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnRefresh);
        
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);
        
        return panel;
    }
    
    private void addListeners() {
        btnAdd.addActionListener(e -> addCourse());
        btnDelete.addActionListener(e -> deleteCourse());
        btnRefresh.addActionListener(e -> refreshForm());
        
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                loadSelectedCourse();
            }
        });
    }
    
    private void addCourse() {
        if (!validateForm()) {
            return;
        }
        
        String courseName = txtCourseName.getText().trim();
        int credits = Integer.parseInt(txtCredits.getText().trim());
        String courseType = (String) cmbCourseType.getSelectedItem();
        
        Course course = new Course(courseName, credits, courseType);
        courses.add(course);
        DataManager.saveCourses(courses);
        
        refreshTable();
        refreshForm();
        JOptionPane.showMessageDialog(this, "Thêm môn học thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void deleteCourse() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn môn học để xóa!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Bạn có chắc chắn muốn xóa môn học này?", 
            "Xác nhận", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            courses.remove(selectedRow);
            DataManager.saveCourses(courses);
            refreshTable();
            refreshForm();
            JOptionPane.showMessageDialog(this, "Xóa môn học thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void loadSelectedCourse() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            Course course = courses.get(selectedRow);
            txtCourseName.setText(course.getCourseName());
            txtCredits.setText(String.valueOf(course.getCredits()));
            cmbCourseType.setSelectedItem(course.getCourseType());
        }
    }
    
    private void refreshTable() {
        tableModel.setRowCount(0);
        for (Course course : courses) {
            Object[] row = {
                course.getCourseId(),
                course.getCourseName(),
                course.getCredits(),
                course.getCourseType()
            };
            tableModel.addRow(row);
        }
    }
    
    private void refreshForm() {
        txtCourseName.setText("");
        txtCredits.setText("");
        cmbCourseType.setSelectedIndex(0);
        table.clearSelection();
    }
    
    private boolean validateForm() {
        // Validate Course Name
        if (txtCourseName.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên môn học!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtCourseName.requestFocus();
            return false;
        }
        
        // Validate course name length
        if (txtCourseName.getText().trim().length() < 3) {
            JOptionPane.showMessageDialog(this, "Tên môn học phải có ít nhất 3 ký tự!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtCourseName.requestFocus();
            return false;
        }
        
        // Validate Credits
        if (txtCredits.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số tín chỉ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtCredits.requestFocus();
            return false;
        }
        
        try {
            int credits = Integer.parseInt(txtCredits.getText().trim());
            if (credits < 1 || credits > 10) {
                JOptionPane.showMessageDialog(this, "Số tín chỉ phải từ 1 đến 10!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                txtCredits.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Số tín chỉ phải là số nguyên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtCredits.requestFocus();
            return false;
        }
        
        return true;
    }
}

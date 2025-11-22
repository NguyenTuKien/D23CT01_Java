import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GradePanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private List<Grade> grades;
    private List<Student> students;
    private List<Course> courses;
    
    private JComboBox<Student> cmbStudent;
    private JComboBox<Course> cmbCourse;
    private JTextField txtScore;
    private JButton btnAdd;
    private JButton btnRefresh;
    
    private JRadioButton rbSortByName;
    private JRadioButton rbSortByScore;
    private JButton btnStatistics;
    
    public GradePanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Load data
        grades = DataManager.loadGrades();
        students = DataManager.loadStudents();
        courses = DataManager.loadCourses();
        
        // Create input panel
        JPanel inputPanel = createInputPanel();
        add(inputPanel, BorderLayout.NORTH);
        
        // Create table
        String[] columnNames = {"Mã SV", "Họ tên", "Mã môn", "Tên môn", "Điểm"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        
        // Create sort and statistics panel
        JPanel bottomPanel = createBottomPanel();
        add(bottomPanel, BorderLayout.SOUTH);
        
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
        
        // Student
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Sinh viên:"), gbc);
        gbc.gridx = 1;
        cmbStudent = new JComboBox<>();
        updateStudentComboBox();
        panel.add(cmbStudent, gbc);
        
        // Course
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Môn học:"), gbc);
        gbc.gridx = 1;
        cmbCourse = new JComboBox<>();
        updateCourseComboBox();
        panel.add(cmbCourse, gbc);
        
        // Score
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Điểm (0-10):"), gbc);
        gbc.gridx = 1;
        txtScore = new JTextField(20);
        panel.add(txtScore, gbc);
        
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnAdd = new JButton("Thêm/Cập nhật");
        btnRefresh = new JButton("Làm mới");
        
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnRefresh);
        
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);
        
        return panel;
    }
    
    private JPanel createBottomPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        panel.add(new JLabel("Sắp xếp theo:"));
        
        rbSortByName = new JRadioButton("Họ tên sinh viên");
        rbSortByScore = new JRadioButton("Điểm (giảm dần)");
        
        ButtonGroup group = new ButtonGroup();
        group.add(rbSortByName);
        group.add(rbSortByScore);
        
        panel.add(rbSortByName);
        panel.add(rbSortByScore);
        
        btnStatistics = new JButton("Thống kê");
        panel.add(btnStatistics);
        
        return panel;
    }
    
    private void addListeners() {
        btnAdd.addActionListener(e -> addOrUpdateGrade());
        btnRefresh.addActionListener(e -> refreshForm());
        rbSortByName.addActionListener(e -> sortByName());
        rbSortByScore.addActionListener(e -> sortByScore());
        btnStatistics.addActionListener(e -> showStatistics());
    }
    
    private void addOrUpdateGrade() {
        if (!validateForm()) {
            return;
        }
        
        Student selectedStudent = (Student) cmbStudent.getSelectedItem();
        Course selectedCourse = (Course) cmbCourse.getSelectedItem();
        double score = Double.parseDouble(txtScore.getText().trim());
        
        if (selectedStudent == null || selectedCourse == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sinh viên và môn học!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int studentId = selectedStudent.getStudentId();
        int courseId = selectedCourse.getCourseId();
        
        // Check if grade already exists
        Grade existingGrade = null;
        for (Grade grade : grades) {
            if (grade.getStudentId() == studentId && grade.getCourseId() == courseId) {
                existingGrade = grade;
                break;
            }
        }
        
        if (existingGrade != null) {
            // Update only if new score is higher
            if (score > existingGrade.getScore()) {
                existingGrade.setScore(score);
                JOptionPane.showMessageDialog(this, "Cập nhật điểm thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Điểm mới không cao hơn điểm cũ!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            // Add new grade
            Grade newGrade = new Grade(studentId, courseId, score);
            grades.add(newGrade);
            JOptionPane.showMessageDialog(this, "Thêm điểm thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
        }
        
        DataManager.saveGrades(grades);
        refreshTable();
        refreshForm();
    }
    
    private void sortByName() {
        List<GradeInfo> gradeInfoList = getGradeInfoList();
        gradeInfoList.sort(Comparator.comparing(g -> g.studentName));
        displayGradeInfoList(gradeInfoList);
    }
    
    private void sortByScore() {
        List<GradeInfo> gradeInfoList = getGradeInfoList();
        gradeInfoList.sort((g1, g2) -> Double.compare(g2.score, g1.score));
        displayGradeInfoList(gradeInfoList);
    }
    
    private List<GradeInfo> getGradeInfoList() {
        List<GradeInfo> gradeInfoList = new ArrayList<>();
        for (Grade grade : grades) {
            Student student = findStudentById(grade.getStudentId());
            Course course = findCourseById(grade.getCourseId());
            if (student != null && course != null) {
                gradeInfoList.add(new GradeInfo(
                    grade.getStudentId(),
                    student.getFullName(),
                    grade.getCourseId(),
                    course.getCourseName(),
                    grade.getScore()
                ));
            }
        }
        return gradeInfoList;
    }
    
    private void displayGradeInfoList(List<GradeInfo> gradeInfoList) {
        tableModel.setRowCount(0);
        for (GradeInfo info : gradeInfoList) {
            Object[] row = {info.studentId, info.studentName, info.courseId, info.courseName, info.score};
            tableModel.addRow(row);
        }
    }
    
    private void showStatistics() {
        if (courses.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không có môn học nào!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        StringBuilder statistics = new StringBuilder();
        statistics.append("THỐNG KÊ ĐIỂM THEO MÔN HỌC\n\n");
        
        for (Course course : courses) {
            int passed = 0;
            int failed = 0;
            
            for (Grade grade : grades) {
                if (grade.getCourseId() == course.getCourseId()) {
                    if (grade.getScore() >= 5.0) {
                        passed++;
                    } else {
                        failed++;
                    }
                }
            }
            
            statistics.append(String.format("Môn: %s (Mã: %d)\n", course.getCourseName(), course.getCourseId()));
            statistics.append(String.format("  - Qua môn (điểm >= 5): %d sinh viên\n", passed));
            statistics.append(String.format("  - Chưa qua môn: %d sinh viên\n\n", failed));
        }
        
        JTextArea textArea = new JTextArea(statistics.toString());
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 400));
        
        JOptionPane.showMessageDialog(this, scrollPane, "Thống kê", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void updateStudentComboBox() {
        cmbStudent.removeAllItems();
        for (Student student : students) {
            cmbStudent.addItem(student);
        }
    }
    
    private void updateCourseComboBox() {
        cmbCourse.removeAllItems();
        for (Course course : courses) {
            cmbCourse.addItem(course);
        }
    }
    
    private Student findStudentById(int studentId) {
        for (Student student : students) {
            if (student.getStudentId() == studentId) {
                return student;
            }
        }
        return null;
    }
    
    private Course findCourseById(int courseId) {
        for (Course course : courses) {
            if (course.getCourseId() == courseId) {
                return course;
            }
        }
        return null;
    }
    
    private void refreshTable() {
        tableModel.setRowCount(0);
        for (Grade grade : grades) {
            Student student = findStudentById(grade.getStudentId());
            Course course = findCourseById(grade.getCourseId());
            
            if (student != null && course != null) {
                Object[] row = {
                    grade.getStudentId(),
                    student.getFullName(),
                    grade.getCourseId(),
                    course.getCourseName(),
                    grade.getScore()
                };
                tableModel.addRow(row);
            }
        }
    }
    
    private void refreshForm() {
        txtScore.setText("");
        if (cmbStudent.getItemCount() > 0) {
            cmbStudent.setSelectedIndex(0);
        }
        if (cmbCourse.getItemCount() > 0) {
            cmbCourse.setSelectedIndex(0);
        }
        table.clearSelection();
        ButtonGroup group = new ButtonGroup();
        group.clearSelection();
        rbSortByName.setSelected(false);
        rbSortByScore.setSelected(false);
        refreshTable();
    }
    
    private boolean validateForm() {
        // Validate Student selection
        if (cmbStudent.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sinh viên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Validate Course selection
        if (cmbCourse.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn môn học!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Validate Score
        if (txtScore.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập điểm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtScore.requestFocus();
            return false;
        }
        
        try {
            double score = Double.parseDouble(txtScore.getText().trim());
            if (score < 0 || score > 10) {
                JOptionPane.showMessageDialog(this, "Điểm phải từ 0 đến 10!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                txtScore.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Điểm phải là số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtScore.requestFocus();
            return false;
        }
        
        return true;
    }
    
    // Helper class for sorting and displaying grades
    private static class GradeInfo {
        int studentId;
        String studentName;
        int courseId;
        String courseName;
        double score;
        
        GradeInfo(int studentId, String studentName, int courseId, String courseName, double score) {
            this.studentId = studentId;
            this.studentName = studentName;
            this.courseId = courseId;
            this.courseName = courseName;
            this.score = score;
        }
    }
}

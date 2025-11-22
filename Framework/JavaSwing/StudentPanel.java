import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class StudentPanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private List<Student> students;
    
    private JTextField txtFullName;
    private JTextField txtDateOfBirth;
    private JComboBox<String> cmbMajor;
    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnRefresh;
    
    public StudentPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Load data
        students = DataManager.loadStudents();
        
        // Create input panel
        JPanel inputPanel = createInputPanel();
        add(inputPanel, BorderLayout.NORTH);
        
        // Create table
        String[] columnNames = {"Mã SV", "Họ tên", "Ngày sinh", "Chuyên ngành"};
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
        
        // Full Name
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Họ tên:"), gbc);
        gbc.gridx = 1;
        txtFullName = new JTextField(20);
        panel.add(txtFullName, gbc);
        
        // Date of Birth
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Ngày sinh (dd/MM/yyyy):"), gbc);
        gbc.gridx = 1;
        txtDateOfBirth = new JTextField(20);
        panel.add(txtDateOfBirth, gbc);
        
        // Major
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Chuyên ngành:"), gbc);
        gbc.gridx = 1;
        String[] majors = {"Công nghệ phần mềm", "Hệ thống thông tin", "An toàn thông tin", "Đa phương tiện"};
        cmbMajor = new JComboBox<>(majors);
        panel.add(cmbMajor, gbc);
        
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnAdd = new JButton("Thêm");
        btnEdit = new JButton("Sửa");
        btnRefresh = new JButton("Làm mới");
        
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnRefresh);
        
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);
        
        return panel;
    }
    
    private void addListeners() {
        btnAdd.addActionListener(e -> addStudent());
        btnEdit.addActionListener(e -> editStudent());
        btnRefresh.addActionListener(e -> refreshForm());
        
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                loadSelectedStudent();
            }
        });
    }
    
    private void addStudent() {
        if (!validateForm()) {
            return;
        }
        
        try {
            String fullName = txtFullName.getText().trim();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dateOfBirth = sdf.parse(txtDateOfBirth.getText().trim());
            String major = (String) cmbMajor.getSelectedItem();
            
            Student student = new Student(fullName, dateOfBirth, major);
            students.add(student);
            DataManager.saveStudents(students);
            
            refreshTable();
            refreshForm();
            JOptionPane.showMessageDialog(this, "Thêm sinh viên thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Ngày sinh không đúng định dạng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void editStudent() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sinh viên để sửa!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (!validateForm()) {
            return;
        }
        
        try {
            String fullName = txtFullName.getText().trim();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dateOfBirth = sdf.parse(txtDateOfBirth.getText().trim());
            String major = (String) cmbMajor.getSelectedItem();
            
            Student student = students.get(selectedRow);
            student.setFullName(fullName);
            student.setDateOfBirth(dateOfBirth);
            student.setMajor(major);
            
            DataManager.saveStudents(students);
            refreshTable();
            JOptionPane.showMessageDialog(this, "Sửa sinh viên thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Ngày sinh không đúng định dạng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void loadSelectedStudent() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            Student student = students.get(selectedRow);
            txtFullName.setText(student.getFullName());
            txtDateOfBirth.setText(student.getDateOfBirthString());
            cmbMajor.setSelectedItem(student.getMajor());
        }
    }
    
    private void refreshTable() {
        tableModel.setRowCount(0);
        for (Student student : students) {
            Object[] row = {
                student.getStudentId(),
                student.getFullName(),
                student.getDateOfBirthString(),
                student.getMajor()
            };
            tableModel.addRow(row);
        }
    }
    
    private void refreshForm() {
        txtFullName.setText("");
        txtDateOfBirth.setText("");
        cmbMajor.setSelectedIndex(0);
        table.clearSelection();
    }
    
    private boolean validateForm() {
        // Validate Full Name
        if (txtFullName.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập họ tên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtFullName.requestFocus();
            return false;
        }
        
        // Validate name contains only letters and spaces
        if (!txtFullName.getText().trim().matches("^[a-zA-ZÀ-ỹ\\s]+$")) {
            JOptionPane.showMessageDialog(this, "Họ tên chỉ được chứa chữ cái!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtFullName.requestFocus();
            return false;
        }
        
        // Validate Date of Birth
        if (txtDateOfBirth.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày sinh!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtDateOfBirth.requestFocus();
            return false;
        }
        
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            Date dob = sdf.parse(txtDateOfBirth.getText().trim());
            
            // Check if date is not in the future
            if (dob.after(new Date())) {
                JOptionPane.showMessageDialog(this, "Ngày sinh không được trong tương lai!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                txtDateOfBirth.requestFocus();
                return false;
            }
            
            // Check if student is at least 15 years old
            long ageInMillis = new Date().getTime() - dob.getTime();
            int age = (int) (ageInMillis / (1000L * 60 * 60 * 24 * 365));
            if (age < 15) {
                JOptionPane.showMessageDialog(this, "Sinh viên phải ít nhất 15 tuổi!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                txtDateOfBirth.requestFocus();
                return false;
            }
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Ngày sinh không đúng định dạng (dd/MM/yyyy)!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtDateOfBirth.requestFocus();
            return false;
        }
        
        return true;
    }
}

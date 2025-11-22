import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int idCounter = 10000;
    
    private int studentId;
    private String fullName;
    private Date dateOfBirth;
    private String major;
    
    public Student(String fullName, Date dateOfBirth, String major) {
        this.studentId = ++idCounter;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.major = major;
    }
    
    // Getters and Setters
    public int getStudentId() {
        return studentId;
    }
    
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    
    public String getFullName() {
        return fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    public String getMajor() {
        return major;
    }
    
    public void setMajor(String major) {
        this.major = major;
    }
    
    public String getDateOfBirthString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(dateOfBirth);
    }
    
    public static void setIdCounter(int counter) {
        idCounter = counter;
    }
    
    public static int getIdCounter() {
        return idCounter;
    }
    
    @Override
    public String toString() {
        return studentId + " - " + fullName;
    }
}

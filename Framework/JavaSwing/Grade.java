import java.io.Serializable;

public class Grade implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int studentId;
    private int courseId;
    private double score;
    
    public Grade(int studentId, int courseId, double score) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.score = score;
    }
    
    // Getters and Setters
    public int getStudentId() {
        return studentId;
    }
    
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    
    public int getCourseId() {
        return courseId;
    }
    
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
    
    public double getScore() {
        return score;
    }
    
    public void setScore(double score) {
        this.score = score;
    }
}

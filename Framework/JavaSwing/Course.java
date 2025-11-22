import java.io.Serializable;

public class Course implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int idCounter = 100;
    
    private int courseId;
    private String courseName;
    private int credits;
    private String courseType;
    
    public Course(String courseName, int credits, String courseType) {
        this.courseId = ++idCounter;
        this.courseName = courseName;
        this.credits = credits;
        this.courseType = courseType;
    }
    
    // Getters and Setters
    public int getCourseId() {
        return courseId;
    }
    
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
    
    public String getCourseName() {
        return courseName;
    }
    
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    
    public int getCredits() {
        return credits;
    }
    
    public void setCredits(int credits) {
        this.credits = credits;
    }
    
    public String getCourseType() {
        return courseType;
    }
    
    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }
    
    public static void setIdCounter(int counter) {
        idCounter = counter;
    }
    
    public static int getIdCounter() {
        return idCounter;
    }
    
    @Override
    public String toString() {
        return courseId + " - " + courseName;
    }
}

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static final String STUDENT_FILE = "SV.DAT";
    private static final String COURSE_FILE = "MH.DAT";
    private static final String GRADE_FILE = "BDIEM.DAT";
    
    // Student operations
    public static void saveStudents(List<Student> students) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(STUDENT_FILE))) {
            oos.writeObject(students);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    public static List<Student> loadStudents() {
        File file = new File(STUDENT_FILE);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(STUDENT_FILE))) {
            List<Student> students = (List<Student>) ois.readObject();
            // Update counter
            if (!students.isEmpty()) {
                int maxId = students.stream()
                    .mapToInt(Student::getStudentId)
                    .max()
                    .orElse(10000);
                Student.setIdCounter(maxId);
            }
            return students;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    
    // Course operations
    public static void saveCourses(List<Course> courses) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(COURSE_FILE))) {
            oos.writeObject(courses);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    public static List<Course> loadCourses() {
        File file = new File(COURSE_FILE);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(COURSE_FILE))) {
            List<Course> courses = (List<Course>) ois.readObject();
            // Update counter
            if (!courses.isEmpty()) {
                int maxId = courses.stream()
                    .mapToInt(Course::getCourseId)
                    .max()
                    .orElse(100);
                Course.setIdCounter(maxId);
            }
            return courses;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    
    // Grade operations
    public static void saveGrades(List<Grade> grades) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(GRADE_FILE))) {
            oos.writeObject(grades);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    public static List<Grade> loadGrades() {
        File file = new File(GRADE_FILE);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(GRADE_FILE))) {
            return (List<Grade>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}

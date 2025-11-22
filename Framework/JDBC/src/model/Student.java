package model;

public class Student {
    private String id, name, classroom;
    private double gpa;

    public Student(String id, String name, String classroom, double gpa) {
        this.id = id;
        this.name = name;
        this.classroom = classroom;
        this.gpa = gpa;
    }
    public String getId() {
        return id;
    }
    public String getName(){
        return name;
    }
    public String getClassroom() {
        return classroom;
    }
    public double getGpa() {
        return gpa;
    }
    public void setId (String id) {
        this.id = id;
    }
    public void setName (String name) {
        this.name = name;
    }
    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
}

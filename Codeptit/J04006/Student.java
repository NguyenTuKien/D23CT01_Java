package J04006;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Student {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private final String id = "B20DCCN001";
    private String name;
    private String clss;
    private Date dob;
    private float gpa;

    Student (String name, String clss, String dob, float gpa) throws Exception {
        this.name = name;
        this.clss = clss;
        this.dob = sdf.parse(dob);
        this.gpa = gpa;
    }

    @Override
    public String toString(){
        return id + " " + name + " " + clss + " " + sdf.format(dob) + " " + String.format("%.2f", gpa);
    }
}

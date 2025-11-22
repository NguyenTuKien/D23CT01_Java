package J07058;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception{
        Scanner cin = new Scanner(new File("MONHOC.in"));
        int n = cin.nextInt();
        cin.nextLine();
        ArrayList <Subject> subjects = new ArrayList<>();
        for (int i = 0; i < n; ++i){
            String id = cin.nextLine();
            String name = cin.nextLine();
            String method = cin.nextLine();
            subjects.add(new Subject(id, name, method));
        }
        Collections.sort(subjects);
        for (Subject subject : subjects){
            System.out.println(subject.toString());
        }
    }
}

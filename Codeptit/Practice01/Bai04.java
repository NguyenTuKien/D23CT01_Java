package Practice01;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class Bai04 {

    public static boolean isThuanNghich(int n) {
        String s = String.valueOf(n);
        String reversed = new StringBuilder(s).reverse().toString();
        return s.equals(reversed);
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("DAYSO.DAT");
        ObjectInputStream ois = new ObjectInputStream(fis);

        ArrayList<Integer> arr = (ArrayList<Integer>) ois.readObject();
        
        ois.close();
        fis.close();

        Set<Integer> results = new TreeSet<>();
        
        for (int num : arr) {
            if (num >= 100 && isThuanNghich(num)) {
                results.add(num);
            }
        }

        for (int num : results) {
            System.out.println(num);
        }
    }
}
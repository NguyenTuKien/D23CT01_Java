package Practice01;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Bai06 {

    public static boolean isNonDecreasing(int n) {
        String s = String.valueOf(n);
        if (s.length() < 2) {
            return false;
        }
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i + 1) < s.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FileInputStream fis1 = new FileInputStream("DATA1.in");
        ObjectInputStream ois1 = new ObjectInputStream(fis1);
        ArrayList<Integer> list1 = (ArrayList<Integer>) ois1.readObject();
        ois1.close();
        fis1.close();

        FileInputStream fis2 = new FileInputStream("DATA2.in");
        ObjectInputStream ois2 = new ObjectInputStream(fis2);
        ArrayList<Integer> list2 = (ArrayList<Integer>) ois2.readObject();
        ois2.close();
        fis2.close();

        Map<Integer, Integer> map1 = new TreeMap<>();
        for (int num : list1) {
            if (isNonDecreasing(num)) {
                map1.put(num, map1.getOrDefault(num, 0) + 1);
            }
        }

        Map<Integer, Integer> map2 = new TreeMap<>();
        for (int num : list2) {
            if (isNonDecreasing(num)) {
                map2.put(num, map2.getOrDefault(num, 0) + 1);
            }
        }
        
        for (int num : map1.keySet()) {
            if (map2.containsKey(num)) {
                System.out.println(num + " " + map1.get(num) + " " + map2.get(num));
            }
        }
    }
}
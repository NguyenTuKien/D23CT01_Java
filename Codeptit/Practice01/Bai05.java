package Practice01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Bai05 {

    private static boolean hasDigit(String s) {
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    private static boolean containsInvalidPunctuation(String s) {
        for (char c : s.toCharArray()) {
            if (c == ',' || c == '.' || c == '?' || c == '!' || c == ':') {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("VANBAN.in"));
        Set<String> set = new TreeSet<>();

        while (sc.hasNext()) {
            String word = sc.next();
            if (hasDigit(word) && !containsInvalidPunctuation(word)) {
                set.add(word);
            }
        }

        for (String s : set) {
            System.out.println(s);
        }

        sc.close();
    }
}
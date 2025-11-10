import java.util.Scanner;
import java.util.Stack;

public class J03027 {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        String s = cin.next();
        Stack<Character> st = new Stack<>();
        for (char c : s.toCharArray()) {
            if(st.isEmpty() || st.peek() != c) st.push(c);
            else st.pop();
        }
        if(st.isEmpty()) System.out.println("Empty String");
        else {
            StringBuilder ans = new StringBuilder();
            while(!st.isEmpty()) ans.append(st.pop());
            ans.reverse();
            System.out.println(ans.toString());
        }
    }
}

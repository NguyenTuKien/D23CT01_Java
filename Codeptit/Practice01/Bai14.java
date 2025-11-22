package Practice01;

import java.util.Scanner;
import java.util.Stack;
import java.util.ArrayList;

public class Bai14 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        scanner.nextLine();
        while (t-- > 0) {
            String exp = scanner.nextLine();
            Stack<Integer> st = new Stack<>();
            int cnt = 0;
            ArrayList<Integer> res = new ArrayList<>();
            for (char c : exp.toCharArray()) {
                if (c == '(') {
                    cnt++;
                    st.push(cnt);
                    res.add(cnt);
                } else if (c == ')') {
                    int cur = st.pop();
                    res.add(cur);
                }
            }
            for (int i = 0; i < res.size(); ++i) {
                System.out.print(res.get(i) + (i == res.size() - 1 ? "" : " "));
            }
            System.out.println();
        }
    }
}
//Cho một biểu thức đúng, tức là các dấu ngoặc đơn đều đầy đủ mở và đóng, đảm bảo đúng thứ tự. Hãy viết chương trình đánh số các cặp dấu ngoặc theo thứ tự xuất hiện, tính từ 1.
//
//Ví dụ với biểu thức                                              (a + (b *c) ) + (d/e)
//
//ta có thứ tự của các cặp ‘(‘, ‘)’ là                         1  2  2  1  3  3
//
//Input:
//
//Dòng đầu tiên đưa vào số lượng bộ test T (không quá 100).
//Những dòng kế tiếp đưa vào các bộ test. Mỗi bộ test là một biểu thức số học được đưa vào trên một dòng, độ dài không quá 106.
//Output:
//
//Đưa ra kết quả mỗi test theo từng dòng.
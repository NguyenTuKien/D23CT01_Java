package Practice01.Bai11;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        List<CongNhan> ds = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String ma = sc.nextLine().trim();
            String ten = sc.nextLine().trim();
            String vao = sc.nextLine().trim();
            String ra = sc.nextLine().trim();
            ds.add(new CongNhan(ma, ten, vao, ra));
        }

        Collections.sort(ds);

        for (CongNhan cn : ds) {
            System.out.println(cn);
        }
    }
}

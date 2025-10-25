import java.io.*;
import java.util.*;
public class Main {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0){
            Map <Integer, Integer> idx = new HashMap<>();
            Map <Integer, Integer> freq = new HashMap<>();
            List <Integer> list = new ArrayList<>();
            int n = sc.nextInt();
            for(int i = 0; i < n; i++){
                int a = sc.nextInt();
                list.add(a);
                if(!idx.containsKey(a)) idx.put(a, i);
                freq.put(a, freq.getOrDefault(a, 0) + 1);
            }
            Collections.sort(list, (a, b) -> {
                if(freq.get(a) == freq.get(b)) return idx.get(a) - idx.get(b);
                return freq.get(b) - freq.get(a);
            });
            for (int num : list) {
                System.out.print(num + " ");
            }
        }
    }

}
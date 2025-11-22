package Practice01;

import java.util.*;

public class Bai15 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine(); 

        Set<String> classNames = new HashSet<>();
        
        List<String[]> relations = new ArrayList<>();
        List<String[]> queries = new ArrayList<>();

        // Đọc n quan hệ
        for (int i = 0; i < n; i++) {
            String[] parts = sc.nextLine().split(" ");
            relations.add(parts);
            classNames.add(parts[0]); 
            classNames.add(parts[2]); 
        }

        for (int i = 0; i < m; i++) {
            String[] parts = sc.nextLine().split(" ");
            queries.add(parts);
            classNames.add(parts[0]);
            classNames.add(parts[2]);
        }

        int V = classNames.size(); 
        
        Map<String, Integer> idMap = new HashMap<>();
        
        int id = 0;
        for (String className : classNames) {
            idMap.put(className, id);
            id++;
        }

        boolean[][] is_a = new boolean[V][V];
        boolean[][] has_a = new boolean[V][V];

        for (int i = 0; i < V; i++) {
            is_a[i][i] = true;
        }

        for (String[] rel : relations) {
            int idA = idMap.get(rel[0]);
            int idB = idMap.get(rel[2]);
            String op = rel[1];

            if (op.equals("is-a")) {
                is_a[idA][idB] = true;
            } else if (op.equals("has-a")) {
                has_a[idA][idB] = true;
            }
        }

        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) { 
                for (int j = 0; j < V; j++) { 

                    if (is_a[i][k] && is_a[k][j]) {
                        is_a[i][j] = true;
                    }

                    if (has_a[i][k] && has_a[k][j]) {
                        has_a[i][j] = true;
                    }

                    if (is_a[i][k] && has_a[k][j]) {
                        has_a[i][j] = true;
                    }

                    if (has_a[i][k] && is_a[k][j]) {
                        has_a[i][j] = true;
                    }
                }
            }
        }

        for (int i = 0; i < m; i++) {
            String[] query = queries.get(i);
            int idA = idMap.get(query[0]);
            int idB = idMap.get(query[2]);
            String op = query[1];

            boolean result = false;
            if (op.equals("is-a")) {
                result = is_a[idA][idB];
            } else if (op.equals("has-a")) {
                result = has_a[idA][idB];
            }

            System.out.println("Query " + (i + 1) + ": " + result);
        }

        sc.close();
    }
}
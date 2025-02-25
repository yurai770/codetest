import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] values;
    static List<Map<Integer, Integer>> nodes;
    static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int V = Integer.parseInt(bf.readLine());
        nodes = new ArrayList<>();
        values = new int[V + 1];
        for (int i = 0; i <= V; i++) {
            nodes.add(new HashMap<>());
            values[i] = -1;
        }
        for (int i = 0; i < V - 1; i++) {
            int[] lines = listConvert(bf.readLine().split(" "));
            nodes.get(lines[0]).put(lines[1], lines[2]);
        }

        int fans = findAnswer(1);
        if (answer < fans) {
            answer = fans;
        }
        System.out.println(answer);
    }

    private static int findAnswer(int idx) {
        if (values[idx] != -1) {
            return values[idx];
        }

        Map<Integer, Integer> edges = nodes.get(idx);
        if (edges.isEmpty()) {
            values[idx] = 0;
            return values[idx];
        }

        if (edges.size() == 1) {
            for (int key : edges.keySet()) {
                int value = edges.get(key) + findAnswer(key);
                if (values[idx] < value) {
                    values[idx] = value;
                }
                return values[idx];
            }
        }

        PriorityQueue<Integer> pqueue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int key : edges.keySet()) {
            int value = edges.get(key) + findAnswer(key);
            if (values[idx] < value) {
                values[idx] = value;
            }
            pqueue.add(value);
        }

        int a1 = pqueue.poll();
        int a2 = pqueue.poll();

        if (a1 + a2 > answer) {
            answer = a1 + a2;
        }

        return values[idx];
    }

    private static int[] listConvert(String[] split) {
        int[] intlist = new int[split.length];
        for (int i = 0; i < intlist.length; i++) {
            intlist[i] = Integer.parseInt(split[i]);
        }
        return intlist;
    }
}

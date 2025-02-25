
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<Node> graph;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int testcase = 0; testcase < T; testcase++) {
            st = new StringTokenizer(bf.readLine());

            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(bf.readLine());

            graph = new ArrayList<>();
            for (int n = 0; n < N; n++) {
                int cost = Integer.parseInt(st.nextToken());
                graph.add(new Node(n, cost));
            }

            for (int k = 0; k < K; k++) {
                st = new StringTokenizer(bf.readLine());
                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;

                graph.get(b).edges.add(a);
            }

            int target = Integer.parseInt(bf.readLine()) - 1;

            int answer = dfsAndMemo(target);

            sb.append(answer).append("\n");
        }
        System.out.print(sb);
    }

    private static int dfsAndMemo(int target) {
        Node node = graph.get(target);

        if (node.maxCost != -1) {
            return node.maxCost;
        }

        int max_cost = 0;
        for (int nextTarget : node.edges) {
            int next_cost = dfsAndMemo(nextTarget);
            if (next_cost > max_cost) {
                max_cost = next_cost;
            }
        }

        node.maxCost = node.ownCost + max_cost;

        return node.maxCost;
    }
}

class Node {
    int num;
    int ownCost;
    int maxCost;
    List<Integer> edges;

    Node(int num, int ownCost) {
        this.num = num;
        this.ownCost = ownCost;
        maxCost = -1;
        edges = new ArrayList<>();
    }
}

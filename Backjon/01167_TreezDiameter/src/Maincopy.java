import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Maincopy {
    static List<Node> nodes;
    static int furthest_idx;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream("src/input.txt")));
        // BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            int V = Integer.parseInt(bf.readLine());
            nodes = new ArrayList<>();
            for (int i = 0; i < V; i++) {
                st = new StringTokenizer(bf.readLine());
                int node_num = Integer.parseInt(st.nextToken()) - 1;
                Node node = new Node(node_num);
                int cnt = st.countTokens() / 2;
                for (int j = 0; j < cnt; j++) {
                    int a = Integer.parseInt(st.nextToken()) - 1;
                    int b = Integer.parseInt(st.nextToken());
                    node.addEdge(new Edge(a, b));
                }
                nodes.add(node);
            }

            nodes.sort((o1, o2) -> o1.getNum() - o2.getNum());

            int target = findFurthest(0, -1).getIdx();
            int answer = findFurthest(target, -1).getDistance();
            sb.append(String.format("#%d %d\n", tc, answer));
            bf.readLine();
        }

        System.out.println(sb);
        bf.close();
    }

    private static Result findFurthest(int idx, int parent) {
        Node target_node = nodes.get(idx);
        Result result = new Result(target_node.getNum(), 0);

        for (Edge e : target_node.getEdges()) {
            if (e.getDest() == parent) {
                continue;
            }

            Result get_result = findFurthest(e.getDest(), idx);
            if (result.getDistance() <= e.getWeight() + get_result.getDistance()) {
                result.setDistance(e.getWeight() + get_result.getDistance());
                result.setIdx(get_result.getIdx());
            }
        }

        return result;

    }
}

// class Node {
// private int num;
// private List<Edge> edges;

// public Node(int num) {
// this.num = num;
// edges = new ArrayList<>();
// }

// public int getNum() {
// return num;
// }

// public List<Edge> getEdges() {
// return edges;
// }

// public void addEdge(Edge edge) {
// edges.add(edge);
// }

// }

// class Edge {
// private int dest;
// private int weight;

// public Edge(int dest, int weight) {
// this.dest = dest;
// this.weight = weight;
// }

// public int getDest() {
// return dest;
// }

// public int getWeight() {
// return weight;
// }
// }
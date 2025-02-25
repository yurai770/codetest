import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();
        for (int testcase = 0; testcase < T; testcase++) {
            int[] NMW = listConvert(bf.readLine().split(" "));
            int[] min_route = new int[NMW[0]];
            List<Node> nodes = new ArrayList<>();
            int[] tmp;
            final int MAX_INT = 50000000;
            for (int n = 0; n < NMW[0]; n++) {
                nodes.add(new Node(n));
                min_route[n] = MAX_INT;
            }
            for (int m = 0; m < NMW[1]; m++) {
                tmp = listConvert(bf.readLine().split(" "));
                nodes.get(tmp[0] - 1).addEdge(new Edge(tmp[1] - 1, tmp[2]));
                nodes.get(tmp[1] - 1).addEdge(new Edge(tmp[0] - 1, tmp[2]));
            }
            for (int w = 0; w < NMW[2]; w++) {
                tmp = listConvert(bf.readLine().split(" "));
                nodes.get(tmp[0] - 1).addEdge(new Edge(tmp[1] - 1, -tmp[2]));
            }

            min_route[0] = 0;

            boolean updated = false;
            for (int v = 0; v < NMW[0]; v++) {
                updated = false;
                for (int start_idx = 0; start_idx < NMW[0]; start_idx++) {
                    Node start_node = nodes.get(start_idx);
                    for (Edge edge : start_node.getEdges()) {
                        int dest_idx = edge.getDist();
                        int cost = edge.getWeight();

                        if (min_route[dest_idx] > min_route[start_idx] + cost) {
                            min_route[dest_idx] = min_route[start_idx] + cost;
                            updated = true;
                        }
                    }
                }
                if (!updated) {
                    break;
                }
            }

            if (updated) {
                sb.append("YES").append("\n");
            } else {
                sb.append("NO").append("\n");
            }

        }
        System.out.print(sb);
    }

    private static int[] listConvert(String[] split) {
        int[] intlist = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            intlist[i] = Integer.parseInt(split[i]);
        }
        return intlist;
    }
}

class Node {
    private int num;
    private List<Edge> edges;

    Node(int num) {
        this.num = num;
        edges = new ArrayList<>();
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public List<Edge> getEdges() {
        return edges;
    }
}

class Edge {
    private int dist;
    private int weight;

    public int getDist() {
        return dist;
    }

    public int getWeight() {
        return weight;
    }

    Edge(int dist, int weight) {
        this.dist = dist;
        this.weight = weight;
    }
}
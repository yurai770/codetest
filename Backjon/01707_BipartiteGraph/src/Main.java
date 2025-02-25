import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int testcase = 1; testcase <= T; testcase++) {
            st = new StringTokenizer(bf.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            List<Node> graph = new ArrayList<>();
            for (int v = 0; v < V; v++) {
                graph.add(new Node(v));
            }
            for (int e = 0; e < E; e++) {
                st = new StringTokenizer(bf.readLine());
                int idx = Integer.parseInt(st.nextToken()) - 1;
                int desti = Integer.parseInt(st.nextToken()) - 1;
                graph.get(idx).addEdge(desti);
                graph.get(desti).addEdge(idx);
            }

            int search_idx = 0;
            Deque<Integer> queue = new ArrayDeque<>();
            boolean result = true;

            while (search_idx < V && result) {
                if (graph.get(search_idx).isVisited()) {
                    search_idx++;
                    continue;
                }

                graph.get(search_idx).setBlack();
                graph.get(search_idx).visitEnd();
                queue.add(search_idx);
                while (queue.size() > 0) {
                    Node popped_node = graph.get(queue.pop());

                    for (int target_idx : popped_node.getEdges()) {
                        Node target_node = graph.get(target_idx);
                        if (target_node.isVisited()) {
                            if (popped_node.getColorCode() == target_node.getColorCode()) {
                                result = false;
                                break;
                            }
                            continue;
                        }

                        target_node.setOrderColor(popped_node.getColorCode());
                        target_node.visitEnd();
                        queue.add(target_idx);
                    }
                }

                search_idx++;
            }

            if (result) {
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }

        }
        System.out.print(sb);
    }
}

class Node {
    private int num;
    private List<Integer> edges;
    private boolean visited;
    private int color_code;
    private final int BLACK = 0;
    private final int WHITE = 1;
    private final int NONECOLOR = -1;

    Node(int num) {
        this.num = num;
        edges = new ArrayList<>();
        visited = false;
        color_code = NONECOLOR;
    }

    public int getNum() {
        return num;
    }

    public List<Integer> getEdges() {
        return edges;
    }

    public void addEdge(int edge) {
        edges.add(edge);
    }

    public boolean isVisited() {
        return visited;
    }

    public void visitEnd() {
        visited = true;
    }

    public void setBlack() {
        color_code = BLACK;
    }

    public int getColorCode() {
        return color_code;
    }

    public void setOrderColor(int order_color_code) {
        if (order_color_code == BLACK) {
            this.color_code = WHITE;
        } else {
            this.color_code = BLACK;
        }
    }
}
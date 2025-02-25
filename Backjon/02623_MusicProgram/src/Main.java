
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<Node> graph;
    static int[] anslist;
    static int ans_idx;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        anslist = new int[N];
        ans_idx = N - 1;
        graph = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            graph.add(new Node(i));
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            st.nextToken();
            int a;
            int b = Integer.parseInt(st.nextToken()) - 1;
            while (st.hasMoreTokens()) {
                a = b;
                b = Integer.parseInt(st.nextToken()) - 1;
                graph.get(a).addEdge(b);
            }
        }

        boolean now_cycling = true;
        for (int i = 0; i < N; i++) {
            now_cycling = dfsAndHasCycling(i);
            if (now_cycling) {
                break;
            }
        }

        if (now_cycling) {
            System.out.println(0);
        } else {
            StringBuilder sb = new StringBuilder();
            for (int num : anslist) {
                sb.append(num).append("\n");
            }
            System.out.print(sb);
        }
    }

    // if it cycling, return true. else, return false
    private static boolean dfsAndHasCycling(int now_idx) {
        Node now_node = graph.get(now_idx);
        if (now_node.isCycling()) {
            return true;
        }

        if (now_node.isVisited()) {
            return false;
        }
        now_node.visitEnd();

        now_node.startCycling();
        for (int next_idx : now_node.getEdges()) {
            if ((dfsAndHasCycling(next_idx))) {
                return true;
            }
        }
        anslist[ans_idx--] = now_node.getNum() + 1;
        now_node.endCycling();
        return false;
    }
}

class Node {
    private int num;
    private List<Integer> edges;
    private boolean visited;
    private boolean cycling;

    public Node(int num) {
        this.num = num;
        edges = new ArrayList<>();
        visited = false;
        cycling = false;
    }

    public int getNum() {
        return num;
    }

    public List<Integer> getEdges() {
        return edges;
    }

    public boolean isVisited() {
        return visited;
    }

    public boolean isCycling() {
        return cycling;
    }

    public void addEdge(int edge) {
        edges.add(edge);
    }

    public void visitEnd() {
        this.visited = true;
    }

    public void startCycling() {
        this.cycling = true;
    }

    public void endCycling() {
        this.cycling = false;
    }
}
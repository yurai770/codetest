import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<Node> graph;
    static int[] anslist;
    static int idx;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            graph.add(new Node(i));
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            graph.get(a).getEdges().add(b);
        }
        anslist = new int[N];
        idx = N - 1;

        for (int i = 0; i < N; i++) {
            dfs(i);
        }

        StringBuilder sb = new StringBuilder();
        for (int n : anslist) {
            sb.append(n).append(" ");
        }
        System.out.println(sb);

    }

    private static void dfs(int target) {
        Node targetNode = graph.get(target);

        if (targetNode.isVisited()) {
            return;
        }

        targetNode.visitEnd();
        for (int next : targetNode.getEdges()) {
            dfs(next);
        }
        anslist[idx--] = targetNode.getNum() + 1;
    }
}

class Node {
    private int num;
    private List<Integer> edges;
    private boolean visited;

    Node(int num) {
        this.num = num;
        edges = new ArrayList<>();
        visited = false;
    }

    public void addEdge(int edge) {
        edges.add(edge);
    }

    public List<Integer> getEdges() {
        return edges;
    }

    public int getNum() {
        return num;
    }

    public boolean isVisited() {
        return visited;
    }

    public void visitEnd() {
        visited = true;
    }
}
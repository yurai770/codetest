
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static List<Node> origin_graph;
    static int N;

    public static void main(String[] args) throws Exception {
        origin_graph = new ArrayList<>();

        // 입력 처리 메소드
        stdInputMethod();

        // 다익스트라 실행
        doDij();

    }

    private static void doDij() {
        // 준비 부분
        boolean[] isVisited = new boolean[N];
        long[] dijComplete = new long[N];
        for (int n = 0; n < N; n++) {
            dijComplete[n] = Long.MAX_VALUE;
        }
        dijComplete[0] = 0;

        // 우선순위큐 준비하고 초기화
        PriorityQueue<Edge> pQueue = new PriorityQueue<>((o1, o2) -> o1.getCost() - o2.getCost());
        pQueue.add(new Edge(0, 0));

        // 다익스트라 실행
        while (!isVisited[N - 1]) {
            Edge nextEdge = pQueue.poll();
            if (isVisited[nextEdge.getEndNodeNum()]) {
                continue;
            }

            isVisited[nextEdge.getEndNodeNum()] = true;
            Node nowNode = origin_graph.get(nextEdge.getEndNodeNum());

            for (Edge e : nowNode.getEdges()) {

            }
        }
    }

    // 입력 처리 메소드
    private static void stdInputMethod() throws Exception {
        // BufferedReader bf = new BufferedReader(new FileReader(""));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(bf.readLine());
        int i = 0;
        while (st.hasMoreTokens()) {
            origin_graph.add(new Node(i++, Integer.parseInt(st.nextToken())));
        }

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());

            origin_graph.get(a).addEdges(new Edge(b, d));
            origin_graph.get(b).addEdges(new Edge(a, d));
        }

    }
}

class Node {
    private final int nodeNum;
    private final int gasCost;
    private final List<Edge> edges;

    public Node(int nodeNum, int gasCost) {
        this.nodeNum = nodeNum;
        this.gasCost = gasCost;
        edges = new ArrayList<>();
    }

    public int getNodeNum() {
        return nodeNum;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void addEdges(Edge e) {
        edges.add(e);
    }

    public int getGasCost() {
        return gasCost;
    }

}

class Edge {
    private final int endNodeNum;
    private final int cost;

    public Edge(int endNodeNum, int cost) {
        this.endNodeNum = endNodeNum;
        this.cost = cost;
    }

    public int getEndNodeNum() {
        return endNodeNum;
    }

    public int getCost() {
        return cost;
    }
}
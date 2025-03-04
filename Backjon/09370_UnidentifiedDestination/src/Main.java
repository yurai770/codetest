import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int TEST = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int tc = 0; tc < TEST; tc++) {
            st = new StringTokenizer(bf.readLine());
            int N = Integer.parseInt(st.nextToken()); // 교차로 수 (노드)
            int M = Integer.parseInt(st.nextToken()); // 도로 수 (간선)
            int T = Integer.parseInt(st.nextToken()); // 목적지 후보 수

            // 그래프 만들고 교차로 수 만큼 노드 준비
            List<Node> graph = new ArrayList<>();
            for (int n = 0; n < N; n++) {
                Node node = new Node(n);
                graph.add(node);
            }

            // 출발지와 지나는 노드들은 1빼서 준비
            st = new StringTokenizer(bf.readLine());
            int S = Integer.parseInt(st.nextToken()) - 1; // 출발지
            int G = Integer.parseInt(st.nextToken()) - 1; // 지나가는 노드1
            int H = Integer.parseInt(st.nextToken()) - 1; // 지나가는 노드2
            // 도로 추가 해주기
            for (int m = 0; m < M; m++) {
                st = new StringTokenizer(bf.readLine());
                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;
                int d = Integer.parseInt(st.nextToken());

                graph.get(a).addEdge(new Edge(b, d));
                graph.get(b).addEdge(new Edge(a, d));
            }

            // 후보지 추가하고 오름차순 정렬 해놓기
            List<Integer> destinations = new ArrayList<>();
            for (int t = 0; t < T; t++) {
                destinations.add(Integer.parseInt(bf.readLine()) - 1);
            }
            destinations.sort((o1, o2) -> o1 - o2);

            // 출발지에서 출발하는 다익스트라
            List<Integer> allDijkstra = dijkstra(S, graph);

            // g지점에서 출발하는 다익스트라
            List<Integer> gDijkstra = dijkstra(G, graph);

            // h지점에서 출발하는 다익스트라
            List<Integer> hDijkstra = dijkstra(H, graph);

            // 전체 최소 길이 = (g,h)를 지난 최소길이 비교하기
            int loadGH = 0;
            for (Edge e : graph.get(G).getEdges()) {
                if (e.getTargetNode() == H) {
                    loadGH = e.getValue();
                    break;
                }
            }

            for (int D : destinations) {
                int loadS_G_H_D = allDijkstra.get(G) + loadGH + hDijkstra.get(D);
                int loadS_H_G_D = allDijkstra.get(H) + loadGH + gDijkstra.get(D);
                if (allDijkstra.get(D) == Integer.min(loadS_G_H_D, loadS_H_G_D)) {
                    sb.append(D + 1).append(" ");
                }
            }

            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    // 다익스트라 알고리즘
    private static List<Integer> dijkstra(int startPoint, List<Node> graph) {
        final int MAX_VALUE = 100000000;
        PriorityQueue<DijNode> pQueue = new PriorityQueue<>();
        List<Integer> dijComplete = new ArrayList<>();
        boolean[] isVisted = new boolean[graph.size()];

        // 시작값 초기화
        for (int i = 0; i < graph.size(); i++) {
            dijComplete.add(MAX_VALUE);
        }
        dijComplete.set(startPoint, 0);
        pQueue.add(new DijNode(startPoint, 0));

        // 알고리즘 수행. 방문여부 체크하면서 우선순위 큐로 반복
        while (!pQueue.isEmpty()) {
            DijNode nowNode = pQueue.poll();
            // 방문한 노드라면 돌아간다
            if (isVisted[nowNode.getNodeNum()]) {
                continue;
            }

            isVisted[nowNode.getNodeNum()] = true;

            // 노드가 가진 모든 간선이 향하는 노드를 갱신한다.
            for (Edge e : graph.get(nowNode.getNodeNum()).getEdges()) {
                int newValue = dijComplete.get(nowNode.getNodeNum()) + e.getValue();
                dijComplete.set(e.getTargetNode(), Integer.min(newValue, dijComplete.get(e.getTargetNode())));
                pQueue.add(new DijNode(e.getTargetNode(), dijComplete.get(e.getTargetNode())));
            }
        }

        return dijComplete;
    }
}

class DijNode implements Comparable<DijNode> {
    private final int nodeNum;
    private int cost;

    public DijNode(int nodeNum, int cost) {
        this.nodeNum = nodeNum;
        this.cost = cost;
    }

    // 비교시 오름차순 1. 비용 2. 번호
    @Override
    public int compareTo(DijNode o) {
        if (cost - o.getCost() == 0) {
            return nodeNum - o.getNodeNum();
        }
        return cost - o.getCost();
    }

    public int getNodeNum() {
        return nodeNum;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int value) {
        this.cost = value;
    }

}

class Node {
    private final int nodeNum;
    private List<Edge> edges;

    public Node(int nodeNum) {
        this.nodeNum = nodeNum;
        edges = new ArrayList<>();
    }

    public int getNodeNum() {
        return nodeNum;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void addEdge(Edge e) {
        edges.add(e);
    }

}

class Edge {
    private final int targetNode;
    private final int value;

    Edge(int targetNode, int value) {
        this.targetNode = targetNode;
        this.value = value;
    }

    public int getTargetNode() {
        return targetNode;
    }

    public int getValue() {
        return value;
    }
}
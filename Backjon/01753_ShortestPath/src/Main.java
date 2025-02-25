import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int V = Integer.parseInt(st.nextToken());
        List<Node> nodes = new ArrayList<>();
        int[] distance = new int[V];
        for (int i = 0; i < V; i++) {
            nodes.add(new Node(i));
            distance[i] = Integer.MAX_VALUE;
        }
        int E = Integer.parseInt(st.nextToken());

        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        int start = Integer.parseInt(bf.readLine()) - 1;
        distance[start] = 0;
        queue.add(new int[] { start, 0 });

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(bf.readLine());
            int idx = Integer.parseInt(st.nextToken());
            nodes.get(idx - 1).addLines(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        while (queue.size() > 0) {
            Node target = nodes.get(queue.poll()[0]);
            if (target.isVisited()) {
                continue;
            }

            for (int another_node_idx : target.getLinesKeys()) {
                distance[another_node_idx] = Integer.min(distance[another_node_idx],
                        distance[target.getNum()] + target.getLines().get(another_node_idx));
                queue.add(new int[] { another_node_idx, distance[another_node_idx] });
            }

            target.visitedEnd();
        }

        for (int ans : distance) {
            if (ans == Integer.MAX_VALUE) {
                sb.append("INF\n");
            } else {
                sb.append(ans).append("\n");
            }
        }

        System.out.print(sb);
    }
}

class Node {
    private int num;
    private Map<Integer, Integer> lines;
    private boolean visited;

    Node(int num) {
        this.num = num;
        lines = new HashMap<>();
        visited = false;
    }

    void addLines(int v, int w) {
        v -= 1;
        if (!(lines.containsKey(v))) {
            lines.put(v, w);
            return;
        }
        if (lines.get(v) > w) {
            lines.replace(v, w);
        }
    }

    public Map<Integer, Integer> getLines() {
        return lines;
    }

    Set<Integer> getLinesKeys() {
        return lines.keySet();
    }

    boolean isVisited() {
        return visited;
    }

    void visitedEnd() {
        visited = true;
    }

    public int getNum() {
        return num;
    }
}

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<List<Edge>> graph;
    static final long MAXVALUE = 200000 * 1000 + 1;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>(N);
        for (int n = 0; n < N; n++) {
            graph.add(new ArrayList<>());
        }

        for (int e = 0; e < E; e++) {
            st = new StringTokenizer(bf.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());

            graph.get(node1).add(new Edge(node2, weight));
            graph.get(node2).add(new Edge(node1, weight));
        }

        st = new StringTokenizer(bf.readLine());
        int v1 = Integer.parseInt(st.nextToken()) - 1;
        int v2 = Integer.parseInt(st.nextToken()) - 1;

        ReClass st_v = dijkstra(0, v1, v2);
        ReClass v1_ed = dijkstra(v1, v2, N - 1);
        ReClass v2_ed = dijkstra(v2, v1, N - 1);

        if (v1_ed.getA() == MAXVALUE) {
            System.out.println(-1);
            return;
        }

        long selected = Long.min(st_v.getA() + v2_ed.getB(), st_v.getB() + v1_ed.getB());

        if (selected >= MAXVALUE) {
            System.out.println(-1);
            return;
        }

        System.out.println(v1_ed.getA() + selected);
    }

    private static ReClass dijkstra(int start_idx, int t1, int t2) {
        long[] path = new long[graph.size()];

        for (int i = 0; i < path.length; i++) {
            path[i] = MAXVALUE;
        }

        path[start_idx] = 0;
        Queue<Edge> queue = new PriorityQueue<>((o1, o2) -> {
            if (o1.getWeight() - o2.getWeight() > 0) {
                return 1;
            } else {
                return -1;
            }
        });

        queue.add(new Edge(start_idx, 0));

        while (queue.size() > 0) {
            Edge popped = queue.poll();
            if (popped.getWeight() > path[popped.getDesti()]) {
                continue;
            }

            for (Edge target : graph.get(popped.getDesti())) {
                long newdist = path[popped.getDesti()] + target.getWeight();
                if (newdist < path[target.getDesti()]) {
                    path[target.getDesti()] = newdist;
                    queue.add(new Edge(target.getDesti(), newdist));
                }

            }
        }

        return new ReClass(path[t1], path[t2]);
    }
}

class ReClass {
    private long a;
    private long b;

    ReClass(long a, long b) {
        this.a = a;
        this.b = b;
    }

    public long getA() {
        return a;
    }

    public long getB() {
        return b;
    }
}

class Edge {
    private int desti;
    private long weight;

    public Edge(int desti, long weight) {
        this.desti = desti;
        this.weight = weight;
    }

    public int getDesti() {
        return desti;
    }

    public void setDesti(int desti) {
        this.desti = desti;
    }

    public long getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
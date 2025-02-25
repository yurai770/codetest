import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(bf.readLine());
        int M = Integer.parseInt(bf.readLine());
        final long MAXVALUE = 100000 * 100000 + 1;

        List<List<Edge>> graph = new ArrayList<>();
        long[] path = new long[N];
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
            path[i] = MAXVALUE;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Edge(b, v));
        }
        st = new StringTokenizer(bf.readLine());
        int start = Integer.parseInt(st.nextToken()) - 1;
        int end = Integer.parseInt(st.nextToken()) - 1;

        Queue<Edge> queue = new PriorityQueue<>();
        queue.add(new Edge(start, 0));
        path[start] = 0;

        while (queue.size() > 0) {
            Edge here = queue.poll();

            if (here.getCost() > path[here.getDesti()]) {
                continue;
            }

            for (Edge there : graph.get(here.getDesti())) {
                long new_distence = path[here.getDesti()] + there.getCost();
                if (new_distence < path[there.getDesti()]) {
                    path[there.getDesti()] = new_distence;
                    queue.add(new Edge(there.getDesti(), new_distence));
                }
            }
        }

        System.out.println(path[end]);

    }
}

class Edge implements Comparable<Edge> {
    private int desti;
    private long cost;

    public Edge(int desti, long cost) {
        this.desti = desti;
        this.cost = cost;
    }

    public int getDesti() {
        return desti;
    }

    public long getCost() {
        return cost;
    }

    @Override
    public int compareTo(Edge o) {
        if (this.getCost() - o.getCost() > 0) {
            return 1;
        } else {
            return 0;
        }
    }
}
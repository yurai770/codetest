import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        boolean[] isVisited = new boolean[V];
        List<List<Edge>> nodes = new ArrayList<>();
        for(int v = 0; v < V; v++){
            nodes.add(new ArrayList<>());
        }
        for(int i = 0; i < E; i++){
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken()) -1;
            int b = Integer.parseInt(st.nextToken()) -1;
            int cost = Integer.parseInt(st.nextToken());

            nodes.get(a).add(new Edge(b, cost));
            nodes.get(b).add(new Edge(a, cost));
        }

        PriorityQueue<Edge> queue = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        queue.addAll(nodes.get(0));
        isVisited[0] = true;
        int answer = 0;
        int checker = 1;
        while(checker < isVisited.length){
            Edge pop_edge = queue.poll();
            if(isVisited[pop_edge.node]){
                continue;
            }

            answer += pop_edge.cost;
            queue.addAll(nodes.get(pop_edge.node));
            isVisited[pop_edge.node] = true;
            checker++;
        }
        
        System.out.println(answer);

    }
}

class Edge{
    int node;
    int cost;

    public Edge(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }
    
}

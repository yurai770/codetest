import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        List<Node> computers = new ArrayList<>();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(bf.readLine());
        for (int i = 0; i < num; i++) {
            computers.add(new Node());
        }

        int lines = Integer.parseInt(bf.readLine());
        for (int i = 0; i < lines; i++) {
            int[] line = listConvert(bf.readLine().split(" "));
            computers.get(line[0] - 1).addConnectNode(line[1] - 1);
            computers.get(line[1] - 1).addConnectNode(line[0] - 1);
        }

        Deque<Integer> idx_queue = new ArrayDeque<>();
        idx_queue.add(0);
        while (idx_queue.size() > 0) {
            Node nowNode = computers.get(idx_queue.pop());
            if (nowNode.isVisited()) {
                continue;
            }

            for (int addidx : nowNode.getConnect_nodes()) {
                idx_queue.add(addidx);
            }
            nowNode.visitEnd();
        }

        int ans = 0;
        for (Node com : computers) {
            if (com.isVisited()) {
                ans += 1;
            }
        }

        System.out.println(ans - 1);
    }

    private static int[] listConvert(String[] split) {
        int[] intlist = new int[split.length];
        for (int i = 0; i < intlist.length; i++) {
            intlist[i] = Integer.parseInt(split[i]);
        }
        return intlist;
    }
}

class Node {
    private boolean visited;
    private List<Integer> connect_nodes;

    Node() {
        visited = false;
        connect_nodes = new ArrayList<>();
    }

    public boolean isVisited() {
        return visited;
    }

    public void visitEnd() {
        this.visited = true;
    }

    public List<Integer> getConnect_nodes() {
        return connect_nodes;
    }

    public void addConnectNode(int node) {
        connect_nodes.add(node);
    }
}
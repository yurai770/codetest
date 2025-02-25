import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] NMR = listConvert(bf.readLine().split(" "));

        List<Node> nodes = new ArrayList<>();
        for (int n = 0; n <= NMR[0]; n++) {
            nodes.add(new Node(n));
        }

        for (int m = 0; m < NMR[1]; m++) {
            int[] line = listConvert(bf.readLine().split(" "));
            nodes.get(line[0]).addConnectNode(line[1]);
            nodes.get(line[1]).addConnectNode(line[0]);
        }

        Node nowNode;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.add(NMR[2]);
        StringBuilder dfs_str = new StringBuilder();
        while (stack.size() > 0) {
            nowNode = nodes.get(stack.pop());
            if (nowNode.isDfsVisit()) {
                continue;
            }

            dfs_str.append(nowNode.getNum() + " ");
            for (int node : nowNode.getDFSConnectList()) {
                stack.addFirst(node);
            }
            nowNode.endDfs();
        }

        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(NMR[2]);
        StringBuilder bfs_str = new StringBuilder();
        while (queue.size() > 0) {
            nowNode = nodes.get(queue.pop());
            if (nowNode.isBfsVisit()) {
                continue;
            }

            bfs_str.append(nowNode.getNum() + " ");
            for (int node : nowNode.getBFSConnectList()) {
                queue.add(node);
            }
            nowNode.endBfs();
        }

        System.out.println(dfs_str);
        System.out.println(bfs_str);
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
    private int num;
    private List<Integer> connect_list;
    private boolean bfs_visit;
    private boolean dfs_visit;

    public Node(int num) {
        this.num = num;
        connect_list = new ArrayList<>();
        bfs_visit = false;
        dfs_visit = false;
    }

    public int getNum() {
        return num;
    }

    public List<Integer> getDFSConnectList() {
        connect_list.sort((o1, o2) -> o2 - o1);
        return connect_list;
    }

    public List<Integer> getBFSConnectList() {
        connect_list.sort((o1, o2) -> o1 - o2);
        return connect_list;
    }

    public void addConnectNode(int node) {
        connect_list.add(node);
    }

    public boolean isBfsVisit() {
        return bfs_visit;
    }

    public void endBfs() {
        bfs_visit = true;
    }

    public boolean isDfsVisit() {
        return dfs_visit;
    }

    public void endDfs() {
        dfs_visit = true;
    }

}
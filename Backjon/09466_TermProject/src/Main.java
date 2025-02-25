
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<Node> graph;
    static Deque<Integer> stack;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int testcase = 0; testcase < T; testcase++) {
            int len = Integer.parseInt(bf.readLine());
            graph = new ArrayList<>();
            st = new StringTokenizer(bf.readLine());
            for (int i = 0; i < len; i++) {
                int n = Integer.parseInt(st.nextToken()) - 1;
                Node node = new Node(i, n);
                graph.add(node);
            }

            stack = new ArrayDeque<>();
            for (int idx = 0; idx < len; idx++) {
                dfs(idx);
            }

            sb.append(stack.size()).append("\n");
        }
        System.out.print(sb);
    }

    private static void dfs(int idx) {
        Node nowNode = graph.get(idx);
        if (nowNode.isVisited()) {
            return;
        }

        if (nowNode.isCycling()) {
            while (true) {
                int tmp = stack.pollLast();
                if (nowNode.getNum() == tmp) {
                    break;
                }
            }
            return;
        }

        stack.addLast(nowNode.getNum());

        nowNode.startCycling();
        dfs(nowNode.getNext());
        nowNode.endCycling();
        nowNode.visitEnd();
    }
}

class Node {
    private int num;
    private int next;
    private boolean visited;
    private boolean cycling;

    public Node(int num, int next) {
        this.num = num;
        this.next = next;
        visited = false;
        cycling = false;
    }

    public int getNum() {
        return num;
    }

    public int getNext() {
        return next;
    }

    public boolean isVisited() {
        return visited;
    }

    public void visitEnd() {
        visited = true;
    }

    public boolean isCycling() {
        return cycling;
    }

    public void startCycling() {
        cycling = true;
    }

    public void endCycling() {
        cycling = false;
    }
}
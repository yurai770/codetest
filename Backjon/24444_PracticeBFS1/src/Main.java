import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] NMR = listConvert(bf.readLine().split(" "));
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < NMR[0]; i++) {
            nodes.add(new Node());
        }
        for (int i = 0; i < NMR[1]; i++) {
            int[] line = listConvert(bf.readLine().split(" "));
            nodes.get(line[0] - 1).addLine(line[1]);
            nodes.get(line[1] - 1).addLine(line[0]);
        }

        LinkedList<Integer> idx_queue = new LinkedList<>();
        int seq = 1;
        idx_queue.add(NMR[2] - 1);
        while (idx_queue.size() > 0) {
            Node nowNode = nodes.get(idx_queue.pop());
            if (nowNode.isVisited()) {
                continue;
            }

            nowNode.setSeq(seq++);
            nowNode.alreadyVisited();
            for (int num : nowNode.getLinelist()) {
                idx_queue.add(num - 1);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Node node : nodes) {
            sb.append(node.getSeq() + "\n");
        }
        System.out.println(sb);
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
    private int seq;
    private boolean visited;
    private List<Integer> linelist;

    public Node() {
        this.visited = false;
        this.linelist = new ArrayList<>();
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public boolean isVisited() {
        return visited;
    }

    public void alreadyVisited() {
        visited = true;
    }

    public List<Integer> getLinelist() {
        linelist.sort((o1, o2) -> o2 - o1);
        return linelist;
    }

    public void addLine(int line) {
        linelist.add(line);
    }

}
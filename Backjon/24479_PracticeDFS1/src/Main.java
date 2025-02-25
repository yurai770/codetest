import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        List<Node> nodelist = new ArrayList<>();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] NMR = listConvert(bf.readLine().split(" "));

        for (int i = 0; i < NMR[0]; i++) {
            nodelist.add(new Node(i));
        }

        for (int i = 0; i < NMR[1]; i++) {
            int[] line = listConvert(bf.readLine().split(" "));
            nodelist.get(line[0] - 1).addLine(line[1]);
            nodelist.get(line[1] - 1).addLine(line[0]);
        }

        LinkedList<Integer> idx_stack = new LinkedList<>();
        int seq = 1;
        idx_stack.addFirst(NMR[2]);

        while (idx_stack.size() > 0) {
            Node nownode = nodelist.get(idx_stack.pop() - 1);
            if (nownode.Is_Visited()) {
                continue;
            }
            nownode.alreadyVisited();
            nownode.setSeq(seq++);
            for (int num : nownode.getLine()) {
                idx_stack.addFirst(num);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nodelist.size(); i++) {
            sb.append(nodelist.get(i).getSeq() + "\n");
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
    private int num;
    private List<Integer> line;
    private int seq;
    private boolean visited;

    Node(int num) {
        this.num = num;
        line = new ArrayList<>();
        visited = false;
    }

    public void alreadyVisited() {
        visited = true;
    }

    public boolean Is_Visited() {
        return visited;
    }

    public void addLine(int num) {
        line.add(num);
    }

    public List<Integer> getLine() {
        line.sort((o1, o2) -> o1 - o2);
        return line;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

}
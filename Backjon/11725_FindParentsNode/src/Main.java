import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(br.readLine());
        int[] parent = new int[len];
        Node[] nodes = new Node[len];

        for (int i = 0; i < len; i++) {
            nodes[i] = new Node();
        }

        for (int i = 0; i < len - 1; i++) {
            int[] tmp = listConvert(br.readLine().split(" "));
            nodes[tmp[0] - 1].addLine(tmp[1]);
            nodes[tmp[1] - 1].addLine(tmp[0]);
        }

        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(1);
        while (queue.size() > 0) {
            int target = queue.pop();
            for (int line : nodes[target - 1].getLines()) {
                if (parent[target - 1] == line) {
                    continue;
                }
                if (parent[line - 1] != 0) {
                    continue;
                }
                parent[line - 1] = target;
                queue.add(line);
            }
        }

        StringBuilder sb = new StringBuilder("");
        for (int i = 1; i < len; i++) {
            sb.append(parent[i] + "\n");
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
    List<Integer> lines;

    Node() {
        lines = new ArrayList<>();
    }

    void addLine(int line) {
        lines.add(line);
    }

    List<Integer> getLines() {
        return lines;
    }
}
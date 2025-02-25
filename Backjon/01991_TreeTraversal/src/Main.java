import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<Node> nodes;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(bf.readLine());
        nodes = new ArrayList<>();
        StringTokenizer st;
        for (int i = 0; i < len; i++) {
            st = new StringTokenizer(bf.readLine());
            nodes.add(new Node(st.nextToken().charAt(0), st.nextToken().charAt(0), st.nextToken().charAt(0)));
        }
        nodes.sort((o1, o2) -> o1.getRoot() - o2.getRoot());

        System.out.println(preorder(0));
        System.out.println(inorder(0));
        System.out.println(postorder(0));
    }

    static String preorder(int idx) {
        if (idx < 0) {
            return "";
        }

        Node targetNode = nodes.get(idx);
        String return_str = "";
        return_str += targetNode.getRoot();
        return_str += preorder(targetNode.getLeftIdx());
        return_str += preorder(targetNode.getRightIdx());
        return return_str;
    }

    static String inorder(int idx) {
        if (idx < 0) {
            return "";
        }

        Node targetNode = nodes.get(idx);
        String return_str = "";
        return_str += inorder(targetNode.getLeftIdx());
        return_str += targetNode.getRoot();
        return_str += inorder(targetNode.getRightIdx());
        return return_str;
    }

    static String postorder(int idx) {
        if (idx < 0) {
            return "";
        }

        Node targetNode = nodes.get(idx);
        String return_str = "";
        return_str += postorder(targetNode.getLeftIdx());
        return_str += postorder(targetNode.getRightIdx());
        return_str += targetNode.getRoot();
        return return_str;
    }

}

class Node {
    private char root;
    private char left;
    private char right;

    Node(char root, char left, char right) {
        this.root = root;
        this.left = left;
        this.right = right;
    }

    public char getRoot() {
        return root;
    }

    public int getLeftIdx() {
        if (left == '.') {
            return -1;
        }

        return left - 'A';
    }

    public int getRightIdx() {
        if (right == '.') {
            return -1;
        }

        return right - 'A';
    }
}
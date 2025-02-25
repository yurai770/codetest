import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BST bst = new BST();
        String st;
        while((st = bf.readLine()) != null){
            if(st.equals("")){
                break;
            }
            bst.add(new Node(Integer.parseInt(st)));
        }

        StringBuilder sb = new StringBuilder();
        bst.postOrder(sb);
        System.out.print(sb);
    }
}

class BST{
    private Node head = null;

    BST(){};
    
    public void add(Node node){
        if (head == null){
            head = node;
            return;
        }

        Node target = head;
        while(true){
            if (target.getNum() < node.getNum()){
                if (target.getRight() == null){
                    target.setRight(node);
                    return;
                }
                target = target.getRight();
            }else{
                if (target.getLeft() == null){
                    target.setLeft(node);
                    return;
                }
                target = target.getLeft();
            }
        }
    }

    public StringBuilder postOrder(StringBuilder sb){
        postorderTraverse(head, sb);
        return sb;
    }

    private StringBuilder postorderTraverse(Node target, StringBuilder sb){
        if (target != null){
            postorderTraverse(target.getLeft(), sb);
            postorderTraverse(target.getRight(), sb);
            sb.append(target.getNum() + "\n");
        }

        return sb;
    }
}



class Node{
    private int num;
    private Node left = null;
    private Node right = null;

    Node(int num) {
        this.num = num;
    }
    public int getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
    }
    public Node getLeft() {
        return left;
    }
    public void setLeft(Node left) {
        this.left = left;
    }
    public Node getRight() {
        return right;
    }
    public void setRight(Node right) {
        this.right = right;
    }
}
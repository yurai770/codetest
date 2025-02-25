import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws Exception {
        Deque<String> stack = new ArrayDeque<>();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] notation = bf.readLine().split("");
        String answer = "";
        String tmp;

        for (int idx = 0; idx < notation.length; idx++) {
            if (notation[idx].equals("(")) {
                stack.addLast(notation[idx]);
                continue;
            }
            if (notation[idx].equals(")")) {
                while (true) {
                    tmp = stack.removeLast();
                    if (tmp.equals("(")) {
                        break;
                    }
                    answer += tmp;
                }
                continue;
            }
            if (notation[idx].equals("*") || notation[idx].equals("/")) {
                while (stack.size() > 0) {
                    tmp = stack.removeLast();
                    if (tmp.equals("+") || tmp.equals("-") || tmp.equals("(")) {
                        stack.addLast(tmp);
                        break;
                    }
                    answer += tmp;
                }
                stack.addLast(notation[idx]);
                continue;
            }
            if (notation[idx].equals("+") || notation[idx].equals("-")) {
                while (stack.size() > 0) {
                    tmp = stack.removeLast();
                    if (tmp.equals("(")) {
                        stack.addLast(tmp);
                        break;
                    }
                    answer += tmp;
                }
                stack.addLast(notation[idx]);
                continue;
            }

            answer += notation[idx];
        }

        while (stack.size() > 0) {
            answer += stack.removeLast();
        }

        System.out.println(answer);
    }
}
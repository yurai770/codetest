import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Mainf {
    public static void main(String[] args) throws Exception {
        Deque<String> before_stack;
        Deque<String> now_stack = new ArrayDeque<>();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] targets = bf.readLine().split("");
        String[] boooooooooom = bf.readLine().split("");
        int before_len;

        for (int i = 0; i < targets.length; i++) {
            now_stack.addLast(targets[i]);
        }

        do {
            int boom_idx = 0;
            before_stack = now_stack;
            before_len = before_stack.size();
            now_stack = new ArrayDeque<>();
            while (!(before_stack.isEmpty())) {
                String tmp = before_stack.pop();
                if (tmp.equals(boooooooooom[boom_idx])) {
                    boom_idx++;
                } else {
                    boom_idx = 0;
                    if (tmp.equals(boooooooooom[boom_idx])) {
                        boom_idx++;
                    }
                }

                now_stack.addLast(tmp);

                if (boom_idx < boooooooooom.length) {
                    continue;
                }
                for (; boom_idx > 0; boom_idx--) {
                    now_stack.removeLast();
                }
            }
        } while (before_len != now_stack.size());

        StringBuilder sb = new StringBuilder();
        for (String c : now_stack) {
            sb.append(c);
        }
        System.out.println(sb.length() == 0 ? "FRULA" : sb.toString());
    }

}

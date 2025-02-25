import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws Exception {
        Deque<Integer> value_stack = new ArrayDeque<Integer>();
        Deque<Integer> idx_stack = new ArrayDeque<Integer>();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String len = bf.readLine();
        int[] values = listConvert(bf.readLine().split(" "));
        int[] answers = new int[values.length];

        for (int idx = 0; idx < values.length; idx++) {
            while (true) {
                if (value_stack.size() == 0 || values[idx] <= value_stack.peek()) {
                    value_stack.addFirst(values[idx]);
                    idx_stack.addFirst(idx);
                    break;
                }

                answers[idx_stack.pop()] = values[idx];
                value_stack.pop();
            }
        }
        while (idx_stack.size() > 0) {
            answers[idx_stack.pop()] = -1;
        }

        for (int ans : answers) {
            bw.write(ans + " ");
        }
        bw.flush();
    }

    private static int[] listConvert(String[] split) {
        int[] intlist = new int[split.length];
        for (int i = 0; i < intlist.length; i++) {
            intlist[i] = Integer.parseInt(split[i]);
        }
        return intlist;
    }
}

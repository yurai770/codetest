import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(bf.readLine());
        int[] numbers = listConvert(bf.readLine().split(" "));
        int[] F = new int[1000000];
        int[] Fnumbers = new int[len];
        int[] answers = new int[len];
        Deque<Integer> idx_stack = new ArrayDeque<>();
        Deque<Integer> F_value_stack = new ArrayDeque<>();

        for (int i = 0; i < len; i++) {
            F[numbers[i] - 1] += 1;
            answers[i] = -1;
        }
        for (int i = 0; i < len; i++) {
            Fnumbers[i] = F[numbers[i] - 1];
        }

        for (int idx = 0; idx < len; idx++) {
            while (true) {
                if (F_value_stack.size() == 0 || F_value_stack.peek() >= Fnumbers[idx]) {
                    F_value_stack.addFirst(Fnumbers[idx]);
                    idx_stack.addFirst(idx);
                    break;
                }

                answers[idx_stack.pop()] = numbers[idx];
                F_value_stack.pop();
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int ans : answers) {
            sb.append(ans + " ");
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

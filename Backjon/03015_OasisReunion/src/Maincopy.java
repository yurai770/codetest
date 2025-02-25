import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Maincopy {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        long num = Long.parseLong(bf.readLine());
        long[] people = new long[(int) num];
        for (long i = 0; i < num; i++) {
            people[(int) i] = Long.parseLong(bf.readLine());
        }

        Deque<Long> person_stack = new ArrayDeque<>();
        Deque<Long> idx_stack = new ArrayDeque<>();
        long[] answer_list = new long[(int) num];
        long correction = 0;
        long popped_value = 0;

        for (long idx = 0; idx < num; idx++) {
            correction = 0;
            popped_value = 0;
            while (person_stack.size() != 0) {
                if (person_stack.peek() >= people[(int) idx]) {
                    answer_list[(int) idx_stack.peek().longValue()] += Long.max(1, correction);
                    break;
                }

                answer_list[(int) idx_stack.pop().longValue()] += Long.max(1, correction);
                if (popped_value == 0 || popped_value == person_stack.peek()) {
                    correction++;
                } else {
                    correction = 0;
                }
                popped_value = person_stack.pop();
            }
            person_stack.addFirst(people[(int) idx]);
            idx_stack.addFirst(idx);
        }

        correction = 0;
        idx_stack.pop();
        while (idx_stack.size() > 0) {
            popped_value = person_stack.pop();
            answer_list[(int) idx_stack.pop().longValue()] += correction;
            if (person_stack.peek() > popped_value) {
                correction = 0;
            } else {
                correction++;
            }
        }

        long answer = 0;
        for (Long ans : answer_list) {
            answer += ans;
        }
        System.out.println(answer);

    }

}

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(bf.readLine());
        int[] people = new int[num];
        for (int i = 0; i < num; i++) {
            people[i] = Integer.parseInt(bf.readLine());
        }

        Deque<Integer> person_stack = new ArrayDeque<>();
        Deque<Integer> idx_stack = new ArrayDeque<>();
        int[] answer_list = new int[num];
        long correction = 0;
        int popped_value = 0;

        for (int idx = 0; idx < num; idx++) {
            correction = 0;
            popped_value = 0;
            while (person_stack.size() != 0) {
                if (person_stack.peek() >= people[idx]) {
                    answer_list[idx_stack.peek()] += Long.max(1, correction);
                    break;
                }

                answer_list[idx_stack.pop()] += Long.max(1, correction);
                if (popped_value == 0 || popped_value == person_stack.peek()) {
                    correction++;
                } else {
                    correction = 0;
                }
                popped_value = person_stack.pop();
            }
            person_stack.addFirst(people[idx]);
            idx_stack.addFirst(idx);
        }

        correction = 0;
        idx_stack.pop();
        while (idx_stack.size() > 0) {
            popped_value = person_stack.pop();
            answer_list[idx_stack.pop()] += correction;
            if (person_stack.peek() > popped_value) {
                correction = 0;
            } else {
                correction++;
            }
        }

        long answer = 0;
        for (int ans : answer_list) {
            answer += ans;
        }
        System.out.println(answer);

    }

}

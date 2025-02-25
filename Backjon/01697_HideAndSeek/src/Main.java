import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] str = bf.readLine().split(" ");
        int start_point = Integer.parseInt(str[0]);
        int end_point = Integer.parseInt(str[1]);
        int[] find_time = new int[100001];

        for (int i = 0; i < find_time.length; i++) {
            find_time[i] = -1;
        }
        Deque<Integer> idx_queue = new ArrayDeque<>();
        find_time[start_point] = 0;

        if (start_point - 1 >= 0) {
            find_time[start_point - 1] = 1;
            idx_queue.add(start_point - 1);
        }

        if (start_point < end_point) {
            if (start_point + 1 <= 100000) {
                find_time[start_point + 1] = 1;
                idx_queue.add(start_point + 1);
            }
            if (start_point * 2 <= 100000) {
                find_time[start_point * 2] = 1;
                idx_queue.add(start_point * 2);
            }
        }

        while (idx_queue.size() > 0) {
            int target = idx_queue.pop();
            if (find_time[end_point] != -1) {
                break;
            }

            if (target - 1 >= 0 && find_time[target - 1] == -1) {
                find_time[target - 1] = find_time[target] + 1;
                idx_queue.add(target - 1);
            }

            if (target > end_point) {
                continue;
            }

            if (target + 1 <= 100000 && find_time[target + 1] == -1) {
                find_time[target + 1] = find_time[target] + 1;
                idx_queue.add(target + 1);
            }
            if (target * 2 <= 100000 && find_time[target * 2] == -1) {
                find_time[target * 2] = find_time[target] + 1;
                idx_queue.add(target * 2);
            }
        }

        System.out.println(find_time[end_point]);
    }
}

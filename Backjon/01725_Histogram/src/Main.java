import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int[] histogram;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(bf.readLine());
        histogram = new int[num];
        for (int i = 0; i < num; i++) {
            histogram[i] = Integer.parseInt(bf.readLine());
        }

        System.out.println(divideConquer(0, histogram.length - 1));
    }

    static int divideConquer(int start_idx, int end_idx) {
        if (end_idx - start_idx < 1) {
            return histogram[start_idx];
        }

        int mid_idx = (start_idx + end_idx) / 2;
        int left_idx = mid_idx;
        int right_idx = mid_idx + 1;

        int mid_max = Integer.MIN_VALUE;
        int left_max = divideConquer(start_idx, mid_idx);
        int right_max = divideConquer(mid_idx + 1, end_idx);
        int height = Integer.MAX_VALUE;
        boolean move_left = true;
        boolean move_right = true;

        while (move_left || move_right) {
            height = Integer.min(height, Integer.min(histogram[left_idx], histogram[right_idx]));
            mid_max = Integer.max(mid_max, height * (right_idx - left_idx + 1));

            if (left_idx - 1 < start_idx) {
                move_left = false;
            }
            if (right_idx + 1 > end_idx) {
                move_right = false;
            }

            if (move_left && move_right) {
                if (histogram[left_idx - 1] > histogram[right_idx + 1]) {
                    left_idx -= 1;
                } else if (histogram[left_idx - 1] < histogram[right_idx + 1]) {
                    right_idx += 1;
                } else {
                    left_idx -= 1;
                    right_idx += 1;
                }
            } else if (move_left) {
                left_idx -= 1;
            } else if (move_right) {
                right_idx += 1;
            }

        }

        return Integer.max(mid_max, Integer.max(left_max, right_max));
    }
}

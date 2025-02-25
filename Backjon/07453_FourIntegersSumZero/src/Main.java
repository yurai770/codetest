
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int limiter;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(bf.readLine());
        int[][] board = new int[4][len];
        StringTokenizer st;

        for (int i = 0; i < len; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < 4; j++) {
                board[j][i] = Integer.parseInt(st.nextToken());
            }
        }

        int[] list1 = new int[len * len];
        int[] list2 = new int[len * len];
        limiter = list2.length - 1;

        int idx = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                list1[idx] = board[0][i] + board[1][j];
                list2[idx] = board[2][i] + board[3][j];
                idx++;
            }
        }
        Arrays.sort(list1);
        Arrays.sort(list2);
        long answer = 0;

        int before_target = Integer.MIN_VALUE;
        long findit = 0;
        for (int i = 0; i < list1.length; i++) {
            if (list1[i] != before_target) {
                findit = findAnswer(list2, -list1[i]);
                before_target = list1[i];
            }
            answer += findit;
        }

        System.out.println(answer);
    }

    static long findAnswer(int[] list, int target) {
        long answer = 0;
        int left_idx = 0;
        int right_idx = limiter;
        int mid_idx;
        int mid_num;
        boolean findit = false;

        while (true) {
            mid_idx = (left_idx + right_idx) / 2;
            mid_num = list[mid_idx];
            if (mid_num == target) {
                findit = true;
                break;
            }

            if (left_idx + 1 == right_idx) {
                if (list[right_idx] == target) {
                    findit = true;
                    mid_idx = right_idx;
                }
                break;
            }

            if (mid_num < target) {
                left_idx = mid_idx;
            } else {
                right_idx = mid_idx;
            }
        }

        if (findit) {
            answer++;
            int tmp = mid_idx;
            while (tmp < list.length - 1) {
                tmp++;
                if (list[tmp] != target) {
                    break;
                }
                answer++;
            }

            tmp = mid_idx;
            while (0 < tmp) {
                tmp--;
                if (list[tmp] != target) {
                    break;
                }
                answer++;
            }

            limiter = tmp + 1;
        }

        return answer;
    }
}

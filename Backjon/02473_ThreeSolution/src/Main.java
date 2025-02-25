import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(bf.readLine());
        int plus_size = 0, minus_size = 0;
        int[] sols = new int[len];
        AnswerC ansC = new AnswerC();

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < len; i++) {
            int tmp = Integer.parseInt(st.nextToken());
            if (tmp > 0) {
                plus_size++;
            } else {
                minus_size++;
            }
            sols[i] = tmp;
        }

        int[] plus_sol = new int[plus_size];
        int[] minus_sol = new int[minus_size];
        int plus_idx = 0, minus_idx = 0;
        for (int num : sols) {
            if (num > 0) {
                plus_sol[plus_idx++] = num;
            } else {
                minus_sol[minus_idx++] = num;
            }
        }

        Arrays.sort(plus_sol);
        Arrays.sort(minus_sol);

        // plus 3개
        if (plus_size >= 3) {
            ansC.checkAnswer(plus_sol[0], plus_sol[1], plus_sol[2]);
        }

        // minus 3개
        if (minus_size >= 3) {
            ansC.checkAnswer(minus_sol[minus_size - 1],
                    minus_sol[minus_size - 2], minus_sol[minus_size - 3]);
        }

        // 한쪽 리스트가 없음
        if (plus_size == 0 || minus_size == 0) {
            System.out.println(ansC.getAnsList());
            return;
        }

        // plus 2 + minus 1
        int limiter = minus_size - 1;
        for (int i = 0; i < plus_size; i++) {
            for (int j = i + 1; j < plus_size; j++) {
                int target = plus_sol[i] + plus_sol[j];
                int opt = findOptinumIdx(target, minus_sol, limiter);
                ansC.checkAnswer(plus_sol[i], plus_sol[j], minus_sol[opt]);
                limiter = opt;
            }
        }

        // plus 1 + minus 2
        limiter = plus_size - 1;
        for (int i = 0; i < minus_size; i++) {
            for (int j = i + 1; j < minus_size; j++) {
                int target = minus_sol[i] + minus_sol[j];
                int opt = findOptinumIdx(target, plus_sol, limiter);
                ansC.checkAnswer(minus_sol[i], minus_sol[j], plus_sol[opt]);
            }
        }

        System.out.println(ansC.getAnsList());
    }

    private static int findOptinumIdx(int origin_num, int[] list, int limiter) {
        int target = -origin_num;
        int left_idx, right_idx, mid_idx;
        left_idx = 0;
        right_idx = limiter;

        while (true) {
            mid_idx = (left_idx + right_idx) / 2;
            if (list[mid_idx] == target) {
                return mid_idx;
            }

            if (right_idx - left_idx <= 1) {
                if (target - list[left_idx] < list[right_idx] - target) {
                    return left_idx;
                } else {
                    return right_idx;
                }
            }

            if (list[mid_idx] < target) {
                left_idx = mid_idx;
            } else {
                right_idx = mid_idx;
            }

        }

    }
}

class AnswerC {
    private int answer;
    private int[] anslist;

    public AnswerC() {
        answer = Integer.MAX_VALUE;
        anslist = new int[3];
    }

    public void checkAnswer(int num1, int num2, int num3) {
        int tmp = Math.abs(num1 + num2 + num3);
        if (tmp < answer) {
            answer = tmp;
            anslist[0] = num1;
            anslist[1] = num2;
            anslist[2] = num3;
        }
    }

    public String getAnsList() {
        Arrays.sort(anslist);
        StringBuilder sb = new StringBuilder();
        for (int num : anslist) {
            sb.append(num).append(" ");
        }
        return sb.toString();
    }
}
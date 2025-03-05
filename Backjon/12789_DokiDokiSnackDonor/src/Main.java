
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        // 한 줄 버린다.
        bf.readLine();
        StringTokenizer st = new StringTokenizer(bf.readLine());

        // 사전 준비
        boolean canIt = false;
        int check_num = 1;
        Deque<Integer> stack = new ArrayDeque<>();

        // 토큰 비교 -> 안되면 스택 비교 -> 안되면 스택에 넣기
        // 토큰이 없는데 스택에서 못빼면 실패
        // 토큰도 스택도 전부다 비운다면 성공
        // next_token_num이 -1이라면 없는것으로 취급
        int next_token_num = -1;
        while (true) {
            if (next_token_num == -1) {
                if (st.hasMoreTokens()) {
                    next_token_num = Integer.parseInt(st.nextToken());
                }
            }

            if (next_token_num == check_num) {
                check_num++;
                next_token_num = -1;
                continue;
            }

            if (stack.isEmpty()) {
                if (next_token_num == -1) {
                    canIt = true;
                    break;
                } else {
                    stack.addFirst(next_token_num);
                    next_token_num = -1;
                    continue;
                }
            }

            if (stack.peek() == check_num) {
                stack.pop();
                check_num++;
                continue;
            }

            if (next_token_num != -1) {
                stack.addFirst(next_token_num);
                next_token_num = -1;
                continue;
            }

            canIt = false;
            break;
        }

        if (canIt) {
            System.out.println("Nice");
        } else {
            System.out.println("Sad");
        }
    }
}

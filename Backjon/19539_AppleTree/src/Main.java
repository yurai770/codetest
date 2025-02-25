import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        int[] arr = new int[N];
        String str = bf.readLine();
        StringTokenizer st = new StringTokenizer(str);
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int one = 0;
        int two = 0;
        Arrays.sort(arr);

        for (int idx = 0; idx < arr.length; idx++) {
            if (arr[idx] == 1) {
                two++;
                continue;
            }

            // 2가 남았다면 2 처리
            if (two > 0) {
                if (arr[idx] >= two * 2) {
                    arr[idx] -= two * 2;
                    two = 0;
                } else {
                    two -= arr[idx] / 2;
                    arr[idx] %= 2;
                }
            }
            // 아니면...
            // 1이 남았다면 1 처리
            if (one > 0) {
                if (arr[idx] >= one) {
                    arr[idx] -= one;
                    one = 0;
                } else {
                    one -= arr[idx];
                    arr[idx] = 0;
                }
            }

            /// 공통으로
            /// 3으로 나눈 나머지저장
            int rest = arr[idx] % 3;
            if (rest == 2) {
                one++;
            }
            if (rest == 1) {
                two++;
            }
        }

        if (one == 0 & two == 0) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}

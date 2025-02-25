import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int testcase = 1; testcase <= T; testcase++) {
            st = new StringTokenizer(bf.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int r1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());

            int long_r = Integer.max(r1, r2);

            int answer = -1;
            if (x1 == x2 && y1 == y2 && r1 == r2) {
                sb.append(answer).append("\n");
                continue;
            }

            double distance = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
            double r_sum = (double) (r1 + r2);
            if (distance == r_sum) {
                answer = 1;
            } else if (distance > r_sum) {

            }
            {
            }

            sb.append(answer).append("\n");
        }

        System.out.print(sb);
    }
}

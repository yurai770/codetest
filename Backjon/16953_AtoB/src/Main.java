import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int calculate = 0;
        boolean notfound = true;
        while (notfound) {
            calculate++;
            if (a == b) {
                notfound = false;
                break;
            }

            if (b == 0) {
                break;
            }
            if (b % 10 == 1) {
                b = b / 10;
                continue;
            }
            if ((b % 10) % 2 == 0) {
                b = b / 2;
                continue;
            }

            break;
        }

        if (notfound) {
            calculate = -1;
        }

        System.out.println(calculate);
    }
}

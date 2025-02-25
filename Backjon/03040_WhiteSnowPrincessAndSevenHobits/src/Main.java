import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] hobits = new int[9];
        int sum = 0;
        int i = 0;
        int j = 0;
        for (int idx = 0; idx < hobits.length; idx++) {
            hobits[idx] = Integer.parseInt(bf.readLine());
            sum += hobits[idx];
        }

        int tmp = 0;
        for (i = 0; i < hobits.length; i++) {
            for (j = i + 1; j < hobits.length; j++) {
                tmp = sum - hobits[i] - hobits[j];
                if (tmp == 100) {
                    break;
                }
            }
            if (tmp == 100) {
                break;
            }
        }

        for (int idx = 0; idx < hobits.length; idx++) {
            if (idx == i || idx == j) {
                continue;
            }
            System.out.println(hobits[idx]);
        }
    }
}

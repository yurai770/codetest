import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] SP = listConvert(bf.readLine().split(" "));
        String[] DNA_string = bf.readLine().split("");
        int[] ACGT = listConvert(bf.readLine().split(" "));

        for (int idx = 0; idx < SP[1]; idx++) {
            if (DNA_string[idx].equals("A")) {
                ACGT[0] -= 1;
            }
            if (DNA_string[idx].equals("C")) {
                ACGT[1] -= 1;
            }
            if (DNA_string[idx].equals("G")) {
                ACGT[2] -= 1;
            }
            if (DNA_string[idx].equals("T")) {
                ACGT[3] -= 1;
            }
        }

        int answer = checkPassword(ACGT);
        for (int new_idx = SP[1]; new_idx < SP[0]; new_idx++) {
            int old_idx = new_idx - SP[1];
            if (DNA_string[old_idx].equals("A")) {
                ACGT[0] += 1;
            }
            if (DNA_string[old_idx].equals("C")) {
                ACGT[1] += 1;
            }
            if (DNA_string[old_idx].equals("G")) {
                ACGT[2] += 1;
            }
            if (DNA_string[old_idx].equals("T")) {
                ACGT[3] += 1;
            }

            if (DNA_string[new_idx].equals("A")) {
                ACGT[0] -= 1;
            }
            if (DNA_string[new_idx].equals("C")) {
                ACGT[1] -= 1;
            }
            if (DNA_string[new_idx].equals("G")) {
                ACGT[2] -= 1;
            }
            if (DNA_string[new_idx].equals("T")) {
                ACGT[3] -= 1;
            }
            answer += checkPassword(ACGT);
        }

        System.out.println(answer);
    }

    static int checkPassword(int[] pw) {
        int ok = 1;
        for (int bit : pw) {
            if (bit > 0) {
                ok = 0;
                break;
            }
        }
        return ok;
    }

    private static int[] listConvert(String[] split) {
        int[] intlist = new int[split.length];
        for (int i = 0; i < intlist.length; i++) {
            intlist[i] = Integer.parseInt(split[i]);
        }
        return intlist;
    }
}

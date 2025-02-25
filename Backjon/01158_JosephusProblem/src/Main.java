import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] nk = listConvert(bf.readLine().split(" "));
        LinkedList<Integer> queue = new LinkedList<>();
        int target_idx;
        int[] anslist = new int[nk[0]];

        for (int i = 1; i <= nk[0]; i++) {
            queue.add(i);
        }

        for (int i = 0; i < nk[0]; i++) {
            // target_idx = nk[1] % queue.size();
            for (int j = 0; j < nk[1] - 1; j++) {
                queue.add(queue.pop());
            }

            anslist[i] = queue.pop();
        }

        for (int i = 0; i < anslist.length; i++) {
            if (i == 0) {
                System.out.print("<");
            } else {
                System.out.print(", ");
            }
            System.out.print(anslist[i]);
        }
        System.out.print(">");

    }

    static int[] listConvert(String[] strlist) {
        int[] intlist = new int[strlist.length];
        for (int i = 0; i < intlist.length; i++) {
            intlist[i] = Integer.parseInt(strlist[i]);
        }
        return intlist;
    }
}

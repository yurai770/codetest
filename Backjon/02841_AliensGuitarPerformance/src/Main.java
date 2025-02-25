import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] num_and_flat = listConvert(bf.readLine().split(" "));
        int[][] melody = new int[num_and_flat[0]][];
        int answer = 0;
        for (int i = 0; i < num_and_flat[0]; i++) {
            melody[i] = listConvert(bf.readLine().split(" "));
        }

        ArrayList<LinkedList> stacks = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            stacks.add(new LinkedList<Integer>());
        }

        for (int[] musicnote : melody) {
            LinkedList<Integer> target_stack = stacks.get(musicnote[0] - 1);
            while (true) {
                if (target_stack.size() <= 0) {
                    target_stack.add(musicnote[1]);
                    answer += 1;
                    break;
                }

                int tmp = target_stack.removeLast();
                if (tmp == musicnote[1]) {
                    target_stack.add(tmp);
                    break;
                }

                answer += 1;
                if (tmp < musicnote[1]) {
                    target_stack.add(tmp);
                    target_stack.add(musicnote[1]);
                    break;
                }
            }
        }
        System.out.println(answer);
    }

    static int[] listConvert(String[] split) {
        int[] intlist = new int[split.length];
        for (int i = 0; i < intlist.length; i++) {
            intlist[i] = Integer.parseInt(split[i]);
        }
        return intlist;
    }
}

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class MainFail {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        int[] mnk;
        LinkedList<int[]> cabbages;
        int[] anslist = new int[T];
        int answer;
        LinkedList<int[]> queue = new LinkedList<>();
        int[] target;
        int[][] around = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };

        for (int testcase = 0; testcase < T; testcase++) {
            answer = 0;
            mnk = listConvert(bf.readLine().split(" "));
            cabbages = new LinkedList<>();
            for (int i = 0; i < mnk[2]; i++) {
                cabbages.add(listConvert(bf.readLine().split(" ")));
            }

            cabbages.sort((o1, o2) -> {
                return o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1];
            });

            while (cabbages.size() > 0) {
                if (queue.size() <= 0) {
                    queue.add(cabbages.pop());
                    answer += 1;
                }
                target = queue.pop();
                for (int[] move : around) {
                    int[] moved = { target[0] + move[0], target[1] + move[1] };
                    for (int i = 0; i < cabbages.size(); i++) {
                        if (moved[0] == cabbages.get(i)[0] & moved[1] == cabbages.get(i)[1]) {
                            queue.add(moved);
                            cabbages.remove(i);
                            break;
                        }
                    }
                }
            }
            anslist[testcase] = answer;

        }

        for (int ans : anslist) {
            System.out.println(ans);
        }
    }

    static int[] listConvert(String[] strlist) {
        int[] intlist = new int[strlist.length];
        for (int i = 0; i < intlist.length; i++) {
            intlist[i] = Integer.parseInt(strlist[i]);
        }
        return intlist;
    }
}

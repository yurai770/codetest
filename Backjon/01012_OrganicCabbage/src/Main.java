import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        int[] mnk;
        int[][] cabbage_ground;
        int[] cabbage;
        int[][] around = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };
        int[] anslist = new int[T];
        int answer;
        LinkedList<int[]> que = new LinkedList<>();
        int[] now;

        for (int testcase = 0; testcase < T; testcase++) {
            answer = 0;
            mnk = listConvert(bf.readLine().split(" "));
            cabbage_ground = new int[mnk[0] + 2][mnk[1] + 2];
            for (int i = 0; i < mnk[2]; i++) {
                cabbage = listConvert(bf.readLine().split(" "));
                cabbage_ground[cabbage[0] + 1][cabbage[1] + 1] = 1;
            }

            for (int x = 1; x < cabbage_ground.length - 1; x++) {
                for (int y = 1; y < cabbage_ground[0].length - 1; y++) {
                    if (cabbage_ground[x][y] == 0) {
                        continue;
                    }

                    answer += 1;
                    cabbage_ground[x][y] = 0;
                    que.add(new int[] { x, y });
                    while (que.size() > 0) {
                        now = que.pop();
                        for (int[] move : around) {
                            if (cabbage_ground[now[0] + move[0]][now[1] + move[1]] == 1) {
                                cabbage_ground[now[0] + move[0]][now[1] + move[1]] = 0;
                                que.add(new int[] { now[0] + move[0], now[1] + move[1] });
                            }
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

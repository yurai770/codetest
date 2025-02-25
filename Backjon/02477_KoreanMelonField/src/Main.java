import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(bf.readLine());
        LinkedList<int[]> li = new LinkedList<>();
        int big_square = 1;
        int small_square = 1;

        int[] check = new int[4];
        for (int i = 0; i < 6; i++) {
            li.add(listConvert(bf.readLine().split(" ")));
            check[li.get(i)[0] - 1] += 1;
        }

        while (true) {
            int[] tmp = li.removeFirst();
            if (check[tmp[0] - 1] != 2) {
                li.addFirst(tmp);
                break;
            }
            li.add(tmp);
            continue;
        }

        boolean checker = true;
        for (int i = 0; i < li.size(); i++) {
            if (check[li.get(i)[0] - 1] == 1) {
                big_square *= li.get(i)[1];
                continue;
            }

            if (checker) {
                checker = false;
                continue;
            }

            small_square = li.get(i)[1] * li.get(i + 1)[1];
            i++;
            checker = true;
        }

        System.out.println((big_square - small_square) * num);

    }

    static int[] listConvert(String[] split) {
        int[] intlist = new int[split.length];
        for (int i = 0; i < intlist.length; i++) {
            intlist[i] = Integer.parseInt(split[i]);
        }

        return intlist;
    }
}

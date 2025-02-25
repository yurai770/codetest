import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int weight_len = Integer.parseInt(bf.readLine());
        int[] weights = listConvert(bf.readLine().split(" "));
        int bead_len = Integer.parseInt(bf.readLine());
        int[] beads = listConvert(bf.readLine().split(" "));

        Iterator<Integer> itr;
        Set<Integer> before_set = new TreeSet<>();
        Set<Integer> now_set = new TreeSet<>();
        int tmp;

        now_set.add(0);
        for (int weight : weights) {
            before_set = now_set;
            now_set = new TreeSet<>();
            itr = before_set.iterator();
            while (itr.hasNext()) {
                tmp = itr.next();
                now_set.add(tmp + weight);
                now_set.add(tmp);
                if (tmp > weight) {
                    now_set.add(tmp - weight);
                } else {
                    now_set.add(weight - tmp);
                }
            }
        }

        for (int bead : beads) {
            if (now_set.contains(bead)) {
                System.out.print("Y ");
                continue;
            }
            System.out.print("N ");
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

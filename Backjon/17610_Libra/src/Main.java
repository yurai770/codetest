import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws Exception {
        Set<Integer> before_set;
        Set<Integer> new_set = new HashSet<>();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(bf.readLine());
        int[] weights = listConvert(bf.readLine().split(" "));
        Iterator<Integer> itr;
        int tmp;
        int sum = 0;

        for (int weight : weights) {
            before_set = new HashSet<>(new_set);

            new_set.add(weight);
            sum += weight;

            itr = before_set.iterator();
            while (itr.hasNext()) {
                tmp = itr.next();
                new_set.add(tmp + weight);
                if (tmp - weight == 0) {
                    continue;
                }
                new_set.add(Math.abs(tmp - weight));
            }
        }

        System.out.println(sum - new_set.size());
    }

    private static int[] listConvert(String[] split) {
        int[] intlist = new int[split.length];
        for (int i = 0; i < intlist.length; i++) {
            intlist[i] = Integer.parseInt(split[i]);
        }
        return intlist;
    }
}

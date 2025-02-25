import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int limit;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        limit = n - m;
        st = new StringTokenizer(bf.readLine());
        List<Integer> numbers = new ArrayList<>();
        while (st.hasMoreTokens()) {
            numbers.add(Integer.parseInt(st.nextToken()));
        }
        numbers.sort((o1, o2) -> o1 - o2);

        String answer = doLoop(numbers, "");
        System.out.print(answer);
    }

    private static String doLoop(List<Integer> numbers, String str) {
        if (numbers.size() <= limit) {
            return str + "\n";
        }

        int checker = -1;
        String restr = "";
        for (int num : numbers) {
            if (checker == num) {
                continue;
            } else {
                checker = num;
            }

            String makestr;
            if (str.equals("")) {
                makestr = String.valueOf(num);
            } else {
                makestr = str + " " + num;
            }
            List<Integer> copylist = new ArrayList<>(numbers);
            copylist.remove(Integer.valueOf(num));
            restr += doLoop(copylist, makestr);
        }

        return restr;
    }
}

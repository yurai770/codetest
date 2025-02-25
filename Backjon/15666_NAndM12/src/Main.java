import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int M;
    static List<Integer> list;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Set<Integer> set = new HashSet<>();
        st = new StringTokenizer(bf.readLine());
        while (st.hasMoreTokens()) {
            set.add(Integer.parseInt(st.nextToken()));
        }

        list = new ArrayList<>(set);
        list.sort((o1, o2) -> o1 - o2);

        String answer = getLoop(0, 0, "");
        System.out.print(answer);
    }

    private static String getLoop(int idx, int depth, String str) {
        if (depth >= M) {
            return str + "\n";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = idx; i < list.size(); i++) {
            String st = str + list.get(i) + " ";
            String getstr = getLoop(i, depth + 1, st);
            sb.append(getstr);
        }

        return sb.toString();
    }
}

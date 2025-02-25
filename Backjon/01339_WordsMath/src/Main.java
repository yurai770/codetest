import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Character[][] operands;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        operands = new Character[N][];

        Set<Character> alphabets = new HashSet<>();
        for (int x = 0; x < N; x++) {
            String[] tmp = bf.readLine().split("");
            operands[x] = new Character[tmp.length];
            for (int y = 0; y < tmp.length; y++) {
                operands[x][y] = tmp[y].charAt(0);
                alphabets.add(operands[x][y]);
            }
        }

        List<Character> codes = new ArrayList<>();
        int answer = allChecker(codes, alphabets);
        System.out.println(answer);

    }

    private static int allChecker(List<Character> selected_alpha, Set<Character> not_selected_alpha) {
        if (not_selected_alpha.isEmpty()) {
            int sum = 0;

            int given = 9;
            int[] code = new int[26];
            for (Character alpha : selected_alpha) {
                code[alpha - 'A'] = given--;
            }
            for (int x = 0; x < operands.length; x++) {
                int tennnn = 1;
                for (int y = operands[x].length - 1; y >= 0; y--) {
                    sum += tennnn * code[operands[x][y] - 'A'];
                    tennnn *= 10;
                }
            }
            return sum;
        }

        int max_value = 0;
        for (Character target : not_selected_alpha) {
            List<Character> makeList = new ArrayList<>(selected_alpha);
            Set<Character> makeSet = new HashSet<>(not_selected_alpha);
            makeList.add(target);
            makeSet.remove(target);
            max_value = Integer.max(max_value, allChecker(makeList, makeSet));
        }
        return max_value;
    }
}
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    static int len;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] NM = listConvert(bf.readLine().split(" "));
        String[] sts = bf.readLine().split(" ");
        List<Integer> numbers = new LinkedList<>();
        for (String st : sts) {
            numbers.add(Integer.parseInt(st));
        }

        len = NM[0] - NM[1];

        numbers.sort((o1, o2) -> o1 - o2);
        String ans = makeLine("", numbers);

        System.out.println(ans);

    }

    private static String makeLine(String str, List<Integer> numbers) {
        if (numbers.size() <= len) {
            str += "\n";
            return str;
        }

        String restring = "";
        List<Integer> copylist;
        String newstr;
        for (int num : numbers) {
            newstr = new String();
            if (str.equals("")) {
                newstr = String.valueOf(num);
            } else {
                newstr = str + " " + num;
            }
            copylist = new ArrayList<>(numbers);
            copylist.remove(Integer.valueOf(num));
            restring += makeLine(newstr, copylist);
        }
        return restring;
    }

    private static int[] listConvert(String[] split) {
        int[] intlist = new int[split.length];
        for (int i = 0; i < intlist.length; i++) {
            intlist[i] = Integer.parseInt(split[i]);
        }
        return intlist;
    }
}

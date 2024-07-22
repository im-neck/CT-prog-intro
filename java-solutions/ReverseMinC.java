import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ReverseMinC {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] miin = new int[5];
        Arrays.fill(miin, Integer.MAX_VALUE);
        List<String> x = new ArrayList<>();
        StringBuilder bob = new StringBuilder();
        while (scan.hasNextLine()) {
            String perem = scan.nextLine();
            Scanner num = new Scanner(perem);
            int i = 0;
            while (num.hasNext()) {
                int now = num.nextInt();
                if (i == miin.length) {
                    miin = Arrays.copyOf(miin, miin.length * 3);
                    Arrays.fill(miin, i, miin.length, Integer.MAX_VALUE);
                }
                if (miin[i] > now) {
                    miin[i] = now;
                }
                bob.append(miin[i]);
                bob.append(" ");
                i++;
            }
            x.add(bob.toString());
            bob.setLength(0);
        }
        for (String s : x) {
            System.out.println(s);
        }
    }
}

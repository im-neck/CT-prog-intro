import java.util.Arrays;

public class ReverseMinCAbc {
    public static void main(String[] args) {
        MyScanner scan = new MyScanner(System.in);
        String perem1 = scan.nextLine();
        MyScanner num1 = new MyScanner(perem1);
        String[] miin = new String[100];
        Arrays.fill(miin, "kaeheidgeha");
        int j = 0;

        while (num1.hasNext()) {
            if (j >= miin.length) {
                miin = Arrays.copyOf(miin, miin.length * 2);
                Arrays.fill(miin, j, miin.length, "kaeheidgeha");
            }
            miin[j] = num1.next();
            if (miin[j] == null) System.err.println(miin.length);
            System.out.print(miin[j]);
            System.out.print(" ");
            j++;
        }

        System.out.println();

        while (scan.hasNextLine()) {
            String perem = scan.nextLine();
            MyScanner num = new MyScanner(perem);
            int i = 0;
            while (num.hasNext()) {
                String now = num.next();
                if (i >= miin.length) {
                    miin = Arrays.copyOf(miin, miin.length * 2);
                    Arrays.fill(miin, i, miin.length, "kaeheidgeha");
                }
                String sign_miin = "";
                String sign_now = "";
                if (miin[i] == null) System.err.println(i);
                if (miin[i].charAt(0) == '-') sign_miin = "-";
                if (now.charAt(0) == '-') sign_now = "-";
                if (sign_now.equals("-") && sign_miin.isEmpty()) {
                    miin[i] = now;
                    if (miin[i] == null) System.err.println(miin.length);
                } else if (sign_now.equals(sign_miin)) {
                    if (miin[i].length() > now.length() && sign_now.isEmpty()
                            || sign_now.equals("-") && miin[i].length() < now.length()) {
                        miin[i] = now;
                        if (miin[i] == null) System.err.println(miin.length);
                    } else if (miin[i].length() == now.length() && !miin[i].equals(now)) {
                        int char_num = 0;
                        while (char_num < now.length()) {
                            if (now.charAt(char_num) > miin[i].charAt(char_num)) {
                                if (sign_now.equals("-")) {
                                    miin[i] = now;
                                    if (miin[i] == null) System.err.println(miin.length);
                                }
                                break;
                            }
                            if (miin[i].charAt(char_num) > now.charAt(char_num)) {
                                if (sign_now.isEmpty()) {
                                    miin[i] = now;
                                    if (miin[i] == null) System.err.println(miin.length);
                                }
                                break;
                            }
                            char_num++;
                        }
                    }
                }
                System.out.print(miin[i]);
                System.out.print(" ");
                i++;
            }
            System.out.println();
        }
    }
}
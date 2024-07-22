import java.util.Arrays;

public class Reverse {
    public static void main(String[] var0) {
        MyScanner var1 = new MyScanner(System.in);
        int[][] var2 = new int[1][1];
        int var3;
        for (var3 = 0; var1.hasNextLine(); ++var3) {
            String var4 = var1.nextLine();
            MyScanner var5 = new MyScanner(var4);
            int var6 = var4.length();
            int[] var7 = new int[var6];
            Arrays.fill(var7, Integer.MAX_VALUE);

            for (int var8 = 0; var5.hasNext(); ++var8) {
                var7[var8] = var5.nextInt();
            }
            if (var2.length < var3 + 1) {
                var2 = (int[][]) Arrays.copyOf(var2, var2.length * 2);
            }
            var2[var3] = var7;
        }
        --var3;
        while (var3 >= 0) {
            for (int var9 = var2[var3].length - 1; var9 >= 0; --var9) {
                if (var2[var3][var9] < Integer.MAX_VALUE) {
                    System.out.print(var2[var3][var9] + " ");
                }
            }
            System.out.println();
            --var3;
        }
    }
}
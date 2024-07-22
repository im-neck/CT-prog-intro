import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class I {
    public static void main(String[] args) {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in)); // :NOTE: memory leak
        try {
            int n = Integer.parseInt(bf.readLine());

            String input1 = bf.readLine();
            String[] num1 = input1.split(" "); // :NOTE: regex + \\s+
            int x1 = Integer.parseInt(num1[0]);
            int y1 = Integer.parseInt(num1[1]);
            int h1 = Integer.parseInt(num1[2]);

            int xl_min = x1 - h1;
            int xr_max = x1 + h1;
            int yl_min = y1 - h1;
            int yr_max = y1 + h1;

            for (int i = 1; i < n; i++) {
                String input = bf.readLine();
                String[] num = input.split(" ");
                int x = Integer.parseInt(num[0]);
                int y = Integer.parseInt(num[1]);
                int h = Integer.parseInt(num[2]);
                if (xl_min > (x - h)) {
                    xl_min = x - h;
                }
                if (xr_max < (x + h)) {
                    xr_max = x + h;
                }
                if (yl_min > (y - h)) {
                    yl_min = y - h;
                }
                if (yr_max < (y + h)) {
                    yr_max = y + h;
                }

            }
            System.out.print((xl_min + xr_max) / 2 + " ");
            System.out.print((yl_min + yr_max) / 2 + " ");
            System.out.print((int) Math.ceil((double) Math.max(xr_max - xl_min, yr_max - yl_min) / 2));

        } catch (IOException e) {
            throw new RuntimeException(e); // :NOTE: ???
        }
    }
}

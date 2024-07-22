import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A {
    public static void main(String[] args) {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        try {
            String input = bf.readLine();
            String[] num = input.split(" ");
            System.out.println((int) Math.round(2 * Math.ceil((Double.parseDouble(num[2]) - Double.parseDouble(num[1])) / (Double.parseDouble(num[1]) - Double.parseDouble(num[0]))) + 1));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

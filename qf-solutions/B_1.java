import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int k = -710*25000;
        for (; n>0;n--){
            System.out.println(k);
            k+=710;
        }
    }
}
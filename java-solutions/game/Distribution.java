package game;
import java.util.Random;
public class Distribution {

            public static int[] distribution(int countBots, int range ) {


                int[] arr = new int[countBots];
                Random rand = new Random();

                for (int i = 0; i < countBots; i++) {
                    int num = rand.nextInt(range ) + 1;
                    boolean contains = false;
                    for (int j = 0; j < i; j++) {
                        if (arr[j] == num) {
                            contains = true;
                            break;
                        }
                    }
                    if (!contains) {
                        arr[i] = num;
                    } else {
                        i--;
                    }
                }
                return arr;
               /* for (int i = 0; i < countBots; i++) {
                    System.out.print(arr[i] + " ");
                }*/
        }

    }


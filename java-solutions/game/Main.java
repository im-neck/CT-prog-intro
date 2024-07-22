package game;

import java.util.*;


public class Main {
    public static void main(String[] args) {

        System.out.println("Hi, Bro! You want to play, don't you? Enter m, n, k! (enter 3 numbers separated by a space) ");
        Scanner sc = new Scanner(System.in);
        String mString;
        String nString;
        String kString;
        int m;
        int n;
        int k;
        while (true) {
            try {
                mString = sc.next();
                nString = sc.next();
                kString = sc.next();
                m = Integer.parseInt(mString);
                n = Integer.parseInt(nString);
                k = Integer.parseInt(kString);
                if (m <= 0 || n <= 0 || k <= 0) {
                    throw new NumberFormatException();
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Oh bro, you entered incorrect data" +
                        " Try again, you will definitely succeed!");

            } catch (NoSuchElementException e) {
                System.out.println("Oh my goodness! What are you doing ? Please, don't do this again");
                System.exit(6);
            }
        }
        System.out.println("You're cool! Maybe you want to play in a tournament? (yes/no)");
        String ans = sc.next();
        if (ans.equalsIgnoreCase("Yes")) {
            //tournament(m, n, k);
            System.out.println("Hey buddy you’d like to play with  bots?(yes/no)");
            ans = sc.next();
            while (true) {
                if (ans.equalsIgnoreCase("No")) {
                    tournamentWithNoBots(m, n, k);
                    break;
                } else if (ans.equalsIgnoreCase("Yes")) {
                    tournamentWithYesBots(m, n, k);
                    break;
                } else {
                    System.out.println("Oooh! Honey, what did you do? Write just yes or no, please... I believe in you");
                }
            }
        }

        if (ans.equalsIgnoreCase("No")) {
            final Game game = new Game(false, new HumanPlayer(), new HumanPlayer());
            int result;
            do {
                result = game.play(new MNKboard(m, n, k));
                System.out.println("Game result: " + result);
            } while (result != 0);
        }
    }


    private static void tournamentWithNoBots(int m, int n, int k) {

        System.out.println("How many persons will play?" + System.lineSeparator());
        Scanner sc = new Scanner(System.in);
        int numberOfPlayers;
        while (true) {
            try {
                String move1 = sc.nextLine();
                Scanner scanFromLine = new Scanner(move1);
                numberOfPlayers = scanFromLine.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("I'm sorry, but you entered incorrect numbers." +
                        "Try again, you will definitely succeed!");
            } catch (NoSuchElementException e) {
                System.out.println("Oh my goodness! What are you doing ? Please, don't do this again");
                System.exit(6);

            }

        }
        int num_powerTwo = 1;
        while (num_powerTwo < numberOfPlayers) {
            num_powerTwo *= 2;
        }
        num_powerTwo /= 2;
        List<Integer> winners = new ArrayList<>();
        for (int i = 1; i <= numberOfPlayers; i++) {
            winners.add(i);
        }

        int i = 0;
        int result;

        Game game = new Game(false, new HumanPlayer(), new HumanPlayer());
        System.out.println("list of players :" + winners + "(at the qualifying stage)");
        do {
            System.out.print(winners.get(i));
            System.out.print(" VS ");
            System.out.println(winners.get(i + 1));
            result = game.playTournament(new MNKboard(m, n, k));
            System.out.println("Game result: " + result);
            if (result == 0) {
                continue;
            }

            winners.remove(i + result - 1);
            i += 1;

        } while (num_powerTwo != winners.size());

        i = 0;
        System.out.println("list of players :" + winners + "(in the tournament)");

        do {
            num_powerTwo /= 2;
            if (winners.size() == 4) System.out.println("in the semifinals: " + winners);
            if (winners.size() == 2) System.out.println("in the finals: " + winners);
            i = 0;

            do {
                System.out.print(winners.get(i));
                System.out.print(" VS ");
                System.out.println(winners.get(i + 1));
                result = game.playTournament(new MNKboard(m, n, k));
                System.out.println("Game result: " + result);
                if (result == 0) {
                    continue;
                }

                winners.remove(i + result - 1);
                i += 1;

            } while (winners.size() > num_powerTwo);
        } while (1 != winners.size());
        System.out.println("winner: " + winners.get(0) + "Urrraaaaaaaaaaaaaa!");



    }

    private static void tournamentWithYesBots(int m, int n, int k) {
        System.out.println("How many bots will play with you and how many human (enter 2 numbers separated by a space)");
        System.out.println("( 1-number of bots; 2-number of humans )");
        Scanner scan = new Scanner(System.in);
        int countBots = scan.nextInt();
        int countHuman = scan.nextInt();
        int[] arr;
        arr = Distribution.distribution(countBots, countHuman + countBots);
        List<Character> players = new ArrayList<>();
        int i;
        for (i = 0; i < countHuman + countBots; i++) {
            players.add('H');
        }

        for (i = 0; i < countBots; i++) {
            players.set(arr[i] - 1, 'B');
        }

        int num_powerTwo = 1;
        while (num_powerTwo < countHuman + countBots) {
            num_powerTwo *= 2;
        }
        num_powerTwo /= 2;
        List<Integer> winners = new ArrayList<>();
        for (i = 1; i <= countHuman + countBots; i++) {
            winners.add(i);
        }
        i = 0;
        int result;
        Random rand = new Random();
        Game gameHH = new Game(false, new HumanPlayer(), new HumanPlayer());
        Game gameBB = new Game(false, new RandomPlayer(rand,m,n), new RandomPlayer(rand,m,n));
        Game gameBH = new Game(false, new RandomPlayer(rand,m,n), new HumanPlayer());
        if (num_powerTwo != winners.size()){
        System.out.println("list of players :" + winners + "(at the qualifying stage)");
        }
        do {
            // выглядит как копипаста
            System.out.print(winners.get(i) + "-" + players.get(i));
            System.out.print(" VS ");
            System.out.println(winners.get(i + 1) + "-" + players.get(i + 1));
            if (players.get(i) == 'H' && players.get(i + 1) == 'H') {

                result = gameHH.playTournament(new MNKboard(m, n, k));

            } else if (players.get(i) == 'B' && players.get(i + 1) == 'B') {
                result = gameBB.playTournament(new MNKboard(m, n, k));
            } else {
                result = gameBH.playTournament(new MNKboard(m, n, k));
            }
            if (result == 0) {
                continue;
            }
            int l = i + result - 1;
            int w = i + 3 - result - 1;
            System.out.println("lose: " + winners.get(l) + "; won: " + winners.get(w));

            players.remove(i + result - 1);
            winners.remove(i + result - 1);
            i++;

        } while (num_powerTwo != winners.size());

        i = 0;
        System.out.println("list of players :" + winners + "(in the tournament)");

        do {
            num_powerTwo /= 2;
            if (winners.size() == 4) System.out.println("in the semifinals: " + winners);
            if (winners.size() == 2) System.out.println("in the finals: " + winners);
            i = 0;

            do {
                System.out.print(winners.get(i) + "-" + players.get(i));
                System.out.print(" VS ");
                System.out.println(winners.get(i + 1) + "-" + players.get(i + 1));
                if (players.get(i) == 'H' && players.get(i + 1) == 'H') {
                    result = gameHH.playTournament(new MNKboard(m, n, k));
                } else if (players.get(i) == 'B' && players.get(i + 1) == 'B') {
                    result = gameBB.playTournament(new MNKboard(m, n, k));
                } else {
                    result = gameBH.playTournament(new MNKboard(m, n, k));
                }

                int l = i + result - 1;
                int w = i + 3 - result - 1;
                System.out.print("lose: " + winners.get(l) + "; won: " + winners.get(w) + System.lineSeparator());
                if (result == 0) {
                    continue;
                }
                players.remove(i + result - 1);
                winners.remove(i + result - 1);
                i += 1;

            } while (winners.size() > num_powerTwo);
        } while (1 != winners.size());
        System.out.println("winner: " + winners.get(0) + " Urrraaaaaaaaaaaaaa!");

    }
}


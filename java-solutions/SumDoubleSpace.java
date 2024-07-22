public class SumDoubleSpace {
    public static void main(String[] args) {

        double ans = 0d;
        int start = 0;
        int fin = 0;
        for (int i = 0; i < args.length; i++) {
            args[i] = args[i].trim();
            start = 0;
            fin = 0;
            for (int j = 0; j < args[i].length(); j++) {
                if (Character.getType(args[i].charAt(j)) != Character.SPACE_SEPARATOR) {
                    fin = j;
                } else {
                    if (Character.getType(args[i].charAt(start)) != Character.SPACE_SEPARATOR) {
                        ans += Double.parseDouble(args[i].substring(start, fin + 1));
                    }
                    if (j + 1 < args[i].length()) {
                        start = j + 1;
                    } else start = j;
                }
            }
            if (!args[i].isEmpty() && Character.getType(args[i].charAt(start)) != Character.SPACE_SEPARATOR) {
                ans += Double.parseDouble(args[i].substring(start, fin + 1));
            }
        }
        System.out.print(ans);
    }
}
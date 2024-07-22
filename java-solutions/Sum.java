public class Sum {
    public static void main(String[] args) {
        int ans = 0;
        int start = 0;
        int fin = 0;
        for (int i = 0; i < args.length; i++) {
            args[i] = args[i].trim();
            start = 0;
            fin = 0;
            for (int j = 0; j < args[i].length(); j++) {
                if (!Character.isWhitespace(args[i].charAt(j))) {
                    fin = j;
                } else {
                    if (!Character.isWhitespace(args[i].charAt(start))) {
                        ans = ans + Integer.parseInt(args[i].substring(start, fin + 1));
                    }
                    if (j + 1 < args[i].length()) {
                        start = j + 1;
                    } else start = j;
                }
            }
            if (!args[i].isEmpty() && !Character.isWhitespace(args[i].charAt(start))) {
                ans = ans + Integer.parseInt(args[i].substring(start, fin + 1));
            }
        }
        // ans=ans+Integer.parseInt(args[args.length-1].substring(start, fin+1));
        System.out.println(ans);

    }
}

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

public class WordStatInput {
    public static void main(String[] args) {
        int start = 0;
        int fin = 0;
        Map<String, Integer> map = new LinkedHashMap<>();

        StringBuilder data = new StringBuilder();
        String u;
        String infile = args[0];
        String outfile = args[1];

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(infile), StandardCharsets.UTF_8));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outfile), StandardCharsets.UTF_8))) {


            int character;
            while ((character = reader.read()) != -1) {
                data.append((char) character);
            }

            for (int i = 0; i < data.length(); i++) {
                if (Character.isLetter(data.charAt(i))
                        || Character.getType(data.charAt(i)) == Character.DASH_PUNCTUATION || data.charAt(i) == '\'') {
                    fin++;
                } else {
                    if (i != 0 && (Character.isLetter(data.charAt(i - 1))
                            || Character.getType(data.charAt(i - 1)) == Character.DASH_PUNCTUATION
                            || data.charAt(i - 1) == '\'')) {
                        u = data.substring(start, fin).toLowerCase();

                        if (map.containsKey(u)) {
                            int value = map.get(u);
                            value++;
                            map.put(u, value);
                        } else {
                            map.put(u, 1);
                        }
                    }
                    start = i + 1;
                    fin = start;
                }
            }
            if (start < data.length()) {
                map.put(data.substring(start, data.length()).toLowerCase(),
                        map.getOrDefault(data.substring(start, data.length()).toLowerCase(), 0) + 1);
            }
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                writer.write(entry.getKey() + " " + entry.getValue() + "\n");
            }

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

    }
}
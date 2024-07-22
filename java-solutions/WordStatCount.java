import java.nio.charset.StandardCharsets;
import java.util.*;

import java.io.*;

public class WordStatCount {
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
                if (Character.isLetter(data.charAt(i)) || Character.getType(data.charAt(i)) == Character.DASH_PUNCTUATION || data.charAt(i) == '\'') {
                    fin = i + 1;
                } else {
                    if (i != 0 && (Character.isLetter(data.charAt(i - 1))
                            || Character.getType(data.charAt(i - 1)) == Character.DASH_PUNCTUATION
                            || data.charAt(i - 1) == '\'')) {

                        u = data.substring(start, fin).toLowerCase();

                        map.put(u, map.getOrDefault(u, 0) + 1);//!!!!!!!
                    }
                    start = i + 1;
                    fin = start;
                }
            }
            if (start < data.length()) {
                map.put(data.substring(start, data.length()).toLowerCase(),
                        map.getOrDefault(data.substring(start, data.length()).toLowerCase(), 0) + 1);
            }

            List<Map.Entry<String,Integer>> list = new ArrayList<>(map.entrySet());
            list.sort((entry1, entry2) -> entry1.getValue().compareTo(entry2.getValue()));
            for (Map.Entry entry : list) {
                writer.write(entry.getKey() + " " + entry.getValue());
                writer.newLine();
            }

        }catch(IOException e){
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}


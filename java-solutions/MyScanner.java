import java.io.*;
import java.util.Arrays;
import java.util.InputMismatchException;

public class MyScanner {
    private final String separator = System.lineSeparator();
    private int index = 0;
    private char[] buf = new char[128];
    private final Reader reader;

    MyScanner(InputStream input) {
        this.reader = new InputStreamReader(input);
        reading();
    }

    MyScanner(String input) {
        this.reader = new StringReader(input);
        reading();
    }

    MyScanner(File input) throws IOException {
        this.reader = new InputStreamReader(new FileInputStream(input));
        reading();
    }

    private void reading() {
        try {
            int am_chars = reader.read(buf); //сколько сможет, столько возьмет
            if (am_chars == -1) am_chars++;
            if (am_chars < buf.length) buf = Arrays.copyOfRange(buf, 0, am_chars);
            index = 0;
        } catch (IOException e) {
            System.out.println("Reading problem" + e.getMessage());
        }
    }


    public String nextLine() {
        StringBuilder bob_the_builder = new StringBuilder();
        if (System.lineSeparator().length() == 2) {
            for (; index < buf.length; index++) {
                bob_the_builder.append(buf[index]);
                int sepCount = 0;
                while ((separator.indexOf(buf[index]) != -1)) {
                    sepCount++;
                    if (index + 1 == buf.length) {
                        reading();
                        index--;
                    }
                    index++;
                    if (buf[index] == separator.charAt(separator.length() - 1)) {
                        index++;
                        break;
                    }
                }
                if (sepCount != 0) {
                    return bob_the_builder.substring(0, bob_the_builder.length() - sepCount);
                }
                if (index + 1 == buf.length) {
                    reading();
                    index--;
                }
            }
        } else {
            for (; index < buf.length; index++) {
                bob_the_builder.append(buf[index]);
                if (buf[index] == System.lineSeparator().charAt(0)) {
                    index++;
                    return bob_the_builder.substring(0, bob_the_builder.length() - 1);
                }
                if (index + 1 == buf.length) {
                    reading();
                    index--;
                }
            }
        }
        return "";
    }

    public boolean hasNextLine() {
        if (index == buf.length) reading();
        return index < buf.length;
    }

    public String next() {
        StringBuilder bob_the_builder = new StringBuilder();
        for (; index < buf.length; index++) {
            if (Character.getType(buf[index]) != Character.SPACE_SEPARATOR && buf[index] != separator.charAt(0)) {
                for (; index < buf.length && Character.getType(buf[index]) != Character.SPACE_SEPARATOR
                        && buf[index] != separator.charAt(0) && buf[index] != 0; index++) {
                    bob_the_builder.append(buf[index]);
                    if (index + 1 == buf.length) {
                        reading();
                        index--;
                    }
                }
                return bob_the_builder.toString();
            }
            if (index + 1 == buf.length) {
                reading();
                index--;
            }
        }
        return "";
    }

    public int nextInt() {
        try {
            return Integer.parseInt(next());
        } catch (InputMismatchException e) {
            System.err.println("It's not int: " + e);
            System.exit(1);
        }
        return -1;
    }

    public boolean hasNext() {
        for (; index < buf.length; index++) {
            if (Character.getType(buf[index]) != Character.SPACE_SEPARATOR && buf[index] != separator.charAt(0)) //тут не пишем про ноль? :/
            {
                return true;
            }
            if (index + 1 == buf.length) {
                reading();
                index--;
            }
        }
        return false;
    }

    public String nextWord() {
        StringBuilder bob_the_builder = new StringBuilder();
        for (; index < buf.length; index++) {
            if (Character.getType(buf[index]) == Character.DASH_PUNCTUATION && buf[index] != '\'') {
                for (; index < buf.length && Character.getType(buf[index]) == Character.DASH_PUNCTUATION
                        && buf[index] != '\''; index++) {
                    bob_the_builder.append(buf[index]);
                    if (index + 1 == buf.length) {
                        reading();
                        index--;
                    }
                }
                return bob_the_builder.toString();
            }
            if (index + 1 == buf.length) {
                reading();
                index--;
            }
        }
        return "";
    }
}

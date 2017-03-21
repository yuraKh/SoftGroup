package topPhrases;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * This class is generate random text file from another
 * which has 50 phrases per line separated by a pipe (|).
 * @author  Yury Khodanitcky
 */
public class FileGenerator {

    private List<String> list;
    private Random randomGenerator;

    public static void main(String[] args) {

        FileGenerator fileGenerator = new FileGenerator();
        fileGenerator.save("example.txt");
    }

    /**
     * Constructs <tt>FileGenerator</tt> with the default initial
     * list of phrases from source text file.
     */
    public FileGenerator() {
        randomGenerator = new Random();
        list = choose();
    }

    /**
     * Return random phrase from list.
     * @return random phrase from list.
     */
    public String anyItem() {
        int index = randomGenerator.nextInt(list.size());
        String item = list.get(index);
        return item;
    }

    /**
     * Create new text file which has 50 phrases per line separated by a pipe (|).
     * @param fileName
     */
    private void save(String fileName) {
        File file = new File(fileName);
        BufferedWriter output;
        int count = 0;
        try {
            output = new BufferedWriter(new FileWriter(file));

            for (int i = 0; i < 1000; i++) {
                for (int n = 0; n < 50; n++) {
                    count++;
                    output.write(anyItem() + "|");
                }
                output.write('\n');

            }
            System.out.print(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Return list of words, which is lounge than 3 characters, from source file.
     *
     * @return list of words, which is lounge than 3 characters, from source file.
     */
    public List<String> choose() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("new.txt"));
            String line = reader.readLine();
            List<String> words = new ArrayList<>();
            while (line != null) {
                String[] wordsLine = line.split(" ");
                for (String word : wordsLine) {
                    word = word.replaceAll("[^а-яА-ЯёЁa-zA-Z\\s]", "");
                    if (!word.equals("")&&word.length()>3) {
                        words.add(word);
                    }
                }
                line = reader.readLine();
            }
            return words;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
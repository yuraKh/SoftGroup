package topPhrases;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Used to find the most used phrases from the input File.
 * @author  Yury Khodanitcky
 */
public class TopPhrases {


    public static void main(String[] args) throws IOException {

        TopPhrases topPhrases = new TopPhrases();

        List<Path> path = topPhrases.getPath(new File("example.txt"), 500);
        Multiset<String> multiset = topPhrases.getTop(path);
        System.out.print(multiset);
    }

    /**
     * Retunr list of Phases to temp files.
     *
     * @param file      source text file
     * @param maxMemory memory limit
     * @return list of Phases to temp files.
     */
    public List<Path> getPath(File file, int maxMemory) {

        List<String> phrases = new ArrayList<>(maxMemory);
        List<Path> tempFiles = new ArrayList<>();
        try {

            BufferedReader reader = new BufferedReader(new FileReader(file));

            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                String[] linePhrases = line.split("\\|");
                for (String phrase : linePhrases) {
                    if (phrases.size() >= maxMemory)
                        tempFiles.add(saveTempFile(phrases));
                    phrases.add(phrase);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempFiles;
    }

    /**
     * Return Path to temp file.
     * @param phrases list of phrases which will be saved to temp file.
     * @return Path to temp file.
     */
    private Path saveTempFile(List<String> phrases) {

        try {
            Path tempFile = Files.createTempFile("TopPhrases", "");
            BufferedWriter writer = Files.newBufferedWriter(tempFile);
            for (String s : phrases) {
                writer.write(s);
                writer.newLine();
            }
            writer.close();
            phrases.clear();
            return tempFile;
        } catch (IOException e) {
            throw new RuntimeException("Filed to save into tmp file.", e);
        }
    }

    /**
     * Return Multiset of phrases from temp file.
     * @param path path to temp file.
     * @return Multiset of phrases from temp file.
     * @throws IOException file not found.
     */
    private Multiset<String> getPhrases(Path path) throws IOException {

        Multiset<String> multiset = HashMultiset.create();
        BufferedReader reader = Files.newBufferedReader(path);
        for (String line = reader.readLine(); line != null; line = reader.readLine()) {
            multiset.add(line);
        }

        reader.close();
        Files.deleteIfExists(path);
        return Multisets.copyHighestCountFirst(multiset);
    }

    /**
     * Return sorted Multiset in which top phrases is first.
     * @param pathList list of Phases to temp files.
     * @return sorted Multiset in which top phrases is first.
     * @throws IOException file not found.
     */
    public Multiset<String> getTop(List<Path> pathList) throws IOException {
        Multiset<String> multiset = HashMultiset.create();
        for (Path p : pathList) {
            multiset.addAll(getPhrases(p));
        }
        return Multisets.copyHighestCountFirst(multiset);
    }
}
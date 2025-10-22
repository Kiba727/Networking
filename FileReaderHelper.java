
/**
 * Name(s): 	Mack Bautista, Patrick Dang
 * Email: 		mbaut981@mtroyal.ca, pdang289@mtroyal.ca
 * Course: 		COMP3533-002
 * Instructor: 	Mingwei Gong
 * Lab: 		5
 * Due Date: 	Oct. 23, 2025
 */

package Networking;

import java.io.*;
import java.util.*;

/**
 * Class Name: FileReaderHelper
 * Purpose: This class is designed to read words.txt
 * @author Mack Bautista
 * @author Patrick Dang
 */
public class FileReaderHelper {

    public static final int NO_WORDS = 0;

    private List<String> words;

    public FileReaderHelper(String fileName) throws IOException {
        words = new ArrayList<>();
        loadWords(fileName);
    }

    private void loadWords(String fileName) throws IOException {
        BufferedReader br = new BufferedReader((new FileReader(fileName)));
        String line;

        while ((line = br.readLine()) != null) {
            if (!line.trim().isEmpty()) {
                words.add(line.trim());
            }
        }

        br.close();
        System.out.println("Loaded " + words.size() + " words from " + fileName);
    }

    public String getMatchingWords(String prefix) {
        StringBuilder matches = new StringBuilder();
        int count = 0;
        for (String w : words) {
            if (w.toLowerCase().startsWith(prefix.toLowerCase())) {
                if (count > 0) {
                   matches.append(", "); 
                }
                matches.append(w);
                count++;
            }
        }

        if (matches.isEmpty()) {
            return "No words starting with " + prefix;
        }

        return matches.toString();
    }

    private void printAllWords() {
        System.out.println("Printing all loaded words.");
        for (String word : words) {
            System.out.println(word);
        }
        System.out.println("End of word list (" + words.size() + " words total).");
    }
}

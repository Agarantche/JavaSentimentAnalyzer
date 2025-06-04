/**
 * This programs analyzes the words from a large number of movie reviews with ratings, and it will keep tracks
 * of all the words found in the reviews, and it will also keep track of scores, and it will use those words and scores
 * to rate the phrase that is typed and checks if that random phrase is either good or bad, and it will give it a score by
 * averaging the scores of all the words in the phrase together. the scoring range will be between the range 0-4
 * 2 means the phrase is neutral, anything about 2 means the phrase is positive, and anything under 2 will mean the phrase is
 * negative.
 *
 *
 * @Author: Adam Garantche
 * Date: 11/7/2024
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SentimentAnalyzer {
    public static void main(String[] args) throws FileNotFoundException {
        // Initialize scanners for user input
        Scanner in = new Scanner(System.in);

        // Gets file paths from user and creates new files
        System.out.print("Enter a stop word file: ");
        String stop = in.nextLine();
        File stopFile = new File(stop);
        System.out.print("Enter a review file: ");
        String reviews = in.nextLine();
        File reviewFile = new File(reviews);

        // Creates file scanners for the stop and review text
        Scanner stopTxt = new Scanner(stopFile);
        Scanner reviewTxt = new Scanner(reviewFile);

        // Initializes word tables for the stop and review text
        WordTable stopTable = new WordTable();
        WordTable reviewTable = new WordTable();
        WordTree results = new WordTree(); // Initializes a word tree for the results

        // Reads each line of the stop text and adds it to the stop table with a placeholder score
        while (stopTxt.hasNextLine()) {
            String data = stopTxt.nextLine();
            stopTable.add(data, 0);
        }

        // Reads each line of the review text and adds it to the review table if it's not a stop word
        while (reviewTxt.hasNextLine()) {
            int score = reviewTxt.nextInt();
            String data = reviewTxt.nextLine().toLowerCase();
            String word = ""; // Initializes a string holder

            // Loop to parse and build each word character by character
            for (int i = 0; i < data.length(); i++) {
                if ((data.charAt(i) >= 'a' && data.charAt(i) <= 'z') || data.charAt(i) == '\'' || data.charAt(i) == '-') {
                    word += data.charAt(i);
                } else {
                    word = cleanString(word);
                    if (!word.isEmpty() && !stopTable.contains(word)) {
                        reviewTable.add(word, score);
                    }
                    word = "";
                }
            }
        }

        // Puts words into a review file line by line (excluding stop words) listed in alphabetical order with their corresponding scores
        PrintWriter out = new PrintWriter(new File(reviewFile + ".words"));
        reviewTable.print(out);
        out.close();


        // Prompt to read in and analyze a new phrases
        System.out.print("Would you like to analyze a phrase? (yes/no): ");
        String analyzeAnswer = in.nextLine().toLowerCase();

        // Reading in new phrases if prompt is answered yes
        while (analyzeAnswer.equals("yes")) {
            System.out.print("Enter phrase: ");
            String phrase = in.nextLine().toLowerCase();
            double totalScore = 0;
            int wordCount = 0;

            // Processes phrase word by word
            String word = "";
            for (int i = 0; i < phrase.length(); i++) {
                char ch = phrase.charAt(i);
                if (Character.isLetter(ch) || ch == '\'' || ch == '-') {
                    word += ch;
                } else {
                    word = cleanString(word);
                    if ((!word.isEmpty()) && !stopTable.contains(word)) {
                        totalScore += reviewTable.getScore(word);
                        wordCount++;
                    }
                    word = "";
                }
            }
            word = cleanString(word); // Utilizes cleanString method on words

            // Calculates total score count and word count in review table
            if (!word.isEmpty() && !stopTable.contains(word)) {
                totalScore += reviewTable.getScore(word);
                wordCount++;
            }

            // Handles phrase rating by checking score
            if (wordCount == 0) {
                System.out.println("Your phrase contained no words that affect sentiment.");
            } else {
                double phraseScore = totalScore / wordCount;
                System.out.printf("Score: %.3f\n", phraseScore);
                if (phraseScore > 2.0) {
                    System.out.println("Your phrase was positive.");
                } else if (phraseScore < 2.0) {
                    System.out.println("Your phrase was negative.");
                } else {
                    System.out.println("Your phrase was perfectly neutral.");
                }
            }

            //Prompt the user for an answer again
            System.out.print("Would you like to analyze another phrase? (yes/no): ");
            analyzeAnswer = in.nextLine().toLowerCase();
        }

    }

    /**
     * String cleaning method to remove hyphens and single quotations
     *
     * @param word that is being cleaned
     * @return clean word without hyphen or single quotations
     */
    public static String cleanString(String word) {
       while (!word.isEmpty() && (word.charAt(0) == '\'' || word.charAt(0) == '-')){
           word = word.substring(1);
       }
       while (!word.isEmpty() && (word.charAt(word.length() - 1) == '\'' || (word.charAt(word.length() - 1 ) == '-'))){
           word = word.substring(0, word.length() - 1);
       }
       return word;
    }
}

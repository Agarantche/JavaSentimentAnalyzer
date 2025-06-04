import java.io.PrintWriter;

/**
 * WordTable - Manages a collection of WordTree instances, one for each letter of the alphabet.
 *
 * The WordTable class represents a collection of WordTrees, where each WordTree is associated with a
 * specific letter of the alphabet. This structure allows for efficient storage and retrieval of words
 * and their associated sentiment scores.
 */
public class WordTable {
    private WordTree[] table = new WordTree[26];

    // Method creates a new word table at each 26 positions in the word tree
    public WordTable() {
        for (int i = 0; i < table.length; i++) {
            table[i] = new WordTree();
        }
    }

    /**
     * Method adds a word and a score into the word table
     *
     * @param word that is being added
     * @param score that is being added
     */
    public void add(String word, int score) {
        table[word.charAt(0) - 'a'].add(word, score);
    }

    /**
     * Method that gets the corresponding score with a given word
     *
     * @param word that the score is trying to be found from
     * @return the score associated with the word
     */
    public double getScore(String word) {
        return table[word.charAt(0) - 'a'].getScore(word);
    }

    /**
     * Method that checks if a word is already present within it
     *
     * @param word that is being checked if its already present
     * @return a true or false statement stating if the word is in the table or not
     */
    public boolean contains(String word) {
        if (word.charAt(0) < 'a' || word.charAt(0) > 'z') {
            return false;
        }
        return table[word.charAt(0) - 'a'].contains(word);
    }

    /**
     * Prints the contents of the WordTable to the provided PrintWriter in a manner that
     * provides sentiment scores and the number of time each word has been encountered
     *
     * @param out PrintWriter that will output the content into a file
     */
    public void print(PrintWriter out) {
        for (int i = 0; i < table.length; i++) {
            table[i].print(out);
        }
    }
}

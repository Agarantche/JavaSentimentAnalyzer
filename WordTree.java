import java.io.PrintWriter;

/**
 * WordTree - A binary search tree for storing and accessing word sentiment data.
 *
 * The WordTree class represents a binary search tree that stores words, their associated sentiment scores,
 * and the number of times each word has been encountered. It provides methods for adding new words,
 * checking if a word is contained in the tree, retrieving the average sentiment score for a given word,
 * and printing the contents of the tree to a PrintWriter.
 */
public class WordTree {
    // Node content initializers
    private static class Node {
        public String word;
        public int score;
        public int count;
        public boolean black;
        public Node left;
        public Node right;

        // Node content setters
        public Node(String word, int score, boolean black) {
            this.word = word;
            this.score = score;
            this.count = 1;
            this.black = black;
            this.left = null;
            this.right = null;
        }

        /**
         * Getter method to calculate the average score count
         *
         * @return average score count
         */
        public double getAverageScore() {
            return (double) score / count;
        }
    }

    private Node root = null;

    /**
     * Method to add a word with it's corresponding score into the word tree
     *
     * @param word that will be added to the word tree
     * @param score that will be added to the word tree
     */
    public void add(String word, int score) {
        root = add(root, word, score);
        root.black = true;
    }

    /**
     * Method to add a new node into the word tree
     *
     * @param node that is being analyzed
     * @param word is the word that is being added to the node
     * @param score is the score being added to the node
     * @return the node that was added to the tree
     */
    private static Node add(Node node, String word, int score) {
        if (node == null) {
            return new Node(word, score, false);
        }
        int compare = word.compareTo(node.word);
        if (compare < 0) {
            node.left = add(node.left, word, score);
        } else if (compare > 0) {
            node.right = add(node.right, word, score);
        } else {
            node.score += score;
            node.count++;
        }
        return node;
    }

    /**
     * Method to see if a word is within a tree
     *
     * @param word that is being checked
     * @return a true or false stating if the word was found in the tree
     */
    public boolean contains(String word) {
        return get(root, word) != null;
    }

    /**
     * Method to get the score of a word
     *
     * @param word that is being used to check its score
     * @return the score of the word
     */
    public double getScore(String word) {
        Node node = get(root, word);
        if (node == null) {
            return 2.0;
        } else {
            return node.getAverageScore();
        }
    }

    /**
     * Getter method to get a node and the word contained within it
     *
     * @param node a place or container within the word tree
     * @param word the value that is being held within the node
     * @return the node
     */
    private static Node get(Node node, String word) {
        if (node == null) {
            return null;
        }

        int compare = word.compareTo(node.word);
        if (compare < 0) {
            return get(node.left, word);
        } else if (compare > 0) {
            return get(node.right, word);
        } else {
            return node;
        }
    }


    /**
     * Print method for the word tree
     *
     * @param out
     */
    public void print(PrintWriter out) {
        printInOrder(root, out);

    }

    /**
     * Recursively prints the word tree out in order
     *
     * @param node is the current position
     * @param out the file to write the tree contents to
     */
    private void printInOrder(Node node, PrintWriter out) {
        if (node != null) {
            printInOrder(node.left, out);
            out.println(node.word + "\t" + node.score + "\t" + node.count);
            printInOrder(node.right, out);
        }
    }

}

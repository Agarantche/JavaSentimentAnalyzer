# Java Movie Review Sentiment Analyzer

This Java application performs sentiment analysis on text, specifically designed to process movie reviews. It builds a sentiment lexicon from a dataset of reviews and their associated numerical ratings (typically 0-4). The program then filters out common "stop words" and uses the learned word scores to calculate and classify the sentiment (positive, negative, or neutral) of new user-provided phrases.

The core data structure for managing the sentiment lexicon is a custom implementation of a hash table where each entry (indexed by the first letter of a word) points to a Binary Search Tree (`WordTree`) containing words starting with that letter and their aggregated sentiment scores.

## Features

* **Sentiment Lexicon Building:** Reads movie reviews and their scores to learn the sentiment value of individual words.
* **Stop Word Filtering:** Ignores common words (e.g., "the", "a", "is") that generally don't carry significant sentiment, using a provided stop word list.
* **Custom Data Structures:**
    * `WordTree`: A Binary Search Tree to store words, their total accumulated sentiment score, and the count of occurrences.
    * `WordTable`: An array of `WordTree`s (acting as a hash table based on the first letter) for efficient word lookup.
* **Word Cleaning:** Basic preprocessing of words to handle leading/trailing punctuation (apostrophes, hyphens) and convert to lowercase.
* **Phrase Analysis:** Takes user input (a phrase or sentence) and calculates an overall sentiment score by averaging the scores of its constituent words (if found in the lexicon).
* **Sentiment Classification:** Classifies the analyzed phrase as positive, negative, or neutral based on its calculated score (e.g., >2.0 is positive, <2.0 is negative, 2.0 is neutral).
* **Output Word List:** Generates a file listing all unique words found in the reviews (excluding stop words) along with their total score and count.
* **Interactive CLI:** Prompts the user for input files and phrases to analyze.

## Technologies Used

* **Java:** Core programming language.
* **Data Structures:** Custom implementation of Binary Search Trees (`WordTree`) and a hash table like structure (`WordTable`).
* **File I/O:** `java.io.File`, `java.util.Scanner`, `java.io.PrintWriter` for reading input files (reviews, stop words) and writing output.
* **String Manipulation:** For parsing lines, extracting words, and cleaning text.
* **Basic Natural Language Processing (NLP) Concepts:** Sentiment scoring, stop word removal.

## How to Compile and Run

1.  **Prerequisites:**
    * Java Development Kit (JDK) installed (e.g., JDK 8 or later).

2.  **Download Files:**
    * Ensure you have all necessary `.java` files in a single directory:
        * `SentimentAnalyzer.java` (Main class with CLI)
        * `WordTable.java`
        * `WordTree.java`
    * You will also need two input text files:
        * A **stop word file** (e.g., `stopwords.txt`): Each line contains one stop word.
        * A **review file** (e.g., `moviereviews.txt`): Each line starts with an integer score (0-4) followed by the review text.

3.  **Compile:**
    * Open a terminal or command prompt.
    * Navigate to the directory where you saved the `.java` files.
    * Compile all the Java files:
        ```bash
        javac SentimentAnalyzer.java WordTable.java WordTree.java
        ```
        (Or `javac *.java` if they are the only Java files)

4.  **Run:**
    * After successful compilation, run the main class (`SentimentAnalyzer.java`):
        ```bash
        java SentimentAnalyzer
        ```
    * The program will prompt you to enter the path to your stop word file and then your review file.
    * After processing, it will ask if you want to analyze a phrase.

## Input File Formats

* **Stop Word File:** Plain text, one stop word per line.
    Example (`stopwords.txt`):
    ```
    a
    an
    the
    is
    ```
* **Review File:** Plain text. Each line must start with an integer sentiment score (e.g., 0, 1, 2, 3, 4) followed by a space, and then the review text.
    Example (`moviereviews.txt`):
    ```
    4 This movie was absolutely fantastic and a joy to watch!
    0 I hated every single boring minute of it.
    2 It was an okay film, neither good nor bad.
    ```

## Project Structure

* `SentimentAnalyzer.java`: Contains the `main` method, handles user interaction, file processing logic, and phrase analysis.
* `WordTable.java`: Manages an array of `WordTree` objects, distributing words based on their first letter.
* `WordTree.java`: Implements a Binary Search Tree to store words and their associated sentiment scores and counts.

---

*Developed by Adam Garantche

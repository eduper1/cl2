import java.util.*;

public class TextAnalysisTool {

    // Main method (entry point)
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get paragraph input
        String text = getParagraphInput(scanner);

        // Calculate character and word counts
        int charCount = text.length();
        String[] words = text.split("\\s+");
        int wordCount = words.length;

        // Find the most common character (case-insensitive, excluding spaces)
        char mostCommonChar = ' ';
        int maxCharCount = 0;
        if (!text.isEmpty()) {
            Map<Character, Integer> charFrequency = new HashMap<>();
            for (char c : text.toCharArray()) {
                if (c != ' ') { // Exclude spaces
                    char lowerCaseChar = Character.toLowerCase(c);
                    charFrequency.put(lowerCaseChar, charFrequency.getOrDefault(lowerCaseChar, 0) + 1);
                    if (charFrequency.get(lowerCaseChar) > maxCharCount) {
                        maxCharCount = charFrequency.get(lowerCaseChar);
                        mostCommonChar = lowerCaseChar;
                    }
                }
            }
        }

        // Get frequency of a user-specified character (case-insensitive)
        char targetChar = getCharacterInput(scanner);
        int charFreq = 0;
        for (char c : text.toCharArray()) {
            if (Character.toLowerCase(c) == Character.toLowerCase(targetChar)) {
                charFreq++;
            }
        }

        // Get frequency of a user-specified word (case-insensitive)
        String targetWord = getWordInput(scanner);
        int wordFreq = 0;
        for (String word : words) {
            if (word.equalsIgnoreCase(targetWord)) {
                wordFreq++;
            }
        }

        // Calculate unique words (case-insensitive)
        Set<String> uniqueWords = new HashSet<>();
        for (String word : words) {
            uniqueWords.add(word.toLowerCase());
        }
        int uniqueWordCount = uniqueWords.size();

        // Display results
        System.out.println("\n--- Text Analysis Results ---");
        System.out.println("1. Character Count: " + charCount);
        System.out.println("2. Word Count: " + wordCount);
        System.out.printf("3. Most Common Character: '%s' (appeared %d times)\n",
                text.isEmpty() ? "None" : mostCommonChar, maxCharCount);
        System.out.println("4. Frequency of Character '" + targetChar + "': " + charFreq);
        System.out.println("5. Frequency of Word '" + targetWord + "': " + wordFreq);
        System.out.println("6. Number of Unique Words: " + uniqueWordCount);

        scanner.close();
    }

    // Helper method: Get paragraph input with validation
    private static String getParagraphInput(Scanner scanner) {
        String text;
        do {
            System.out.print("Enter a paragraph: ");
            text = scanner.nextLine().trim();
            if (text.isEmpty()) {
                System.out.println("Paragraph cannot be empty. Try again.");
            }
        } while (text.isEmpty());
        return text;
    }

    // Helper method: Get valid single character input
    private static char getCharacterInput(Scanner scanner) {
        String input;
        do {
            System.out.print("\nEnter a character to check its frequency: ");
            input = scanner.nextLine().trim();
            if (input.length() != 1) {
                System.out.println("Please enter exactly one character.");
            }
        } while (input.length() != 1);
        return input.charAt(0);
    }

    // Helper method: Get valid single-word input
    private static String getWordInput(Scanner scanner) {
        String input;
        do {
            System.out.print("\nEnter a word to check its frequency: ");
            input = scanner.nextLine().trim().toLowerCase();
            if (input.isEmpty()) {
                System.out.println("Word cannot be empty.");
            } else if (input.contains(" ")) {
                System.out.println("Please enter a single word with no spaces.");
            }
        } while (input.isEmpty() || input.contains(" "));
        return input;
    }
}
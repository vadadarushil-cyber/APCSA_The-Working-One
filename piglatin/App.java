package piglatin;

public class App {
    public static void main(String[] args)
    {
        int score = TestSuite.run();

        if (score > 4)
        {
            Book input = new Book();

            // Load test data
            input.readFromUrl("TestBook", "Dog\nCat\nMouse");

            // Load online text
            input.readFromUrl("The Popular Magazine", "https://www.gutenberg.org/cache/epub/1513/pg1513.txt");

            // Print first 3 lines of the original
            input.printLines(0, 2);

            // Translate to Pig Latin
            Book output = PigLatinTranslator.translate(input);

            // Print first 3 lines of translated version
            output.printLines(0, 2);

            // Save to file
            output.writeToFile("test.txt");
        }
    }
}

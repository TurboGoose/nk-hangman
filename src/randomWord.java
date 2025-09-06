import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Scanner;

public class randomWord {
    public static final int lives = 5;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Отвиснем в игре 'Висельница'? Напишите 'ДА', чтобы начать, или 'НЕ', чтобы не отвиснуть.");
        while (true) {
            String line = sc.nextLine().toUpperCase();
            if (line.equals("ДА")) {
                HideWord();
            } else if (line.equals("НЕ")) {
                System.out.println("BYE-BYE");
                sc.close();
                System.exit(0);
            } else {
                System.out.println("ПИШИ ТО, ЧТО Я УКАЗАЛ!!! 'ДА' или 'НЕ'");
            }
        }
    }

    public static char readLetterFromConsol() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Вводи");
        String letter = scan.next().toUpperCase().trim();
        while (!letter.matches("^[А-ЯЁ]$")) {
            System.out.println("По-русски пиши и по одной букве");
            letter = scan.next().toUpperCase().trim();
        }
        return letter.charAt(0);
    }

    public static void HideWord() throws FileNotFoundException {
        String path = "src/словарь программиста.txt";
        File fr = new File(path);
        Scanner sc = new Scanner(fr);
        String line = sc.nextLine();
        String[] words = line.split(" ");
        Random random = new Random();
        int rndIndex = random.nextInt(words.length);
        String rndword = words[rndIndex].toUpperCase();
        String masked = "*".repeat(rndword.length());
        StringBuilder maskedWord = new StringBuilder(masked);
        SeekLetter(rndword, maskedWord);
    }

    public static void SeekLetter(String rndword, StringBuilder maskedWord){
        int tries = 0;
        char letter;
        List<Character> usedLetters = new ArrayList<>();
        while (tries < lives) {
            System.out.println(maskedWord);
            System.out.println();

            if (!usedLetters.isEmpty()) {
                System.out.println("Использованные буквы: ");
                usedLetters.forEach(System.out::print);
                System.out.println();
            }
            letter = readLetterFromConsol();
            if (usedLetters.contains(letter)) {
                System.out.println("поношена тобой");
                continue;
            }
            if (rndword.contains(String.valueOf(letter))) {
                for (int i = 0; i < rndword.length(); i++) {
                    if (letter == rndword.charAt(i)) {
                        maskedWord.setCharAt(i, letter);
                        System.out.println("\n                          \n");
                    }
                }
                if (!String.valueOf(maskedWord).contains("*")) {
                    System.out.println(rndword + "\nЭто победа! Играем 'ДА' 'НЕ'?");
                    return;
                }
            } else {
                System.out.println("Мимо.Попробуй другую букву.");
                tries++;
                HangmanDrawer(tries);
                if (tries == lives) {
                    System.out.println("Ну вот и повисели. Неугаданное слово " + rndword + "\nИграем 'ДА' 'НЕ'?");
                    return;
                }
            }
            usedLetters.add(letter);
        }
    }

    public static void HangmanDrawer(int tries){
        String[] hangmanStages = {"  +---+\n" +
                "  |   |\n" +
                "      |\n" +
                "      |\n" +
                "      |\n" +
                "      |\n" +
                "=========",
                "  +---+\n" +
                        "  |   |\n" +
                        "  O   |\n" +
                        " /|   |\n" +
                        "      |\n" +
                        "      |\n" +
                        "=========",
                "  +---+\n" +
                        "  |   |\n" +
                        "  O   |\n" +
                        " /|\\  |\n" +
                        "      |\n" +
                        "      |\n" +
                        "=========",
                "  +---+\n" +
                        "  |   |\n" +
                        "  O   |\n" +
                        " /|\\  |\n" +
                        " /    |\n" +
                        "      |\n" +
                        "=========",
                "  +---+\n" +
                        "  |   |\n" +
                        "  O   |\n" +
                        " /|\\  |\n" +
                        " / \\  |\n" +
                        "      |\n" +
                        "========="
        };
        System.out.println(hangmanStages[tries-1]);
    }
}
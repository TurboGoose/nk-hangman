import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Scanner;


public class randomWord {
    public static final int lives = 5;

    public static void main(String[] args) throws FileNotFoundException {
        choice();
    }

    public static void choice() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.println("�������� � ���� '����������'? �������� '��', ����� ������, ��� '��', ����� �� ���������.");
        while(true) {
            String line = sc.nextLine().toUpperCase();
            if (line.equals("��")) {
                Game();
            } else if (line.equals("��")) {
                System.out.println("BYE-BYE");
                sc.close();
                System.exit(0);
            } else {
                System.out.println("���� ��, ��� � ������!!! '��' ��� '��'");
            }
        }

    }

    public static char enter() {
        Scanner scan = new Scanner(System.in);
        System.out.println("�����");
        String letter = scan.next().toUpperCase().trim();
        while (!letter.matches("^[�-ߨ]$")) {
            System.out.println("��-������ ���� � �� ����� �����");
            letter = scan.next().toUpperCase().trim();
        }
        return letter.charAt(0);
    }

    public static void Game() throws FileNotFoundException {
        int tries = 0;
        String path = "src/������� ������������.txt";
        File fr = new File(path);
        Scanner sc = new Scanner(fr);
        String line = sc.nextLine();
        String[] words = line.split(" ");
        Random random = new Random();
        int rndIndex = random.nextInt(words.length);
        String rndword = words[rndIndex].toUpperCase();
        String masked = "*".repeat(rndword.length());
        StringBuilder maskedWord = new StringBuilder(masked);
        System.out.println(rndword);
        List<Character> usedLetters = new ArrayList<>();




        char letter;
        while (tries < lives) {
            System.out.println(maskedWord);
            System.out.println();


            if (!usedLetters.isEmpty()) {
                System.out.println("�������������� �����: ");
                usedLetters.forEach(System.out::print);
                System.out.println();
            }
            letter = enter();
            if (usedLetters.contains(letter)) {
                System.out.println(" �������� �����");
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
                    System.out.println(rndword + "\n�� �������� ��, � ��� ������? �������");
                    choice();

                }
            } else {
                System.out.println("����.�������� ������ �����.");
                tries++;
                grade(tries);
                if (tries == lives) {
                    System.out.println("�� ��� � ��������. ����������� ����� " + rndword);
                    choice();
                }


            }
            usedLetters.add(letter);

        }
    }
    public static void grade(int tries){
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









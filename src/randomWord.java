import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class randomWord {
    public static final int lives = 5;

    public static void main(String[] args) throws FileNotFoundException {
        manu();
    }

    public static void manu() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Отвиснем? Напишите 'ДА', чтобы начать, или 'НЕ', чтобы не отвиснуть.");
        String a = sc.nextLine().toUpperCase();
        String b = "ДА";
        String c = "НЕ";
        if (a.equals(b)) {
            Game();
        } else if (a.equals(c)) {
            System.out.println("BYE-BYE");
            sc.close();
        } else {
            System.out.println("ПИШИ ТО, ЧТО Я УКАЗАЛ!!!");
            manu();
        }

    }

    public static char letterFromConsole() {
        char letter;
        Scanner scan = new Scanner(System.in);
        System.out.println("Буква");
        letter = scan.next().toUpperCase().charAt(0);
        if (!Character.toString(letter).matches("[А-ЯЁ]")) {
            do {
                System.out.println("По-русски пиши");
                letter = scan.next().toUpperCase().charAt(0);
            } while (!Character.toString(letter).matches("[А-ЯЁ]"));
        }
        return letter;
    }

    public static void Game() throws FileNotFoundException {
        int tries = 0;
        String sep = File.separator;
        String path = sep + "C:" + sep + "Users" + sep + "ASUS" + sep + "IdeaProjects" + sep + "test" + sep + "words2";
        File fr = new File(path);
        Scanner sc = new Scanner(fr);
        String line = sc.nextLine();
        String[] words = line.split(" ");
        // BLYAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAT'
        Random random = new Random();
        int rndIndex = random.nextInt(words.length);
        String rndword = words[rndIndex].toUpperCase();
        StringBuilder maskedWord = new StringBuilder(rndword.replaceAll(".", "*"));
        System.out.println(rndword);
        List<Character> usedLetters = new ArrayList<>();




        char letter;
        while (tries < lives) {
            System.out.println(maskedWord);
            System.out.println();


            if (!usedLetters.isEmpty()) {
                System.out.println("Использованные буквы: ");
                usedLetters.forEach(System.out::print);
                System.out.println();
            }
            letter = letterFromConsole();
            if (usedLetters.contains(letter)) {
                System.out.println(" поношена(-ы) тобой");
                continue;
            }
            if (rndword.contains(String.valueOf(letter))) {
                for (int i = 0; i < rndword.length(); i++) {
                    if (letter == rndword.charAt(i)) {
                        maskedWord.setCharAt(i, letter);
                    }
                }
                if (!String.valueOf(maskedWord).contains("*")) {
                    System.out.println(rndword + "\nНу закончил ты, и что дальше? Поновой");
                    manu();


                }
            } else {
                System.out.println("Мимо.Попробуй другую букву.");
                tries++;
                if (tries == lives) {
                    System.out.println("|----------");
                    System.out.println("|         |");
                    System.out.println("|        ()");
                    System.out.println("|        /|) ");
                    System.out.println("|         |");
                    System.out.println("|        / ) ");
                    System.out.println("|");
                    System.out.println("|");
                    System.out.println("|");
                    System.out.println("Ну вот и повисели. Неугаданное слово " + rndword);
                    manu();
                }



            }
            if (tries == 1) {
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
            } else if (tries == 2) {
                System.out.println("|----------");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
            } else if (tries == 3) {
                System.out.println("|----------");
                System.out.println("|         |");
                System.out.println("|        ()");
                System.out.println("|        /|) ");
                System.out.println("|         |");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
            } else if (tries == 4) {
                System.out.println("|----------");
                System.out.println("|         |");
                System.out.println("|        ()");
                System.out.println("|        /|) ");
                System.out.println("|         |");
                System.out.println("|        /  ");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");

            }

            usedLetters.add(letter);


        }




    }


}








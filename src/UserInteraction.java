import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class UserInteraction {

    //    Реализуем взаимодействие пользователя и программы посредством консольного меню
    static {
        System.out.println("Добро пожаловать в программу \"криптоанализатор\"!\n" +
                "Feci quod potui, faciant meliora potentes!");
    }

    public static void startEnciphering() {
        System.out.println("Что желает Ваше Августейшее Величество?\n" +
                "1: Шифрование текста\n" +
                "2: Дешифрование текста, ключ известен :)\n" +
                "3: Дешифрование текста, ключ неизвестен :(\n" +
                "4: Выход из программы");

        Scanner sc = new Scanner(System.in);
        while (!sc.hasNextInt()) {
            System.out.println("Мой Повелитель, не смею перечить, но челом бью! Введите цифру от 1 до 4");
            sc = new Scanner(System.in);
        }
        int choice = sc.nextInt();
        switch (choice) {
            case 1 -> {
                System.out.println("Отличный выбор!\nВведите путь к файлу с текстом");
                Scanner kb1 = new Scanner(System.in);
                String path1 = kb1.nextLine();
                while (!Files.isRegularFile(Path.of(path1))) {
                    System.out.println("Хм... Не похоже на путь к файлу:( попробуйте еще раз");
                    path1 = kb1.nextLine();
                }
                System.out.println("Введите ключ");
                int key1 = 0;
                while (!kb1.hasNextInt()) {
                    System.out.println("А это точно ключ?:) введите число и нажмите Enter");
                    kb1 = new Scanner(System.in);
                }
                key1 = kb1.nextInt();
                CaesarCipher.encrypt(path1, key1);
                startEnciphering();
            }
            case 2 -> {
                System.out.println("Отличный выбор!\nВведите путь к файлу с текстом");
                Scanner kb2 = new Scanner(System.in);
                String path2 = kb2.nextLine();
                while (!Files.isRegularFile(Path.of(path2))) {
                    System.out.println("Хм... Не похоже на путь к файлу:( попробуйте еще раз");
                    path2 = kb2.nextLine();
                }
                System.out.println("Введите ключ");
                int key2 = 0;
                while (!kb2.hasNextInt()) {
                    System.out.println("А это точно ключ?:) введите число и нажмите Enter");
                    kb2 = new Scanner(System.in);
                }
                key2 = kb2.nextInt();
                CaesarCipher.decrypt(path2, key2);
                startEnciphering();
            }
            case 3 -> {
                System.out.println("Отличный выбор!\nВведите путь к файлу с текстом");
                Scanner kb3 = new Scanner(System.in);
                String path3 = kb3.nextLine();
                while (!Files.isRegularFile(Path.of(path3))) {
                    System.out.println("Хм... Не похоже на путь к файлу:( попробуйте еще раз");
                    path3 = kb3.nextLine();
                }
                CaesarCipher.bruteforce(path3);
                startEnciphering();
            }
            case 4 -> System.out.println("Аве Цезарь!");
            default -> {
                System.out.println("Мой Повелитель, не смею перечить, но челом бью! Введите цифру от 1 до 4");
                startEnciphering();
            }
        }
    }
}
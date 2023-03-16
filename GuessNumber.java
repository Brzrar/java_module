import java.util.Scanner;
import java.util.Random;

public class GuessNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int maxNumber = random.nextInt(991) + 10; // генерируем случайное число от 10 до 1000
        int numberOfAttempts = 0;

        System.out.println("Загадайте число от 10 до " + maxNumber);
        System.out.println("1. Загадал");
        System.out.println("2. Выход");
        System.out.print("Ваш выбор: ");

        int choice = scanner.nextInt();
        while (choice != 2) {
            if (choice == 1) {
                int minNumber = 10;
                int guessedNumber;

                while (true) {
                    guessedNumber = (maxNumber + minNumber) / 2; // берем среднее значение между минимальным и максимальным числом
                    numberOfAttempts++;

                    System.out.println("Вы загадали " + guessedNumber + "?");
                    System.out.println("1. Больше");
                    System.out.println("2. Меньше");
                    System.out.println("3. Угадал");
                    System.out.println("4. Выход");
                    System.out.print("Ваш выбор: ");

                    choice = scanner.nextInt();
                    if (choice == 3) {
                        System.out.println("Угадал! Количество попыток: " + numberOfAttempts);
                        break;
                    } else if (choice == 1) {
                        minNumber = guessedNumber + 1;
                    } else if (choice == 2) {
                        maxNumber = guessedNumber - 1;
                    } else if (choice == 4) {
                        break;
                    } else {
                        System.out.println("Выберите действие из списка!");
                    }
                }
            } else {
                System.out.println("Выберите действие из списка!");
            }

            System.out.println();
            System.out.println("Загадайте число от 10 до " + maxNumber);
            System.out.println("1. Загадал");
            System.out.println("2. Выход");
            System.out.print("Ваш выбор: ");
            choice = scanner.nextInt();
            maxNumber = random.nextInt(991) + 10; // генерируем новое случайное число
            numberOfAttempts = 0;
        }
    }
}

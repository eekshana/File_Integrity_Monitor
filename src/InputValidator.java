import java.util.Scanner;

public class InputValidator {

    public static int readMenuChoice(
            Scanner scanner) {

        while (true) {

            System.out.print(
                    "Enter your choice: ");

            String input =
                    scanner.nextLine().trim();

            try {

                int choice =
                        Integer.parseInt(input);

                if (choice >= 1 && choice <= 4) {
                    return choice;
                }

                System.out.println(
                        "Please enter a number from 1 to 4.");

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid input. Please enter a number.");
            }
        }
    }

    public static String readDirectoryPath(
            Scanner scanner) {

        while (true) {

            System.out.print(
                    "Enter directory path: ");

            String path =
                    scanner.nextLine().trim();

            if (!path.isEmpty()) {
                return path;
            }

            System.out.println(
                    "Directory path cannot be empty.");
        }
    }
}
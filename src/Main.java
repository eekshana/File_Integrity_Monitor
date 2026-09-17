import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println();
        System.out.println("========================================");
        System.out.println("       FILE INTEGRITY MONITOR");
        System.out.println("========================================");

        boolean running = true;

        while (running) {

            displayMenu();

            int choice =
                    InputValidator.readMenuChoice(scanner);

            switch (choice) {

                case 1:
                    createBaseline(scanner);
                    break;

                case 2:
                    checkIntegrity(scanner);
                    break;

                case 3:
                    showBaseline();
                    break;

                case 4:
                    running = false;
                    System.out.println(
                            "Thank you for using File Integrity Monitor.");
                    break;

                default:
                    System.out.println(
                            "Invalid choice.");
            }
        }

        scanner.close();
    }

    private static void displayMenu() {

        System.out.println();
        System.out.println("----------------------------------------");
        System.out.println("1. Create / Update Baseline");
        System.out.println("2. Check File Integrity");
        System.out.println("3. Show Baseline Files");
        System.out.println("4. Exit");
        System.out.println("----------------------------------------");
    }

    private static void createBaseline(
            Scanner scanner) {

        String directoryPath =
                InputValidator.readDirectoryPath(scanner);

        System.out.println();
        System.out.println(
                "Scanning directory...");

        ArrayList<FileRecord> records =
                FileScanner.scanDirectory(
                        directoryPath);

        if (records.isEmpty()) {

            System.out.println(
                    "No files were found.");

            return;
        }

        System.out.println(
                "Files found: " + records.size());

        StorageManager.saveBaseline(records);
    }

    private static void checkIntegrity(
            Scanner scanner) {

        if (!StorageManager.baselineExists()) {

            System.out.println();
            System.out.println(
                    "No baseline exists.");

            System.out.println(
                    "Create a baseline first using option 1.");

            return;
        }

        String directoryPath =
                InputValidator.readDirectoryPath(scanner);

        System.out.println();
        System.out.println(
                "Scanning current files...");

        ArrayList<FileRecord> current =
                FileScanner.scanDirectory(
                        directoryPath);

        ArrayList<FileRecord> baseline =
                StorageManager.loadBaseline();

        ArrayList<FileRecord> added =
                IntegrityChecker.findAddedFiles(
                        baseline,
                        current);

        ArrayList<FileRecord> modified =
                IntegrityChecker.findModifiedFiles(
                        baseline,
                        current);

        ArrayList<FileRecord> deleted =
                IntegrityChecker.findDeletedFiles(
                        baseline,
                        current);

        ReportGenerator.generateReport(
                added,
                modified,
                deleted);
    }

    private static void showBaseline() {

        if (!StorageManager.baselineExists()) {

            System.out.println();
            System.out.println(
                    "No baseline has been created yet.");

            return;
        }

        ArrayList<FileRecord> records =
                StorageManager.loadBaseline();

        System.out.println();
        System.out.println(
                "========================================");

        System.out.println(
                "          BASELINE FILES");

        System.out.println(
                "========================================");

        System.out.println(
                "Total files: " + records.size());

        System.out.println();

        for (FileRecord record : records) {

            System.out.println(
                    record.getFilePath());

            System.out.println(
                    "SHA-256: " + record.getHash());

            System.out.println(
                    "Size: " + record.getFileSize()
                            + " bytes");

            System.out.println(
                    "----------------------------------------");
        }
    }
}
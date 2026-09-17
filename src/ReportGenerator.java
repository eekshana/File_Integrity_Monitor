import java.util.ArrayList;

public class ReportGenerator {

    public static void generateReport(
            ArrayList<FileRecord> added,
            ArrayList<FileRecord> modified,
            ArrayList<FileRecord> deleted) {

        System.out.println();
        System.out.println("========================================");
        System.out.println("        FILE INTEGRITY REPORT");
        System.out.println("========================================");

        System.out.println(
                "Added files    : " + added.size());

        System.out.println(
                "Modified files : " + modified.size());

        System.out.println(
                "Deleted files  : " + deleted.size());

        System.out.println(
                "========================================");

        displaySection(
                "ADDED FILES",
                added);

        displaySection(
                "MODIFIED FILES",
                modified);

        displaySection(
                "DELETED FILES",
                deleted);

        if (added.isEmpty()
                && modified.isEmpty()
                && deleted.isEmpty()) {

            System.out.println();
            System.out.println(
                    "No integrity changes detected.");
        }

        System.out.println();
        System.out.println(
                "========================================");
    }

    private static void displaySection(
            String title,
            ArrayList<FileRecord> records) {

        if (records.isEmpty()) {
            return;
        }

        System.out.println();
        System.out.println("----- " + title + " -----");

        for (FileRecord record : records) {

            System.out.println(
                    record.getFilePath());

            System.out.println(
                    "SHA-256: " + record.getHash());

            System.out.println();
        }
    }
}
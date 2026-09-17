import java.io.File;
import java.util.ArrayList;

public class FileScanner {

    public static ArrayList<FileRecord> scanDirectory(
            String directoryPath) {

        ArrayList<FileRecord> records = new ArrayList<>();

        File directory = new File(directoryPath);

        if (!directory.exists()) {

            System.out.println("Directory does not exist.");
            return records;
        }

        if (!directory.isDirectory()) {

            System.out.println("The selected path is not a directory.");
            return records;
        }

        scan(directory, records);

        return records;
    }

    private static void scan(
            File directory,
            ArrayList<FileRecord> records) {

        File[] files = directory.listFiles();

        if (files == null) {
            return;
        }

        for (File file : files) {

            if (file.isDirectory()) {

                scan(file, records);

            } else {

                String hash =
                        HashGenerator.generateSHA256(
                                file.getAbsolutePath());

                if (hash != null) {

                    FileRecord record =
                            new FileRecord(
                                    file.getAbsolutePath(),
                                    hash,
                                    file.length(),
                                    file.lastModified()
                            );

                    records.add(record);
                }
            }
        }
    }
}
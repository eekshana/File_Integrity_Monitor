import java.io.*;
import java.util.ArrayList;
import java.util.Base64;

public class StorageManager {

    private static final String BASELINE_FILE =
            "data/baseline.txt";

    public static void saveBaseline(
            ArrayList<FileRecord> records) {

        File dataDirectory = new File("data");

        if (!dataDirectory.exists()) {
            dataDirectory.mkdirs();
        }

        try (PrintWriter writer =
                     new PrintWriter(
                             new FileWriter(BASELINE_FILE))) {

            for (FileRecord record : records) {

                String encodedPath =
                        Base64.getEncoder()
                                .encodeToString(
                                        record.getFilePath()
                                                .getBytes());

                writer.println(
                        encodedPath + "|" +
                        record.getHash() + "|" +
                        record.getFileSize() + "|" +
                        record.getLastModified());
            }

            System.out.println(
                    "Baseline saved successfully.");

            System.out.println(
                    "Location: " + BASELINE_FILE);

        } catch (IOException e) {

            System.out.println(
                    "Error while saving baseline.");
        }
    }

    public static ArrayList<FileRecord> loadBaseline() {

        ArrayList<FileRecord> records =
                new ArrayList<>();

        File file = new File(BASELINE_FILE);

        if (!file.exists()) {

            System.out.println(
                    "No baseline found.");

            return records;
        }

        try (BufferedReader reader =
                     new BufferedReader(
                             new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split("\\|");

                if (parts.length != 4) {
                    continue;
                }

                String filePath =
                        new String(
                                Base64.getDecoder()
                                        .decode(parts[0]));

                String hash = parts[1];

                long fileSize =
                        Long.parseLong(parts[2]);

                long lastModified =
                        Long.parseLong(parts[3]);

                FileRecord record =
                        new FileRecord(
                                filePath,
                                hash,
                                fileSize,
                                lastModified);

                records.add(record);
            }

        } catch (IOException |
                 IllegalArgumentException e) {

            System.out.println(
                    "Error while loading baseline.");
        }

        return records;
    }

    public static boolean baselineExists() {

        File file =
                new File(BASELINE_FILE);

        return file.exists();
    }
}
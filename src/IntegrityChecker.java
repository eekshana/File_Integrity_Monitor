import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class IntegrityChecker {

    public static ArrayList<FileRecord> findAddedFiles(
            ArrayList<FileRecord> baseline,
            ArrayList<FileRecord> current) {

        ArrayList<FileRecord> added =
                new ArrayList<>();

        HashMap<String, FileRecord> baselineMap =
                createMap(baseline);

        for (FileRecord record : current) {

            if (!baselineMap.containsKey(
                    record.getFilePath())) {

                added.add(record);
            }
        }

        return added;
    }

    public static ArrayList<FileRecord> findModifiedFiles(
            ArrayList<FileRecord> baseline,
            ArrayList<FileRecord> current) {

        ArrayList<FileRecord> modified =
                new ArrayList<>();

        HashMap<String, FileRecord> baselineMap =
                createMap(baseline);

        for (FileRecord record : current) {

            FileRecord oldRecord =
                    baselineMap.get(
                            record.getFilePath());

            if (oldRecord != null) {

                if (!oldRecord.getHash()
                        .equals(record.getHash())) {

                    modified.add(record);
                }
            }
        }

        return modified;
    }

    public static ArrayList<FileRecord> findDeletedFiles(
            ArrayList<FileRecord> baseline,
            ArrayList<FileRecord> current) {

        ArrayList<FileRecord> deleted =
                new ArrayList<>();

        HashMap<String, FileRecord> currentMap =
                createMap(current);

        for (FileRecord record : baseline) {

            if (!currentMap.containsKey(
                    record.getFilePath())) {

                deleted.add(record);
            }
        }

        return deleted;
    }

    private static HashMap<String, FileRecord> createMap(
            ArrayList<FileRecord> records) {

        HashMap<String, FileRecord> map =
                new HashMap<>();

        for (FileRecord record : records) {

            map.put(
                    record.getFilePath(),
                    record);
        }

        return map;
    }
}
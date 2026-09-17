public class FileRecord {

    private String filePath;
    private String hash;
    private long fileSize;
    private long lastModified;

    public FileRecord(String filePath, String hash,
                      long fileSize, long lastModified) {

        this.filePath = filePath;
        this.hash = hash;
        this.fileSize = fileSize;
        this.lastModified = lastModified;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getHash() {
        return hash;
    }

    public long getFileSize() {
        return fileSize;
    }

    public long getLastModified() {
        return lastModified;
    }

    @Override
    public String toString() {

        return "File: " + filePath
                + " | Hash: " + hash
                + " | Size: " + fileSize + " bytes";
    }
}
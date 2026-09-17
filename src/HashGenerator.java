import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashGenerator {

    public static String generateSHA256(String filePath) {

        try {

            MessageDigest digest =
                    MessageDigest.getInstance("SHA-256");

            FileInputStream inputStream =
                    new FileInputStream(filePath);

            byte[] buffer = new byte[4096];

            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {

                digest.update(buffer, 0, bytesRead);
            }

            inputStream.close();

            byte[] hashBytes = digest.digest();

            StringBuilder hashString = new StringBuilder();

            for (byte b : hashBytes) {

                hashString.append(
                        String.format("%02x", b));
            }

            return hashString.toString();

        } catch (NoSuchAlgorithmException e) {

            System.out.println("SHA-256 algorithm not available.");
            return null;

        } catch (IOException e) {

            System.out.println(
                    "Unable to read file: " + filePath);

            return null;
        }
    }
}
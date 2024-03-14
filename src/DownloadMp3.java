import java.io.*;
import java.net.URL;
import java.nio.channels.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.regex.*;

public class DownloadMp3 {
    public static void main(String[] args) {

    }

    private void downloadFiles(List<String> listOfUris) {
        //          https://www.baeldung.com/java-download-file
        String FILE_URL = "https://cdn1.101.ru/vardata/modules/podcast/data/2024/03/10/94d6cab1b2dd3a7cbd6ed18dfaa61f44.mp3";
        String FILE_NAME = "C:/test/test.mp3";

        InputStream in = null;
        try {
            in = new URL(FILE_URL).openStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Files.copy(in, Paths.get(FILE_NAME), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    InputStream getInputStreamFromFile(String fileName) {
        InputStream inputStream = null;
        try {
            File file = new File(getClass().getClassLoader().getResource("fileTest.txt").getFile());
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static String readFromInputStream(InputStream inputStream)
            throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

    private static List<String> getUris(String text) {
        text.f
    }
}
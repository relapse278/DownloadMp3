import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DownloadMp3 {
    public static void main(String[] args) {
            DownloadMp3 program = new DownloadMp3();
            program.action("C:/test/Фиксики.html");
    }

    private void action(String filename) {
        downloadFiles(getUris(getStringFromFile(filename)));
    }

    private void downloadFiles(List<String> listOfUris) {
        //          https://www.baeldung.com/java-download-file
        int i = 1;

        for (String uri : listOfUris) {
//            String FILE_URL = "https://cdn1.101.ru/vardata/modules/podcast/data/2024/03/10/94d6cab1b2dd3a7cbd6ed18dfaa61f44.mp3";
            String FILE_NAME = "C:/test/Фиксики" + String.format("%03d", (i++)) + ".mp3";

            InputStream in = null;
            try {
                in = new URL(uri).openStream();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                Files.copy(in, Paths.get(FILE_NAME), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private String getStringFromFile(String fileName) {
        //    https://stackoverflow.com/questions/224952/most-concise-way-to-read-the-contents-of-a-file-input-stream-in-java
        Scanner s;

        try {
            s = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        StringBuilder builder = new StringBuilder();
        while(s.hasNextLine()) builder.append(s.nextLine());

        return builder.toString();
        //System.out.println(builder.toString());
    }

//    private String readFromInputStream(InputStream inputStream)
//            throws IOException {
//        StringBuilder resultStringBuilder = new StringBuilder();
//        try (BufferedReader br
//                     = new BufferedReader(new InputStreamReader(inputStream))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                resultStringBuilder.append(line).append("\n");
//            }
//        }
//        return resultStringBuilder.toString();
//    }

    private List<String> getUris(String text) {
        //      https://regex101.com/library/qY2kI7?orderBy=MOST_UPVOTES&page=1&search=&filterFlavors=pcre
        final String regex = "((https:)[A-Z0-9a-z\\.\\/]*(\\.mp3))";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(text);
        List<String> allMatches = new ArrayList<>();

        while (matcher.find()) {
            allMatches.add(matcher.group(0));
        }

        return allMatches.stream().distinct().collect(Collectors.toList());
    }
}
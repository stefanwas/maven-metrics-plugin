package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;

public class SourceUtil {
    public static String readFileContent(File file, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(file.toPath());
        return new String(encoded, encoding);
    }

    public static String[] realFileAsTextLines(String fullText) throws IOException {
        ArrayList<String> textLines = new ArrayList<>();
        BufferedReader br = new BufferedReader(new StringReader(fullText));
        while (true) {
            final String line = br.readLine();
            if (line == null) {
                break;
            }
            textLines.add(line);
        }
        String[] lines = textLines.toArray(new String[textLines.size()]);
        return lines;

    }
}

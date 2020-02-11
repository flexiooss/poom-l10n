package org.condingmatters.poom.l10n.formatter.json;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class JsonFormatterClient {
    private Map<String, Map<String, String>> localizations;

    public JsonFormatterClient(String path) throws IOException {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        URL resource = contextClassLoader.getResource(path);
        String data = readResourceFile(resource);
        readJSONFile(data);
    }

    public String get(String id) {
        return "Bonjour Toto, nous sommes le {date:td} à {date:tt} ({date:t}) vous etes venus {count:d} fois pendant {minutes:f} minutes.";
    }

    private String readResourceFile(URL resource) throws IOException {
        System.out.println("Reading " + resource);
        StringBuilder textBuilder = new StringBuilder();
        try (InputStream resourceStream = resource.openStream();
             InputStreamReader inputStreamReader = new InputStreamReader(resourceStream, Charset.forName(StandardCharsets.UTF_8.name()));
             Reader reader = new BufferedReader(inputStreamReader)
        ) {
            int c = 0;
            while ((c = reader.read()) != -1) {
                textBuilder.append((char) c);
            }
        }
        return textBuilder.toString();
    }

    private void readJSONFile(String fileContent) {
        try (JsonParser parser = new JsonFactory().createParser(fileContent)) {
            JsonToken jsonToken = parser.currentToken();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

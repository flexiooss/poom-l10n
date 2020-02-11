package org.condingmatters.poom.l10n.formatter.json;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

public class JsonFormatterClient {
    private Map<String, Map<String, String>> localizations;

    public JsonFormatterClient(String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        URL resource = contextClassLoader.getResource(path);
        this.localizations = objectMapper.readValue(resource, Map.class);
    }

    public String get(String locale, String key) {
        if (locale.isEmpty()) {
            this.localizations.get(0).get(key);
        }
        String lang = locale.substring(0, 2);
        if (!this.localizations.containsKey(locale)) {
            if (this.localizations.containsKey(lang)) {
                return this.localizations.get(lang).get(key);
            }
        }

        return this.localizations.get(locale).get(key);
    }
}

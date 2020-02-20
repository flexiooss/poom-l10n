package org.condingmatters.poom.l10n.formatter.json;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.Map;

public class JsonFormatterClient implements BundleClient {
    private Map<String, Map<String, String>> localizations;

    public JsonFormatterClient(String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        URL resource = contextClassLoader.getResource(path);
        this.localizations = objectMapper.readValue(resource, Map.class);
    }

    public String get(Locale locale, String key) {
        if (locale == null) {
            this.localizations.get(0).get(key);
        }
        String lang = locale.getLanguage();
        String variant = locale.toLanguageTag();
        if (!this.localizations.containsKey(variant)) {
            if (this.localizations.containsKey(lang)) {
                return this.localizations.get(lang).get(key);
            }
        }

        return this.localizations.get(variant).get(key);
    }
}

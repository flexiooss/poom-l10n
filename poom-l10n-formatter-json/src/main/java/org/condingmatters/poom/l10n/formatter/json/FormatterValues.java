package org.condingmatters.poom.l10n.formatter.json;

import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class FormatterValues {
    private JsonFormatterClient client;
    private Locale locale;
    private ZoneOffset offset;

    private String id;
    private Map<String, Object> values;

    public FormatterValues(JsonFormatterClient client) {
        this.values = new HashMap<>();
        this.client = client;
    }

    public FormatterValues key(String key) {
        this.id = key;
        return this;
    }

    public FormatterValues with(String key, Object value) throws FormatterException {
        if (this.values.containsKey(key)) {
            throw new FormatterException("Key already set");
        }
        this.values.put(key, value);
        return this;
    }

    public String at(Locale locate, ZoneOffset offset) throws FormatterException {
        this.locale = locale;
        this.offset = offset;
        String sentence = this.client.get(this.id);
        return new JsonFormatter(sentence, locale, offset).format(values);
    }
}

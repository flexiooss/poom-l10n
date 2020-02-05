package org.condingmatters.poom.l10n.formatter.json;

import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Formatter {
    private JsonFormatterClient client = new JsonFormatterClient();
    private String id;
    private Map<String, Object> values;
    private Locale locale;
    private ZoneOffset offset;

    public Formatter() {
        this.id = null;
        this.values = new HashMap<>();
        this.locale = Locale.FRANCE;
    }

    public Formatter key(String id) {
        this.id = id;
        return this;
    }

    public Formatter with(String key, Object value) throws FormatterException {
        if (this.values.containsKey(key)) {
            throw new FormatterException("Key already set");
        }
        this.values.put(key, value);
        return this;
    }

    public Formatter at(Locale locate, ZoneOffset offset){
        this.locale = locale;
        this.offset = offset;
        return this;
    }

    public String format() throws FormatterException {
        if (this.id == null) {
            throw new FormatterException("Sentence to format not set");
        }
        String sentence = this.client.get(this.id);
        return new JsonFormatter(sentence, locale, offset).format(values);
    }
}

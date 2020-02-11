package org.condingmatters.poom.l10n.formatter.json;

public class Formatter {
    public static FormatterValues format(JsonFormatterClient client, String s) {
        return new FormatterValues(client).key(s);
    }
}


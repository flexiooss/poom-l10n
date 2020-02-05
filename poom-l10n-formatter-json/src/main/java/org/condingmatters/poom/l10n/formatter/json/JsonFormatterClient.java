package org.condingmatters.poom.l10n.formatter.json;

public class JsonFormatterClient {
    public String get(String id){
        return "Bonjour Toto, nous sommes le {date:td} à {date:tt} ({date:t}) vous etes venus {count:d} fois pendant {minutes:f} minutes.";
    }
}

import org.condingmatters.poom.l10n.formatter.json.JsonFormatter;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class JsonFormatterDateTest {
    private Map<String, Object> values = new HashMap<>();

    @Test
    public void testDate() throws Exception {
        values.put("date", LocalDate.of(2020, 10, 29));
        String format = new JsonFormatter("{date:td}", Locale.FRANCE, ZoneOffset.UTC)
                .format(values);
        assertThat(format, is("29/10/2020"));

        format = new JsonFormatter("{date:td}", Locale.US, ZoneOffset.UTC)
                .format(values);
        assertThat(format, is("10/29/20"));
    }

    @Test
    public void testDateFromDateTime() throws Exception {
        values.put("date", LocalDateTime.of(2020, 10, 29, 0, 0, 0));
        String format = new JsonFormatter("{date:td}", Locale.FRANCE, ZoneOffset.ofHours(-1))
                .format(values);
        assertThat(format, is("28/10/2020"));

        format = new JsonFormatter("{date:td}", Locale.FRANCE, ZoneOffset.ofHours(1))
                .format(values);
        assertThat(format, is("29/10/2020"));
    }
}

import org.condingmatters.poom.l10n.formatter.json.FormatterException;
import org.condingmatters.poom.l10n.formatter.json.JsonFormatter;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAmount;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class JsonFormatterTest {
    private Map<String, Object> values = new HashMap<>();
    private LocalTime time = LocalTime.of(12,30,15,200000000);
    private LocalDate date = LocalDate.of(2020, 10, 29);
    private LocalDateTime dateTime = LocalDateTime.of(date, time);

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testComplete() throws Exception {
        String s = "Bonjour Toto, nous sommes le {date:td} à {date:tt} ({date:t}) vous etes venus {count:d} fois pendant {minutes:f} minutes.";
        JsonFormatter formatter = new JsonFormatter(s, Locale.FRANCE, ZoneOffset.of(ZoneOffset.UTC.getId()));
        this.values.put("user", "Toto");
        this.values.put("count", 16);
        this.values.put("date", dateTime);
        this.values.put("minutes", 1.5);
        assertThat(formatter.format(values), is("Bonjour Toto, nous sommes le jeudi 29 octobre 2020 à 13 h 30 UTC (jeudi 29 octobre 2020 13 h 30 UTC) vous etes venus 16 fois pendant 1.5 minutes."));
    }

    @Test
    public void givenFormatString__giveString__thenGetString() throws Exception {
        String s = "{a:s}";
        JsonFormatter formatter = new JsonFormatter(s, Locale.FRANCE, ZoneOffset.of(ZoneOffset.UTC.getId()));
        this.values.put("a", "plok");
        assertThat(formatter.format(values), is("plok"));
    }

    @Test
    public void givenFormatString__giveInt__thenGetError() throws Exception {
        String s = "{a:s}";
        JsonFormatter formatter = new JsonFormatter(s, Locale.FRANCE, ZoneOffset.of(ZoneOffset.UTC.getId()));
        this.values.put("a", 16);
        thrown.expect(FormatterException.class);
        assertThat(formatter.format(values), is("16"));
    }

    @Test
    public void givenFormatString__giveFloat__thenGetError() throws Exception {
        String s = "{a:s}";
        JsonFormatter formatter = new JsonFormatter(s, Locale.FRANCE, ZoneOffset.of(ZoneOffset.UTC.getId()));
        this.values.put("a", 1.6F);
        thrown.expect(FormatterException.class);
        formatter.format(values);
    }

    @Test
    public void givenFormatString__giveDateTime__thenGetError() throws Exception {
        String s = "{a:s}";
        JsonFormatter formatter = new JsonFormatter(s, Locale.FRANCE, ZoneOffset.of(ZoneOffset.UTC.getId()));
        this.values.put("a", dateTime);
        thrown.expect(FormatterException.class);
        formatter.format(values);
    }

    @Test
    public void givenFormatString__giveDate__thenGetError() throws Exception {
        String s = "{a:s}";
        JsonFormatter formatter = new JsonFormatter(s, Locale.FRANCE, ZoneOffset.of(ZoneOffset.UTC.getId()));
        this.values.put("a", date);
        thrown.expect(FormatterException.class);
        formatter.format(values);
    }

    @Test
    public void givenFormatString__giveTime__thenGetError() throws Exception {
        String s = "{a:s}";
        JsonFormatter formatter = new JsonFormatter(s, Locale.FRANCE, ZoneOffset.of(ZoneOffset.UTC.getId()));
        this.values.put("a", time);
        thrown.expect(FormatterException.class);
        formatter.format(values);
    }


    @Test
    public void givenFormatInt__giveString__thenGetError() throws Exception {
        String s = "{a:d}";
        JsonFormatter formatter = new JsonFormatter(s, Locale.FRANCE, ZoneOffset.of(ZoneOffset.UTC.getId()));
        this.values.put("a", "plok");
        thrown.expect(FormatterException.class);
        formatter.format(values);
    }

    @Test
    public void givenFormatInt__giveInt__thenGetInt() throws Exception {
        String s = "{a:d}";
        JsonFormatter formatter = new JsonFormatter(s, Locale.FRANCE, ZoneOffset.of(ZoneOffset.UTC.getId()));
        this.values.put("a", 42);
        assertThat(formatter.format(values), is("42"));
    }

    @Test
    public void givenFormatInt__giveFloat__thenGetError() throws Exception {
        String s = "{a:d}";
        JsonFormatter formatter = new JsonFormatter(s, Locale.FRANCE, ZoneOffset.of(ZoneOffset.UTC.getId()));
        this.values.put("a", 15.5);
        thrown.expect(FormatterException.class);
        formatter.format(values);
    }

    @Test
    public void givenFormatInt__giveDateTime__thenGetError() throws Exception {
        String s = "{a:d}";
        JsonFormatter formatter = new JsonFormatter(s, Locale.FRANCE, ZoneOffset.of(ZoneOffset.UTC.getId()));
        this.values.put("a", dateTime);
        thrown.expect(FormatterException.class);
        formatter.format(values);
    }

    @Test
    public void givenFormatInt__giveDate__thenGetError() throws Exception {
        String s = "{a:d}";
        JsonFormatter formatter = new JsonFormatter(s, Locale.FRANCE, ZoneOffset.of(ZoneOffset.UTC.getId()));
        this.values.put("a", date);
        thrown.expect(FormatterException.class);
        formatter.format(values);
    }

    @Test
    public void givenFormatInt__giveTime__thenGetError() throws Exception {
        String s = "{a:d}";
        JsonFormatter formatter = new JsonFormatter(s, Locale.FRANCE, ZoneOffset.of(ZoneOffset.UTC.getId()));
        this.values.put("a", time);
        thrown.expect(FormatterException.class);
        formatter.format(values);
    }

    @Test
    public void givenFormatDateTime__giveString__thenGetError() throws Exception {
        String s = "{a:td}";
        JsonFormatter formatter = new JsonFormatter(s, Locale.FRANCE, ZoneOffset.of(ZoneOffset.UTC.getId()));
        this.values.put("a", "plok");
        thrown.expect(FormatterException.class);
        formatter.format(values);
    }

    @Test
    public void givenFormatDateTime__giveInt__thenGetError() throws Exception {
        String s = "{a:td}";
        JsonFormatter formatter = new JsonFormatter(s, Locale.FRANCE, ZoneOffset.of(ZoneOffset.UTC.getId()));
        this.values.put("a", 42);
        thrown.expect(FormatterException.class);
        formatter.format(values);
    }

    @Test
    public void givenFormatDateTime__giveFloat__thenGetError() throws Exception {
        String s = "{a:td}";
        JsonFormatter formatter = new JsonFormatter(s, Locale.FRANCE, ZoneOffset.of(ZoneOffset.UTC.getId()));
        this.values.put("a", 15.5);
        thrown.expect(FormatterException.class);
        formatter.format(values);
    }

    @Test
    public void givenFormatDateTime__giveDateTime__thenGetError() throws Exception {
        String s = "{a:t}";
        JsonFormatter formatter = new JsonFormatter(s, Locale.FRANCE, ZoneOffset.of(ZoneOffset.UTC.getId()));
        this.values.put("a", dateTime);
        assertThat(formatter.format(values), is("29/10/2020 12:30:15:200"));

        formatter = new JsonFormatter(s, Locale.FRANCE, ZoneOffset.of("+02:00"));
        this.values.put("a", dateTime);
        assertThat(formatter.format(values), is("29/10/2020 14:30:15:200"));

        formatter = new JsonFormatter(s, Locale.UK, ZoneOffset.of(ZoneOffset.UTC.getId()));
        this.values.put("a", dateTime);
        assertThat(formatter.format(values), is("10/20/2020 12:30:15:200"));

        formatter = new JsonFormatter(s, Locale.UK, ZoneOffset.of("+02:00"));
        this.values.put("a", dateTime);
        assertThat(formatter.format(values), is("10/29/2020 2:30:15:200"));
    }

    @Test
    public void TestDate() throws Exception {

    }

    @Test
    public void TestTime() throws Exception {

    }

    @Test
    public void testOffset() throws Exception {

    }
}

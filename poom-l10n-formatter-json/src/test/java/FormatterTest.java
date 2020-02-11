import org.condingmatters.poom.l10n.formatter.json.Formatter;
import org.condingmatters.poom.l10n.formatter.json.JsonFormatterClient;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class FormatterTest {
    private JsonFormatterClient client;

    @Before
    public void setUp() throws Exception {
        this.client = new JsonFormatterClient("spec/Localizations.json");
    }

    @Test
    public void testCompleteFR() throws Exception {
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.of(2020, 10, 29), LocalTime.of(12, 30, 15));

        String format = Formatter.format(this.client, "r1")
                .with("user", "Toto")
                .with("count", 16)
                .with("date", localDateTime)
                .with("minutes", 1.5)
                .with("msgs", 15000)
                .at(Locale.FRANCE, ZoneOffset.of(ZoneOffset.UTC.getId()));

        assertThat(format, is("Bonjour Toto, nous sommes le 29 oct. 2020 à 12:30:15 (29 oct. 2020 12:30:15:30:0) vous etes venus 16 fois pendant 1,5 minutes. Vous avez 15 000 messages !"));
    }

    @Test
    public void testCompleteUK() throws Exception {
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.of(2020, 10, 29), LocalTime.of(12, 30, 15));

        String format = Formatter.format(this.client, "r1")
                .with("user", "Toto")
                .with("count", 16)
                .with("date", localDateTime)
                .with("minutes", 1.5)
                .with("msgs", 15000)
                .at(Locale.UK, ZoneOffset.of(ZoneOffset.UTC.getId()));

        assertThat(format, is("Hello Toto, we are on 29-Oct-2020 at 12:30:15 (29-Oct-2020 12:30:15:30:0) you have come 16 times for 1.5 minutes. You have 15,000 messages!"));
    }

    @Test
    public void testCompleteUS() throws Exception {
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.of(2020, 10, 29), LocalTime.of(12, 30, 15));

        String format = Formatter.format(this.client, "r1")
                .with("user", "Toto")
                .with("count", 16)
                .with("date", localDateTime)
                .with("minutes", 1.5)
                .with("msgs", 15000)
                .at(Locale.US, ZoneOffset.of(ZoneOffset.UTC.getId()));

        assertThat(format, is("Hello Toto, we are on Oct 29, 2020 at 12:30:15 PM (Oct 29, 2020 12:30:15 PM:30:0) you have come 16 times for 1.5 minutes. You have 15,000 messages!"));
    }
}

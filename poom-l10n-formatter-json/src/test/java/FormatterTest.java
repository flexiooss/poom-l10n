import org.condingmatters.poom.l10n.formatter.json.Formatter;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.Locale;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class FormatterTest {

    @Test
    public void testComplete() throws Exception {
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.of(2020, 10, 29), LocalTime.of(12, 30, 15));

        String format = Formatter.format(null,"base")
                .with("user", "Toto")
                .with("count", 16)
                .with("date", localDateTime)
                .with("minutes", 1.5)
                .at(Locale.FRANCE, ZoneOffset.of(ZoneOffset.UTC.getId()));

        assertThat(format, is("Bonjour Toto, nous sommes le jeudi 29 octobre 2020 à 13 h 30 CET (jeudi 29 octobre 2020 13 h 30 CET) vous etes venus 16 fois pendant 1.5 minutes."));
    }

}

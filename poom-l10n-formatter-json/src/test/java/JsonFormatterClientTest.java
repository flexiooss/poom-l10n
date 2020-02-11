import org.condingmatters.poom.l10n.formatter.json.JsonFormatterClient;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class JsonFormatterClientTest {
    @Test
    public void testFR() throws Exception {
        JsonFormatterClient client = new JsonFormatterClient("spec/Localizations.json");
        String s = client.get("fr", "r1");
        assertThat(s, is("Bonjour {user:s}, nous sommes le {date:td} à {date:tt} ({date:t}) vous etes venus {count:d} fois pendant {minutes:f} minutes. Vous avez {msgs:d} messages !"));
    }

    @Test
    public void testEN() throws Exception {
        JsonFormatterClient client = new JsonFormatterClient("spec/Localizations.json");
        String s = client.get("en-GB", "r1");
        assertThat(s, is("Hello {user:s}, we are on {date:td} at {date:tt} ({date:t}) you have come {count:d} times for {minutes:f} minutes. You have {msgs:d} messages!"));
    }
}

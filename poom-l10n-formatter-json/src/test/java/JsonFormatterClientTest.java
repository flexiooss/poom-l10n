import org.condingmatters.poom.l10n.formatter.json.JsonFormatterClient;
import org.junit.Test;

public class JsonFormatterClientTest {
    @Test
    public void test() throws Exception {
        JsonFormatterClient client = new JsonFormatterClient("Localizations.json");
        client.get("base");
    }
}

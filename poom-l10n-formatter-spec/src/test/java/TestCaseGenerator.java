import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import org.codingmatters.poom.l10n.formatter.TestCaseExpectedValue;
import org.codingmatters.poom.l10n.formatter.TestCaseFormatter;
import org.codingmatters.poom.l10n.formatter.TestCaseValue;
import org.codingmatters.poom.l10n.formatter.json.TestCaseFormatterWriter;
import org.codingmatters.value.objects.values.ObjectValue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class TestCaseGenerator {
    public static void main(String[] args) throws IOException {
        TestCaseFormatter test = TestCaseFormatter.builder()
                .name("givenFormatString__giveString__thenGetString")
                .locale("FR")
                .zone("UTC")
                .values(
                        TestCaseValue.builder().key("a").value(ObjectValue.builder().property("value", v -> v.stringValue("plok")).build()).build()
                )
                .expectedValue(
                        TestCaseExpectedValue.builder().value("plok").exception(false).build()
                )
                .build();

        TestCaseFormatterWriter writer = new TestCaseFormatterWriter();
        try (OutputStream stream = new FileOutputStream(new File(test.name() + ".json"))) {
            try (JsonGenerator generator = new JsonFactory().createGenerator(stream)) {
                writer.write(generator, test);
                System.out.println("write "+test.name());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

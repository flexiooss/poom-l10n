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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TestCaseGenerator {
    public static void main(String[] args) throws IOException {
        TestCaseFormatter test = TestCaseFormatter.builder()
                .name("Test_givenFormatInt__giveDate__thenGetDate")
                .template("{a:d}")
                .locale("FR")
                .offset("0")
                .values(
//                        TestCaseValue.builder().key("a").value(ObjectValue.builder().property("value", v -> v.doubleValue(5d)).build()).build()
//                        TestCaseValue.builder().key("a").value(ObjectValue.builder().property("value", v -> v.longValue(42L)).build()).build()
                        TestCaseValue.builder().key("a").value(ObjectValue.builder().property("value", v -> v.datetimeValue(LocalDateTime.now())).build()).build()
//                        TestCaseValue.builder().key("a").value(ObjectValue.builder().property("value", v -> v.dateValue(LocalDate.now())).build()).build()
//                        TestCaseValue.builder().key("a").value(ObjectValue.builder().property("value", v -> v.timeValue(LocalTime.now())).build()).build()
//                        TestCaseValue.builder().key("a").value(ObjectValue.builder().property("value", v -> v.stringValue("plok")).build()).build()
                )
                .expectedValue(
//                        TestCaseExpectedValue.builder().value("5").exception(false).build()
                        TestCaseExpectedValue.builder().exception(true).build()
                )
                .build();

        TestCaseFormatterWriter writer = new TestCaseFormatterWriter();
        try (OutputStream stream = new FileOutputStream(new File(test.name() + ".json"))) {
            try (JsonGenerator generator = new JsonFactory().createGenerator(stream)) {
                writer.write(generator, test);
                System.out.println("write " + test.name());
            }
        }
    }
}

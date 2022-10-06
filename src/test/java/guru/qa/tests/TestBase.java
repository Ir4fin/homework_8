package guru.qa.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    @BeforeAll
    static void configure() {
        Configuration.baseUrl = "https://www.booking.com";
        Configuration.browserSize = "1920x1080";
    }
}

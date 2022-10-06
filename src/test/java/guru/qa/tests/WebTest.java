package guru.qa.tests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import guru.qa.pages.BookingMainPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class WebTest extends TestBase {

    BookingMainPage bookingMainPage = new BookingMainPage();


    @ParameterizedTest
    @ValueSource(strings = {"Черногория", "Бельгия"})
    @DisplayName("There are results of search request {0}")
    void searchHotelsInCountryIsSuccessful(String testData) {
        bookingMainPage.openPage()
                .setDestination(testData)
                .clickOnSubmit();
        $$(".b978843432").shouldBe(CollectionCondition.sizeGreaterThan(0));
    }

    @ParameterizedTest(name = "results of search request {0} contains 'show on map' {1}")
    @CsvSource(value = {
            "Черногория, Показать на карте",
            "Бельгия, Показать на карте",
    })
    void resultOfSearchHasShowOnMap(String testData, String expectedResult) {
        bookingMainPage.openPage()
                .setDestination(testData)
                .clickOnSubmit();
        $$(".b978843432")
                .first()
                .shouldHave(text(expectedResult));
    }


    static Stream<Arguments> bookingMenuMainPageChangesIfLanguageChanged() {
        return Stream.of(
                Arguments.of("Русский", List.of("Жилье", "Авиабилеты", "Аренда машин", "Варианты досуга", "Такси от/до аэропорта")),
                Arguments.of("English (UK)", List.of("Stays", "Flights", "Car rentals", "Attractions", "Airport taxis"))
        );
    }

    @MethodSource
    @ParameterizedTest(name = "Смена языка {0} аффектит название кнопок {1} в меню на главной")
    void bookingMenuMainPageChangesIfLanguageChanged(String language, List<String> expectedButtons) {
        bookingMainPage.openPage()
                .changeLanguage(language);
        $$(".bui-header__tab li").shouldHave(texts(expectedButtons));
    }


}

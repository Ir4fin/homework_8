package guru.qa.pages;

import com.codeborne.selenide.SelenideElement;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class BookingMainPage {

    private SelenideElement destinationInput = $("#ss"),
                            submitButton = $(".sb-searchbox__button");


    public BookingMainPage openPage() {
        open("https://www.booking.com");

        return this;
    }

    public BookingMainPage changeLanguage(String language) {
        $(".bui-avatar__image").click();
        $(byText(language)).click();

        return this;
    }

    public BookingMainPage setDestination(String value) {
        destinationInput.setValue(value);

        return this;
    }

    public BookingMainPage clickOnSubmit() {
        submitButton.click();

        return this;
    }


}

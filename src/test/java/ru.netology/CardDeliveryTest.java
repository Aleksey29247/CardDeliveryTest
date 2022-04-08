package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {


    String planningDate = generateDate(4);

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    public void souldSendForm() {
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "800x800";
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").val("Новосибирск");
        $(" [data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $x("//input[@type= 'tel']").val(planningDate);
        $("[data-test-id=name] input").setValue("Пригода Елена Дмитриевна");
        $("[data-test-id=phone] input").setValue("+79237481592");
        $("[data-test-id='agreement']").click();
        $$(withText("Забронировать")).first().click();
        $("[class='notification__content']")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + (planningDate)), Duration.ofSeconds(15));
    }

    @Test
    public void selectTheDesiredCity() {
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "800x800";
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").val("Бердск");
        $(" [data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $x("//input[@type= 'tel']").val(planningDate);
        $("[data-test-id=name] input").setValue("Пригода Елена Дмитриевна");
        $("[data-test-id=phone] input").setValue("+79237481592");
        $("[data-test-id='agreement']").click();
        $$(withText("Забронировать")).first().click();
        $("[data-test-id = 'city'] .input__sub")
                .shouldHave(exactText("Доставка в выбранный город недоступна"));
    }

}    










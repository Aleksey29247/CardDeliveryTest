package ru.netology;

import com.codeborne.selenide.Configuration;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;


import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {


   @Test
   public void souldSendForm() {
       Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "800x800";
       open("http://localhost:9999/");
       $$x("//input[@type= 'text']").get(0).val("Новосибирск");
       $(" [data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
       $$x("//input[@type='tel']").get(0).val("09.04.2022");
        $$x("//input[@type='text']").get(1).val("Пригода Елена Дмитриевна");
       $$x("//input[@type='tel']").get(1).val("+79237481592");
       $("[data-test-id='agreement']").click();
       $$("button").find(exactText("Забронировать")).click();
       $$("button").find(exactText("Успешно!"));
    }

    @Test
    public void selectTheDesiredCity() {
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "800x800";
        open("http://localhost:9999/");
        $$x("//input[@type= 'text']").get(0).val("Но");
        $(withText("Новосибирск")).click();
         }
}    










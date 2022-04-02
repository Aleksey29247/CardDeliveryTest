package ru.netology;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.time.Duration;


import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    private WebDriver driver;

    @BeforeAll
    public static void setUpAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUp() {
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "800x800";
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    public void souldSendForm() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
        $$x("//input[@type= 'text']").get(0).val("Новосибирск");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $$x("//input[@type='tel']").get(0).val("05.04.2022");
        $$x("//input[@type='text']").get(1).val("Пригода Елена Дмитриевна");
        $$x("//input[@type='tel']").get(1).val("+79237481592");
        $("[data-test-id='agreement']").click();
        $(withText("Забронировать")).click();
        $(byText("Успешно!")).shouldBe(visible, Duration.ofSeconds(10));
    }

}







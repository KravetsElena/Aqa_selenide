package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.SetValueOptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldHappyPathTest() {

        Calendar c = new GregorianCalendar();
        c.add(Calendar.DAY_OF_YEAR, 3); // увеличиваем на 3 дня от текущей даты
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy"); //придаем нужный формат дате
        String date = format1.format(c.getTime());//c.getTime().toString();//вытягиваем измененную дату в нужном формате и присваиваем переменной


        SelenideElement form = $(".form, .form_size_m, .form_theme_alfa-on-white");
        form.$("[data-test-id=city] input").setValue("Казань");
        form.$("[data-test-id=date] input").setValue(date);
        form.$("[data-test-id=name] input").setValue("Иван Соколов");
        form.$("[data-test-id=phone] input").setValue("+79092364751");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $(withText("Успешно!")).shouldBe(Condition.visible, Duration.ofSeconds(15));
    }


}

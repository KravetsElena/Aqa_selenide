package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.GregorianCalendar;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;


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
        String date = format1.format(c.getTime());//вытягиваем измененную дату в нужном формате и присваиваем переменной

        SelenideElement form = $(".form, .form_size_m, .form_theme_alfa-on-white");
        form.$("[data-test-id=city] input").setValue("Казань");
        form.$("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.UP), Keys.DELETE);
        form.$("[data-test-id=date] input").setValue(date);
        form.$("[data-test-id=name] input").setValue("Иван Соколов");
        form.$("[data-test-id=phone] input").setValue("+79092364751");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $(withText("Успешно!")).shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldMistakeCity() {

        Calendar c = new GregorianCalendar();
        c.add(Calendar.DAY_OF_YEAR, 3); // увеличиваем на 3 дня от текущей даты
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy"); //придаем нужный формат дате
        String date = format1.format(c.getTime());//вытягиваем измененную дату в нужном формате и присваиваем переменной


        SelenideElement form = $(".form, .form_size_m, .form_theme_alfa-on-white");
        form.$("[data-test-id=city] input").setValue("казань");
        form.$("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.UP), Keys.DELETE);
        form.$("[data-test-id=date] input").setValue(date);
        form.$("[data-test-id=name] input").setValue("Иван Соколов");
        form.$("[data-test-id=phone] input").setValue("+79092364751");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $(withText("Успешно!")).shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldLatinCity() {

        Calendar c = new GregorianCalendar();
        c.add(Calendar.DAY_OF_YEAR, 3); // увеличиваем на 3 дня от текущей даты
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy"); //придаем нужный формат дате
        String date = format1.format(c.getTime());//вытягиваем измененную дату в нужном формате и присваиваем переменной


        SelenideElement form = $(".form, .form_size_m, .form_theme_alfa-on-white");
        form.$("[data-test-id=city] input").setValue("Moscow");
        form.$("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.UP), Keys.DELETE);
        form.$("[data-test-id=date] input").setValue(date);
        form.$("[data-test-id=name] input").setValue("Иван Соколов");
        form.$("[data-test-id=phone] input").setValue("+79092364751");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        form.$(withText("Доставка в выбранный город недоступна")).shouldBe(Condition.visible);
    }

    @Test
    void shouldAnotherCity() {

        Calendar c = new GregorianCalendar();
        c.add(Calendar.DAY_OF_YEAR, 3); // увеличиваем на 3 дня от текущей даты
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy"); //придаем нужный формат дате
        String date = format1.format(c.getTime());//вытягиваем измененную дату в нужном формате и присваиваем переменной


        SelenideElement form = $(".form, .form_size_m, .form_theme_alfa-on-white");
        form.$("[data-test-id=city] input").setValue("Златоуст");
        form.$("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.UP), Keys.DELETE);
        form.$("[data-test-id=date] input").setValue(date);
        form.$("[data-test-id=name] input").setValue("Иван Соколов");
        form.$("[data-test-id=phone] input").setValue("+79092364751");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        form.$(withText("Доставка в выбранный город недоступна")).shouldBe(Condition.visible);
    }

    @Test
    void shouldDateBeforeLimit() {

        Calendar cal = new GregorianCalendar();
        cal.add(Calendar.DAY_OF_YEAR, 1); // увеличиваем на 1 дня от текущей даты

        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy"); //придаем нужный формат дате
        String date = format1.format(cal.getTime());//вытягиваем измененную дату в нужном формате и присваиваем переменной


        SelenideElement form = $(".form, .form_size_m, .form_theme_alfa-on-white");
        form.$("[data-test-id=city] input").setValue("Казань");
        form.$("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.UP), Keys.DELETE);
        form.$("[data-test-id=date] input").setValue(date);
        form.$("[data-test-id=name] input").setValue("Иван Соколов");
        form.$("[data-test-id=phone] input").setValue("+79092364751");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        form.$(withText("Заказ на выбранную дату невозможен")).shouldBe(Condition.visible);
    }

    @Test
    void shouldDateOverLimit() {

        Calendar cal = new GregorianCalendar();
        cal.add(Calendar.DAY_OF_YEAR, 4); // увеличиваем на 4 дня от текущей даты

        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy"); //придаем нужный формат дате
        String date = format1.format(cal.getTime());//вытягиваем измененную дату в нужном формате и присваиваем переменной


        SelenideElement form = $(".form, .form_size_m, .form_theme_alfa-on-white");
        form.$("[data-test-id=city] input").setValue("Казань");
        form.$("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.UP), Keys.DELETE);
        form.$("[data-test-id=date] input").setValue(date);
        form.$("[data-test-id=name] input").setValue("Иван Соколов");
        form.$("[data-test-id=phone] input").setValue("+79092364751");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $(withText("Успешно!")).shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldLatinNameTest() {

        Calendar c = new GregorianCalendar();
        c.add(Calendar.DAY_OF_YEAR, 3); // увеличиваем на 3 дня от текущей даты
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy"); //придаем нужный формат дате
        String date = format1.format(c.getTime());//вытягиваем измененную дату в нужном формате и присваиваем переменной

        SelenideElement form = $(".form, .form_size_m, .form_theme_alfa-on-white");
        form.$("[data-test-id=city] input").setValue("Казань");
        form.$("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.UP), Keys.DELETE);
        form.$("[data-test-id=date] input").setValue(date);
        form.$("[data-test-id=name] input").setValue("Ivan Sokolov");
        form.$("[data-test-id=phone] input").setValue("+79092364751");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $(withText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.")).shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldNameWithDashTest() {

        Calendar c = new GregorianCalendar();
        c.add(Calendar.DAY_OF_YEAR, 3); // увеличиваем на 3 дня от текущей даты
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy"); //придаем нужный формат дате
        String date = format1.format(c.getTime());//вытягиваем измененную дату в нужном формате и присваиваем переменной

        SelenideElement form = $(".form, .form_size_m, .form_theme_alfa-on-white");
        form.$("[data-test-id=city] input").setValue("Казань");
        form.$("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.UP), Keys.DELETE);
        form.$("[data-test-id=date] input").setValue(date);
        form.$("[data-test-id=name] input").setValue("Анна-Мария Смирнова");
        form.$("[data-test-id=phone] input").setValue("+79092364751");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $(withText("Успешно!")).shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldValidationCheckbox() {

        Calendar c = new GregorianCalendar();
        c.add(Calendar.DAY_OF_YEAR, 3); // увеличиваем на 3 дня от текущей даты
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy"); //придаем нужный формат дате
        String date = format1.format(c.getTime());//вытягиваем измененную дату в нужном формате и присваиваем переменной

        SelenideElement form = $(".form, .form_size_m, .form_theme_alfa-on-white");
        form.$("[data-test-id=city] input").setValue("Казань");
        form.$("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.UP), Keys.DELETE);
        form.$("[data-test-id=date] input").setValue(date);
        form.$("[data-test-id=name] input").setValue("Анна-Мария Смирнова");
        form.$("[data-test-id=phone] input").setValue("+79092364751");
        form.$(".button").click();
        $("[data-test-id='agreement'].input_invalid .checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }
}

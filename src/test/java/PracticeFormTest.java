
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PracticeFormTest {
    @BeforeAll
    static void beforeAll() {
        //Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = true;
        //Configuration.timeout = 5000; // default 4000
    }

    @Test
    void fillFormTest() {
        open("/text-box");
        $("#userName").setValue("Alex");
        $("#userEmail").setValue("alex@egorov.com");
        $("#currentAddress").setValue("Some street 1");
        $("#permanentAddress").setValue("Another street 1");
        $("#submit").click();

        $("#output #name").shouldHave(text("Alex"));
        $("#output #email").shouldHave(text("alex@egorov.com"));
        $("#output #currentAddress").shouldHave(text("Some street 1"));
        $("#output #permanentAddress").shouldHave(text("Another street 1"));
    }

    @Test
    void fullFormTest() {
        open("/automation-practice-form");
        $("#firstName").setValue("Alex");
        $("#lastName").setValue("Egorov");
        $("#userEmail").setValue("alex@egorov.com");
        //$("[for=gender-radio-1]").click(); //
        $("#gender-radio-1").parent().$(byText("Male")).click(); //Нашли id в поле input далее переходим в родительский элемент (div) там ищем Male
        $("#userNumber").setValue("5555555555");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption("2000"); //selectOption для работы с выпадающими списками
        $(".react-datepicker__month-select").selectOption("September");
        $(".react-datepicker__day--030:not(.react-datepicker__day--outside-month)").click(); //Ищет число 30 и исключает дни предыдущего или следующего месяца
        $("#subjectsInput").setValue("Maths").pressEnter();
        $("#subjectsInput").setValue("Physics").pressEnter();
        $("#hobbies-checkbox-1").parent().$(byText("Sports")).click();
        $("#hobbies-checkbox-3").parent().$(byText("Music")).click();
        $("#uploadPicture").uploadFromClasspath("panda.jpg"); //uploadFromClasspath() метод загрузки файла из resources
        $("#currentAddress").setValue("Russia, Penza");
        $("#state").click();
        $("#stateCity-wrapper").$(byText("Rajasthan")).click(); //берем айди всей строки и там ищем по байтекст
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Jaiselmer")).click();
        $("#submit").click();
        //Проверка результатов table-responsive
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text("Alex Egorov"));
        $(".table-responsive").shouldHave(text("alex@egorov.com"));
        $(".table-responsive").shouldHave(text("Male"));
        $(".table-responsive").shouldHave(text("5555555555"));
        $(".table-responsive").shouldHave(text("30 September,2000"));
        $(".table-responsive").shouldHave(text("Maths, Physics"));
        $(".table-responsive").shouldHave(text("Sports, Music"));
        $(".table-responsive").shouldHave(text("panda.jpg"));
        $(".table-responsive").shouldHave(text("Russia, Penza"));
        $(".table-responsive").shouldHave(text("Rajasthan Jaiselmer"));

    }

}

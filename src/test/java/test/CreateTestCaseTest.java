package test;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static helpers.TestValues.*;

public class CreateTestCaseTest extends BaseTest {
    @Test
    void createTestCase() {
        List<String> links = new ArrayList<>();
        loginPage.openPage(BASE_URL, LOGIN_URL)
                .checkTitle("Вход в учетную запись", "h4")
                .setEmail(TEST_EMAIL)
                .setPasswordFirst(TEST_PASSWORD);
        workspacePage.switchSideMenu("Мои пространства")
                .selectWorkspace(TEST_NAME_WORKSPACES);
       $$("td.align-middle div div").findBy(text(TEST_NAME_SHORT_PROJECT)).doubleClick();
        workspacePage.clickBtn("+ Раздел");
        workspacePage.checkTitle("Новый раздел","div.modal-simple__content h5");
       $("input#section_title_input").setValue(TEST_NAME_BASIC_SECTION);
       $("div.modal-simple select.form-select").selectOption("Корневой раздел");
       $("div.modal-simple textarea.form-control").setValue(TEST_BODY);
       workspacePage.clickBtn("Сохранить");
       workspacePage.checkTitle(TEST_NAME_BASIC_SECTION,"div.cases__content h5");
       workspacePage.clickBtn("+ Кейс");
       workspacePage.checkTitle("Новая запись","div.card-header h5");
       executeJavaScript("document.querySelector('footer.site-footer').style.display = 'none'");

       $("input.form-control[placeholder]").setValue(TEST_TITLE);
        $("select#section").selectOption("Основной раздел");
        $("select#responsible_id").selectOption(0);
        $("select#status_id").selectOption("Draft");
        $("select#type_id").selectOption("Smoke");
        $("select#priority_id").selectOption("High");
        $("select#priority_id").selectOption("High");
        $("select#severity_id").selectOption("Blocker");
       $$("div[aria-owns='quill-mention-list']").get(0).setValue(TEST_BODY + "Информация");
       $$("div[aria-owns='quill-mention-list']").get(1).setValue(TEST_BODY + "Предусловие");
       $$("div[aria-owns='quill-mention-list']").get(2).setValue(TEST_BODY + "Шаг 1: Описание шага!");
       $$("div[aria-owns='quill-mention-list']").get(3).setValue(TEST_BODY + "Шаг 1: Ожидаемый результат!");
       $$("div[aria-owns='quill-mention-list']").get(4).setValue(TEST_BODY + "Шаг 2: Описание шага");
       $$("div[aria-owns='quill-mention-list']").get(5).setValue(TEST_BODY + "Шаг 2: Ожидаемый результат");
       workspacePage.clickBtn("Сохранить и перейти к записи");
//       $$(".btns-group button").findBy(text("Сохранить и перейти к записи")).click();
       workspacePage.checkTitle(TEST_TITLE,"div.card-body h5");
       workspacePage.checkTitle(TEST_TITLE,"div.card-body h2");
       $x("//h4[text()='Информация']//following::div[1]//p")
               .shouldHave(text(TEST_BODY + "Информация"));
       $x("//h4[text()='Предусловие']//following::div[1]//p")
               .shouldHave(text(TEST_BODY + "Предусловие"));

       $x("//div[text()='Шаг № 1']//following::div[text()='Описание:']/following::div[1]//p")
               .shouldHave(text(TEST_BODY + "Шаг 1: Описание шага!"));
       $x("//div[text()='Шаг № 1']//following::div[text()='Результат:']/following::div[1]//p")
               .shouldHave(text(TEST_BODY + "Шаг 1: Ожидаемый результат!"));

       $x("//div[text()='Шаг № 2']//following::div[1]/following::div[1]//p")
               .shouldHave(text(TEST_BODY + "Шаг 2: Описание шага"));
        $x("//div[text()='Шаг № 2']//following::div[text()='Результат:']/following::div[1]//p")
                .shouldHave(text(TEST_BODY + "Шаг 2: Ожидаемый результат"));

        executeJavaScript("window.open()");
        switchTo().window(1);
        open(PROJECT_BASE_URL + PROJECT_CASES_URL);
        $x(("//td[text()='")+ TEST_TITLE + ("']//ancestor-or-self::tr/td/input")).click();
        $("div.opened-view__section-header div.section-dd__button").click();
        $$("div.section-dd__dropdown_item")
                .findBy(text(" Удалить ")).click();
        $$("div.modal-simple button").findBy(text(" Удалить ")).shouldHave(visible).click();


        $x("//h5[text()='Основной раздел']//following::div[@class='section-dd__button']").click();
        $$("div.section-dd__dropdown_item")
                .findBy(text(" Удалить раздел ")).click();
        $$("div.modal-simple button").findBy(text(" Ок ")).shouldHave(visible).click();



       int a=1;

        leftNavigationSideBar(links);
        printList(links);
    }

}

package test;

import helpers.StringModifier;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static helpers.TestValues.*;
import static helpers.TestValues.TEST_PASSWORD;

public class CreateProjectTest extends BaseTest {
    @Test
    @Feature("Тестироване сущности 'PROJECT'")
    @Owner("buyanovMV")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Тестироване создания новой сущности 'PROJECT'")
    void createWorkspace() {
        StringModifier.nameWorkspaceAndShortNameWorkSpaceRandom();
        loginPage.openPage(BASE_URL, LOGIN_URL)
                .checkTitle("Вход в учетную запись", "h4")
                .setEmail(TEST_EMAIL)
                .setPasswordFirst(TEST_PASSWORD);
        workspacePage.switchSideMenu("Мои пространства")
                .selectWorkspace(TEST_NAME_WORKSPACES)
                .switchTabWorkspace("Проекты")
                .createProject("Добавить проект")
                .setNameProject(TEST_NAME_PROJECT)
                .setShortNameProject(TEST_NAME_SHORT_PROJECT)
                .clickBtn("Создать")
                .checkTitle(TEST_NAME_PROJECT,"h3");
    }
}

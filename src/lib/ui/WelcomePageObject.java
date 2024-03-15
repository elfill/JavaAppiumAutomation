package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class WelcomePageObject extends MainPageObject{

    public WelcomePageObject(AppiumDriver driver){
        super(driver);
    }

    private final static String
            STEP_LEARN_MORE_LINK = "xpath://XCUIElementTypeButton[@name='Узнать подробнее о Википедии']",
            STEP_NEW_WAYS_TO_EXPLORE_TEXT = "xpath://XCUIElementTypeStaticText[@name='Новые способы изучения']",
            STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK = "xpath://XCUIElementTypeStaticText[@name='Добавить или изменить предпочтительные языки']",
            STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK = "xpath://XCUIElementTypeStaticText[@name='Узнать подробнее о сборе данных']",
            NEXT_LINK = "xpath://XCUIElementTypeButton[@name='Далее']",
            GET_STARTED_BUTTON = "xpath://XCUIElementTypeButton[@name='Начать']"
            ;

    public void waitForLearnMoreLink(){
        this.waitForElementPresent(
                STEP_LEARN_MORE_LINK,
                 "Cannot find 'Узнать подробнее о википедии' link ",
                10);
    }

    public void waitForNewWaysToExploreText(){
        this.waitForElementPresent(
                STEP_NEW_WAYS_TO_EXPLORE_TEXT,
                "Cannot find 'Новые способы изучения' link ",
                10);
    }

    public void waitForAddOrEditPreferredLangText(){
        this.waitForElementPresent(
                STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK,
                "Cannot find 'Добавить или изменить предпочтительные языки' link ",
                10);
    }

    public void waitForLearnMoreAboutDataCollectedText(){
        this.waitForElementPresent(
                STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK,
                "Cannot find 'Узнать подробнее о сборе данных' link ",
                10);
    }

    public void clickNextButton(){
        this.waitForElementAndClick(
                NEXT_LINK,
                "Cannot find and click 'Далее' button",
                10);
    }

    public void clickGetStartedButton(){
        this.waitForElementAndClick(
                GET_STARTED_BUTTON,
                "Cannot find and click 'Начать' button",
                10);
    }
}

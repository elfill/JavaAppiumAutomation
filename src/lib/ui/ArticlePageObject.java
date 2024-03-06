package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    public ArticlePageObject(AppiumDriver driver){
        super(driver);
    }

    private static final String
            TITLE_IN_PORTRAIT_ORIENTATION = "//android.view.View/android.view.View[@instance = 2]",
            TITLE_IN_LANDSCAPE_ORIENTATION = "//android.view.View/android.view.View[@instance = 3]",
            FOOTER = "//*[@text='View article in browser']",
            SAVE_BUTTON = "//*[@content-desc='Save']",
            ADD_TO_LIST_BUTTON = "//android.widget.LinearLayout//*[@resource-id ='org.wikipedia:id/snackbar_action']",
            MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
            MY_LIST_OK_BUTTON = "//*[@text = 'OK']",
            NAVIGATE_UP_BUTTON = "//android.widget.ImageButton[@content-desc= 'Navigate up']";

    public WebElement waitForTitleElementInPortraitOrientation(){
        return this.waitForElementPresent(
                By.xpath(TITLE_IN_PORTRAIT_ORIENTATION),
                "Cannot find article title on page!",
                15);
    }

    public WebElement waitForTitleElementInLandscapeOrientation(){
        return this.waitForElementPresent(
                By.xpath(TITLE_IN_LANDSCAPE_ORIENTATION),
                "Cannot find article title on page!",
                15);
    }

    public String getArticleTitleInPortraitOrientation(){
        WebElement title_element = waitForTitleElementInPortraitOrientation();
        return  title_element.getAttribute("text");
    }

    public String getArticleTitleInLandscapeOrientation(){
        WebElement title_element = waitForTitleElementInLandscapeOrientation();
        return  title_element.getAttribute("text");
    }

    public void  swipeToFooter(){
        this.swipeUpToFindElement(
                By.xpath(FOOTER),
                "Cannot find the end of article",
                20
        );
    }

    public void addArticleToMyList(String name_of_folder){
        this.waitForElementAndClick(
                By.xpath(SAVE_BUTTON),
                "Cannot find button to save article",
                5
        );

         this.waitForElementAndClick(
                By.xpath(ADD_TO_LIST_BUTTON),
                "Cannot find button to save article",
                5
        );

        this.waitForElementAndSendKeys(
                By.id(MY_LIST_NAME_INPUT),
                name_of_folder,
                "Cannot put text into articles folder input",
                5
        );

        this.waitForElementAndClick(
                By.xpath(MY_LIST_OK_BUTTON),
                "Cannot press 'OK' button",
                5
        );
    }

    public void closeArticle() {
        this.waitForElementAndClick(
                By.xpath(NAVIGATE_UP_BUTTON),
                "Cannot close article, cannot find 'Navigate Up' button",
                5
        );
        this.waitForElementAndClick(
                By.xpath(NAVIGATE_UP_BUTTON),
                "Cannot close article, cannot find 'Navigate Up' button",
                5
        );
    }
}

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
            NAVIGATE_UP_BUTTON = "//android.widget.ImageButton[@content-desc= 'Navigate up']",
            SAVE_TO_FOLDER_TPL = "//*[contains(@text, '{NAME_OF_FOLDER}')]",
            VIEW_LIST_BUTTON = "//android.widget.LinearLayout//*[@text ='View list']"
            ;

    /* TEMPLATE METHODS */
    private static String getXPathOfFolderToSave(String name_of_folder){
        return SAVE_TO_FOLDER_TPL.replace("{NAME_OF_FOLDER}", name_of_folder);
    }

//    private static String getXPathOfButtonViewList(String name_of_button){
//        return VIEW_LIST_BUTTON_TPL.replace("{NAME_OF_BUTTON}", name_of_button);
//    }

    /* TEMPLATE METHODS */

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

    public void addAdditionalArticleToMyList(String name_of_folder){
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

        String folder_to_save_xpath = getXPathOfFolderToSave(name_of_folder);

        this.waitForElementAndClick(
                By.xpath(folder_to_save_xpath),
                "Cannot choose existing folder to save article",
                5
        );
    }

    public void moveToViewList(){
        this.waitForElementAndClick(
                By.xpath(VIEW_LIST_BUTTON),
                "Cannot view saved list",
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

    public void assertText() {
        super.assertElementPresent(
                By.id("pcs-edit-section-title-description"),
                "text",
                "Object-oriented programming language",
                "Element is not found",
                5
        );
    }
}

package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    public ArticlePageObject(AppiumDriver driver){
        super(driver);
    }

    private static final String
            TITLE_IN_PORTRAIT_ORIENTATION = "xpath://android.view.View/android.view.View[@instance = 2]",
            TITLE_IN_LANDSCAPE_ORIENTATION = "xpath://android.view.View/android.view.View[@instance = 3]",
            FOOTER = "xpath://*[@text='View article in browser']",
            SAVE_BUTTON = "xpath://*[@content-desc='Save']",
            ADD_TO_LIST_BUTTON = "xpath://android.widget.LinearLayout//*[@resource-id ='org.wikipedia:id/snackbar_action']",
            MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input",
            MY_LIST_OK_BUTTON = "xpath://*[@text = 'OK']",
            NAVIGATE_UP_BUTTON = "xpath://android.widget.ImageButton[@content-desc= 'Navigate up']",
            SAVE_TO_FOLDER_TPL = "xpath://*[contains(@text, '{NAME_OF_FOLDER}')]",
            VIEW_LIST_BUTTON = "xpath://android.widget.LinearLayout//*[@text ='View list']",
            ASSERT_TEXT_LINK ="id:pcs-edit-section-title-description"
            ;

    /* TEMPLATE METHODS */
    private static String getXPathOfFolderToSave(String name_of_folder){
        return SAVE_TO_FOLDER_TPL.replace("{NAME_OF_FOLDER}", name_of_folder);
    }

    /* TEMPLATE METHODS */

    public WebElement waitForTitleElementInPortraitOrientation(){
        return this.waitForElementPresent(
                TITLE_IN_PORTRAIT_ORIENTATION,
                "Cannot find article title on page!",
                15);
    }

    public WebElement waitForTitleElementInLandscapeOrientation(){
        return this.waitForElementPresent(
                TITLE_IN_LANDSCAPE_ORIENTATION,
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
                FOOTER,
                "Cannot find the end of article",
                20
        );
    }

    public void addArticleToMyList(String name_of_folder){
        this.waitForElementAndClick(
                SAVE_BUTTON,
                "Cannot find button to save article",
                5
        );

         this.waitForElementAndClick(
                ADD_TO_LIST_BUTTON,
                "Cannot find button to save article",
                5
        );

        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot put text into articles folder input",
                5
        );

        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot press 'OK' button",
                5
        );
    }

    public void addAdditionalArticleToMyList(String name_of_folder){
        this.waitForElementAndClick(
                SAVE_BUTTON,
                "Cannot find button to save article",
                5
        );

        this.waitForElementAndClick(
                ADD_TO_LIST_BUTTON,
                "Cannot find button to save article",
                5
        );

        String folder_to_save_xpath = getXPathOfFolderToSave(name_of_folder);

        this.waitForElementAndClick(
                folder_to_save_xpath,
                "Cannot choose existing folder to save article",
                5
        );
    }

    public void moveToViewList(){
        this.waitForElementAndClick(
                VIEW_LIST_BUTTON,
                "Cannot view saved list",
                5
        );
    }

    public void closeArticle() {
        this.waitForElementAndClick(
                NAVIGATE_UP_BUTTON,
                "Cannot close article, cannot find 'Navigate Up' button",
                5
        );
        this.waitForElementAndClick(
                NAVIGATE_UP_BUTTON,
                "Cannot close article, cannot find 'Navigate Up' button",
                5
        );
    }

    public void assertText() {
        super.assertElementPresent(
                ASSERT_TEXT_LINK,
                "text",
                "Object-oriented programming language",
                "Element is not found",
                5
        );
    }
}

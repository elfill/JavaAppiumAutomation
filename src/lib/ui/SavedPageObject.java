package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SavedPageObject extends MainPageObject {

    public SavedPageObject(AppiumDriver driver) {
        super(driver);
    }

    private static final String
            NOT_NOW_BUTTON = "org.wikipedia:id/negativeButton",
            FOLDER_BY_NAME_TPL = "//*[@text = '{FOLDER_NAME}']",
            ARTICLE_BY_TITLE_TPL = "//*[@text = '{TITLE}']",
            SAVED_ARTICLE_LIST = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@resource-id='org.wikipedia:id/page_list_item_title']",
            SEARCH_RESULT_BY_SUBSTRING_IN_SAVED_FOLDER_TPL = "//*[@text='{SUBSTRING}']",
            PATH_TO_FIRST_TITLE_OF_ARTICLE_IN_SAVED_FOLDER = "org.wikipedia:id/page_list_item_description",
            PATH_TO_SECOND_TITLE_OF_ARTICLE_IN_SAVED_FOLDER = "pcs-edit-section-title-description"

                    ;


    /* TEMPLATE METHODS */
    private static String getFolderXPathByName(String name_of_folder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSaveArticleXPathByTitle(String article_title) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_IN_SAVED_FOLDER_TPL.replace("{SUBSTRING}", substring);
    }

    /* TEMPLATE METHODS */


    public void clickNotNow() {
        this.waitForElementAndClick(
                By.id(NOT_NOW_BUTTON),
                "Cannot press 'Not now' button",
                5
        );
    }

    public void openFolderByName(String name_of_folder) {
        String folder_name_xpath = getFolderXPathByName(name_of_folder);
        this.waitForElementAndClick(
                By.xpath(folder_name_xpath),
                "Cannot find folder by name " + name_of_folder,
                5
        );
    }

    private void waitForArticleToAppearByTitle(String article_title) {
        String article_xpath = getSaveArticleXPathByTitle(article_title);
        this.waitForElementPresent(
                By.xpath(article_xpath),
                "Cannot find saved article by title " + article_title,
                15
        );
    }

    private void waitForArticleToDisappearByTitle(String article_title) {
        String article_xpath = getSaveArticleXPathByTitle(article_title);
        this.waitForElementNotPresent(
                By.xpath(article_xpath),
                "Saved article still present with title " + article_title,
                15
        );
    }

    public void swipeByArticleToDelete(String article_title) {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSaveArticleXPathByTitle(article_title);
        this.swipeElementToLeft(
                By.xpath(article_xpath),
                "Cannot find saved article"
        );
        this.waitForArticleToDisappearByTitle(article_title);
    }

    public int getAmountOfSavedArticles() {
        this.waitForElementPresent(
                By.xpath(SAVED_ARTICLE_LIST),
                "Cannot find list of saved articles",
                15
        );
        return this.getAmountOfElements(By.xpath(SAVED_ARTICLE_LIST));
    }

    public void clickSavedByArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(
                By.xpath(search_result_xpath),
                "Cannot find and click search result with " + substring,
                10);
    }

    public String savingFirstArticleTitleInSavedFolderToCompare(String text_of_title) {
        text_of_title = super.waitForElementAndGetAttribute(
                By.id(PATH_TO_FIRST_TITLE_OF_ARTICLE_IN_SAVED_FOLDER),
                "text",
                "Cannot find title of article",
                15
        );
        return text_of_title;
    }

    public String savingSecondArticleTitleInSavedFolderToCompare(String text_of_title) {
        text_of_title = super.waitForElementAndGetAttribute(
                By.id(PATH_TO_SECOND_TITLE_OF_ARTICLE_IN_SAVED_FOLDER),
                "text",
                "Cannot find title of article",
                15
        );
        return text_of_title;
    }

}
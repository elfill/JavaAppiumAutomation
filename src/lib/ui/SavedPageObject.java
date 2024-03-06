package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SavedPageObject extends MainPageObject{

    public SavedPageObject(AppiumDriver driver){
        super(driver);
    }

    private static final String
            NOT_NOW_BUTTON = "org.wikipedia:id/negativeButton",
            FOLDER_BY_NAME_TPL = "//*[@text = '{FOLDER_NAME}']",
            ARTICLE_BY_TITLE_TPL = "//*[@text = '{TITLE}']"

            ;


    /* TEMPLATE METHODS */
    private static String getFolderXPathByName(String name_of_folder){
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSaveArticleXPathByTitle(String article_title){
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }
    /* TEMPLATE METHODS */


    public void clickNotNow(){
        this.waitForElementAndClick(
                By.id(NOT_NOW_BUTTON),
                "Cannot press 'Not now' button",
                5
        );
    }

    public void openFolderByName(String name_of_folder){
        String folder_name_xpath = getFolderXPathByName(name_of_folder);
        this.waitForElementAndClick(
                By.xpath(folder_name_xpath),
                "Cannot find folder by name " + name_of_folder,
                5
        );
    }

    private void waitForArticleToAappearByTitle(String article_title){
        String article_xpath = getSaveArticleXPathByTitle(article_title);
        this.waitForElementPresent(
                By.xpath(article_xpath),
                "Cannot find saved article by title " + article_title,
                15
        );
    }

    private void waitForArticleToDisappearByTitle(String article_title){
        String article_xpath = getSaveArticleXPathByTitle(article_title);
        this.waitForElementNotPresent(
                By.xpath(article_xpath),
                "Saved article still present with title " + article_title,
                15
        );
    }

    public void swipeByArticletoDelete(String article_title){
        this.waitForArticleToAappearByTitle(article_title);
        String article_xpath = getSaveArticleXPathByTitle(article_title);
        this.swipeElementToLeft(
                By.xpath(article_xpath),
                "Cannot find saved article"
        );
        this.waitForArticleToDisappearByTitle(article_title);
    }


}

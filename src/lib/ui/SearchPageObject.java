package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {

    public SearchPageObject(AppiumDriver driver){
        super(driver);
    }

    private static final String
            SEARCH_SKIP_BUTTON = "id:org.wikipedia:id/fragment_onboarding_skip_button",
            SEARCH_INIT_ELEMENT = "xpath://*[contains(@text, 'Search Wikipedia')]",
            SEARCH_INPUT = "id:org.wikipedia:id/search_src_text",
            SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id = 'org.wikipedia:id/fragment_search_results']//*[@text = '{SUBSTRING}']",
            SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_title']",
            SEARCH_RESULT_EMPTY_ELEMENT = "xpath://*[@text='No results']",
            SEARCH_COMPARED_TEXT_TPL = "xpath://*[contains(@text, '{COMPARED_TEXT}')]"
                    ;

    /* TEMPLATE METHODS */
    private static String getResultSearchElement(String substring){
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getXpathForCompareText(String compared_text){
        return SEARCH_COMPARED_TEXT_TPL.replace("{COMPARED_TEXT}", compared_text);
    }

    /* TEMPLATE METHODS */

    public void pressSkipButtonAtStartOfApp() {
        this.waitForElementPresent(
                SEARCH_SKIP_BUTTON,
                "Cannot find 'SKIP' button",
                5);
        this.waitForElementAndClick(
                SEARCH_SKIP_BUTTON,
                "Cannot click 'SKIP' button",
                5);
    }

    public void initSearchInput() {
        this.waitForElementPresent(
                SEARCH_INIT_ELEMENT,
                "Cannot find search input after clicking search init element");
        this.waitForElementAndClick(
                SEARCH_INIT_ELEMENT,
                "Cannot find and click search init element",
                5);
    }

    public void waitForCancelButtonToAppear(){
        this.waitForElementPresent(
                SEARCH_CANCEL_BUTTON,
                "Cannot find search cancel button",
                5);
    }

    public void waitForCancelButtonToDisappear(){
        this.waitForElementNotPresent(
                SEARCH_CANCEL_BUTTON,
                "Search cancel button still present",
                5);
    }

    public void clickCancelSearch(){
        this.waitForElementAndClick(
                SEARCH_CANCEL_BUTTON,
                "Cannot find and click cancel button",
                5);
    }

    public void typeSearchLine(String search_line){
        this.waitForElementAndSendKeys(
                SEARCH_INPUT, search_line,
                "Cannot find and type into search input",
                5);
    }

    public void waitForSearchResult(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(
                search_result_xpath,
                "Cannot find search result with " + substring);
    }

    public void clickByArticleWithSubstring(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(
                search_result_xpath,
                "Cannot find and click search result with " + substring,
                10);
    }

    public int getAmountOfFoundArticles(){
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by the request",
                15
        );
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public void waitForEmptyResultLabel(){
        this.waitForElementPresent(
                SEARCH_RESULT_EMPTY_ELEMENT,
                "Cannot find empty result element",
                15);
    }

    public void assertThereIsNoResultOfSearch(){
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT, "We supposed not to find any results");
    }

    public void compareText(String compared_text){
        String compared_text_xpath = getXpathForCompareText(compared_text);
        this.assertElementHasText(
                compared_text_xpath,
                compared_text,
                "Expected text " + "'" + compared_text + "'" + " not found",
                5);
    }

    public void waitElementNotPresent(String search_line){
        this.waitForElementNotPresent(
                SEARCH_RESULT_ELEMENT,
                "Search result " + "'" + search_line + "'" + " still appear ",
                5
        );
    }

    public void clearSearchFiled(){
        this.waitForElementAndClear(
                SEARCH_INPUT,
                "Cannot find search field",
                5
        );
    }

}

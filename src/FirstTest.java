import lib.CoreTestCase;
import lib.UI.MainPageObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;

public class FirstTest extends CoreTestCase {

    private MainPageObject MainPageObject;

    protected void setUp() throws Exception{
        super.setUp();

        MainPageObject = new MainPageObject(driver);
    }


    @Test
    public void testSearch() {

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip' button",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/fragment_search_results']//*[@text = 'Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by Java",
                5
        );

    }

    @Test
    public void testCancelSearch()
    {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip' button",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find 'Search…' input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find 'X' to cancel search",
                5
        );

        MainPageObject.waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "X is still present on the page",
                5
        );
    }

    @Test
    public void testCompareArticleTitle() {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip' button",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/fragment_search_results']//*[@text = 'Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by Java",
                5
        );

        WebElement title_element = MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id = 'pcs']//*[@text = 'Java (programming language)']"),
                "Cannot find article title",
                15
        );

        String article_title = title_element.getAttribute("text");
        Assert.assertEquals(
                "We see unexpected title",
                "Java (programming language)",
                article_title
        );
    }

    @Test
    public void testCompareText()
    {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip' button",
                5
        );
        MainPageObject.assertElementHasText(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Search Wikipedia",
                "The expected text does not match the found one",
                5
        );
    }

    @Test
    public void testSearchResultAndClear()
    {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip' button",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find 'Search Wikipedia' input",
                5
        );

         WebElement searchResults = MainPageObject.waitForElementPresent(
                By.xpath("//android.view.ViewGroup[@instance=2]"),
                "Cannot find results of search",
                5
        );

        MainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search field",
                5
        );

        MainPageObject.waitForElementNotPresent(
                By.xpath("//android.view.ViewGroup[@instance=2]"),
                "Search results not clear",
                5
        );
    }

    @Test
    public void testSwipeArticle() {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip' button",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Appium",
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_title'][@text = 'Appium']"),
                "Cannot find 'Appium' article in search",
                5
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id = 'pcs-edit-section-title-description'][@text = 'Automation for Apps']"),
                "Cannot find article title",
                15
        );

        MainPageObject.swipeUpToFindElement(
                By.xpath("//*[@text='View article in browser']"),
                "Cannot fine the end of the article",
                20
        );
    }

    @Test
    public void testSaveFirstArticleToMyList()
    {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip' button",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/fragment_search_results']//*[@text = 'Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by Java",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@content-desc='Save']"),
                "Cannot find button to save article",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.LinearLayout//*[@resource-id ='org.wikipedia:id/snackbar_action']"),
                "Cannot find button to save article",
                5
        );

        String name_of_folder = "Learning programming";

        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot put text into articles folder input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text = 'OK']"),
                "Cannot press 'OK' button",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc= 'Navigate up']"),
                "Cannot close article, cannot find 'X' link",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc= 'Navigate up']"),
                "Cannot close search list, cannot find 'arrow back' link",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc = 'Saved']"),
                "Cannot find navigation button 'Saved'",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/negativeButton"),
                "Cannot press 'Not now' button",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text = '" + name_of_folder + "']"),
                "Cannot find created folder",
            5
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@text = 'Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' text",
                5
        );

        MainPageObject.swipeElementToLeft(
                By.xpath("//*[@text = 'Object-oriented programming language']"),
                "Cannot find save article"
        );

        MainPageObject.waitForElementNotPresent(
                By.xpath("//*[@text = 'Object-oriented programming language']"),
                "Cannot delete saved article",
                5
        );

    }

    @Test
    public  void testAmountOfNotEmptySearch()
    {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip' button",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String search_line = "Linkin park discography";

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                search_line,
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_title']";

        MainPageObject.waitForElementPresent(
                By.xpath(search_result_locator),
                "Cannot find anything by the request" + search_line,
                15
        );

        int amount_of_search_results = MainPageObject.getAmountOfElements(
                By.xpath(search_result_locator)
        );

        Assert.assertTrue(
                "We found too few results!",
                amount_of_search_results > 0
        );
    }

    @Test
    public void testAmountOfEmptySearch()
    {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip' button",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String search_line = "zxcvasdfqwer";

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                search_line,
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_title']";
        String empty_result_label = "//*[@text='No results']";

        MainPageObject.waitForElementPresent(
                By.xpath(empty_result_label),
                "Cannot find result label by the request " + search_line,
                15
        );

        MainPageObject.assertElementNotPresent(
                By.xpath(search_result_locator),
                "We have found some results by request " + search_line
        );
    }

    @Test
    public void testChangeScreenOrientationOnSearchResults()
    {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip' button",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String search_line = "java";

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                search_line,
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/fragment_search_results']//*[@text = 'Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by " + search_line,
                15
        );

        String title_before_rotation = MainPageObject.waitForElementAndGetAttribute(
                By.id("pcs-edit-section-title-description"),
                "text",
                "Cannot find title of article",
                15
        );

        driver.rotate(ScreenOrientation.LANDSCAPE);

        String title_after_rotation = MainPageObject.waitForElementAndGetAttribute(
                By.id("pcs-edit-section-title-description"),
                "text",
                "Cannot find title of article",
                15
        );

        Assert.assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_rotation
        );

        driver.rotate(ScreenOrientation.PORTRAIT);

        String title_after_second_rotation = MainPageObject.waitForElementAndGetAttribute(
                By.id("pcs-edit-section-title-description"),
                "text",
                "Cannot find title of article",
                15
        );

        Assert.assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_second_rotation
        );


    }

    @Test
    public void testCheckSearchArticleInBackground()
    {

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip' button",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/fragment_search_results']//*[@text = 'Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by Java",
                5
        );

        driver.runAppInBackground(2);

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/fragment_search_results']//*[@text = 'Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic after returning from background",
                5
        );

    }

    @Test
    public void saveTwoArticleToMyList()
    {

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip' button",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/fragment_search_results']//*[@text = 'Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by Java",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@content-desc='Save']"),
                "Cannot find button to save article",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.LinearLayout//*[@resource-id ='org.wikipedia:id/snackbar_action']"),
                "Cannot find button to save article",
                5
        );

        String name_of_folder = "Save 2 article";

        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot put text into articles folder input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text = 'OK']"),
                "Cannot press 'OK' button",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/fragment_search_results']//*[@text = 'Island in Indonesia']"),
                "Cannot find 'Island in Indonesia' topic searching by Java",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@content-desc='Save']"),
                "Cannot find button to save article",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.LinearLayout//*[@resource-id ='org.wikipedia:id/snackbar_action']"),
                "Cannot find button to save article",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/item_title_container"),
                "Cannot choose existing folder to save article",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.LinearLayout//*[@text ='View list']"),
                "Cannot find button to save article",
                5
        );



        int amount_of_saved_articles_before_deletion = MainPageObject.getAmountOfElements(
                By.xpath("//*[@resource-id='org.wikipedia:id/reading_list_recycler_view']/android.widget.FrameLayout")
        );

        Assert.assertTrue(
                "We found the wrong number of results!",
                amount_of_saved_articles_before_deletion == 2
        );

        String title_before_deletion = MainPageObject.waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/page_list_item_description"),
                "text",
                "Cannot find title of article",
                15
        );

        MainPageObject.swipeElementToLeft(
                By.xpath("//*[@text = 'Object-oriented programming language']"),
                "Cannot find save article"
        );

        MainPageObject.waitForElementNotPresent(
                By.xpath("//*[@text = 'Object-oriented programming language']"),
                "Cannot delete saved article",
                5
        );

        int amount_of_saved_articles_after_deletion = MainPageObject.getAmountOfElements(
                By.xpath("//*[@resource-id='org.wikipedia:id/reading_list_recycler_view']/android.widget.FrameLayout")
        );

        Assert.assertTrue(
                "We found too many results!",
                amount_of_saved_articles_after_deletion == 1
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Island in Indonesia']"),
                "Cannot found text 'Island in Indonesia'",
                15
        );

        String title_after_deletion = MainPageObject.waitForElementAndGetAttribute(
                By.id("pcs-edit-section-title-description"),
                "text",
                "Cannot find title of article",
                15
        );

        Assert.assertEquals(
                "Article title have been changed after deletion second article",
                title_before_deletion,
                title_after_deletion
        );

    }

    @Test
    public void testAssertExpection()
    {

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip' button",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/fragment_search_results']//*[@text = 'Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by Java",
                5
        );

        MainPageObject.assertElementPresent(
                By.id("pcs-edit-section-title-description"),
                "text",
                "Object-oriented programming language",
                "Element is not found",
                5
        );
    }
}

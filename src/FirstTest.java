import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;
import org.openqa.selenium.*;

public class FirstTest extends CoreTestCase {

    private MainPageObject MainPageObject;

    protected void setUp() throws Exception{
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        MainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testSearchAndCompareText() {
        {
            SearchPageObject SearchPageObject = new SearchPageObject(driver);
            SearchPageObject.pressSkipButtonAtStartOfApp();
            SearchPageObject.initSearchInput();
            String compared_text = "ololo";
            SearchPageObject.compareText(compared_text);
        }
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

        assertTrue(
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

        assertTrue(
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

        assertEquals(
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

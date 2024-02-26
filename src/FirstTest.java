import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDeivce");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/16984529/Desktop/JavaApiumAutomation/JavaAppiumAutomation/apks/Wikipedia_2.7.50449-r-2023-07-31_Apkpure.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.rotate(ScreenOrientation.PORTRAIT);
        driver.quit();
    }

    @Test
    public void firstTest() {

        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip' button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementPresent(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/fragment_search_results']//*[@text = 'Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by Java",
                5
        );

    }

    @Test
    public void testCancelSearch()
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip' button",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find 'Search…' input",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find 'X' to cancel search",
                5
        );

        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "X is still present on the page",
                5
        );
    }

    @Test
    public void testCompareArticleTitle() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip' button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/fragment_search_results']//*[@text = 'Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by Java",
                5
        );

        WebElement title_element = waitForElementPresent(
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
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip' button",
                5
        );
        assertElementHasText(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Search Wikipedia",
                "The expected text does not match the found one",
                5
        );
    }

    @Test
    public void testSearchResultAndClear()
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip' button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find 'Search Wikipedia' input",
                5
        );

         WebElement searchResults = waitForElementPresent(
                By.xpath("//android.view.ViewGroup[@instance=2]"),
                "Cannot find results of search",
                5
        );

        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search field",
                5
        );

        waitForElementNotPresent(
                By.xpath("//android.view.ViewGroup[@instance=2]"),
                "Search results not clear",
                5
        );
    }

    @Test
    public void testSwipeArticle() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip' button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Appium",
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_title'][@text = 'Appium']"),
                "Cannot find 'Appium' article in search",
                5
        );

        waitForElementPresent(
                By.xpath("//*[@resource-id = 'pcs-edit-section-title-description'][@text = 'Automation for Apps']"),
                "Cannot find article title",
                15
        );

        swipeUpToFindElement(
                By.xpath("//*[@text='View article in browser']"),
                "Cannot fine the end of the article",
                20
        );
    }

    @Test
    public void saveFirstArticleToMyList()
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip' button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/fragment_search_results']//*[@text = 'Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by Java",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@content-desc='Save']"),
                "Cannot find button to save article",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.LinearLayout//*[@resource-id ='org.wikipedia:id/snackbar_action']"),
                "Cannot find button to save article",
                5
        );

        String name_of_folder = "Learning programming";

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot put text into articles folder input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text = 'OK']"),
                "Cannot press 'OK' button",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc= 'Navigate up']"),
                "Cannot close article, cannot find 'X' link",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc= 'Navigate up']"),
                "Cannot close search list, cannot find 'arrow back' link",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc = 'Saved']"),
                "Cannot find navigation button 'Saved'",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/negativeButton"),
                "Cannot press 'Not now' button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text = '" + name_of_folder + "']"),
                "Cannot find created folder",
            5
        );

        waitForElementPresent(
                By.xpath("//*[@text = 'Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' text",
                5
        );

        swipeElementToLeft(
                By.xpath("//*[@text = 'Object-oriented programming language']"),
                "Cannot find save article"
        );

        waitForElementNotPresent(
                By.xpath("//*[@text = 'Object-oriented programming language']"),
                "Cannot delete saved article",
                5
        );

    }

    @Test
    public  void testAmountOfNotEmptySearch()
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip' button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String search_line = "Linkin park discography";

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                search_line,
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_title']";

        waitForElementPresent(
                By.xpath(search_result_locator),
                "Cannot find anything by the request" + search_line,
                15
        );

        int amount_of_search_results = getAmountOfElements(
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
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip' button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String search_line = "zxcvasdfqwer";

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                search_line,
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_title']";
        String empty_result_label = "//*[@text='No results']";

        waitForElementPresent(
                By.xpath(empty_result_label),
                "Cannot find result label by the request " + search_line,
                15
        );

        assertElementNotPresent(
                By.xpath(search_result_locator),
                "We have found some results by request " + search_line
        );
    }

    @Test
    public void testChangeScreenOrientationOnSearchResults()
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip' button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String search_line = "java";

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                search_line,
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/fragment_search_results']//*[@text = 'Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by " + search_line,
                15
        );

        String title_before_rotation = waitForElementAndGetAttribute(
                By.id("pcs-edit-section-title-description"),
                "text",
                "Cannot find title of article",
                15
        );

        driver.rotate(ScreenOrientation.LANDSCAPE);

        String title_after_rotation = waitForElementAndGetAttribute(
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

        String title_after_second_rotation = waitForElementAndGetAttribute(
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

        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip' button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementPresent(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/fragment_search_results']//*[@text = 'Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by Java",
                5
        );

        driver.runAppInBackground(2);

        waitForElementPresent(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/fragment_search_results']//*[@text = 'Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic after returning from background",
                5
        );

    }

    @Test
    public void saveTwoArticleToMyList()
    {
        
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip' button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/fragment_search_results']//*[@text = 'Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by Java",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@content-desc='Save']"),
                "Cannot find button to save article",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.LinearLayout//*[@resource-id ='org.wikipedia:id/snackbar_action']"),
                "Cannot find button to save article",
                5
        );

        String name_of_folder = "Save 2 article";

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot put text into articles folder input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text = 'OK']"),
                "Cannot press 'OK' button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/fragment_search_results']//*[@text = 'Island in Indonesia']"),
                "Cannot find 'Island in Indonesia' topic searching by Java",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@content-desc='Save']"),
                "Cannot find button to save article",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.LinearLayout//*[@resource-id ='org.wikipedia:id/snackbar_action']"),
                "Cannot find button to save article",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/item_title_container"),
                "Cannot choose existing folder to save article",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.LinearLayout//*[@text ='View list']"),
                "Cannot find button to save article",
                5
        );



        int amount_of_saved_articles_before_deletion = getAmountOfElements(
                By.xpath("//*[@resource-id='org.wikipedia:id/reading_list_recycler_view']/android.widget.FrameLayout")
        );

        Assert.assertTrue(
                "We found the wrong number of results!",
                amount_of_saved_articles_before_deletion == 2
        );

        String title_before_deletion = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/page_list_item_description"),
                "text",
                "Cannot find title of article",
                15
        );

        swipeElementToLeft(
                By.xpath("//*[@text = 'Object-oriented programming language']"),
                "Cannot find save article"
        );

        waitForElementNotPresent(
                By.xpath("//*[@text = 'Object-oriented programming language']"),
                "Cannot delete saved article",
                5
        );

        int amount_of_saved_articles_after_deletion = getAmountOfElements(
                By.xpath("//*[@resource-id='org.wikipedia:id/reading_list_recycler_view']/android.widget.FrameLayout")
        );

        Assert.assertTrue(
                "We found too many results!",
                amount_of_saved_articles_after_deletion == 1
        );

        waitForElementAndClick(
                By.xpath("//*[@text='Island in Indonesia']"),
                "Cannot found text 'Island in Indonesia'",
                15
        );

        String title_after_deletion = waitForElementAndGetAttribute(
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



    //методы
    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by, error_message, 5);
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInseconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInseconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    private void assertElementHasText(By by, String expected_text, String error_message, long timeoutInSeconds) {
        WebElement webElement = waitForElementPresent(by, error_message, timeoutInSeconds);
        String element = webElement.getText();
        Assert.assertEquals(error_message,expected_text,element);
    }

    protected void swipeUP(int timeOfSwipe)
    {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);
        action
                .press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y)
                .release().perform();
    }

    protected void swipeUpQuick()
    {
        swipeUP(200);
    }

    protected void  swipeUpToFindElement(By by, String error_message, int max_swipes)
    {
        int already_swipe = 0;
        while (driver.findElements(by).size() == 0)
        {
            if (already_swipe > max_swipes){
                waitForElementPresent(by, "Cannot find element by swiping up.\n" + error_message, 0);
                return;
            }
            swipeUpQuick();
            ++already_swipe;
        };
    }

    protected void swipeElementToLeft(By by, String error_message)
    {
        WebElement element = waitForElementPresent(
                by,
                error_message,
                10);

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(300)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    };

    private  int getAmountOfElements(By by)
    {
        List elements = driver.findElements(by);
        return elements.size();
    }

    private void  assertElementNotPresent(By by, String error_message)
    {
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements > 0) {
            String default_message = "An element '" + by.toString() + "' supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    private String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        return  element.getAttribute(attribute);
    }

}

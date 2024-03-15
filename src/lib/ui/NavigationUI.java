package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject{

    public NavigationUI(AppiumDriver driver){
        super(driver);
    }

    private static final String
            SAVED_BUTTON_LINK = "xpath://android.widget.FrameLayout[@content-desc = 'Saved']";

    public void clicksaved(){
        this.waitForElementAndClick(
                SAVED_BUTTON_LINK,
                "Cannot find navigation button 'Saved'",
                5
        );
    }
}

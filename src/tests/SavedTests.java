package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.NavigationUI;
import lib.ui.SavedPageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class SavedTests extends CoreTestCase {
    @Test
    public void testSaveFirstArticleToMyList()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.pressSkipButtonAtStartOfApp();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElementInPortraitOrientation();

        String article_title = ArticlePageObject.getArticleTitleInPortraitOrientation();
        String name_of_folder = "Learning programming";

        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clicksaved();

        SavedPageObject SavePageObject = new SavedPageObject(driver);
        SavePageObject.clickNotNow();
        SavePageObject.openFolderByName(name_of_folder);
        SavePageObject.swipeByArticletoDelete(article_title);
    }
}

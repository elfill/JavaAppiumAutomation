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
        SavePageObject.swipeByArticleToDelete(article_title);
    }

    @Test
    public void testSaveTwoArticleToMyList()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.pressSkipButtonAtStartOfApp();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElementInPortraitOrientation();
        String name_of_folder = "save 2 articles";
        ArticlePageObject.addArticleToMyList(name_of_folder);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Island in Indonesia");
        ArticlePageObject.addAdditionalArticleToMyList(name_of_folder);
        ArticlePageObject.moveToViewList();
        SavedPageObject SavedPageObject = new SavedPageObject(driver);
        SavedPageObject.getAmountOfSavedArticles();
        assertTrue(
                "We found the wrong number of results!",
                SavedPageObject.getAmountOfSavedArticles() == 2
        );
        String title_before_deletion = SavedPageObject.savingFirstArticleTitleInSavedFolderToCompare("Java");
        SavedPageObject.swipeByArticleToDelete("Object-oriented programming language");
        SavedPageObject.getAmountOfSavedArticles();
        assertTrue(
                "We found too many results!",
                SavedPageObject.getAmountOfSavedArticles() == 1
        );
        SavedPageObject.clickSavedByArticleWithSubstring("Island in Indonesia");
        String title_after_deletion = SavedPageObject.savingSecondArticleTitleInSavedFolderToCompare("Java");
        assertEquals(
                "Article title have been changed after deletion second article",
                title_before_deletion,
                title_after_deletion
        );
    }

}

package org.example.pages;

import com.github.jsdevel.testng.selenium.AbstractPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.net.URL;

public class GoogleHomePage extends AbstractPage<GoogleHomePage, SamplePageFactory> {
  @FindBy(css = "[name=q]")
  public WebElement q;

  public GoogleSearchResultsPage submitSearch(String query) {
    q.sendKeys(query);
    q.submit();
    return getPageFactory().getSearchResultsPage();
  }

  @Override
  public boolean isPageViewableFrom(URL proposedUrl) {
    return proposedUrl.getPath().startsWith("/");
  }
}

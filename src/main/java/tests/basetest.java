package tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class basetest {
    Page page;
    Playwright playwright;
    Browser browser;
    String baseurl;


    @BeforeMethod
    public void setup() throws IOException {
        Properties prop =new Properties();
        FileInputStream fis= new FileInputStream("src/main/resources/config.properties");
        prop.load(fis);
        String browserName =prop.getProperty("browser");
        playwright = Playwright.create();
        if("firefox".equals(browserName))
        {
           browser= playwright.firefox().launch();
        } else if ("safari".equals(browserName)) {
           browser= playwright.webkit().launch();
        }
        else if ("chrome".equals(browserName)){
            browser =playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        }



        page =browser.newPage();
        page.setDefaultTimeout(8000);
        baseurl= prop.getProperty("qa_url");

    }
}

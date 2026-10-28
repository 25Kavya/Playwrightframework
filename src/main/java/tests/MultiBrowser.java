package tests;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.nio.file.Paths;

public class MultiBrowser
{

        Playwright playwright;
        Browser browser;
        Page page;
    BrowserContext context;

    String browserName;

    @BeforeMethod
    @Parameters("browser")
    public void setup(@Optional("chromium") String browserName){
        this.browserName= browserName;
        playwright= Playwright.create();
        BrowserType browserType;
        switch (browserName.toLowerCase()) {
            case "firefox": browserType= playwright.firefox(); break;
            case "webkit": browserType= playwright.webkit(); break;
            case "chromium": browserType= playwright.chromium(); break;
            default: throw new IllegalArgumentException("Unknown browser: " + browserName);
        }
        browser= browserType.launch(new BrowserType.LaunchOptions().setHeadless(false));
        context= browser.newContext();
        context.tracing().start(new Tracing.StartOptions()
        .setScreenshots(true)
        .setSnapshots(true)
        .setSources(true));
        page= context.newPage();
        page.navigate("https://rahulshettyacademy.com/loginpagePractise/");



        }
        @AfterMethod
        public void teardown()
        {
            context.tracing().stop(new Tracing.StopOptions()
            .setPath(Paths.get("trace-" + browserName + ".zip")));
            context.close();
            browser.close();
            playwright.close();
        }


@Test
    public void ChildWindowHandling(){

       Locator BlinkingTexts= page.locator(".blinkingText");
       Page childpage= context.waitForPage(()-> BlinkingTexts.first().click());
       childpage.waitForLoadState();
       String childText =childpage.locator(".red").textContent();
       String emailId= childText.split("at")[1].trim().split(" ")[0];
       childpage.close();
       page.bringToFront();
       page.getByLabel("Username").fill(emailId);
       System.out.println(page.getByLabel("Username").inputValue());
       page.waitForTimeout(4000);
    }

    @Test
    public void Radio(){
        Locator userbttn=page.getByRole(AriaRole.RADIO, new Page.GetByRoleOptions().setName("User"));
        userbttn.click();
        page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Okay")).click();
       Assert.assertTrue( userbttn.isChecked());

        Locator check =page.getByRole(AriaRole.CHECKBOX,new Page.GetByRoleOptions().setName("I agree to the terms and conditions"));
       check.check();
       Assert.assertTrue(check.isChecked());

       page.getByRole(AriaRole.COMBOBOX).selectOption("Consultant");
       page.waitForTimeout(4000);


    }
}

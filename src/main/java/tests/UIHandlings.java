package tests;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


public class UIHandlings {
    Page page;
    Playwright playwright;
    Browser browser;



    @BeforeMethod
    public void setup()
    {
        playwright =Playwright.create();
        browser =playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page =browser.newPage();
        page.setDefaultTimeout(8000);
        page.navigate("https://rahulshettyacademy.com/AutomationPractice/");
        PlaywrightAssertions.setDefaultAssertionTimeout(7000);
    }

    @Test
    public void popupvalidations(){
       assertThat(page.getByPlaceholder("Hide/Show Example")).isVisible();
       page.locator("#hide-textbox").click();
       assertThat(page.getByPlaceholder("Hide/Show Example")).isHidden();

       page.onDialog(dialog -> dialog.accept());
       page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Alert")).click();

       page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Mouse Hover")).hover();
       page.waitForTimeout(4000);
       page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Top")).click();


       FrameLocator framepage = page.frameLocator("#courses-iframe");
       framepage.getByRole(AriaRole.LINK,new FrameLocator.GetByRoleOptions().setName("Learning Paths")).click();






    }







}

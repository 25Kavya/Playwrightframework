package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Loginpage {
Page page;
String baseurl;
private static final String email_placeholder="you@email.com";
private static final String password="Password";

public Loginpage (Page page, String baseurl ){
    this.page=page;
    this.baseurl=baseurl;
}

    public Dashboardpage loginpageapplication(){
        page.navigate(baseurl);
        page.navigate("qa_url");
        System.out.println(page.title());
        assertThat(page).hasTitle("EventHub — Discover & Book Events");
        page.getByPlaceholder(email_placeholder).fill("rareraw18@gmail.com", new Locator.FillOptions().setTimeout(9000));
        page.getByLabel(password).fill("Kavya@321");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign In")).click();
        Dashboardpage dash= new Dashboardpage(page);
        return dash;
    }
}

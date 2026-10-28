package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Dashboardpage {
    Page page;

    public Dashboardpage(Page page)
    {
        this.page=page;
    }

    public void waitForborwserevents()
    {
        assertThat(page.getByRole(AriaRole.LINK,
                new Page.GetByRoleOptions().setName("Browse Events").setExact(true))).isVisible();
    }
}

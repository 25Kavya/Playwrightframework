package pages;

import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Bookingformpage {
    Page page;
    private static final String Full_Name="Full Name";
    private static final String Customer_mail="#customer-email";
    private static final String Phone="#phone";

    public Bookingformpage(Page page){
        this.page=page;
    }


    public void fillandconfirm(String fullname, String mailid, String phoneno){

        page.getByLabel(Full_Name).fill(fullname);
        page.locator(Customer_mail).fill(mailid);
        page.locator(Phone).fill(phoneno);
        page.locator("#confirm-booking").click();
        assertThat(page.getByText("Your tickets are reserved.")).isVisible();
    }
}

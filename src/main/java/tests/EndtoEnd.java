package tests;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


public class EndtoEnd {
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
    page.navigate("https://eventhub.rahulshettyacademy.com/login");
    PlaywrightAssertions.setDefaultAssertionTimeout(7000);
}


    @Test( description = "create an event- book that event and check or verify whether its booked")
    public void demotest()

    {
        System.out.println(page.title());
         assertThat(page).hasTitle("EventHub — Discover & Book Events");
         page.getByPlaceholder("you@email.com").fill("rareraw18@gmail.com", new Locator.FillOptions().setTimeout(9000));
         page.getByLabel("Password").fill("Kavya@321");
         page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign In")).click();
         assertThat(page.getByRole(AriaRole.LINK,
                 new Page.GetByRoleOptions().setName("Browse Events").setExact(true))).isVisible();

         String eventTitle = "AI workshop " + System.currentTimeMillis();
         page.navigate("https://eventhub.rahulshettyacademy.com/admin/events");
         page.locator("#event-title-input").fill(eventTitle);
         page.getByPlaceholder("Describe the event…").fill("Everything about AI");
         page.getByLabel("Category").selectOption("Workshop");
         page.getByLabel("City").fill("Bengaluru");
         page.getByLabel("Venue").fill("HSR layout");
         page.getByLabel("Event Date & Time").fill("2026-11-25T14:30");
         page.getByLabel("Total Seats").fill("250");
         page.getByLabel("Price ($)").fill("500");
         page.locator("#add-event-btn").click();
         assertThat(page.getByText("Event created!")).isVisible();

         page.getByTestId("nav-events").click();
         Locator eventcards=page.getByTestId("event-card");
         System.out.println(eventcards.count());
         Locator targetcard=eventcards.filter(new Locator.FilterOptions().setHasText("AI workshop"));
         assertThat(targetcard).isVisible(new LocatorAssertions.IsVisibleOptions().setTimeout(10000));
         String seatsText =targetcard.getByText("seats").innerText();
         System.out.println(seatsText);
         targetcard.getByTestId("book-now-btn").click();

         page.getByLabel("Full Name").fill("kavs");
         page.locator("#customer-email").fill("hello@gmail.com");
         page.locator("#phone").fill("9191917878");
         page.locator("#confirm-booking").click();

         assertThat(page.getByText("Your tickets are reserved.")).isVisible();
         String Bookingref=page.locator(".booking-ref").innerText();
         page.getByLabel("View My Bookings").click();





    }

    @AfterMethod
    public void tearDown()
    {
        browser.close();
        playwright.close();
    }

}
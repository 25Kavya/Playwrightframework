package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class EventBookingpage {
    Page page;

    public EventBookingpage(Page page) {
        this.page = page;
    }

    public void Bookingscreen() {
        page.getByTestId("nav-events").click();
    }

    public Locator waittimeforEventsLoad() {
        Locator eventcards = page.getByTestId("event-card");
        assertThat(eventcards.first()).isVisible();
        return eventcards;
    }

    public Locator Findeventcard(String eventtitle) {

        Locator eventcards = waittimeforEventsLoad(String titlecard);
        Locator targetcard = eventcards.filter(new Locator.FilterOptions().setHasText(titlecard));
        assertThat(targetcard).isVisible();
        return targetcard;

    }

    public int Getseatcount(String titlecard) {

        Locator targetcard = Findeventcard(titlecard);
        String seatsText = targetcard.getByText("seats").innerText();
        System.out.println(seatsText);
        return Integer.parseInt(seatsText.split(" ")[0]);


    }

    public Bookingformpage proceedTobooking(String titlecard){
        Locator targetcard = Findeventcard(titlecard);
        targetcard.getByTestId("book-now-btn").click();
        return new Bookingformpage(page);
    }
}
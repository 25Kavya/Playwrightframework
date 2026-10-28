package pages;

import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class AdminEventspage {
Page page;
private static final String Event_Title_Input= "event-title-input";
private static final String Describe_Event="Describe the event…";
private static final String City_Label="City";
private static final String Venue_Label="Venue";
private static final String Date_Time_Label="Event Date & Time";
private static final String Seats_Total= "Total Seats";
private static final String Add_Event="#add-event-btn";
private static final String Successful_Event="Event created!";
private static final String Category="Category";
private static final String Price= "Price ($)";


public AdminEventspage(Page page){
    this.page=page;
}
public void Adminpage(){
    page.navigate("https://eventhub.rahulshettyacademy.com/admin/events");
}

public void EventDetails(String title, String description, String category, String city, String venue, String datetime,
                         String seats){

    page.locator(Event_Title_Input).fill(title);
    page.getByPlaceholder(Describe_Event).fill(description);
    page.getByLabel(Category).selectOption(category);
    page.getByLabel(City_Label).fill(city);
    page.getByLabel(Venue_Label).fill(venue);
    page.getByLabel(Date_Time_Label).fill(datetime);
    page.getByLabel(Seats_Total).fill(seats);
    page.getByLabel(Price).fill(price);
    page.locator(Add_Event).click();
    assertThat(page.getByText(Successful_Event)).isVisible();
}
}

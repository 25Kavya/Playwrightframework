package tests;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


public class FrameworkBuildTest extends basetest {

String eventtitle="AI workshop";

    @Test( description = "create an event- book that event and check or verify whether its booked")
    public void demotest()

    {
        Loginpage loginpage= new Loginpage( page, baseurl);
        Dashboardpage dash=loginpage.loginpageapplication();
        dash.waitForborwserevents();
        AdminEventspage adminpage=new AdminEventspage(page)
        adminpage.Adminpage();
        adminpage.EventDetails(
                eventtitle,
                "everything about AI",
                "workshop",
                "Bengaluru",
                "HSR Layout",
                "26-1-2027",
                "500");
       EventBookingpage bookingpage= new EventBookingpage(page);
        bookingpage.Bookingscreen();
        bookingpage.Findeventcard(eventtitle);
       int beforeseatcount= bookingpage.Getseatcount(targetcard);
        Bookingformpage bookingformpage= bookingpage.proceedTobooking(targetcard);
        bookingformpage.fillandconfirm( fullname:"kavs", mailid:"hello@gmail.com", phoneno:"9191917878");
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
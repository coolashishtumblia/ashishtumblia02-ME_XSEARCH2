
package apTests;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
///
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TestCases {
    RemoteWebDriver driver;

    public TestCases() throws MalformedURLException {
        System.out.println("Constructor: TestCases");

        final DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(BrowserType.CHROME);
        driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);


        // Set browser to maximize and wait
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    public void testCase01() {
        System.out.println("Start Test case: testCase01");
        driver.get("https://www.wikipedia.org/");
        String url = driver.getCurrentUrl();
        if (url.toLowerCase().contains("wikipedia")) {
            System.out.println("testCase01: Pass");
        } else {
            System.out.println("testCase01: Fail");
        }
        System.out.println("end Test case: testCase01");
    }

    public void testCase02() {
        System.out.println("Start Test case: testCase02");
        driver.get("https://www.wikipedia.org/");
        WebElement header =
                driver.findElement(By.xpath("//*[@id='www-wikipedia-org']/main/div[1]/h1/span"));
        String headertext = header.getText().toLowerCase();
        if (headertext.equals("wikipedia")) {
            WebElement footerElement1 = driver.findElement(
                    By.xpath("//a[@href='https://meta.wikimedia.org/wiki/Terms_of_use']"));
            WebElement footerElement2 = driver.findElement(
                    By.xpath("//a[@href='https://meta.wikimedia.org/wiki/Privacy_policy']"));
            String Terms_of_Use = footerElement1.getText();
            String Privacy_Policy = footerElement2.getText();
            if (Terms_of_Use.equalsIgnoreCase("Terms of Use")
                    && Privacy_Policy.equalsIgnoreCase("Privacy Policy")) {
                System.out.println("testCase02: Pass");
            } else {
                System.out.println("testCase02: Fail");
            }
        } else {
            System.out.println("testCase02: Fail");
        }
        System.out.println("end Test case: testCase02");

    }

    public void testCase03() {
        System.out.println("Start Test case: testCase03");
        driver.get("https://www.wikipedia.org/");
        WebElement textBox = driver.findElement(By.id("searchInput"));
        textBox.sendKeys("apple");
        WebElement selectAppleInc =
                driver.findElement(By.xpath("//*[@id='typeahead-suggestions']/div/a[2]/div[1]/h3"));
        selectAppleInc.click();
        List<WebElement> founders = driver
                .findElements(By.xpath("//*[@id='mw-content-text']/div[1]/table[1]/tbody/tr[9]"));

        for (WebElement founder : founders) {
            String founderName = founder.getText();
            if (founderName.contains("Steve Jobs")) {
                System.out.println("testCase03: Pass");
            } else {
                System.out.println("testCase03: Fail");
            }
        }
        System.out.println("end Test case: testCase03");
    }

    public void testCase04() {
        System.out.println("Start Test case: testCase04");
        driver.get("https://www.wikipedia.org/");
        WebElement textBox = driver.findElement(By.id("searchInput"));
        textBox.sendKeys("microsoft");
        WebElement selectMicrosoft=driver.findElement(By.xpath("//*[@id='typeahead-suggestions']/div/a[1]/div[1]/h3"));
        selectMicrosoft.click();
        List<WebElement> founders=driver.findElements(By.xpath("//*[@id='mw-content-text']/div[1]/table[1]/tbody/tr[8]"));
        for(WebElement founder: founders){
            String founderName=founder.getText();
            if(founderName.contains("Bill Gates")){
                WebElement BillGatesLink=driver.findElement(By.xpath("//*[@id='mw-content-text']/div[1]/table[1]/tbody/tr[8]/td/div/ul/li[1]/a"));
                BillGatesLink.click();
               String url= driver.getCurrentUrl().toLowerCase();
               if(url.contains("bill_gates")){
                System.out.println("testCase04: Pass");
               }
               else{
                System.out.println("testCase04: Fail");
               }
            }
            else{
                System.out.println("testCase04: Fail");
            }
        }
        System.out.println("end Test case: testCase04");
    }

    public void testCase05() {
        System.out.println("Start Test case: testCase05");
        driver.get("https://en.wikipedia.org/");
        WebElement mainMenu=driver.findElement(By.id("vector-main-menu-dropdown-checkbox"));
        mainMenu.click();
        WebElement aboutWikipedia=driver.findElement(By.xpath("//*[@id='n-aboutsite']/a"));
        aboutWikipedia.click();
        String url=driver.getCurrentUrl().toLowerCase();
        if(url.contains("about")){
            System.out.println("testCase05: Pass");
        }
        else{
            System.out.println("testCase05: Fail");
        }
        System.out.println("end Test case: testCase05");

    }

}


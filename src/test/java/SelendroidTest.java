import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.ExcelReader;

import java.net.URL;
import java.net.MalformedURLException;
import java.sql.Time;

public class SelendroidTest {
    public AppiumDriver<MobileElement> driver;
    public WebDriverWait wait;

    @BeforeClass
    public void setup () throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Nexus_5_API_28");
        caps.setCapability(MobileCapabilityType.APP, "D:/Softs/selendroid-test-app-0.17.0.apk");
        caps.setCapability(MobileCapabilityType.UDID, "emulator-5554");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9");
        caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "60");

        driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
        wait = new WebDriverWait(driver, 30);
    }

    @Test(priority = 1)
    public void displayUpdatePopup() {
        String title = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("android:id/alertTitle"))).getText();
        Assert.assertEquals(title, "selendroid-test-app");

        String message = driver.findElementById("android:id/message").getText();
        Assert.assertEquals(message, "This app was built for an older version of Android and may not work properly. Try checking for updates, or contact the developer.");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("android:id/button1"))).click();
    }

    @Test(priority = 2)
    public void displayText() {
        driver.findElementById("io.selendroid.testapp:id/visibleButtonTest").click();
        String text = driver.findElementById("io.selendroid.testapp:id/visibleTextView").getText();
        Assert.assertEquals(text, "Text is sometimes displayed");
    }

    @Test(priority = 3)
    public void displayAndFocus() {
        String isInitiallyFocused = driver.findElementById("io.selendroid.testapp:id/my_text_field").getAttribute("focused");

        driver.findElementById("io.selendroid.testapp:id/topLevelElementTest").click();

        String text = driver.findElementById("io.selendroid.testapp:id/focusedText").getText();
        Assert.assertEquals(text, "Should only be found once");

        String isFocused = driver.findElementById("io.selendroid.testapp:id/my_text_field").getAttribute("focused");
        Assert.assertEquals(Boolean.parseBoolean(isFocused), !Boolean.parseBoolean(isInitiallyFocused));
    }

    @Test(priority = 4)
    public void displayToast() {
        driver.findElementByAccessibilityId("showToastButtonCD").click();

        String toastText = driver.findElement(By.xpath("//android.widget.Toast")).getAttribute("name");
        Assert.assertEquals(toastText, "Hello selendroid toast!");
    }

    @Test(priority = 5)
    public void registerUser() throws Exception{
        XSSFRow testData = ExcelReader.getRowData(1);

        String username = testData.getCell(0).toString();
        String email = testData.getCell(1).toString();
        String password = testData.getCell(2).toString();
        String name = testData.getCell(3).toString();
        String language = testData.getCell(4).toString();
        String tnc = testData.getCell(5).toString().toLowerCase();

        int lanIndex = getLanguageIndex(language);

        driver.findElementByAccessibilityId("startUserRegistrationCD").click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("io.selendroid.testapp:id/inputUsername"))).sendKeys(username);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("io.selendroid.testapp:id/inputEmail"))).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("io.selendroid.testapp:id/inputPassword"))).sendKeys(password);

        new TouchAction(driver).press(PointOption.point(550, 640)).waitAction().moveTo(PointOption.point(550, 60)).release().perform();

        WebElement inputName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("io.selendroid.testapp:id/inputName")));
        inputName.clear();
        inputName.sendKeys(name);

        wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.id("io.selendroid.testapp:id/input_preferedProgrammingLanguage"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.ListView/android.widget.CheckedTextView[" + (lanIndex+1) + "]"))).click();


        new TouchAction(driver).press(PointOption.point(550, 640)).waitAction().moveTo(PointOption.point(550, 60)).release().perform();

        MobileElement checkbox = driver.findElementById("io.selendroid.testapp:id/input_adds");

       if(!checkbox.getAttribute("checked").equals(tnc)){
           driver.findElementById("io.selendroid.testapp:id/input_adds").click();
       }

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("io.selendroid.testapp:id/btnRegisterUser"))).click();

        String nameLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("io.selendroid.testapp:id/label_name_data"))).getText();
        Assert.assertEquals(nameLabel, name);

        String usernameLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("io.selendroid.testapp:id/label_username_data"))).getText();
        Assert.assertEquals(usernameLabel, username);

        String pwdLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("io.selendroid.testapp:id/label_password_data"))).getText();
        Assert.assertEquals(pwdLabel, password);

        String emailLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("io.selendroid.testapp:id/label_email_data"))).getText();
        Assert.assertEquals(emailLabel, email);

        String languageLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("io.selendroid.testapp:id/label_preferedProgrammingLanguage_data"))).getText();
        Assert.assertEquals(languageLabel, language);

        String acceptLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("io.selendroid.testapp:id/label_acceptAdds_data"))).getText();
        Assert.assertEquals(acceptLabel, tnc);

        driver.findElementById("io.selendroid.testapp:id/buttonRegisterUser").click();
    }

    private int getLanguageIndex(String language){
        int lanIndex;

        switch (language) {
            case "PHP":
                lanIndex = 1;
                break;

            case "Scala":
                lanIndex = 2;
                break;

            case "Python":
                lanIndex = 3;
                break;

            case "Javascript":
                lanIndex = 4;
                break;

            case "Java":
                lanIndex = 5;
                break;

            case "C++":
                lanIndex = 6;
                break;

            case "C#":
                lanIndex = 7;
                break;

            case "Ruby":
            default:
                lanIndex = 0;
                break;
        }

        return lanIndex;
    }


    @AfterClass
    public void closeDriver(){
        driver.quit();
    }

}


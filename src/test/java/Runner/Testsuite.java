package Runner;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Testsuite {
    WebDriver Object;

    public void login(String username, String password) {
        WebElement txtUsername = Object.findElement(By.xpath("//input[@id='user-name']"));
        WebElement txtPassword = Object.findElement(By.xpath("//input[@id='password']"));
        WebElement btnLogin = Object.findElement(By.xpath("//input[@id='login-button']"));
        txtUsername.sendKeys(username);
        txtPassword.sendKeys(password);
        btnLogin.click();
    }

    @Before
    public void Setup() {
        String s;
        s = System.setProperty("webdriver.chrome.driver", "C:\\Webdriver\\chromedriver.exe");
        Object = new ChromeDriver();
        Object.get("https://www.saucedemo.com/");
        Object.manage().window().maximize();
    }

    @Test
    public void Login_Thanh_Cong() {
        login("standard_user", "secret_sauce");
        Object.navigate().to("https://www.saucedemo.com/inventory.html");
    }

    @Test
    public void Login_voi_username_sai() {
        login("wrong_user", "secret_sauce");
        WebElement errorLabel = Object.findElement(By.xpath("//div[@class='login-box']//h3"));
        Assert.assertEquals("Epic sadface: User name khong hop le"
                , errorLabel.getText());
    }

    @Test
    public void Login_voi_password_sai() {
        login("standard_user", "wrong_sauce");
        WebElement errorLabel = Object.findElement(By.xpath("//div[@class='error-message-container error']//h3"));
        Assert.assertEquals("Epic sadface: Username and password do not match any user in this service"
                , errorLabel.getText());
         }
        @Test

        public void Login_voi_usename_trong() {
            login("",
                    "secret_sauce");
            WebElement erroLaberl = Object.findElement(By.xpath("//div[@class='error-message-container error']//h3"));
            Assert.assertEquals("Epic sadface: Username is required"
                    , erroLaberl.getText());
        }
        @After

        public void tearDown() {
            Object.close();
        }
    }



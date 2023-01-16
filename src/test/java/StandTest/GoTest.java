package StandTest;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class GoTest {

    static WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;

    JavascriptExecutor js = (JavascriptExecutor) driver;




    @BeforeAll
   static void registerDriver() {

        WebDriverManager.chromedriver().setup();

    }

    @BeforeEach
    void setupBrowser() {

       driver = new ChromeDriver();
       webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
       actions = new Actions(driver); //расширяет возможности по реализации действий

    }

    private void presenceOfElementLocated() {
    }


    @Test
    @DisplayName("Тест-кейс №1: Авторизация на сайте")
    void test_1() throws InterruptedException {

        driver.get("https://test-stand.gb.ru/login");

        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("1234");
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("81dc9bdb52");
        driver.findElement(By.xpath("//button[@form='login']")).click();
        Thread.sleep(5000);
        String s = driver.findElement(By.xpath("//*[@id=\"app\"]/main/nav/ul/li[3]/a")).getText();
        assert (s.equals("Hello,1234"));
    }
    @Test
    @DisplayName("Тест-кейс №2: Проверка регистрации с невалидными данными")
    void test_2() throws InterruptedException {

        driver.get("https://test-stand.gb.ru/login");

        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("1234");
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("81dc9bdb51");
        driver.findElement(By.xpath("//button[@form='login']")).click();
        Thread.sleep(5000);

        String s = driver.findElement(By.xpath("//h2[@class='svelte-uwkxn9']")).getText();

        assertTrue (s.equals("401"));
    }


    @Test
    @DisplayName("Тест-кейс №3: Авторизация на сайте и проверка наличия постов")
    void test_3() throws InterruptedException {

        driver.get("https://test-stand.gb.ru/login");

        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("1234");
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("81dc9bdb52");
        driver.findElement(By.xpath("//button[@form='login']")).click();
        Thread.sleep(5000);
        actions.moveToElement(driver.findElement(By.xpath("//h2[.='Rise with vegetables']")))
                .click()
                .pause(Duration.ofSeconds(3))
                .perform();

        String s = driver.findElement(By.xpath("//h1[.='Rise with vegetables']")).getText();
        assert (s.equals("Rise with vegetables"));
    }

    @Test
    @DisplayName("Тест-кейс №4: Авторизация на сайте и проверка наличия описания под постом")
    void test_4() throws InterruptedException {

        driver.get("https://test-stand.gb.ru/login");

        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("1234");
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("81dc9bdb52");
        driver.findElement(By.xpath("//button[@form='login']")).click();
        Thread.sleep(5000);
        actions.moveToElement(driver.findElement(By.xpath("//h2[.='Rise with vegetables']")))
                .click()
                .pause(Duration.ofSeconds(3))
                .perform();

        driver.findElement(By.xpath("//div[text()='Самое простое блюдо риси овощи']"));
        Assertions.assertTrue(driver.getPageSource().contains("Самое простое блюдо риси овощи"));

    }
    @Test
    @DisplayName("Тест-кейс №5: Авторизация на сайте и проверка перехода между страницами")
    void test_5() throws InterruptedException {

        driver.get("https://test-stand.gb.ru/login");

        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("1234");
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("81dc9bdb52");
        driver.findElement(By.xpath("//button[@form='login']")).click();
        Thread.sleep(5000);
        actions.moveToElement(driver.findElement(By.xpath("//*[@class='svelte-d01pfs']")))
                .click()
                .pause(Duration.ofSeconds(3))
                .perform();

        driver.findElement(By.xpath("//h2[text()='Potatoes Free']"));
        Assertions.assertTrue(driver.getPageSource().contains("Potatoes Free"));

    }


        @AfterAll
        static void tearDown() {

            driver.quit();

    }
}
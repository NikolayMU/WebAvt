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
    @DisplayName("Тест-кейс №2: Поверка отображения коректного номера телефона")
    void test_22() throws InterruptedException {

        driver.get("https://confaelshop.ru/");

        String s = driver.findElement(By.xpath("//a[@class='phone']")).getText();
        assert (s.equals("8 (495) 241-91-04"));     // проверка значения

    }
    @Test
    @DisplayName("Тест-кейс №3: Добавление товара в корзину")
    void test_3() throws InterruptedException {

        driver.get("https://confaelshop.ru/");


        actions.moveToElement(driver.findElement(By.xpath("//div[@class='menu__catalog js-open-catalog']//span[.='Каталог']")))
                .click()
                .pause(Duration.ofSeconds(3))
                .perform();

        actions.moveToElement(driver.findElement(By.xpath("//div[@class='submenu__link']//a[.='Шоколад']")))
                .click()
                .pause(Duration.ofSeconds(3))
                .perform();

        driver.findElement(By.xpath("//li[@id='bx_1847241719_492']")).click();
        Thread.sleep(5000);

        driver.findElement(By.xpath("//a[.='Шоколадный ЗАЯЦ 110 г в ассортименте']")).click();
        Thread.sleep(5000);

        driver.findElement(By.xpath("//a[@id='cf-btn-cart']")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//a[@class='btn btn btn--cart']")).click();
        String s = driver.findElement(By.xpath("//a[.='ЗАЯЦ молочный шоколад 110гр']")).getText();

        assertTrue (s.equals("ЗАЯЦ молочный шоколад 110гр"));

    }
    @Test
    @DisplayName("Тест-кейс №4: Проверка регистрации с невалидными данными")
    void test_4() throws InterruptedException {

        driver.get("https://confaelshop.ru/#popup-login");

        driver.findElement(By.xpath("//input[@name='USER_LOGIN']")).sendKeys("nmu1989@gmail.com");
        driver.findElement(By.xpath("//input[@name='USER_PASSWORD']")).sendKeys("f111albc");
        driver.findElement(By.xpath("//span[text()='ВОЙТИ']")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"wrapper\"]/div[2]/div/div/div[5]/div[3]/a")).click();
        String s = driver.findElement(By.xpath("/html/body/div[4]/div/div/div[1]/form/div[1]/p/font")).getText();

        assertTrue (s.equals("Неверный логин или пароль."));
    }
    @Test
    @DisplayName("Тест-кейс №5: Удаление рекламного баннера")
    void test_5() throws InterruptedException {

        driver.get("https://confaelshop.ru/");

        ((JavascriptExecutor)driver).executeScript("let element = document.evaluate(\"//div[@class='blockattach__content']\",document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null)\n" +
           "element.singleNodeValue.remove()");

        Thread.sleep(5000);

    }

    @Test
    @DisplayName("Тест-кейс №6: Скроллинг")
    void test_6() throws InterruptedException {

        driver.get("https://confaelshop.ru/");

        actions.scrollToElement(driver.findElement(By.xpath("//div[@class='popmechanic-input']")))
                .perform();
        Thread.sleep(5000);

        actions.moveToElement(driver.findElement(By.xpath("//div[@class='popmechanic-input']")))
                .click()
                .sendKeys("nmu1989@gmail.com")
                .pause(Duration.ofSeconds(3))
                .perform();

        driver.findElement(By.xpath("//button[@name='button']")).click();
        Assertions.assertTrue(driver.getPageSource().contains("Остался последний шаг — подтвердить вашу электронную почту. " +
                "Для этого нажмите кнопку в письме, которое мы вам отправили."));
    }


        @AfterAll
        static void tearDown() {

            driver.quit();

    }
}
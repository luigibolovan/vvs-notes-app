package ro.upt.vvs.notesapp.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SeleniumTest {

    @LocalServerPort
    private int localPort;

    private String serverUrl;
    private WebDriver driver;

    @BeforeAll
    public static void init() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void initServerUrl() {
        this.serverUrl = "http://localhost:" + localPort;
        this.driver = new ChromeDriver();
        this.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Order(1)
    public void whenPressingAddButtonWithEmptyNote_nothingShouldBeAdded() {
        String textNote = "";

        WebDriverWait wait = new WebDriverWait(driver, 30, 1000);
        driver.get(serverUrl);
        By searchInput = By.id("note_text");
        wait.until(presenceOfElementLocated(searchInput));
        driver.findElement(searchInput).sendKeys(textNote);

        By addButton = By.id("submit_note");
        wait.until(elementToBeClickable(addButton));
        driver.findElement(addButton).click();

        assertThrows(org.openqa.selenium.NoSuchElementException.class, () -> {
            driver.findElement(By.className("card-text"));
        });
    }

    @Test
    @Order(2)
    public void whenInsertedNewNote_newNoteShouldBeShown() {
        String textNote = "Abracadabra";
        WebDriverWait wait = new WebDriverWait(driver, 30, 1000);
        driver.get(serverUrl);
        By searchInput = By.id("note_text");
        wait.until(presenceOfElementLocated(searchInput));
        driver.findElement(searchInput).sendKeys(textNote);

        By addButton = By.id("submit_note");
        wait.until(elementToBeClickable(addButton));
        driver.findElement(addButton).click();

        assertEquals(textNote, driver.findElement(By.className("card-text")).getText());
    }

    @Test
    @Order(3)
    public void whenDeletedSingleNote_noCardShouldBePresent() {
        WebDriverWait wait = new WebDriverWait(driver, 30, 1000);
        driver.get(serverUrl);

        List<WebElement> cards = driver.findElements(By.className("card"));
        By deleteBtn = By.id("delete_btn");
        wait.until(elementToBeClickable(deleteBtn));
        assertEquals(cards.size(), 1);
        cards.get(0).findElement(deleteBtn).click();

        driver.get(serverUrl);
        By searchInput = By.id("note_text");
        wait.until(presenceOfElementLocated(searchInput));

        List<WebElement> currentCards = driver.findElements(By.className("card"));
        assertEquals(currentCards.size(), 0);
    }

    @Test
    @Order(4)
    public void whenDeletedNote_cardShouldDisappear() {
        String textNote = "Abracadabra";
        WebDriverWait wait = new WebDriverWait(driver, 30, 1000);
        driver.get(serverUrl);
        By searchInput = By.id("note_text");
        wait.until(presenceOfElementLocated(searchInput));
        driver.findElement(searchInput).sendKeys(textNote);

        By addButton = By.id("submit_note");
        wait.until(elementToBeClickable(addButton));
        driver.findElement(addButton).click();

        By deleteButton = By.id("delete_btn");
        wait.until(presenceOfElementLocated(deleteButton));

        textNote = "2nd abracadabra";
        wait.until(presenceOfElementLocated(searchInput));
        driver.findElement(searchInput).sendKeys(textNote);
        wait.until(elementToBeClickable(addButton));
        driver.findElement(addButton).click();

        List<WebElement> cardElements = driver.findElements(By.className("card"));
        assertEquals(cardElements.size(), 2);

        WebElement lastElement = cardElements.get(1);
        By lastElementDeleteButton = By.id("delete_btn");
        lastElement.findElement(lastElementDeleteButton).click();

        List<WebElement> currentCardElements = driver.findElements(By.className("card"));
        assertEquals(currentCardElements.size(), 1);
    }
}

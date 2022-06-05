package qaguru.homework;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import qaguru.homework.config.WebDriverProvider;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebTest {

    private WebDriver driver;

    @BeforeEach
    public void startDriver() {
        driver = new WebDriverProvider().get();
    }

    @Test
    public void testGitHubTitle() {
        assertEquals(driver.getTitle(), "GitHub: Where the world builds software · GitHub");
    }

    @AfterEach
    public void stopDriver() {
        driver.quit();
    }
}

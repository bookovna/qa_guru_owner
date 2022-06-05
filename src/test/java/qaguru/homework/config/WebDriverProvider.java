package qaguru.homework.config;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Objects;
import java.util.function.Supplier;

public class WebDriverProvider implements Supplier<WebDriver> {

    private WebDriverConfig config;

    public WebDriverProvider() {
        ConfigFactory.setProperty("place", System.getProperty("place", "local"));
        this.config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());
    }

    @Override
    public WebDriver get() {
        WebDriver driver = createWebDriver();
        driver.get(config.getBaseUrl());
        return driver;
    }

    private WebDriver createWebDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(config.getBrowser().name().toLowerCase());
        capabilities.setVersion(config.getBrowserVersion());

        if (config.isRemote()) {
            return new RemoteWebDriver(config.getRemoteDriverUrl(), capabilities);
        }

        switch (config.getBrowser()) {
            case CHROME: {
                return new ChromeDriver(new ChromeOptions().merge(capabilities));
            }
            case FIREFOX: {
                return new FirefoxDriver(new FirefoxOptions().merge(capabilities));
            }
        }

        throw new RuntimeException("Тип браузера не может быть null");
    }
}

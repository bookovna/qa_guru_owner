package qaguru.homework.config;

import org.aeonbits.owner.Config;

import java.net.URL;

@Config.Sources("classpath:${place}.properties")
public interface WebDriverConfig extends Config {

    @Key("baseUrl")
    @DefaultValue("https://github.com")
    String getBaseUrl();

    @Key("browser")
    @DefaultValue("CHROME")
    Browser getBrowser();

    @Key("browserVersion")
    @DefaultValue("100.0")
    String getBrowserVersion();

    @Key("isRemote")
    @DefaultValue("false")
    boolean isRemote();

    @Key("remoteDriverUrl")
    @DefaultValue("")
    URL getRemoteDriverUrl();
}

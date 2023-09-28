import com.microsoft.playwright.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.nio.file.Paths;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Execution(ExecutionMode.CONCURRENT)
public class PlaywrightDemoTest {
    Playwright playwright;
    Browser chromeBrowser;
    Browser firefoxBrowser;
    BrowserContext context;
    Page page;

    @Test
    void playwrightDemoChrome() throws InterruptedException {
        playwright = Playwright.create();
        chromeBrowser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setChannel("chrome")
                .setArgs(List.of("--start-maximized"))
                .setHeadless(false));
        context = chromeBrowser.newContext(new Browser.NewContextOptions()
                .setViewportSize(null));
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
        page = context.newPage();

        page.navigate("https://www.google.com/maps");
        page.locator("#searchboxinput").fill("Hải Châu District, Da Nang");
        page.keyboard().press("Enter");
        page.locator("button[aria-label='Restaurants']").click();

        assertThat(page.locator("#searchboxinput")).hasValue("Restaurants");
        Thread.sleep(3000);
        context.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("trace.zip")));
        context.close();
        playwright.close();
    }

    @Test
    void playwrightDemoFireFox() throws InterruptedException {
        playwright = Playwright.create();
        firefoxBrowser = playwright.firefox().launch(new BrowserType.LaunchOptions()
                .setArgs(List.of("--start-maximized"))
                .setHeadless(false));
        context = firefoxBrowser.newContext(new Browser.NewContextOptions()
                .setViewportSize(null));
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
        page = context.newPage();

        page.navigate("https://www.google.com/maps");
        page.locator("#searchboxinput").fill("Hải Châu District, Da Nang");
        page.keyboard().press("Enter");
        page.locator("button[aria-label='Restaurants']").click();

        Thread.sleep(3000);
        context.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("trace.zip")));
        context.close();
        playwright.close();
    }
}

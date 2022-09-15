package util;

import com.microsoft.playwright.BrowserContext;
import static util.Constants.STORAGE_PATH;
import static util.JunitExtension.LOG;
import static util.JunitExtension.browserContext;


public class MsHelper {
    private static final String LOGIN_URL = "https://login.microsoftonline.com/";

    public static void login() {
        if(browserContext.storageState().length() > 0){
            var USERNAME = Util.getVariable("EMAIL");
            var PASSWORD = Util.getVariable("PASSWORD");

            var page = JunitExtension.page;
            page.navigate(LOGIN_URL);
            page.locator("[type=email]").type(USERNAME);
            page.locator("[value=Next]").click();
            page.waitForTimeout(1000);
            page.locator("[type=password]").type(PASSWORD);
            page.locator("[type=submit]").click();
            page.waitForTimeout(1000);
            page.locator("[type=submit]").click(); //stay signed in? yes

            // then save authentication context to storage-state.json
            page.context().storageState(new BrowserContext.StorageStateOptions()
                    .setPath(STORAGE_PATH));
        } else {
            LOG.info("ALREADY LOGGER IN");
        }
    }
}

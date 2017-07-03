package com.company;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.Mouse;
import com.hp.lft.sdk.web.*;
import org.junit.*;
import unittesting.UnitTestClassBase;

public class LeanFtUserFlowTest extends UnitTestClassBase {

    Browser browser;
    public LeanFtUserFlowTest() {
        //Change this constructor to private if you supply your own public constructor
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        instance = new LeanFtUserFlowTest();
        globalSetup(LeanFtUserFlowTest.class);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        globalTearDown();
    }

    @Before
    public void setUp() throws Exception {
         browser = BrowserFactory.launch(BrowserType.CHROME);
        browser.navigate("http://localhost:8083");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void loginTest() throws GeneralLeanFtException {
        EditField loginField = browser.describe(EditField.class, new EditFieldDescription.Builder()
                .type("text").id("login-field").tagName("INPUT").index(0).build());

        loginField.setValue("admin");
        EditField passField = browser.describe(EditField.class, new EditFieldDescription.Builder()
                .type("password").id("password-field").tagName("INPUT").index(0).build());
        passField.setValue("admin");
        Button loginButton = browser.describe(Button.class, new ButtonDescription.Builder()
                .text("Login").id("login-button").tagName("DIV").build());
        loginButton.click();
        WebElement welcomeLabel = browser.describe(WebElement.class, new WebElementDescription.Builder()
                .id("labal-username").tagName("DIV").visible(true).build());
        Assert.assertTrue("Label is no ok",welcomeLabel.getInnerText().contains("admin"));
    }

}
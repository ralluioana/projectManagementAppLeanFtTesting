package com.company;

import static org.junit.Assert.*;

import com.hp.lft.report.CaptureLevel;
import com.hp.lft.report.ModifiableReportConfiguration;
import com.hp.lft.report.ReportConfigurationFactory;
import com.hp.lft.report.Reporter;
import com.hp.lft.sdk.web.*;
import org.junit.*;
import com.hp.lft.sdk.*;
import com.hp.lft.verifications.*;

import unittesting.*;

public class LeanFtTest extends UnitTestClassBase {

    public LeanFtTest() {
        //Change this constructor to private if you supply your own public constructor
    }
Browser browser;
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        instance = new LeanFtTest();
        globalSetup(LeanFtTest.class);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        globalTearDown();
    }

    @Before
    public void setUp() throws Exception {
        browser = BrowserFactory.launch(BrowserType.CHROME);
        browser.navigate("http://localhost:8083");
        ModifiableReportConfiguration defaultReportConfiguration = ReportConfigurationFactory.createDefaultReportConfiguration();
        defaultReportConfiguration.setSnapshotsLevel(CaptureLevel.All);

        Reporter.init(defaultReportConfiguration);
    }

    @After
    public void tearDown() throws Exception {
        browser.close();
    }

    @Test
    public void loginTest() throws GeneralLeanFtException {
        Reporter reporter=new Reporter();
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
        welcomeLabel.highlight();
        Assert.assertTrue("Label is no ok", welcomeLabel.getInnerText().contains("admin"));

    }

}
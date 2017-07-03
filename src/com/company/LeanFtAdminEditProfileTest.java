package com.company;

import com.hp.lft.report.CaptureLevel;
import com.hp.lft.report.ModifiableReportConfiguration;
import com.hp.lft.report.ReportConfigurationFactory;
import com.hp.lft.report.Reporter;
import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.web.*;
import org.junit.*;
import unittesting.UnitTestClassBase;

public class LeanFtAdminEditProfileTest extends UnitTestClassBase {

    public LeanFtAdminEditProfileTest() {
        //Change this constructor to private if you supply your own public constructor
    }
Browser browser;
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        instance = new LeanFtAdminEditProfileTest();
        globalSetup(LeanFtAdminEditProfileTest.class);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        globalTearDown();
    }

    @Before
    public void setUp() throws Exception {
//        browser = BrowserFactory.attach(new BrowserDescription.Builder().url("http://localhost:8083/#!user").build());
        browser = BrowserFactory.launch(BrowserType.CHROME);
        browser.navigate("http://localhost:8083");
        ModifiableReportConfiguration defaultReportConfiguration = ReportConfigurationFactory.createDefaultReportConfiguration();
        defaultReportConfiguration.setSnapshotsLevel(CaptureLevel.All);

        Reporter.init(defaultReportConfiguration);
    }

    @After
    public void tearDown() throws Exception {
//        browser.close();
    }

    @Test
    public void editProfileTest() throws GeneralLeanFtException {
        login();
        WebElement profilMenuItem =browser.describe(WebElement.class, new WebElementDescription.Builder()
                .tagName("SPAN").innerText("PROFIL").index(0).build());
        profilMenuItem.click();

        EditField numeField = browser.describe(EditField.class, new EditFieldDescription.Builder()
                .type("text").id("nume-field").tagName("INPUT").index(0).build());
        numeField.setValue("Dragomir");
        EditField prenumeField = browser.describe(EditField.class, new EditFieldDescription.Builder()
                .type("text").id("prenume-field").tagName("INPUT").index(0).build());
        prenumeField.setValue("Catalin");
        EditField emailField = browser.describe(EditField.class, new EditFieldDescription.Builder()
                .type("text").id("email-field").tagName("INPUT").index(0).build());
        emailField.setValue("dragomir.catalin@yahoo.com");
        EditField orepeziField = browser.describe(EditField.class, new EditFieldDescription.Builder()
                .type("text").id("ore-pe-zi-field").tagName("INPUT").index(0).build());
        orepeziField.setValue("8");
        Button saveButton = browser.describe(Button.class, new ButtonDescription.Builder()
                .text("Salveaza").id("button-save").tagName("DIV").build());
        saveButton.click();

        WebElement echipeMenuItem = browser.describe(WebElement.class, new WebElementDescription.Builder()
                .tagName("SPAN").innerText("ECHIPE").index(0).build());
        echipeMenuItem.click();
        profilMenuItem.click();
        Assert.assertTrue(numeField.getValue().equals("Dragomir"));
        Assert.assertTrue(prenumeField.getValue().equals("Catalin"));
        Assert.assertTrue(emailField.getValue().equals("dragomir.catalin@yahoo.com"));
        Assert.assertTrue(orepeziField.getValue().equals("8"));
    }
    public void login() throws GeneralLeanFtException {
        EditField loginField = browser.describe(EditField.class, new EditFieldDescription.Builder()
                .type("text").id("login-field").tagName("INPUT").index(0).build());
        loginField.setValue("admin");
        EditField passField = browser.describe(EditField.class, new EditFieldDescription.Builder()
                .type("password").id("password-field").tagName("INPUT").index(0).build());
        passField.setValue("admin");
        Button loginButton = browser.describe(Button.class, new ButtonDescription.Builder()
                .text("Login").id("login-button").tagName("DIV").build());
        loginButton.click();
    }

}
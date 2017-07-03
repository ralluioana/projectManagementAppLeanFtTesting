package com.company;

import com.hp.lft.report.CaptureLevel;
import com.hp.lft.report.ModifiableReportConfiguration;
import com.hp.lft.report.ReportConfigurationFactory;
import com.hp.lft.report.Reporter;
import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.web.*;
import org.junit.*;
import unittesting.UnitTestClassBase;

import java.util.List;

public class LeanFtAdminAddResourcesTest extends UnitTestClassBase {

    public LeanFtAdminAddResourcesTest() {
        //Change this constructor to private if you supply your own public constructor
    }
Browser browser;
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        instance = new LeanFtAdminAddResourcesTest();
        globalSetup(LeanFtAdminAddResourcesTest.class);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        globalTearDown();
    }

    @Before
    public void setUp() throws Exception {
        browser = BrowserFactory.launch(BrowserType.CHROME);
//        browser = BrowserFactory.attach(new BrowserDescription.Builder().type(BrowserType.CHROME).url("http://localhost:8083/#!user").build());
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
    public void addEchipa() throws GeneralLeanFtException {
        login();
        browser.describe(WebElement.class, new WebElementDescription.Builder()
                .tagName("SPAN").innerText("EDITARE").index(0).build()).click();
        browser.describe(EditField.class, new EditFieldDescription.Builder()
                .id("nume-echipa-textfield").tagName("INPUT").index(0).build()).setValue("Echipa 4");
        browser.describe(WebElement.class, new WebElementDescription.Builder()
                .id("nume-manager-combobox").tagName("DIV").index(0).build()).click();
        browser.describe(WebElement.class, new WebElementDescription.Builder()
                .tagName("SPAN").innerText("Popovici Mihai").index(0).build()).click();
        browser.describe(Button.class, new ButtonDescription.Builder()
                .id("add-echipe-button").tagName("DIV").index(0).build()).click();

        browser.describe(WebElement.class, new WebElementDescription.Builder()
                .tagName("SPAN").innerText("ECHIPE").index(0).build()).click();
        WebElement managerLabel = browser.describe(WebElement.class, new WebElementDescription.Builder()
                .tagName("DIV").innerText("MANAGER: Popovici Mihai").index(0).build());
        managerLabel.highlight();
        Assert.assertTrue(managerLabel.exists());
    }

    @Test
    public void addFunctie() throws GeneralLeanFtException, InterruptedException {
        login();
        browser.describe(WebElement.class, new WebElementDescription.Builder()
                .tagName("SPAN").innerText("EDITARE").index(0).build()).click();
        browser.describe(EditField.class, new EditFieldDescription.Builder()
                .id("nume-functie-text-field").tagName("INPUT").index(0).build()).setValue("Product Owner");
        browser.describe(Button.class, new ButtonDescription.Builder()
                .tagName("DIV").innerText("Adaugare functie").index(0).build()).click();
        WebElement functii = browser.describe(WebElement.class, new WebElementDescription.Builder()
                .id("checkboxesgorup-functii").tagName("DIV").index(0).build());
        Thread.sleep(1000);
        Assert.assertTrue(functii.getInnerText().contains("OWNER"));
        functii.highlight();
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
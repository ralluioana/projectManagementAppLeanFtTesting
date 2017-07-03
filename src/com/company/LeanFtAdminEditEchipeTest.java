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

public class LeanFtAdminEditEchipeTest extends UnitTestClassBase {

    public LeanFtAdminEditEchipeTest() {
        //Change this constructor to private if you supply your own public constructor
    }
Browser browser;
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        instance = new LeanFtAdminEditEchipeTest();
        globalSetup(LeanFtAdminEditEchipeTest.class);
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
    public void editEchipeAdminViewTest() throws GeneralLeanFtException {
        login();
        WebElement echipeMenuItem = browser.describe(WebElement.class, new WebElementDescription.Builder()
                .tagName("SPAN").innerText("ECHIPE").index(0).build());
        echipeMenuItem.click();
        WebElement comboBoxSetManager = browser.describe(WebElement.class, new WebElementDescription.Builder()
                .id("combobox-angajati-echipa-Echipa 3").tagName("DIV").visible(true).build());
        comboBoxSetManager.click();
        browser.describe(WebElement.class, new WebElementDescription.Builder()
                .tagName("SPAN").innerText("Hercut Cristian").visible(true).build()).click();
        browser.describe(Button.class, new ButtonDescription.Builder()
                .id("set-manager-echipa-Echipa 3").tagName("DIV").innerText("Seteaza Manager").build()).click();

        browser.describe(WebElement.class, new WebElementDescription.Builder()
                .tagName("SPAN").innerText("ANGAJATI").index(0).build()).click();
        browser.describe(EditField.class, new EditFieldDescription.Builder()
                .type("text").id("filtru-nume").tagName("INPUT").index(0).build()).setValue("hercut");
        Table table = browser.describe(Table.class, new TableDescription.Builder()
                .xpath("//DIV[@id=\"tabel-echipa-adminView\"]/DIV[3]/TABLE[1]").tagName("TABLE").index(0).build());
        table.highlight();
        List<String> columnHeaders = table.getColumnHeaders();
        for(String column: columnHeaders)
        {
                TableRow tableRow = table.getRows().get(1);
            Assert.assertTrue(tableRow.getCells().get(0).getText().equals("Hercut"));
            Assert.assertTrue(tableRow.getCells().get(3).getText().equals("Echipa 3"));
            Assert.assertTrue(tableRow.getCells().get(4).getText().equals("MANAGER"));
        }

        browser.describe(WebElement.class, new WebElementDescription.Builder()
                .tagName("SPAN").innerText("ECHIPE").index(0).build()).click();
        WebElement managerLabel = browser.describe(WebElement.class, new WebElementDescription.Builder()
                .tagName("DIV").innerText("MANAGER: Hercut Cristian").index(0).build());
        managerLabel.highlight();
        Assert.assertTrue(managerLabel.exists());
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
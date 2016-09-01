package nopCommercee;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.xpath.compiler.Keywords;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yogesh on 31-08-2016.
 */
public class StepDefination {

    WebDriver driver;
    @Before
    public void openBrowser(){
        System.setProperty("webdriver.chrome.driver","C:/Users/Yogesh/IdeaProjects/Nopcommerceee/src/test/resource/chromedriver.exe");
        driver=new ChromeDriver();
        driver.get("http://demo.nopcommerce.com/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
    @After
    public void closeBrowser()
    {
        driver.quit();
    }

    public void checkTitle()
    {
        String title = driver.getTitle();
        System.out.println(title);
        Assert.assertTrue(title.equals("nopCommerce demo store"));

    }

    private  void enterRegisterDetails()
    {
        selectGender(true);

        WebElement firstName = driver.findElement(By.name("FirstName"));
        firstName.sendKeys("Barack");

        WebElement lastName = driver.findElement(By.name("LastName"));
        lastName.sendKeys("Obama");


        WebElement day = driver.findElement(By.name("DateOfBirthDay"));
        Select day_select = new Select(day);
        day_select.selectByVisibleText("1");

        WebElement month = driver.findElement(By.name("DateOfBirthMonth"));
        Select month_select = new Select(month);
        month_select.selectByVisibleText("June");

        WebElement year = driver.findElement(By.name("DateOfBirthYear"));
        Select year_select = new Select(year);
        year_select.selectByVisibleText("1980");

        WebElement email = driver.findElement(By.id("Email"));
        email.sendKeys((new Random()).nextInt()+"_test1@test.com");

        System.out.print("Wait");
        WebElement newsletter = driver.findElement(By.id("Newsletter"));
        newsletter.click();

        String password_text = "Password1";

        WebElement password = driver.findElement(By.id("Password"));
        password.sendKeys(password_text);

        WebElement ConfirmPassword = driver.findElement(By.id("ConfirmPassword"));
        ConfirmPassword.sendKeys(password_text);

        WebElement submit = driver.findElement(By.name("register-button"));
       // System.out.println("Get attribute demo-get value:" + submit.getAttribute("value"));
        submit.click();

//        checkForText("Your registration completed");
        //driver.findElement(By.cssSelector(".button-1.register-continue-button")).click();
        //driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
    }

    public void checkForText(String text)
    {
        WebElement content_ui = driver.findElement(By.tagName("body"));

        //System.out.println(content_ui.getText());

        Assert.assertTrue(content_ui.getText().contains(text));
    }

    public void selectGender(boolean male)
    {
        if (male)
        {
            WebElement gender_male = driver.findElement(By.id("gender-male"));
            gender_male.click();
        }
        else {

            WebElement gender_female = driver.findElement(By.id("gender-female"));
            gender_female.click();
        }
    }


    public void gotoRegisterScreen()
    {
        WebElement register_link = driver.findElement(By.className("ico-register"));
        register_link.click();
    }

    @Given("^user is on home page$")
    public void user_is_on_home_page() throws Throwable {
        checkTitle();


    }

    @When("^user register$")
    public void user_register() throws Throwable {
        gotoRegisterScreen();
        enterRegisterDetails();
    }

    @Then("^he should see message \"([^\"]*)\"$")
    public void he_should_see_message(String arg1)  {

        checkForText(arg1);
    }

    @Then("^he search product with keyword\"([^\"]*)\"$")
    public void he_search_product_with_keyword(String keyword) {

        WebElement search= driver.findElement(By.name("q"));
        search.sendKeys(keyword);
        WebElement search_button= driver.findElement(By.xpath("//input[@value='Search']"));
        search_button.click();
        //System.out.println("wait");

    }



    @Then("^he should see equal (\\d+) result$")
    public void he_should_see_equal_result(int Count) throws Throwable {

        List<WebElement> results = driver.findElements(By.className("item-box"));

        int result_count = results.size();
        Assert.assertEquals(Count,result_count);

    }

    @When("^user select the first result it should be added$")
    public void user_select_the_first_result_it_should_be_added() throws Throwable {

        List<WebElement> results = driver.findElements(By.className("item-box"));
        WebElement first_result = results.get(results.size()-1);
        WebElement buttons = first_result.findElement(By.className("buttons"));
        WebElement add_card = buttons.findElement(By.tagName("input"));
        add_card.click();
        Assert.assertTrue(driver.findElement(By.className("cart-qty")).getText().contains("1"));
        System.out.print("Wait");


    }




    @Then("^he select product with \"([^\"]*)\" from menu$")
    public void he_select_product_with_from_menu(String keyword)  {

       Actions actions=new Actions(driver);
        WebElement mainmenu= driver.findElement(By.linkText("Computers"));
       actions.moveToElement(mainmenu).perform();
       driver.findElement(By.linkText(keyword)).click();
        //WebElement sub=driver.findElement(By.cssSelector(".active.last>a"));
        //sub.click();

        //WebDriverWait wait = new WebDriverWait(driver, 5);



            }

    @Then("^he should see equal <count> result$")
    public void he_should_see_equal_count_result(int count) throws Throwable {
       // int resultcount=0;
        List<WebElement> result1=driver.findElements(By.className("product-item"));
        int resultcount= result1.size();
        Assert.assertEquals(count,resultcount);


    }

    @When("^user select the second result it sjould be added$")
    public void user_select_the_second_result_it_sjould_be_added() throws Throwable {


        List<WebElement>box=driver.findElements(By.className("product-item"));
//        System.out.println("size"+pom);
        int pom = box.size();

        WebElement seccondelem= box.get(pom-1);
        WebElement second_result=seccondelem.findElement(By.className("buttons"));
        WebElement addcart=second_result.findElement(By.tagName("input"));
        addcart.click();


           }

//@menu clothes and shoes



    @Then("^he select product from menu with \"([^\"]*)\"$")
    public void he_select_product_from_menu_with(String keyword) {


        Actions act = new Actions(driver);
        act.moveToElement(driver.findElement(By.linkText("Apparel"))).perform();
        WebElement submn= driver.findElement(By.linkText(keyword));
        submn.click();
       // driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
      //  WebDriverWait wait = new WebDriverWait(driver, 5);


    }



    @Then("^he should able to see equal (\\d+) result$")
    public void he_should_able_to_see_equal_result(int count) throws Throwable {
        List<WebElement> result1=driver.findElements(By.className("item-box"));
        int resultcount= result1.size();
        Assert.assertEquals(count,resultcount);

    }



    @When("^user select the second result it should  be added$")
    public void user_select_the_second_result_it_should_be_added() throws Throwable {

        List<WebElement> results4 = driver.findElements(By.className("item-box"));
        WebElement first_result = results4.get(results4.size()-2);
        WebElement buttons = first_result.findElement(By.className("buttons"));
        WebElement add_card1 = buttons.findElement(By.cssSelector(".button-2.product-box-add-to-cart-button"));
        add_card1.click();
        Assert.assertTrue(driver.findElement(By.className("cart-qty")).getText().contains("1"));
        System.out.print("Wait");

    }


}

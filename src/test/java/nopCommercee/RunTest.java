package nopCommercee;

/**
 * Created by Yogesh on 31-08-2016.
 */


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;



@RunWith(Cucumber.class)
@CucumberOptions(format="html:target/CucumberReports",
        features="C:/Users/Yogesh/IdeaProjects/Nopcommerceee/src/test/feature/",
        tags="@smoke")
public class RunTest {
}

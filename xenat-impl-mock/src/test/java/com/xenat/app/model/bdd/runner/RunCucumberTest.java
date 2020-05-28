package com.xenat.app.model.bdd.runner;

//import com.xenat.app.model.impl.MockDataUtil;

import com.xenat.app.test.Unit;
import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

/**
 * Run cucumber tests.
 */
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
		strict = false,
		features = "classpath:api/bdd/features",
		glue = {"com.xenat.app.model.bdd.steps"}
)
@Category(Unit.class)
public class RunCucumberTest {
//TODO Add cucumber tests

//	@BeforeClass
//	public static void startTomcat() {
//		MockDataUtil.resetData();
//	}
//
//	@AfterClass
//	public static void closeTomcat() {
//		MockDataUtil.resetData();
//	}
}

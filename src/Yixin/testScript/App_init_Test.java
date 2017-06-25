package Yixin.testScript;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import Yixin.util.Log;

import Yixin.util.App_BaseCase;

public class App_init_Test extends App_BaseCase {

	public static Logger logger = Logger.getLogger(App_init_Test.class);

	@Test
	public void Test_Startup() {
		Log.startTestCase("启动APP");
		Log.startTestCase("进入APP首页");
		Assert.assertTrue(driver.getPageSource().contains("请输入品牌或车型"));
		Log.endTestCase("进入APP首页");
	}
}

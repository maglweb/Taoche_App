package Yixin.util;

import java.io.File;
import java.net.URL;

import org.apache.commons.collections.map.StaticBucketMap;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.iOSFindAll;
import io.appium.java_client.remote.MobileCapabilityType;

public class App_BaseCase {
	public AppiumDriver driver;

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod() throws Exception {
		// File app = new File("E:\\OtherSoftware\\weixin_1060.apk");
		File app = new File("E:\\OtherSoftware\\taoche_release.apk");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		// 不需要按照的话去掉这个
		capabilities.setCapability(MobileCapabilityType.APP,
				app.getAbsolutePath());
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Q505T");
		capabilities
				.setCapability(MobileCapabilityType.PLATFORM_VERSION, "4.3");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,
				"Android");
		capabilities.setCapability(MobileCapabilityType.APP_PACKAGE,
				"com.taoche.yixin.app");
		capabilities.setCapability(MobileCapabilityType.APP_ACTIVITY,
				"com.taoche.yixin.app.activity.home.AppstartActivity");
		capabilities.setCapability(MobileCapabilityType.APP_WAIT_ACTIVITY,
				"com.taoche.yixin.app.activity.home.AppstartActivity");
		// 支持中文输入
		capabilities.setCapability("unicodeKeyboard", "True");
		// 重置输入法为系统默认
		capabilities.setCapability("restKeyboard", "True");
		// 安装时不对apk进行重签名，设置很有必要，否则有的apk在重签名后无法正常使用
		// capabilities.setCapability("noSign", "True");
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),
				capabilities);
		Thread.sleep(5000);
		System.out.println("启动APP");
		// driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		Thread.sleep(20000);
		/*
		 * WebDriverWait wait = new WebDriverWait(driver, 20);
		 * wait.until(ExpectedConditions
		 * .presenceOfElementLocated(By.name("登录")));
		 */
		// 获取应用占屏幕大小
		int width = driver.manage().window().getSize().getWidth();
		int height = driver.manage().window().getSize().getHeight();

		int startx = width * 3 / 4;
		int endx = width * 1 / 4;
		int y = height * 1 / 2;

		// 每次启动时，有启动应用的闪屏页，需向左滑动4次
		try {
			for (int i = 0; i < 4; i++) {
				driver.swipe(startx, y, endx, y, 2000);
				Thread.sleep(500);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod() {
		driver.quit();
	}
}

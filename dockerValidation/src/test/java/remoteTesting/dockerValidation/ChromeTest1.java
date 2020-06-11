package remoteTesting.dockerValidation;

import java.beans.DesignMode;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ChromeTest1 {

	@BeforeTest
	public void startDockerScale() throws IOException, InterruptedException {
		File fl = new File("output.txt");
		if(fl.delete()) {
			System.out.println("Output File deleted sucessfully.");
		}
		StartDocker sD = new StartDocker();
		sD.startFile();
	}
	
	@AfterTest
	public void stopDockerDeleteFile() throws IOException, InterruptedException {
		StopDocker stopDocker = new StopDocker();
		stopDocker.stopFile();
	}
	
	@Test
	public static void test1() throws MalformedURLException {
		// TODO Auto-generated method stub
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		URL url = new URL("http://192.168.99.100:4444/wd/hub");
		RemoteWebDriver driver = new RemoteWebDriver(url, cap);
		driver.get("http://google.com");
		System.out.println(driver.getTitle());	
		driver.close();
		driver.quit();
		
	}

}

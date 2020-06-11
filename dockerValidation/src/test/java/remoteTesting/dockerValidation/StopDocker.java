package remoteTesting.dockerValidation;

import static org.testng.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

import org.testng.Assert;
import org.testng.annotations.Test;

public class StopDocker {
	

	public void stopFile() throws IOException, InterruptedException {
		boolean flag = false;
		Runtime runTime = Runtime.getRuntime();
		runTime.exec("cmd /c start dockerdown.bat");
		
		String f = "output.txt";
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.SECOND, 30);
		long stopTime = cal.getTimeInMillis();
		while(System.currentTimeMillis()< stopTime) {
			if(flag) {
				break;
			}
			BufferedReader reader = new BufferedReader(new FileReader(f));
			String currentLine = reader.readLine();
			
			while(currentLine != null && !flag){
				if (currentLine.contains("stopped: selenium-hub")) {
					System.out.println("Selenium Grid is Down Now");
					flag = true;
					break;
				}
				currentLine = reader.readLine();
			}
			reader.close();
		}
		Assert.assertTrue(flag);
		Thread.sleep(3000);

	}

}

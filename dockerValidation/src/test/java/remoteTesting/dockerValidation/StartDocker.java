package remoteTesting.dockerValidation;

import static org.testng.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

import org.testng.Assert;
import org.testng.annotations.Test;

public class StartDocker {
	

	public void startFile() throws IOException, InterruptedException {
		boolean flag = false;
		Runtime runTime = Runtime.getRuntime();
		runTime.exec("cmd /c start dockerup.bat");
		
		String f = "output.txt";
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.SECOND, 45);
		long stopTime = cal.getTimeInMillis();	
		Thread.sleep(3000);
		while(System.currentTimeMillis()< stopTime) {
			if(flag) {
				break;
			}			
			BufferedReader reader = new BufferedReader(new FileReader(f));
			String currentLine = reader.readLine();			
			while(currentLine != null && !flag){
				if (currentLine.contains("Selenium Grid node is up and ready to register")) {
					System.out.println("Selenium Grid is Ready");
					flag = true;
					break;
				}
				currentLine = reader.readLine();
			}
			reader.close();
		}
		Assert.assertTrue(flag);
		runTime.exec("cmd /c start scale.bat");
		Thread.sleep(5000);
	}

}

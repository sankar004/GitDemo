package remoteTesting.dockerValidation;

import java.io.IOException;

import org.testng.annotations.Test;

public class StartDocker1 {
	
	@Test
	public void startFile() throws IOException {
		
		Runtime runTime = Runtime.getRuntime();
		runTime.exec("cmd /c start C:\\docker\\dockerup.bat");
		
	}

}

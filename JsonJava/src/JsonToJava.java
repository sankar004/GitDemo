import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonToJava {

	
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		
		ObjectMapper oM = new ObjectMapper();
		CustomerDetailsNew cD = oM.readValue(new File("E:\\Testing\\Project\\SDET\\JsonJava\\cutomerInfo0.json"), CustomerDetailsNew.class);
		System.out.println(cD.getStudentName());
	}
	
}

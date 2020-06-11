import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.commons.text.StringEscapeUtils;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DbToJsonSingleFile {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, JsonGenerationException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		ArrayList<CustomerDetails> a = new ArrayList<CustomerDetails>();
		JsonArray jA = new JsonArray();
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Business" , "root" , "root");
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select * from CustomerInfo where purchasedDate=CURDATE() and Location ='Asia';");
		while(rs.next()) {
			CustomerDetails cD = new CustomerDetails();
			cD.setCourseName(rs.getString(1));
			cD.setPurchaseDate(rs.getString(2));
			cD.setAmount(rs.getInt(3));
			cD.setLocation(rs.getString(4));
			a.add(cD);
		}
		
		for(int i=0; i<a.size();i++) {
			ObjectMapper oM = new ObjectMapper();
			oM.writeValue(new File("E:\\Testing\\Project\\SDET\\JsonJava\\cutomerInfo" + i + ".json"), a.get(i));
			Gson g = new Gson();
			String jsonString = g.toJson(a.get(i));
			jA.add(jsonString);			
		}
		
		JSONObject jO = new JSONObject();
		jO.put("data", jA);
		System.out.println(jO.toJSONString());
		String unescapeString = StringEscapeUtils.unescapeJava(jO.toJSONString());
		System.out.println(unescapeString);
		String string1 = unescapeString.replace("\"{", "{");
		String finalJsonString = string1.replace("}\"", "}");
		System.out.println(finalJsonString);
		try(FileWriter file = new FileWriter("E:\\Testing\\Project\\SDET\\JsonJava\\SingleJson.json")){
		file.write(finalJsonString);
		}
		conn.close();
	}

}

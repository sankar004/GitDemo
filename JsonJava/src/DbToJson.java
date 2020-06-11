import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DbToJson {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, JsonGenerationException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		ArrayList<CustomerDetails> a = new ArrayList<CustomerDetails>();
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
			oM.writeValue(new File("E:\\Testing\\Project\\SDET\\JsonJava\\cutomerInfor" + i + ".json"), a.get(i));
		}
		conn.close();
	}

}

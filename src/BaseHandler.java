import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@JsonTypeName("BaseHandler")
public class BaseHandler implements Handler {
	public BaseHandler() {
		System.out.println("Create FileHandler");
	}
	@JacksonXmlProperty( localName = "basename")
	private String baseName;
	@JacksonXmlProperty( localName = "user")
	private String userName;
	@JacksonXmlProperty( localName = "password")
	private String password;
	@JacksonXmlProperty( localName = "insert")
	private String insert;
	
	public void writeMessage(String message) {
		System.out.println("DataBaseHandler recive:"+message);
		Connection c = null;
	    Statement stmt = null;
		try {
 			 Class.forName("org.postgresql.Driver");
	         c = DriverManager.getConnection(baseName,userName, password);
	         c.setAutoCommit(false);
	         stmt = c.createStatement();
	         String sql = String.format(insert, message);
	         stmt.executeUpdate(sql);
	         stmt.close();
	         c.commit();
	         c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getBaseName() {
		return baseName;
	}

	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getInsert() {
		return insert;
	}

	public void setInsert(String insert) {
		this.insert = insert;
	}

}

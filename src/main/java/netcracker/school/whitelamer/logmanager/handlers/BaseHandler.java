package netcracker.school.whitelamer.logmanager.handlers;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import netcracker.school.whitelamer.logmanager.databases.DataBase;
import netcracker.school.whitelamer.logmanager.databases.PosgresBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@JsonTypeName("BaseHandler")
public class BaseHandler implements Handler {
	/*!for debug
	public BaseHandler() {

		System.out.println("Create FileHandler");
	}*/
	@JacksonXmlProperty( localName = "basename")
	private String baseName;
	@JacksonXmlProperty( localName = "user")
	private String userName;
	@JacksonXmlProperty( localName = "password")
	private String password;
	@JacksonXmlProperty( localName = "insert")
	private String insert;
	
	public void writeMessage(String message) {
		// for debugSystem.out.println("DataBaseHandler recive:"+message);
		DataBase base=new PosgresBase();
		if(base.connect(baseName,userName,password)){
			String sql = String.format(insert, message);
			base.insert(sql);
			base.close();
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

	@Override
	public String toString() {
		return "BaseHandler{" +
				"baseName='" + baseName + '\'' +
				", userName='" + userName + '\'' +
				'}';
	}
}

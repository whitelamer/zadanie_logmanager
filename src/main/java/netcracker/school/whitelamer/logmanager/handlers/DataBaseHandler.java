package netcracker.school.whitelamer.logmanager.handlers;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import netcracker.school.whitelamer.logmanager.queries.DataBaseQueryExecutor;

@JsonTypeName("DataBaseHandler")
public class DataBaseHandler implements Handler {
	@JacksonXmlProperty( localName = "basename")
	private String baseName;
	@JacksonXmlProperty( localName = "user")
	private String userName;
	@JacksonXmlProperty( localName = "password")
	private String password;
	@JacksonXmlProperty( localName = "insert")
	private String insert;
	
	public void writeMessage(String message) {
        DataBaseQueryExecutor base = new DataBaseQueryExecutor();
        String sql = String.format(insert, message);
        base.executeQuery(baseName,userName,password,sql);
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

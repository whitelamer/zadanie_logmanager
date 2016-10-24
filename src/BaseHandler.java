import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;


@JsonTypeName("BaseHandler")
public class BaseHandler implements Handler {
	public BaseHandler() {
		System.out.println("Create FileHandler");
	}
	@JacksonXmlProperty( localName = "filename")
	private String fileName;
	public void writeMessage(String message) {
		System.out.println("FileHandler recive:"+message);
		try {
			Files.write(Paths.get(fileName), message.getBytes(),
					StandardOpenOption.CREATE, StandardOpenOption.APPEND);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}

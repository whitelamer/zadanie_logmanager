package whitelamer.logmanager.netcracker.school;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;


@JsonTypeName("FileHandler")
public class FileHandler implements Handler {
    /// for debug
	//	public FileHandler() {
	//		System.out.println("Create FileHandler");
	//	}
	@JacksonXmlProperty( localName = "filename")
	private String fileName;
	public void writeMessage(String message) {
		/*!for debugSystem.out.println("FileHandler recive:"+message);*/
		try {
			File file = new File(fileName);
			if (!file.exists()) file.createNewFile();
		}catch (IOException e) {
		}
		try (PrintWriter stream = new PrintWriter(new FileOutputStream(fileName,true))){
            stream.println(message);
		} catch (IOException e) {
        }
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

    @Override
    public String toString() {
        return "FileHandler{" +
                "fileName='" + fileName + '\'' +
                '}';
    }
}

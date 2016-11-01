package whitelamer.logmanager.netcracker.school;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;


@JsonTypeName("FileHandler")
public class FileHandler implements Handler {
	//	public FileHandler() {
	//		System.out.println("Create FileHandler");
	//	}
	@JacksonXmlProperty( localName = "filename")
	private String fileName;
	public void writeMessage(String message) {
		System.out.println("FileHandler recive:"+message);
		try {
			File file = new File(fileName);
			if(!file.exists())file.createNewFile();
			
//			make log rotate
//			if(file.length()>1000){
//				file.renameTo(fileName+".1");
//			}
			
			FileOutputStream fop=new FileOutputStream(file,true);
			fop.write(message.getBytes());
			fop.write("\n".getBytes());
			fop.flush();
			fop.close();
			
			//Files.write(Paths.get(fileName), message.getBytes(),StandardOpenOption.CREATE, StandardOpenOption.APPEND);
		} catch (IOException e) {
			return;
		}
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}

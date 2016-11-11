package netcracker.school.whitelamer.logmanager.handlers;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;


@JsonTypeName("FileHandler")
public class FileHandler implements Handler {

	@JacksonXmlProperty( localName = "filename")
	private String fileName;
	public void writeMessage(String message) {
		try (PrintWriter stream = new PrintWriter(new FileOutputStream(fileName,true))){
            stream.println(message);
		} catch (IOException e) {
			System.err.println("[LogManager] writeMessage error while write file {"+fileName+"}:"+e.getLocalizedMessage());
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

package netcracker.school.whitelamer.logmanager.handlers;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use=JsonTypeInfo.Id.NAME,include = JsonTypeInfo.As.PROPERTY,property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = FileHandler.class, name = "file"),
    @JsonSubTypes.Type(value = DataBaseHandler.class, name = "base"),
})
public interface Handler {
	void writeMessage(String message);
}

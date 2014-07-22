package views.vo;

import java.io.Serializable;

import org.codehaus.jackson.JsonNode;

public interface VO extends Serializable {
	
	JsonNode toJSON();
	String stringify();

}

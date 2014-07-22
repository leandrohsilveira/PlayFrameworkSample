package views.vo.impl;

import org.codehaus.jackson.JsonNode;

import play.libs.Json;
import views.vo.VO;

public abstract class BaseVO implements VO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5130090438397053431L;
	
	@Override
	public JsonNode toJSON() {
		return Json.toJson(this);
	}
	
	@Override
	public String stringify() {
		return Json.stringify(toJSON());
	}
	
}

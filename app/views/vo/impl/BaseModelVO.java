package views.vo.impl;

import play.db.ebean.Model;
import views.vo.ModelVO;

public abstract class BaseModelVO<T extends Model> extends BaseVO implements ModelVO<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5130090438397053431L;
	
	public BaseModelVO(T model) {
		super();
		toVO(model);
	}
	
}

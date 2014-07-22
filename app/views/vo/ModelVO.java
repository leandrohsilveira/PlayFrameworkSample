package views.vo;

import play.db.ebean.Model;

public interface ModelVO<T extends Model> extends VO {

	Long getId();
	T getModel();
	void toVO(T model);
	
}

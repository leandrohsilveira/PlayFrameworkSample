package models;

import play.db.ebean.Model;

public class EntidadeBase extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3981465871793630960L;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public boolean equals(Object arg0) {
		if(arg0 instanceof EntidadeBase) {
			return id.equals(((EntidadeBase) arg0).getId());
		}
		return false;
	}

}

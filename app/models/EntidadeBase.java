package models;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

import play.db.ebean.Model;

@MappedSuperclass
public class EntidadeBase extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3981465871793630960L;
	
	@Id
	@SequenceGenerator(initialValue=0,allocationSize=1,name="seq_name")
	private Long id;
	
	@Column(name="ativo", nullable=false)
	private boolean ativo = true;

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

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
}

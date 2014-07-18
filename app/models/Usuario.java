package models;

import javax.persistence.Column;
import javax.persistence.Entity;

import play.data.validation.Constraints.MaxLength;

@Entity
public class Usuario extends EntidadeBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9052132527752407992L;
	
	@Column(name="nome", length=50)
	@MaxLength(value=50)
	private String nome;
	
	@Column(name="email", length=50)
	@MaxLength(value=50)
	private String email;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
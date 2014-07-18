package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import play.data.validation.Constraints.MaxLength;
import play.db.ebean.Model;

@Entity
public class Usuario extends EntidadeBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9052132527752407992L;
	
	@Column(name="nome", length=50)
	@MaxLength(value=50)
	private String nome;
	
	@Column(name="login", length=16)
	@MaxLength(value=16)
	private String login;
	
	@Column(name="senha", length=16)
	@MaxLength(value=16)
	private String senha;
	
	@Column(name="email", length=50)
	@MaxLength(value=50)
	private String email;
	
	@Transient
	private static Finder<Long, Usuario> finder; 
	
	public static List<Usuario> recuperarAtivos() {
		return finder().where().ieq("ativo", "true").findList();
	}
	
	public static Finder<Long, Usuario> finder() {
		return finder = finder != null ? finder : new Finder<Long, Usuario>(Long.class, Usuario.class);
	}

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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
}
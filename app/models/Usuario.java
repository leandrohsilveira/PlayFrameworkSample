package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

import enums.PapelEnum;
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
	
	@Column(name="papel", nullable=false)
	@Enumerated(value=EnumType.STRING)
	private PapelEnum papel;
	
	@Transient
	private static Finder<Long, Usuario> finder; 
	
	public static List<Usuario> recuperarAtivos() {
		return finder().where().ieq("ativo", "true").findList();
	}
	
	public static List<Usuario> recuperarAtivos(Integer primeiroResultado, Integer resultadosPorPagina) {
		return finder().where().ieq("ativo", "true").setFirstRow(primeiroResultado).setMaxRows(resultadosPorPagina).findList();
	}
	
	public static Integer contarUsuariosAtivos() {
		return finder().where().ieq("ativo", "true").findRowCount();
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

	public PapelEnum getPapel() {
		return papel;
	}

	public void setPapel(PapelEnum papel) {
		this.papel = papel;
	}
	
}
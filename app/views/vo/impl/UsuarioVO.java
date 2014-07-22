package views.vo.impl;

import models.Usuario;
import views.vo.ModelVO;
import enums.PapelEnum;

public class UsuarioVO extends BaseModelVO<Usuario> implements ModelVO<Usuario> {

	public UsuarioVO(Usuario model) {
		super(model);
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 9201344837230707801L;
	
	private Long id;
	private String nome;
	private String login;
	private String email;
	private PapelEnum papel;
	
	@Override
	public Long getId() {
		return id;
	}
	
	@Override
	public Usuario getModel() {
		Usuario usuario = new Usuario();
		usuario.setId(id);
		usuario.setNome(nome);
		usuario.setLogin(login);
		usuario.setEmail(email);
		usuario.setPapel(papel);
		return usuario;
	}
	
	@Override
	public void toVO(Usuario model) {
		id = model.getId();
		nome = model.getNome();
		login = model.getLogin();
		email = model.getEmail();
		papel = model.getPapel();
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public PapelEnum getPapel() {
		return papel;
	}
	public void setPapel(PapelEnum papel) {
		this.papel = papel;
	}

}

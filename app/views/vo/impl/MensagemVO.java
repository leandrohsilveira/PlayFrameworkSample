package views.vo.impl;

import views.vo.VO;

public class MensagemVO extends BaseVO implements VO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2246307093882236727L;

	public static final int TIPO_SUCESSO = 0;
	public static final int TIPO_INFO = 1;
	public static final int TIPO_ALERTA = 2;
	public static final int TIPO_ERRO = 3;
	public static final int TIPO_FATAL = 4;
	
	private int tipo = TIPO_INFO;
	private String titulo;
	private String descricao;
	private String classe;
	
	public MensagemVO(int tipo, String titulo, String descricao) {
		super();
		this.tipo = tipo;
		this.titulo = titulo;
		this.descricao = descricao;
	}
	
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getClasse() {
		return classe;
	}
	public void setClasse(String classe) {
		this.classe = classe;
	}
	
}

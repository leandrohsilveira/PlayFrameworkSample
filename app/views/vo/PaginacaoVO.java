package views.vo;

import java.io.Serializable;
import java.util.List;

import models.Usuario;

public class PaginacaoVO<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7521754305320323874L;
	
	private List<T> itens;
	private Integer paginas;
	private Integer resultadosPorPaginas;
	private Integer paginaSelecionada;
	private Integer intervaloExibicao = 5;
	
	public PaginacaoVO(List<T> itens, Integer paginas, Integer resultadosPorPagina, Integer paginaSelecionada) {
		super();
		this.itens = itens;
		this.paginas = paginas;
		this.resultadosPorPaginas = resultadosPorPagina;
		this.paginaSelecionada = paginaSelecionada;
	}

	public List<T> getItens() {
		return itens;
	}
	
	public Integer getPaginas() {
		return paginas;
	}
	
	public Integer getResultadosPorPaginas() {
		return resultadosPorPaginas;
	}
	
	public Integer getPaginaSelecionada() {
		return paginaSelecionada;
	}
	
	public boolean primeiraPagina() {
		return paginaSelecionada == 1;
	}
	
	public boolean ultimaPagina() {
		return paginaSelecionada == paginas;
	}
	
	public boolean selecionada(Integer pagina) {
		return paginaSelecionada == pagina;
	}
	
	public Integer getPaginaAnterior() {
		return paginaSelecionada - 1;
	}
	
	public Integer getProximaPagina() {
		return paginaSelecionada + 1;
	}
	
	public boolean intervalo(Integer pagina) {
		return pagina < paginaSelecionada + intervaloExibicao && pagina > paginaSelecionada - intervaloExibicao;
	}
	
	public boolean possuiRegistros() {
		return itens != null && !itens.isEmpty();
	}

	public Integer getIntervaloExibicao() {
		return intervaloExibicao;
	}

	public void setIntervaloExibicao(Integer intervaloExibicao) {
		this.intervaloExibicao = intervaloExibicao;
	}
	
}

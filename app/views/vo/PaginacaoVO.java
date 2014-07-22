package views.vo;

import java.io.Serializable;
import java.util.List;

import views.vo.impl.BaseVO;

public class PaginacaoVO<T> extends BaseVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7521754305320323874L;
	
	private List<T> itens;
	private Integer paginas;
	private Integer primeiroIndice = 0;
	private Integer resultadosPorPaginas;
	private Integer paginaSelecionada;
	private Integer intervaloExibicao = 5;
	private String paginador;
	
	public PaginacaoVO(Integer resultadosPorPagina, Integer paginaSelecionada, Integer totalRegistros) {
		super();
		this.resultadosPorPaginas = resultadosPorPagina;
		this.paginaSelecionada = paginaSelecionada;
		
		this.paginaSelecionada = this.paginaSelecionada != null && this.paginaSelecionada > 0 ? this.paginaSelecionada : 1;
		this.resultadosPorPaginas = this.resultadosPorPaginas != null && this.resultadosPorPaginas > 0 ? this.resultadosPorPaginas : 3;
		
		primeiroIndice = (this.paginaSelecionada - 1) * this.resultadosPorPaginas;
		if(totalRegistros % resultadosPorPagina == 0) {
			paginas = totalRegistros / resultadosPorPagina;
		} else {
			paginas = totalRegistros / resultadosPorPagina + 1;
		}
		this.paginaSelecionada = this.paginaSelecionada <= paginas ? this.paginaSelecionada : paginas;
	}

	public List<T> getItens() {
		return itens;
	}
	
	public void setItens(List<T> itens) {
		this.itens = itens;
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

	public Integer getPrimeiroIndice() {
		return primeiroIndice;
	}

	public void setPrimeiroIndice(Integer primeiroIndice) {
		this.primeiroIndice = primeiroIndice;
	}

	public String getPaginador() {
		return paginador;
	}

	public void setPaginador(String paginador) {
		this.paginador = paginador;
	}

	
}

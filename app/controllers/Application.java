package controllers;

import java.util.List;

import com.avaje.ebean.Ebean;

import models.Usuario;
import play.Logger;
import play.Routes;
import play.data.DynamicForm;
import play.data.Form;
import play.db.ebean.Model;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.vo.PaginacaoVO;

public class Application extends Controller {
	
	private static PaginacaoVO<Usuario> montarListaUsuariosVO(DynamicForm form) {
		String paginaStr = form.bindFromRequest().get("pagina");
		String resultadosPorPaginaStr = form.bindFromRequest().get("itens-por-pagina");
		
		paginaStr = paginaStr != null ? paginaStr : "1";
		resultadosPorPaginaStr = resultadosPorPaginaStr != null ? resultadosPorPaginaStr : "3";
		
		Integer pagina = Integer.valueOf(paginaStr);
		Integer resultadosPorPagina = Integer.valueOf(resultadosPorPaginaStr);
		
		pagina = pagina > 0 ? pagina : 1;
		
		
		resultadosPorPagina = resultadosPorPagina > 0 ? resultadosPorPagina : 1;
		
		Integer primeiroItem = (pagina - 1) * resultadosPorPagina;
		Integer contAtivos = Usuario.contarUsuariosAtivos();
		Integer numeroDePaginas;
		if(contAtivos % resultadosPorPagina == 0) {
			numeroDePaginas = contAtivos / resultadosPorPagina;
		} else {
			numeroDePaginas = contAtivos / resultadosPorPagina + 1;
		}
		pagina = pagina <= numeroDePaginas ? pagina : numeroDePaginas;
		Logger.debug(String.format("Pagina selecionada: %d / %d", pagina, numeroDePaginas));
		List<Usuario> usuarios = Usuario.recuperarAtivos(primeiroItem, resultadosPorPagina);
		return new PaginacaoVO<Usuario>(usuarios, numeroDePaginas, resultadosPorPagina, pagina);
	}
  
    public static Result index() {
    	PaginacaoVO<Usuario> vo = montarListaUsuariosVO(Form.form());
        return ok(index.render(vo));
    }
    
    public static Result salvar() {
    	Usuario usuario = Form.form(Usuario.class).bindFromRequest().get();
    	usuario.save();
    	return redirect(routes.Application.index());
    }
    
    public static Result remover() {
    	String idStr = Form.form().bindFromRequest().get("usuario-selecionado");
    	Logger.debug("Usuário selecionado: "+idStr);
    	if(idStr != null) {
    		Long id = new Long(idStr);
    		Usuario usuario = new Model.Finder<Long, Usuario>(Long.class, Usuario.class).byId(id);
    		usuario.setAtivo(false);
    		usuario.update();
    	}
    	return redirect(routes.Application.index());
    }
    
    public static Result javascriptRoutes() {
        response().setContentType("text/javascript");
        return ok(
            Routes.javascriptRouter("jsRoutes",
                controllers.routes.javascript.Application.salvar(),
                controllers.routes.javascript.Application.remover()
            )
        );
    }
    
}

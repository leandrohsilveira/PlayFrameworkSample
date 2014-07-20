package controllers;

import java.util.List;

import com.avaje.ebean.Ebean;

import models.Usuario;
import play.Logger;
import play.Routes;
import play.api.UsefulException;
import play.data.DynamicForm;
import play.data.Form;
import play.db.ebean.Model;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.html.defaultpages.error;
import views.vo.PaginacaoVO;

public class Application extends Controller {
	
	private static PaginacaoVO<Usuario> montarListaUsuariosVO(Integer pagina, Integer resultadosPorPagina) {
		pagina = pagina != null && pagina > 0 ? pagina : 1;
		resultadosPorPagina = resultadosPorPagina != null && resultadosPorPagina > 0 ? resultadosPorPagina : 3;
		
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
    	return usuarios(null);
    }
    
    public static Result usuarios(Integer pagina) {
    	return usuarios(pagina, new Usuario());
    }
    
    private static Result usuarios(Integer pagina, Usuario usuario) {
    	PaginacaoVO<Usuario> vo = montarListaUsuariosVO(pagina, null);
    	return ok(index.render(vo, usuario));
    }
    
    public static Result acao() {
    	String idStr = Form.form().bindFromRequest().get("usuario-selecionado");
    	String acao = Form.form().bindFromRequest().get("botao-acao");
    	Logger.debug("Ação: "+acao);
    	Logger.debug("Usuário selecionado: "+idStr);
    	try {
	    	if(idStr != null) {
    			if("editar".equals(acao)) {
    				return editar(Long.valueOf(idStr));
    			} else if("remover".equals(acao)) {
    				return remover(Long.valueOf(idStr));
    			}
	    	}
	    	return index();
    	} catch(NumberFormatException e) {
    		UsefulException exception = new UsefulException(String.format("Não é possível converter o valor '%s' para número!", idStr), e) {
				private static final long serialVersionUID = 1L;
			};
    		return badRequest(error.render(exception));
    	}
    }
    
    public static Result salvar() {
    	Usuario usuario = Form.form(Usuario.class).bindFromRequest().get();
    	if(usuario.getId() == null) {
    		usuario.save();
    	} else {
    		usuario.update();
    	}
    	return redirect(routes.Application.index());
    }
    
    public static Result remover(Long id) {
    	if(id != null) {
    		Usuario usuario = new Model.Finder<Long, Usuario>(Long.class, Usuario.class).byId(id);
    		usuario.setAtivo(false);
    		usuario.update();
    	}
    	return redirect(routes.Application.index());
    }
    
    public static Result editar(Long id) {
    	Usuario usuario = null;
    	if(id != null) {
			usuario = Usuario.finder().byId(id);
    	}
    	return usuarios(null, usuario);
    }
    
    public static Result javascriptRoutes() {
        response().setContentType("text/javascript");
        return ok(
            Routes.javascriptRouter("jsRoutes",
                controllers.routes.javascript.Application.salvar()
            )
        );
    }
    
}

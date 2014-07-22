package controllers;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.POJONode;

import com.avaje.ebean.Ebean;

import models.Usuario;
import play.Logger;
import play.Routes;
import play.api.UsefulException;
import play.api.templates.Html;
import play.data.DynamicForm;
import play.data.Form;
import play.db.ebean.Model;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.html.paginador;
import views.html.defaultpages.error;
import views.vo.PaginacaoVO;
import views.vo.VO;
import views.vo.impl.MensagemVO;
import views.vo.impl.UsuarioVO;

public class Application extends Controller {
	
    public static Result index() {
    	return ok(index.render());
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
    
    
    
    // AJAX
    
    public static Result listar(Integer pagina, Integer resultadosPorPagina) {
    	Integer totalRegistros = Usuario.contarUsuariosAtivos();
    	PaginacaoVO<UsuarioVO> paginacaoVO = new PaginacaoVO<UsuarioVO>(resultadosPorPagina, pagina, totalRegistros);
    	List<Usuario> usuarios = Usuario.recuperarAtivos(paginacaoVO.getPrimeiroIndice(), paginacaoVO.getResultadosPorPaginas());
    	Html pg = paginador.render(paginacaoVO.getPaginaSelecionada(), paginacaoVO.getPaginas(), paginacaoVO.getResultadosPorPaginas(), "usuarios");
    	paginacaoVO.setPaginador(pg.toString());
    	List<UsuarioVO> vos = new ArrayList<UsuarioVO>();
    	for (Usuario usuario : usuarios) {
    		vos.add(new UsuarioVO(usuario));
		}
    	paginacaoVO.setItens(vos);
    	return ok(paginacaoVO.toJSON());
    }
    
    public static Result editar(Long id) {
    	if(id != null) {
    		Usuario usuario = Usuario.finder().byId(id);
    		if(usuario != null) {
    			VO vo = new UsuarioVO(usuario);
    			return ok(vo.toJSON());
    		} else {
    			VO vo = new MensagemVO(MensagemVO.TIPO_ERRO, "Usuário não encontrado.", String.format("O usuário com ID %d não foi localizado.", id));
    			return badRequest(vo.toJSON());
    		}
    	} else {
    		VO vo = new MensagemVO(MensagemVO.TIPO_ERRO, "Não foi informado um ID pra localizar o usuário.", null);
    		return badRequest(vo.toJSON());
    	}
    }
    
    public static Result javascriptRoutes() {
        response().setContentType("text/javascript");
        return ok(
            Routes.javascriptRouter("jsRoutes",
                controllers.routes.javascript.Application.editar(),
                controllers.routes.javascript.Application.listar(),
                controllers.routes.javascript.Application.remover()
            )
        );
    }
    
}

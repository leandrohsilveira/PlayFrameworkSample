package controllers;

import java.util.List;

import com.avaje.ebean.Ebean;

import models.Usuario;
import play.Logger;
import play.data.Form;
import play.db.ebean.Model;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class Application extends Controller {
  
    public static Result index() {
    	List<Usuario> usuarios = Usuario.recuperarAtivos();
        return ok(index.render(usuarios));
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
    
    
    
}

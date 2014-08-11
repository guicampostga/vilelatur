package br.com.gfc.usuario;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import br.com.gfc.generic.BaseController;
@ManagedBean
@SessionScoped
public class UsuarioController extends BaseController<Usuario>{

	public UsuarioController() {
		super(Usuario.class);
		this.dao = new UsuarioDao();
	}


	public void limpar() {
		this.objeto = new Usuario();
	}
	
	public String lista(){
		limpar();
		return "listar";
	}
	
	public String getUsuarioLogado() { 
		String user;
		 user = (((SecurityContext) SecurityContextHolder.getContext()).getAuthentication().getName());
		 System.out.println(user);
		   return  user;

		} 
	
}

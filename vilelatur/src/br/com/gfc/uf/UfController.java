package br.com.gfc.uf;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.gfc.generic.BaseController;
import br.com.gfc.uf.Uf;

@ManagedBean
@SessionScoped
public class UfController extends BaseController<Uf>  {

	public UfController() {
		super(Uf.class);
		this.dao = new UfDao();
	}

	@Override
	public void limpar() {
		this.objeto = new Uf();
	}
	

	

}

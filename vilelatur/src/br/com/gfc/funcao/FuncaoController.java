package br.com.gfc.funcao;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.gfc.funcao.Funcao;
import br.com.gfc.generic.BaseController;

@ManagedBean
@SessionScoped
public class FuncaoController extends BaseController<Funcao>  {

	public FuncaoController() {
		super(Funcao.class);
		this.dao = new FuncaoDao();
	}

	@Override
	public void limpar() {
		this.objeto = new Funcao();
	}
	

	

}

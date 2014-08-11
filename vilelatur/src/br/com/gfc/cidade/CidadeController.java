package br.com.gfc.cidade;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.gfc.generic.BaseController;

@ManagedBean
@SessionScoped
public class CidadeController extends BaseController<Cidade>  {

	public CidadeController() {
		super(Cidade.class);
		this.dao = new CidadeDao();
	}

	@Override
	public void limpar() {
		this.objeto = new Cidade();
		}
	

	

}

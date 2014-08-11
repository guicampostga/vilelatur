package br.com.gfc.motorista;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.gfc.generic.BaseController;

@ManagedBean
@SessionScoped
public class MotoristaController extends BaseController<Motorista>  {

	public MotoristaController() {
		super(Motorista.class);
		this.dao = new MotoristaDao();
	}

	@Override
	public void limpar() {
		this.objeto = new Motorista();
	}
	

	

}

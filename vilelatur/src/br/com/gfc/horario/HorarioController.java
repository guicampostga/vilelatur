package br.com.gfc.horario;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.gfc.generic.BaseController;

@ManagedBean
@SessionScoped
public class HorarioController extends BaseController<Horario>  {

	public HorarioController() {
		super(Horario.class);
		this.dao = new HorarioDao();
	}

	@Override
	public void limpar() {
		this.objeto = new Horario();
	}
	

	

}

package br.com.gfc.veiculo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.gfc.generic.BaseController;
@ManagedBean
@SessionScoped
public class VeiculoController extends BaseController<Veiculo> {

	public VeiculoController() {
		super(Veiculo.class);
		this.dao = new VeiculoDao();
	}

	@Override
	public void limpar() {
		this.objeto = new Veiculo();
	}

}

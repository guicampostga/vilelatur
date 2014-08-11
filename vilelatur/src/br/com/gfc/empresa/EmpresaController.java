package br.com.gfc.empresa;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.gfc.empresa.Empresa;
import br.com.gfc.generic.BaseController;
import br.com.gfc.generic.LibUtils;

@ManagedBean
@SessionScoped
public class EmpresaController  extends BaseController<Empresa>{
	
	public EmpresaController() {
		super(Empresa.class);
		this.dao = new EmpresaDao();
	}

	@Override
	public void limpar() {
		this.objeto = new Empresa();
	}
	
	@Override
	public String incluir() {
		String CNPJ1 = this.objeto.getCnpj().replace(".", "");
		String CNPJ2 = CNPJ1.replace("/","");
		String CNPJ3 = CNPJ2.replace("-", "");
		System.out.println(CNPJ3);
		if (LibUtils.isValidCNPJ(CNPJ3)) {
			return super.incluir();
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("CNPJ Invalido!"));
			return "";
		}
	}

	public String alterar() {
		String CNPJ1 = this.objeto.getCnpj().replace(".", "");
		String CNPJ2 = CNPJ1.replace("/","");
		String CNPJ3 = CNPJ2.replace("-", "");
		if (LibUtils.isValidCNPJ(CNPJ3)) {
			return super.alterar();
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("CNPJ Invalido!"));
			return "";
		}
	}
	
}

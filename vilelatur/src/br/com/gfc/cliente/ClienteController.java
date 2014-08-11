package br.com.gfc.cliente;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.gfc.generic.BaseController;
import br.com.gfc.generic.LibUtils;

@ManagedBean
@SessionScoped
public class ClienteController extends BaseController<Cliente> {

	public ClienteController() {
		super(Cliente.class);
		this.dao = new ClienteDao();
	}

	@Override
	public void limpar() {
		this.objeto = new Cliente();
	}

	@Override
	public String incluir() {
		String CPF1 = this.objeto.getCpf().replace(".", "");
		String CPF2 = CPF1.replace("-", "");
		if (LibUtils.isValidCPF(CPF2)) {
			return super.incluir();
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("CPF Invalido!"));
			return "";
		}
	}

	public String alterar() {
		String CPF1 = this.objeto.getCpf().replace(".", "");
		String CPF2 = CPF1.replace("-", "");
		if (LibUtils.isValidCPF(CPF2)) {
			return super.alterar();
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("CPF Invalido!"));
			return "listar";
		}
	}
	
	public Cliente dadosCompletos(){
		super.objeto = super.listaDM.getRowData();
		return super.objeto;
	}
	
	private Cliente objetoSelecionado;

	public Cliente getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Cliente objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

	protected Integer clienteId;

	public List<Cliente> listar() {
		String parametro = LibUtils.getParameter("clienteId");
		if (parametro != null)
			this.clienteId = Integer.valueOf(parametro);
		if (this.clienteId != null) {
			dao.setCondicaoFixa(" cliente_codigo = " + this.clienteId);
		}
		this.clienteId = null;
		return super.listar();
	}
}

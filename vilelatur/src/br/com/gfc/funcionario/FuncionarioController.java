package br.com.gfc.funcionario;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.gfc.generic.BaseController;
import br.com.gfc.generic.LibUtils;

@ManagedBean
@SessionScoped
public class FuncionarioController extends BaseController<Funcionario>{

	public FuncionarioController() {
		super(Funcionario.class);
		this.dao = new FuncionarioDao();
	}

	@Override
	public void limpar() {
		this.objeto = new Funcionario();
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
			return "";
		}
	}
	
	private Funcionario objetoSelecionado;

	public Funcionario getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Funcionario objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

	protected Integer funcionarioId;

	public List<Funcionario> listar() {
		String parametro = LibUtils.getParameter("clienteId");
		if (parametro != null)
			this.funcionarioId = Integer.valueOf(parametro);
		if (this.funcionarioId != null) {
			dao.setCondicaoFixa(" cliente_codigo = " + this.funcionarioId);
		}
		this.funcionarioId = null;
		return super.listar();
	}
}


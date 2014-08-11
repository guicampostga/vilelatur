package br.com.gfc.generic;

import java.util.List;


import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.com.gfc.cidade.Cidade;


public abstract class BaseController<T> implements IController {

	protected T objeto;
	
	protected List<T> lista;

	protected DataModel<T> listaDM;
	protected IDao<T> dao;
	protected Class<T> typeClass;

	public BaseController(Class<T> typeClass) {
		this.typeClass = typeClass;
		this.limpar();
	}
	
	public abstract void limpar();

	public String incluir() {
		this.dao.inserir(this.objeto);
		this.limpar();
		return "";
	}

	public String alterar() {
		this.dao.alterar(this.objeto);
		this.limpar();
		return "listar";
	}

	public String remover() {
		this.objeto = listaDM.getRowData();
		this.dao.remover(this.objeto);
		this.limpar();
		return "listar";
	}

	public String exibirFormularioIncluir() {
		this.limpar();
		return "exibirFormularioIncluir";
	}

	public String exibirFormularioEditar() {
		this.objeto = listaDM.getRowData();
		return "exibirFormularioEditar";
	}

	public T getObjeto() {
		return this.objeto;
	}

	public void setObjeto(T objeto) {
		this.objeto = objeto;
	}

	public List<T> getLista() {
		this.listar();
		return lista;
	}

	public void setLista(List<T> lista) {
		this.lista = lista;
	}

	public DataModel<T> getListaDM() {
		this.listar();
		listaDM = new ListDataModel<T>(this.lista);
		return listaDM;
	}

	public void setListaDM(DataModel<T> listaDM) {
		this.listaDM = listaDM;
	}

	public IDao<T> getDao() {
		return dao;
	}

	public void setDao(IDao<T> dao) {
		this.dao = dao;
	}

	public List<T> listar() {
		this.lista = this.dao.listaTudo();
		return this.lista;
	}

	@Override
	public String excluir() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> pesquisar() {
		// TODO Auto-generated method stub
		return null;
	}

}


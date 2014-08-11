package br.com.gfc.generic;

import java.sql.Statement;
import java.util.List;

public class BaseDao<T> implements IDao<T> {

	protected Conexao conexao;

	protected Statement stmt;

	protected String condicaoFixa;

	public BaseDao() {
		conexao = new Conexao();

	}

	@Override
	public void inserir(T objeto) {
		conexao = new Conexao();
	}

	@Override
	public void alterar(T objeto) {
		conexao = new Conexao();
	}

	@Override
	public void remover(T objeto) {
		conexao = new Conexao();
	}

	@Override
	public List<T> listaTudo() {
		conexao = new Conexao();
		return null;
	}

	@Override
	public T listaPorId(Integer Id) {
		conexao = new Conexao();
		return null;
	}
	
	
	public String getCondicaoFixa() {
		return condicaoFixa;
	}

	@Override
	public void setCondicaoFixa(String condicaoFixa) {
		this.condicaoFixa = condicaoFixa;
	}
}

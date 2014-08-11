package br.com.gfc.generic;

import java.util.List;



public interface IDao<T> {

	public void inserir (T objeto);
	
	public void alterar (T objeto);
	
	public void remover (T objeto);
	
	public List<T> listaTudo();
	
	public T listaPorId(Integer Id);
	
	public void setCondicaoFixa(String condicaoFixa);
	
}

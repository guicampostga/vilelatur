package br.com.gfc.generic;

import java.util.List;


public interface IController {

	public String incluir();

	public String alterar();

	public String excluir();

	public String exibirFormularioIncluir();

	public List<?> pesquisar();

}

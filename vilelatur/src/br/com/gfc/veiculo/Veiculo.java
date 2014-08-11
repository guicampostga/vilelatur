package br.com.gfc.veiculo;

public class Veiculo {

	private Integer codigo;
	
	private String modeloVeiculo;
	
	private String marca;
	
	private String placa;
	
	private String ano;
	
	private Integer qtdVagas;
	
	public Integer getCodigo() {
		return codigo;
	}

	public String getModeloVeiculo() {
		return modeloVeiculo;
	}

	public String getMarca() {
		return marca;
	}

	public String getPlaca() {
		return placa;
	}

	public String getAno() {
		return ano;
	}

	public Integer getQtdVagas() {
		return qtdVagas;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public void setModeloVeiculo(String modeloVeiculo) {
		this.modeloVeiculo = modeloVeiculo;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public void setQtdVagas(Integer qtdVagas) {
		this.qtdVagas = qtdVagas;
	}

}

package br.com.gfc.motorista;

import java.util.Date;

public class Motorista {
	
	private Integer codigo;
	private Integer funcionario;
	private String funcionarioNome;
	private String cnh;
	private String cnhCodigo;
	private Date cnhValidade;
	private Integer veiculo;
	private String veiculoNome;
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public Integer getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Integer funcionario) {
		this.funcionario = funcionario;
	}
	public String getFuncionarioNome() {
		return funcionarioNome;
	}
	public void setFuncionarioNome(String funcionarioNome) {
		this.funcionarioNome = funcionarioNome;
	}
	public String getCnh() {
		return cnh;
	}
	public void setCnh(String cnh) {
		this.cnh = cnh;
	}
	public String getCnhCodigo() {
		return cnhCodigo;
	}
	public void setCnhCodigo(String cnhCodigo) {
		this.cnhCodigo = cnhCodigo;
	}
	public Date getCnhValidade() {
		return cnhValidade;
	}
	public void setCnhValidade(Date cnhValidade) {
		this.cnhValidade = cnhValidade;
	}
	public Integer getVeiculo() {
		return veiculo;
	}
	public void setVeiculo(Integer veiculo) {
		this.veiculo = veiculo;
	}
	public String getVeiculoNome() {
		return veiculoNome;
	}
	public void setVeiculoNome(String veiculoNome) {
		this.veiculoNome = veiculoNome;
	}
}

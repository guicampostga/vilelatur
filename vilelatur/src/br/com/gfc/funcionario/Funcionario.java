package br.com.gfc.funcionario;

import java.util.Date;

import br.com.gfc.viagem.ComplementoId;

public class Funcionario {

	private Integer codigo;
	private String nome;
	private String cpf;
	private String rg;
	private Date dataNasc;
	private String endereco;
	private String numeroCasa;
	private String complemento;
	private String bairro;
	private String cidade;
	private Integer uf;
	private String telefone;
	private String email;
	private Integer funcao;
	private String funcaoNome;
	private String ufNome;
	public ComplementoId complementoId = new ComplementoId();
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public Date getDataNasc() {
		return dataNasc;
	}
	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getNumeroCasa() {
		return numeroCasa;
	}
	public void setNumeroCasa(String numeroCasa) {
		this.numeroCasa = numeroCasa;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public Integer getUf() {
		return uf;
	}
	public void setUf(Integer uf) {
		this.uf = uf;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getFuncao() {
		return funcao;
	}
	public void setFuncao(Integer funcao) {
		this.funcao = funcao;
	}
	public String getFuncaoNome() {
		return funcaoNome;
	}
	public void setFuncaoNome(String funcaoNome) {
		this.funcaoNome = funcaoNome;
	}
	public String getUfNome() {
		return ufNome;
	}
	public void setUfNome(String ufNome) {
		this.ufNome = ufNome;
	}
	public ComplementoId getComplementoId() {
		return complementoId;
	}
	public void setComplementoId(ComplementoId complementoId) {
		this.complementoId = complementoId;
	}

	
}
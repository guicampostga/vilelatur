package br.com.gfc.empresa;

public class Empresa {
	
	private Integer codigo;
	private String nomeFantazia;
	private String razaoSocial;
	private String cnpj;
	private String endereco;
	private String bairro;
	private String numero;
	private String complemento;
	private String cidade;
	private Integer estado;
	private String telefone;
	private String email;
	
	public Integer getCodigo() {
		return codigo;
	}
	public String getNomeFantazia() {
		return nomeFantazia;
	}
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public String getCnpj() {
		return cnpj;
	}
	public String getEndereco() {
		return endereco;
	}
	public String getBairro() {
		return bairro;
	}
	public String getNumero() {
		return numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public String getCidade() {
		return cidade;
	}
	public Integer getEstado() {
		return estado;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public void setNomeFantazia(String nomeFantazia) {
		this.nomeFantazia = nomeFantazia;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
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
}

package br.com.gfc.horario;

public class Horario {
	
	private Integer codigo;
	private String horaSaida;
	private String horaChegada;
	private Integer cidadeOrigem;
	private Integer cidadeDestino;
	private String cidadeOrigemVlr;
	private String cidadeDestinoVlr;

	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getHoraSaida() {
		return horaSaida;
	}
	public void setHoraSaida(String horaSaida) {
		this.horaSaida = horaSaida;
	}
	public String getHoraChegada() {
		return horaChegada;
	}
	public void setHoraChegada(String horaChegada) {
		this.horaChegada = horaChegada;
	}
	public Integer getCidadeOrigem() {
		return cidadeOrigem;
	}
	public void setCidadeOrigem(Integer cidadeOrigem) {
		this.cidadeOrigem = cidadeOrigem;
	}
	public Integer getCidadeDestino() {
		return cidadeDestino;
	}
	public void setCidadeDestino(Integer cidadeDestino) {
		this.cidadeDestino = cidadeDestino;
	}
	public String getCidadeOrigemVlr() {
		return cidadeOrigemVlr;
	}
	public void setCidadeOrigemVlr(String cidadeOrigemVlr) {
		this.cidadeOrigemVlr = cidadeOrigemVlr;
	}
	public String getCidadeDestinoVlr() {
		return cidadeDestinoVlr;
	}
	public void setCidadeDestinoVlr(String cidadeDestinoVlr) {
		this.cidadeDestinoVlr = cidadeDestinoVlr;
	}
}

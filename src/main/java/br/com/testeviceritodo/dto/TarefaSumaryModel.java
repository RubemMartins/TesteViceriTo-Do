package br.com.testeviceritodo.dto;

public class TarefaSumaryModel {

	private long id;
	private String descricao;
	private String prioridade;
	private long donoid;
	private String dono;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getPrioridade() {
		return prioridade;
	}
	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}
	public String getDono() {
		return dono;
	}
	public void setDono(String dono) {
		this.dono = dono;
	}
	public long getDonoid() {
		return donoid;
	}
	public void setDonoid(long donoid) {
		this.donoid = donoid;
	}
	
	
	
}

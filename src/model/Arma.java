
package model;

public class Arma {

	private String nome = "vazio";
	private int pontos = 0;
	private String URLimagem = "";
	private char simbolo = 'L';
	private boolean situacao = true;
	
	public Arma() {
		
	}
	
	public Arma(String nome, int pontos, String URLimagem) {
		this.nome = nome;
		this.pontos = pontos;
		this.URLimagem = URLimagem;
	}

	public void setSituacao(boolean situacao, String URLimagem) {
		this.situacao = situacao;
		this.URLimagem = URLimagem;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String descricao) {
		this.nome = descricao;
	}

	public int getPontos() {
		return pontos;
	}
	
	public void setPontos(int pontos) {
		this.pontos = pontos;
	}

	public String getURLimagem() {
		return URLimagem;
	}

	public boolean getSituacao() {
		return situacao;
	}
	
	public void setSituacao(boolean situacao) {
		this.situacao = situacao;
	}
	
	public void setSimbolo(char simbolo) {
		this.simbolo = simbolo;
	}
	
	public char getSimbolo() {
		return simbolo;
	}

}

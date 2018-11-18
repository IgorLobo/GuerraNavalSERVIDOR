
package model;

public class Arma {
//****************ATRIBUTOS**************************
	private String nome = "Livre";
	private int pontos = 0;
	private String URLimagem = "/images/Livre.png";
	private char simbolo = 'L';
	private boolean situacao = false;
	
	
//*****************METODOS***************************
	public String getNome() {return nome;}
	public int getPontos() {return pontos;}
	public String getURLimagem() {return URLimagem;}
  	public boolean getSituacao() {return situacao;}
	public char getSimbolo() {return simbolo;}	
	
	public void setArma(String nome, int pontos, char simbolo) {
		this.nome = nome;
		this.pontos = pontos;
		this.simbolo = simbolo;
		this.URLimagem = "/images/" + nome + ".png";
		this.situacao = true;
	}
	
	public void destruir() {
		this.situacao = false;
		this.URLimagem = "/images/destruido" + this.nome + ".png";
	}

}

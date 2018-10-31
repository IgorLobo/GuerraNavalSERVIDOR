package model;

import java.net.Socket;

import connection.Servidor;

public class Jogador {

	private String nome = "vazio";
	private int pontos = 0;
	private Socket socketJogador = null;
	
	public Jogador( String nome) {
		this.nome = nome;		
	}

	public String getNome() {return nome;}
	public int getPontos() {return pontos;}
	
	//public int getIP() {return IP;}
	
	public void adicionarPontos(int pontos) {
		this.pontos += pontos;
	}
}

package model;

public class Jogador {

	private String nome = "vazio";
	private int pontos = 0;
	private int socket = 0;
	private int IP = 0;
	
	public Jogador( String nome, int socket, int IP) {
		this.nome = nome;
		this.socket = socket;
		this.IP = IP;
	}

	public String getNome() {return nome;}
	public int getPontos() {return pontos;}
	public int getSocket() {return socket;}
	public int getIP() {return IP;}
	
	public void adicionarPontos(int pontos) {
		this.pontos += pontos;
	}
}

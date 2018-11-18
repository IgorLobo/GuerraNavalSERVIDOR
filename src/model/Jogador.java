package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import model.Jogo;
import connection.Servidor;

public class Jogador extends Thread {

	private int id = -1;
	private String ip = "";
	private String nome = "vazio";
	private int pontos = 0;
	private Socket socketJogador = null;

	public Jogador(Socket socketJogador, int identificador, String ip, String nome) {
		this.id = identificador;
		this.ip = ip;
		this.nome = nome;
		this.pontos = 0;
		this.socketJogador = socketJogador;
	}

	public Jogador(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public int getPontos() {
		return pontos;
	}

	public int getIdentificador() {
		return id;
	}

	public String getIp() {
		return ip;
	}

	public Socket getSocketJogador() {
		return socketJogador;
	}

	public void adicionarPontos(int pontos) {
		this.pontos += pontos;
	}

}

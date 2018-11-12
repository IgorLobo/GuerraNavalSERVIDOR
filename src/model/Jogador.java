package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import model.Jogo;
import connection.Servidor;

public class Jogador extends Thread{
	
	private int identificador = -1;
	private String ip = "";
	private String nome = "vazio";
	private int pontos = 0;
	
	private Socket socketJogador = null;
	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;
	
	
	public Jogador(Socket socketJogador, int identificador, String ip, String nome) {
		this.identificador = identificador;
		this.ip = ip;
		this.nome = nome;
		this.pontos = 0;
		this.socketJogador = socketJogador;

		try {
			this.bufferedReader = new BufferedReader(new InputStreamReader(socketJogador.getInputStream()));
			this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socketJogador.getOutputStream()));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Jogador(String nome) {
		this.nome = nome;		
	}

	public String getNome() {return nome;}
	public int getPontos() {return pontos;}
	
	
	public int getIdentificador() {
		return identificador;
	}

	public String getIp() {
		return ip;
	}

	public Socket getSocketJogador() {
		return socketJogador;
	}

	public BufferedReader getBufferedReader() {
		return bufferedReader;
	}

	public BufferedWriter getBufferedWriter() {
		return bufferedWriter;
	}

	public void adicionarPontos(int pontos) {
		this.pontos += pontos;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}


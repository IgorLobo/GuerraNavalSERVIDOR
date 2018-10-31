package model;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

public class Jogo {
//******************ATRIBUTOS**************************
	private Arma[][] tabuleiro = null;
	private int tamanho = -1;	
	private ArrayList<Jogador> jogadoresArrayList = null;
	private int qtdArmasRestantes = 0;
	public static ArrayList<BufferedWriter> conexoesArrayList = new ArrayList<BufferedWriter>();
	
//****************CONSTRUTOR**************************
	public Jogo() {}
	
	public Jogo(int tamanho) throws Exception {
		if (tamanho < 3)
			throw new Exception("O tamanho do tabuleiro não pode ser menor que 3.");
		this.tamanho = tamanho;
		tabuleiro = new Arma[tamanho][tamanho];
		for (int i = 0; i < tamanho; i++) {
			for (int j = 0; j < tamanho; j++) {
				tabuleiro[i][j] = new Arma();
			}						
		}
		randomArmas("Porta-Avioes", 5, 'P', 30);
		randomArmas("Submarino", 5, 'S', 30);
		randomArmas("Cruzador", 4, 'C',  20);
		randomArmas("Destroyer", 3, 'D', 20);
		
		jogadoresArrayList = new ArrayList<Jogador>();
	}
//****************METODOS*****************************
	public int getQntArmasRestantes() {return qtdArmasRestantes;}
	
	public String getNomeJogador(int posicao) {
		return jogadoresArrayList.get(posicao).getNome();
	}
		
	public int getPontosJogador(int posicao) {
		return jogadoresArrayList.get(posicao).getPontos();
	}
	
	public int getTamanhoArrayJogadores() {
		return jogadoresArrayList.size();
	}
	
	public String getArmaURL(int linha, int coluna) {
		return tabuleiro[linha][coluna].getURLimagem();
	}
	
	public String getArmaNome(int linha,int coluna) {
		return tabuleiro[linha][coluna].getNome();
	}

	public int disparo(String cordenadas)  {
		String[] cordenadasXY = cordenadas.split(",");
		int linha = Integer.parseInt(cordenadasXY[0]);
		int coluna = Integer.parseInt(cordenadasXY[1]);
		if (linha > tamanho || coluna > tamanho)
			try {
				throw new Exception("Disparo fora do tabuleiro!");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		if (tabuleiro[linha][coluna].getSituacao()) {
			tabuleiro[linha][coluna].destruir();
			qtdArmasRestantes--;
			return tabuleiro[linha][coluna].getPontos();
		}
		return 0;
	}
	
	private void randomArmas(String nome, int pontos, char simbolo,int porcentagem) {
		int linha = 0;
		int coluna = 0;
		int qtdTotalDeArmas = (((tamanho * tamanho) * 60)/100) ;
		int qtdPorArma = ((qtdTotalDeArmas * porcentagem)/100);
		Random random = new Random();
		while (qtdPorArma > 0) {
			linha = random.nextInt(tamanho);
			coluna = random.nextInt(tamanho);
			if (tabuleiro[linha][coluna].getSimbolo() == 'L') {
				tabuleiro[linha][coluna].setArma(nome, pontos, simbolo);
				qtdPorArma--;
				qtdArmasRestantes++;
			}
		}
	}		
}

package model;

import java.util.ArrayList;
import java.util.Random;

public class Jogo {

	private Arma[][] tabuleiro = null;
	private int tamanho = -1;
	private ArrayList<Jogador> JogadoresArrayList;

	public Jogo(int tamanho) throws Exception {
		if (tamanho < 3)
			throw new Exception("O tamanho do tabuleiro não pode ser menor que 5.");
		this.tamanho = tamanho;
		tabuleiro = new Arma[tamanho][tamanho];
		for (int i = 0; i < tamanho; i++) {
			for (int j = 0; j < tamanho; j++) {
				tabuleiro[i][j] = new Arma();
			}
			randomArmas("PortaAviao", 5, 'P', true, 0.30f);
			randomArmas("Submarino", 5, 'S', true, 0.30f);
			randomArmas("Cruzador", 4, 'C', true, 0.20f);
			randomArmas("Destroyer", 3, 'D', true, 0.20f);			
		}
		
		//JogadoresArrayList = new ArrayList<Jogador>();
	}

	public int disparo(String cordenadas) throws Exception {
		String[] cordenadasXY = cordenadas.split(",");
		int linha = Integer.parseInt(cordenadasXY[0]);
		int coluna = Integer.parseInt(cordenadasXY[1]);
		if (linha > tamanho || coluna > tamanho)
			throw new Exception("Disparo fora do tabuleiro!");

		if (tabuleiro[linha][coluna].getSituacao()) {
			tabuleiro[linha][coluna].setSituacao(false, "ULR DA IMAGEM !!!");
			return tabuleiro[linha][coluna].getPontos();
		}
		return 0;
	}
	
	private void randomArmas(String descricao, int pontos, char simbolo, boolean situacao, float porcentagem) {
		int linha = 0;
		int coluna = 0;
		int qtdTotalArmas = 0;
		Random random = new Random();
		float qtdArmasTotal = ((float) (0.60 * (tamanho * tamanho)));
		float qtdPorArma = ((float) (porcentagem * qtdArmasTotal));

		float a = ((float) (qtdPorArma));
		int b = ((int) (a));
		a -= b;

		if (a >= 0.50f) {
			qtdPorArma = (int) (porcentagem * qtdArmasTotal);
			qtdArmasTotal++;
		} else {
			tabuleiro[linha][coluna].setSimbolo('L');
		}
		qtdTotalArmas += qtdPorArma;

		while (qtdPorArma >= 0) {
			linha = random.nextInt(tamanho);
			coluna = random.nextInt(tamanho);
			if (tabuleiro[linha][coluna].getSimbolo() == 'L') {
				tabuleiro[linha][coluna].setNome(descricao);
				tabuleiro[linha][coluna].setPontos(pontos);
				tabuleiro[linha][coluna].setSimbolo(simbolo);
				tabuleiro[linha][coluna].setSituacao(situacao);
				qtdPorArma--;
			}
		}
		while (qtdPorArma < ((int) (qtdArmasTotal))) {
			qtdPorArma++;
		}
	}
	
	public Arma[][] getTabuleiro() {
		return tabuleiro;
	}

}

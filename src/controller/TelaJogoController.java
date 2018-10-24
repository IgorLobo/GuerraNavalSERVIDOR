package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.Jogo;

public class TelaJogoController implements Initializable {

//-----------------------------------ATRIBUTOS-------------------------------------------
	public static int tamanho = -1;
	private String historico = "";
	private Jogo tabuleiro = null;
	private Button botoesDoTabuleiro[][];

//---------------------------COMPONENTES DA TELA FXML----------------------------------------
	
    @FXML
    private GridPane grid_tabelaDeArmas;

    @FXML
    private Label lb_armasRestantes;

    @FXML
    private TextArea txtArea_historicoJogadas;

    @FXML
    private GridPane grid_tabuleiroJogo;


//--------------------------------------BUTTONS-----------------------------------------------
	private void iniciarJogo() {
		try {
			tabuleiro = new Jogo(tamanho);
			grid_tabuleiroJogo.setVgap(10);
			grid_tabuleiroJogo.setHgap(10);
			grid_tabuleiroJogo.resize(calcularTamanhoDaGrid(),calcularTamanhoDaGrid());
			grid_tabuleiroJogo.setAlignment(Pos.TOP_LEFT);
			botoesDoTabuleiro = new Button[tamanho][tamanho];

			// For para criar o array de botoes com evento
			for (int i = 0; i < tamanho; i++) {
				for (int j = 0; j < tamanho; j++) {
					botoesDoTabuleiro[i][j] = new Button();
					botoesDoTabuleiro[i][j].setMaxSize(40, 40);
					botoesDoTabuleiro[i][j].setId(i + "," + j);
					botoesDoTabuleiro[i][j].setOnAction(new EventHandler<ActionEvent>() {
						public void handle(ActionEvent event) {
							Button botaoOnClick = (Button) event.getSource();
							tabuleiro.disparo(botaoOnClick.getId());
							String[] cordenadasXY = botaoOnClick.getId().split(",");
							int linha = Integer.parseInt(cordenadasXY[0]);
							int coluna = Integer.parseInt(cordenadasXY[1]);
							botoesDoTabuleiro[linha][coluna].setGraphic(new ImageView(new Image(tabuleiro.getTabuleiro()[linha][coluna].getURLimagem(),60, 60, false, false)));
							atualizarHistorico(botaoOnClick.getId());
							lb_armasRestantes.setText(Integer.toString(tabuleiro.getQntArmasRestantes()));
						}
					});
				}
			}

			for (int i = 0; i < tamanho; i++) {
				for (int j = 0; j < tamanho; j++) {
					grid_tabuleiroJogo.add(botoesDoTabuleiro[i][j], j, i);
					botoesDoTabuleiro[i][j].setGraphic(new ImageView(new Image(tabuleiro.getTabuleiro()[i][j].getURLimagem(),60, 60, false, false)));
				}
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
			e.getMessage();
		}
	}

//---------------------------------METODOS------------------------------------------------------

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			iniciarJogo();
			lb_armasRestantes.setText(Integer.toString(tabuleiro.getQntArmasRestantes()));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void atualizarHistorico(String id) {
		historico += id +" - " + getNomeDaArma(id)  + "\n";
		txtArea_historicoJogadas.setText(String.format("%s", historico));
	}
	
	public String getNomeDaArma(String coordenadas) {
		String[] cordenadasXY = coordenadas.split(",");
		int linha = Integer.parseInt(cordenadasXY[0]);
		int coluna = Integer.parseInt(cordenadasXY[1]);
		for (int i = 0; i < tamanho; i++) {
			for (int j = 0; j < tamanho; j++) {
				if (i == linha && j == coluna) {
					return tabuleiro.getTabuleiro()[i][j].getNome();
				}
			}
		}
		return null;
	}
	
	
	public int calcularTamanhoDaGrid() {
		int espaçamento = (10 * (this.tamanho - 1));
		int tamanhoDosBotoes = 40 * tamanho;
		return (espaçamento + tamanhoDosBotoes);
	}
}

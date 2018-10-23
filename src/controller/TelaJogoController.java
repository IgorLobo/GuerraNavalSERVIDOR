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
	private Jogo jogo = null;
	private String historico = "";

//---------------------------COMPONENTES DA TELA FXML----------------------------------------
	@FXML
	private GridPane grid_tabelaArmas;

	@FXML
	private Label lb_armasRestantes;

	@FXML
	public GridPane grid_tabuleiroJogo;

    @FXML
    private ScrollPane scrollPane;
	
    @FXML
    private SplitPane splitpane;
    
	@FXML
	private TextArea txtArea_historicoJogadas;

//--------------------------------------BUTTONS-----------------------------------------------
	private void iniciarJogo() {
		try {
			Jogo tab = new Jogo(tamanho);
			grid_tabuleiroJogo.setVgap(10);
			grid_tabuleiroJogo.setHgap(10);
			grid_tabuleiroJogo.resize(calcularTamanhoDaGrid(),calcularTamanhoDaGrid());
			grid_tabuleiroJogo.setAlignment(Pos.TOP_LEFT);
			Button botao[][] = new Button[tamanho][tamanho];

			// For para criar o array de botoes com evento
			for (int i = 0; i < tamanho; i++) {
				for (int j = 0; j < tamanho; j++) {
					botao[i][j] = new Button();
					botao[i][j].setMaxSize(40, 40);
					botao[i][j].setId(i + "-" + j);
					botao[i][j].setOnAction(new EventHandler<ActionEvent>() {
						public void handle(ActionEvent event) {
							Button teste = (Button) event.getSource();
							testar(teste.getId());
						}
					});
				}
			}

			for (int i = 0; i < tamanho; i++) {
				for (int j = 0; j < tamanho; j++) {
					grid_tabuleiroJogo.add(botao[i][j], j, i);
					
					
					
					botao[i][j].setGraphic(new ImageView(new Image(tab.getTabuleiro()[i][j].getURLimagem(),100, 100, false, false)));
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
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void testar(String t) {
		historico += t + "\n";
		txtArea_historicoJogadas.setText(String.format("%s", historico));
	}

	public int calcularTamanhoDaGrid() {
		int espaçamento = (10 * (this.tamanho - 1));
		int tamanhoDosBotoes = 40 * tamanho;
		return (espaçamento + tamanhoDosBotoes);
	}
}

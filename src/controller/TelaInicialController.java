package controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import javafx.event.EventHandler;
import connection.GerenciadorDeClientes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Jogo;
import javafx.scene.control.TextArea;

public class TelaInicialController implements Initializable {

//----------------------------------ATRIBUTOS------------------------------------------------
	static ServerSocket servidor = null;
	private Socket cliente = null;
	
	

//---------------------------COMPONENTES DA TELA FXML----------------------------------------
	@FXML
	private TextField txf_ipServidor;

	@FXML
	private Button btn_iniciarJogo;

	@FXML
	private Button btn_iniciarServidor;

	@FXML
	private TextField txf_portaServidor;

	@FXML
	private Button btn_pararServidor;

	@FXML
	private Button btn_pararJogo;

	@FXML
	public static TextField txf_tamanhoTabuleiro;

	@FXML
	private TextArea txtArea_usuariosConectados;

//-----------------------------BUTTONS-------------------------------------------------------

	@FXML
	void click_btnIniciarServidor(ActionEvent event) {
		try {
			// inicio server
			System.out.println("startando servidor");

			// porta de conexão
			servidor = new ServerSocket(9999);

			// confirmação de iniciado
			System.out.println("servidor startado");

			// esperando conexões
			while (true) {
				cliente = servidor.accept();
				receberAcessos();

			}

		} catch (IOException e) {
			try {
				if (servidor != null)
					servidor.close();

			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO",
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
	}

	@FXML
	void click_btnPararServidor(ActionEvent event) {
		try {
			servidor.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	@FXML
	public static void click_btnIniciarJogo(ActionEvent event) {
		try {
			int tamanho = Integer.parseInt(txf_tamanhoTabuleiro.getText());
			Jogo tab = new Jogo(tamanho);
			TelaJogoController.grid_tabuleiroJogo.setVgap(10);
			TelaJogoController.grid_tabuleiroJogo.setHgap(10);
			TelaJogoController.grid_tabuleiroJogo.setMinSize(520, 600);			
			Button botao[][] = new Button[tamanho][tamanho];
			
			for (int i = 0; i < tamanho; i++) {
				for (int j = 0; j < tamanho; j++) {
					botao[i][j] = new Button();
					botao[i][j].setPrefSize(40, 40);
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
					TelaJogoController.grid_tabuleiroJogo.add(botao[i][j], j, i);
					botao[i][j].setText("" + tab.getTabuleiro()[i][j].getSimbolo());
				}
			}
			
			abrirTela();

		} catch (Exception e) {
			e.getMessage();
		}
	}

	
	@FXML
	void click_btnPararJogo(ActionEvent event) {

	}

//-----------------------------METODOS-------------------------------------------------------
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	public void receberAcessos() throws IOException {
		try {
			new GerenciadorDeClientes(cliente);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Porta indisponivel ou servidor disconectado", "ERRO",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

	}
	
	public static void testar(String t) {
		TelaJogoController.txtArea_historicoJogadas.setText(String.format("%s", t));
	}
	
	public static void abrirTela() {
		// TODO Auto-generated method stub
		
	}

}

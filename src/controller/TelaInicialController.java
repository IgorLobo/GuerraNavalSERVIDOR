package controller;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import connection.Servidor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Jogador;
import model.Jogo;

public class TelaInicialController implements Initializable {

//----------------------------------ATRIBUTOS------------------------------------------------
	static ServerSocket servidor = null;
	private Socket socket = null;
	private static ServerSocket server;
	private Thread conexao = null;
	private int jogador = 0;

//---------------------------COMPONENTES DA TELA FXML----------------------------------------
	@FXML
	private TextField txf_ipServidor;

	@FXML
	private TextField txf_portaServidor;

	@FXML
	private Button btn_iniciarJogo;

	@FXML
	private Button btn_iniciarServidor;

	@FXML
	private Button btn_pararServidor;

	@FXML
	private TextField txf_tamanhoTabuleiro;

	@FXML
	private ListView<Jogador> list_conectados;

//-----------------------------BUTTONS-------------------------------------------------------

	@FXML
	void click_btnIniciarServidor(ActionEvent event) {
		try {		
			int tempo = 0;

			// inicio server
			System.out.println("startando servidor");

			// porta de conexão
			servidor = new ServerSocket(Integer.parseInt(txf_portaServidor.getText()));

			// confirmação de iniciado
			System.out.println("servidor startado");
			conexao = new Thread() {
				@Override
				public void run() {
					while (jogador != 3) {
						try {
							socket = servidor.accept();

							System.out.println("Cliente conectado");
							Thread thread = new Servidor(socket);
							thread.start();
						} catch (IOException e) {
							JOptionPane.showMessageDialog(null, e.getMessage());
						}
					}
				}
			};conexao.start();			
		} catch (IOException e) {
			try {
				if (servidor != null)
					servidor.close();

			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
	}

	@FXML
	void click_btnPararServidor(ActionEvent event) {
		try {
			servidor.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	@FXML
	void click_btnIniciarJogo(ActionEvent event) {
		try {
			TelaJogoController.tamanho = Integer.parseInt(txf_tamanhoTabuleiro.getText());
			abrirTela();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
		}

	}

	@FXML
	void click_btnPararJogo(ActionEvent event) {

	}

//-----------------------------METODOS-------------------------------------------------------
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			txf_ipServidor.setText(InetAddress.getLocalHost().getHostAddress());
			btn_pararServidor.setDisable(true);

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void abrirTela() {
		try {
			Parent root = FXMLLoader.load(this.getClass().getResource("/view/TelaJogo.fxml"));
			Scene cena = new Scene(root);
			Stage tela = new Stage();
			tela.setScene(cena);
			tela.show();
			tela.setMaximized(true);
			tela.setResizable(false);
			tela.setTitle("JOGO GUERRA NAVAL");
			tela.getIcons().add(new Image(getClass().getResourceAsStream("/images/batalhaNaval.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

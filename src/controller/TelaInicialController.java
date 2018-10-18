package controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import connection.GerenciadorDeClientes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
	private TextField txf_tamanhoTabuleiro;

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
				JOptionPane.showMessageDialog(null, "Porta indisponivel ou servidor disconectado", "ERRO",
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
	}

	@FXML
	void click_btnPararServidor(ActionEvent event) {

	}

	@FXML
	void click_btnIniciarJogo(ActionEvent event) {

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
}

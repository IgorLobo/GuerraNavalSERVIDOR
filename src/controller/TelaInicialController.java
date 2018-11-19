package controller;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import connection.Servidor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Jogador;

public class TelaInicialController implements Initializable {

//----------------------------------ATRIBUTOS------------------------------------------------
	private Servidor servidor = null;
//---------------------------COMPONENTES DA TELA FXML----------------------------------------
	@FXML
	private TextField txf_ipServidor;

	@FXML
	private TextField txf_portaServidor;

	@FXML
	private Button btn_tamanho;

	@FXML
	private Button btn_iniciarServidor;

	@FXML
	private Button btn_iniciarJogo;

	@FXML
	private TextField txf_tamanhoTabuleiro;
	
	@FXML
	private static TextArea txa_lista;


//-----------------------------BUTTONS-------------------------------------------------------
	@FXML
	void click_btnIniciarServidor(ActionEvent event) {
		try {
			System.out.println("startando servidor");
			System.out.println("servidor startado");
			servidor = new Servidor(Integer.parseInt(txf_portaServidor.getText()));
			servidor.startServer();
			btn_iniciarJogo.setDisable(false);
			btn_iniciarServidor.setDisable(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	@FXML
	void click_btnIniciarJogo(ActionEvent event) {
		try {
			abrirTela();
			servidor.stopServer();
			servidor.setStatusServidor(false);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	@FXML
	void click_btnTamanho(ActionEvent event) {
		if (Integer.parseInt(txf_tamanhoTabuleiro.getText().trim()) < 3) {
			JOptionPane.showMessageDialog(null, "O tamanho não pode ser menor que 3!");
		} else {
			try {
				TelaJogoController.tamanho = Integer.parseInt(txf_tamanhoTabuleiro.getText());
				btn_tamanho.setDisable(true);
				btn_iniciarServidor.setDisable(false);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

//-----------------------------METODOS-------------------------------------------------------
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			btn_iniciarJogo.setDisable(true);
			btn_iniciarServidor.setDisable(true);
			txf_ipServidor.setText(InetAddress.getLocalHost().getHostAddress());
			util.MaskTextfield.campoNumerico(txf_portaServidor);
			util.MaskTextfield.campoNumerico(txf_tamanhoTabuleiro);
			util.MaskTextfield.tamanhoMaximo(txf_tamanhoTabuleiro, 11);
			util.MaskTextfield.tamanhoMaximo(txf_portaServidor, 4);
			
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

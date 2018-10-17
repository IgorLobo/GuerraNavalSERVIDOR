package view;

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

public class TelaInicialController implements Initializable {

//----------------------------------ATRIBUTOS------------------------------------------------
	static ServerSocket servidor = null;
	private Socket cliente = null;

//---------------------------COMPONENTES DA TELA FXML----------------------------------------
	@FXML
	private Button btn_iniciarJogo;

	@FXML
	private Button btn_abrirServidor;

    @FXML
    private TextField txf_tamanhoDoTabuleiro;
    
    @FXML
    private Label lb_IP;
    

//-------------------------------------------------------------------------------------------

	@FXML
	void click_btnAbrirServidor(ActionEvent event) {

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

	public void receberAcessos() throws IOException {
		try {
			new GerenciadorDeClientes(cliente);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Porta indisponivel ou servidor disconectado", "ERRO",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

	}

	@FXML
	void click_btnIniciarJogo(ActionEvent event) {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

}

package view;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import connection.GerenciadorDeClientes;

public class TelaInicialController implements Initializable {

//----------------------------------ATRIBUTOS------------------------------------------------
	static ServerSocket servidor = null;
	private Socket cliente = null;

//---------------------------COMPONENTES DA TELA FXML----------------------------------------
	@FXML
	private Button btn_iniciarJogo;

	@FXML
	private Button btn_abrirServidor;

<<<<<<< HEAD
    @FXML
    private TextField txf_tamanhoDoTabuleiro;
    
    @FXML
    private Label lb_IP;
=======
	@FXML
	private TextField txf_tamanhoDoTabuleiro;
>>>>>>> b2d7c34107ab62d79e6c7d1ff8830a7ca157e8df

//-------------------------------------------------------------------------------------------

	@FXML
	void click_btnAbrirServidor(ActionEvent event) {

		try {
			// inicio server
			System.out.println("startando servidor");

			// porta de conex�o
			servidor = new ServerSocket(9999);

			// confirma��o de iniciado
			System.out.println("servidor startado");

			// esperando conex�es
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
<<<<<<< HEAD
=======
		// TODO Auto-generated method stub

>>>>>>> b2d7c34107ab62d79e6c7d1ff8830a7ca157e8df
	}

}

package view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class TelaInicialController implements Initializable {
//---------------------------COMPONENTES DA TELA FXML----------------------------------------
    @FXML
    private Button btn_iniciarJogo;

    @FXML
    private Button btn_abrirServidor;

    @FXML
    private TextField txf_tamanhoDoTabuleiro;

//-------------------------------------------------------------------------------------------
    
    
    @FXML
    void click_btnAbrirServidor(ActionEvent event) {

    }

    @FXML
    void click_btnIniciarJogo(ActionEvent event) {

    }	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}

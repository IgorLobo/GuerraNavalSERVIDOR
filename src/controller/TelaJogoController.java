package controller;

import java.awt.TextArea;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class TelaJogoController implements Initializable {
//-----------------------------------ATRIBUTOS-------------------------------------------
	
	
	
//---------------------------COMPONENTES DA TELA FXML----------------------------------------
	 @FXML
	    private AnchorPane pane;

	    @FXML
	    private GridPane grid_tabelaArmas;

	    @FXML
	    private Label lb_armasRestantes;

	    @FXML
	    private TextArea txtArea_jogadores;

	    @FXML
	    public static TextArea txtArea_historicoJogadas;

	    @FXML
	    public static GridPane grid_tabuleiroJogo;

	

//---------------------------------METODOS------------------------------------------------------
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}

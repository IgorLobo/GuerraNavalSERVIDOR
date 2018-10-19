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
	private static int tamanho;
	
	
	
//---------------------------COMPONENTES DA TELA FXML----------------------------------------
	 @FXML
	    private AnchorPane pane;

	    @FXML
	    private GridPane grid_tabelaArmas;

	    @FXML
	    private Label lb_armasRestantes;

	    @FXML
	    public GridPane grid_tabuleiroJogo;
	    
	    

	

//---------------------------------METODOS------------------------------------------------------
	
	    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1 ) {

	}

}

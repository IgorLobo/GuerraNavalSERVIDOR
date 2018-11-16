package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import connection.ControladorTCP;
import connection.Servidor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import model.Jogo;

public class TelaJogoController implements Initializable {

//-----------------------------------ATRIBUTOS-------------------------------------------
	public static int tamanho = -1;
	private String historico = "";
	private Jogo jogo = null;
	private ControladorTCP comunicador= null;
	private Button botoesDoTabuleiro[][];
	
	
	private int s= 0;
	private Thread escutar = null;
	private ObjectOutputStream objectOutputStream = null;
	private ObjectInputStream objectInputStream = null;
	private InputStream inputStream=null;
	private OutputStream outputStream=null;
//---------------------------COMPONENTES DA TELA FXML----------------------------------------

    @FXML
    private Label lb_armasRestantes;

    @FXML
    private TextArea txtArea_historicoJogadas;

    @FXML
    private GridPane grid_jogadores;
    
    @FXML
    private GridPane grid_tabuleiroJogo;
    
    @FXML
    private Button btn_encerrarJogo;
    
    @FXML
    private Button teste;

//--------------------------------------BUTTONS-----------------------------------------------
	private void iniciarJogo() {
		try {
			jogo = Servidor.jogo;
			grid_tabuleiroJogo.resize(calcularTamanhoDaGrid(),calcularTamanhoDaGrid());
			botoesDoTabuleiro = new Button[tamanho][tamanho];

			// For para criar o array de botoes com evento
			for (int i = 0; i < tamanho; i++) {
				for (int j = 0; j < tamanho; j++) {
					botoesDoTabuleiro[i][j] = new Button();
					botoesDoTabuleiro[i][j].setId(i + "," + j);
					botoesDoTabuleiro[i][j].setOnAction(new EventHandler<ActionEvent>() {
						public void handle(ActionEvent event) {
							
							
							
							
							Button botaoOnClick = (Button) event.getSource();
							s = jogo.disparo(botaoOnClick.getId());
							int[] coordenadas = traduzirCoordenadas(botaoOnClick.getId());
							botoesDoTabuleiro[coordenadas[0]][coordenadas[1]].setGraphic(new ImageView(new Image(jogo.getArmaURL(coordenadas[0],coordenadas[1]),60, 60, false, false)));
							atualizarHistorico(coordenadas[0], coordenadas[1]);
							lb_armasRestantes.setText(Integer.toString(jogo.getQntArmasRestantes()));
						}
					});
				}
			}

			for (int i = 0; i < tamanho; i++) {
				for (int j = 0; j < tamanho; j++) {
					grid_tabuleiroJogo.add(botoesDoTabuleiro[i][j], j, i);
					botoesDoTabuleiro[i][j].setGraphic(new ImageView(new Image(jogo.getArmaURL(i,j),60, 60, false, false)));
				}
			}
			atualizarListaDeJogadores();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
			e.getMessage();
		}
	}
    
    @FXML
    void clickBtn_encerrarJogo(ActionEvent event) {
    	try {
    		
    		escutar();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
    }

//---------------------------------METODOS------------------------------------------------------
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			iniciarJogo();
			escutar();
			//comunicacao();
			lb_armasRestantes.setText(Integer.toString(jogo.getQntArmasRestantes()));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void comunicacao() {
		try {
		comunicador = new ControladorTCP();
		comunicador.comunicador();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	private void atualizarHistorico(int linha, int coluna) {
		historico += linha + "," + coluna +" - " + jogo.getArmaNome(linha, coluna)  + " "+s+ "\n";
		txtArea_historicoJogadas.setText(String.format("%s", historico));
	}
	
	private int[] traduzirCoordenadas(String coordenadas) {
		String[] coordenadasXY = coordenadas.split(",");
		int[] resultado = new int[2];
		resultado[0] = Integer.parseInt(coordenadasXY[0]);
		resultado[1] = Integer.parseInt(coordenadasXY[1]);
		return resultado;
	}
	
	private int calcularTamanhoDaGrid() {
		int espaçamento = (10 * (tamanho - 1));
		int tamanhoDosBotoes = 60 * tamanho;
		return (espaçamento + tamanhoDosBotoes);
	}
	
	@SuppressWarnings("static-access")
	private void atualizarListaDeJogadores() {
		Label nome = new Label("Nome");
		nome.setFont(Font.font ("Arial Black", 16));
		Label pontos = new Label("Pontos");
		pontos.setFont(Font.font ("Arial Black", 16));
		grid_jogadores.setHalignment(nome, HPos.CENTER);
		grid_jogadores.setHalignment(pontos, HPos.CENTER);
		grid_jogadores.add(nome , 0, 0);
		grid_jogadores.add(pontos, 1, 0);
		
		for (int i = 0; i < jogo.getTamanhoArrayJogadores(); i++) {
		nome = new Label(jogo.getNomeJogador(i));
		pontos = new Label(Integer.toString(jogo.getPontosJogador(i)));
		grid_jogadores.getRowConstraints().add(new RowConstraints(25));
		grid_jogadores.add(nome ,0 , i+1 );
		grid_jogadores.add(pontos ,1 , i+1);
		grid_jogadores.setHalignment(nome, HPos.CENTER);
		grid_jogadores.setHalignment(pontos, HPos.CENTER);
		
		}

	}
	
	private void jogar() {
		try {
			String jogada = "";
			int resultado = 0;
			objectInputStream = new ObjectInputStream(Servidor.jogo.socketJogador().getInputStream());
			objectOutputStream = new ObjectOutputStream(Servidor.jogo.socketJogador().getOutputStream());		
			
			jogada = objectInputStream.readUTF();
			resultado = Servidor.jogo.disparo(jogada);
			
			objectOutputStream.writeUTF(String.valueOf(resultado));
			objectOutputStream.flush();			
		} catch (IOException e) {		
		}
	}
	
	public void escutar() {
		try {
			escutar = new Thread() {
				@Override
				public void run() {
					try {
						
						while (true) {
						objectOutputStream = new ObjectOutputStream(Servidor.jogo.socketJogador().getOutputStream());
						System.out.println("OK");
						objectOutputStream.writeUTF("true");
						objectOutputStream.flush();
						JOptionPane.showMessageDialog(null, "Aguardando jogador " + Servidor.jogo.getNomeJogador(Servidor.jogo.getIdJogadorDaVez()), "Jogada", JOptionPane.INFORMATION_MESSAGE);
						jogar();
						}						
						
					} catch (Exception e) {
					}
				}
				
			};
			escutar.start();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

}

package connection;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;

import javax.swing.JOptionPane;

import controller.TelaJogoController;
import model.Jogador;
import model.Jogo;

public class ControladorTCP implements Runnable{

	Thread comunicador = null;
	DataInputStream entrada = null;
	DataOutputStream saida = null;

	
	/*public ControladorTCP(int portaServer) throws IOException{
		ss = new ServerSocket(portaServer);
		socket = ss.accept();
		
	}*/
	
	public void enviarMensagem(String mensagem) throws IOException{
		saida.writeUTF(mensagem);
		saida.flush();
	}
	
	public String receberMensagem()throws IOException{
		return entrada.readUTF();
	}
	
	public synchronized void comunicador() throws IOException {
		if (comunicador == null) {
			this.comunicador = new Thread(this, "Thread-Comunicadora");
			this.comunicador.start();
		}
	}

	@Override
	public void run() {
		try {			
			while (Servidor.statusServidor) {
				entrada = new DataInputStream(Servidor.jogo.socketJogador().getInputStream());
				saida = new DataOutputStream(Servidor.jogo.socketJogador().getOutputStream());
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}
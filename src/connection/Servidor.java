package connection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

import model.Jogador;
import controller.TelaInicialController;
import controller.TelaJogoController;
import model.Jogo;

public class Servidor implements Runnable {

	private ObjectOutputStream objectOutputStream = null;
	private ObjectInputStream objectInputStream = null;
	private InputStream inputStream = null;
	private OutputStream outputStream = null;

	private Thread runServidor = null;
	private ServerSocket serverSocket = null;
	public volatile static boolean statusServidor = false;
	private int portaServidor = 5555;
	public static Jogo jogo;

	public Servidor(int porta) {
		this.runServidor = null;
		this.serverSocket = null;
		this.statusServidor = false;
		this.portaServidor = porta;
	}

	public Thread getRunServidor() {
		return runServidor;
	}

	public void setRunServidor(Thread runServidor) {
		this.runServidor = runServidor;
	}

	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

	public boolean getStatusServidor() {
		return statusServidor;
	}

	public void setStatusServidor(boolean statusServidor) {
		this.statusServidor = statusServidor;
	}

	public int getPortaServidor() {
		return portaServidor;
	}

	public void setPortaServidor(int portaServidor) {
		this.portaServidor = portaServidor;
	}

	public synchronized void startServer() throws IOException {
		if (runServidor == null) {
			this.serverSocket = new ServerSocket(this.portaServidor);
			this.runServidor = new Thread(this, "Thread-Servidor");
			this.runServidor.start();
			this.statusServidor = true;
		}
	}

	public synchronized void stopServer() throws IOException {

		if (serverSocket != null && runServidor != null) {
			this.statusServidor = false;
			this.runServidor.interrupt();
			this.runServidor = null;
			try {
				this.serverSocket.close();

			} catch (IOException e) {
				System.out.println(e.getLocalizedMessage());
			}
		}
	}

	// protocolo de conexão, gerenciamento de acontecimentos
	@Override
	public void run() {
		try {
			int id = 0;
			String ipJogador = "";
			String mensagemCliente = "";
			jogo = new Jogo(TelaJogoController.tamanho);
			Jogador jogador = null;
			// jogo.setVezJogo(0);

			while (this.statusServidor) {
				Socket socket = this.serverSocket.accept();
				System.out.println("Entrou o trodos");

				objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
				objectInputStream = new ObjectInputStream(socket.getInputStream());
				
				
				
				objectOutputStream.writeUTF(String.valueOf(TelaJogoController.tamanho));
				objectOutputStream.flush();				
				
				mensagemCliente = objectInputStream.readUTF();
				

				ipJogador = socket.getInetAddress().getHostAddress();
				jogador = new Jogador(socket, id, ipJogador, mensagemCliente);
				jogo.setJogadores(jogador);
				id++;
				objectInputStream.close();	
				objectOutputStream.close();
				socket.close();
			}

		} catch (Exception e) {			
		}
	}
}

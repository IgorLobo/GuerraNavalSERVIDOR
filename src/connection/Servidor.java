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

import controller.TelaInicialController;
import model.Jogo;

public class Servidor extends Thread {

	private Socket socketCliente;
	private int ip;
	private String nomeCliente;
	private int portaServidor;
	private InputStream inputStream = null;
	private InputStreamReader inputStreamReader = null;
	private BufferedReader bufferedReader = null;

	public Servidor() {
	}

	public Servidor(Socket socketCliente) {
		try {
			this.socketCliente = socketCliente;
			this.inputStream = socketCliente.getInputStream();
			this.inputStreamReader = new InputStreamReader(this.inputStream);
			this.bufferedReader = new BufferedReader(this.inputStreamReader);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// protocolo de conexão, gerenciamento de acontecimentos
	@Override
	public void run() {
		try {
			String mensagemTeste = "";
			OutputStream outputStream = this.socketCliente.getOutputStream();
			Writer writer = new OutputStreamWriter(outputStream);
			BufferedWriter bufferedWriter = new BufferedWriter(writer);
			Jogo.conexoesArrayList.add(bufferedWriter);
			mensagemTeste = this.bufferedReader.readLine();
			
			while (true) {
				
			}
			

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Cliente fechou a conexão", "ERRO", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * 
	 * 
			// entrada e saída do servidor para client
			BufferedReader leitor = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			PrintWriter escritor = new PrintWriter(cliente.getOutputStream(), true);
			escritor.println("escreva o nome");
			String msg = leitor.readLine();
			this.nomeCliente = msg;
			escritor.println("olá nome " + msg);

			while (true) {
				msg = leitor.readLine();
				escritor.println(msg + msg);
			}
	 * ----------Fechamento de conexão------- public void fecharSocket(Socket
	 * socket) throws IOException { socket.close(); }
	 * 
	 * 
	 * 
	 * 
	 * *--------Validação de chegada e sáida (protocolo)-------* public void
	 * tratamentoConexao(Socket socket) throws IOException { try {
	 * ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
	 * ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
	 * 
	 * String msg = input.readUTF(); System.out.println("Mensagem recebida");
	 * output.writeUTF("TESTE DA PORRA");
	 * 
	 * // fecha entrada/saída de dadados output.close(); input.close();
	 * 
	 * } catch (IOException e) { // tratamento de falhas } finally {
	 * fecharSocket(socket); } }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

}

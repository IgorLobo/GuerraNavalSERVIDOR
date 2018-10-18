package connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

import controller.TelaInicialController;

public class GerenciadorDeClientes extends Thread{

	private Socket cliente;
	private String nomeCliente;

	public GerenciadorDeClientes(Socket cliente) {
		this.cliente = cliente;
		start();
	}
	
	
	//protocolo de conex�o, gerenciamento de acontecimentos
	@Override
	public void run() {
		try {
			//entrada e sa�da do servidor para client
			BufferedReader leitor = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			PrintWriter escritor = new PrintWriter(cliente.getOutputStream(),true);
			escritor.println("escreva o nome");
			String msg = leitor.readLine();
			this.nomeCliente= msg;
			escritor.println("ol� nome " + msg);
			
			
			while(true) {
				msg = leitor.readLine();
				escritor.println(msg+msg);
			}
			
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Cliente fechou a conex�o", "ERRO", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * 
	 *----------Fechamento de conex�o-------
	 * public void fecharSocket(Socket socket) throws IOException {
		socket.close();
	}
	 * 
	 * 
	 * 
	 * 
	 * *--------Valida��o de chegada e s�ida (protocolo)-------*
	 * public void tratamentoConexao(Socket socket) throws IOException {
		try {
			ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

			String msg = input.readUTF();
			System.out.println("Mensagem recebida");
			output.writeUTF("TESTE DA PORRA");

			// fecha entrada/sa�da de dadados
			output.close();
			input.close();

		}
		catch (IOException e) {
			// tratamento de falhas
		}
		finally {
			fecharSocket(socket);
		}
	}
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	
}


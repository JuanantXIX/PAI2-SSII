
package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.net.ServerSocketFactory;

public class IntegrityVerifierServer extends Thread {

	private ServerSocket serverSocket;


	// Constructor del Servidor
	public IntegrityVerifierServer() throws Exception {
		// ServerSocketFactory para construir los ServerSockets
		ServerSocketFactory socketFactory = ServerSocketFactory.getDefault();
		// Creaci�n de un objeto ServerSocket escuchando peticiones en el puerto 7070
		this.serverSocket = socketFactory.createServerSocket(7070);
	}
	// Ejecuci�n del servidor para escuchar peticiones de los clientes
	public void runServer() {
		while (true) {
			// Espera las peticiones del cliente para comprobar mensaje/MAC
			try {
				System.err.println("Esperando conexiones de clientes...");
				Socket socket = this.serverSocket.accept();
				// Abre un BufferedReader para leer los datos del cliente
				BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				// Abre un PrintWriter para enviar datos al cliente
				PrintWriter output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
				// Se lee del cliente el mensaje y el macdelMensajeEnviado
				String mensaje = input.readLine();
				// A continuaci�n habr�a que calcular el mac del MensajeEnviado que podr�a ser
				String macdelMensajeEnviado = input.readLine();
				//mac del MensajeCalculado
				System.out.println(mensaje + " " + macdelMensajeEnviado);
				if (macdelMensajeEnviado.equals(mensaje)) {
					output.println("Mensaje enviado integro ");
				} else {
					output.println("Mensaje enviado no integro.");
				}
				output.close();
				input.close();
				socket.close();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}
	}
	@Override
	public void run() {
		this.runServer();
	}
}
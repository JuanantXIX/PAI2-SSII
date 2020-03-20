
package main;

import java.math.BigDecimal;

import cliente.IntegrityVerifierClient;
import servidor.NoIntegrityVerifierServer;

public class MainWithManInTheMiddleInServer {

	public static void main(final String[] args) throws Exception {
		NoIntegrityVerifierServer server = new NoIntegrityVerifierServer(new BigDecimal("123"));
		server.start();
		IntegrityVerifierClient cliente = new IntegrityVerifierClient(new BigDecimal("541"), new BigDecimal("855"), new BigDecimal("978"), "Test message v2");
		cliente.runCliente();
		System.exit(0);

	}

}

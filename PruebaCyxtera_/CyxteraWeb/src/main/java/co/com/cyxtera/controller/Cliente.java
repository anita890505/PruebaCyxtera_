package co.com.cyxtera.controller;

import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

/**
 * Será la clase base que se utilizará para todos 
 * los clientes que deseen, 
 * usarlo los métodos principales:
 * @author Ana Maria Alvarez
 *
 */
public class Cliente {

	private String url;
	private String contextPath;
	
	private static final Logger log = Logger.getLogger(Client.class.getName());
	
	public Cliente(String url, String contextPath) {
		super();
		this.url = url;
		this.contextPath = contextPath;
	}
	
	/**
	 * Crea el cliente HTTP para invocar la petición REST, 
	 * en caso de requerir autenticar la petición este es el lugar para hacerlo.
	 * @param path
	 * @return
	 */
	protected WebTarget createClient(String path) {
        String assembledPath = assembleEndpoint(path);
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(assembledPath);
        return target;
    }
	
	/**
	 * Construye la url a ejecutar
	 * @param path
	 * @return
	 */
	private String assembleEndpoint(String path) {
        String endpoint = url.concat(contextPath).concat(path);
        log.info(String.format("Calling endpoint %s", endpoint));
        return endpoint;
    }
	
	
}
package co.com.cyxtera.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import co.com.cyxtera.modelo.Operacion;

public class BitsoClient extends Cliente{
	
	private static final Logger log = Logger.getLogger(BitsoClient.class.getName());
	 
    public BitsoClient(String url, String contextPath) {
        super(url, contextPath);
    }
 
    /**
     * retona el resultado
     * @return
     * @throws ServiceException
     */
    public Operacion getResult(List<Double> d, List<String>o) throws ServiceException {
        log.info("Getting ripple price");
        WebTarget client = createClient(ApplicationEndpoint.getResultado(d,o));
        Response response = client.request(MediaType.APPLICATION_JSON).get();
        log.info("Status " + response.getStatus());
        Operacion result = new Operacion();
        Integer status = response.getStatus();
        if (Status.OK.getStatusCode() == status) {
            result = response.readEntity(Operacion.class);
        } else {
            throw new ServiceException(response.readEntity(String.class), status);
        }
        return result;
    }
 
    /**
     * retorna los numeros
     * @return
     * @throws ServiceException
     */
    public Double getNumero(Double num) throws ServiceException {
        log.info("Getting ripple price");
        List<Operacion> rs = new ArrayList<Operacion>();
        WebTarget client = createClient(ApplicationEndpoint.getNumeros(num));
        Response response = client.request(MediaType.APPLICATION_JSON).get();
        log.info("Status " + response.getStatus());
        Double result = 0.0;
        Integer status = response.getStatus();
        if (Status.OK.getStatusCode() == status) {
        	result= (response.readEntity(Operacion.class).getNumDos());
            
        } else {
            throw new ServiceException(response.readEntity(String.class), status);
        }
        return result;
    }
    
    /**
     * retorna las operaciones
     * @return
     * @throws ServiceException
     */
    public String getOperaciones(String oper) throws ServiceException {
        log.info("Getting ripple price");
        WebTarget client = createClient(ApplicationEndpoint.getOperaciones(oper));
        Response response = client.request(MediaType.APPLICATION_JSON).get();
        log.info("Status " + response.getStatus());
        String result = null;
        Integer status = response.getStatus();
        if (Status.OK.getStatusCode() == status) {
        	result=response.readEntity(Operacion.class).getOperacion();
        } else {
            throw new ServiceException(response.readEntity(String.class), status);
        }
        return result;
    }
}

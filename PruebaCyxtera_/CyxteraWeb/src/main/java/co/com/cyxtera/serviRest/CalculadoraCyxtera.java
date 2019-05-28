package co.com.cyxtera.serviRest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import co.com.cyxtera.enums.ServiciosEnum;
import co.com.cyxtera.modelo.Auditoria;
import co.com.cyxtera.modelo.Operacion;
import co.com.cyxtera.servicio.CyxteraServicio;


/**
 * servicio a exponer para la creacion de la calculdora
 * @author Ana Maria Alvarez
 * @fecha 27/05/2019
 * 
 *
 */
@Path("/cyxteraCalculo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Stateless
public class CalculadoraCyxtera {

	/*
	 * variables a utilizar
	 */
	@EJB
	public CyxteraServicio cyxtera;
	private Logger log;
	private String acction;
	private List<Operacion> operacion;
	private List<Double> numerosCarga;
	private List<String> operacionesCarga;
	
	/**
	 * constructor
	 */
	public CalculadoraCyxtera() {
		super();
		log = Logger.getLogger(CalculadoraCyxtera.class.getName());
		acction = null;
		operacion = new ArrayList<Operacion>();
		numerosCarga = new ArrayList<Double>(); 
		operacionesCarga = new ArrayList<String>();
	}

	/**
	 * metodo de creacion de la sesion,
	 * @return retorna 1 al crear la sesion
	 */
	@GET
	@Path("/crearSesion")
	public Response crearSesion() {
		String mensaje = null;
		Response r = null;
		try {
			log.info("llamado al servicio de crear sesion");
			mensaje =String.valueOf(cyxtera.creaSesion(ServiciosEnum.NUEVASESION.getOper()));
			Auditoria aud = new Auditoria();
			aud.setFechaCreacion(new Date());
			aud.setServicio(ServiciosEnum.NUEVASESION.getOper());
			aud.setTransaccion(ServiciosEnum.NUEVASESION.getId().toString());
			aud.setUsuario("webservice");
			cyxtera.crearAuditoria(aud);
			setAcction(mensaje);
			r = Response.ok(mensaje).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}

	/**
	 * metodo de carna de numeros
	 * @param numero
	 * @return
	 */
	@POST
	@Path("/adicionarNumero")
	public Response adicionNumero(@PathParam("numero") Double numero) {
		Response r = null;
		try {
			log.info("envio de valor");
			 numerosCarga.add(numero);
			 Auditoria aud = new Auditoria();
				aud.setFechaCreacion(new Date());
				aud.setServicio(ServiciosEnum.OPERACION.getOper());
				aud.setTransaccion(ServiciosEnum.OPERACION.getId().toString());
				aud.setUsuario("webservice");
				cyxtera.crearAuditoria(aud);
			 r = Response.ok("ok").build();
		} catch (Exception e) {
			log.info("error al hacer llamado al servicio de crear sesion");
			e.printStackTrace();
		}
		return r;
	}
	
	/**
	 * metodo de seleccion de operacion
	 * @param operacion
	 * @return
	 */
	@POST
	@Path("/adicionarOperadores")
	public Response adicionOperacion(@PathParam("operacion") String operacion) {
		Response r = null;
		try {
			log.info("envio de valor");
			operacionesCarga.add(cyxtera.seleccionOperacion(operacion));
			 Auditoria aud = new Auditoria();
				aud.setFechaCreacion(new Date());
				aud.setServicio(ServiciosEnum.OPERADOR.getOper());
				aud.setTransaccion(ServiciosEnum.OPERADOR.getId().toString());
				aud.setUsuario("webservice");
				cyxtera.crearAuditoria(aud);
			r = Response.ok("ok").build();
		} catch (Exception e) {
			log.info("error al hacer llamado al servicio de crear sesion");
			e.printStackTrace();
		}
		return r;
	}
	
	/**
	 * retorna el resulado segun la operacion escogida
	 * @return
	 */
	@POST
	@Path("/verResultado")
	public Response resultado(@PathParam("numerosCarga")
			List<Double>numerosCarga, @PathParam("operacionesCarga") List<String>operacionesCarga ) {
		Response r = null;
		try {
			log.info("envio de valor");
			List<Operacion> opera = new ArrayList<Operacion>();
			if(numerosCarga.size()>0 && operacionesCarga.size()>0) {
				Operacion operacion = new Operacion();
				for(Double num : numerosCarga) {
					if(operacion.getNumUno()>0.0) {
						operacion.setNumUno(num);	
					}else {
						operacion.setNumDos(num);
					}
					
					if(operacion.getNumDos()>0.0) {
						for(String op : operacionesCarga) {
							operacion.setOperacion(op);
						}
					}
					opera.add(operacion);
				}
				operacion.setResult(cyxtera.realizarOperacion(opera));
				Auditoria aud = new Auditoria();
				aud.setFechaCreacion(new Date());
				aud.setServicio(ServiciosEnum.RESULTADO.getOper());
				aud.setTransaccion(ServiciosEnum.RESULTADO.getId().toString());
				aud.setUsuario("webservice");
				cyxtera.crearAuditoria(aud);
				
				 r = Response.ok(operacion.getResult()).build();
			}
			
			
		} catch (Exception e) {
			log.info("error al hacer llamado al servicio de crear sesion");
			e.printStackTrace();
		}
		return r;
	}
	
	/**
	 * lista el log de auditoria
	 * @return
	 */
	@GET
	@Path("/verAudotoria")
	public Response verAuditoria() {
		Response r = null;
		try {
			log.info("envio de valor");
			List<Auditoria> audit =  cyxtera.consultaAuditoria();
			r = Response.ok(audit).build();
		} catch (Exception e) {
			log.info("error al hacer llamado al servicio de crear sesion");
			e.printStackTrace();
		}
		return r;
	}

	/*
	 * metodos set y get utilizados
	 */
	public String getAcction() {
		return acction;
	}


	public void setAcction(String acction) {
		this.acction = acction;
	}

	public List<Operacion> getOperacion() {
		return operacion;
	}

	public void setOperacion(List<Operacion> operacion) {
		this.operacion = operacion;
	}
	
}

package co.com.cyxtera.servicio;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import co.com.cyxtera.fachada.CyxteraFachada;
import co.com.cyxtera.modelo.Auditoria;
import co.com.cyxtera.modelo.Operacion;

@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class CyxteraServicio {
	
	@EJB
	CyxteraFachada facade;
	
	/**
	 * metodo que crea la auditoria de cualquier operacion
	 * @param auditoria
	 * @return
	 * @throws Exception
	 */
	public String crearAuditoria(Auditoria auditoria) throws Exception{
		return facade.crearAuditoria(auditoria);
	}
	
	/**
	 * metodo que lista las auditorias
	 * @return
	 * @throws Exception
	 */
	public List<Auditoria> consultaAuditoria() throws Exception{
		return facade.consultaAuditoria();
	}
	
	/**
	 * metodo que genera la sesion a realizar
	 * @param opcion
	 * @return
	 * @throws Exception
	 */
	public int creaSesion(String opcion) throws Exception{
		return facade.creaSesion(opcion);
	}
	
	/**
	 * metodo que consulta la seleccion de la operacion
	 * @param operacion
	 * @return
	 * @throws Exception
	 */
	public String seleccionOperacion(String operacion) throws Exception{
		return facade.seleccionOperacion(operacion);
	}
	
	/**
	 * metodo que realiza la operacion seleccionada con los numeros enviados
	 * @param numeros
	 * @return
	 * @throws Exception
	 */
	public Double realizarOperacion(List<Operacion> operacion) throws Exception{
		return facade.realizarOperacion(operacion);
	}
	
}

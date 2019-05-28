package co.com.cyxtera.fachada;

import java.util.List;

import javax.ejb.Remote;

import co.com.cyxtera.modelo.Auditoria;
import co.com.cyxtera.modelo.Operacion;




@Remote
public interface CyxteraFachada {
	
	/**
	 * metodo que crea la auditoria de cualquier operacion
	 * 
	 * @param auditoria
	 * @return
	 * @throws Exception
	 */
	public String crearAuditoria(Auditoria auditoria) throws Exception;

	/**
	 * metodo que lista las auditorias
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Auditoria> consultaAuditoria() throws Exception;

	/**
	 * metodo que genera la sesion a realizar
	 * 
	 * @param opcion
	 * @return
	 * @throws Exception
	 */
	public int creaSesion(String opcion) throws Exception;

	/**
	 * metodo que consulta la seleccion de la operacion
	 * 
	 * @param operacion
	 * @return
	 * @throws Exception
	 */
	public String seleccionOperacion(String operacion) throws Exception;

	/**
	 * metodo que realiza la operacion seleccionada con los numeros enviados
	 * 
	 * @param numeros
	 * @return
	 * @throws Exception
	 */
	public Double realizarOperacion(List<Operacion> operacion) throws Exception;
}

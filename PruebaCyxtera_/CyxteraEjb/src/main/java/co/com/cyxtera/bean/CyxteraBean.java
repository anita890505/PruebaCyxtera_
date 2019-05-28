package co.com.cyxtera.bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.com.cyxtera.enums.OperacionesEnum;
import co.com.cyxtera.enums.ServiciosEnum;
import co.com.cyxtera.fachada.CyxteraFachada;
import co.com.cyxtera.modelo.Auditoria;
import co.com.cyxtera.modelo.Operacion;



@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CyxteraBean implements CyxteraFachada {

	@PersistenceContext(unitName = "facturacionCyxtera")
	private EntityManager em;

	public CyxteraBean() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see co.com.seriviceejb.fachada.ServiceWebFacade#crearAuditoria(co.com.servicemodel.entity.Auditoria)
	 */
	@Override
	public String crearAuditoria(Auditoria auditoria) throws Exception {
		if(auditoria.getUsuario()!=null && !auditoria.getUsuario().equalsIgnoreCase("")) {
			em.persist(auditoria);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see co.com.seriviceejb.fachada.ServiceWebFacade#consultaAuditoria()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Auditoria> consultaAuditoria() throws Exception {
		List<Auditoria> aud = new ArrayList<Auditoria>();
		Query q = em.createNamedQuery(Auditoria.CONSULTAR_AUDITORIA);
		aud = q.getResultList();
		if(aud.size()<=0) {
			aud = null;
		}
		return aud;
	}

	/*
	 * (non-Javadoc)
	 * @see co.com.seriviceejb.fachada.ServiceWebFacade#creaSesion(java.lang.String)
	 */
	@Override
	public int creaSesion(String opcion) throws Exception {
		int id = 0;
		if(opcion!=null && !opcion.equalsIgnoreCase("")) {
			switch (opcion) {
				case "NUEVASESION":
					id = ServiciosEnum.NUEVASESION.getId();
				break;
				case "OPERADOR":
					id = ServiciosEnum.OPERADOR.getId();
				break;
				case "OPERACION":
					id = ServiciosEnum.OPERACION.getId();
				break;
			}
		}
		return id;
	}

	/*
	 * (non-Javadoc)
	 * @see co.com.seriviceejb.fachada.ServiceWebFacade#seleccionOperacion(java.util.List)
	 */
	@Override
	public String seleccionOperacion(String operacion) throws Exception {
		String resul = null;
		if(operacion!=null) {
				switch (operacion) {
					case "SUMA":
						resul = OperacionesEnum.SUMA.getId().toString();
					break;
					case "RESTA":
						resul = OperacionesEnum.RESTA.getId().toString();
					break;
					case "MULTIPLICACION":
						resul = OperacionesEnum.MULTIPLICACION.getId().toString();
					break;
					case "DIVISION":
						resul = OperacionesEnum.DIVISION.getId().toString();
					break;
					case "POTENCIACION":
						resul = OperacionesEnum.POTENCIACION.getId().toString();
					break;

				}
		}
		return resul;
	}

	/*
	 * (non-Javadoc)
	 * @see co.com.seriviceejb.fachada.ServiceWebFacade#realizarOperacion(java.util.List)
	 */
	@Override
	public Double realizarOperacion(List<Operacion> operacion) throws Exception {
		Double valor = 0.0;
		Double res=0.0;
		Double temp=0.0;
		if(operacion.size()>0) {
			for(Operacion oper: operacion) {
				if(oper.getOperacion().equals(OperacionesEnum.SUMA.getId().toString())) {
					res = oper.getNumUno() + oper.getNumDos();
					temp  = res;
					oper.setResult(res);
				}else if(oper.getOperacion().equals(OperacionesEnum.RESTA.getId().toString())) {
					res = oper.getNumUno() - oper.getNumDos();
					temp  = res;
					oper.setResult(res);
				}else if(oper.getOperacion().equals(OperacionesEnum.MULTIPLICACION.getId().toString())) {
					res = oper.getNumUno()* oper.getNumDos();
					temp  = res;
					oper.setResult(res);
				}else if(oper.getOperacion().equals(OperacionesEnum.DIVISION.getId().toString())) {
					res = oper.getNumUno() / oper.getNumDos();
					temp  = res;
					oper.setResult(res);
				}else if(oper.getOperacion().equals(OperacionesEnum.POTENCIACION.getId().toString())) {
					res = Math.pow(oper.getNumUno(),oper.getNumDos());
					temp  = res;
					 oper.setResult(res);
				}
			}
		}
		return valor;
	}

}

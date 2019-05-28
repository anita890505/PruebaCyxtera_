package co.com.cyxtera.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import co.com.cyxtera.enums.OperacionesEnum;
import co.com.cyxtera.modelo.Operacion;
import co.com.cyxtera.servicio.CyxteraServicio;

@ManagedBean(name = "fact")
@SessionScoped
public class FactutacionControl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Properties prop = new Properties();
	private String user;
	private Double numero;
	private List<OperacionesEnum> oper;
	private List<Double> n;
	private List<String> o;
	

	@EJB
	CyxteraServicio facturacionServicio;

	public FactutacionControl() {
		super();
		cargarPropiedades();

	}

	/**
	 * metodo especializado en inicializar variables
	 */
	public void cargarPropiedades() {
		prop = new Properties();
		try {
			numero =0.0;
			oper = new ArrayList<OperacionesEnum>();
			n = new ArrayList<Double>();
			o = new ArrayList<String>();
			oper.add(OperacionesEnum.DIVISION);
			oper.add(OperacionesEnum.SUMA);
			oper.add(OperacionesEnum.RESTA);
			oper.add(OperacionesEnum.MULTIPLICACION);
			oper.add(OperacionesEnum.POTENCIACION);
			prop.load(FactutacionControl.class.getResourceAsStream("mensajes.properties"));
			user = ((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					.get("app.user.name"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@PostConstruct
	public void cargarPermisos() {

	}

	/**
	 * metodo que se encarga de persistir la factura del cliente
	 */
	public void opera(Double valor) {
		try {
			if (valor>0) {
				BitsoClient client = new BitsoClient("http://localhost:8081/CyxteraWeb/calculadora/cysteraCalculo/", "adicionarNumero");
				Double ripplePrice = client.getNumero(valor);
				n.add(valor);
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "", prop.getProperty("exitoGuardado")));
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "", prop.getProperty("errorDatosBusqueda")));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "", prop.getProperty("erroProceso")));
		}
	}

	public void sigo(String valor) {
		String operacion = null;
		
		try {
			switch (valor) {
			case "+":
				operacion = OperacionesEnum.SUMA.getNombre();
				break;
			case "-":
				operacion = OperacionesEnum.RESTA.getNombre();
				break;
			case "*":
				operacion = OperacionesEnum.MULTIPLICACION.getNombre();
				break;
			case "/":
				operacion = OperacionesEnum.DIVISION.getNombre();
				break;
		}
		
		BitsoClient client = new BitsoClient("http://localhost:8081/CyxteraWeb/calculadora/cysteraCalculo/", "adicionarOperadores");
			o.add(client.getOperaciones(operacion));
			
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * limpia y permite crear una nueva a factura a un cliente nuevo
	 */
	public void resultado() {
		try {
			BitsoClient client = new BitsoClient("http://localhost:8081/CyxteraWeb/calculadora/cysteraCalculo/", "adicionarOperadores");
			numero = Double.valueOf(client.getResult(n,o).getResult());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * cierra el dialogo de creacion de detalle
	 */
	public void cerrarDialogo() {
	}

	/**
	 * abre el dialogo de creacion de detalle
	 */
	public void abreDialogo() {
	}

	public Properties getProp() {
		return prop;
	}

	public void setProp(Properties prop) {
		this.prop = prop;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}


	public Double getNumero() {
		return numero;
	}

	public void setNumero(Double numero) {
		this.numero = numero;
	}

}

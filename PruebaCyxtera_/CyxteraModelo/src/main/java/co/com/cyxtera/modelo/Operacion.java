package co.com.cyxtera.modelo;

public class Operacion {
	
	private Integer id;
	private String operacion;
	private Double numUno;
	private Double numDos;
	private Double result;
	
	public Operacion() {
		super();
	}

	public Operacion(Integer id, String operacion, Double numUno, Double numDos, Double result) {
		super();
		this.id = id;
		this.operacion = operacion;
		this.numUno = numUno;
		this.numDos = numDos;
		this.result = result;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOperacion() {
		return operacion;
	}
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	public Double getNumUno() {
		return numUno;
	}
	public void setNumUno(Double numUno) {
		this.numUno = numUno;
	}
	public Double getNumDos() {
		return numDos;
	}
	public void setNumDos(Double numDos) {
		this.numDos = numDos;
	}
	public Double getResult() {
		return result;
	}
	public void setResult(Double result) {
		this.result = result;
	}
	
	
	
	

}

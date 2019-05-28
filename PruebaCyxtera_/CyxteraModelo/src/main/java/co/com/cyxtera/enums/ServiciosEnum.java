package co.com.cyxtera.enums;

public enum ServiciosEnum {

	NUEVASESION(1,"NUEVASESION"),
	OPERADOR(2,"NUMERO INGREADO"),
	OPERACION(3,"OPERACION"),
	RESULTADO(4,"RESULTADO_OPERA");
	
	private Integer id;
	private String oper;
	
	private ServiciosEnum(Integer id, String oper) {
		this.id = id;
		this.oper = oper;
	}
	public Integer getId() {
		return id;
	}
	public String getOper() {
		return oper;
	}
	
	@Override
	public String toString() {
		return id.toString();
	}
	
}
